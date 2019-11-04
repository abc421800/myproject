package com.cost168.costaudit.service.work.impl;

import com.cost168.costaudit.mapper.work.WorkRegisterMapper;
import com.cost168.costaudit.pojo.sys.SysRole;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.vo.WorkAttendanceParam;
import com.cost168.costaudit.pojo.work.vo.WorkAttendancePersonExportParam;
import com.cost168.costaudit.service.sys.SysRoleService;
import com.cost168.costaudit.service.sys.SysUserRoleService;
import com.cost168.costaudit.service.work.WorkAttendancePersonService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.OrderUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: 考勤人员台账实现层
 * @author: ZYL
 * @create: 2019-05-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkAttendancePersonServiceImpl implements WorkAttendancePersonService {
    @Resource
    private WorkRegisterMapper workRegisterMapper;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleService sysRoleService;

    private final Logger LOG = LogManager.getLogger(WorkAttendancePersonServiceImpl.class);

    @Override
    public EUDataGridResult selectWorkAttendancePerson(HttpServletRequest request, int page, int rows, Map<String, Object> map) {
        SysUser currentUser = shiroUtil.getInstance().currentUser();
        String name = request.getParameter("name");
        String enterpriseName = request.getParameter("enterpriseName");
        String morningOrAfternoon = request.getParameter("morningOrAfternoon");
        String status = request.getParameter("status");
        String attStarttime = request.getParameter("attStarttime");
        String attEndtime = request.getParameter("attEndtime");
        String regYear1 = request.getParameter("regYear");
        map.put("name", name);
        map.put("enterpriseName", enterpriseName);
        map.put("morningOrAfternoon", morningOrAfternoon);
        map.put("status", status);
        map.put("year", regYear1);
        map.put("attStarttime", attStarttime);
        map.put("attEndtime", attEndtime);
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        String roleId = sysUserRoleService.selectRoleByUserId(currentUser.getId());
        SysRole role = sysRoleService.selectByPrimaryKey(roleId);
        if (null != role && "驻场人员".equals(role.getName())) {
            map.put("account", currentUser.getAccount());
        }
        EUDataGridResult result = new EUDataGridResult();
        List<WorkAttendanceParam> person = workRegisterMapper.selectWorkAttendancePerson(map);
        int total = workRegisterMapper.selectCountAttendancePersonByMap(map);
        if (person.size() > 0 && total > 0) {
        	//无效排序到最后面
        	List<WorkAttendanceParam> lastList=new ArrayList<WorkAttendanceParam>();
            //汇总
            List<WorkAttendanceParam> footer = new ArrayList<WorkAttendanceParam>();
            WorkAttendanceParam w1 = new WorkAttendanceParam();
            w1.setName("汇总");
            w1.setAnnualTotal("0");
            w1.setMonthOfWorkday1("0");
            w1.setMonthOfWorkday2("0");
            w1.setMonthOfWorkday3("0");
            w1.setMonthOfWorkday4("0");
            w1.setMonthOfWorkday5("0");
            w1.setMonthOfWorkday6("0");
            w1.setMonthOfWorkday7("0");
            w1.setMonthOfWorkday8("0");
            w1.setMonthOfWorkday9("0");
            w1.setMonthOfWorkday10("0");
            w1.setMonthOfWorkday11("0");
            w1.setMonthOfWorkday12("0");
            w1.setMonthOfLeave1("0");
            w1.setMonthOfLeave2("0");
            w1.setMonthOfLeave3("0");
            w1.setMonthOfLeave4("0");
            w1.setMonthOfLeave5("0");
            w1.setMonthOfLeave6("0");
            w1.setMonthOfLeave7("0");
            w1.setMonthOfLeave8("0");
            w1.setMonthOfLeave9("0");
            w1.setMonthOfLeave10("0");
            w1.setMonthOfLeave11("0");
            w1.setMonthOfLeave12("0");
            w1.setMonthOfRestday1("0");
            w1.setMonthOfRestday2("0");
            w1.setMonthOfRestday3("0");
            w1.setMonthOfRestday4("0");
            w1.setMonthOfRestday5("0");
            w1.setMonthOfRestday6("0");
            w1.setMonthOfRestday7("0");
            w1.setMonthOfRestday8("0");
            w1.setMonthOfRestday9("0");
            w1.setMonthOfRestday10("0");
            w1.setMonthOfRestday11("0");
            w1.setMonthOfRestday12("0");
            w1.setMonthOfOvertime1("0");
            w1.setMonthOfOvertime2("0");
            w1.setMonthOfOvertime3("0");
            w1.setMonthOfOvertime4("0");
            w1.setMonthOfOvertime5("0");
            w1.setMonthOfOvertime6("0");
            w1.setMonthOfOvertime7("0");
            w1.setMonthOfOvertime8("0");
            w1.setMonthOfOvertime9("0");
            w1.setMonthOfOvertime10("0");
            w1.setMonthOfOvertime11("0");
            w1.setMonthOfOvertime12("0");
            w1.setYearOfWork("0");
            w1.setYearOfRest("0");
            w1.setYearOfLeave("0");
            w1.setYearOvertime("0");
            w1.setAttendanceRate("0%");
            int temp = (page - 1) * rows + 1;
            for (WorkAttendanceParam w2 : person) {	
                w2.setRaking("" + temp);
                w1.setAnnualTotal(w2.getAnnualTotal().equals("0") || w2.getAnnualTotal() == null ? w1.getAnnualTotal() : String.valueOf((Double.parseDouble(w1.getAnnualTotal()) + Double.parseDouble(w2.getAnnualTotal()))));
                w1.setMonthOfWorkday1(w2.getMonthOfWorkday1().equals("0") ? w1.getMonthOfWorkday1() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday1()) + Double.parseDouble(w2.getMonthOfWorkday1()))));
                w1.setMonthOfWorkday2(w2.getMonthOfWorkday2().equals("0") ? w1.getMonthOfWorkday2() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday2()) + Double.parseDouble(w2.getMonthOfWorkday2()))));
                w1.setMonthOfWorkday3(w2.getMonthOfWorkday3().equals("0") ? w1.getMonthOfWorkday3() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday3()) + Double.parseDouble(w2.getMonthOfWorkday3()))));
                w1.setMonthOfWorkday4(w2.getMonthOfWorkday4().equals("0") ? w1.getMonthOfWorkday4() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday4()) + Double.parseDouble(w2.getMonthOfWorkday4()))));
                w1.setMonthOfWorkday5(w2.getMonthOfWorkday5().equals("0") ? w1.getMonthOfWorkday5() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday5()) + Double.parseDouble(w2.getMonthOfWorkday5()))));
                w1.setMonthOfWorkday6(w2.getMonthOfWorkday6().equals("0") ? w1.getMonthOfWorkday6() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday6()) + Double.parseDouble(w2.getMonthOfWorkday6()))));
                w1.setMonthOfWorkday7(w2.getMonthOfWorkday7().equals("0") ? w1.getMonthOfWorkday7() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday7()) + Double.parseDouble(w2.getMonthOfWorkday7()))));
                w1.setMonthOfWorkday8(w2.getMonthOfWorkday8().equals("0") ? w1.getMonthOfWorkday8() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday8()) + Double.parseDouble(w2.getMonthOfWorkday8()))));
                w1.setMonthOfWorkday9(w2.getMonthOfWorkday9().equals("0") ? w1.getMonthOfWorkday9() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday9()) + Double.parseDouble(w2.getMonthOfWorkday9()))));
                w1.setMonthOfWorkday10(w2.getMonthOfWorkday10().equals("0") ? w1.getMonthOfWorkday10() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday10()) + Double.parseDouble(w2.getMonthOfWorkday10()))));
                w1.setMonthOfWorkday11(w2.getMonthOfWorkday11().equals("0") ? w1.getMonthOfWorkday11() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday11()) + Double.parseDouble(w2.getMonthOfWorkday11()))));
                w1.setMonthOfWorkday12(w2.getMonthOfWorkday12().equals("0") ? w1.getMonthOfWorkday12() : String.valueOf((Double.parseDouble(w1.getMonthOfWorkday12()) + Double.parseDouble(w2.getMonthOfWorkday11()))));
                w1.setMonthOfLeave1(w2.getMonthOfLeave1().equals("0") ? w1.getMonthOfLeave1() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave1()) + Double.parseDouble(w2.getMonthOfLeave1()))));
                w1.setMonthOfLeave2(w2.getMonthOfLeave2().equals("0") ? w1.getMonthOfLeave2() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave2()) + Double.parseDouble(w2.getMonthOfLeave2()))));
                w1.setMonthOfLeave3(w2.getMonthOfLeave3().equals("0") ? w1.getMonthOfLeave3() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave3()) + Double.parseDouble(w2.getMonthOfLeave3()))));
                w1.setMonthOfLeave4(w2.getMonthOfLeave4().equals("0") ? w1.getMonthOfLeave4() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave4()) + Double.parseDouble(w2.getMonthOfLeave4()))));
                w1.setMonthOfLeave5(w2.getMonthOfLeave5().equals("0") ? w1.getMonthOfLeave5() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave5()) + Double.parseDouble(w2.getMonthOfLeave5()))));
                w1.setMonthOfLeave6(w2.getMonthOfLeave6().equals("0") ? w1.getMonthOfLeave6() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave6()) + Double.parseDouble(w2.getMonthOfLeave6()))));
                w1.setMonthOfLeave7(w2.getMonthOfLeave7().equals("0") ? w1.getMonthOfLeave7() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave7()) + Double.parseDouble(w2.getMonthOfLeave7()))));
                w1.setMonthOfLeave8(w2.getMonthOfLeave8().equals("0") ? w1.getMonthOfLeave8() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave8()) + Double.parseDouble(w2.getMonthOfLeave8()))));
                w1.setMonthOfLeave9(w2.getMonthOfLeave9().equals("0") ? w1.getMonthOfLeave9() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave9()) + Double.parseDouble(w2.getMonthOfLeave9()))));
                w1.setMonthOfLeave10(w2.getMonthOfLeave10().equals("0") ? w1.getMonthOfLeave10() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave10()) + Double.parseDouble(w2.getMonthOfLeave10()))));
                w1.setMonthOfLeave11(w2.getMonthOfLeave11().equals("0") ? w1.getMonthOfLeave11() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave11()) + Double.parseDouble(w2.getMonthOfLeave11()))));
                w1.setMonthOfLeave12(w2.getMonthOfLeave12().equals("0") ? w1.getMonthOfLeave12() : String.valueOf((Double.parseDouble(w1.getMonthOfLeave12()) + Double.parseDouble(w2.getMonthOfLeave12()))));
                w1.setMonthOfRestday1(w2.getMonthOfRestday1().equals("0") ? w1.getMonthOfRestday1() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday1()) + Double.parseDouble(w2.getMonthOfRestday1()))));
                w1.setMonthOfRestday2(w2.getMonthOfRestday2().equals("0") ? w1.getMonthOfRestday2() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday2()) + Double.parseDouble(w2.getMonthOfRestday2()))));
                w1.setMonthOfRestday3(w2.getMonthOfRestday3().equals("0") ? w1.getMonthOfRestday3() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday3()) + Double.parseDouble(w2.getMonthOfRestday3()))));
                w1.setMonthOfRestday4(w2.getMonthOfRestday4().equals("0") ? w1.getMonthOfRestday4() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday4()) + Double.parseDouble(w2.getMonthOfRestday4()))));
                w1.setMonthOfRestday5(w2.getMonthOfRestday5().equals("0") ? w1.getMonthOfRestday5() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday5()) + Double.parseDouble(w2.getMonthOfRestday5()))));
                w1.setMonthOfRestday6(w2.getMonthOfRestday6().equals("0") ? w1.getMonthOfRestday6() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday6()) + Double.parseDouble(w2.getMonthOfRestday6()))));
                w1.setMonthOfRestday7(w2.getMonthOfRestday7().equals("0") ? w1.getMonthOfRestday7() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday7()) + Double.parseDouble(w2.getMonthOfRestday7()))));
                w1.setMonthOfRestday8(w2.getMonthOfRestday8().equals("0") ? w1.getMonthOfRestday8() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday8()) + Double.parseDouble(w2.getMonthOfRestday8()))));
                w1.setMonthOfRestday9(w2.getMonthOfRestday9().equals("0") ? w1.getMonthOfRestday9() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday9()) + Double.parseDouble(w2.getMonthOfRestday9()))));
                w1.setMonthOfRestday10(w2.getMonthOfRestday10().equals("0") ? w1.getMonthOfRestday10() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday10()) + Double.parseDouble(w2.getMonthOfRestday10()))));
                w1.setMonthOfRestday11(w2.getMonthOfRestday11().equals("0") ? w1.getMonthOfRestday11() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday11()) + Double.parseDouble(w2.getMonthOfRestday11()))));
                w1.setMonthOfRestday12(w2.getMonthOfRestday12().equals("0") ? w1.getMonthOfRestday12() : String.valueOf((Double.parseDouble(w1.getMonthOfRestday12()) + Double.parseDouble(w2.getMonthOfRestday12()))));
                w1.setMonthOfOvertime1(w2.getMonthOfOvertime1().equals("0") ? w1.getMonthOfOvertime1() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime1()) + Double.parseDouble(w2.getMonthOfOvertime1()))));
                w1.setMonthOfOvertime2(w2.getMonthOfOvertime2().equals("0") ? w1.getMonthOfOvertime2() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime2()) + Double.parseDouble(w2.getMonthOfOvertime2()))));
                w1.setMonthOfOvertime3(w2.getMonthOfOvertime3().equals("0") ? w1.getMonthOfOvertime3() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime3()) + Double.parseDouble(w2.getMonthOfOvertime3()))));
                w1.setMonthOfOvertime4(w2.getMonthOfOvertime4().equals("0") ? w1.getMonthOfOvertime4() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime4()) + Double.parseDouble(w2.getMonthOfOvertime4()))));
                w1.setMonthOfOvertime5(w2.getMonthOfOvertime5().equals("0") ? w1.getMonthOfOvertime5() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime5()) + Double.parseDouble(w2.getMonthOfOvertime5()))));
                w1.setMonthOfOvertime6(w2.getMonthOfOvertime6().equals("0") ? w1.getMonthOfOvertime6() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime6()) + Double.parseDouble(w2.getMonthOfOvertime6()))));
                w1.setMonthOfOvertime7(w2.getMonthOfOvertime7().equals("0") ? w1.getMonthOfOvertime7() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime7()) + Double.parseDouble(w2.getMonthOfOvertime7()))));
                w1.setMonthOfOvertime8(w2.getMonthOfOvertime8().equals("0") ? w1.getMonthOfOvertime8() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime8()) + Double.parseDouble(w2.getMonthOfOvertime8()))));
                w1.setMonthOfOvertime9(w2.getMonthOfOvertime9().equals("0") ? w1.getMonthOfOvertime9() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime9()) + Double.parseDouble(w2.getMonthOfOvertime9()))));
                w1.setMonthOfOvertime10(w2.getMonthOfOvertime10().equals("0") ? w1.getMonthOfOvertime10() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime10()) + Double.parseDouble(w2.getMonthOfOvertime10()))));
                w1.setMonthOfOvertime11(w2.getMonthOfOvertime11().equals("0") ? w1.getMonthOfOvertime11() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime11()) + Double.parseDouble(w2.getMonthOfOvertime11()))));
                w1.setMonthOfOvertime12(w2.getMonthOfOvertime12().equals("0") ? w1.getMonthOfOvertime12() : String.valueOf((Double.parseDouble(w1.getMonthOfOvertime12()) + Double.parseDouble(w2.getMonthOfOvertime12()))));
                w1.setYearOfWork(w2.getYearOfWork().equals("0") ? w1.getYearOfWork() : String.valueOf((Double.parseDouble(w1.getYearOfWork()) + Double.parseDouble(w2.getYearOfWork()))));
                w1.setYearOfRest(w2.getYearOfRest().equals("0") ? w1.getYearOfRest() : String.valueOf((Double.parseDouble(w1.getYearOfRest()) + Double.parseDouble(w2.getYearOfRest()))));
                w1.setYearOfLeave(w2.getYearOfLeave().equals("0") ? w1.getYearOfLeave() : String.valueOf((Double.parseDouble(w1.getYearOfLeave()) + Double.parseDouble(w2.getYearOfLeave()))));
                w1.setYearOvertime(w2.getYearOvertime().equals("0") ? w1.getYearOvertime() : String.valueOf((Double.parseDouble(w1.getYearOvertime()) + Double.parseDouble(w2.getYearOvertime()))));

                String cYear = w2.getYear();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = sdf.format(new Date());
                String regYear = currentDate.substring(0, 4);
                //12月份上班天数
                int month12Work = Integer.parseInt(w2.getMonthOfWorkday12());
                if (null != w2.getYear()) {
                    //如果年份等于当前年，12月有上班天数，且人员有效，则开始计算考勤率
                    if (w2.getYear().equals(regYear) && month12Work > 0 && w2.getStatus().equals("有效")) {
                        //当前年某月的总天数
                        int month1 = OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 1);
                        //当前年的总天数1+2+3+4+....+12
                        int year = (month1 + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 2) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 3) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 4) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 5) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 6) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 7) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 8) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 9) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 10) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 11) + OrderUtil.getYearMonthDays(Integer.parseInt(regYear), 12));
                        w2.setAttendanceRate(String.valueOf(Double.parseDouble(w2.getYearOfWork()) / (year - Double.parseDouble(w2.getYearOfRest())) * 100) + "%");
                        //汇总考勤率
