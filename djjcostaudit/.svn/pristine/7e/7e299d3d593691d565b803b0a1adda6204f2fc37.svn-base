package com.cost168.costaudit.service.yaohao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.yaohao.YaohaoAutocodeMapper;
import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;
import com.cost168.costaudit.service.yaohao.YaohaoAutocodeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class YaohaoAutocodeServiceImpl implements YaohaoAutocodeService {

	@Autowired
	private YaohaoAutocodeMapper yaohaoAutocodeMapper;
	
	@Override
	public YaohaoAutocode selectByPrimaryKey(String id) {
		return yaohaoAutocodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return yaohaoAutocodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(YaohaoAutocode record) {
		return yaohaoAutocodeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<YaohaoAutocode> selectListByMap(Map<String, Object> map) {
		return yaohaoAutocodeMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return yaohaoAutocodeMapper.selectCountByMap(map);
	}

	@Override
	public int insertSelective(YaohaoAutocode record) {
		return yaohaoAutocodeMapper.insertSelective(record);
	}

	@Override
	public YaohaoAutocode getCodeAorB() {
		YaohaoAutocode code=yaohaoAutocodeMapper.selectByPrimaryKey("1");
		return code;
	}

}
