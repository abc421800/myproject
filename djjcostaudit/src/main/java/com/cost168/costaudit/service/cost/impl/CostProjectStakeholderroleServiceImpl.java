package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostProjectStakeholderroleMapper;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderrole;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderroleExample;
import com.cost168.costaudit.service.cost.CostProjectStakeholderroleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostProjectStakeholderroleServiceImpl implements CostProjectStakeholderroleService{
	@Autowired
	public CostProjectStakeholderroleMapper costProjectStakeholderroleMapper;

	@Override
	public List<CostProjectStakeholderrole> selectByExample(
			CostProjectStakeholderroleExample example) {
		return costProjectStakeholderroleMapper.selectByExample(example);
	}

	@Override
	public CostProjectStakeholderrole selectByPrimaryKey(String id) {
		return costProjectStakeholderroleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CostProjectStakeholderrole> getList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return costProjectStakeholderroleMapper.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return costProjectStakeholderroleMapper.getCount(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return costProjectStakeholderroleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostProjectStakeholderrole record) {
		// TODO Auto-generated method stub
		return costProjectStakeholderroleMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostProjectStakeholderrole record) {
		// TODO Auto-generated method stub
		return costProjectStakeholderroleMapper.updateByPrimaryKeySelective(record);
	}

}
