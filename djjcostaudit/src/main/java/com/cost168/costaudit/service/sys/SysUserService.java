package com.cost168.costaudit.service.sys;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.sys.SysUserExample;

public interface SysUserService {
	
	List<SysUser> selectByExample(SysUserExample example);
	
	SysUser selectByPrimaryKey(String id);
	
	int insertSelective(SysUser record,Map<String, Object> map);
	
	int updateByPrimaryKeySelective(SysUser sysOrg,Map<String, Object> map);
	
	int deleteByPrimaryKey(String id);

	List<SysUser> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);

	SysUser getUserByAccount(String Account);

	String allPermByUserId(String userId);

	SysUser selectByUserName(String userName);
	
	SysUser selectByUserAccount(String account);

}
