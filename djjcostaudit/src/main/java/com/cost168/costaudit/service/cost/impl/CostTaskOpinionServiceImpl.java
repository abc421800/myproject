package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostTaskOpinionMapper;
import com.cost168.costaudit.pojo.cost.CostTaskOpinion;
import com.cost168.costaudit.service.cost.CostTaskOpinionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * ClassName: CostTaskOpinionServiceImpl 
 * @Description: TODO
 * @author lixiang
 * @date 2019-4-29上午9:07:09
 * @Company  广东华联软件科技有限公司
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CostTaskOpinionServiceImpl implements CostTaskOpinionService{

	
	@Autowired
	private CostTaskOpinionMapper costTaskOpinionMapper;
	
	@Override
	public CostTaskOpinion selectByPrimaryKey(String id) {
		return costTaskOpinionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostTaskOpinion record) {
		return costTaskOpinionMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostTaskOpinion record) {
		return costTaskOpinionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CostTaskOpinion> selectListByMap(Map<String, Object> map) {
		return costTaskOpinionMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costTaskOpinionMapper.selectCountByMap(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costTaskOpinionMapper.deleteByPrimaryKey(id);
	}

}
