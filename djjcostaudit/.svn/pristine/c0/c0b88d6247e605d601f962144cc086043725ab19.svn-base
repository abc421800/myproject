package com.cost168.costaudit.mapper.yaohao;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.yaohao.YaohaoAssess;
import com.cost168.costaudit.pojo.yaohao.param.YaohaoAssessParam;

import java.util.List;
import java.util.Map;

public interface YaohaoAssessMapper {
    List<YaohaoAssessParam> selectListByMap(Map<String, Object> map);

    YaohaoAssessParam queryOne(String id);

    int deleteByPrimaryKey(String id);

    int insert(YaohaoAssess assess);

    YaohaoAssess selectByPrimaryKey(String id);

    int updateByPrimaryKey(YaohaoAssessParam assessParam);

    int updateByPrimaryKeySelective(YaohaoAssessParam assessParam);

    int selectCountByMap(Map<String, Object> map);

    String queryYear();

    CostEnterprise selectEnterpriseCodeByName(String enterpriseName);

    CostEnterprise selectEnterpriseCodeByCode(String enterpriseCode);

    List<YaohaoAssessParam> queryEnterpriseByParam(Map<String, Object> map);

    int countEnterpriseByParam(Map<String, Object> map);

    YaohaoAssessParam selectOne(Map<String, Object> map);
    
    List<YaohaoAssess> selectList(Map<String, Object> map);
    
    int selectCount(Map<String, Object> map);
}