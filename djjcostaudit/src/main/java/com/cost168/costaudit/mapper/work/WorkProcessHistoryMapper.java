package com.cost168.costaudit.mapper.work;

import com.cost168.costaudit.pojo.work.WorkProcessHistory;
import com.cost168.costaudit.pojo.work.WorkProcessHistoryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkProcessHistoryMapper {
    int countByExample(WorkProcessHistoryExample example);

    int deleteByExample(WorkProcessHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkProcessHistory record);

    int insertSelective(WorkProcessHistory record);

    List<WorkProcessHistory> selectByExample(WorkProcessHistoryExample example);

    WorkProcessHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkProcessHistory record, @Param("example") WorkProcessHistoryExample example);

    int updateByExample(@Param("record") WorkProcessHistory record, @Param("example") WorkProcessHistoryExample example);

    int updateByPrimaryKeySelective(WorkProcessHistory record);

    int updateByPrimaryKey(WorkProcessHistory record);

	List<WorkProcessHistory> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
	
	WorkProcessHistory getCurrentNode(Map<String, Object> map);
}