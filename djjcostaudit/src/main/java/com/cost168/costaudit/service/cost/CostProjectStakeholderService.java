package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostProjectStakeholder;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderExample;

public interface CostProjectStakeholderService {
	List<CostProjectStakeholder> selectByExample(CostProjectStakeholderExample example);
	
	CostProjectStakeholder selectByPrimaryKey(String id);
	
	 int deleteByPrimaryKey(String id);
	 
	 int insertSelective(CostProjectStakeholder record);
	 
	 int updateByPrimaryKeySelective(CostProjectStakeholder record);
	 
	 List<CostProjectStakeholder> getListByProj(Map<String, Object> map);
	 
	 int getCountByProj(Map<String, Object> map);
	 
	 List<CostProjectStakeholder> getListByName(Map<String, Object> map);
}
