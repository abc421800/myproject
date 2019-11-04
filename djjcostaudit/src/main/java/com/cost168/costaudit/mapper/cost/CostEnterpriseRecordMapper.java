package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostEnterpriseRecord;
import com.cost168.costaudit.pojo.cost.CostEnterpriseRecordExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostEnterpriseRecordMapper {
    int countByExample(CostEnterpriseRecordExample example);

    int deleteByExample(CostEnterpriseRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostEnterpriseRecord record);

    int insertSelective(CostEnterpriseRecord record);

    List<CostEnterpriseRecord> selectByExample(CostEnterpriseRecordExample example);

    CostEnterpriseRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostEnterpriseRecord record, @Param("example") CostEnterpriseRecordExample example);

    int updateByExample(@Param("record") CostEnterpriseRecord record, @Param("example") CostEnterpriseRecordExample example);

    int updateByPrimaryKeySelective(CostEnterpriseRecord record);

    int updateByPrimaryKey(CostEnterpriseRecord record);

    List<CostEnterpriseRecord> selectListByMap(Map<String, Object> map);

    int selectCountByMap(Map<String, Object> map);

    List<CostEnterpriseRecord> selectByYears(Map<String, Object> map);

    CostEnterpriseRecord selectByYear(Map<String, Object> map);
    
    String selectYearList();
    
}