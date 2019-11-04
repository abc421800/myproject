package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostContentTemplate;
import com.cost168.costaudit.pojo.cost.CostUnitProject;
import com.cost168.costaudit.pojo.cost.CostUnitProjectExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostUnitProjectMapper {
    int countByExample(CostUnitProjectExample example);

    int deleteByExample(CostUnitProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostUnitProject record);

    int insertSelective(CostUnitProject record);

    List<CostUnitProject> selectByExample(CostUnitProjectExample example);

    CostUnitProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostUnitProject record, @Param("example") CostUnitProjectExample example);

    int updateByExample(@Param("record") CostUnitProject record, @Param("example") CostUnitProjectExample example);

    int updateByPrimaryKeySelective(CostUnitProject record);

    int updateByPrimaryKey(CostUnitProject record);

    List<CostUnitProject> selectByExample();

    List<CostUnitProject> getUnitByProjectIdList(Map<String,Object> map);

    List<CostUnitProject> getUnitByProjectId(Map<String,Object> map);
}