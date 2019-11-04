package com.cost168.costaudit.service.work;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.work.WorkCalendar;
import com.cost168.costaudit.pojo.work.WorkCalendarExample;
import com.cost168.costaudit.pojo.work.vo.WorkCalendarVo;

public interface WorkCalendarService {
	
	List<WorkCalendar> selectByExample(WorkCalendarExample example);
	
	WorkCalendar selectByPrimaryKey(String id);
	
	int insertSelective(WorkCalendar record);
	
	int updateByPrimaryKeySelective(WorkCalendar workCalendar);
	
	int deleteByPrimaryKey(String id);
	
    List<WorkCalendar> selectListByMap(Map<String,Object> map);
    
    List<WorkCalendarVo> selectListByMap2(Map<String,Object> map);
    
    int selectCountByMap(Map<String,Object> map);

}