package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostTaskCoordinate;
import com.cost168.costaudit.pojo.cost.CostTaskCoordinateExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostTaskCoordinateMapper {
    int countByExample(CostTaskCoordinateExample example);

    int deleteByExample(CostTaskCoordinateExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostTaskCoordinate record);

    int insertSelective(CostTaskCoordinate record);

    List<CostTaskCoordinate> selectByExample(CostTaskCoordinateExample example);

    CostTaskCoordinate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostTaskCoordinate record, @Param("example") CostTaskCoordinateExample example);

    int updateByExample(@Param("record") CostTaskCoordinate record, @Param("example") CostTaskCoordinateExample example);

    int updateByPrimaryKeySelective(CostTaskCoordinate record);

    int updateByPrimaryKey(CostTaskCoordinate record);

	List<CostTaskCoordinate> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
}