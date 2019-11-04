package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostProjectType;

public interface CostProjectTypeService {
	 List<CostProjectType> getRoots();
	 
	 List<CostProjectType> getChilds(CostProjectType root);
	 
	 List<CostProjectType> getList(Map<String, Object> map);
	    
	 int getCount(Map<String, Object> map);
	 
	 int insertSelective(CostProjectType record);
	 
	 CostProjectType selectByPrimaryKey(String id);
	 
	 int deleteByPrimaryKey(String id);
	 
	 int updateByPrimaryKeySelective(CostProjectType record);
}
