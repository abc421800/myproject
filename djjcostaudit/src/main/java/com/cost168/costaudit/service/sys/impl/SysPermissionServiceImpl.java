package com.cost168.costaudit.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.sys.SysPermissionMapper;
import com.cost168.costaudit.pojo.sys.SysPermissionExample.Criteria;
import com.cost168.costaudit.pojo.sys.SysPermission;
import com.cost168.costaudit.pojo.sys.SysPermissionExample;
import com.cost168.costaudit.service.sys.SysPermissionService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl implements SysPermissionService {
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Override
	public List<SysPermission> selectByExample(SysPermissionExample example) {
		return sysPermissionMapper.selectByExample(example);
	}

	@Override
	public SysPermission selectByPrimaryKey(String id) {
		return sysPermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysPermission record) {
		return sysPermissionMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysPermission sysPermission) {
		return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return sysPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SysPermission> getRoots() {
		SysPermissionExample example = new SysPermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidIsNull();
		example.setOrderByClause("num asc");
		List<SysPermission> list = sysPermissionMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<SysPermission> getChilds(SysPermission root) {
		SysPermissionExample example = new SysPermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(root.getId());
		example.setOrderByClause("num asc");
		List<SysPermission> list = sysPermissionMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<SysPermission> getList(Map<String, Object> map) {
		return sysPermissionMapper.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return sysPermissionMapper.getCount(map);
	}

}
