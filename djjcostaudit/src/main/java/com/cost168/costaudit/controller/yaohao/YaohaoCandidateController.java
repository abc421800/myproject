package com.cost168.costaudit.controller.yaohao;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.utils.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidate;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateRemove;
import com.cost168.costaudit.service.cost.CostEnterpriseService;
import com.cost168.costaudit.service.yaohao.YaohaoAutocodeService;
import com.cost168.costaudit.service.yaohao.YaohaoCandidateRemoveService;
import com.cost168.costaudit.service.yaohao.YaohaoCandidateService;
import com.cost168.costaudit.shiro.shiroUtil;

/**
 *
 * ClassName: YaohaoCandidateController
 * @Description: TODO
 * @author lixiang
 * @date 2019-7-8上午10:53:04
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/yaohaoCandidate")
public class YaohaoCandidateController {


	@Autowired
	private YaohaoCandidateService yaohaoCandidateService;
	@Autowired
	private YaohaoAutocodeService yaohaoAutocodeService;
	@Autowired
	private CostEnterpriseService costEnterpriseService;
	@Autowired
	private YaohaoCandidateRemoveService yaohaoCandidateRemoveService;

	private final Logger logger = LogManager.getLogger(YaohaoCandidateController.class);

	@RequestMapping("/toNameList")
	public String toEdit(HttpServletRequest request){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String currentDate = sdf.format(new Date());
		    String currentYear = currentDate.substring(0, 4);
			Map<String,Object> map=new HashMap<String,Object>();
			YaohaoAutocode code=yaohaoAutocodeService.getCodeAorB();
			List<YaohaoCandidate> cList= yaohaoCandidateService.selectListByMap(null);
			if(null!=cList && cList.size()==9999999){
				map.put("effectiveFlag", "在库");
				map.put("batch", currentYear);
				List<CostEnterprise> eList= costEnterpriseService.selectListByMap(map);
				YaohaoCandidate can=null;
				for(CostEnterprise e:eList){
					can=new YaohaoCandidate();
					can.setId(OrderUtil.buildOrderId(""));
					can.setCreater("admin");
					can.setEnterpriseCode(e.getCode());
					can.setCreateTime(new Date());
					can.setYaohaoYear(currentYear);
					can.setYaohaoGrade("第二档");
					can.setRoundNum(code.getLunNumB());
					can.setRemoveFlag("1");
					int temp=yaohaoCandidateService.insertSelective(can);
					if(temp<=0){
						logger.error("插入异常！");
					}
				}
			}
			request.setAttribute("code", code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "yaohao/yaohaoMng/nameList";
	}



	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,YaohaoCandidate yaohaoCandidate, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> mapEnt=new HashMap<String,Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String currentDate = sdf.format(new Date());
	        String currentYear = currentDate.substring(0, 4);
			Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoCandidate);
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    map.put("yaohaoYear",currentYear);
			List<YaohaoCandidate> list=null;
		    for(int i=1;i<=2;i++){
				if(i==1){
					map.put("isPage",false);
					list=new ArrayList<YaohaoCandidate>();
					List<YaohaoCandidate> list1= yaohaoCandidateService.selectListByMap(map);
					for (YaohaoCandidate c:list1){
						CostEnterprise e=costEnterpriseService.selectByCostEnterpriseCode(c.getEnterpriseCode());
						c.setContacts(e.getContacts());
						c.setContactsPhone(e.getContactsPhone());
						c.setBidder(e.getBidder());
						c.setBidderPhone(e.getBidderPhone());
						list.add(c);
					}
					Global.CANDIDATE_EXPORT_LIST=list;
				}else {
					list= yaohaoCandidateService.selectListByMap(map);
				}
			}
		    int total= yaohaoCandidateService.selectCountByMap(map);
		    //处理不参与的企业
		    //if("第二档".equals(yaohaoCandidate.getYaohaoGrade())){
			List<CostEnterprise> eList=null;
			eList=costEnterpriseService.selectListByMap(mapEnt);
			if("bcy".equals(yaohaoCandidate.getYaohaoJoin())){
			    	mapEnt.put("effectiveFlag", "在库");
			    	mapEnt.put("batch", currentYear);
			    	int temp=Integer.parseInt(currentYear);
			    	int assess_year=temp-1;
			    	mapEnt.put("year", assess_year);
			    	mapEnt.put("effectiveFlag", "暂停");
			    	eList=costEnterpriseService.selectListByMap(mapEnt);
			    	if(null!=eList&&eList.size()>0){
			    		YaohaoCandidate can=null;
			    		for(CostEnterprise e:eList){
			    			can=new YaohaoCandidate();
			    			can.setId(OrderUtil.buildOrderId(""));
							can.setEnterpriseCode(e.getCode());
			    			can.setEnterpriseName(e.getName());
			    			can.setEnterpriseId(e.getId());
			    			can.setAssessResult(e.getAssessResultRk());
			    			can.setWinNum("0");
			    			can.setYaohaoYear(currentYear);
			    			can.setContacts(e.getContacts());
			    			can.setContactsPhone(e.getContactsPhone());
			    			can.setBidder(e.getBidder());
			    			can.setBidderPhone(e.getBidderPhone());
		    			    if("第二档".equals(yaohaoCandidate.getYaohaoGrade())){
		    			    	can.setYaohaoGrade("第二档");
		    			    }else{
		    			    	can.setYaohaoGrade("第一档");
		    			    }
		    			    can.setRemarkStr("暂停摇号资格");
		    			    list.add(can);
			    		}
			    		total=total+eList.size();
			    	}
			    }
		    //}
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			String ids=request.getParameter("ids");
			String yaohaoGradeFlag=request.getParameter("yaohaoGradeFlag");
			YaohaoAutocode code=yaohaoAutocodeService.getCodeAorB();
			SysUser currentUser=shiroUtil.getInstance().currentUser();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String currentDate = sdf.format(new Date());
	        String currentYear = currentDate.substring(0, 4);
	        List<YaohaoCandidate> list=null;
			if(null!=ids&&!"".equals(ids)){
				if("B".equals(yaohaoGradeFlag)){
					list=yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(currentYear, "第二档");
				}else{
					list=yaohaoCandidateService.selectCandidateCurrentYaohaoGrade(currentYear, "第一档");
				}
				List<String> strList=new ArrayList<String>();
				for(YaohaoCandidate y:list){
					strList.add(y.getEnterpriseCode());
				}
				List<String> arrList=Arrays.asList(ids.split(","));
				YaohaoCandidate can=null;
				Map<String,Object> map=new HashMap<String, Object>();
				for(String id:arrList){
					if(strList.contains(id)){
						continue;
					}else{
						can=new YaohaoCandidate();
						can.setId(OrderUtil.buildOrderId(""));
						can.setCreater(currentUser.getName());
						can.setEnterpriseCode(id);
						can.setCreateTime(new Date());
						can.setYaohaoYear(currentYear);
						YaohaoAutocode orderCode=yaohaoAutocodeService.getCodeAorB();
						String autocode=null;
						if("B".equals(yaohaoGradeFlag)){
							autocode=orderCode.getCodeB();
							can.setYaohaoGrade("第二档");
							can.setRoundNum(code.getLunNumB());
						}else{
							autocode=orderCode.getCodeA();
							can.setYaohaoGrade("第一档");
							can.setRoundNum(code.getLunNumA());
						}
						can.setRemoveFlag("1");
						int temp=0;
						temp=yaohaoCandidateService.insertSelective(can);
						if(temp<=0){
							logger.error("插入异常！");
						}
						//查询candidateRemove表
						map.put("orderCode", autocode);
						map.put("enterpriseId", id);
						List<YaohaoCandidateRemove> crList=yaohaoCandidateRemoveService.selectListByMap(map);
						if(null!=crList && crList.size()>0){
							temp=yaohaoCandidateService.deleteByPrimaryKey(crList.get(0).getCandidateId());
							if (temp<=0){
								logger.error("删除异常！");
							}
							temp=yaohaoCandidateRemoveService.deleteByPrimaryKey(crList.get(0).getId());
							if(temp<=0){
								logger.error("删除异常！");
							}
						}
						map.clear();
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

	@ResponseBody
	@RequestMapping("/del")
	public GlobalResult del(HttpServletRequest request) {
		GlobalResult result=new GlobalResult();
		try {
			String ids=request.getParameter("ids");
			String arr[]=ids.split(",");
			YaohaoAutocode auto= yaohaoAutocodeService.getCodeAorB();
			SysUser currentUser=shiroUtil.getInstance().currentUser();
			for(String i:arr){
				int temp=0;
				YaohaoCandidate cd=yaohaoCandidateService.selectByPrimaryKey(i);
				cd.setRemoveFlag("2");
				temp=yaohaoCandidateService.updateByPrimaryKeySelective(cd);
				if(temp<=0){
					logger.error("更新异常！");
				}
				CostEnterprise coste=costEnterpriseService.selectByCostEnterpriseCode(cd.getEnterpriseCode());
				YaohaoCandidateRemove cr=new YaohaoCandidateRemove();
				cr.setId(OrderUtil.buildOrderId(""));
				cr.setCreater(currentUser.getName());
				cr.setCreateTime(new Date());
				cr.setEnterpriseId(cd.getEnterpriseCode());
				cr.setEnterpriseName(coste.getName());
				if("第二档".equals(cd.getYaohaoGrade())){
					cr.setOrderCode(auto.getCodeB());
				}else{
					cr.setOrderCode(auto.getCodeA());
				}
				cr.setCandidateId(i);
				temp=yaohaoCandidateRemoveService.insertSelective(cr);
				if(temp<0){
					logger.error("插入异常！");
				}
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

/*	@ResponseBody
	public void select(HttpServletRequest request,YaohaoCandidate yaohaoCandidate) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> mapEnt=new HashMap<String,Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sdf.format(new Date());
			String currentYear = currentDate.substring(0, 4);
			Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoCandidate);
			map.putAll(selectMap);
			map.put("yaohaoYear",currentYear);
			List<YaohaoCandidate> list= yaohaoCandidateService.selectListByMap(map);
			int total= yaohaoCandidateService.selectCountByMap(map);
			//处理不参与的企业
			//if("第二档".equals(yaohaoCandidate.getYaohaoGrade())){
			List<CostEnterprise> eList=null;
			if("bcy".equals(yaohaoCandidate.getYaohaoJoin())){
				mapEnt.put("effectiveFlag", "在库");
				mapEnt.put("batch", currentYear);
				int temp=Integer.parseInt(currentYear);
				int assess_year=temp-1;
				mapEnt.put("year", assess_year);
				mapEnt.put("effectiveFlag", "暂停");
				eList=costEnterpriseService.selectListByMap(mapEnt);
				if(null!=eList&&eList.size()>0){
					YaohaoCandidate can=null;
					for(CostEnterprise e:eList){
						can=new YaohaoCandidate();
						can.setId(OrderUtil.buildOrderId(""));
						can.setEnterpriseName(e.getName());
						can.setEnterpriseId(e.getId());
						can.setAssessResult(e.getAssessResultRk());
						can.setWinNum("0");
						can.setYaohaoYear(currentYear);
						if("第二档".equals(yaohaoCandidate.getYaohaoGrade())){
							can.setYaohaoGrade("第二档");
						}else{
							can.setYaohaoGrade("第一档");
						}
						can.setRemarkStr("暂停摇号资格");
						list.add(can);
					}
					total=total+eList.size();
				}
			}
			//}
			Global.CANDIDATE_EXPORT_LIST=list;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	//导出摇号名单
	@ResponseBody
	@RequestMapping("/exportYaohaoCandidate")
	public void exportYaohaoCandidate(HttpServletRequest request,YaohaoCandidate yaohaoCandidate,HttpServletResponse response){
		List<YaohaoCandidate> list =new ArrayList<YaohaoCandidate>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					YaohaoCandidate winbid=yaohaoCandidateService.selectByPrimaryKey(id);
					list.add(winbid);
				}
			}else{
				/*Map<String,Object> map=new HashMap<String,Object>();
				Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoCandidate);
				map.putAll(selectMap);
				map.put("bidFlagNull", "bidFlagNull");
				list= yaohaoCandidateService.selectListByMap(map);*/
				list=Global.CANDIDATE_EXPORT_LIST;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String path=request.getSession().getServletContext().getRealPath("/template/report/yaohaocandidate_template.xlsx");
			String newPath="";
			String dateString = formatter.format(new Date());
			newPath=request.getSession().getServletContext().getRealPath("/template/temp/摇号名单_"+dateString+".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(YaohaoCandidate l:list){
				CostEnterprise  e=costEnterpriseService.selectByCostEnterpriseCode(l.getEnterpriseCode());
				if(null!=e){
					l.setEnterpriseName(e.getName());
				}
				cellList =new ArrayList<String>();
				cellList.add(l.getEnterpriseName());
				cellList.add(null!=l.getAssessResult()?l.getAssessResult():"未评定");
				cellList.add(l.getWinNum());
				cellList.add(l.getYaohaoYear());
				cellList.add(l.getYaohaoGrade());
				cellList.add(l.getBidder());
				cellList.add(l.getBidderPhone());
				cellList.add(l.getContacts());
				cellList.add(l.getContactsPhone());
				rowList.add(cellList);
			}
			date.put("摇号名单", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping("/uploadQdb")
	public void uploadQdb(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/摇珠会议签到表-模版下载.docx");
			String fileName="摇珠会议签到表-模版下载.docx";
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
	@RequestMapping("/uploadJlb")
	public void uploadJlb(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/摇珠记录表-模版下载.docx");
			String fileName="摇珠记录表-模版下载.docx";
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
