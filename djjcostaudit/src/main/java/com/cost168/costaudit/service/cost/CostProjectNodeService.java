package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostProjectNode;

public interface CostProjectNodeService {
	
	 int deleteByPrimaryKey(String id);
	 
	 int insertSelective(CostProjectNode record);
	 
	 CostProjectNode selectByPrimaryKey(String id);
	 
	 int updateByPrimaryKeySelective(CostProjectNode record);
	 
	 List<CostProjectNode> getList(Map<String, Object> map);
	 
	 int getCount(Map<String,Object> map);
}
