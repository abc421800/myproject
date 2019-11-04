package com.cost168.costaudit.service.yaohao;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.yaohao.YaohaoCandidate;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateExample;

public interface YaohaoCandidateService {
	
	List<YaohaoCandidate> selectByExample(YaohaoCandidateExample example);

	YaohaoCandidate selectByPrimaryKey(String id);

    int insertSelective(YaohaoCandidate record);

    int updateByPrimaryKeySelective(YaohaoCandidate yaohaoCandidate);

    int deleteByPrimaryKey(String id);

    List<YaohaoCandidate> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
    
    List<YaohaoCandidate> selectCandidateCurrentYaohaoGrade(String yaohaoYear,String yaohaoGrade );
    
}
