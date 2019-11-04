package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostPriceLibrary;
import com.cost168.costaudit.pojo.cost.CostPriceLibraryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostPriceLibraryMapper {
    int countByExample(CostPriceLibraryExample example);

    int deleteByExample(CostPriceLibraryExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostPriceLibrary record);

    int insertSelective(CostPriceLibrary record);

    List<CostPriceLibrary> selectByExample(CostPriceLibraryExample example);

    CostPriceLibrary selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostPriceLibrary record, @Param("example") CostPriceLibraryExample example);

    int updateByExample(@Param("record") CostPriceLibrary record, @Param("example") CostPriceLibraryExample example);

    int updateByPrimaryKeySelective(CostPriceLibrary record);

    int updateByPrimaryKey(CostPriceLibrary record);
    
    int selectCountByMap(Map<String,Object> map);
    
    List<CostPriceLibrary> selectListByMap(Map<String,Object> map);
}