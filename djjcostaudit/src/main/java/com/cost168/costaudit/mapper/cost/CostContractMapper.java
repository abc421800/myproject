package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostContract;
import com.cost168.costaudit.pojo.cost.CostContractExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostContractMapper {
    int countByExample(CostContractExample example);

    int deleteByExample(CostContractExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostContract record);

    int insertSelective(CostContract record);

    List<CostContract> selectByExample(CostContractExample example);

    CostContract selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostContract record, @Param("example") CostContractExample example);

    int updateByExample(@Param("record") CostContract record, @Param("example") CostContractExample example);

    int updateByPrimaryKeySelective(CostContract record);

    int updateByPrimaryKey(CostContract record);
    
    int selectCountByMap(Map<String,Object> map);
    
    List<CostContract> selectListByMap(Map<String,Object> map);
    List<CostContract> selectChildrenListByMap(Map<String,Object> map);

    List<CostContract> selectTask();
}