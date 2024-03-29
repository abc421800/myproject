package com.cost168.costaudit.service.cost.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostProjectNodeMapper;
import com.cost168.costaudit.mapper.cost.CostProjectPeriodMapper;
import com.cost168.costaudit.pojo.cost.CostProjectNode;
import com.cost168.costaudit.pojo.cost.CostProjectNodeExample;
import com.cost168.costaudit.pojo.cost.CostProjectPeriod;
import com.cost168.costaudit.pojo.cost.CostProjectPeriodExample;
import com.cost168.costaudit.service.cost.CostProjectPeriodService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostProjectPeriodServiceImpl implements CostProjectPeriodService {
	@Autowired
	public CostProjectPeriodMapper costProjectPeriodMapper;
	@Autowired
	public CostProjectNodeMapper costProjectNodeMapper;

	@Override
	public void initData(String projId) {
		CostProjectNodeExample example = new CostProjectNodeExample();
		List<CostProjectNode> nodeList =costProjectNodeMapper.selectByExample(example);
		if(nodeList!= null && nodeList.size()>0){
			for(CostProjectNode node:nodeList){
				CostProjectPeriod p1 = new CostProjectPeriod();
				p1.setProjectId(projId);
				p1.setId(UUID.randomUUID().toString().replace("-", ""));
				p1.setName(node.getName());
				p1.setNum(node.getNum());
				p1.setUpdateTime(new Date());
				costProjectPeriodMapper.insertSelective(p1);
			}
		}
	}

	@Override
	public List<CostProjectPeriod> selectByExample(
			CostProjectPeriodExample example) {
		return costProjectPeriodMapper.selectByExample(example);
	}

	@Override
	public CostProjectPeriod selectByPrimaryKey(String id) {
		return costProjectPeriodMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CostProjectPeriod record) {
		return costProjectPeriodMapper.updateByPrimaryKeySelective(record);
	}

}
