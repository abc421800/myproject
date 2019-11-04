package com.cost168.costaudit.service.sys;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.sys.SysRole;
import com.cost168.costaudit.pojo.sys.SysRoleExample;

public interface SysRoleService {
	
	List<SysRole> selectByExample(SysRoleExample example);
	
	SysRole selectByPrimaryKey(String id);
	
	int insertSelective(SysRole record,Map<String,Object> map);
	
	int updateByPrimaryKeySelective(SysRole sysOrg,Map<String,Object> map);
	
	int deleteByPrimaryKey(String id);
	
    List<SysRole> selectListByMap(Map<String,Object> map);
    
    int selectCountByMap(Map<String,Object> map);
    
    SysRole selectByRoleName(String roleName);

}
