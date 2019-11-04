package com.cost168.costaudit.controller.work;

import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.work.WorkPerson;
import com.cost168.costaudit.pojo.work.vo.WorkAttendancePersonExportParam;
import com.cost168.costaudit.service.work.WorkAttendancePersonService;
import com.cost168.costaudit.service.work.WorkEnterpriseService;
import com.cost168.costaudit.service.work.WorkPersonService;
import com.cost168.costaudit.service.work.WorkRegisterService;
import com.cost168.costaudit.utils.EUDataGridResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @description: 考勤人员台账控制层
 * @author: ZYL
 * @create: 2019-05-24
 */
@Controller
@RequestMapping("/workAttendancePerson")
public class WorkAttendancePersonController {
    @Resource
    private WorkAttendancePersonService workAttendancePersonService;
    @Resource
    private WorkPersonService workPersonService;
    @Resource
    private WorkEnterpriseService workEnterpriseService;
    @Resource
    private WorkRegisterService workRegisterService;


    /**
     * created by ZYL on 2019/6/17
     * 跳转至驻场人员详情页
     */
    @RequestMapping("/toAttendancePerson")
    public String toAttendancePerson(HttpServletRequest request) {
        String pageYear = workRegisterService.selectYearList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        String currentYear = currentDate.substring(0, 4);
        request.setAttribute("pageYear", pageYear);
        request.setAttribute("currentYear", currentYear);
        return "/attendanceManager/attendanceManager";
    }

    /**
     * created by ZYL on 2019/6/17
     * 根据条件查询考勤人员台账
     */
    @ResponseBody
    @RequestMapping("/listPerson")
    public EUDataGridResult selectList(HttpServletRequest request, int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        return workAttendancePersonService.selectWorkAttendancePerson(request, page, rows, map);
    }

    /**
     * created by ZYL on 2019/6/6
     * 考勤人员详情页
     */
    @RequestMapping("/toPersonEdit")
    public String residentPersonDetail(HttpServletRequest request) {
        String id = request.getParameter("id");
        WorkPerson person = workPersonService.selectByPrimaryKey(id);
        WorkEnterprise workEnterprise = workEnterpriseService.selectByPrimaryKey(person.getEnterpriseId());
        person.setEnterpriseName(workEnterprise.getName());
        request.setAttribute("obj", person);
        return "/register/personEdit";
    }

    /**
     * created by ZYL on 2019/6/6
     * 考勤人员台账导出
     */
    @ResponseBody
    @RequestMapping("/exportData")
    public void exportData(HttpServletRequest request, HttpServletResponse response, String regYear) {
        List<WorkAttendancePersonExportParam> list = new ArrayList<WorkAttendancePersonExportParam>();
        Map<String, Object> selectMap = new HashMap<String, Object>();
        try {
            ServletOutputStream out = response.getOutputStream();
            String personIds = request.getParameter("personIds");
            if (null != personIds && !"".equals(personIds)) {
                String arr[] = personIds.split(",");
                for (String personId : arr) {
                    selectMap.put("personId", personId);
                    WorkAttendancePersonExportParam param = workAttendancePersonService.selectAttendanceById(selectMap);
                    list.add(param);
                }
            } else {
                list = workAttendancePersonService.selectDataList(selectMap);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String path = request.getSession().getServletContext().getRealPath("/template/report/attendance_template.xlsx");
            String dateString = formatter.format(new Date());
            String fileName = regYear + "年度考勤台账导出_" + dateString + ".xlsx";
            String downloadFileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attactment; fileName=" + downloadFileName);
            workAttendancePersonService.exportWorkAttendancePerson(path, out, list, regYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
