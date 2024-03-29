package com.cost168.costaudit.controller.cost;

import com.cost168.costaudit.pojo.cost.*;
import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.service.cost.*;
import com.cost168.costaudit.service.sys.SysOrgService;
import com.cost168.costaudit.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * ClassName: CostPriceLibraryController 
 * @Description: TODO
 * @author lixiang
 * @date 2019-2-18上午8:56:03
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/costPriceLibrary")
public class CostPriceLibraryController {
	
	
	@Autowired
	private CostPriceLibraryService costPriceLibraryService;
	@Autowired
	private CostAttachmentService costAttachmentService;
	@Autowired
	private CostTaskService costTaskService;
	@Autowired
	private CostProjectService costProjectService;
	@Autowired
	private CostContractService costContractService;
	@Autowired
	private SysOrgService sysOrgService;
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,CostPriceLibrary costPriceLibrary, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			//SysUser currentUser=shiroUtil.getInstance().currentUser();
			String startTime =request.getParameter("createTimeStart");
			String endTime =request.getParameter("createTimeEnd");
			String typeLibraryAll=request.getParameter("typeLibraryAll");
			if(costPriceLibrary.getTypeLibrary()==null || "".equals(costPriceLibrary.getTypeLibrary())){
				costPriceLibrary.setTypeLibrary(typeLibraryAll);
			}
			Map<String,Object> selectMap=JsonUtils.object2Map(costPriceLibrary);
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
				Global.PRI_END_TIME = startTime;
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
				Global.PRI_END_TIME = endTime;
			}
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    List<CostPriceLibrary> list= costPriceLibraryService.selectListByMap(map);
		    Global.PRI_EXPORT_LIST = list;
		    int total= costPriceLibraryService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public GlobalResult del(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			for(String i:arr){
				costPriceLibraryService.deleteByPrimaryKey(i);
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/importTemplateZc")
	public void importTemplateZc(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/新增主材单价库导入模版.xlsx");
			String fileName="新增主材单价库导入模版.xlsx";
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
	@RequestMapping("/importTemplateZh")
	public void importTemplateZh(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/新增项目综合单价导入模版.xlsx");
			try {
				ExcelUtil.downLoad(path, "新增项目综合单价导入模版.xlsx", request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping("/importPriceLibrary")
	public GlobalResult importPriceLibrary(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try{
			    String attId=request.getParameter("id");
			    String taskId=request.getParameter("taskId");
			    CostTask task = costTaskService.selectByPrimaryKey(taskId);
			    CostProject project= costProjectService.selectByPrimaryKey(task.getProjectId());
			    CostContract contract= costContractService.selectByPrimaryKey(task.getContractId());
				Properties props = new Properties();
				props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
				String path=(String) props.get("fileupload");
				CostAttachment  att=costAttachmentService.selectByPrimaryKey(attId);
				path=path.substring(0,2)+att.getUrl();
				//读取exc
				HashMap<String, HashMap<Integer, ArrayList<Object>>> excelMap = ExcelUtil.readExcel(2,path, 0);
				//遍历excel的sheet
				for(Entry<String, HashMap<Integer, ArrayList<Object>>> excel : excelMap.entrySet()){
					//取出一个sheet内容
					HashMap<Integer, ArrayList<Object>> sheetMap = excel.getValue();
					ArrayList<Object> row = null;
					CostPriceLibrary costPriceLibrary=null;
					//遍历一个sheet里的每行数据
					for(Entry<Integer, ArrayList<Object>> sheet : sheetMap.entrySet()){
						row = sheet.getValue();
						//System.out.println(row.get(1)+"------"+row.get(2));
						/*CostPriceLibrary pl=costPriceLibraryService.selectByName((String)row.get(0));
						if(null!=pl){
							continue;
						}*/
						costPriceLibrary=new CostPriceLibrary();
						String id=UUID.randomUUID().toString().replace("-", "");
						costPriceLibrary.setId(id);
						costPriceLibrary.setTaskId(taskId);
						costPriceLibrary.setTaskName(task.getName());
						costPriceLibrary.setTaskCode(task.getCode());
						costPriceLibrary.setTaskPersonLiable(task.getPersonLiable());
						costPriceLibrary.setProjectId(project.getId());
						costPriceLibrary.setProjectName(project.getName());
						if(null!=contract){
							costPriceLibrary.setContractId(contract.getId());
							costPriceLibrary.setContractName(contract.getName());
						}
						if(null!=contract && null!=contract.getExecutiveDepartment()){
							SysOrg org=sysOrgService.selectByPrimaryKey(contract.getExecutiveDepartment());
							if(null!=org.getName()){
								costPriceLibrary.setContractExeDepartment(org.getName());
							}
						}
						if("主材定价".equals(task.getAuditPriceType())){
							costPriceLibrary.setTypeLibrary("主材定价");
							costPriceLibrary.setName(null!=row.get(0)?row.get(0).toString():null);
							costPriceLibrary.setFeature(null!=row.get(1)?row.get(1).toString():null);
							costPriceLibrary.setEngineeringNumber(null!=row.get(2)?row.get(2).toString().replace(".00", ""):null);
							costPriceLibrary.setUsePosition(null!=row.get(3)?row.get(3).toString():null);
							costPriceLibrary.setBasis(null!=row.get(4)?row.get(4).toString():null);
							costPriceLibrary.setBiddingBrand(null!=row.get(5)?row.get(5).toString():null);
							costPriceLibrary.setUseBrand(null!=row.get(6)?row.get(6).toString():null);
							costPriceLibrary.setUnit(null!=row.get(7)?row.get(7).toString():null);
							String contractingPrice=row.get(8).toString();
							if(null!=contractingPrice && !"".equals(contractingPrice)){
								BigDecimal bdContractingPrice=new BigDecimal(contractingPrice); 
								bdContractingPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								costPriceLibrary.setContractingPrice(bdContractingPrice);
							}
							String supervisorPrice=row.get(9).toString();
							if(null!=supervisorPrice && !"".equals(supervisorPrice)){
								BigDecimal bdSupervisorPrice=new BigDecimal(supervisorPrice); 
								bdSupervisorPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								costPriceLibrary.setSupervisorPrice(bdSupervisorPrice);
							}
							String settlementPrice=row.get(10).toString();
							if(null!=settlementPrice && !"".equals(settlementPrice)){
								BigDecimal bdSettlementPrice=new BigDecimal(settlementPrice); 
								bdSettlementPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								costPriceLibrary.setSettlementPrice(bdSettlementPrice);
							}
							costPriceLibrary.setDescription(row.get(11).toString());
						}
						if("综合单价".equals(task.getAuditPriceType())){
							costPriceLibrary.setTypeLibrary("综合单价");
							costPriceLibrary.setCode(null!=row.get(0)?row.get(0).toString().replace(".00", ""):null);
							costPriceLibrary.setName(null!=row.get(1)?row.get(1).toString():null);
							costPriceLibrary.setFeature(null!=row.get(2)?row.get(2).toString():null);
							costPriceLibrary.setEngineeringNumber(null!=row.get(3)?row.get(3).toString().replace(".00", ""):null);
							costPriceLibrary.setBasis(null!=row.get(4)?row.get(4).toString():null);
							costPriceLibrary.setUnit(null!=row.get(5)?row.get(5).toString():null);
							String contractingPrice=row.get(6).toString();
							if(null!=contractingPrice && !"".equals(contractingPrice)){
								BigDecimal bdContractingPrice=new BigDecimal(contractingPrice); 
								bdContractingPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								costPriceLibrary.setContractingPrice(bdContractingPrice);
							}
							String supervisorPrice=row.get(7).toString();
							if(null!=supervisorPrice && !"".equals(supervisorPrice)){
								BigDecimal bdSupervisorPrice=new BigDecimal(supervisorPrice); 
								bdSupervisorPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								costPriceLibrary.setSupervisorPrice(bdSupervisorPrice);
							}
							String settlementPrice=row.get(8).toString();
							if(null!=settlementPrice && !"".equals(settlementPrice)){
								BigDecimal bdSettlementPrice=new BigDecimal(settlementPrice); 
								bdSettlementPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								costPriceLibrary.setSettlementPrice(bdSettlementPrice);
							}
							costPriceLibrary.setDescription(row.get(9).toString());
						}
						
						costPriceLibrary.setCreateTime(new Date());
						costPriceLibraryService.insertSelective(costPriceLibrary);
					}
				}
				//附件更新状态已经入库
				att.setCategory("已入库");
				costAttachmentService.updateByPrimaryKeySelective(att);
			result.setStatus(200);
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	//综合单价库导出
	@ResponseBody
	@RequestMapping("/exportPriceLibraryZh")
	public void exportPriceLibraryZh(HttpServletRequest request,HttpServletResponse response){
		try {
			String ids=request.getParameter("id");
			List<CostPriceLibrary> list =new ArrayList<CostPriceLibrary>();
			if(ids!=null && !"".equals(ids)){
				String arrIds[]= ids.split(",");
				for(String id:arrIds){
					CostPriceLibrary pl=costPriceLibraryService.selectByPrimaryKey(id);
					if(null!=pl){
						list.add(pl);
					}
				}
			}else{
				 list = Global.PRI_EXPORT_LIST;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = Global.PRI_START_TIME;
			String endTime = Global.PRI_END_TIME;
			String path=request.getSession().getServletContext().getRealPath("/template/report/comprehensive_template.xlsx");
			String newPath="";
			if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/综合单价库_"+startTime+"-"+endTime+".xlsx");
			}
			else{
				String dateString = formatter.format(new Date());
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/综合单价库_"+dateString+".xlsx");
			}
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>(); 
			List<String> cellList=new ArrayList<String>();
			for(CostPriceLibrary l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getCode());
				cellList.add(l.getName());
				cellList.add(l.getFeature());
				cellList.add(l.getEngineeringNumber());
				cellList.add(l.getBasis());
				cellList.add(l.getUnit());
				cellList.add(l.getContractingPrice()!=null?l.getContractingPrice()+"":"");
				cellList.add(l.getSupervisorPrice()!=null?l.getSupervisorPrice()+"":"");
				cellList.add(l.getSettlementPrice()!=null?l.getSettlementPrice()+"":"");
				cellList.add(l.getDescription());
				cellList.add(l.getProjectName());
				cellList.add(l.getContractName());
				cellList.add(l.getContractExeDepartment());
				cellList.add(l.getTaskCode());
				cellList.add(l.getTaskPersonLiable());
				cellList.add(l.getCreateTime()!=null?formatter.format(l.getCreateTime()):"");
				rowList.add(cellList);
			}
			date.put("综合单价库", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//主材单价库导出
	@ResponseBody
	@RequestMapping("/exportPriceLibraryZc")
	public void exportPriceLibraryZc(HttpServletRequest request,HttpServletResponse response){
		try {
			String ids=request.getParameter("id");
			List<CostPriceLibrary> list =new ArrayList<CostPriceLibrary>();
			if(ids!=null && !"".equals(ids)){
				String arrIds[]= ids.split(",");
				for(String id:arrIds){
					CostPriceLibrary pl=costPriceLibraryService.selectByPrimaryKey(id);
					if(null!=pl){
						list.add(pl);
					}
				}
			}else{
				 list = Global.PRI_EXPORT_LIST;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = Global.PRI_START_TIME;
			String endTime = Global.PRI_END_TIME;
			String path=request.getSession().getServletContext().getRealPath("/template/report/material_template.xlsx");
			String newPath="";
			if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/主材单价库_"+startTime+"-"+endTime+".xlsx");
			}
			else{
				String dateString = formatter.format(new Date());
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/主材单价库_"+dateString+".xlsx");
			}
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>(); 
			List<String> cellList=new ArrayList<String>();
			for(CostPriceLibrary l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getName());
				cellList.add(l.getFeature());
				cellList.add(l.getEngineeringNumber());
				cellList.add(l.getUsePosition());
				cellList.add(l.getBasis());
				cellList.add(l.getBiddingBrand());
				cellList.add(l.getUseBrand());
				cellList.add(l.getUnit());
				cellList.add(l.getContractingPrice()!=null?l.getContractingPrice()+"":"");
				cellList.add(l.getSupervisorPrice()!=null?l.getSupervisorPrice()+"":"");
				cellList.add(l.getSettlementPrice()!=null?l.getSettlementPrice()+"":"");
				cellList.add(l.getDescription());
				cellList.add(l.getProjectName());
				cellList.add(l.getContractName());
				cellList.add(l.getContractExeDepartment());
				cellList.add(l.getTaskCode());
				cellList.add(l.getTaskPersonLiable());
				cellList.add(l.getCreateTime()!=null?formatter.format(l.getCreateTime()):"");
				rowList.add(cellList);
			}
			date.put("主材单价库", rowList);
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
	@RequestMapping("/uploadZh")
	public void uploadZh(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/变更-签证综合单价导入模版.xlsx");
			String fileName="变更-签证综合单价导入模版.xlsx";
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
	@RequestMapping("/uploadZc")
	public void uploadZc(HttpServletRequest request,HttpServletResponse response){
		try {
			String path=request.getSession().getServletContext().getRealPath("/template/report/变更-签证主材单价导入模版.xlsx");
			String fileName = "变更-签证主材单价导入模版.xlsx";
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
	
	/**
	 * 
	 * @Description: 导入
	 * @param @param request
	 * @param @return   
	 * @return GlobalResult  
	 * @throws
	 * @author lixiang
	 * @date 2019-3-14下午3:56:48
	 * @Company  广东华联软件科技有限公司
	 */
	@ResponseBody
	@RequestMapping("importZh")
	public GlobalResult importZh(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String flagType=request.getParameter("flagType");
		try {
			List<CostPriceLibrary> costPriceLibrary = costPriceLibraryService.importZh(request,flagType);
			if(null!=costPriceLibrary && costPriceLibrary.size()>0){
				result.setData(costPriceLibrary);
				result.setStatus(300);
			}else{
				result.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	
}
