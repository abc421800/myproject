package com.cost168.costaudit.mapper.yaohao;

import com.cost168.costaudit.pojo.yaohao.YaohaoCandidate;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YaohaoCandidateMapper {
    int countByExample(YaohaoCandidateExample example);

    int deleteByExample(YaohaoCandidateExample example);

    int deleteByPrimaryKey(String id);

    int insert(YaohaoCandidate record);

    int insertSelective(YaohaoCandidate record);

    List<YaohaoCandidate> selectByExample(YaohaoCandidateExample example);

    YaohaoCandidate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YaohaoCandidate record, @Param("example") YaohaoCandidateExample example);

    int updateByExample(@Param("record") YaohaoCandidate record, @Param("example") YaohaoCandidateExample example);

    int updateByPrimaryKeySelective(YaohaoCandidate record);

    int updateByPrimaryKey(YaohaoCandidate record);
    
    List<YaohaoCandidate> selectListByMap(Map<String, Object> map);
    
    List<YaohaoCandidate> selectListByBidflag(String bidFlag);
    
	int selectCountByMap(Map<String, Object> map);
}