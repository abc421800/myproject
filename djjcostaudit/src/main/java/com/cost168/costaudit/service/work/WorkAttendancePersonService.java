package com.cost168.costaudit.service.work;

import com.cost168.costaudit.pojo.work.vo.WorkAttendancePersonExportParam;
import com.cost168.costaudit.utils.EUDataGridResult;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @description: 考勤人员台账服务层
 * @author: ZYL
 * @create: 2019-05-24
 */
public interface WorkAttendancePersonService {
    EUDataGridResult selectWorkAttendancePerson(HttpServletRequest request, int page, int rows,Map<String, Object> map);

    int selectCountAttendancePersonByMap(Map<String, Object> map);

    //查询导出考勤人员台账所需数据
    List<WorkAttendancePersonExportParam> selectDataList(Map<String, Object> map);

    //导出考勤人员台账
    public void exportWorkAttendancePerson(String path, OutputStream out, List<WorkAttendancePersonExportParam> list, String regYear);

    //根据id查询导出所需数据
    WorkAttendancePersonExportParam selectAttendanceById(Map<String, Object> map);
}
