package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cost168.costaudit.pojo.cost.CostPriceLibrary;
import com.cost168.costaudit.pojo.cost.CostPriceLibraryExample;

public interface CostPriceLibraryService {
	
	List<CostPriceLibrary> selectByExample(CostPriceLibraryExample example);
	
	int deleteByPrimaryKey(String id);
	
	int insertSelective(CostPriceLibrary record);
	
	CostPriceLibrary selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(CostPriceLibrary record);
	
    List<CostPriceLibrary> selectListByMap(Map<String,Object> map);
    
    int selectCountByMap(Map<String,Object> map);

	CostPriceLibrary selectByName(String name);
	
	CostPriceLibrary selectByCode(String code);

	List<CostPriceLibrary> importZh(HttpServletRequest request,String flagType);

}
