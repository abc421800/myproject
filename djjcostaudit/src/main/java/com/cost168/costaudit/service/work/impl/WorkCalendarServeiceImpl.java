package com.cost168.costaudit.service.work.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.work.WorkCalendarMapper;
import com.cost168.costaudit.pojo.work.WorkCalendar;
import com.cost168.costaudit.pojo.work.WorkCalendarExample;
import com.cost168.costaudit.pojo.work.vo.WorkCalendarVo;
import com.cost168.costaudit.service.work.WorkCalendarService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkCalendarServeiceImpl implements WorkCalendarService {
	
	@Autowired
	private WorkCalendarMapper workCalendarMapper;


	@Override
	public List<WorkCalendar> selectByExample(WorkCalendarExample example) {
		return workCalendarMapper.selectByExample(example);
	}

	@Override
	public WorkCalendar selectByPrimaryKey(String id) {
		return workCalendarMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WorkCalendar record) {
		return workCalendarMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkCalendar record) {
		return workCalendarMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return workCalendarMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<WorkCalendar> selectListByMap(Map<String, Object> map) {
		return workCalendarMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return workCalendarMapper.selectCountByMap(map);
	}

	@Override
	public List<WorkCalendarVo> selectListByMap2(Map<String, Object> map) {
		return workCalendarMapper.selectListByMap2(map);
	}

}
