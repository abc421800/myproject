package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostTaskType;
import com.cost168.costaudit.pojo.cost.CostTaskTypeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostTaskTypeMapper {
    int countByExample(CostTaskTypeExample example);

    int deleteByExample(CostTaskTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostTaskType record);

    int insertSelective(CostTaskType record);

    List<CostTaskType> selectByExample(CostTaskTypeExample example);

    CostTaskType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostTaskType record, @Param("example") CostTaskTypeExample example);

    int updateByExample(@Param("record") CostTaskType record, @Param("example") CostTaskTypeExample example);

    int updateByPrimaryKeySelective(CostTaskType record);

    int updateByPrimaryKey(CostTaskType record);
    
    List<CostTaskType> getList(Map<String, Object> map);
    
    int getCount(Map<String, Object> map);
}