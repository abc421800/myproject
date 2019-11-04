package com.cost168.costaudit.service.yaohao;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;

public interface YaohaoAutocodeService {
	
	 YaohaoAutocode selectByPrimaryKey(String id);
	
	 int deleteByPrimaryKey(String id);
	 
	 int updateByPrimaryKeySelective(YaohaoAutocode record);
	 
	 int insertSelective(YaohaoAutocode record);
	 
	 List<YaohaoAutocode> selectListByMap(Map<String,Object> map);

	 int selectCountByMap(Map<String,Object> map);
	 
	 YaohaoAutocode getCodeAorB();
	 
	 

}
