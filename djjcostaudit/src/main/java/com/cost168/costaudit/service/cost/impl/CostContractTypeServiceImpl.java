package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostContractTypeMapper;
import com.cost168.costaudit.pojo.cost.CostContractType;
import com.cost168.costaudit.pojo.cost.CostContractTypeExample;
import com.cost168.costaudit.service.cost.CostContractTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostContractTypeServiceImpl implements CostContractTypeService {

	@Autowired
	private CostContractTypeMapper costContractTypeMapper;
	
	@Override
	public List<CostContractType> selectByExample(CostContractTypeExample example) {
		return costContractTypeMapper.selectByExample(example);
	}

	@Override
	public CostContractType selectByPrimaryKey(String id) {
		return costContractTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostContractType record) {
		return costContractTypeMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostContractType record) {
		return costContractTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costContractTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CostContractType> selectListByMap(Map<String, Object> map) {
		return costContractTypeMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costContractTypeMapper.selectCountByMap(map);
	}

}
