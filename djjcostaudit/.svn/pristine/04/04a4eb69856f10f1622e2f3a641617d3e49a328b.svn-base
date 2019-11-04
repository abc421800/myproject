package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cost168.costaudit.pojo.cost.CostDocument;

public interface CostDocumentService {
	
	CostDocument selectByPrimaryKey(String id);
	
	int insertSelective(CostDocument record);
	
	int updateByPrimaryKeySelective(CostDocument record);
	
	List<CostDocument> selectListByMap(Map<String,Object> map);
	
	int selectCountByMap(Map<String,Object> map);
	
	int deleteByPrimaryKey(String id);
	
	List<CostDocument> selectRelationDocListByMap(Map<String,Object> map);
    
    int selectRelationDocCountByMap(Map<String,Object> map);
    
    public List<CostDocument> importDocument(HttpServletRequest request);
    
    public CostDocument selectBySymbol(String symbol);

	List<CostDocument> importDocument2(HttpServletRequest request);
}
