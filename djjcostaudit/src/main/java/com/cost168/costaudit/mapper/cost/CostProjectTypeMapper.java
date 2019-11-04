package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostProjectType;
import com.cost168.costaudit.pojo.cost.CostProjectTypeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostProjectTypeMapper {
    int countByExample(CostProjectTypeExample example);

    int deleteByExample(CostProjectTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostProjectType record);

    int insertSelective(CostProjectType record);

    List<CostProjectType> selectByExample(CostProjectTypeExample example);

    CostProjectType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostProjectType record, @Param("example") CostProjectTypeExample example);

    int updateByExample(@Param("record") CostProjectType record, @Param("example") CostProjectTypeExample example);

    int updateByPrimaryKeySelective(CostProjectType record);

    int updateByPrimaryKey(CostProjectType record);
    
    List<CostProjectType> getList(Map<String, Object> map);
    
    int getCount(Map<String, Object> map);
}