package com.cost168.costaudit.service.sys.impl;

import java.util.Date;
import java.util.List;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.sys.SysRoleMapper;
import com.cost168.costaudit.pojo.sys.SysRole;
import com.cost168.costaudit.pojo.sys.SysRoleExample;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.sys.SysRoleExample.Criteria;
import com.cost168.costaudit.pojo.sys.SysRolePermissionKey;
import com.cost168.costaudit.service.sys.SysRolePermissionService;
import com.cost168.costaudit.service.sys.SysRoleService;
import com.cost168.costaudit.shiro.shiroUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRolePermissionService sysRolePermissionService;

	@Override
	public List<SysRole> selectByExample(SysRoleExample example) {
		return sysRoleMapper.selectByExample(example);
	}

	@Override
	public SysRole selectByPrimaryKey(String id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysRole record,Map<String, Object> map) {
		//插入role_per
		if(!StringUtils.isBlank(record.getPermissionId())){
			String perIds[]=record.getPermissionId().split(",");
			SysRolePermissionKey rp=null;
			for( String p:perIds){
				rp=new SysRolePermissionKey();
				rp.setRoleId(record.getId());
				rp.setPermissionId(p);
				sysRolePermissionService.insertSelective(rp);
			}
		}
		SysUser user= shiroUtil.getInstance().currentUser();
		record.setCreater(user.getName());
		record.setCreateTime(new Date());
		return sysRoleMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole sysOrg,Map<String, Object> map) {
		
		String roleId=sysOrg.getId();
		String perId=sysOrg.getPermissionId();
		if(roleId!=null && !"".equals(roleId)){
			sysRolePermissionService.deleteRolePerByRoleId(roleId);
			String perIds[]=perId.split(",");
			SysRolePermissionKey rp=null;
			for( String p:perIds){
				rp=new SysRolePermissionKey();
				rp.setRoleId(roleId);
				rp.setPermissionId(p);
				sysRolePermissionService.insertSelective(rp);
			}
		}
		sysOrg.setUpdateTime(new Date());
		return sysRoleMapper.updateByPrimaryKeySelective(sysOrg);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return sysRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SysRole> selectListByMap(Map<String, Object> map) {
		return sysRoleMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return sysRoleMapper.selectCountByMap(map);
	}

	@Override
	public SysRole selectByRoleName(String roleName) {
		SysRoleExample example=new SysRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(roleName);
		List<SysRole> roleList=sysRoleMapper.selectByExample(example);
		if(null!=roleList && roleList.size()>0){
			return roleList.get(0);
		}else{
			return null;
		}
	}

}
