package com.cost168.costaudit.controller.sys;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.utils.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cost168.costaudit.mapper.sys.SysUserMapper;
import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.pojo.sys.SysRole;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.sys.SysOrgService;
import com.cost168.costaudit.service.sys.SysRoleService;
import com.cost168.costaudit.service.sys.SysUserOrgService;
import com.cost168.costaudit.service.sys.SysUserRoleService;
import com.cost168.costaudit.service.sys.SysUserService;
import com.cost168.costaudit.shiro.shiroUtil;

/**
 * 
 * ClassName: SysUserCotroller 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-26下午3:54:13
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserCotroller {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserOrgService sysUserOrgService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysOrgService sysOrgService;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	
	
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,SysUser user, int page, int rows) {
		String orgName=request.getParameter("orgName");
		String status=request.getParameter("status");
		String roleNameNotEq=request.getParameter("roleNameNotEq");
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(user);
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    if(null!=orgName && !"".equals(orgName)){
		    	map.put("orgName", orgName);
		    }
			if(null!=status && !"".equals(status)){
				map.put("status", status);
			}
			if(null!=roleNameNotEq && !"".equals(roleNameNotEq)){
				map.put("roleNameNotEq", roleNameNotEq);
			}
		    List<SysUser> list= sysUserService.selectListByMap(map);
		    Global.USE_EXPORT_LIST = list;
		    int total= sysUserService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/workPersonList")
	public EUDataGridResult workPersonList(HttpServletRequest request,SysUser user, int page, int rows) {
		String orgName=request.getParameter("orgName");
		String names=request.getParameter("names");
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String n=java.net.URLDecoder.decode(names, "UTF-8");
			Map<String,Object> selectMap=JsonUtils.object2Map(user);
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    if(null!=orgName && !"".equals(orgName)){
		    	map.put("orgName", orgName);
		    }
		    map.put("roleNameNotEq", "驻场人员");
		    List<SysUser> list= sysUserService.selectListByMap(map);
		    if(null!=n){
				String arrName[]=n.split(",");
				for(String s:arrName){
					for(SysUser u:list){
						if(s.equals(u.getName())){
							u.setChecked(true);
						}
					}
				}
			}
		    int total= sysUserService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String url="";
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			url="/sysUser/upd";
			SysUser sysUser = sysUserService.selectByPrimaryKey(id);
			//部门
			String orgId=sysUserOrgService.selectOrgByUserId(sysUser.getId());
			sysUser.setOrgId(orgId);
			//角色
			String roleId=sysUserRoleService.selectRoleByUserId(sysUser.getId());
			sysUser.setRoleId(roleId);
			
			request.setAttribute("obj", sysUser);
		}else{
			url="/sysUser/save";
			String documentId=UUID.randomUUID().toString().replace("-", "");
			SysUser sysUser=new SysUser();
			sysUser.setId(documentId);
			request.setAttribute("obj", sysUser);
		}
		request.setAttribute("url", url);
		return "sys/userAdd";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,SysUser sysUser){
		GlobalResult result=new GlobalResult();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			SysUser user=sysUserService.getUserByAccount(sysUser.getAccount());
			if(null!=user){
				result.setStatus(600);
			}else{
				String orgId=request.getParameter("orgId");
				String roleId=request.getParameter("roleId");
				map.put("roleId", roleId);
				map.put("orgId", orgId);
				sysUser.setPassword(DigestUtils.md5Hex("000000"));
				sysUser.setStatus("禁用");
				sysUserService.insertSelective(sysUser, map);
				result.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,SysUser sysUser){
		GlobalResult result=new GlobalResult();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			String orgId=request.getParameter("orgId");
			String roleId=request.getParameter("roleId");
			map.put("orgId", orgId);
			map.put("roleId", roleId);
			sysUserService.updateByPrimaryKeySelective(sysUser,map);
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
			for(String i:arr){
				sysUserService.deleteByPrimaryKey(i);
				sysUserOrgService.deleteUserOrgByUserId(i);
				sysUserRoleService.deleteUserRoleByUserId(i);
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	
	@RequestMapping("/personCenter")
	public String personCenter(HttpServletRequest request){
		try {
			SysUser currentUser =shiroUtil.getInstance().currentUser();
			request.setAttribute("user", currentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sys/personCenter";
	}
	
	/**
	 * 
	 * @Description: 导入用户
	 * @param @param request
	 * @param @return   
	 * @return GlobalResult  
	 * @throws
	 * @author lixiang
	 * @date 2019-1-25下午3:50:35
	 * @Company  广东华联软件科技有限公司
	 */
	@ResponseBody
	@RequestMapping("importUser")
	public GlobalResult importUser(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try{
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					//源文件后缀
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					//新文件名称
					Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
					String path=(String) props.get("fileupload");
					path=path+"user/"+nowDate+"/";
					File filePath=new File(path+fileName);
					if (!filePath.exists()) {    
						filePath.mkdirs();    
					}
					file.transferTo(filePath); 
					//读取exc
					HashMap<String, HashMap<Integer, ArrayList<Object>>> excelMap = ExcelUtil.readExcel(1,path + fileName, 0);
					//遍历excel的sheet
					for(Entry<String, HashMap<Integer, ArrayList<Object>>> excel : excelMap.entrySet()){
						//取出一个sheet内容
						HashMap<Integer, ArrayList<Object>> sheetMap = excel.getValue();
						ArrayList<Object> row = null;
						SysUser sysUser=null;
						//遍历一个sheet里的每行数据
						Map<String,Object> map=null;
						for(Entry<Integer, ArrayList<Object>> sheet : sheetMap.entrySet()){
							row = sheet.getValue();
							//System.out.println(row.get(1)+"------"+row.get(2));
							SysUser user=sysUserService.selectByUserName((String)row.get(0));
							if(null!=user){
								continue;
							}
							sysUser=new SysUser();
							map=new HashMap<String, Object>();
							String id=UUID.randomUUID().toString().replace("-", "");
							sysUser.setId(id);
							sysUser.setPassword(DigestUtils.md5Hex("1"));
							sysUser.setName(row.get(0).toString());
							sysUser.setAccount(row.get(1).toString());
							SysRole role=sysRoleService.selectByRoleName((String)row.get(2));
							if(null!=role){
								map.put("roleId", role.getId());
							}
							String orgName=row.get(3).toString();
							if(null!=orgName && !"".equals(orgName)){
								orgName=orgName.substring(orgName.lastIndexOf("/")+1, orgName.length());
							}
							SysOrg org=sysOrgService.selectByOrgName(orgName);
							if(null!=org){
								map.put("orgId", org.getId());
							}
							sysUser.setSex(row.get(4).toString());
							sysUser.setPhone(row.get(5).toString().replace(".00", ""));
							sysUser.setWechat(row.get(6).toString().replace(".00", ""));
							sysUser.setEmail(row.get(7).toString());
							sysUser.setStatus("禁用");
							sysUserService.insertSelective(sysUser, map);
						}
					}
				}
				result.setStatus(200);
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	//导出
	@ResponseBody
	@RequestMapping("/exportUser")
	public void exportUser(HttpServletRequest request,SysUser user,HttpServletResponse response){
		List<SysUser> list =new ArrayList<SysUser>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					SysUser doc=sysUserService.selectByPrimaryKey(id);
					list.add(doc);
				}
			}else{
				list= sysUserMapper.selectListByMap(null);
			}
			String path=request.getSession().getServletContext().getRealPath("/template/report/user_template.xlsx");
			String newPath=request.getSession().getServletContext().getRealPath("/template/temp/用户列表.xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>(); 
			List<String> cellList=new ArrayList<String>();
			for(SysUser l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getName());
				cellList.add(l.getAccount());
				StringBuffer roleName=new StringBuffer();
				String roleIds=sysUserRoleService.selectRoleByUserId(l.getId());
				if(null!=roleIds){
					String arr[]=roleIds.split(",");
					if(null!=arr && arr.length>0){
						for(String str:arr){
							SysRole sysRole=sysRoleService.selectByPrimaryKey(str);
							if(null!=sysRole){
								roleName.append(sysRole.getName()).append("/");
							}
						}
						roleName.deleteCharAt(roleName.length()-1);
					}
				}
				cellList.add(roleName.toString());
				cellList.add(l.getOrgId());
				cellList.add(l.getSex());
				cellList.add(l.getPhone());
				cellList.add(l.getWechat());
				cellList.add(l.getEmail());
				rowList.add(cellList);
			}
			date.put("用户列表", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping("/importTemplate")
	public void importTemplate(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/用户导入模版.xlsx");
			String fileName="用户导入模版.xlsx";
			try {
				if (IeEncordingUtil.isMSBrowser(request)) {
					fileName = URLEncoder.encode(fileName, "UTF8");
				}
				ExcelUtil.downLoad(path, fileName, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping("/resetPwd")
	public GlobalResult resetPwd(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		try {
			SysUser user=new SysUser();
			user.setId(ids);
			user.setPassword(DigestUtils.md5Hex("000000"));
			sysUserService.updateByPrimaryKeySelective(user, null);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/prohibitUser")
	public GlobalResult prohibitUser(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String flag=request.getParameter("flag");
		try {
			SysUser user=new SysUser();
			user.setId(ids);
			if(flag!=null && !"".equals(flag)){
				if("y".equals(flag)){
					user.setStatus("可用");
				}else{
					user.setStatus("禁用");
				}
			}
			sysUserService.updateByPrimaryKeySelective(user, null);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	
}
