package com.cost168.costaudit.service.cost.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostDpctRelationMapper;
import com.cost168.costaudit.pojo.cost.CostDpctRelation;
import com.cost168.costaudit.pojo.cost.CostDpctRelationExample;
import com.cost168.costaudit.pojo.cost.CostDpctRelationExample.Criteria;
import com.cost168.costaudit.service.cost.CostDpctRelationService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostDpctRelationServiceImpl implements CostDpctRelationService{

	
	@Autowired
	private CostDpctRelationMapper costDpctRelationMapper;
	
	@Override
	public int insertSelective(CostDpctRelation record) {
		record.setCreateTime(new Date());
		return costDpctRelationMapper.insertSelective(record);
	}

	@Override
	public List<CostDpctRelation> selectByDocumentId(String id) {
		CostDpctRelationExample example=new CostDpctRelationExample();
		Criteria criteria = example.createCriteria();
		criteria.andDocumentIdEqualTo(id);
		example.setOrderByClause("create_time desc");
		return costDpctRelationMapper.selectByExample(example);
	}

	@Override
	public List<CostDpctRelation> selectByProjectId(String id) {
		CostDpctRelationExample example=new CostDpctRelationExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(id);
		example.setOrderByClause("create_time desc");
		return costDpctRelationMapper.selectByExample(example);
	}

	@Override
	public List<CostDpctRelation> selectByProjectIdAndDocId(String id,String docId) {
		CostDpctRelationExample example=new CostDpctRelationExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(id);
		criteria.andDocumentIdEqualTo(docId);
		example.setOrderByClause("create_time desc");
		return costDpctRelationMapper.selectByExample(example);
	}
	
	@Override
	public List<CostDpctRelation> selectByContractId(String id) {
		CostDpctRelationExample example=new CostDpctRelationExample();
		Criteria criteria = example.createCriteria();
		criteria.andContractIdEqualTo(id);
		example.setOrderByClause("create_time desc");
		return costDpctRelationMapper.selectByExample(example);
	}

	@Override
	public List<CostDpctRelation> selectByMap(Map<String, Object> map) {
		return costDpctRelationMapper.selectByMap(map);
	}


	@Override
	public CostDpctRelation selectByPrimaryKey(String id) {
		return costDpctRelationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costDpctRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CostDpctRelation record) {
		return costDpctRelationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CostDpctRelation> selectByTaskId(String id) {
		CostDpctRelationExample example=new CostDpctRelationExample();
		Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(id);
		example.setOrderByClause("create_time desc");
		return costDpctRelationMapper.selectByExample(example);
	}

}
