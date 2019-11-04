package com.cost168.costaudit.service.yaohao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.yaohao.YaohaoPunishMapper;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.yaohao.YaohaoPunish;
import com.cost168.costaudit.pojo.yaohao.YaohaoPunishExample;
import com.cost168.costaudit.service.yaohao.YaohaoPunishService;
import com.cost168.costaudit.shiro.shiroUtil;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class YaohaoPunishServiceImpl implements YaohaoPunishService {
	
	@Autowired
	private YaohaoPunishMapper yaohaoPunishMapper;

	@Override
	public List<YaohaoPunish> selectByExample(YaohaoPunishExample example) {
		return yaohaoPunishMapper.selectByExample(example);
	}

	@Override
	public YaohaoPunish selectByPrimaryKey(String id) {
		return yaohaoPunishMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(YaohaoPunish record) {
		return yaohaoPunishMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(YaohaoPunish yaohaoPunish) {
		return yaohaoPunishMapper.updateByPrimaryKeySelective(yaohaoPunish);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return yaohaoPunishMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<YaohaoPunish> selectListByMap(Map<String, Object> map) {
		return yaohaoPunishMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return yaohaoPunishMapper.selectCountByMap(map);
	}

	@Override
	public List<YaohaoPunish> selectListTzByMap(Map<String, Object> map) {
		return yaohaoPunishMapper.selectListTzByMap(map);
	}

	@Override
	public int selectCountTzByMap(Map<String, Object> map) {
		return yaohaoPunishMapper.selectCountTzByMap(map);
	}

	@Override
	public int save(HttpServletRequest request, YaohaoPunish yaohaoPunish)throws Exception {
		yaohaoPunish.setEnterpriseCode(yaohaoPunish.getEnterpriseId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String punishTime =request.getParameter("punishTimeStr");
		String start=punishTime.substring(0, 10); 
		String end=punishTime.substring(punishTime.length()-10, punishTime.length());
		yaohaoPunish.setExecuteStartTime(sdf.parse(start));
		yaohaoPunish.setExecuteEndTime(sdf.parse(end));
        String currentDate = sdf.format(new Date());
        String currenYear = currentDate.substring(0, 4);
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		yaohaoPunish.setCreater(currentUser.getName());
		yaohaoPunish.setCreareTime(new Date());
		yaohaoPunish.setPunishYear(currenYear);
		int i=yaohaoPunishMapper.insertSelective(yaohaoPunish);
		return i;
	}

	@Override
	public int update(HttpServletRequest request, YaohaoPunish yaohaoPunish)throws Exception{
		yaohaoPunish.setEnterpriseCode(yaohaoPunish.getEnterpriseId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String punishTime =request.getParameter("punishTimeStr");
		String start=punishTime.substring(0, 10); 
		String end=punishTime.substring(punishTime.length()-10, punishTime.length());
		yaohaoPunish.setExecuteStartTime(sdf.parse(start));
		yaohaoPunish.setExecuteEndTime(sdf.parse(end));
        String currentDate = sdf.format(new Date());
        String currenYear = currentDate.substring(0, 4);
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		yaohaoPunish.setCreater(currentUser.getName());
		yaohaoPunish.setCreareTime(new Date());
		yaohaoPunish.setPunishYear(currenYear);
		int i=yaohaoPunishMapper.updateByPrimaryKeySelective(yaohaoPunish);
		return i;
	}

}
