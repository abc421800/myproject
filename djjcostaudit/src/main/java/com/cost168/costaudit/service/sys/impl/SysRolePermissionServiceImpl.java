package com.cost168.costaudit.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.sys.SysPermissionMapper;
import com.cost168.costaudit.mapper.sys.SysRolePermissionMapper;
import com.cost168.costaudit.pojo.sys.SysPermission;
import com.cost168.costaudit.pojo.sys.SysRolePermissionExample;
import com.cost168.costaudit.pojo.sys.SysRolePermissionExample.Criteria;
import com.cost168.costaudit.pojo.sys.SysRolePermissionKey;
import com.cost168.costaudit.service.sys.SysRolePermissionService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	
	
	@Override
	public List<SysRolePermissionKey> selectByExample(SysRolePermissionExample example) {
		return sysRolePermissionMapper.selectByExample(example);
	}

	@Override
	public int insertSelective(SysRolePermissionKey record) {
		return sysRolePermissionMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(SysRolePermissionKey key) {
		return sysRolePermissionMapper.deleteByPrimaryKey(key);
	}

	@Override
	public String selectPermissionByRoleId(String roleId) {
		//return sysRolePermissionMapper.selectPermissionByRoleId(roleId);
		StringBuffer psb=new StringBuffer();
		SysRolePermissionExample example=new SysRolePermissionExample();
		com.cost168.costaudit.pojo.sys.SysRolePermissionExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<SysRolePermissionKey> rpList=sysRolePermissionMapper.selectByExample(example);
		if(null!=rpList && rpList.size()>0){
			for(SysRolePermissionKey p:rpList){
				SysPermission permission=sysPermissionMapper.selectByPrimaryKey(p.getPermissionId());
				if(null!=permission){
					psb.append(permission.getId()).append(",");
				}
			}
		}
		if(psb.length()>0){
			psb=psb.deleteCharAt(psb.length()-1);
		}
		return psb.toString();
	}

	@Override
	public int deleteRolePerByRoleId(String roleId) {
		SysRolePermissionExample example=new SysRolePermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return sysRolePermissionMapper.deleteByExample(example);
	}


}
