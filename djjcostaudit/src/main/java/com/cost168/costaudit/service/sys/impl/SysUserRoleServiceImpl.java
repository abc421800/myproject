package com.cost168.costaudit.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.sys.SysUserRoleMapper;
import com.cost168.costaudit.pojo.sys.SysUserRoleExample;
import com.cost168.costaudit.pojo.sys.SysUserRoleKey;
import com.cost168.costaudit.pojo.sys.SysUserRoleExample.Criteria;
import com.cost168.costaudit.service.sys.SysUserRoleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public List<SysUserRoleKey> selectByExample(SysUserRoleExample example) {
		return sysUserRoleMapper.selectByExample(example);
	}

	@Override
	public int insertSelective(SysUserRoleKey record) {
		return sysUserRoleMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(SysUserRoleKey key) {
		return sysUserRoleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int deleteUserRoleByUserId(String id) {
		SysUserRoleExample example=new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		return sysUserRoleMapper.deleteByExample(example);
	}

	@Override
	public String selectRoleByUserId(String userId) {
		return sysUserRoleMapper.selectRoleByUserId(userId);
	}

	@Override
	public int deleteUserRoleByRoleId(String id) {
		SysUserRoleExample example=new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(id);
		return sysUserRoleMapper.deleteByExample(example);
	}

}
