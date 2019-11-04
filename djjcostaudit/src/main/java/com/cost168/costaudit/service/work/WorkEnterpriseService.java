package com.cost168.costaudit.service.work;

import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.work.WorkEnterpriseExample;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WorkEnterpriseService {

    List<WorkEnterprise> selectByExample(WorkEnterpriseExample example);

    WorkEnterprise selectByPrimaryKey(String id);

    int insertSelective(WorkEnterprise record);

    int updateByPrimaryKeySelective(WorkEnterprise workEnterprise);

    int deleteByPrimaryKey(String id);

    List<WorkEnterprise> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
    
    public List<WorkEnterprise> importWorkEnt(HttpServletRequest request);
    
    WorkEnterprise selectByWorkEnterpriseName(String name);
}
