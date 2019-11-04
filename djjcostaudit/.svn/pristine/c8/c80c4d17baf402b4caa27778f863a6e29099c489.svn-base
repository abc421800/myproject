package com.cost168.costaudit.mapper.work;

import com.cost168.costaudit.pojo.work.WorkCalendar;
import com.cost168.costaudit.pojo.work.WorkCalendarExample;
import com.cost168.costaudit.pojo.work.vo.WorkCalendarVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkCalendarMapper {
    int countByExample(WorkCalendarExample example);

    int deleteByExample(WorkCalendarExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkCalendar record);

    int insertSelective(WorkCalendar record);

    List<WorkCalendar> selectByExample(WorkCalendarExample example);

    WorkCalendar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkCalendar record, @Param("example") WorkCalendarExample example);

    int updateByExample(@Param("record") WorkCalendar record, @Param("example") WorkCalendarExample example);

    int updateByPrimaryKeySelective(WorkCalendar record);

    int updateByPrimaryKey(WorkCalendar record);
    
    List<WorkCalendar> selectListByMap(Map<String,Object> map);
    
    List<WorkCalendarVo> selectListByMap2(Map<String,Object> map);
    
    int selectCountByMap(Map<String,Object> map);
}