package com.cost168.costaudit.mapper.sys;

import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.pojo.sys.SysOrgExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysOrgMapper {
    int countByExample(SysOrgExample example);

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    List<SysOrg> selectByExample(SysOrgExample example);

    SysOrg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);

	List<SysOrg> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);
}