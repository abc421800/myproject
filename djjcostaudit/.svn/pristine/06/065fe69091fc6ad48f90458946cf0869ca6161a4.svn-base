package com.cost168.costaudit.service.cost;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cost168.costaudit.pojo.cost.CostDpctRelation;
import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.pojo.cost.CostTaskExample;

public interface CostTaskService {
	
	List<CostTask> selectByExample(CostTaskExample example);
	
	CostTask selectByPrimaryKey(String id);
	
	int insertSelective(CostTask record);
	
	int updateByPrimaryKeySelective(CostTask record);
	
	List<CostTask> selectByDocId(String docId);
	
	List<CostTask> selectListByMap(Map<String,Object> map);
	
	int selectCountByMap(Map<String,Object> map);
    
	int deleteByPrimaryKey(String id);

	void insertSelectiveMap(CostTask costTask, CostDpctRelation dpct,Map<String, Object> map);
	
	public List<CostTask> importTaskGg(HttpServletRequest request);
	
	public List<CostTask> importTaskDj(HttpServletRequest request);
	
	public List<CostTask> importTaskHt(HttpServletRequest request);
	
	public CostTask selectByTaskCode(String code);
}
