package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostDpctRelation;
import com.cost168.costaudit.pojo.cost.CostDpctRelationExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostDpctRelationMapper {
    int countByExample(CostDpctRelationExample example);

    int deleteByExample(CostDpctRelationExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostDpctRelation record);

    int insertSelective(CostDpctRelation record);

    List<CostDpctRelation> selectByExample(CostDpctRelationExample example);

    CostDpctRelation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostDpctRelation record, @Param("example") CostDpctRelationExample example);

    int updateByExample(@Param("record") CostDpctRelation record, @Param("example") CostDpctRelationExample example);

    int updateByPrimaryKeySelective(CostDpctRelation record);

    int updateByPrimaryKey(CostDpctRelation record);
    
    List<CostDpctRelation> selectByMap(Map<String,Object> map);
    
}