package com.cost168.costaudit.mapper.sys;

import com.cost168.costaudit.pojo.sys.SysUserOrgExample;
import com.cost168.costaudit.pojo.sys.SysUserOrgKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserOrgMapper {
    int countByExample(SysUserOrgExample example);

    int deleteByExample(SysUserOrgExample example);

    int deleteByPrimaryKey(SysUserOrgKey key);

    int insert(SysUserOrgKey record);

    int insertSelective(SysUserOrgKey record);

    List<SysUserOrgKey> selectByExample(SysUserOrgExample example);

    int updateByExampleSelective(@Param("record") SysUserOrgKey record, @Param("example") SysUserOrgExample example);

    int updateByExample(@Param("record") SysUserOrgKey record, @Param("example") SysUserOrgExample example);
    
    String selectOrgByUserId(String userId);
}