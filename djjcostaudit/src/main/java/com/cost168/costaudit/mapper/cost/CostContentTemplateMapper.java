package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostContentTemplate;
import com.cost168.costaudit.pojo.cost.CostContentTemplateExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostContentTemplateMapper {

    int deleteByExample(CostContentTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostContentTemplate record);

    int insertSelective(CostContentTemplate record);

    List<CostContentTemplate> selectByExample(CostContentTemplateExample example);

    CostContentTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostContentTemplate record, @Param("example") CostContentTemplateExample example);

    int updateByExample(@Param("record") CostContentTemplate record, @Param("example") CostContentTemplateExample example);

    int updateByPrimaryKeySelective(CostContentTemplate record);

    int updateByPrimaryKey(CostContentTemplate record);

    List<CostContentTemplate> selectByExample();

    List<CostContentTemplate> getContentByProjectIdList(Map<String,Object> map);

    List<CostContentTemplate> getContentByProjectId(Map<String,Object> map);

    int countByExample(Map<String, Object> map);

}