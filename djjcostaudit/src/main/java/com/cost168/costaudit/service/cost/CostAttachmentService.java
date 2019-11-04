package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostAttachment;

public interface CostAttachmentService {
	
	CostAttachment selectByPrimaryKey(String id);
	
	int insertSelective(CostAttachment record);
	
	List<CostAttachment> selectByRelationId(String relationId);
	
	List<CostAttachment> selectRelationAttachByMap(Map<String,Object> map);
	
	List<CostAttachment> selectByPid(String pid);
	
	int deleteByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(CostAttachment record);

}
