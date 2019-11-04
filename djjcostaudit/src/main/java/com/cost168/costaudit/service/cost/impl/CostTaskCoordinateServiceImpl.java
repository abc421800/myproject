package com.cost168.costaudit.service.cost.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostTaskCoordinateMapper;
import com.cost168.costaudit.pojo.cost.CostTaskCoordinate;
import com.cost168.costaudit.pojo.cost.CostTaskCoordinateExample;
import com.cost168.costaudit.service.cost.CostTaskCoordinateService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostTaskCoordinateServiceImpl implements CostTaskCoordinateService {
	
	@Autowired
	private CostTaskCoordinateMapper costTaskCoordinateMapper;


	@Override
	public List<CostTaskCoordinate> selectByExample(CostTaskCoordinateExample example) {
		return costTaskCoordinateMapper.selectByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costTaskCoordinateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostTaskCoordinate record) {
		record.setCreateTime(new Date());
		return costTaskCoordinateMapper.insertSelective(record);
	}

	@Override
	public CostTaskCoordinate selectByPrimaryKey(String id) {
		return costTaskCoordinateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CostTaskCoordinate record) {
		record.setCreateTime(new Date());
		return costTaskCoordinateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CostTaskCoordinate> selectListByMap(Map<String, Object> map) {
		return costTaskCoordinateMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costTaskCoordinateMapper.selectCountByMap(map);
	}

}
