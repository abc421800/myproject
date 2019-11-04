package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostDocument;
import com.cost168.costaudit.pojo.cost.CostDocumentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostDocumentMapper {
    int countByExample(CostDocumentExample example);

    int deleteByExample(CostDocumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostDocument record);

    int insertSelective(CostDocument record);

    List<CostDocument> selectByExample(CostDocumentExample example);

    CostDocument selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostDocument record, @Param("example") CostDocumentExample example);

    int updateByExample(@Param("record") CostDocument record, @Param("example") CostDocumentExample example);

    int updateByPrimaryKeySelective(CostDocument record);

    int updateByPrimaryKey(CostDocument record);
    
    List<CostDocument> selectListByMap(Map<String,Object> map);
	
	int selectCountByMap(Map<String,Object> map);
    
    List<CostDocument> selectRelationDocListByMap(Map<String,Object> map);
    
    int selectRelationDocCountByMap(Map<String,Object> map);
}