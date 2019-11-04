package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostProjectNode;
import com.cost168.costaudit.pojo.cost.CostProjectNodeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostProjectNodeMapper {
    int countByExample(CostProjectNodeExample example);

    int deleteByExample(CostProjectNodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostProjectNode record);

    int insertSelective(CostProjectNode record);

    List<CostProjectNode> selectByExample(CostProjectNodeExample example);

    CostProjectNode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostProjectNode record, @Param("example") CostProjectNodeExample example);

    int updateByExample(@Param("record") CostProjectNode record, @Param("example") CostProjectNodeExample example);

    int updateByPrimaryKeySelective(CostProjectNode record);

    int updateByPrimaryKey(CostProjectNode record);
    
    List<CostProjectNode> getList(Map<String, Object> map);
    
    int getCount(Map<String,Object> map);
}