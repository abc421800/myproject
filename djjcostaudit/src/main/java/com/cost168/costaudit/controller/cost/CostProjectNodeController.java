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

import com.cost168.costaudit.pojo.cost.CostProjectNode;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostProjectNodeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

@Controller
@RequestMapping("/costProjectNode")
public class CostProjectNodeController {
	@Autowired
	public CostProjectNodeService costProjectNodeService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult list(HttpServletRequest request, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("isPage", true);
	    map.put("curPage", rows*(page-1));
	    map.put("pageSize", rows);
		List<CostProjectNode> list = costProjectNodeService.getList(map);
		int total = costProjectNodeService.getCount(map);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request,ModelMap model){
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			CostProjectNode costProjectNode = costProjectNodeService.selectByPrimaryKey(id);
			model.put("costProjectNode", costProjectNode);
		}
		return "projectNode/add";
	}
	@RequestMapping("/save")
	@ResponseBody
	public GlobalResult save(HttpServletRequest request,CostProjectNode costProjectNode){
		GlobalResult result=new GlobalResult();
		try {
			if(costProjectNode.getId()==null || "".equals(costProjectNode.getId())){
				SysUser user= shiroUtil.getInstance().currentUser();
				String id = UUID.randomUUID().toString().replace("-", "");
				costProjectNode.setId(id);
				costProjectNode.setCreateTime(new Date());
				costProjectNode.setCreater(user.getName());
				costProjectNode.setDeleteFlag(1);
				costProjectNodeService.insertSelective(costProjectNode);
			}
			else{
				costProjectNodeService.updateByPrimaryKeySelective(costProjectNode);
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
					CostProjectNode costProjectNode = costProjectNodeService.selectByPrimaryKey(i);
					costProjectNode.setDeleteFlag(2);
					costProjectNodeService.updateByPrimaryKeySelective(costProjectNode);
					//costProjectNodeService.deleteByPrimaryKey(i);
				}
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	@RequestMapping("/getData")
	@ResponseBody
	public List<CostProjectNode> getData(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		List<CostProjectNode> list = costProjectNodeService.getList(map);
		return list;
	}
	
}
