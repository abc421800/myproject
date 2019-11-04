package com.cost168.costaudit.service.work.impl;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.work.WorkProcessHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.work.WorkProcessHistoryMapper;
import com.cost168.costaudit.pojo.work.WorkProcessHistoryExample;
import com.cost168.costaudit.service.work.WorkProcessHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkProcessHistoryServiceImpl implements WorkProcessHistoryService{

	@Autowired
	private WorkProcessHistoryMapper workProcessHistoryMapper;
	
	@Override
	public List<WorkProcessHistory> selectByExample(WorkProcessHistoryExample example) {
		return workProcessHistoryMapper.selectByExample(example);
	}

	@Override
	public WorkProcessHistory selectByPrimaryKey(String id) {
		return workProcessHistoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WorkProcessHistory record) {
		return workProcessHistoryMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkProcessHistory workProcessHistory) {
		return workProcessHistoryMapper.updateByPrimaryKeySelective(workProcessHistory);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return workProcessHistoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<WorkProcessHistory> selectListByMap(Map<String, Object> map) {
		return workProcessHistoryMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return workProcessHistoryMapper.selectCountByMap(map);
	}

	@Override
	public WorkProcessHistory getCurrentNode(Map<String, Object> map) {
		return workProcessHistoryMapper.getCurrentNode(map);
	}

}
