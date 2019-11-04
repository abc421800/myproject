package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostContractType;
import com.cost168.costaudit.pojo.cost.CostContractTypeExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostContractTypeMapper {
    int countByExample(CostContractTypeExample example);

    int deleteByExample(CostContractTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostContractType record);

    int insertSelective(CostContractType record);

    List<CostContractType> selectByExample(CostContractTypeExample example);

    CostContractType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostContractType record, @Param("example") CostContractTypeExample example);

    int updateByExample(@Param("record") CostContractType record, @Param("example") CostContractTypeExample example);

    int updateByPrimaryKeySelective(CostContractType record);

    int updateByPrimaryKey(CostContractType record);
    
    List<CostContractType> selectListByMap(Map<String,Object> map);
	
	int selectCountByMap(Map<String,Object> map);
}