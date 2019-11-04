package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostTaskCoordinate;
import com.cost168.costaudit.pojo.cost.CostTaskCoordinateExample;

public interface CostTaskCoordinateService {
	
	List<CostTaskCoordinate> selectByExample(CostTaskCoordinateExample example);
	
	int deleteByPrimaryKey(String id);
	
	int insertSelective(CostTaskCoordinate record);
	
	CostTaskCoordinate selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(CostTaskCoordinate record);

	List<CostTaskCoordinate> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);

}
