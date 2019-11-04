package com.cost168.costaudit.service.sys;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.sys.SysPermission;
import com.cost168.costaudit.pojo.sys.SysPermissionExample;

public interface SysPermissionService {

	List<SysPermission> selectByExample(SysPermissionExample example);
	
	SysPermission selectByPrimaryKey(String id);
	
	int insertSelective(SysPermission record);
	
	int updateByPrimaryKeySelective(SysPermission sysPermission);
	
	int deleteByPrimaryKey(String id);
	
	List<SysPermission> getRoots();
	 
	List<SysPermission> getChilds(SysPermission root);
	
	List<SysPermission> getList(Map<String, Object> map);
    
    int getCount(Map<String, Object> map);
	
}
