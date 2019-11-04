package com.cost168.costaudit.service.sys;

import java.util.List;

import com.cost168.costaudit.pojo.sys.SysUserRoleExample;
import com.cost168.costaudit.pojo.sys.SysUserRoleKey;

public interface SysUserRoleService {
	
	List<SysUserRoleKey> selectByExample(SysUserRoleExample example);
	
	int insertSelective(SysUserRoleKey record);
	
	int deleteByPrimaryKey(SysUserRoleKey key);

	int deleteUserRoleByUserId(String id);
	
	int deleteUserRoleByRoleId(String id);
	
	String selectRoleByUserId(String userId);

}
