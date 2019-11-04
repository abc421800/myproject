package com.cost168.costaudit.service.sys;

import java.util.List;

import com.cost168.costaudit.pojo.sys.SysRolePermissionExample;
import com.cost168.costaudit.pojo.sys.SysRolePermissionKey;

public interface SysRolePermissionService {
	
	List<SysRolePermissionKey> selectByExample(SysRolePermissionExample example);
	
	int insertSelective(SysRolePermissionKey record);

	int deleteByPrimaryKey(SysRolePermissionKey key);
	
	String selectPermissionByRoleId(String roleId);

	int deleteRolePerByRoleId(String roleId);

}


