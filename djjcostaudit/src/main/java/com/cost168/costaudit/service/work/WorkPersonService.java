package com.cost168.costaudit.service.work;

import com.cost168.costaudit.pojo.work.WorkPerson;
import com.cost168.costaudit.pojo.work.WorkPersonExample;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface WorkPersonService {

    List<WorkPerson> selectByExample(WorkPersonExample example);

    WorkPerson selectByPrimaryKey(String id);
    
    WorkPerson selectByAccount(String account);

    WorkPerson selectByName(String name);

    int insertSelective(WorkPerson record);

    int updateByPrimaryKeySelective(WorkPerson workPerson);

    int deleteByPrimaryKey(String id);
    
    int deleteByExample(WorkPersonExample example);

    List<WorkPerson> selectListByMap(Map<String,Object> map);

    int selectCountByMap(Map<String,Object> map);
    
    public List<WorkPerson> importPerson(HttpServletRequest request);
}
