package com.cost168.costaudit.service.work;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.work.WorkProcessHistory;
import com.cost168.costaudit.pojo.work.WorkProcessHistoryExample;
/**
 * 
 * ClassName: WorkProcessHistoryService 
 * @Description: TODO
 * @author lixiang
 * @date 2019-5-30上午10:00:56
 * @Company  广东华联软件科技有限公司
 */
public interface WorkProcessHistoryService {
	
	List<WorkProcessHistory> selectByExample(WorkProcessHistoryExample example);

	WorkProcessHistory selectByPrimaryKey(String id);

    int insertSelective(WorkProcessHistory record);

    int updateByPrimaryKeySelective(WorkProcessHistory workProcessHistory);

    int deleteByPrimaryKey(String id);

    List<WorkProcessHistory> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
    
    WorkProcessHistory getCurrentNode(Map<String,Object> map);

}
