package com.cost168.costaudit.controller.cost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostProjectStakeholder;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderExample;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderrole;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderroleExample;
import com.cost168.costaudit.service.cost.CostProjectStakeholderService;
import com.cost168.costaudit.service.cost.CostProjectStakeholderroleService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

@Controller
@RequestMapping("/stakelholder")
public class CostProjectStakelholderController {
	@Autowired
	private CostProjectStakeholderService costProjectStakeholderService;
	@Autowired
	private CostProjectStakeholderroleService costProjectStakeholderroleService;
	
	@RequestMapping("/getStakeholderList")
	@ResponseBody
	public EUDataGridResult getStakeholderList(HttpServletRequest request,int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
	    Map<String,Object> map=new HashMap<String,Object>();
		String projId = request.getParameter("projId");
		map.put("projId", projId);
		map.put("isPage", true);
	    map.put("curPage", rows*(page-1));
	    map.put("pageSize", rows);
		CostProjectStakeholderExample example = new CostProjectStakeholderExample();
		com.cost168.costaudit.pojo.cost.CostProjectStakeholderExample.Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projId);
		List<CostProjectStakeholder> list = costProjectStakeholderService.getListByProj(map);
		for(CostProjectStakeholder c:list){
			CostProjectStakeholderrole role = costProjectStakeholderroleService.selectByPrimaryKey(c.getRoleId());
			if(role!=null && role.getName()!=null){
				c.setRoleName(role.getName());
			}
		}
		result.setRows(list);
		int total = costProjectStakeholderService.getCountByProj(map);
		result.setTotal(total);
		return result;
	}

	@RequestMapping("/addStakeholder")
	public String addProjectStakeholder(HttpServletRequest request, ModelMap model){
		CostProjectStakeholderroleExample example = new CostProjectStakeholderroleExample();
		example.setOrderByClause(" num asc");
		List<CostProjectStakeholderrole> list = costProjectStakeholderroleService.selectByExample(example);
		model.put("roleList", list);
		String id = request.getParameter("id");
		if(id!=null && !"".equals(id)){
			CostProjectStakeholder stakeholder = costProjectStakeholderService.selectByPrimaryKey(id);
			model.put("stakeholder", stakeholder);
		}
		//model.put("projId", request.getParameter("projId"));
		return "project/projectStakeholder";
	}
	
	@ResponseBody
	@RequestMapping("/saveStakeholder")
	public GlobalResult saveStakeholder(HttpServletRequest request, ModelMap model,CostProjectStakeholder c){
		GlobalResult result=new GlobalResult();
		try {
			String id = c.getId();
			if(id==null || "".equals(id)){
				id = UUID.randomUUID().toString().replace("-", "");
				c.setId(id);
				costProjectStakeholderService.insertSelective(c);
			}
			else{
				costProjectStakeholderService.updateByPrimaryKeySelective(c);
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	@RequestMapping("/getUserName")
	@ResponseBody
	public List<CostProjectStakeholder> getUserName(HttpServletRequest request, ModelMap model){
		String userName = request.getParameter("userName");
		GlobalResult result=new GlobalResult();
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("name", userName);
		/*CostProjectStakeholderExample example = new CostProjectStakeholderExample();
		com.cost168.costaudit.pojo.cost.CostProjectStakeholderExample.Criteria criteria = example.createCriteria();
		if(userName!=null && !"".equals(userName)){
			criteria.andNameLike("%"+userName+"%");
		}
		example.setDistinct(true);*/
		List<CostProjectStakeholder> userList = costProjectStakeholderService.getListByName(map);
		result.setData(userList);
		return userList;
	}
	@RequestMapping("/deleteStakeholder")
	@ResponseBody
	public GlobalResult deleteStakeholder(HttpServletRequest request, ModelMap model){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			for(String i:arr){
				if(i!=null && !"".equals(i)){
					costProjectStakeholderService.deleteByPrimaryKey(i);
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
