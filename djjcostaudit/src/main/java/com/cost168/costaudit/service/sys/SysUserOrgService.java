package com.cost168.costaudit.service.sys;

import java.util.List;

import com.cost168.costaudit.pojo.sys.SysUserOrgExample;
import com.cost168.costaudit.pojo.sys.SysUserOrgKey;

public interface SysUserOrgService {
	
	List<SysUserOrgKey> selectByExample(SysUserOrgExample example);
	
	int insertSelective(SysUserOrgKey record);
	
	int deleteByPrimaryKey(SysUserOrgKey key);
	
	int deleteUserOrgByUserId(String userId);
	
	List<SysUserOrgKey> uoListByUserId(String userId);
	
	List<SysUserOrgKey> uoListByOrgId(String orgId);
	
	String selectOrgByUserId(String userId);
	
}
