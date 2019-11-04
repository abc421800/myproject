package com.cost168.costaudit.controller.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.work.WorkCalendar;
import com.cost168.costaudit.pojo.work.vo.WorkCalendarVo;
import com.cost168.costaudit.service.work.WorkCalendarService;
import com.cost168.costaudit.utils.GlobalResult;

/**
 * 
 * ClassName: WorkCalendarController 
 * @Description: TODO
 * @author lixiang
 * @date 2019-6-12上午8:36:08
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/workCalendar")
public class WorkCalendarController {
	
	@Autowired
	private WorkCalendarService workCalendarService;
	     
	
	@ResponseBody
	@RequestMapping("/list")
	public List<WorkCalendarVo> list(HttpServletRequest request) {
		
		List<WorkCalendarVo> list=workCalendarService.selectListByMap2(null);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			String id=request.getParameter("id");
			String weekDays=request.getParameter("weekDays");
			String excepDays=request.getParameter("excepDays");
			WorkCalendar wc= workCalendarService.selectByPrimaryKey(id);
			wc.setWeekdays(weekDays);
			wc.setExcepdays(excepDays);
			workCalendarService.updateByPrimaryKeySelective(wc);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			String year=request.getParameter("year");
			String weekDays=request.getParameter("weekDays");
			String excepDays=request.getParameter("excepDays");
			WorkCalendar wc=new WorkCalendar();
			wc.setId(year);
			wc.setYear(year);
			wc.setWeekdays(weekDays);
			wc.setExcepdays(excepDays);
			workCalendarService.insertSelective(wc);
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
		try {
			String year=request.getParameter("year");
			workCalendarService.deleteByPrimaryKey(year);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
