package com.cost168.costaudit.mapper.yaohao;

import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateRemove;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateRemoveExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YaohaoCandidateRemoveMapper {
    int countByExample(YaohaoCandidateRemoveExample example);

    int deleteByExample(YaohaoCandidateRemoveExample example);

    int deleteByPrimaryKey(String id);

    int insert(YaohaoCandidateRemove record);

    int insertSelective(YaohaoCandidateRemove record);

    List<YaohaoCandidateRemove> selectByExample(YaohaoCandidateRemoveExample example);

    YaohaoCandidateRemove selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YaohaoCandidateRemove record, @Param("example") YaohaoCandidateRemoveExample example);

    int updateByExample(@Param("record") YaohaoCandidateRemove record, @Param("example") YaohaoCandidateRemoveExample example);

    int updateByPrimaryKeySelective(YaohaoCandidateRemove record);

    int updateByPrimaryKey(YaohaoCandidateRemove record);
    
    List<YaohaoCandidateRemove> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
    
}