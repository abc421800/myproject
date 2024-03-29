package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostTaskTypeMapper;
import com.cost168.costaudit.pojo.cost.CostTaskType;
import com.cost168.costaudit.pojo.cost.CostTaskTypeExample;
import com.cost168.costaudit.pojo.cost.CostTaskTypeExample.Criteria;
import com.cost168.costaudit.service.cost.CostTaskTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostTaskTypeServiceImpl implements CostTaskTypeService {
	
	@Autowired
	private CostTaskTypeMapper costTaskTypeMapper;

	@Override
	public List<CostTaskType> selectByExample(CostTaskTypeExample example) {
		return costTaskTypeMapper.selectByExample(example);
	}

	@Override
	public CostTaskType selectByPrimaryKey(String id) {
		return costTaskTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostTaskType record) {
		return costTaskTypeMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostTaskType record) {
		return costTaskTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costTaskTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CostTaskType> getChilds(CostTaskType root) {
		CostTaskTypeExample example = new CostTaskTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(root.getId());
		example.setOrderByClause("num asc");
		List<CostTaskType> list = costTaskTypeMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<CostTaskType> getRoots() {
		CostTaskTypeExample example = new CostTaskTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidIsNull();
		example.setOrderByClause("num asc ");
		List<CostTaskType> list = costTaskTypeMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<CostTaskType> getList(Map<String, Object> map) {
		return costTaskTypeMapper.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return costTaskTypeMapper.getCount(map);
	}

	@Override
	public CostTaskType getCostTaskTypeByName(String name) {
		CostTaskType taskType=null;
		CostTaskTypeExample example = new CostTaskTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPidIsNull();
		example.setOrderByClause("num asc ");
		List<CostTaskType> list = costTaskTypeMapper.selectByExample(example);
		if(null!=list && list.size()>0){
			taskType=list.get(0);
		}
		return taskType;
	}
	
	@Override
	public CostTaskType getCostTaskTypeLeafByName(String name) {
		CostTaskType taskType=null;
		CostTaskTypeExample example = new CostTaskTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPidIsNotNull();
		example.setOrderByClause("num asc ");
		List<CostTaskType> list = costTaskTypeMapper.selectByExample(example);
		if(null!=list && list.size()>0){
			taskType=list.get(0);
		}
		return taskType;
	}

}
