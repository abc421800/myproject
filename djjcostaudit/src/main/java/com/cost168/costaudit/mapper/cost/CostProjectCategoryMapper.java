package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostProjectCategory;
import com.cost168.costaudit.pojo.cost.CostProjectCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CostProjectCategoryMapper {
    int countByExample(CostProjectCategoryExample example);

    int deleteByExample(CostProjectCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostProjectCategory record);

    int insertSelective(CostProjectCategory record);

    List<CostProjectCategory> selectByExample(CostProjectCategoryExample example);

    CostProjectCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostProjectCategory record, @Param("example") CostProjectCategoryExample example);

    int updateByExample(@Param("record") CostProjectCategory record, @Param("example") CostProjectCategoryExample example);

    int updateByPrimaryKeySelective(CostProjectCategory record);

    int updateByPrimaryKey(CostProjectCategory record);
}