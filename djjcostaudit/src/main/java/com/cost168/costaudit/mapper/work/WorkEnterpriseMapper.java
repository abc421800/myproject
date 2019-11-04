package com.cost168.costaudit.mapper.work;

import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.work.WorkEnterpriseExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkEnterpriseMapper {
    int countByExample(WorkEnterpriseExample example);

    int deleteByExample(WorkEnterpriseExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkEnterprise record);

    int insertSelective(WorkEnterprise record);

    List<WorkEnterprise> selectByExample(WorkEnterpriseExample example);

    WorkEnterprise selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkEnterprise record, @Param("example") WorkEnterpriseExample example);

    int updateByExample(@Param("record") WorkEnterprise record, @Param("example") WorkEnterpriseExample example);

    int updateByPrimaryKeySelective(WorkEnterprise record);

    int updateByPrimaryKey(WorkEnterprise record);

    List<WorkEnterprise> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
}