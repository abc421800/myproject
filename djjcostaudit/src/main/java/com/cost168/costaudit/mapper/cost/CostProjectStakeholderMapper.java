package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostProjectStakeholder;
import com.cost168.costaudit.pojo.cost.CostProjectStakeholderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostProjectStakeholderMapper {
    int countByExample(CostProjectStakeholderExample example);

    int deleteByExample(CostProjectStakeholderExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostProjectStakeholder record);

    int insertSelective(CostProjectStakeholder record);

    List<CostProjectStakeholder> selectByExample(CostProjectStakeholderExample example);

    CostProjectStakeholder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostProjectStakeholder record, @Param("example") CostProjectStakeholderExample example);

    int updateByExample(@Param("record") CostProjectStakeholder record, @Param("example") CostProjectStakeholderExample example);

    int updateByPrimaryKeySelective(CostProjectStakeholder record);

    int updateByPrimaryKey(CostProjectStakeholder record);
    
    List<CostProjectStakeholder> getListByProj(Map<String, Object> map);
    
    int getCountByProj(Map<String, Object> map);
    
    List<CostProjectStakeholder> getListByName(Map<String, Object> map);
}