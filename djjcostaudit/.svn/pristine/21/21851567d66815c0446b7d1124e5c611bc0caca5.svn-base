package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostTaskOpinion;
import com.cost168.costaudit.pojo.cost.CostTaskOpinionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostTaskOpinionMapper {
    int countByExample(CostTaskOpinionExample example);

    int deleteByExample(CostTaskOpinionExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostTaskOpinion record);

    int insertSelective(CostTaskOpinion record);

    List<CostTaskOpinion> selectByExample(CostTaskOpinionExample example);

    CostTaskOpinion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostTaskOpinion record, @Param("example") CostTaskOpinionExample example);

    int updateByExample(@Param("record") CostTaskOpinion record, @Param("example") CostTaskOpinionExample example);

    int updateByPrimaryKeySelective(CostTaskOpinion record);

    int updateByPrimaryKey(CostTaskOpinion record);

	List<CostTaskOpinion> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
}