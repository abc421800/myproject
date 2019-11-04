package com.cost168.costaudit.service.sys;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.pojo.sys.SysOrgExample;

public interface SysOrgService {
	
	List<SysOrg> selectByExample(SysOrgExample example);
	
	SysOrg selectByPrimaryKey(String id);
	
	int insertSelective(SysOrg record);
	
	int updateByPrimaryKeySelective(SysOrg sysOrg);
	
	int deleteByPrimaryKey(String id);

	List<SysOrg> selectListByMap(Map<String, Object> map);

	int selectCountByMap(Map<String, Object> map);

	List<SysOrg> getChilds(SysOrg root);

	List<SysOrg> getRoots();

	SysOrg selectByOrgName(String orgName);

}
