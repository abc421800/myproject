package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostProjectPeriod;
import com.cost168.costaudit.pojo.cost.CostProjectPeriodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CostProjectPeriodMapper {
    int countByExample(CostProjectPeriodExample example);

    int deleteByExample(CostProjectPeriodExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostProjectPeriod record);

    int insertSelective(CostProjectPeriod record);

    List<CostProjectPeriod> selectByExample(CostProjectPeriodExample example);

    CostProjectPeriod selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostProjectPeriod record, @Param("example") CostProjectPeriodExample example);

    int updateByExample(@Param("record") CostProjectPeriod record, @Param("example") CostProjectPeriodExample example);

    int updateByPrimaryKeySelective(CostProjectPeriod record);

    int updateByPrimaryKey(CostProjectPeriod record);
}