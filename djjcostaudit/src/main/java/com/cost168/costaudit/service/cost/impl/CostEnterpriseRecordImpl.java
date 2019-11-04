package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cost168.costaudit.mapper.cost.CostEnterpriseRecordMapper;
import com.cost168.costaudit.pojo.cost.CostEnterpriseRecord;
import com.cost168.costaudit.pojo.cost.CostEnterpriseRecordExample;
import com.cost168.costaudit.service.cost.CostEnterpriseRecordService;

@Service
@Transactional
public class CostEnterpriseRecordImpl implements CostEnterpriseRecordService {

    @Autowired
    private CostEnterpriseRecordMapper costEnterpriseRecordMapper;


    @Override
    public List<CostEnterpriseRecord> selectByExample(
            CostEnterpriseRecordExample example) {
        return costEnterpriseRecordMapper.selectByExample(example);
    }

    @Override
    public CostEnterpriseRecord selectByPrimaryKey(String id) {
        return costEnterpriseRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CostEnterpriseRecord record) {
        return costEnterpriseRecordMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(
            CostEnterpriseRecord costEnterpriseRecord) {
        return costEnterpriseRecordMapper.updateByPrimaryKeySelective(costEnterpriseRecord);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return costEnterpriseRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CostEnterpriseRecord> selectListByMap(Map<String, Object> map) {
        return costEnterpriseRecordMapper.selectListByMap(map);
    }

    @Override
    public int selectCountByMap(Map<String, Object> map) {
        return costEnterpriseRecordMapper.selectCountByMap(map);
    }

	@Override
	public String selectYearList() {
		return costEnterpriseRecordMapper.selectYearList();
	}
}
