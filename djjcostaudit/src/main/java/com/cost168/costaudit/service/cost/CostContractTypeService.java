package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostContractType;
import com.cost168.costaudit.pojo.cost.CostContractTypeExample;

public interface CostContractTypeService {
	
	List<CostContractType> selectByExample(CostContractTypeExample example);
	
	CostContractType selectByPrimaryKey(String id);
	
	int insertSelective(CostContractType costContractType);
	
	int updateByPrimaryKeySelective(CostContractType record);
	
	int deleteByPrimaryKey(String id);
	
	List<CostContractType> selectListByMap(Map<String,Object> map);
	
	int selectCountByMap(Map<String,Object> map);

}
