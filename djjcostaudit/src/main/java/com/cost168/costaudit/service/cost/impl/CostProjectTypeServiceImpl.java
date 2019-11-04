package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostProjectTypeMapper;
import com.cost168.costaudit.pojo.cost.CostProjectType;
import com.cost168.costaudit.pojo.cost.CostProjectTypeExample;
import com.cost168.costaudit.pojo.cost.CostProjectTypeExample.Criteria;
import com.cost168.costaudit.service.cost.CostProjectTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostProjectTypeServiceImpl implements CostProjectTypeService {
	@Autowired
	public CostProjectTypeMapper costProjectTypeMapper;
	@Override
	public List<CostProjectType> getRoots() {
		CostProjectTypeExample example = new CostProjectTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidIsNull();
		criteria.andDeleteFlagEqualTo(1);
		List<CostProjectType> list = costProjectTypeMapper.selectByExample(example);// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<CostProjectType> getChilds(CostProjectType root) {
		CostProjectTypeExample example = new CostProjectTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(root.getId());
		criteria.andDeleteFlagEqualTo(1);
		example.setOrderByClause("num");
		List<CostProjectType> list = costProjectTypeMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<CostProjectType> getList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return costProjectTypeMapper.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return costProjectTypeMapper.getCount(map);
	}

	@Override
	public int insertSelective(CostProjectType record) {
		// TODO Auto-generated method stub
		return costProjectTypeMapper.insertSelective(record);
	}

	@Override
	public CostProjectType selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return costProjectTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return costProjectTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CostProjectType record) {
		// TODO Auto-generated method stub
		return costProjectTypeMapper.updateByPrimaryKeySelective(record);
	}

}
