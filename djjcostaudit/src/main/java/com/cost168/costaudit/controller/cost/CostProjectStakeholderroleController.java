package com.cost168.costaudit.controller.cost;

import java.util.Date;
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

import com.cost168.costaudit.pojo.cost.CostProjectStakeholderrole;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostProjectStakeholderroleService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

@Controller
@RequestMapping("/projectStakeholderroler")
public class CostProjectStakeholderroleController {
	@Autowired
	public CostProjectStakeholderroleService costProjectStakeholderroleService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult list(HttpServletRequest request, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("isPage", true);
	    map.put("curPage", rows*(page-1));
	    map.put("pageSize", rows);
		List<CostProjectStakeholderrole> list = costProjectStakeholderroleService.getList(map);
		int total = costProjectStakeholderroleService.getCount(map);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request,ModelMap model){
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			CostProjectStakeholderrole c = costProjectStakeholderroleService.selectByPrimaryKey(id);
			model.put("c", c);
		}
		return "projectStakeholderrole/add";
	}
	@RequestMapping("/save")
	@ResponseBody
	public GlobalResult save(HttpServletRequest request,CostProjectStakeholderrole c){
		GlobalResult result=new GlobalResult();
		try {
			if(c.getId()==null || "".equals(c.getId())){
				SysUser user= shiroUtil.getInstance().currentUser();
				String id = UUID.randomUUID().toString().replace("-", "");
				c.setId(id);
				c.setCreaterTime(new Date());
				c.setCreater(user.getName());
				c.setDeleteFlag(1);
				costProjectStakeholderroleService.insertSelective(c);
			}
			else{
				costProjectStakeholderroleService.updateByPrimaryKeySelective(c);
			}
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
					CostProjectStakeholderrole c = costProjectStakeholderroleService.selectByPrimaryKey(i);
					c.setDeleteFlag(2);
					costProjectStakeholderroleService.updateByPrimaryKeySelective(c);
					//costProjectStakeholderroleService.deleteByPrimaryKey(i);
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
