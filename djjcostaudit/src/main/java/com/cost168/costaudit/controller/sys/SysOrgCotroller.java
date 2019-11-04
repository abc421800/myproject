package com.cost168.costaudit.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.sys.SysUserOrgKey;
import com.cost168.costaudit.service.sys.SysOrgService;
import com.cost168.costaudit.service.sys.SysUserOrgService;
import com.cost168.costaudit.service.sys.SysUserService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.utils.JsonUtils;

/**
 * 
 * ClassName: SysOrgCotroller 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-28上午8:29:30
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/sysOrg")
public class SysOrgCotroller {
	
	@Autowired
	private SysOrgService sysOrgService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserOrgService sysUserOrgService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,SysOrg org, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		String pidIsNotNull=request.getParameter("pidIsNotNull");
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(org);
			map.putAll(selectMap);
			if(null!=pidIsNotNull && !"".equals(pidIsNotNull)){
				map.put("pidIsNotNull", pidIsNotNull);
			}
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    List<SysOrg> list= sysOrgService.selectListByMap(map);
		    int total= sysOrgService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/sysOrgList")
	public List<SysOrg> sysOrgList() {
		List<SysOrg> lsit = sysOrgService.selectByExample(null);
		return lsit;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String url="";
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			url="/sysUser/upd";
			SysOrg sysOrg = sysOrgService.selectByPrimaryKey(id);
			request.setAttribute("obj", sysOrg);
		}else{
			url="/sysUser/save";
			String uuid=UUID.randomUUID().toString().replace("-", "");
			SysUser sysUser=new SysUser();
			sysUser.setId(uuid);
			request.setAttribute("obj", sysUser);
		}
		request.setAttribute("url", url);
		return "sys/userAdd";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,SysOrg sysOrg){
		GlobalResult result=new GlobalResult();
		try {
			String orgId=sysOrg.getId();
			if(null==orgId || "".equals(orgId)){
				String uuid=UUID.randomUUID().toString().replace("-", "");
				sysOrg.setId(uuid);
			}
			sysOrg.setDeleteFlag(1);
			sysOrgService.insertSelective(sysOrg);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,SysOrg sysOrg){
		GlobalResult result=new GlobalResult();
		try {
			sysOrgService.updateByPrimaryKeySelective(sysOrg);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public GlobalResult del(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			SysOrg sysOrg=null;
			for(String i:arr){
				sysOrg=new SysOrg();
				sysOrg.setId(i);
				sysOrg.setDeleteFlag(2);
				sysOrgService.updateByPrimaryKeySelective(sysOrg);
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@RequestMapping("/orgTree")
	@ResponseBody
	public List<Map<String,Object>> orgTree(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SysOrg> rootList = sysOrgService.getRoots();
		if(!CollectionUtils.isEmpty(rootList)){
			for(SysOrg root:rootList){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", root.getId());
				map.put("text",root.getName());
//				map.put("state", "closed"); //根结点默认收起
				if(!CollectionUtils.isEmpty(sysOrgService.getChilds(root))){
					try {
						map.put("children", getChildren(root));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(map);
			}
		}
		return list;
	}
	
	private List<Map<String,Object>> getChildren(SysOrg root){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SysOrg> childSet = sysOrgService.getChilds(root);
		if(!CollectionUtils.isEmpty(childSet)){
			for(SysOrg org:childSet){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", org.getId());
				map.put("text",org.getName());
				if(!CollectionUtils.isEmpty(sysOrgService.getChilds(org))){
					map.put("children", getChildren(org));
				}
				list.add(map);
			}
		}
		return list;
	}
	
	@RequestMapping("orgDetails")
	@ResponseBody
	public Map<String,Object> orgDetails(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//获取当前展开的节点id 
			String orgId = request.getParameter("id");
			if(null != orgId && orgId.length() >0){
				SysOrg org = sysOrgService.selectByPrimaryKey(orgId);
				SysOrg orgParent = sysOrgService.selectByPrimaryKey(org.getPid());
				if(org!=null){
					map.put("id", org.getId());
					map.put("pid", org.getPid());
					map.put("pidName", orgParent.getName());
					map.put("name", org.getName());
					map.put("description", org.getDescription());
					map.put("num", org.getNum());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/userList")
	public EUDataGridResult list(HttpServletRequest request,Integer page, Integer rows) {
		EUDataGridResult result=new EUDataGridResult();
		List<SysUser> uList=new ArrayList<SysUser>();
		if(page==null || rows==null){page=1;rows=10;}
		String orgId=request.getParameter("orgId");
		List<SysUserOrgKey> userOrgKey= sysUserOrgService.uoListByOrgId(orgId);
		for(SysUserOrgKey uo:userOrgKey) {
			SysUser u=sysUserService.selectByPrimaryKey(uo.getUserId());
			if(null!=u) {
				uList.add(u);
			}
		}
		result.setRows(uList);
		result.setTotal(uList.size());
		return result;
	}
	
}