//                        String rate = "";
//                w1.setAttendanceRate(w2.getAttendanceRate().equals("0%") ? w1.getAttendanceRate() : String.valueOf((Double.parseDouble(w1.getAttendanceRate()) + Double.parseDouble(w2.getAttendanceRate()))));
                    } else {
                        //小于2019年
                        if (Integer.parseInt(w2.getYear()) < Integer.parseInt(regYear) && w2.getStatus().equals("有效")) {
                            //当前年的总天数
                            int currentYear = (OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 2) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 2) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 3) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 4) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 5) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 6) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 7) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 8) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 9) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 10) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 11) + OrderUtil.getYearMonthDays(Integer.parseInt(cYear), 12));
                            //设置出勤率,出勤率计算规则 (上班天数 ：1+2+3...12)/(年总天数-(应上班天数：1+2+3...12))*100%,when当年等于当前年，12月份有上班天数时，计算考勤率
                            w2.setAttendanceRate(String.valueOf(Double.parseDouble(w2.getYearOfWork()) / (currentYear - Double.parseDouble(w2.getYearOfRest())) * 100) + "%");
                        } else {
                            //等于2019年且12月无上班记录
                            w2.setAttendanceRate("0%");
                        }
                    }
                }
                temp++;
            }
            //无效排序到最后
            Iterator<WorkAttendanceParam> iterator = person.iterator();
            while (iterator.hasNext()) {
            	WorkAttendanceParam wap = iterator.next();
            	if("无效".equals(wap.getStatus())){
                	lastList.add(wap);
                	iterator.remove();
                }
            }
            person.addAll(lastList);
            footer.add(w1);
            result.setRows(person);
            result.setTotal(total);
            result.setFooter(footer);
        }
        return result;
    }

    @Override
    public int selectCountAttendancePersonByMap(Map<String, Object> map) {
        return workRegisterMapper.selectCountAttendancePersonByMap(map);
    }

    @Override
    public List<WorkAttendancePersonExportParam> selectDataList(Map<String, Object> map) {
        return workRegisterMapper.selectDataList(map);
    }

    @Override
    public WorkAttendancePersonExportParam selectAttendanceById(Map<String, Object> map) {
        return workRegisterMapper.selectAttendanceById(map);
    }

    /**
     * created by ZYL on 2019/6/6
     * 导出考勤人员台账
     */
    @Override
    public void exportWorkAttendancePerson(String path, OutputStream out, List<WorkAttendancePersonExportParam> list, String regYear) {
        Workbook wb = null;
        try {
            try {
                FileInputStream input = new FileInputStream(path);
                String ext = path.substring(path.lastIndexOf(".") + 1);
                if (ext.equalsIgnoreCase("xls")) {
                    //03版本及以下
                    wb = new HSSFWorkbook(input);
                } else if (ext.equalsIgnoreCase("xlsx")) {
                    //07版本及以上
                    wb = new XSSFWorkbook(input);
                }
            } catch (Exception e) {
                LOG.error("系统未知的Excel版本!");
                throw new Exception("系统未知的Excel版本", e);
            }
            Sheet sheet = wb.getSheetAt(0);
            CellStyle headStyle = wb.createCellStyle();
            //设置背景
            headStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            //设置背景色
            headStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
            //设置垂直对齐方式
            headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //首行
            int index = 0;
            Row row0 = sheet.createRow(index);
            Cell cell0 = row0.createCell(0);
            cell0.setCellStyle(headStyle);
            cell0.setCellValue(regYear + "年度考勤台账导出");
            Font contentFont = wb.createFont();
            contentFont.setFontName("宋体");
            contentFont.setFontHeightInPoints((short) 11);
            CellStyle attendanceStyle = wb.createCellStyle();
            //设置水平对齐方式
            attendanceStyle.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐方式
            attendanceStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            attendanceStyle.setFillForegroundColor(IndexedColors.WHITE.index);
            attendanceStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            attendanceStyle.setBorderRight((short) 1);
            attendanceStyle.setFont(contentFont);
            attendanceStyle.setBorderRight((short) 1);
            attendanceStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            attendanceStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            attendanceStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            attendanceStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            attendanceStyle.setFont(contentFont);
            //开始行
            index = 4;
            for (WorkAttendancePersonExportParam param : list) {
                Row row = sheet.createRow(index);
                Cell cell1 = row.createCell(0);
                cell1.setCellStyle(attendanceStyle);
                cell1.setCellValue(param.getName());
                Cell cell2 = row.createCell(1);
                cell2.setCellStyle(attendanceStyle);
                cell2.setCellValue(param.getEnterpriseName());
                Cell cell3 = row.createCell(2);
                cell3.setCellStyle(attendanceStyle);
                cell3.setCellValue(param.getAnnualTotal());
                Cell cell4 = row.createCell(3);
                cell4.setCellStyle(attendanceStyle);
                cell4.setCellValue(param.getYearOfnxj());
                Cell cell5 = row.createCell(4);
                cell5.setCellStyle(attendanceStyle);
                cell5.setCellValue(param.getYearOfWork());
                Cell cell6 = row.createCell(5);
                cell6.setCellStyle(attendanceStyle);
                cell6.setCellValue(param.getYearOfOvertime());
                Cell cell7 = row.createCell(6);
                cell7.setCellStyle(attendanceStyle);
                cell7.setCellValue(param.getYearOfnxj());
                Cell cell8 = row.createCell(7);
                cell8.setCellStyle(attendanceStyle);
                cell8.setCellValue(param.getYearOftqj());

                Cell cell9 = row.createCell(8);
                cell9.setCellStyle(attendanceStyle);
                cell9.setCellValue(param.getYearOfshij());

                Cell cell10 = row.createCell(9);
                cell10.setCellStyle(attendanceStyle);
                cell10.setCellValue(param.getYearOfsyj());

                Cell cell11 = row.createCell(10);
                cell11.setCellStyle(attendanceStyle);
                cell11.setCellValue(param.getYearOfjsj());

                Cell cell12 = row.createCell(11);
                cell12.setCellStyle(attendanceStyle);
                cell12.setCellValue(param.getYearOfbj());

                Cell cell13 = row.createCell(12);
                cell13.setCellStyle(attendanceStyle);
                cell13.setCellValue(param.getYearOfhj());

                Cell cell14 = row.createCell(13);
                cell14.setCellStyle(attendanceStyle);
                cell14.setCellValue(param.getYearOfsangj());

                Cell cell15 = row.createCell(14);
                cell15.setCellStyle(attendanceStyle);
                cell15.setCellValue(param.getYearOflgxx());

                Cell cell16 = row.createCell(15);
                cell16.setCellStyle(attendanceStyle);
                cell16.setCellValue(param.getYearOfcc());

                Cell cell17 = row.createCell(16);
                cell17.setCellStyle(attendanceStyle);
                cell17.setCellValue(param.getYearOfkg());

                Cell cell18 = row.createCell(17);
                cell18.setCellStyle(attendanceStyle);
                cell18.setCellValue(param.getYearOfqt());
//                如果12月份有上班数据，则计算出勤率
                int workDay = Integer.parseInt(param.getMonthOfWorkday12());
                if (workDay > 0) {
                    Cell cell19 = row.createCell(18);
                    cell19.setCellStyle(attendanceStyle);
                    cell19.setCellValue(String.format("%.2f", Double.parseDouble(param.getAttendanceRate())) + "%");
                } else {
                    Cell cell19 = row.createCell(18);
                    cell19.setCellStyle(attendanceStyle);
                    cell19.setCellValue("0%");
                }

                Cell cell20 = row.createCell(19);
                cell20.setCellStyle(attendanceStyle);
                cell20.setCellValue(param.getMonthOfWorkday1());

                Cell cell21 = row.createCell(20);
                cell21.setCellStyle(attendanceStyle);
                cell21.setCellValue(param.getMonthOfOvertime1());

                Cell cell22 = row.createCell(21);
                cell22.setCellStyle(attendanceStyle);
                cell22.setCellValue(param.getMonthOfnxj1());

                Cell cell23 = row.createCell(22);
                cell23.setCellStyle(attendanceStyle);
                cell23.setCellValue(param.getMonthOftqj1());

                Cell cell24 = row.createCell(23);
                cell24.setCellStyle(attendanceStyle);
                cell24.setCellValue(param.getMonthOfshij1());

                Cell cell25 = row.createCell(24);
                cell25.setCellStyle(attendanceStyle);
                cell25.setCellValue(param.getMonthOfsyj1());

                Cell cell26 = row.createCell(25);
                cell26.setCellStyle(attendanceStyle);
                cell26.setCellValue(param.getMonthOfjs1());

                Cell cell27 = row.createCell(26);
                cell27.setCellStyle(attendanceStyle);
                cell27.setCellValue(param.getMonthOfbj1());

                Cell cell28 = row.createCell(27);
                cell28.setCellStyle(attendanceStyle);
                cell28.setCellValue(param.getMonthOfhj1());

                Cell cell29 = row.createCell(28);
                cell29.setCellStyle(attendanceStyle);
                cell29.setCellValue(param.getMonthOfsangj1());

                Cell cell30 = row.createCell(29);
                cell30.setCellStyle(attendanceStyle);
                cell30.setCellValue(param.getMonthOflgxx1());

                Cell cell31 = row.createCell(30);
                cell31.setCellStyle(attendanceStyle);
                cell31.setCellValue(param.getMonthOfcc1());

                Cell cell32 = row.createCell(31);
                cell32.setCellStyle(attendanceStyle);
                cell32.setCellValue(param.getMonthOfkg1());

                Cell cell33 = row.createCell(32);
                cell33.setCellStyle(attendanceStyle);
                cell33.setCellValue(param.getMonthOfqt1());

                Cell cell34 = row.createCell(33);
                cell34.setCellStyle(attendanceStyle);
                cell34.setCellValue(param.getMonthOfWorkday2());

                Cell cell35 = row.createCell(34);
                cell35.setCellStyle(attendanceStyle);
                cell35.setCellValue(param.getMonthOfOvertime2());

                Cell cell36 = row.createCell(35);
                cell36.setCellStyle(attendanceStyle);
                cell36.setCellValue(param.getMonthOfnxj2());

                Cell cell37 = row.createCell(36);
                cell37.setCellStyle(attendanceStyle);
                cell37.setCellValue(param.getMonthOftqj2());

                Cell cell38 = row.createCell(37);
                cell38.setCellStyle(attendanceStyle);
                cell38.setCellValue(param.getMonthOfshij2());

                Cell cell39 = row.createCell(38);
                cell39.setCellStyle(attendanceStyle);
                cell39.setCellValue(param.getMonthOfsyj2());

                Cell cell40 = row.createCell(39);
                cell40.setCellStyle(attendanceStyle);
                cell40.setCellValue(param.getMonthOfjs2());

                Cell cell41 = row.createCell(40);
                cell41.setCellStyle(attendanceStyle);
                cell41.setCellValue(param.getMonthOfbj2());

                Cell cell42 = row.createCell(41);
                cell42.setCellStyle(attendanceStyle);
                cell42.setCellValue(param.getMonthOfhj2());

                Cell cell43 = row.createCell(42);
                cell43.setCellStyle(attendanceStyle);
                cell43.setCellValue(param.getMonthOfsangj2());

                Cell cell44 = row.createCell(43);
                cell44.setCellStyle(attendanceStyle);
                cell44.setCellValue(param.getMonthOflgxx2());

                Cell cell45 = row.createCell(44);
                cell45.setCellStyle(attendanceStyle);
                cell45.setCellValue(param.getMonthOfcc2());

                Cell cell46 = row.createCell(45);
                cell46.setCellStyle(attendanceStyle);
                cell46.setCellValue(param.getMonthOfkg2());

                Cell cell47 = row.createCell(46);
                cell47.setCellStyle(attendanceStyle);
                cell47.setCellValue(param.getMonthOfqt2());

                Cell cell48 = row.createCell(47);
                cell48.setCellStyle(attendanceStyle);
                cell48.setCellValue(param.getMonthOfWorkday3());

                Cell cell49 = row.createCell(48);
                cell49.setCellStyle(attendanceStyle);
                cell49.setCellValue(param.getMonthOfOvertime3());

                Cell cell50 = row.createCell(49);
                cell50.setCellStyle(attendanceStyle);
                cell50.setCellValue(param.getMonthOfnxj3());

                Cell cell51 = row.createCell(50);
                cell51.setCellStyle(attendanceStyle);
                cell51.setCellValue(param.getMonthOftqj3());

                Cell cell52 = row.createCell(51);
                cell52.setCellStyle(attendanceStyle);
                cell52.setCellValue(param.getMonthOfshij3());

                Cell cell53 = row.createCell(52);
                cell53.setCellStyle(attendanceStyle);
                cell53.setCellValue(param.getMonthOfsyj3());

                Cell cell54 = row.createCell(53);
                cell54.setCellStyle(attendanceStyle);
                cell54.setCellValue(param.getMonthOfjs3());

                Cell cell55 = row.createCell(54);
                cell55.setCellStyle(attendanceStyle);
                cell55.setCellValue(param.getMonthOfbj3());

                Cell cell56 = row.createCell(55);
                cell56.setCellStyle(attendanceStyle);
                cell56.setCellValue(param.getMonthOfhj3());

                Cell cell57 = row.createCell(56);
                cell57.setCellStyle(attendanceStyle);
                cell57.setCellValue(param.getMonthOfsangj3());

                Cell cell58 = row.createCell(57);
                cell58.setCellStyle(attendanceStyle);
                cell58.setCellValue(param.getMonthOflgxx3());

                Cell cell59 = row.createCell(58);
                cell59.setCellStyle(attendanceStyle);
                cell59.setCellValue(param.getMonthOfcc3());

                Cell cell60 = row.createCell(59);
                cell60.setCellStyle(attendanceStyle);
                cell60.setCellValue(param.getMonthOfkg3());

                Cell cell61 = row.createCell(60);
                cell61.setCellStyle(attendanceStyle);
                cell61.setCellValue(param.getMonthOfqt3());

                Cell cell62 = row.createCell(61);
                cell62.setCellStyle(attendanceStyle);
                cell62.setCellValue(param.getMonthOfWorkday4());

                Cell cell63 = row.createCell(62);
                cell63.setCellStyle(attendanceStyle);
                cell63.setCellValue(param.getMonthOfOvertime4());

                Cell cell64 = row.createCell(63);
                cell64.setCellStyle(attendanceStyle);
                cell64.setCellValue(param.getMonthOfnxj4());

                Cell cell65 = row.createCell(64);
                cell65.setCellStyle(attendanceStyle);
                cell65.setCellValue(param.getMonthOftqj4());

                Cell cell66 = row.createCell(65);
                cell66.setCellStyle(attendanceStyle);
                cell66.setCellValue(param.getMonthOfshij4());

                Cell cell67 = row.createCell(66);
                cell67.setCellStyle(attendanceStyle);
                cell67.setCellValue(param.getMonthOfsyj4());

                Cell cell68 = row.createCell(67);
                cell68.setCellStyle(attendanceStyle);
                cell68.setCellValue(param.getMonthOfjs4());

                Cell cell69 = row.createCell(68);
                cell69.setCellStyle(attendanceStyle);
                cell69.setCellValue(param.getMonthOfbj4());

                Cell cell70 = row.createCell(69);
                cell70.setCellStyle(attendanceStyle);
                cell70.setCellValue(param.getMonthOfhj4());

                Cell cell71 = row.createCell(70);
                cell71.setCellStyle(attendanceStyle);
                cell71.setCellValue(param.getMonthOfsangj4());

                Cell cell72 = row.createCell(71);
                cell72.setCellStyle(attendanceStyle);
                cell72.setCellValue(param.getMonthOflgxx4());

                Cell cell73 = row.createCell(72);
                cell73.setCellStyle(attendanceStyle);
                cell73.setCellValue(param.getMonthOfcc4());

                Cell cell74 = row.createCell(73);
                cell74.setCellStyle(attendanceStyle);
                cell74.setCellValue(param.getMonthOfkg4());

                Cell cell75 = row.createCell(74);
                cell75.setCellStyle(attendanceStyle);
                cell75.setCellValue(param.getMonthOfqt4());

                Cell cell76 = row.createCell(75);
                cell76.setCellStyle(attendanceStyle);
                cell76.setCellValue(param.getMonthOfWorkday5());

                Cell cell77 = row.createCell(76);
                cell77.setCellStyle(attendanceStyle);
                cell77.setCellValue(param.getMonthOfOvertime5());

                Cell cell78 = row.createCell(77);
                cell78.setCellStyle(attendanceStyle);
                cell78.setCellValue(param.getMonthOfnxj5());

                Cell cell79 = row.createCell(78);
                cell79.setCellStyle(attendanceStyle);
                cell79.setCellValue(param.getMonthOftqj5());

                Cell cell80 = row.createCell(79);
                cell80.setCellStyle(attendanceStyle);
                cell80.setCellValue(param.getMonthOfshij5());

                Cell cell81 = row.createCell(80);
                cell81.setCellStyle(attendanceStyle);
                cell81.setCellValue(param.getMonthOfsyj5());

                Cell cell82 = row.createCell(81);
                cell82.setCellStyle(attendanceStyle);
                cell82.setCellValue(param.getMonthOfjs5());

                Cell cell83 = row.createCell(82);
                cell83.setCellStyle(attendanceStyle);
                cell83.setCellValue(param.getMonthOfbj5());

                Cell cell84 = row.createCell(83);
                cell84.setCellStyle(attendanceStyle);
                cell84.setCellValue(param.getMonthOfhj5());

                Cell cell85 = row.createCell(84);
                cell85.setCellStyle(attendanceStyle);
                cell85.setCellValue(param.getMonthOfsangj5());

                Cell cell86 = row.createCell(85);
                cell86.setCellStyle(attendanceStyle);
                cell86.setCellValue(param.getMonthOflgxx5());

                Cell cell87 = row.createCell(86);
                cell87.setCellStyle(attendanceStyle);
                cell87.setCellValue(param.getMonthOfcc5());

                Cell cell88 = row.createCell(87);
                cell88.setCellStyle(attendanceStyle);
                cell88.setCellValue(param.getMonthOfkg5());

                Cell cell89 = row.createCell(88);
                cell89.setCellStyle(attendanceStyle);
                cell89.setCellValue(param.getMonthOfqt5());

                Cell cell90 = row.createCell(89);
                cell90.setCellStyle(attendanceStyle);
                cell90.setCellValue(param.getMonthOfWorkday6());

                Cell cell91 = row.createCell(90);
                cell91.setCellStyle(attendanceStyle);
                cell91.setCellValue(param.getMonthOfOvertime6());

                Cell cell92 = row.createCell(91);
                cell92.setCellStyle(attendanceStyle);
                cell92.setCellValue(param.getMonthOfnxj6());

                Cell cell93 = row.createCell(92);
                cell93.setCellStyle(attendanceStyle);
                cell93.setCellValue(param.getMonthOftqj6());

                Cell cell94 = row.createCell(93);
                cell94.setCellStyle(attendanceStyle);
                cell94.setCellValue(param.getMonthOfshij6());

                Cell cell95 = row.createCell(94);
                cell95.setCellStyle(attendanceStyle);
                cell95.setCellValue(param.getMonthOfsyj6());

                Cell cell96 = row.createCell(95);
                cell96.setCellStyle(attendanceStyle);
                cell96.setCellValue(param.getMonthOfjs6());

                Cell cell97 = row.createCell(96);
                cell97.setCellStyle(attendanceStyle);
                cell97.setCellValue(param.getMonthOfbj6());

                Cell cell98 = row.createCell(97);
                cell98.setCellStyle(attendanceStyle);
                cell98.setCellValue(param.getMonthOfhj6());

                Cell cell99 = row.createCell(98);
                cell99.setCellStyle(attendanceStyle);
                cell99.setCellValue(param.getMonthOfsangj6());

                Cell cell100 = row.createCell(99);
                cell100.setCellStyle(attendanceStyle);
                cell100.setCellValue(param.getMonthOflgxx6());

                Cell cell101 = row.createCell(100);
                cell101.setCellStyle(attendanceStyle);
                cell101.setCellValue(param.getMonthOfcc6());

                Cell cell102 = row.createCell(101);
                cell102.setCellStyle(attendanceStyle);
                cell102.setCellValue(param.getMonthOfkg6());

                Cell cell103 = row.createCell(102);
                cell103.setCellStyle(attendanceStyle);
                cell103.setCellValue(param.getMonthOfqt6());

                Cell cell104 = row.createCell(103);
                cell104.setCellStyle(attendanceStyle);
                cell104.setCellValue(param.getMonthOfWorkday7());

                Cell cell105 = row.createCell(104);
                cell105.setCellStyle(attendanceStyle);
                cell105.setCellValue(param.getMonthOfOvertime7());

                Cell cell106 = row.createCell(105);
                cell106.setCellStyle(attendanceStyle);
                cell106.setCellValue(param.getMonthOfnxj7());

                Cell cell107 = row.createCell(106);
                cell107.setCellStyle(attendanceStyle);
                cell107.setCellValue(param.getMonthOftqj7());

                Cell cell108 = row.createCell(107);
                cell108.setCellStyle(attendanceStyle);
                cell108.setCellValue(param.getMonthOfshij7());

                Cell cell109 = row.createCell(108);
                cell109.setCellStyle(attendanceStyle);
                cell109.setCellValue(param.getMonthOfsyj7());

                Cell cell110 = row.createCell(109);
                cell110.setCellStyle(attendanceStyle);
                cell110.setCellValue(param.getMonthOfjs7());

                Cell cell111 = row.createCell(110);
                cell111.setCellStyle(attendanceStyle);
                cell111.setCellValue(param.getMonthOfbj7());

                Cell cell112 = row.createCell(111);
                cell112.setCellStyle(attendanceStyle);
                cell112.setCellValue(param.getMonthOfhj7());

                Cell cell113 = row.createCell(112);
                cell113.setCellStyle(attendanceStyle);
                cell113.setCellValue(param.getMonthOfsangj7());

                Cell cell114 = row.createCell(113);
                cell114.setCellStyle(attendanceStyle);
                cell114.setCellValue(param.getMonthOflgxx7());

                Cell cell115 = row.createCell(114);
                cell115.setCellStyle(attendanceStyle);
                cell115.setCellValue(param.getMonthOfcc7());

                Cell cell116 = row.createCell(115);
                cell116.setCellStyle(attendanceStyle);
                cell116.setCellValue(param.getMonthOfkg7());

                Cell cell117 = row.createCell(116);
                cell117.setCellStyle(attendanceStyle);
                cell117.setCellValue(param.getMonthOfqt7());

                Cell cell118 = row.createCell(117);
                cell118.setCellStyle(attendanceStyle);
                cell118.setCellValue(param.getMonthOfWorkday8());

                Cell cell119 = row.createCell(118);
                cell119.setCellStyle(attendanceStyle);
                cell119.setCellValue(param.getMonthOfOvertime8());

                Cell cell120 = row.createCell(119);
                cell120.setCellStyle(attendanceStyle);
                cell120.setCellValue(param.getMonthOfnxj8());

                Cell cell121 = row.createCell(120);
                cell121.setCellStyle(attendanceStyle);
                cell121.setCellValue(param.getMonthOftqj8());

                Cell cell122 = row.createCell(121);
                cell122.setCellStyle(attendanceStyle);
                cell122.setCellValue(param.getMonthOfshij8());

                Cell cell123 = row.createCell(122);
                cell123.setCellStyle(attendanceStyle);
                cell123.setCellValue(param.getMonthOfsyj8());

                Cell cell124 = row.createCell(123);
                cell124.setCellStyle(attendanceStyle);
                cell124.setCellValue(param.getMonthOfjs8());

                Cell cell125 = row.createCell(124);
                cell125.setCellStyle(attendanceStyle);
                cell125.setCellValue(param.getMonthOfbj8());

                Cell cell126 = row.createCell(125);
                cell126.setCellStyle(attendanceStyle);
                cell126.setCellValue(param.getMonthOfhj8());

                Cell cell127 = row.createCell(126);
                cell127.setCellStyle(attendanceStyle);
                cell127.setCellValue(param.getMonthOfsangj8());

                Cell cell128 = row.createCell(127);
                cell128.setCellStyle(attendanceStyle);
                cell128.setCellValue(param.getMonthOflgxx8());

                Cell cell129 = row.createCell(128);
                cell129.setCellStyle(attendanceStyle);
                cell129.setCellValue(param.getMonthOfcc8());

                Cell cell130 = row.createCell(129);
                cell130.setCellStyle(attendanceStyle);
                cell130.setCellValue(param.getMonthOfkg8());

                Cell cell131 = row.createCell(130);
                cell131.setCellStyle(attendanceStyle);
                cell131.setCellValue(param.getMonthOfqt8());

                Cell cell132 = row.createCell(131);
                cell132.setCellStyle(attendanceStyle);
                cell132.setCellValue(param.getMonthOfWorkday9());

                Cell cell133 = row.createCell(132);
                cell133.setCellStyle(attendanceStyle);
                cell133.setCellValue(param.getMonthOfOvertime9());

                Cell cell134 = row.createCell(133);
                cell134.setCellStyle(attendanceStyle);
                cell134.setCellValue(param.getMonthOfnxj9());

                Cell cell135 = row.createCell(134);
                cell135.setCellStyle(attendanceStyle);
                cell135.setCellValue(param.getMonthOftqj9());

                Cell cell136 = row.createCell(135);
                cell136.setCellStyle(attendanceStyle);
                cell136.setCellValue(param.getMonthOfshij9());

                Cell cell137 = row.createCell(136);
                cell137.setCellStyle(attendanceStyle);
                cell137.setCellValue(param.getMonthOfshij9());

                Cell cell138 = row.createCell(137);
                cell138.setCellStyle(attendanceStyle);
                cell138.setCellValue(param.getMonthOfjs9());

                Cell cell139 = row.createCell(138);
                cell139.setCellStyle(attendanceStyle);
                cell139.setCellValue(param.getMonthOfbj9());

                Cell cell140 = row.createCell(139);
                cell140.setCellStyle(attendanceStyle);
                cell140.setCellValue(param.getMonthOfhj9());

                Cell cell141 = row.createCell(140);
                cell141.setCellStyle(attendanceStyle);
                cell141.setCellValue(param.getMonthOfsangj9());

                Cell cell142 = row.createCell(141);
                cell142.setCellStyle(attendanceStyle);
                cell142.setCellValue(param.getMonthOflgxx9());

                Cell cell143 = row.createCell(142);
                cell143.setCellStyle(attendanceStyle);
                cell143.setCellValue(param.getMonthOflgxx9());

                Cell cell144 = row.createCell(143);
                cell144.setCellStyle(attendanceStyle);
                cell144.setCellValue(param.getMonthOfkg9());

                Cell cell145 = row.createCell(144);
                cell145.setCellStyle(attendanceStyle);
                cell145.setCellValue(param.getMonthOfqt9());

                Cell cell146 = row.createCell(145);
                cell146.setCellStyle(attendanceStyle);
                cell146.setCellValue(param.getMonthOfWorkday10());

                Cell cell147 = row.createCell(146);
                cell147.setCellStyle(attendanceStyle);
                cell147.setCellValue(param.getMonthOfOvertime10());

                Cell cell148 = row.createCell(147);
                cell148.setCellStyle(attendanceStyle);
                cell148.setCellValue(param.getMonthOfnxj10());

                Cell cell149 = row.createCell(148);
                cell149.setCellStyle(attendanceStyle);
                cell149.setCellValue(param.getMonthOftqj10());

                Cell cell150 = row.createCell(149);
                cell150.setCellStyle(attendanceStyle);
                cell150.setCellValue(param.getMonthOfshij10());

                Cell cell151 = row.createCell(150);
                cell151.setCellStyle(attendanceStyle);
                cell151.setCellValue(param.getMonthOfsyj10());

                Cell cell152 = row.createCell(151);
                cell152.setCellStyle(attendanceStyle);
                cell152.setCellValue(param.getMonthOfjs10());

                Cell cell153 = row.createCell(152);
                cell153.setCellStyle(attendanceStyle);
                cell153.setCellValue(param.getMonthOfbj10());

                Cell cell154 = row.createCell(153);
                cell154.setCellStyle(attendanceStyle);
                cell154.setCellValue(param.getMonthOfhj10());

                Cell cell155 = row.createCell(154);
                cell155.setCellStyle(attendanceStyle);
                cell155.setCellValue(param.getMonthOfsangj10());

                Cell cell156 = row.createCell(155);
                cell156.setCellStyle(attendanceStyle);
                cell156.setCellValue(param.getMonthOflgxx10());

                Cell cell157 = row.createCell(156);
                cell157.setCellStyle(attendanceStyle);
                cell157.setCellValue(param.getMonthOfcc10());

                Cell cell158 = row.createCell(157);
                cell158.setCellStyle(attendanceStyle);
                cell158.setCellValue(param.getMonthOfkg10());

                Cell cell159 = row.createCell(158);
                cell159.setCellStyle(attendanceStyle);
                cell159.setCellValue(param.getMonthOfqt10());

                Cell cell160 = row.createCell(159);
                cell160.setCellStyle(attendanceStyle);
                cell160.setCellValue(param.getMonthOfWorkday11());

                Cell cell161 = row.createCell(160);
                cell161.setCellStyle(attendanceStyle);
                cell161.setCellValue(param.getMonthOfOvertime11());

                Cell cell162 = row.createCell(161);
                cell162.setCellStyle(attendanceStyle);
                cell162.setCellValue(param.getMonthOfnxj11());

                Cell cell163 = row.createCell(162);
                cell163.setCellStyle(attendanceStyle);
                cell163.setCellValue(param.getMonthOftqj11());

                Cell cell164 = row.createCell(163);
                cell164.setCellStyle(attendanceStyle);
                cell164.setCellValue(param.getMonthOfshij11());

                Cell cell165 = row.createCell(164);
                cell165.setCellStyle(attendanceStyle);
                cell165.setCellValue(param.getMonthOfsyj11());

                Cell cell166 = row.createCell(165);
                cell166.setCellStyle(attendanceStyle);
                cell166.setCellValue(param.getMonthOfjs11());

                Cell cell167 = row.createCell(166);
                cell167.setCellStyle(attendanceStyle);
                cell167.setCellValue(param.getMonthOfbj11());

                Cell cell168 = row.createCell(167);
                cell168.setCellStyle(attendanceStyle);
                cell168.setCellValue(param.getMonthOfhj11());

                Cell cell169 = row.createCell(168);
                cell169.setCellStyle(attendanceStyle);
                cell169.setCellValue(param.getMonthOfsangj11());

                Cell cell170 = row.createCell(169);
                cell170.setCellStyle(attendanceStyle);
                cell170.setCellValue(param.getMonthOflgxx11());

                Cell cell171 = row.createCell(170);
                cell171.setCellStyle(attendanceStyle);
                cell171.setCellValue(param.getMonthOfcc11());

                Cell cell172 = row.createCell(171);
                cell172.setCellStyle(attendanceStyle);
                cell172.setCellValue(param.getMonthOfkg11());

                Cell cell173 = row.createCell(172);
                cell173.setCellStyle(attendanceStyle);
                cell173.setCellValue(param.getMonthOfqt11());

                Cell cell174 = row.createCell(173);
                cell174.setCellStyle(attendanceStyle);
                cell174.setCellValue(param.getMonthOfWorkday12());

                Cell cell175 = row.createCell(174);
                cell175.setCellStyle(attendanceStyle);
                cell175.setCellValue(param.getMonthOfOvertime12());

                Cell cell176 = row.createCell(175);
                cell176.setCellStyle(attendanceStyle);
                cell176.setCellValue(param.getMonthOfnxj12());

                Cell cell177 = row.createCell(176);
                cell177.setCellStyle(attendanceStyle);
                cell177.setCellValue(param.getMonthOftqj12());

                Cell cell178 = row.createCell(177);
                cell178.setCellStyle(attendanceStyle);
                cell178.setCellValue(param.getMonthOfshij12());

                Cell cell179 = row.createCell(178);
                cell179.setCellStyle(attendanceStyle);
                cell179.setCellValue(param.getMonthOfsyj12());

                Cell cell180 = row.createCell(179);
                cell180.setCellStyle(attendanceStyle);
                cell180.setCellValue(param.getMonthOfjs12());

                Cell cell181 = row.createCell(180);
                cell181.setCellStyle(attendanceStyle);
                cell181.setCellValue(param.getMonthOfbj12());

                Cell cell182 = row.createCell(181);
                cell182.setCellStyle(attendanceStyle);
                cell182.setCellValue(param.getMonthOfhj12());

                Cell cell183 = row.createCell(182);
                cell183.setCellStyle(attendanceStyle);
                cell183.setCellValue(param.getMonthOfsangj12());

                Cell cell184 = row.createCell(183);
                cell184.setCellStyle(attendanceStyle);
                cell184.setCellValue(param.getMonthOflgxx12());

                Cell cell185 = row.createCell(184);
                cell185.setCellStyle(attendanceStyle);
                cell185.setCellValue(param.getMonthOfcc12());

                Cell cell186 = row.createCell(185);
                cell186.setCellStyle(attendanceStyle);
                cell186.setCellValue(param.getMonthOfkg12());

                Cell cell187 = row.createCell(186);
                cell187.setCellStyle(attendanceStyle);
                cell187.setCellValue(param.getMonthOfqt12());

                Cell cell188 = row.createCell(187);
                cell188.setCellStyle(attendanceStyle);
                cell188.setCellValue(param.getCreater());
                index++;
            }
            wb.write(out);
            wb.close();
        } catch (Exception e) {
            LOG.error("未知excel错误！");
            e.printStackTrace();
        }
    }
}
