package com.cost168.costaudit.controller.work;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.sys.SysRole;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.WorkCalendar;
import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.work.WorkPerson;
import com.cost168.costaudit.pojo.work.WorkRegister;
import com.cost168.costaudit.pojo.work.vo.WorkRegisterVo;
import com.cost168.costaudit.service.sys.SysRoleService;
import com.cost168.costaudit.service.sys.SysUserRoleService;
import com.cost168.costaudit.service.work.WorkCalendarService;
import com.cost168.costaudit.service.work.WorkEnterpriseService;
import com.cost168.costaudit.service.work.WorkPersonService;
import com.cost168.costaudit.service.work.WorkRegisterService;
import com.cost168.costaudit.shiro.shiroUtil;

/**
 * ClassName: WorkRegisterController
 *
 * @author lixiang
 * @Description: TODO
 */
@Controller
@RequestMapping("/workRegister")
public class WorkRegisterController {


    @Autowired
    private WorkRegisterService workRegisterService;
    @Autowired
    private WorkPersonService workPersonService;
    @Autowired
    private WorkEnterpriseService workEnterpriseService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
	@Autowired
	private WorkCalendarService workCalendarService;

    @RequestMapping("/tolist")
    public String toRegister(HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String year = workRegisterService.selectYearList();
            String month = workRegisterService.selectMonthList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            String regYear = currentDate.substring(0, 4);
            String regMonth1 = currentDate.substring(5, 7);
            int regMonth=Integer.parseInt(regMonth1);
            map.put("regYear", regYear);
            map.put("regMonth", regMonth);
            List<WorkRegister> wrList = workRegisterService.selectByDay(map);
            int days = OrderUtil.getYearMonthDays(Integer.parseInt(regYear), regMonth);
            String temDate="";
            List<String> excepdays=new ArrayList<String>();
			WorkCalendar wc=workCalendarService.selectByPrimaryKey(regYear);
			if(null!=wc && null!=wc.getExcepdays()){
				excepdays=Arrays.asList(wc.getExcepdays().replaceAll(" ","").split(","));
			}
            if (null == wrList || wrList.size() <= 0) {
                //每个人的账号下插入一个月的考勤记录
                List<WorkPerson> wpList = workPersonService.selectListByMap(null);
                if (null != wpList && wpList.size() > 0) {
                    WorkRegister register = null;
                    for (WorkPerson wp : wpList) {
                    	if("无效".equals(wp.getEffectiveFlag())){
                    		continue;
                    	}
                    	Date actualStartTime= wp.getActualStartTime();
        				if(actualStartTime.getTime()<=new Date().getTime()){
	                        for (int i = 1; i <= days; i++) {
	                            register = new WorkRegister();
	                            String id = UUID.randomUUID().toString().replace("-", "");
	                            register.setId(id);
	                            register.setPersonId(wp.getAccount());
	                            register.setEnterpriseId(wp.getEnterpriseId());
	                            register.setRegYearMonth(regYear + regMonth);
	                            register.setRegYear(regYear);
	                            register.setRegMonth(Integer.toString(regMonth));
	                            register.setRegDay(i + "");
	        					if(i<10){
	        						temDate=regYear+"-"+currentDate.substring(5,7)+"-0"+i+"";
	        					}else{
	        						temDate=regYear+"-"+currentDate.substring(5,7)+"-"+i+"";
	        					}
	        					if(excepdays.contains(temDate)){
	        						register.setMorning("休息");
	        						register.setAfternoon("休息");
	        					}
	                            register.setCreateTime(new Date());
	                            String registerTimeStr = regYear + "-" + currentDate.substring(5, 7) + "-" + i + "";
	                            register.setRegisterTime(sdf.parse(registerTimeStr));
	                            workRegisterService.insertSelective(register);
	                        }
        				}
                    }
                }
            }
            request.setAttribute("year", year);
            request.setAttribute("month", month);
            request.setAttribute("currentYear", regYear);
            request.setAttribute("currentMonth", regMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/register/list";
    }

    @ResponseBody
    @RequestMapping("/list")
    public EUDataGridResult list(HttpServletRequest request, WorkRegisterVo workRegisterVo, int page, int rows) {
    	EUDataGridResult result = new EUDataGridResult();
    	try {
	        Map<String, Object> selectMap = JsonUtils.object2Map(workRegisterVo);
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.putAll(selectMap);
	        SysUser currentUser = shiroUtil.getInstance().currentUser();
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (null != startTime && !"".equals(startTime)) {
                map.put("startTime", startTime + " 00:00:00");
            }
            if (null != endTime && !"".equals(endTime)) {
                map.put("endTime", endTime + " 24:00:00");
            }
            map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    String roleId=sysUserRoleService.selectRoleByUserId(currentUser.getId());
		    SysRole role=sysRoleService.selectByPrimaryKey(roleId);
		    if(null!=role && "驻场人员".equals(role.getName())){
		    	 map.put("account", currentUser.getAccount());
		    }
            List<WorkRegisterVo> list = workRegisterService.selectListByMap(map);
            Global.REG_EXPORT_LIST = list;
            int total = workRegisterService.selectCountByMap(map);
            result.setRows(list);
            result.setTotal(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toRegisterDialog")
    public String toRegisterDialog(HttpServletRequest request) {
        String personId = request.getParameter("personId");
        String minDate = request.getParameter("minDate");
        String year=minDate.substring(0, 4);
        String regMonth = minDate.substring(minDate.indexOf("-")+1, minDate.lastIndexOf("-"));
        WorkPerson workPerson = workPersonService.selectByPrimaryKey(personId);
        if (null != workPerson) {
            WorkEnterprise workEnterprise = workEnterpriseService.selectByPrimaryKey(workPerson.getEnterpriseId());
            if (null != workEnterprise) {
                workPerson.setEnterpriseId(workEnterprise.getId());
                workPerson.setEnterpriseName(workEnterprise.getName());
            }
            //日历高亮
            //String regDays="['....-..-01','....-..-02','....-..-15']";
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("personId", workPerson.getAccount());
            map.put("regYearMonth", year+regMonth);
            String regDays= workRegisterService.selectDayStr(map);
            String arrDay[]=regDays.split(",");
            StringBuffer sb=new StringBuffer();
            sb.append("[");
            for(String s:arrDay){
            	if(s.length()==1){
            		s="0"+s;
            	}
            	sb.append("'....-..-"+s+"',");
            }
            sb=sb.deleteCharAt(sb.length()-1);
            sb.append("]");
            request.setAttribute("regDays", sb.toString());
            request.setAttribute("obj", workPerson);
        }
        return "register/registerDialog";
    }

    @RequestMapping("/toRegisterBatchDialog")
    public String toRegisterBatchDialog(HttpServletRequest request) {
        String personIds = request.getParameter("personIds");
        String arrIds[] = personIds.split(",");
        List<WorkPerson> wpList = new ArrayList<WorkPerson>();
        String actualStartTimeStr="";
        String actualEndTimeStr="";
        
        WorkPerson wp = null;
        for (String id : arrIds) {
            wp = workPersonService.selectByPrimaryKey(id);
            if(null!=wp){
            	wpList.add(wp);
            	if(null!=wp.getActualStartTimeStr()){
            		actualStartTimeStr+=wp.getActualStartTimeStr()+",";
            	}
            	if(null!=wp.getActualEndTimeStr()){
            		actualEndTimeStr+=wp.getActualEndTimeStr()+",";
            	}
            }
        }
        request.setAttribute("objList", wpList);
        request.setAttribute("actualStartTimeStr", actualStartTimeStr);
        request.setAttribute("actualEndTimeStr", actualEndTimeStr);
        return "register/registerBatchDialog";
    }

    @ResponseBody
    @RequestMapping("/save")
    public GlobalResult save(HttpServletRequest request, WorkRegister workRegister) {
        GlobalResult result = new GlobalResult();
        SysUser currentUser = shiroUtil.getInstance().currentUser();
        String remarkQt=request.getParameter("remarkQt");
        if(null!=remarkQt&&!"".equals(remarkQt)&&!"其他".equals(remarkQt)){
        	workRegister.setRemark(remarkQt);
        }
        try {
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String registerTimeStrStart = request.getParameter("registerTimeStrStart");
            String registerTimeStrEnd = request.getParameter("registerTimeStrEnd");
			String startDay=registerTimeStrStart.substring(8,10);
			String endDay=registerTimeStrEnd.substring(8,10);
			int s=Integer.parseInt(startDay);
			int e=Integer.parseInt(endDay);
			String regYear = registerTimeStrStart.substring(0, 4);
			String regMonth = registerTimeStrStart.substring(5, 7);
			regMonth=Integer.parseInt(regMonth)+"";
			String yearMonth = regYear + regMonth;
            String morning = workRegister.getMorning();
            String afternoon = workRegister.getAfternoon();
            float workingDay = 0;
            if ("上班".equals(morning) && "上班".equals(afternoon)) {
                workingDay = 1;
            } else if ("上班".equals(morning) && !"上班".equals(afternoon)) {
                workingDay = 0.5f;
            } else if (!"上班".equals(morning) && "上班".equals(afternoon)) {
                workingDay = 0.5f;
            } else {
                workingDay = 0;
            }
			//long days=OrderUtil.getDaySub(registerTimeStrStart,registerTimeStrEnd);
            Map<String,Object> map=new HashMap<String, Object>();
			WorkPerson wp= workPersonService.selectByAccount(workRegister.getPersonId());
			//加班才能改休息日
			List<String> excepdays=new ArrayList<String>();
			WorkCalendar wc=workCalendarService.selectByPrimaryKey(regYear);
			if(null!=wc && null!=wc.getExcepdays()){
				excepdays=Arrays.asList(wc.getExcepdays().replaceAll(" ","").split(","));
			}
			if(e>=s){
				for(int i=s;i<=e;i++){
					map.clear();
					map.put("personId", workRegister.getPersonId());
					map.put("regYear", regYear);
					map.put("regMonth",regMonth);
					map.put("regDay", i);
					List<WorkRegister> registerList = workRegisterService.selectByDay(map);
					WorkRegister register=null; 
					//加班才能改休息日
					String temDate="";
					if(i<10){
						temDate=regYear+"-"+registerTimeStrStart.substring(5,7)+"-0"+i+"";
					}else{
						temDate=regYear+"-"+registerTimeStrStart.substring(5,7)+"-"+i+"";
					}
					if(null!=registerList&& registerList.size()>0){
						 register=registerList.get(0);
						 if(null==register.getLeaveprocess()||"".equals(register.getLeaveprocess())){
	    					if(excepdays.contains(temDate)){
	    						if("加班".equals(workRegister.getMorning()) ){
	    							register.setMorning("加班");
	    						}
	    						if("加班".equals(workRegister.getAfternoon())){
	    							register.setAfternoon("加班");
	    						}
	    						if("休息".equals(workRegister.getMorning()) ){
	    							register.setMorning("休息");
	    						}
	    						if("休息".equals(workRegister.getAfternoon())){
	    							register.setAfternoon("休息");
	    						}
	    					}else{
	    						 	if("年休假".equals(workRegister.getMorning())){
	    							 	if(!"年休假".equals(register.getMorning())){
	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())+0.5)+"");
	    	    						}
	    	    					}else{
	    	    						if("年休假".equals(register.getMorning())){
	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())-0.5)+"");
	    	    						}
	    	    					}
	    	    					if("年休假".equals(workRegister.getAfternoon())){
	    	    						if(!"年休假".equals(register.getAfternoon())){
	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())+0.5)+"");
	    	    						}
	    	    					}else{
	    	    						if("年休假".equals(register.getAfternoon())){
	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())-0.5)+"");
	    	    						}
	    	    					}
			    					 if(!"加班".equals(workRegister.getMorning())){
		    							 register.setMorning(workRegister.getMorning());
		    						 }
		    						 if(!"加班".equals(workRegister.getAfternoon())){
		    							 register.setAfternoon(workRegister.getAfternoon());
		    						 }
		    				}
	    					register.setCreater(currentUser.getName());
	    					register.setRemark(workRegister.getRemark());
	    					workRegisterService.updateByPrimaryKeySelective(register);
	    					workPersonService.updateByPrimaryKeySelective(wp);
						 }
					}else{
		                register = new WorkRegister();
		                String id = UUID.randomUUID().toString().replace("-", "");
		                register.setId(id);
		                register.setPersonId(workRegister.getPersonId());
		                register.setEnterpriseId(workRegister.getEnterpriseId());
		                register.setRegYearMonth(yearMonth);
		                register.setRegYear(regYear);
		                register.setRegMonth(regMonth);
		                register.setRegDay(i+"");
		                register.setWorkingDay(workingDay);
		                register.setCreateTime(new Date());
		                register.setCreater(currentUser.getName());
		                register.setRemark(workRegister.getRemark());
		                String str=regYear+"-"+regMonth+"-"+i+"";
		                register.setRegisterTime(sdf.parse(str));
		                if(excepdays.contains(temDate)){
							if("加班".equals(workRegister.getMorning()) ){
								register.setMorning("加班");
							}
							if("加班".equals(workRegister.getAfternoon())){
								register.setAfternoon("加班");
							}
							if("休息".equals(workRegister.getMorning()) ){
								register.setMorning("休息");
							}
							if("休息".equals(workRegister.getAfternoon())){
								register.setAfternoon("休息");
							}
						}else{
							 if(!"加班".equals(workRegister.getMorning())){
								 register.setMorning(workRegister.getMorning());
							 }
							 if(!"加班".equals(workRegister.getAfternoon())){
								 register.setAfternoon(workRegister.getAfternoon());
							 }
							 if("年休假".equals(workRegister.getMorning())){
								wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())+0.5)+"");
							 }
							 if("年休假".equals(workRegister.getAfternoon())){
								wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())+0.5)+"");
							 }
	    				}
		                workRegisterService.insertSelective(register);
						workPersonService.updateByPrimaryKeySelective(wp);
		            }
					
				}
			}else{
				
			}
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/batchSave")
    public GlobalResult batchSave(HttpServletRequest request, WorkRegister workRegister) {
        GlobalResult result = new GlobalResult();
        SysUser currentUser = shiroUtil.getInstance().currentUser();
        String personIds = workRegister.getPersonId();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String remarkQt=request.getParameter("remarkQt");
        if(null!=remarkQt&&!"".equals(remarkQt)&&!"其他".equals(remarkQt)){
        	workRegister.setRemark(remarkQt);
        }
        try {
        	String registerTimeStrStart = request.getParameter("registerTimeStrStart");
            String registerTimeStrEnd = request.getParameter("registerTimeStrEnd");
 			String startDay=registerTimeStrStart.substring(8,10);
 			String endDay=registerTimeStrEnd.substring(8,10);
 			int s=Integer.parseInt(startDay);
 			int e=Integer.parseInt(endDay);
 			String regYear = registerTimeStrStart.substring(0, 4);
 			String regMonth = registerTimeStrStart.substring(5, 7);
 			regMonth=Integer.parseInt(regMonth)+"";
 			String yearMonth = regYear + regMonth;
            String morning = workRegister.getMorning();
            String afternoon = workRegister.getAfternoon();
            float workingDay = 0;
             if ("上班".equals(morning) && "上班".equals(afternoon)) {
                 workingDay = 1;
             } else if ("上班".equals(morning) && !"上班".equals(afternoon)) {
                 workingDay = 0.5f;
             } else if (!"上班".equals(morning) && "上班".equals(afternoon)) {
                 workingDay = 0.5f;
             } else {
                 workingDay = 0;
             }
            String arrIds[] = personIds.split(",");
            List<WorkPerson> wpList = new ArrayList<WorkPerson>();
            WorkPerson wp = null;
            for (String id : arrIds) {
                wp = workPersonService.selectByPrimaryKey(id);
                wpList.add(wp);
            }
            List<String> excepdays=new ArrayList<String>();
			WorkCalendar wc=workCalendarService.selectByPrimaryKey(regYear);
			if(null!=wc && null!=wc.getExcepdays()){
				excepdays=Arrays.asList(wc.getExcepdays().replaceAll(" ","").split(","));
			}
            for (WorkPerson p : wpList) {
            	Map<String,Object> map=new HashMap<String, Object>();
    			WorkPerson wps= workPersonService.selectByAccount(p.getAccount());
    			for(int i=s;i<=e;i++){
    				map.clear();
    				map.put("personId",p.getAccount());
    				map.put("regYear", regYear);
    				map.put("regMonth",regMonth);
    				map.put("regDay", i);
    				//加班才能改休息日
    				String temDate="";
    				if(i<10){
    					temDate=regYear+"-"+registerTimeStrStart.substring(5,7)+"-0"+i+"";
    				}else{
    					temDate=regYear+"-"+registerTimeStrStart.substring(5,7)+"-"+i+"";
    				}
    				List<WorkRegister> registerList = workRegisterService.selectByDay(map);
    				WorkRegister register=null;
    				if(null!=registerList&& registerList.size()>0){
    					 register=registerList.get(0);
    					 if(null==register.getLeaveprocess()||"".equals(register.getLeaveprocess())){
    							if(excepdays.contains(temDate)){
    	    						if("加班".equals(workRegister.getMorning()) ){
    	    							register.setMorning("加班");
    	    						}
    	    						if("加班".equals(workRegister.getAfternoon())){
    	    							register.setAfternoon("加班");
    	    						}
    	    						if("休息".equals(workRegister.getMorning()) ){
    	    							register.setMorning("休息");
    	    						}
    	    						if("休息".equals(workRegister.getAfternoon())){
    	    							register.setAfternoon("休息");
    	    						}
    	    					}else{
    	    						 	if("年休假".equals(workRegister.getMorning())){
    	    							 	if(!"年休假".equals(register.getMorning())){
    	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())+0.5)+"");
    	    	    						}
    	    	    					}else{
    	    	    						if("年休假".equals(register.getMorning())){
    	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())-0.5)+"");
    	    	    						}
    	    	    					}
    	    	    					if("年休假".equals(workRegister.getAfternoon())){
    	    	    						if(!"年休假".equals(register.getAfternoon())){
    	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())+0.5)+"");
    	    	    						}
    	    	    					}else{
    	    	    						if("年休假".equals(register.getAfternoon())){
    	    	    							wp.setAnnualLeaveUseup((Float.parseFloat(wp.getAnnualLeaveUseup())-0.5)+"");
    	    	    						}
    	    	    					}
    			    					 if(!"加班".equals(workRegister.getMorning())){
    		    							 register.setMorning(workRegister.getMorning());
    		    						 }
    		    						 if(!"加班".equals(workRegister.getAfternoon())){
    		    							 register.setAfternoon(workRegister.getAfternoon());
    		    						 }
    		    				}
    	    					register.setCreater(currentUser.getName());
    	    					register.setRemark(workRegister.getRemark());
    	    					workRegisterService.updateByPrimaryKeySelective(register);
    	    					workPersonService.updateByPrimaryKeySelective(wp);
    					 }
    				}else{
    	                register = new WorkRegister();
    	                String id = UUID.randomUUID().toString().replace("-", "");
    	                register.setId(id);
    	                register.setPersonId(p.getAccount());
    	                register.setEnterpriseId(p.getEnterpriseId());
    	                register.setRegYearMonth(yearMonth);
    	                register.setRegYear(regYear);
    	                register.setRegMonth(regMonth);
    	                register.setRegDay(i+"");
    	                register.setMorning(workRegister.getMorning());
    	                register.setAfternoon(workRegister.getAfternoon());
    	                register.setWorkingDay(workingDay);
    	                register.setCreateTime(new Date());
    	                register.setCreater(currentUser.getName());
    	                register.setRemark(workRegister.getRemark());
    	                String str=regYear+"-"+regMonth+"-"+i+"";
    	                register.setRegisterTime(sdf.parse(str));
    	                workRegisterService.insertSelective(register);
    	                if("年休假".equals(workRegister.getMorning())){
    	                	wps.setAnnualLeaveUseup((Float.parseFloat(wps.getAnnualLeaveUseup())+0.5)+"");
    					}
    					if("年休假".equals(workRegister.getAfternoon())){
    						wps.setAnnualLeaveUseup((Float.parseFloat(wps.getAnnualLeaveUseup())+0.5)+"");
    					}
    					workPersonService.updateByPrimaryKeySelective(wps);
    	            }
    			}
            }
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @RequestMapping("/toPersonEdit")
    public String toPersonEdit(HttpServletRequest request) {
        String id = request.getParameter("id");
        WorkPerson workPerson = workPersonService.selectByPrimaryKey(id);
        WorkEnterprise workEnterprise = workEnterpriseService.selectByPrimaryKey(workPerson.getEnterpriseId());
        workPerson.setEnterpriseName(workEnterprise.getName());
        request.setAttribute("obj", workPerson);
        return "/register/personEdit";
    }

    /**
     * @param @param request
     * @param @param response
     * @return void
     * @throws
     * @Description: TODO
     * @author lixiang
     * @date 2019-6-10上午11:13:58
     * @Company 广东华联软件科技有限公司
     */
    @ResponseBody
    @RequestMapping("/exportRegister")
    public void exportRegister(HttpServletRequest request, HttpServletResponse response) {
        try {
        	List<WorkRegisterVo> list=new ArrayList<WorkRegisterVo>();
        	String currentYear = request.getParameter("currentYear");
            String currentMonth = request.getParameter("currentMonth");
            String ids=request.getParameter("ids");
            Map<String,Object> map=new HashMap<String, Object>();
            if(null!=ids&&!"".equals(ids)){
            	String arr[] = ids.split(",");
            	for(String id:arr){
            		map.put("id", id);
            		map.put("regYearMonth", currentYear+currentMonth);
            		List<WorkRegisterVo> l= workRegisterService.selectListByMap(map);
            		list.addAll(l);
            		map.clear();
            	}
            }else{
            	list = Global.REG_EXPORT_LIST;
            }
            if(currentMonth.length()==1){
            	currentMonth="0"+currentMonth;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(new Date());
            String path = request.getSession().getServletContext().getRealPath("/template/report/考勤管理.xlsx");
            String newPath = request.getSession().getServletContext().getRealPath("/template/temp/" + currentYear+currentMonth + "月度考勤管理导出_导出日期_" + dateString + ".xlsx");
            Map<String, List<List<String>>> date = new HashMap<String, List<List<String>>>();
            List<List<String>> rowList = new ArrayList<List<String>>();
            List<String> cellList = new ArrayList<String>();
            for (WorkRegisterVo l : list) {
                cellList = new ArrayList<String>();
                cellList.add(l.getName());
                cellList.add(l.getEnterpriseName());
                cellList.add(getVa(l.getMorning_1()));
                cellList.add(getVa(l.getAfternoon_1()));
                cellList.add(getVa(l.getMorning_2()));
                cellList.add(getVa(l.getAfternoon_2()));
                cellList.add(getVa(l.getMorning_3()));
                cellList.add(getVa(l.getAfternoon_3()));
                cellList.add(getVa(l.getMorning_4()));
                cellList.add(getVa(l.getAfternoon_4()));
                cellList.add(getVa(l.getMorning_5()));
                cellList.add(getVa(l.getAfternoon_5()));
                cellList.add(getVa(l.getMorning_6()));
                cellList.add(getVa(l.getAfternoon_6()));
                cellList.add(getVa(l.getMorning_7()));
                cellList.add(getVa(l.getAfternoon_7()));
                cellList.add(getVa(l.getMorning_8()));
                cellList.add(getVa(l.getAfternoon_8()));
                cellList.add(getVa(l.getMorning_9()));
                cellList.add(getVa(l.getAfternoon_9()));
                cellList.add(getVa(l.getMorning_10()));
                cellList.add(getVa(l.getAfternoon_10()));
                cellList.add(getVa(l.getMorning_11()));
                cellList.add(getVa(l.getAfternoon_11()));
                cellList.add(getVa(l.getMorning_12()));
                cellList.add(getVa(l.getAfternoon_12()));
                cellList.add(getVa(l.getMorning_13()));
                cellList.add(getVa(l.getAfternoon_13()));
                cellList.add(getVa(l.getMorning_14()));
                cellList.add(getVa(l.getAfternoon_14()));
                cellList.add(getVa(l.getMorning_15()));
                cellList.add(getVa(l.getAfternoon_15()));
                cellList.add(getVa(l.getMorning_16()));
                cellList.add(getVa(l.getAfternoon_16()));
                cellList.add(getVa(l.getMorning_17()));
                cellList.add(getVa(l.getAfternoon_17()));
                cellList.add(getVa(l.getMorning_18()));
                cellList.add(getVa(l.getAfternoon_18()));
                cellList.add(getVa(l.getMorning_19()));
                cellList.add(getVa(l.getAfternoon_19()));
                cellList.add(getVa(l.getMorning_20()));
                cellList.add(getVa(l.getAfternoon_20()));
                cellList.add(getVa(l.getMorning_21()));
                cellList.add(getVa(l.getAfternoon_21()));
                cellList.add(getVa(l.getMorning_22()));
                cellList.add(getVa(l.getAfternoon_22()));
                cellList.add(getVa(l.getMorning_23()));
                cellList.add(getVa(l.getAfternoon_23()));
                cellList.add(getVa(l.getMorning_24()));
                cellList.add(getVa(l.getAfternoon_24()));
                cellList.add(getVa(l.getMorning_25()));
                cellList.add(getVa(l.getAfternoon_25()));
                cellList.add(getVa(l.getMorning_26()));
                cellList.add(getVa(l.getAfternoon_26()));
                cellList.add(getVa(l.getMorning_27()));
                cellList.add(getVa(l.getAfternoon_27()));
                cellList.add(getVa(l.getMorning_28()));
                cellList.add(getVa(l.getAfternoon_28()));
                cellList.add(getVa(l.getMorning_29()));
                cellList.add(getVa(l.getAfternoon_29()));
                cellList.add(getVa(l.getMorning_30()));
                cellList.add(getVa(l.getAfternoon_30()));
                cellList.add(getVa(l.getMorning_31()));
                cellList.add(getVa(l.getAfternoon_31()));
                cellList.add(l.getZnj());
                cellList.add(l.getYxnj());
                cellList.add(l.getSb());
                cellList.add(l.getNxj());
                cellList.add(l.getTqj());
                cellList.add(l.getSj());
                cellList.add(l.getSyj());
                cellList.add(l.getJsj());
                cellList.add(l.getBj());
                cellList.add(l.getHj());
                cellList.add(l.getSangj());
                cellList.add(l.getLgxx());
                cellList.add(l.getCc());
                cellList.add(l.getKg());
                cellList.add(l.getQt());
                cellList.add(l.getJb());
                cellList.add(l.getXx());
                cellList.add(l.getReg_creater());
                String qt=null==l.getQt()?"":l.getQt()+" 天 ";
                String bz=null==l.getReg_remark()?"":l.getReg_remark();
                if(null!=bz&&!"".equals(bz)){
                	cellList.add(qt+bz);
                }else{
                	cellList.add("");
                }
                rowList.add(cellList);
            }
            date.put("考勤管理（月度）", rowList);
            ExcelUtil.copyExcel(2, date, path, newPath, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getVa(String value){
    	String resut="";
    	if(null!=value&&!"".equals(value)){
    		if(value.indexOf("-") !=-1){
    			String arr[]=value.split("-");
    			resut=arr[0];
    		}else{
    			resut=value;
    		}
    	}
    	return resut;
    }
    
    
    
    /**
     * created by ZYL on 2019/6/13
     * 考勤导入，先把用户上传的文件保存到服务器本地硬盘，再读取文件，写数据进数据库。
     */
    @ResponseBody
    @RequestMapping("/attendanceImport")
    public GlobalResult attendanceImport(HttpServletRequest request) {
        return workRegisterService.attendanceImport(request);
    }
    
  //下载导入模板
  	@ResponseBody
  	@RequestMapping("/importREG")
  	public void importREG(HttpServletRequest request,HttpServletResponse response){
  		try {
  			String path=request.getSession().getServletContext().getRealPath("/template/report/考勤登记导入模版.xlsx");
  			String fileName="考勤登记导入模版.xlsx";
  			try {
				if (IeEncordingUtil.isMSBrowser(request)) {
					fileName = URLEncoder.encode(fileName, "UTF8");
				}
  				ExcelUtil.downLoad(path, fileName, request, response);
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  	}
}
