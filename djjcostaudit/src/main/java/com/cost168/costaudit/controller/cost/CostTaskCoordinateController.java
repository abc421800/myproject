package com.cost168.costaudit.controller.cost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostTaskCoordinate;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostTaskCoordinateService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

/**
 * 
 * ClassName: CostTaskCoordinateController 
 * @Description: 协调事项
 * @author lixiang
 * @date 2018-12-20上午11:52:48
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/costTaskCoordinate")
public class CostTaskCoordinateController {
	
	
	@Autowired
	private CostTaskCoordinateService costTaskCoordinateService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,CostTaskCoordinate costTaskCoordinate, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    map.put("taskId", costTaskCoordinate.getTaskId());
		    List<CostTaskCoordinate> list= costTaskCoordinateService.selectListByMap(map);
		    int total= costTaskCoordinateService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			CostTaskCoordinate costTaskCoordinate = costTaskCoordinateService.selectByPrimaryKey(id);
			request.setAttribute("obj", costTaskCoordinate);
		}else{
			//add
			CostTaskCoordinate costTaskCoordinate=new CostTaskCoordinate();
			String taskId=request.getParameter("taskId");
			costTaskCoordinate.setId(null);
			costTaskCoordinate.setTaskId(taskId);
			request.setAttribute("obj", costTaskCoordinate);
		}
		return "task/taskCoordinate";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,CostTaskCoordinate costTaskCoordinate){
		GlobalResult result=new GlobalResult();
		try {
			SysUser currentUser=shiroUtil.getInstance().currentUser();
			if(null!=costTaskCoordinate.getId() && !"".equals(costTaskCoordinate.getId())){
				costTaskCoordinateService.updateByPrimaryKeySelective(costTaskCoordinate);
			}else{
				costTaskCoordinate.setCreater(currentUser.getName());
				String id=UUID.randomUUID().toString().replace("-", "");
				costTaskCoordinate.setId(id);
				costTaskCoordinateService.insertSelective(costTaskCoordinate);
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
	public GlobalResult del(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			for(String i:arr){
				costTaskCoordinateService.deleteByPrimaryKey(i);
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
}
