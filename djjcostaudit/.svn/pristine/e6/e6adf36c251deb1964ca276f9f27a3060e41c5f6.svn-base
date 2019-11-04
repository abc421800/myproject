package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.cost.CostDpctRelation;

public interface CostDpctRelationService {
	
	    //插入来往文件 、项目、合同、审价任务 关联表
		int insertSelective(CostDpctRelation record);
		
		List<CostDpctRelation> selectByDocumentId(String id);
		
		List<CostDpctRelation> selectByProjectId(String id);
		
		List<CostDpctRelation> selectByProjectIdAndDocId(String id,String docId);
		
		List<CostDpctRelation> selectByContractId(String id);
		
		List<CostDpctRelation> selectByTaskId(String id);
		
		List<CostDpctRelation> selectByMap(Map<String,Object> map);
		
		CostDpctRelation selectByPrimaryKey(String id);
		
		int deleteByPrimaryKey(String id);
		
		int updateByPrimaryKeySelective(CostDpctRelation record);


}
