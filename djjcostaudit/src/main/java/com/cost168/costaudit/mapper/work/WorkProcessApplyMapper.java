package com.cost168.costaudit.mapper.work;

import com.cost168.costaudit.pojo.work.WorkProcessApply;
import com.cost168.costaudit.pojo.work.WorkProcessApplyExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkProcessApplyMapper {
    int countByExample(WorkProcessApplyExample example);

    int deleteByExample(WorkProcessApplyExample example);

    int deleteByPrimaryKey(String id);

    int deleteByRedesign(String redesign);

    int insert(WorkProcessApply record);

    int insertSelective(WorkProcessApply record);

    List<WorkProcessApply> selectByExample(WorkProcessApplyExample example);

    List<WorkProcessApply> TimeConflict(WorkProcessApply workProcessApply);

    WorkProcessApply selectByPrimaryKey(String id);

    WorkProcessApply selectByRedesign(String redesign);

    int updateByExampleSelective(@Param("record") WorkProcessApply record, @Param("example") WorkProcessApplyExample example);

    int updateByExample(@Param("record") WorkProcessApply record, @Param("example") WorkProcessApplyExample example);

    int updateByPrimaryKeySelective(WorkProcessApply record);

    int updateByPrimaryKey(WorkProcessApply record);

	List<WorkProcessApply> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);

}