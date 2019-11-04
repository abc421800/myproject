package com.cost168.costaudit.controller.cost;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostContractType;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostContractTypeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

@Controller
@RequestMapping("/costContractType")
public class CostContractTypeController {
	
	@Autowired
	private CostContractTypeService costContractTypeService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,CostContractType costContractType, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String deleteFlag=request.getParameter("deleteFlag");
			if(null!=deleteFlag&& !"".equals(deleteFlag)){
				map.put("deleteFlag", deleteFlag);
			}
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    SysUser currentUser=shiroUtil.getInstance().currentUser();
		    map.put("currentPerson", currentUser.getName());
		    List<CostContractType> list= costContractTypeService.selectListByMap(map);
		    int total= costContractTypeService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/contractTypeList")
	public List<CostContractType> contractTypeList() {
		List<CostContractType> lsit = costContractTypeService.selectByExample(null);
		return lsit;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String url="";
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			url="/costContractType/upd";
			CostContractType costContractType = costContractTypeService.selectByPrimaryKey(id);
			request.setAttribute("obj", costContractType);
		}else{
			url="/costContractType/save";
			String contracttypeId=UUID.randomUUID().toString().replace("-", "");
			CostContractType costContractType=new CostContractType();
			costContractType.setId(contracttypeId);
			request.setAttribute("obj", costContractType);
		}
		List<CostContractType> lsit = costContractTypeService.selectByExample(null);
		request.setAttribute("conTypeList", lsit);
		request.setAttribute("url", url);
		return "contractType/add";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,CostContractType costContractType){
		GlobalResult result=new GlobalResult();
		try {
			SysUser user=shiroUtil.getInstance().currentUser();
			costContractType.setCreateTime(new Date());
			costContractType.setCreater(user.getName());
			costContractType.setDeleteFlag(1);
			costContractTypeService.insertSelective(costContractType);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,CostContractType costContractType){
		GlobalResult result=new GlobalResult();
		try {
			costContractTypeService.updateByPrimaryKeySelective(costContractType);
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
			CostContractType costContractType=null;
			for(String i:arr){
				if(i!=null && !"".equals(i)){
					costContractType=new CostContractType();
					costContractType.setId(i);
					costContractType.setDeleteFlag(2);
					costContractTypeService.updateByPrimaryKeySelective(costContractType);
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
