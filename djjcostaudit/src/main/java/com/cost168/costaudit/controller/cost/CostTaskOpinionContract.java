package com.cost168.costaudit.controller.cost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.pojo.cost.CostTaskOpinion;
import com.cost168.costaudit.service.cost.CostTaskOpinionService;
import com.cost168.costaudit.service.cost.CostTaskService;


/**
 * 
 * ClassName: CostTaskOpinionContract 
 * @Description: TODO
 * @author lixiang
 * @date 2019-4-29上午9:36:58
 * @Company  广东华联软件科技有限公司
 */

@Controller
@RequestMapping("/costTaskOpinion")
public class CostTaskOpinionContract {
	
	
	
	
	@Autowired
	private CostTaskOpinionService costTaskOpinionService;
	@Autowired
	private CostTaskService costTaskService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<CostTaskOpinion> list(HttpServletRequest request) {
		String taskId= request.getParameter("taskId");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("taskId", taskId);
		List<CostTaskOpinion> to= costTaskOpinionService.selectListByMap(map);
		return to;
	}

}
