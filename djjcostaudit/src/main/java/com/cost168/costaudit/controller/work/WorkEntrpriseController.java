package com.cost168.costaudit.controller.work;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.service.cost.CostEnterpriseService;
import com.cost168.costaudit.service.work.WorkEnterpriseService;
import com.cost168.costaudit.service.work.WorkPersonService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.ExcelUtil;
import com.cost168.costaudit.utils.Global;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.utils.JsonUtils;

@Controller
@RequestMapping("/workEnterprise")
public class WorkEntrpriseController {
	
	@Autowired
	private WorkEnterpriseService workEnterpriseService;
	@Autowired
	private WorkPersonService workPersonService;
	@Autowired
	private CostEnterpriseService costEnterpriseService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,WorkEnterprise workEnterprise, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(workEnterprise);
		    String startTime =request.getParameter("startTime");
			String endTime =request.getParameter("endTime");
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
			}
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
			List<WorkEnterprise> list= workEnterpriseService.selectListByMap(map);
			Global.WORKENT_EXPORT_LIST=list;
		    int total= workEnterpriseService.selectCountByMap(map);
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
			url="/workEnterprise/upd";
			WorkEnterprise workEnterprise = workEnterpriseService.selectByPrimaryKey(id);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("enterpriseId", id);
			int sendPersonCount=workPersonService.selectCountByMap(map);
			map.put("effectiveFlag", "有效");
			int sendPersonEffectiveCount=workPersonService.selectCountByMap(map);
			workEnterprise.setSendPerson(sendPersonCount);
			workEnterprise.setSendPersonEffective(sendPersonEffectiveCount);
			request.setAttribute("add_edit", "edit");
			request.setAttribute("obj", workEnterprise);
		}else{
			SysUser currentUser=shiroUtil.getInstance().currentUser();
			url="/workEnterprise/save";
			String enterpriseId=UUID.randomUUID().toString().replace("-", "");
			WorkEnterprise workEnterprise=new WorkEnterprise();
			workEnterprise.setId(enterpriseId);
			workEnterprise.setCreater(currentUser.getName());
			request.setAttribute("add_edit", "add");
			request.setAttribute("obj", workEnterprise);
		}
		request.setAttribute("url", url);
		return "workEnterprise/add";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,WorkEnterprise workEnterprise){
		GlobalResult result=new GlobalResult();
		WorkEnterprise name=workEnterpriseService.selectByWorkEnterpriseName(workEnterprise.getName());
		CostEnterprise  costEnterprise = costEnterpriseService.selectByCostEnterpriseName(workEnterprise.getName());
		try {
			if(null==name){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				if(null!=workEnterprise.getServiceStartStr() && !"".equals(workEnterprise.getServiceStartStr())){
					workEnterprise.setServiceStart(sdf.parse(workEnterprise.getServiceStartStr()));
				}
				if(null!=workEnterprise.getServiceEndStr() && !"".equals(workEnterprise.getServiceEndStr())){
					workEnterprise.setServiceEnd(sdf.parse(workEnterprise.getServiceEndStr()));
				}
				SysUser user= shiroUtil.getInstance().currentUser();
				workEnterprise.setDeleteFlag("1");
				workEnterprise.setCreateTime(new Date());
				workEnterprise.setCreater(user.getName());
				workEnterpriseService.insertSelective(workEnterprise);
				if(null!=costEnterprise){
					costEnterprise.setContacts(workEnterprise.getPersonLiable());
					costEnterprise.setContactsPhone(workEnterprise.getPhone());
					costEnterprise.setAddress(workEnterprise.getAddress());
					costEnterprise.setFax(workEnterprise.getFax());
					costEnterprise.setEmail(workEnterprise.getEmail());
					costEnterprise.setDescription(workEnterprise.getRemark());
					costEnterprise.setStationing("是");
					costEnterpriseService.updateByPrimaryKeySelective(costEnterprise);
				}
				result.setStatus(200);
			}else{
				result.setStatus(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,WorkEnterprise workEnterprise){
		GlobalResult result=new GlobalResult();
		CostEnterprise  costEnterprise = costEnterpriseService.selectByCostEnterpriseName(workEnterprise.getName());
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("nameEq", workEnterprise.getName());
			map.put("noid", workEnterprise.getId());
			List<WorkEnterprise> enterpriseList_name= workEnterpriseService.selectListByMap(map);
			System.out.println(enterpriseList_name.size());
			if(null!=enterpriseList_name && enterpriseList_name.size()>0){
				result.setStatus(300);
				return result;
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(null!=workEnterprise.getServiceStartStr() && !"".equals(workEnterprise.getServiceStartStr())){
				workEnterprise.setServiceStart(sdf.parse(workEnterprise.getServiceStartStr()));
			}
			if(null!=workEnterprise.getServiceEndStr() && !"".equals(workEnterprise.getServiceEndStr())){
				workEnterprise.setServiceEnd(sdf.parse(workEnterprise.getServiceEndStr()));
			}
			if(null!=costEnterprise){
				costEnterprise.setContacts(workEnterprise.getPersonLiable());
				costEnterprise.setContactsPhone(workEnterprise.getPhone());
				costEnterprise.setAddress(workEnterprise.getAddress());
				costEnterprise.setFax(workEnterprise.getFax());
				costEnterprise.setEmail(workEnterprise.getEmail());
				costEnterprise.setDescription(workEnterprise.getRemark());
				costEnterprise.setStationing("是");
				costEnterpriseService.updateByPrimaryKeySelective(costEnterprise);
			}
			workEnterprise.setCreateTime(new Date());
			workEnterpriseService.updateByPrimaryKeySelective(workEnterprise);
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
				
				WorkEnterprise ent=  workEnterpriseService.selectByPrimaryKey(i);
				CostEnterprise costEnt= costEnterpriseService.selectByCostEnterpriseName(ent.getName());
				if(null!=costEnt){
					costEnt.setStationing("否");
					costEnterpriseService.updateByPrimaryKeySelective(costEnt);
				}
				ent.setDeleteFlag("2");
				workEnterpriseService.updateByPrimaryKeySelective(ent);
			/*	WorkPersonExample example=new WorkPersonExample();
				Criteria  criteria=	example.createCriteria();
				criteria.andEnterpriseIdEqualTo(i);
				workPersonService.deleteByExample(example);*/
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	//导出
	@ResponseBody
	@RequestMapping("/exportEnterprise")
	public void exportEnterprise(HttpServletRequest request,WorkEnterprise enterprise,HttpServletResponse response){
		try {
			List<WorkEnterprise> list = Global.WORKENT_EXPORT_LIST;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(new Date());
			String path=request.getSession().getServletContext().getRealPath("/template/report/驻场企业.xlsx");
			String newPath=request.getSession().getServletContext().getRealPath("/template/temp/驻场企业导出_导出日期_"+ dateString +".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>(); 
			List<String> cellList=new ArrayList<String>();
			for(WorkEnterprise l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getName());
				cellList.add(l.getType());
				cellList.add(l.getPersonLiable());
				cellList.add(l.getPhone());
				cellList.add(l.getTelephone());
				cellList.add(l.getEmail());
				cellList.add(l.getAddress());
				cellList.add(l.getServiceStart()!=null?formatter.format(l.getServiceStart()):"");
				cellList.add(l.getServiceEnd()!=null?formatter.format(l.getServiceEnd()):"");
				cellList.add(l.getEffectiveFlag());
				cellList.add(l.getRemark());
				rowList.add(cellList);
			}
			date.put("驻场企业", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导入
	@ResponseBody
	@RequestMapping("/importWorkEnt")
	public GlobalResult importWorkEnt(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			List<WorkEnterprise> taskList = workEnterpriseService.importWorkEnt(request);
			if(null!=taskList && taskList.size()>0){
				result.setData(taskList);
				result.setStatus(300);
			}else{
				result.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

}
