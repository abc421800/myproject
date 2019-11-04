package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.pojo.cost.CostTaskExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostTaskMapper {
    int countByExample(CostTaskExample example);

    int deleteByExample(CostTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostTask record);

    int insertSelective(CostTask record);

    List<CostTask> selectByExample(CostTaskExample example);

    CostTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostTask record, @Param("example") CostTaskExample example);

    int updateByExample(@Param("record") CostTask record, @Param("example") CostTaskExample example);

    int updateByPrimaryKeySelective(CostTask record);

    int updateByPrimaryKey(CostTask record);

	List<CostTask> selectListByMap(Map<String,Object> map);
	
	int selectCountByMap(Map<String,Object> map);
    

}