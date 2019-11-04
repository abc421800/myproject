package com.cost168.costaudit.controller.cost;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.cost.CostEnterpriseRecord;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.yaohao.YaohaoAssess;
import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidate;
import com.cost168.costaudit.service.cost.CostEnterpriseRecordService;
import com.cost168.costaudit.service.cost.CostEnterpriseService;
import com.cost168.costaudit.service.work.WorkEnterpriseService;
import com.cost168.costaudit.service.yaohao.YaohaoAssessService;
import com.cost168.costaudit.service.yaohao.YaohaoAutocodeService;
import com.cost168.costaudit.service.yaohao.YaohaoCandidateService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author YiLi
 */

@Controller
@RequestMapping("/costEnterprise")
public class CostEnterpriseController {

    @Autowired
    private CostEnterpriseService costEnterpriseService;

    @Autowired
    private WorkEnterpriseService workEnterpriseService;
    @Autowired
    private YaohaoCandidateService yaohaoCandidateService;
    @Autowired
    private YaohaoAutocodeService yaohaoAutocodeService;
    @Autowired
    private CostEnterpriseRecordService costEnterpriseRecordService;
    @Autowired
    private YaohaoAssessService yaohaoAssessService;


    @RequestMapping("/toEnterpriseList")
    public String toEnterpriseList(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        String currentYear = currentDate.substring(0, 4);
        String entYear="";
		String year=costEnterpriseRecordService.selectYearList();
		if(null!=year && !"".equals(year)){
			entYear=year;
		}else{
			entYear=currentYear;
		}
		request.setAttribute("entYear", entYear);
		request.setAttribute("currentYear", currentYear);
		return "enterprise/list";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,CostEnterprise costEnterprise, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(costEnterprise);
			map.putAll(selectMap);
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startTime =request.getParameter("startTime");
			String endTime =request.getParameter("endTime");
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
			}
			if(null!=costEnterprise.getBatch() && !"".equals(costEnterprise.getBatch())){
				int temp=Integer.parseInt(costEnterprise.getBatch());
				int assess_year=temp-1;
				map.put("year", assess_year);
			}
			
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    List<CostEnterprise> list= costEnterpriseService.selectListByMap(map);
		    map.put("isPage",false);
		    Global.ENT_EXPORT_LIST = costEnterpriseService.selectListByMap(map);
		    int total= costEnterpriseService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



