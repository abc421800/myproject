package com.cost168.costaudit.controller.cost;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.pojo.cost.CostProjectPeriod;
import com.cost168.costaudit.service.cost.CostProjectPeriodService;
import com.cost168.costaudit.service.cost.CostProjectService;
import com.cost168.costaudit.utils.GlobalResult;

@Controller
@RequestMapping("/costProjectPeriod")
public class CostProjectPeriodController {
	@Autowired
	public CostProjectPeriodService costProjectPeriodService;
	@Autowired
	public CostProjectService costProjectService;
	
	@ResponseBody
	@RequestMapping("/updateCostProjectPeriod")
	public GlobalResult updateCostProjectPeriod(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String id = request.getParameter("id");
		String nodeTimeStr = request.getParameter("nodeTimeStr");
		String description = request.getParameter("description");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if(id!=null && !"".equals(id)){
				CostProjectPeriod p = costProjectPeriodService.selectByPrimaryKey(id);
				if(nodeTimeStr!=null && !"".equals(nodeTimeStr)){
					nodeTimeStr =nodeTimeStr+" 01:09:06";
					p.setNodeTime(sdf.parse(nodeTimeStr));
				}
				p.setDescription(description);
				p.setUpdateTime(new Date());
				costProjectPeriodService.updateByPrimaryKeySelective(p);
				CostProject proj = costProjectService.selectByPrimaryKey(p.getProjectId());
				proj.setProjectNode(p.getName());
				proj.setNodeTime(p.getNodeTime());
				proj.setNodeMemo(p.getDescription());
				costProjectService.updateByPrimaryKeySelective(proj);
				result.setStatus(200);
			}
			else{
				result.setStatus(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

}
