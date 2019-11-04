package com.cost168.costaudit.service.cost.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cost168.costaudit.mapper.cost.CostAttachmentMapper;
import com.cost168.costaudit.pojo.cost.CostAttachment;
import com.cost168.costaudit.pojo.cost.CostAttachmentExample;
import com.cost168.costaudit.pojo.cost.CostAttachmentExample.Criteria;
import com.cost168.costaudit.service.cost.CostAttachmentService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * ClassName: CostAttachmentServiceImpl 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-7上午10:39:28
 * @Company  广东华联软件科技有限公司
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CostAttachmentServiceImpl implements CostAttachmentService{
	
	
	@Autowired
	private CostAttachmentMapper costAttachmentMapper;

	@Override
	public CostAttachment selectByPrimaryKey(String id) {
		return costAttachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostAttachment record) {
		return costAttachmentMapper.insertSelective(record);
	}

	@Override
	public List<CostAttachment> selectByRelationId(String RelationId) {
		CostAttachmentExample example = new CostAttachmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeIdEqualTo(RelationId);
		example.setOrderByClause("creater_time desc");
		List<CostAttachment> list = costAttachmentMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<CostAttachment> selectRelationAttachByMap(Map<String, Object> map) {
		String dataType=(String) map.get("dataType");
		String taskId=(String) map.get("typeId");
		CostAttachmentExample example = new CostAttachmentExample();
		Criteria criteria = example.createCriteria();
		if(null!=taskId){
			criteria.andTypeIdEqualTo(taskId);
		}
		if(null!=dataType){
			criteria.andDataTypeEqualTo(dataType);
		}	
		example.setOrderByClause("creater_time asc");
		List<CostAttachment> list = costAttachmentMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<CostAttachment> selectByPid(String pid) {
		CostAttachmentExample example = new CostAttachmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(pid);
		List<CostAttachment> list = costAttachmentMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costAttachmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CostAttachment record) {
		return costAttachmentMapper.updateByPrimaryKeySelective(record);
	}

	
	
	

}
