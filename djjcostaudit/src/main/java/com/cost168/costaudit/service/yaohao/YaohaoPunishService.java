package com.cost168.costaudit.service.yaohao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cost168.costaudit.pojo.yaohao.YaohaoPunish;
import com.cost168.costaudit.pojo.yaohao.YaohaoPunishExample;

public interface YaohaoPunishService {
	
	List<YaohaoPunish> selectByExample(YaohaoPunishExample example);

	YaohaoPunish selectByPrimaryKey(String id);

    int insertSelective(YaohaoPunish record);

    int updateByPrimaryKeySelective(YaohaoPunish yaohaoPunish);

    int deleteByPrimaryKey(String id);

    List<YaohaoPunish> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
    
    List<YaohaoPunish> selectListTzByMap(Map<String,Object> map);

    int selectCountTzByMap(Map<String,Object> map);

	int save(HttpServletRequest request, YaohaoPunish yaohaoPunish)throws Exception;

	int update(HttpServletRequest request, YaohaoPunish yaohaoPunish)throws Exception;

}