    @ResponseBody
    @RequestMapping("/candidateEnterpriseList")
    public EUDataGridResult candidateEnterpriseList(HttpServletRequest request, CostEnterprise costEnterprise, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            String entYear = currentDate.substring(0, 4);
            Map<String, Object> selectMap = JsonUtils.object2Map(costEnterprise);
            String yaohaoGradeFlag = request.getParameter("yaohaoGradeFlag");
            map.putAll(selectMap);
            map.put("batch", entYear);
            int temp = Integer.parseInt(entYear);
            int assess_year = temp - 1;
            map.put("year", assess_year);
            List<CostEnterprise> list = null;
            if ("B".equals(yaohaoGradeFlag)) {
                list = costEnterpriseService.selectListByMap(map);
            } else {
                map.put("assessResultRk", "第一档");
                list = costEnterpriseService.selectListByMap(map);
            }
            List<YaohaoCandidate> yhList = null;
            List<String> str = new ArrayList<String>();
            List<CostEnterprise> newEntList = new ArrayList<CostEnterprise>();
            if ("B".equals(yaohaoGradeFlag)) {
                yhList = yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(entYear, "第二档");
            } else {
                yhList = yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(entYear, "第一档");
            }
            for (YaohaoCandidate y : yhList) {
                str.add(y.getEnterpriseCode());
            }
            for (CostEnterprise e : list) {
                if (!str.contains(e.getCode())) {
                    newEntList.add(e);
                }
            }
            int total = newEntList.size();
            result.setRows(newEntList);
            result.setTotal(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpServletRequest request) {
        String url = "";
        String id = request.getParameter("id");
        String editFlag = request.getParameter("editFlag");
        if (null != editFlag && !"".equals(editFlag)) {
            request.setAttribute("editFlag", editFlag);
        }
        if (null != id && !"".equals(id)) {
            url = "/costEnterprise/upd";
            CostEnterprise costEnterprise = costEnterpriseService.selectByPrimaryKey(id);
            WorkEnterprise we = workEnterpriseService.selectByWorkEnterpriseName(costEnterprise.getName());
            if (null != we) {
                costEnterprise.setStationing("是");
                request.setAttribute("workEnterpriseId", we.getId());
            } else {
                costEnterprise.setStationing("否");
                request.setAttribute("workEnterpriseId", "没有");
            }
            request.setAttribute("obj", costEnterprise);
            request.setAttribute("add_edit", "edit");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            String currentYear = currentDate.substring(0, 4);
            int s = Integer.parseInt(currentYear);
            int e = s + 2;
            String enterpriseStart = s + "-01" + "-01";
            String enterpriseEnd = e + "-12" + "-31";
            SysUser currentUser = shiroUtil.getInstance().currentUser();
            url = "/costEnterprise/save";
            String enterpriseId = UUID.randomUUID().toString().replace("-", "");
            CostEnterprise costEnterprise = new CostEnterprise();
            costEnterprise.setId(enterpriseId);
            costEnterprise.setCreater(currentUser.getName());
            try {
                costEnterprise.setEnterpriseStart(sdf.parse(enterpriseStart));
                costEnterprise.setEnterpriseEnd(sdf.parse(enterpriseEnd));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            request.setAttribute("workEnterpriseId", "没有");
            request.setAttribute("obj", costEnterprise);
            request.setAttribute("add_edit", "add");
        }
        request.setAttribute("url", url);
        return "enterprise/add";
    }


    @ResponseBody
    @RequestMapping("/save")
    public GlobalResult save(HttpServletRequest request, CostEnterprise costEnterprise) {
        GlobalResult result = new GlobalResult();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String enterpriseTime = request.getParameter("enterpriseTime");
            String start = enterpriseTime.substring(0, 10);
            String end = enterpriseTime.substring(enterpriseTime.length() - 10, enterpriseTime.length());
            costEnterprise.setEnterpriseStart(sdf.parse(start));
            costEnterprise.setEnterpriseEnd(sdf.parse(end));
            SysUser currentUser = shiroUtil.getInstance().currentUser();
            costEnterprise.setCreaterTime(new Date());
            costEnterprise.setCreater(currentUser.getName());
            costEnterprise.setDeleteFlag(1);
            boolean flag_name = false;
            boolean flag_code = false;
            boolean flag_simpleName = false;
            StringBuffer sb = new StringBuffer();
            map.put("nameNoLike", costEnterprise.getName());
            List<CostEnterprise> enterpriseList_name = costEnterpriseService.selectList(map);
            map.clear();
            map.put("code", costEnterprise.getCode());
            List<CostEnterprise> enterpriseList_code = costEnterpriseService.selectList(map);
            map.clear();
            map.put("simpleName", costEnterprise.getSimpleName());
            List<CostEnterprise> enterpriseList_simpleName = costEnterpriseService.selectList(map);
            //判断名字是否重复
            if (null != enterpriseList_name && enterpriseList_name.size() > 0) {
                flag_name = true;
            }
            //判断编码是否重复
            if (null != enterpriseList_code && enterpriseList_code.size() > 0) {
                flag_code = true;
            }
            //判断简称是否重复
            if (null != enterpriseList_simpleName && enterpriseList_simpleName.size() > 0) {
                flag_simpleName = true;
            }
            if (flag_name) {
                sb.append("企业名称重复 ");
            }
            if (flag_code) {
                sb.append("企业编码重复 ");
            }
            if (flag_simpleName) {
                sb.append("企业简称重复 ");
            }
            if (!flag_name && !flag_code && !flag_simpleName) {
                costEnterpriseService.insertSelective(costEnterprise);
                map.clear();
                String currentDate = sdf.format(new Date());
                String currentYear = currentDate.substring(0, 4);
                CostEnterpriseRecord er = new CostEnterpriseRecord();
                er.setId(OrderUtil.buildOrderId(""));
                er.setEnterpriseId(costEnterprise.getId());
                er.setCreateTime(new Date());
                er.setOperator(currentUser.getName());
                er.setStatus("在库");
                er.setYear(currentYear);
                costEnterpriseRecordService.insertSelective(er);
                //更新摇号名单
                YaohaoAutocode code = yaohaoAutocodeService.getCodeAorB();
                YaohaoCandidate canNew = new YaohaoCandidate();
                canNew.setId(OrderUtil.buildOrderId(""));
                canNew.setEnterpriseCode(costEnterprise.getCode());
                canNew.setYaohaoYear(currentYear);
                canNew.setYaohaoGrade("第二档");
                canNew.setCreateTime(new Date());
                canNew.setCreater(currentUser.getName());
                canNew.setRoundNum(code.getLunNumB());
                canNew.setRemoveFlag("1");
                yaohaoCandidateService.insertSelective(canNew);
                result.setStatus(200);
                result.setMsg(sb.toString());
            } else {
                result.setStatus(300);
                result.setMsg(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/upd")
    public GlobalResult upd(HttpServletRequest request, CostEnterprise costEnterprise) {
        GlobalResult result = new GlobalResult();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String enterpriseTime = request.getParameter("enterpriseTime");
            String start = enterpriseTime.substring(0, 10);
            String end = enterpriseTime.substring(enterpriseTime.length() - 10, enterpriseTime.length());
            costEnterprise.setEnterpriseStart(sdf.parse(start));
            costEnterprise.setEnterpriseEnd(sdf.parse(end));
            costEnterprise.setCreaterTime(new Date());
            boolean flag_name = false;
            boolean flag_code = false;
            boolean flag_simpleName = false;
            StringBuffer sb = new StringBuffer();
            if ("出库".equals(costEnterprise.getEffectiveFlag()) || "暂停".equals(costEnterprise.getEffectiveFlag())) {
                costEnterpriseService.updateByPrimaryKeySelective(costEnterprise);
                //摇号名单剔除该企业
                String currentDate = sdf.format(new Date());
                String currentYear = currentDate.substring(0, 4);
                List<YaohaoCandidate> yhOne = yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(currentYear, "第一档");
                List<YaohaoCandidate> yhTwo = yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(currentYear, "第二档");
                yhTwo.addAll(yhOne);
                for (YaohaoCandidate yh : yhTwo) {
                    if (costEnterprise.getCode().equals(yh.getEnterpriseCode())) {
                        yaohaoCandidateService.deleteByPrimaryKey(yh.getId());
                    }
                }
                result.setStatus(200);
            } else {
                map.put("nameNoLike", costEnterprise.getName());
                map.put("noid", costEnterprise.getId());
                List<CostEnterprise> enterpriseList_name = costEnterpriseService.selectListByMap(map);
                map.clear();
                map.put("code", costEnterprise.getCode());
                map.put("noid", costEnterprise.getId());
                List<CostEnterprise> enterpriseList_code = costEnterpriseService.selectListByMap(map);
                map.clear();
                map.put("simpleName", costEnterprise.getSimpleName());
                map.put("noid", costEnterprise.getId());
                List<CostEnterprise> enterpriseList_simpleName = costEnterpriseService.selectListByMap(map);
                //判断名字是否重复
                if (null != enterpriseList_name && enterpriseList_name.size() > 0) {
                    flag_name = true;
                }
                //判断编码是否重复
                if (null != enterpriseList_code && enterpriseList_code.size() > 0) {
                    flag_code = true;
                }
                //判断简称是否重复
                if (null != enterpriseList_simpleName && enterpriseList_simpleName.size() > 0) {
                    flag_simpleName = true;
                }
                if (flag_name) {
                    sb.append("企业名称重复 ");
                }
                if (flag_code) {
                    sb.append("企业编码重复 ");
                }
                if (flag_simpleName) {
                    sb.append("企业简称重复 ");
                }
                if (!flag_name && !flag_code && !flag_simpleName) {
                    CostEnterprise e = costEnterpriseService.selectByPrimaryKey(costEnterprise.getId());
                    costEnterpriseService.updateByPrimaryKeySelective(costEnterprise);
                    //摇号名单
                    if (!"在库".equals(e.getEffectiveFlag())) {
                        if ("在库".equals(costEnterprise.getEffectiveFlag())) {
                            SysUser currentUser = shiroUtil.getInstance().currentUser();
                            String currentDate = sdf.format(new Date());
                            String currentYear = currentDate.substring(0, 4);
                            YaohaoAutocode code = yaohaoAutocodeService.getCodeAorB();
                            YaohaoCandidate canNew = new YaohaoCandidate();
                            canNew.setId(OrderUtil.buildOrderId(""));
                            canNew.setEnterpriseCode(costEnterprise.getCode());
                            canNew.setYaohaoYear(currentYear);
                            canNew.setYaohaoGrade("第二档");
                            canNew.setCreateTime(new Date());
                            canNew.setCreater(currentUser.getName());
                            canNew.setRoundNum(code.getLunNumB());
                            yaohaoCandidateService.insertSelective(canNew);
                        }
                    }
                    result.setStatus(200);
                    result.setMsg(sb.toString());
                } else {
                    result.setStatus(300);
                    result.setMsg(sb.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteProject")
    public GlobalResult deleteProject(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String arr[] = ids.split(",");
        try {
            CostEnterprise costEnterprise = null;
            for (String i : arr) {
                if (i != null && !"".equals(i)) {
                    costEnterprise = new CostEnterprise();
                    costEnterprise.setId(i);
                    costEnterprise.setDeleteFlag(2);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("enterpriseId", costEnterprise.getId());
                    List<CostEnterpriseRecord> erList = costEnterpriseRecordService.selectListByMap(map);
                    for (CostEnterpriseRecord r : erList) {
                        costEnterpriseRecordService.deleteByPrimaryKey(r.getId());
                    }
                    costEnterpriseService.updateByPrimaryKeySelective(costEnterprise);
                }
            }
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

	//导出
	@RequestMapping("/exportEnterprise")
	@ResponseBody
	public void exportEnterprise(HttpServletRequest request,CostEnterprise enterprise,HttpServletResponse response){
		List<CostEnterprise> list =new ArrayList<CostEnterprise>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
				/*Map<String,Object> map=new HashMap<String,Object>();
				String flag=request.getParameter("flag");*/
				String batch= request.getParameter("batch");
				/*if("zk".equals(flag)){
					map.put("effectiveFlag", "在库");
				}else if("zt".equals(flag)){
					map.put("effectiveFlag", "暂停");
				}else{
					map.put("effectiveFlag", "出库");
				}
				String currentDate = sdf.format(new Date());
		        String entYear = currentDate.substring(0, 4);
		        int temp=Integer.parseInt(entYear);
		        int assess_year=temp-1;
		        map.put("year", assess_year);
		        map.put("batch", batch);
				list= costEnterpriseService.selectListByMap(map);*/
			list=Global.ENT_EXPORT_LIST;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(new Date());
			String path=request.getSession().getServletContext().getRealPath("/template/report/enterprise_template.xlsx");
			String newPath=request.getSession().getServletContext().getRealPath("/template/temp/入库批次"+batch+"_入库企业导出_"+dateString+".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(CostEnterprise l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getName());
				cellList.add(l.getSimpleName());
				cellList.add(l.getContacts());
				cellList.add(l.getContactsPhone());
				cellList.add(l.getStatus());
				cellList.add(l.getR_year());
				cellList.add(null==l.getAssessResultRk()|| "".equals(l.getAssessResultRk())?"未评定":l.getAssessResultRk());
				cellList.add(null==l.getYaohaoGradeRk()|| "".equals(l.getYaohaoGradeRk())?"第二档":l.getYaohaoGradeRk());
				cellList.add(l.getWinNum());
				cellList.add(null!=l.getServiceAmountRk()?l.getServiceAmountRk().toString():"");
				cellList.add(l.getStationingStr());
				cellList.add(l.getEnterpriseEndStr());
				rowList.add(cellList);
			}
			date.put("入库企业", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


    //下载导入模板
    @ResponseBody
    @RequestMapping("/importEnt")
    public void importEnt(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/template/report/入库企业导入模版.xlsx");
            try {
                ExcelUtil.downLoad(path, "入库企业导入模版.xlsx", request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入
    @ResponseBody
    @RequestMapping("importEnterprise")
    public GlobalResult importEnterprise(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        int status = costEnterpriseService.importEnterprise(request);
        result.setStatus(status);
        return result;
    }

    //下载z手册
    @ResponseBody
    @RequestMapping("/uploadSC")
    public void uploadSC(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/template/report/广州市代建局造价审核管理系统-用户手册.pdf");
            String fileName = "广州市代建局造价审核管理系统-用户手册.pdf";
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

    //下载考勤管理
    @ResponseBody
    @RequestMapping("/uploadKQ")
    public void uploadKQ(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/template/report/广州市代建局造价审核管理系统-考勤管理.pdf");
            String fileName = "广州市代建局造价审核管理系统-考勤管理.pdf";
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

    //下载考勤管理
    @ResponseBody
    @RequestMapping("/uploadYH")
    public void uploadYH(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/template/report/广州市代建局造价审核管理系统-摇号管理.pdf");
            String fileName = "广州市代建局造价审核管理系统-摇号.pdf";
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
    @ResponseBody
    @RequestMapping("/uploadZX")
    public void uploadZX(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/template/report/广州市代建局造价审核管理系统-摇号管理.pdf");
            String fileName = "广州市代建局造价审核管理系统-造价咨询管理V1.0（20190925）.pdf";
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
    }  @ResponseBody
    @RequestMapping("/uploadZB")
    public void uploadZB(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/template/report/广州市代建局造价审核管理系统-项目指标查询V1.0（20190926）.pdf");
            String fileName = "广州市代建局造价审核管理系统-项目指标查询V1.0（20190926）.pdf";
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
    @ResponseBody
    @RequestMapping("/buildYearRecode")
    public void buildYearRecode(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CostEnterprise> list = costEnterpriseService.selectByExample(null);
            CostEnterpriseRecord cr = null;
            for (CostEnterprise e : list) {
                String year = e.getBatch();
                //int y=Integer.parseInt(year);
                //int m=y+2;
                //for(int a=y;a<=m;a++){
                cr = new CostEnterpriseRecord();
                cr.setId(OrderUtil.buildOrderId(""));
                cr.setEnterpriseId(e.getId());
                cr.setStatus("在库");
                cr.setYear(year);
                cr.setOperator("admin");
                cr.setCreateTime(new Date());
                costEnterpriseRecordService.insertSelective(cr);
                //}

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping("/erlist")
    public EUDataGridResult erlist(HttpServletRequest request, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String enterpriseId = request.getParameter("enterpriseId");
            map.put("enterpriseId", enterpriseId);
            map.put("isPage", true);
            map.put("curPage", rows * (page - 1));
            map.put("pageSize", rows);
            List<CostEnterpriseRecord> list = costEnterpriseRecordService.selectListByMap(map);
            int total = costEnterpriseRecordService.selectCountByMap(map);
            result.setRows(list);
            result.setTotal(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/saveEr")
    public GlobalResult saveEr(HttpServletRequest request, CostEnterpriseRecord costEnterpriseRecord) {
        GlobalResult result = new GlobalResult();
        try {

            SysUser currentUser = shiroUtil.getInstance().currentUser();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("enterpriseId", costEnterpriseRecord.getEnterpriseId());
            map.put("year", costEnterpriseRecord.getYear());
            List<CostEnterpriseRecord> crList = costEnterpriseRecordService.selectListByMap(map);
            if (null != crList && crList.size() > 0) {
                result.setStatus(300);
            } else {
                costEnterpriseRecord.setCreateTime(new Date());
                costEnterpriseRecord.setOperator(currentUser.getName());
                costEnterpriseRecordService.insertSelective(costEnterpriseRecord);
                result.setStatus(200);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updEr")
    public GlobalResult updEr(HttpServletRequest request, CostEnterpriseRecord costEnterpriseRecord) {
        GlobalResult result = new GlobalResult();
        try {
            String enterpriseId = request.getParameter("enterpriseId");
            CostEnterprise ent = costEnterpriseService.selectByPrimaryKey(enterpriseId);
            CostEnterpriseRecord cr = costEnterpriseRecordService.selectByPrimaryKey(costEnterpriseRecord.getId());
            SysUser currentUser = shiroUtil.getInstance().currentUser();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("enterpriseId", enterpriseId);
            map.put("year", cr.getYear());
            map.put("noid", costEnterpriseRecord.getId());
            List<CostEnterpriseRecord> crList = costEnterpriseRecordService.selectListByMap(map);
            if (null != crList && crList.size() > 0) {
                result.setStatus(300);
            } else {
                costEnterpriseRecord.setCreateTime(new Date());
                costEnterpriseRecord.setOperator(currentUser.getName());
                String currentDate = sdf.format(new Date());
                String currentYear = currentDate.substring(0, 4);
                if (cr.getYear().equals(currentYear)) {
                    if ("在库".equals(cr.getStatus())) {
                        if (!"在库".equals(costEnterpriseRecord.getStatus())) {
                            //摇号名单剔除该企业
                            List<YaohaoCandidate> yhOne = yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(cr.getYear(), "第一档");
                            List<YaohaoCandidate> yhTwo = yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(cr.getYear(), "第二档");
                            yhTwo.addAll(yhOne);
                            for (YaohaoCandidate yh : yhTwo) {
                                if (ent.getCode().equals(yh.getEnterpriseCode())) {
                                    yaohaoCandidateService.deleteByPrimaryKey(yh.getId());
                                }
                            }
                        }
                    } else if (!"在库".equals(cr.getStatus())) {
                        if ("在库".equals(costEnterpriseRecord.getStatus())) {
                            //摇号名加入该企业,要判断是加入第几档
                            Map<String, Object> mapAss = new HashMap<String, Object>();
                            mapAss.put("enterpriseCode", ent.getCode());
                            mapAss.put("year", costEnterpriseRecord.getYear());
                            List<YaohaoAssess> assList = yaohaoAssessService.selectList(mapAss);
                            YaohaoAutocode code = yaohaoAutocodeService.getCodeAorB();
                            YaohaoCandidate canNew = new YaohaoCandidate();
                            canNew.setId(OrderUtil.buildOrderId(""));
                            canNew.setEnterpriseCode(ent.getCode());
                            canNew.setYaohaoYear(currentYear);
                            if (null != assList && assList.size() > 0) {
                                if ("第二档".equals(assList.get(0).getAssessResult())) {
                                    canNew.setYaohaoGrade("第二档");
                                    canNew.setRoundNum(code.getLunNumB());
                                } else {
                                    canNew.setYaohaoGrade("第一档");
                                    canNew.setRoundNum(code.getLunNumA());
                                }
                            } else {
                                canNew.setYaohaoGrade("第二档");
                                canNew.setRoundNum(code.getLunNumB());
                            }
                            canNew.setCreateTime(new Date());
                            canNew.setCreater(currentUser.getName());
                            canNew.setRemoveFlag("1");
                            yaohaoCandidateService.insertSelective(canNew);
                        }
                    }
                }
                costEnterpriseRecordService.updateByPrimaryKeySelective(costEnterpriseRecord);
                result.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @RequestMapping("/toAddEr")
    public String toAddEr(HttpServletRequest request) {
        String url = "";
        String id = request.getParameter("id");
        String enterpriseId = request.getParameter("enterpriseId");
        if (null != id && !"".equals(id)) {
            url = "/costEnterprise/updEr";
            CostEnterpriseRecord cr = costEnterpriseRecordService.selectByPrimaryKey(id);
            request.setAttribute("obj", cr);
            request.setAttribute("add_edit", "edit");
        } else {
            url = "/costEnterprise/saveEr";
            String rid = OrderUtil.buildOrderId("");
            CostEnterpriseRecord cr = new CostEnterpriseRecord();
            cr.setId(rid);
            cr.setEnterpriseId(enterpriseId);
            request.setAttribute("obj", cr);
            request.setAttribute("add_edit", "add");
        }
        request.setAttribute("url", url);
        return "enterprise/addEnterpriseRecord";
    }


    @ResponseBody
    @RequestMapping("/delEr")
    public GlobalResult delEr(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String arr[] = ids.split(",");
        try {
            for (String i : arr) {
                costEnterpriseRecordService.deleteByPrimaryKey(i);
            }
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/assessList")
    public EUDataGridResult assessList(HttpServletRequest request, int page, int rows) {
        EUDataGridResult result = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            result = yaohaoAssessService.assessList(request, page, rows, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
