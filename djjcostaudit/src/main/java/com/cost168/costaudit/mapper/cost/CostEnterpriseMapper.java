package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.cost.CostEnterpriseExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostEnterpriseMapper {
    int countByExample(CostEnterpriseExample example);

    int deleteByExample(CostEnterpriseExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostEnterprise record);

    int insertSelective(CostEnterprise record);

    List<CostEnterprise> selectByExample(CostEnterpriseExample example);

    CostEnterprise selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostEnterprise record, @Param("example") CostEnterpriseExample example);

    int updateByExample(@Param("record") CostEnterprise record, @Param("example") CostEnterpriseExample example);

    int updateByPrimaryKeySelective(CostEnterprise record);

    int updateByPrimaryKey(CostEnterprise record);
    
    int selectCountByMap(Map<String,Object> map);
    
    List<CostEnterprise> selectListByMap(Map<String,Object> map);
    
    int selectCount(Map<String,Object> map);
    
    List<CostEnterprise> selectList(Map<String,Object> map);
}