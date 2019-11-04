package com.cost168.costaudit.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.sys.SysRole;
import com.cost168.costaudit.service.sys.SysRolePermissionService;
import com.cost168.costaudit.service.sys.SysRoleService;
import com.cost168.costaudit.service.sys.SysUserRoleService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.utils.JsonUtils;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
		
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpSession session,HttpServletRequest request,SysRole sysRole, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(sysRole);
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    String roleName=(String) session.getAttribute("roleName");
		    if(!"超级管理员".equals(roleName)){
		    	map.put("hideRole", "超级管理员");
		    }
		    List<SysRole> list= sysRoleService.selectListByMap(map);
		    int total= sysRoleService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/roleList")
	public List<SysRole> roleList(HttpSession session,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
	    String roleName=(String) session.getAttribute("roleName");
	    if(!"超级管理员".equals(roleName)){
	    	map.put("hideRole", "超级管理员");
	    }
	    map.put("status", "可用");
		List<SysRole> list= sysRoleService.selectListByMap(map);
		return list;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String url="";
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			url="/sysRole/upd";
			SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
			String perIds=sysRolePermissionService.selectPermissionByRoleId(id);
			sysRole.setPermissionId(perIds);
			request.setAttribute("obj", sysRole);
		}else{
			url="/sysRole/save";
			String roleId=UUID.randomUUID().toString().replace("-", "");
			SysRole sysRole=new SysRole();
			sysRole.setId(roleId);
			request.setAttribute("obj", sysRole);
		}
		request.setAttribute("url", url);
		return "sys/roleAdd";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,SysRole sysRole){
		GlobalResult result=new GlobalResult();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			sysRoleService.insertSelective(sysRole,map);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,SysRole sysRole){
		GlobalResult result=new GlobalResult();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			sysRoleService.updateByPrimaryKeySelective(sysRole,map);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteRole")
	public GlobalResult deleteProject(HttpServletRequest request) {
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			for(String i:arr){
				if(i!=null && !"".equals(i)){
					sysRoleService.deleteByPrimaryKey(i);
					//删除
					sysRolePermissionService.deleteRolePerByRoleId(i);
					sysUserRoleService.deleteUserRoleByRoleId(i);
				}
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

}
