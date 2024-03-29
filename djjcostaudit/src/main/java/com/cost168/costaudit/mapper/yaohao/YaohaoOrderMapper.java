package com.cost168.costaudit.mapper.yaohao;

import com.cost168.costaudit.pojo.yaohao.YaohaoOrder;
import com.cost168.costaudit.pojo.yaohao.YaohaoOrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YaohaoOrderMapper {
    int countByExample(YaohaoOrderExample example);

    int deleteByExample(YaohaoOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(YaohaoOrder record);

    int insertSelective(YaohaoOrder record);

    List<YaohaoOrder> selectByExample(YaohaoOrderExample example);

    YaohaoOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YaohaoOrder record, @Param("example") YaohaoOrderExample example);

    int updateByExample(@Param("record") YaohaoOrder record, @Param("example") YaohaoOrderExample example);

    int updateByPrimaryKeySelective(YaohaoOrder record);

    int updateByPrimaryKey(YaohaoOrder record);
    
    List<YaohaoOrder> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
	
	String selectYearList();
}