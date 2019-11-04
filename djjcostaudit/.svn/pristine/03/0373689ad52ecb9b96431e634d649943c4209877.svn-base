package com.cost168.costaudit.service.yaohao;

import java.util.List;
import java.util.Map;


import com.cost168.costaudit.pojo.yaohao.YaohaoOrder;
import com.cost168.costaudit.pojo.yaohao.YaohaoOrderExample;

public interface YaohaoOrderService {
	
	List<YaohaoOrder> selectByExample(YaohaoOrderExample example);

	YaohaoOrder selectByPrimaryKey(String id);

    int insertSelective(YaohaoOrder record);

    int updateByPrimaryKeySelective(YaohaoOrder yaohaoOrder);

    int deleteByPrimaryKey(String id);

    List<YaohaoOrder> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
    
    int registerOrder(String winbid,YaohaoOrder yaohaoOrder) throws Exception;
    
    int updRegisterOrder(String winbid,YaohaoOrder yaohaoOrder) throws Exception;
			
    String selectYearList();
}
