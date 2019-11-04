package com.cost168.costaudit.mapper.yaohao;

import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;
import com.cost168.costaudit.pojo.yaohao.YaohaoAutocodeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YaohaoAutocodeMapper {
    int countByExample(YaohaoAutocodeExample example);

    int deleteByExample(YaohaoAutocodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(YaohaoAutocode record);

    int insertSelective(YaohaoAutocode record);

    List<YaohaoAutocode> selectByExample(YaohaoAutocodeExample example);

    YaohaoAutocode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YaohaoAutocode record, @Param("example") YaohaoAutocodeExample example);

    int updateByExample(@Param("record") YaohaoAutocode record, @Param("example") YaohaoAutocodeExample example);

    int updateByPrimaryKeySelective(YaohaoAutocode record);

    int updateByPrimaryKey(YaohaoAutocode record);
    
    List<YaohaoAutocode> selectListByMap(Map<String,Object> map);

	int selectCountByMap(Map<String,Object> map);
}