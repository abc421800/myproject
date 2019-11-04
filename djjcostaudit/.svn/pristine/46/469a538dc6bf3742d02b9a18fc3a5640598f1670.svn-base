package com.cost168.costaudit.mapper.work;

import com.cost168.costaudit.pojo.work.WorkPerson;
import com.cost168.costaudit.pojo.work.WorkPersonExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkPersonMapper {
    int countByExample(WorkPersonExample example);

    int deleteByExample(WorkPersonExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkPerson record);

    int insertSelective(WorkPerson record);

    List<WorkPerson> selectByExample(WorkPersonExample example);

    WorkPerson selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkPerson record, @Param("example") WorkPersonExample example);

    int updateByExample(@Param("record") WorkPerson record, @Param("example") WorkPersonExample example);

    int updateByPrimaryKeySelective(WorkPerson record);

    int updateByPrimaryKey(WorkPerson record);

    List<WorkPerson> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
}