package com.cost168.costaudit.service.yaohao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cost168.costaudit.pojo.yaohao.YaohaoWinbid;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbidExample;

public interface YaohaoWinbidService {
	
	List<YaohaoWinbid> selectByExample(YaohaoWinbidExample example);

	YaohaoWinbid selectByPrimaryKey(String id);

    int insertSelective(YaohaoWinbid record);

    int updateByPrimaryKeySelective(YaohaoWinbid yaohaoWinbid);

    int deleteByPrimaryKey(String id);

    List<YaohaoWinbid> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);

	List<YaohaoWinbid> selectListByOrderCode(String code);
	
	int deleteByOrderCode(String orderCode);
	
	BigDecimal getServiceAmountSum(Map<String, Object> map);

	void containContract(HttpServletRequest request) throws Exception;

}
