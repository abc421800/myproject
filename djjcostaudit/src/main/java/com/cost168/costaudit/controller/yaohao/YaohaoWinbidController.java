package com.cost168.costaudit.controller.yaohao;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.controller.cost.CostContractController;
import com.cost168.costaudit.pojo.cost.CostContract;
import com.cost168.costaudit.pojo.yaohao.YaohaoOrder;
import com.cost168.costaudit.service.cost.CostContractService;
import com.cost168.costaudit.utils.*;
import org.jboss.util.platform.Java;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbid;
import com.cost168.costaudit.service.cost.CostEnterpriseService;
import com.cost168.costaudit.service.yaohao.YaohaoWinbidService;

/**
 * 
 * ClassName: YaohaoWinbidController 
 * @Description: TODO
 * @author lixiang
 * @date 2019-7-8下午5:27:43
 * @Company 广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/yaohaoWinbid")
public class YaohaoWinbidController {
	
	
	@Autowired
	private YaohaoWinbidService yaohaoWinbidService;
	@Autowired
	private CostEnterpriseService costEnterpriseService;
	@Autowired
	private CostContractService costContractService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,YaohaoWinbid yaohaoWinbid, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoWinbid);
		try {
			String startTime =request.getParameter("startTime");
			String endTime =request.getParameter("endTime");
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
			}
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    for (int i=1;i<=2;i++){
		    	if(i==2){
					map.put("curPage",false);
				}
				List<YaohaoWinbid> list1= yaohaoWinbidService.selectListByMap(map);
				List<YaohaoWinbid> list = new ArrayList<YaohaoWinbid>();
				for (YaohaoWinbid wd:list1){
					if(wd.getContractId()!=null){
						CostContract costContract=costContractService.selectByPrimaryKey(wd.getContractId());
						if(costContract!=null){
							wd.setSettlementAmount(costContract.getSettlementAmount());
							costContract.getName();
						}
					}
					list.add(wd);
				}
				if(i==2){
					Global.WINBID_EXPORT_LIST=list;
				}else {
					result.setRows(list);
				}
			}
		    int total= yaohaoWinbidService.selectCountByMap(map);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/winbidlist")
	public EUDataGridResult winbidlist(HttpServletRequest request,YaohaoWinbid yaohaoWinbid, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoWinbid);
		try {
			String startTime =request.getParameter("startTime");
			String endTime =request.getParameter("endTime");
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
			}
			map.putAll(selectMap);
			List<YaohaoWinbid> countList= yaohaoWinbidService.selectListByMap(map);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
			for (int i=1;i<=2;i++){
				if(i==2){
					map.put("curPage",false);
				}
				List<YaohaoWinbid> list1= yaohaoWinbidService.selectListByMap(map);
				List<YaohaoWinbid> list = new ArrayList<YaohaoWinbid>();
				for (YaohaoWinbid wd:list1){
					if(wd.getContractId()!=null){
						CostContract costContract=costContractService.selectByPrimaryKey(wd.getContractId());
						if(costContract!=null){
							wd.setSettlementAmount(costContract.getSettlementAmount());
						}
					}
					list.add(wd);
				}
				if(i==2){
					Global.WINBID_EXPORT_LIST=list;
				}else {
					result.setRows(list);
				}
			}

		    int total= yaohaoWinbidService.selectCountByMap(map);
		    //汇总
		    List<YaohaoWinbid> footer = new ArrayList<YaohaoWinbid>();
		    YaohaoWinbid one=new YaohaoWinbid();
		    BigDecimal zeroServiceAmount = new BigDecimal(0);
		    BigDecimal zeroDecideAmount = new BigDecimal(0);
		    one.setServiceAmount(zeroServiceAmount);
		    one.setDecideAmount(zeroDecideAmount);
		    one.setName("汇总");
		    for(YaohaoWinbid t:countList){
		    	one.setServiceAmount(t.getServiceAmount()!=null?one.getServiceAmount().add(t.getServiceAmount()):one.getServiceAmount());
		    	one.setDecideAmount(t.getDecideAmount()!=null?one.getDecideAmount().add(t.getDecideAmount()):one.getDecideAmount());
		    }
		    footer.add(one);
			result.setFooter(footer);
//			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @Description: 关联合同
	 * @param @param request
	 * @param @return   
	 * @return GlobalResult  
	 * @throws
	 * @author lixiang
	 * @date 2019-8-29下午3:30:55
	 * @Company  广东华联软件科技有限公司
	 */
	@ResponseBody
	@RequestMapping("/containContract")
	public GlobalResult containContract(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			yaohaoWinbidService.containContract(request);
			result.setStatus(200);
		} catch (Exception e) {
			result.setStatus(500);
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	//导出中签记录
	@ResponseBody
	@RequestMapping("/exportYaohaoWinbid")
	public void exportYaohaoOrder(HttpServletRequest request,HttpServletResponse response){
		List<YaohaoWinbid> list =new ArrayList<YaohaoWinbid>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					YaohaoWinbid winbid=yaohaoWinbidService.selectByPrimaryKey(id);
					list.add(winbid);
				}
			}else{	
//				list= yaohaoWinbidService.selectListByMap(null);
				list=Global.WINBID_EXPORT_LIST;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String path=request.getSession().getServletContext().getRealPath("/template/report/yaohaowinbid_template.xlsx");
			String newPath="";
			String dateString = formatter.format(new Date());
			newPath=request.getSession().getServletContext().getRealPath("/template/temp/中签记录_"+dateString+".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>(); 
			List<String> cellList=new ArrayList<String>();
			for(YaohaoWinbid l:list){
				CostEnterprise  e=costEnterpriseService.selectByCostEnterpriseCode(l.getEnterpriseCode());
				if(null!=e){
					l.setEnterpriseName(e.getName());
				}
				cellList =new ArrayList<String>();
				cellList.add(l.getOrderCode());
				cellList.add(l.getYaohaoGrade());
				cellList.add(l.getEnterpriseName());
				cellList.add(null==l.getAssessResultStr()|| "".equals(l.getAssessResultStr())?"未评定":l.getAssessResultStr());
				cellList.add(l.getYaohaoTimeStr());
				cellList.add(null!=l.getServiceAmount()?l.getServiceAmount().toString():"");
				cellList.add(null!=l.getDecideAmount()?l.getDecideAmount().toString():"");
				cellList.add(null!=l.getSettlementAmount()?l.getSettlementAmount().toString():"");
				cellList.add(l.getName());
				cellList.add(l.getCreater());
				rowList.add(cellList);
			}
			date.put("中签记录", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//导出中签记录
	@ResponseBody
	@RequestMapping("/erExportYaohaoWinbid")
	public void erExportYaohaoOrder(HttpServletRequest request,HttpServletResponse response){
		List<YaohaoWinbid> list =new ArrayList<YaohaoWinbid>();
		try {
			String ids=request.getParameter("ids");
			String code=request.getParameter("code");
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				}else{
//				list= yaohaoWinbidService.selectListByMap(null);
				list=Global.WINBID_EXPORT_LIST;
			}
			CostEnterprise c=costEnterpriseService.selectByCostEnterpriseCode(code);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String path=request.getSession().getServletContext().getRealPath("/template/report/eryaohaowinbid_template.xlsx");
			String newPath="";
			String dateString = formatter.format(new Date());
			newPath=request.getSession().getServletContext().getRealPath("/template/temp/服务项目统计_"+dateString+".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(YaohaoWinbid l:list){
				CostEnterprise  e=costEnterpriseService.selectByCostEnterpriseCode(l.getEnterpriseCode());
				if(null!=e){
					l.setEnterpriseName(e.getName());
				}
				cellList =new ArrayList<String>();
				cellList.add(c.getName());
				cellList.add(l.getOrderCode());
				cellList.add(l.getName());
				cellList.add(l.getYaohaoGrade());
				cellList.add(l.getYaohaoTimeStr());
				cellList.add(null!=l.getServiceAmount()?l.getServiceAmount().toString():"");
				cellList.add(null!=l.getDecideAmount()?l.getDecideAmount().toString():"");
				cellList.add(null!=l.getSettlementAmount()?l.getSettlementAmount().toString():"");
				cellList.add(l.getCreater());
				rowList.add(cellList);
			}
			date.put("中签记录", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
