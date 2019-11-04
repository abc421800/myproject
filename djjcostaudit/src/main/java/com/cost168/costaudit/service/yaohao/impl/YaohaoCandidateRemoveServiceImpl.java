package com.cost168.costaudit.service.yaohao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cost168.costaudit.mapper.yaohao.YaohaoCandidateRemoveMapper;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateRemove;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateRemoveExample;
import com.cost168.costaudit.service.yaohao.YaohaoCandidateRemoveService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class YaohaoCandidateRemoveServiceImpl implements YaohaoCandidateRemoveService{

	
	@Autowired
	private YaohaoCandidateRemoveMapper yaohaoCandidateRemoveMapper;
	
	@Override
	public List<YaohaoCandidateRemove> selectByExample(
			YaohaoCandidateRemoveExample example) {
		return yaohaoCandidateRemoveMapper.selectByExample(example);
	}

	@Override
	public YaohaoCandidateRemove selectByPrimaryKey(String id) {
		return yaohaoCandidateRemoveMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(YaohaoCandidateRemove record) {
		return yaohaoCandidateRemoveMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(
			YaohaoCandidateRemove yaohaoCandidateRemove) {
		return yaohaoCandidateRemoveMapper.updateByPrimaryKeySelective(yaohaoCandidateRemove);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return yaohaoCandidateRemoveMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<YaohaoCandidateRemove> selectListByMap(Map<String, Object> map) {
		return yaohaoCandidateRemoveMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return yaohaoCandidateRemoveMapper.selectCountByMap(map);
	}

}
