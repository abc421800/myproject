package com.cost168.costaudit.service.sys.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.sys.SysOrgMapper;
import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.pojo.sys.SysOrgExample;
import com.cost168.costaudit.pojo.sys.SysOrgExample.Criteria;
import com.cost168.costaudit.service.sys.SysOrgService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgServiceImpl implements SysOrgService {

	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Override
	public List<SysOrg> selectByExample(SysOrgExample example) {
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public SysOrg selectByPrimaryKey(String id) {
		return sysOrgMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysOrg record) {
		record.setCreateTime(new Date());
		return sysOrgMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysOrg sysOrg) {
		sysOrg.setUpdateTime(new Date());
		return sysOrgMapper.updateByPrimaryKeySelective(sysOrg);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return sysOrgMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SysOrg> selectListByMap(Map<String, Object> map) {
		return sysOrgMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return sysOrgMapper.selectCountByMap(map);
	}

	@Override
	public List<SysOrg> getChilds(SysOrg root) {
		/*SysOrgExample example = new SysOrgExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(root.getId());
		example.setOrderByClause("num asc");
		List<SysOrg> list = sysOrgMapper.selectByExample(example);*/
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pid", root.getId());
		List<SysOrg> list=sysOrgMapper.selectListByMap(map);
		return list;
	}

	@Override
	public List<SysOrg> getRoots() {
		/*SysOrgExample example = new SysOrgExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidIsNull();
		example.setOrderByClause("num asc");
		List<SysOrg> list = sysOrgMapper.selectByExample(example);*/
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pidIsNull", "pidIsNull");
		List<SysOrg> list=sysOrgMapper.selectListByMap(map);
		return list;
	}

	@Override
	public SysOrg selectByOrgName(String orgName) {
		SysOrgExample example=new SysOrgExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(orgName);
		List<SysOrg> orgList=sysOrgMapper.selectByExample(example);
		if(null!=orgList && orgList.size()>0){
			return orgList.get(0);
		}else{
			return null;
		}
	}

}
