package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostProjectStakeholderrole;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderroleExample;

public interface CostProjectStakeholderroleService {
	List<CostProjectStakeholderrole> selectByExample(CostProjectStakeholderroleExample example);
	
	List<CostProjectStakeholderrole> getList(Map<String, Object> map);
    
    int getCount(Map<String, Object> map);
    
    int deleteByPrimaryKey(String id);
    
    int insertSelective(CostProjectStakeholderrole record);
    
    int updateByPrimaryKeySelective(CostProjectStakeholderrole record);
    
    CostProjectStakeholderrole selectByPrimaryKey(String id);
}
