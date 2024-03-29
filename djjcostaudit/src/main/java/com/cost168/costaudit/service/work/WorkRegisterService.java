package com.cost168.costaudit.service.work;

import java.util.List;
import java.util.Map;

import com.cost168.costaudit.pojo.work.WorkRegister;
import com.cost168.costaudit.pojo.work.WorkRegisterExample;
import com.cost168.costaudit.pojo.work.vo.WorkRegisterVo;
import com.cost168.costaudit.utils.GlobalResult;

import javax.servlet.http.HttpServletRequest;

public interface WorkRegisterService {

    List<WorkRegister> selectByExample(WorkRegisterExample example);

    WorkRegister selectByPrimaryKey(String id);

    int insertSelective(WorkRegister record);

    int updateByPrimaryKeySelective(WorkRegister workRegister);

    int deleteByPrimaryKey(String id);

    List<WorkRegisterVo> selectListByMap(Map<String, Object> map);

    int selectCountByMap(Map<String, Object> map);

    List<WorkRegister> selectByDay(Map<String, Object> map);

    String selectYearList();

    String selectMonthList();

    GlobalResult attendanceImport(HttpServletRequest request);

    String selectDayStr(Map<String, Object> map);

    int updateByLeaveprocess(WorkRegister record);

    WorkRegister selectByLeaveprocess(String leaveprocess);
}
