package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostTaskType;
import com.cost168.costaudit.pojo.cost.CostTaskTypeExample;


public interface CostTaskTypeService {
	
	List<CostTaskType> selectByExample(CostTaskTypeExample example);
	
	CostTaskType selectByPrimaryKey(String id);
	
	int insertSelective(CostTaskType record);
	
	int updateByPrimaryKeySelective(CostTaskType record);
	
	int deleteByPrimaryKey(String id);

	List<CostTaskType> getChilds(CostTaskType root);

	List<CostTaskType> getRoots();
	
	List<CostTaskType> getList(Map<String, Object> map);
	    
	int getCount(Map<String, Object> map);

	CostTaskType getCostTaskTypeByName(String name);
	
    CostTaskType getCostTaskTypeLeafByName(String name);
}
