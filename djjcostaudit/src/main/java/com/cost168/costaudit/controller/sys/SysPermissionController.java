package com.cost168.costaudit.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.sys.SysPermission;
import com.cost168.costaudit.service.sys.SysPermissionService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.utils.JsonUtils;
import com.cost168.costaudit.utils.OrderUtil;

/**
 * 
 * ClassName: SysPermissionController 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-29上午9:24:59
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/sysPermission")
public class SysPermissionController {
	
	
	@Autowired
	public SysPermissionService sysPermissionService;
	
	
	@RequestMapping("/treeList")
	@ResponseBody
	public List<Map<String,Object>> perTreeList(HttpSession session,@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SysPermission> rootList = sysPermissionService.getRoots();
		String roleName=(String) session.getAttribute("roleName");
		if(!CollectionUtils.isEmpty(rootList)){
			for(SysPermission root:rootList){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", root.getId());
				map.put("text",root.getName());
				//map.put("state", "closed"); //根结点默认收起
				if(!CollectionUtils.isEmpty(sysPermissionService.getChilds(root))){
					try {
						map.put("children", getChildren(root,roleName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(map);
			}
		}
		return list;
	}
	
	private List<Map<String,Object>> getChildren(SysPermission root,String roleName){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SysPermission> functionSet = sysPermissionService.getChilds(root);
		List<String> per=new ArrayList<String>();
		per.add("菜单列表");
		per.add("添加菜单");
		per.add("修改菜单");
		per.add("删除菜单");
		per.add("审价类型");
		per.add("添加审价类型");
		per.add("修改审价类型");
		per.add("删除审价类型");
		per.add("合同类型");
		per.add("添加合同类型");
		per.add("修改合同类型");
		per.add("删除合同类型");
		
		if(!CollectionUtils.isEmpty(functionSet)){
			for(SysPermission function:functionSet){
				if(!"超级管理员".equals(roleName)){
					if(per.contains(function.getName())){
						continue;
					}
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", function.getId());
				map.put("text",function.getName());
				if(!CollectionUtils.isEmpty(sysPermissionService.getChilds(function))){
					map.put("children", getChildren(function,roleName));
				}
				list.add(map);
			}
		}
		return list;
	}
	
	
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult list(HttpServletRequest request,SysPermission permission, int page, int rows){
		Map<String,Object> selectMap=JsonUtils.object2Map(permission);
		EUDataGridResult result=new EUDataGridResult();
		String parentId = request.getParameter("parentId");
		Map<String,Object> map=new HashMap<String,Object>();
		map.putAll(selectMap);
		map.put("parentId", parentId);
		map.put("isPage", true);
	    map.put("curPage", rows*(page-1));
	    map.put("pageSize", rows);
		List<SysPermission> list = sysPermissionService.getList(map);
		int total = sysPermissionService.getCount(map);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@RequestMapping("/upd")
	@ResponseBody
	public GlobalResult upd(HttpServletRequest request,SysPermission sysPermission){
		GlobalResult result=new GlobalResult();
		try {
			sysPermissionService.updateByPrimaryKeySelective(sysPermission);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public GlobalResult save(HttpServletRequest request,SysPermission sysPermission){
		GlobalResult result=new GlobalResult();
		try {
			String id = OrderUtil.buildOrderId("");
			sysPermission.setId(id);
			sysPermission.setCreateTime(new Date());
			sysPermissionService.insertSelective(sysPermission);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/del")
	public GlobalResult del(HttpServletRequest request) {
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			for(String i:arr){
				if(i!=null && !"".equals(i)){
					sysPermissionService.deleteByPrimaryKey(i);
				}
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@RequestMapping("/findDetails")
	@ResponseBody
	public Map<String,Object> findDetails(HttpServletRequest request,HttpServletResponse respones){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//获取当前展开节点ID
			String id = request.getParameter("id");
			if(null != id && id.length() > 0){
				SysPermission sysPermission = sysPermissionService.selectByPrimaryKey(id);
				SysPermission orgParent = sysPermissionService.selectByPrimaryKey(sysPermission.getPid());
				if(sysPermission != null){
					map.put("id", sysPermission.getId());
					map.put("name", sysPermission.getName());
					map.put("pid", sysPermission.getPid());
					map.put("pidName", orgParent.getName());
					map.put("num", sysPermission.getNum());
					map.put("url", sysPermission.getUrl());
					map.put("permission", sysPermission.getPermission());
					map.put("description", sysPermission.getDescription());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
