package com.cost168.costaudit.mapper.yaohao;

import com.cost168.costaudit.pojo.yaohao.YaohaoWinbid;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbidExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YaohaoWinbidMapper {
    int countByExample(YaohaoWinbidExample example);

    int deleteByExample(YaohaoWinbidExample example);

    int deleteByPrimaryKey(String id);
    
    int deleteByOrderCode(String orderCode);

    int insert(YaohaoWinbid record);

    int insertSelective(YaohaoWinbid record);

    List<YaohaoWinbid> selectByExample(YaohaoWinbidExample example);

    YaohaoWinbid selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YaohaoWinbid record, @Param("example") YaohaoWinbidExample example);

    int updateByExample(@Param("record") YaohaoWinbid record, @Param("example") YaohaoWinbidExample example);

    int updateByPrimaryKeySelective(YaohaoWinbid record);

    int updateByPrimaryKey(YaohaoWinbid record);
    
    List<YaohaoWinbid> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
	
	List<YaohaoWinbid> selectListByOrderCode(String code);

	BigDecimal getServiceAmountSum(Map<String, Object> map);
}