package com.cost168.costaudit.controller.cost;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.mapper.cost.CostTaskMapper;
import com.cost168.costaudit.pojo.cost.CostAttachment;
import com.cost168.costaudit.pojo.cost.CostContract;
import com.cost168.costaudit.pojo.cost.CostDocument;
import com.cost168.costaudit.pojo.cost.CostDpctRelation;
import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.pojo.cost.CostTaskExample;
import com.cost168.costaudit.pojo.cost.CostTaskExample.Criteria;
import com.cost168.costaudit.pojo.cost.CostTaskOpinion;
import com.cost168.costaudit.pojo.cost.CostTaskType;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostAttachmentService;
import com.cost168.costaudit.service.cost.CostContractService;
import com.cost168.costaudit.service.cost.CostDocumentService;
import com.cost168.costaudit.service.cost.CostDpctRelationService;
import com.cost168.costaudit.service.cost.CostProjectService;
import com.cost168.costaudit.service.cost.CostTaskOpinionService;
import com.cost168.costaudit.service.cost.CostTaskService;
import com.cost168.costaudit.service.cost.CostTaskTypeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.ExcelUtil;
import com.cost168.costaudit.utils.Global;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.utils.JsonUtils;

/**
 *
 * ClassName: CostTaskController
 * @Description: 审价任务
 * @author lixiang
 * @date 2018-12-10下午2:02:20
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/costTask")
public class CostTaskController {

	private static final Logger logger = LoggerFactory.getLogger(CostTaskController.class);
	 
	@Autowired
	private CostTaskService costTaskService;
	@Autowired
	private CostTaskTypeService costTaskTypeService;
	@Autowired
	private CostProjectService costProjectService;
	@Autowired
	private CostContractService costContractService;
	@Autowired
	private CostDocumentService costDocumentService;
	@Autowired
	private CostDpctRelationService costDpctRelationService;
	@Autowired
	private CostAttachmentService costAttachmentService;
	@Autowired
	private CostTaskMapper costTaskMapper;
	@Autowired
	private CostTaskOpinionService costTaskOpinionService;

	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,CostTask costTask, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		String self=request.getParameter("self");
		String auditPriceTypeList= request.getParameter("auditPriceTypeList");
		try {
			String shenTime =request.getParameter("shenTime");
			String startTime =request.getParameter("startTime");
			String endTime =request.getParameter("endTime");
			//用于从合同关联任务
			if(null!=costTask.getContractCongId() && !"".equals(costTask.getContractCongId()) && "flag".equals(costTask.getContractCongId())){
				costTask.setContractCongId(null);
				map.put("noContractCongId", "noContractCongId");
			}
			Map<String,Object> selectMap=JsonUtils.object2Map(costTask);
			if(auditPriceTypeList!=null  && !"".equals(auditPriceTypeList)){
				String arr[] = auditPriceTypeList.split(",");
	  			map.put("auditPriceTypeList", arr);
	  		}
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
				Global.TAS_END_TIME = startTime;
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
				Global.TAS_END_TIME = endTime;
			}
			map.put("shenTime", shenTime);
			map.put("dataType","shending");
			map.put("suffix","文件夹");
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    /*if(null!=self && !"".equals(self)){
		    	map.put("currentPerson", currentUser.getName());
		    }*/
		    List<CostTask> list= costTaskService.selectListByMap(map);
		    Global.TAS_EXPORT_LIST = list;
		    int total= costTaskService.selectCountByMap(map);
		    //汇总
		    List<CostTask> footer = new ArrayList<CostTask>();
		    CostTask one=new CostTask();
		    int giveNumber=0;//送审条数
		    int auditNumber=0;//审核条数
		    BigDecimal zero = new BigDecimal(0);
		    one.setAgencyAuditAmount(zero);
		    one.setDecideAmount(zero);
		    one.setCode("汇总");
		    one.setCategory("不需汇总");
		    for(CostTask t:list){
		    	one.setGiveNumber(giveNumber+=t.getGiveNumber()!=null?t.getGiveNumber():0);
		    	one.setAuditNumber(auditNumber+=t.getAuditNumber()!=null?t.getAuditNumber():0);
		    	one.setAgencyAuditAmount(t.getAgencyAuditAmount()!=null?one.getAgencyAuditAmount().add(t.getAgencyAuditAmount()):one.getAgencyAuditAmount());
		    	one.setDecideAmount(t.getDecideAmount()!=null?one.getDecideAmount().add(t.getDecideAmount()):one.getDecideAmount());
		    }
		    footer.add(one);
			result.setFooter(footer);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/docList")
	public EUDataGridResult docList(HttpServletRequest request,int page, int rows){
		EUDataGridResult result = new EUDataGridResult();
		try {
			String taskId = request.getParameter("taskId");
			Map<String,Object> map= new HashMap<String, Object>();
			map.put("taskId", taskId);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
			List<CostDocument> list = costDocumentService.selectRelationDocListByMap(map);
			int total = costDocumentService.selectRelationDocCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//第二种方式
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request){
		String url="";
		String result="";
		String auditPriceType=request.getParameter("auditPriceType");
		List<CostTaskType> taskTypeList=null;
		String dpctRelationId=UUID.randomUUID().toString().replace("-", "");
		if("ggyj".equals(auditPriceType)){
			CostTaskType ggyj=costTaskTypeService.getCostTaskTypeByName("估概预结");
			if(null!=ggyj){
				taskTypeList =costTaskTypeService.getChilds(ggyj);
			}
			result="task/estimatePrefix-add2";
		}else if("htbg".equals(auditPriceType)){
			CostTaskType htbg=costTaskTypeService.getCostTaskTypeByName("合同变更");
			if(null!=htbg){
				taskTypeList =costTaskTypeService.getChilds(htbg);
			}
			result="task/contractChange-add2";
		}else if("djsh".equals(auditPriceType)){
			CostTaskType djsh=costTaskTypeService.getCostTaskTypeByName("单价审核");
			if(null!=djsh){
				taskTypeList =costTaskTypeService.getChilds(djsh);
			}
			result="task/priceCheck-add2";
		}else{
			result="task/estimatePrefix-add2";
		}
		request.setAttribute("taskTypeList", taskTypeList);
		request.setAttribute("dpctRelationId", dpctRelationId);
		String taskId=UUID.randomUUID().toString().replace("-", "");
		CostTask costTask=new CostTask();
		costTask.setId(taskId);
		//costTask.setCode(getNewCode(null));
		url="/costTask/save2";
		request.setAttribute("obj", costTask);
		request.setAttribute("url", url);
		return result;
	}
	//第二种方式
	@ResponseBody
	@RequestMapping("/save2")
	public GlobalResult save2(HttpServletRequest request,CostTask costTask){
		GlobalResult result=new GlobalResult();
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		String dpctRelationId= request.getParameter("dpctRelationId");
		String receiveTimeStr=request.getParameter("receiveTimeStr");
		String decideTimeStr=request.getParameter("decideTimeStr");
		String deliveryTimeStr=request.getParameter("deliveryTimeStr");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			//判断名称是否重复
			map.put("noLikeName", costTask.getName());
			List<CostTask> t=costTaskService.selectListByMap(map);
			if(null!=t && t.size()>0){
				result.setStatus(600);
				return result;
			}
			//
			//判断名称是否重复
			map.clear();
			map.put("noLikeCode", costTask.getCode());
			List<CostTask> t_code=costTaskService.selectListByMap(map);
			if(null!=t_code && t_code.size()>0){
				result.setStatus(800);
				return result;
			}
			//判断是否有结算审核任务
			if("结算审核".equals(costTask.getAuditPriceType())){
				map.clear();
				String conId=costTask.getContractId();
				map.put("contractId", conId);
				map.put("auditPriceType", "结算审核");
				if(null!=conId && !"".equals(conId)){
					List<CostTask> tList=costTaskService.selectListByMap(map);
					if(null!=tList && tList.size()>0){
						result.setStatus(900);
						return result;
					}
				}
			}
			map.clear();
			//插入文件项目合同任务的关联表
			CostDpctRelation dpct=new CostDpctRelation();
			dpct.setId(dpctRelationId);
			dpct.setDocumentId(costTask.getDocumentId());
			dpct.setProjectId(costTask.getProjectId());
			dpct.setContractId(costTask.getContractId());
			dpct.setTaskId(costTask.getId());
			//插入审价任务表
			if(receiveTimeStr!=null && !"".equals(receiveTimeStr)){
				costTask.setReceiveTime(sdf.parse(receiveTimeStr));
			}
			if(decideTimeStr!=null && !"".equals(decideTimeStr)){
				costTask.setDecideTime(sdf.parse(decideTimeStr));
			}
			if(deliveryTimeStr!=null && !"".equals(deliveryTimeStr)){
				costTask.setDeliveryTime(sdf.parse(deliveryTimeStr));
			}
			costTask.setCreater(currentUser.getName());
			costTaskService.insertSelectiveMap(costTask,dpct,map);
			//保存审核历史记录表
			saveTaskOption(costTask, currentUser);
			//在附件表新增文件夹名称（新增主材单价审批表）
			if(costTask.getAuditPriceType()!=null && ("主材定价".equals(costTask.getAuditPriceType()) || "综合单价".equals(costTask.getAuditPriceType()))){
				CostAttachment att=new CostAttachment();
				att.setId(UUID.randomUUID().toString().replace("-", ""));
				att.setTypeId(costTask.getId());
				if("主材定价".equals(costTask.getAuditPriceType())){
					att.setName("新增主材单价审批表");
				}else{
					att.setName("换算（合同外）新增项目综合单价");
				}
				att.setSuffix("文件夹");
				att.setDataType("shending");
				att.setCreater(currentUser.getName());
				att.setCreaterTime(new Date());
				costAttachmentService.insertSelective(att);
			}
			//项目的概算金额读取概算审核任务里的数据
			if("概算审核".equals(costTask.getAuditPriceType())){
				CostProject project = costProjectService.selectByPrimaryKey(costTask.getProjectId());
				Map<String,Object> map1=new HashMap<String,Object>();
				map1.put("projectId", project.getId());
				map1.put("auditPriceType","概算审核");
				List<CostTask> taskList = costTaskService.selectListByMap(map1);
				for(CostTask task:taskList){
					project.setGsGcf(task.getGcfAmount()!=null?project.getGsGcf().add(task.getGcfAmount()):project.getSumGsJe());
					project.setGsElfy(task.getElfyAmount()!=null?project.getGsElfy().add(task.getElfyAmount()):project.getGsElfy());
					project.setGsSlfy(task.getSlfyAmount()!=null?project.getGsSlfy().add(task.getSlfyAmount()):project.getGsSlfy());
					project.setSumGsJe(task.getDecideAmount()!=null?project.getSumGsJe().add(task.getDecideAmount()):project.getSumGsJe());
				}
				costProjectService.updateByPrimaryKeySelective(project);
			}
			//项目汇总
			sumProjAndCon(costTask);
			//在审价类型为招标清单/控制价审核
			/*if(costTask.getAuditPriceType()!=null && ("招标清单/控制价审核".equals(costTask.getAuditPriceType()))){
				Calendar calendar = Calendar.getInstance();
		        calendar.add (Calendar.SECOND, 1);
				CostAttachment att=new CostAttachment();
				String rootId=UUID.randomUUID().toString().replace("-", "");
				att.setId(rootId);
				att.setTypeId(costTask.getId());
			    att.setName("控制价附件");
				att.setSuffix("文件夹");
				att.setDataType("songshen");
				att.setCreater(currentUser.getName());
				att.setCreaterTime(calendar.getTime());
				costAttachmentService.insertSelective(att);
				CostAttachment att2=new CostAttachment();
				calendar.add (Calendar.SECOND, 2);
				att2.setId(UUID.randomUUID().toString().replace("-", ""));
				att2.setPid(rootId);
				att2.setTypeId(costTask.getId());
			    att2.setName("招标文件");
				att2.setSuffix("文件夹");
				att2.setDataType("songshen");
				att2.setCreater(currentUser.getName());
				att2.setCreaterTime(calendar.getTime());
				costAttachmentService.insertSelective(att2);
				CostAttachment att3=new CostAttachment();
				calendar.add (Calendar.SECOND, 3);
				att3.setId(UUID.randomUUID().toString().replace("-", ""));
				att3.setPid(rootId);
				att3.setTypeId(costTask.getId());
			    att3.setName("澄清");
				att3.setSuffix("文件夹");
				att3.setDataType("songshen");
				att3.setCreater(currentUser.getName());
				att3.setCreaterTime(calendar.getTime());
				costAttachmentService.insertSelective(att3);
				CostAttachment att4=new CostAttachment();
				calendar.add (Calendar.SECOND, 4);
				att4.setId(UUID.randomUUID().toString().replace("-", ""));
				att4.setPid(rootId);
				att4.setTypeId(costTask.getId());
			    att4.setName("控制价");
				att4.setSuffix("文件夹");
				att4.setDataType("songshen");
				att4.setCreater(currentUser.getName());
				att4.setCreaterTime(calendar.getTime());
				costAttachmentService.insertSelective(att2);
				CostAttachment att5=new CostAttachment();
				calendar.add (Calendar.SECOND, 5);
				att5.setId(UUID.randomUUID().toString().replace("-", ""));
				att5.setPid(rootId);
				att5.setTypeId(costTask.getId());
			    att5.setName("清单");
				att5.setSuffix("文件夹");
				att5.setDataType("songshen");
				att5.setCreater(currentUser.getName());
				att5.setCreaterTime(calendar.getTime());
				costAttachmentService.insertSelective(att5);
			}*/
			//saveCode(costTask.getCode());
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}



	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String url="";
		String result="";
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			url="/costTask/upd";
			CostTask costTask = costTaskService.selectByPrimaryKey(id);
			if(null!=costTask.getContractId() && !"".equals(costTask.getContractId())){
				CostContract contract=costContractService.selectByPrimaryKey(costTask.getContractId());
				if(null!=contract){
					costTask.setContractType(contract.getContractType());
					costTask.setContractAmount(contract.getMainFlagAmount());
					costTask.setContractName(contract.getName());
				}
			}
			//调取审核历史记录表
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("taskId", costTask.getId());
			map.put("status", costTask.getStatus());
			List<CostTaskOpinion> toList=costTaskOpinionService.selectListByMap(map);
			if(null!=toList && toList.size()>0){
				costTask.setCheckExplain(toList.get(0).getOpinion());
			}
			if("估概预结".equals(costTask.getAuditPriceTypecn())){
				result="task/estimatePrefix-add";
			}else if("合同变更".equals(costTask.getAuditPriceTypecn()) || "设计图纸变更".equals(costTask.getAuditPriceTypecn())){
				result="task/contractChange-add";
			}else if("单价审核".equals(costTask.getAuditPriceTypecn())){
				result="task/priceCheck-add";
			}else{
				result="task/estimatePrefix-add";
			}
			request.setAttribute("obj", costTask);
		}else{
			String taskId=UUID.randomUUID().toString().replace("-", "");
			CostTask costTask=new CostTask();
			costTask.setId(taskId);
			costTask.setCode(getNewCode(null));
			url="/costTask/save";
			request.setAttribute("obj", costTask);
		}
		request.setAttribute("url", url);
		return result;
	}
	//第一种方式
	@RequestMapping("/toAddTask")
	public String toAddTask(HttpServletRequest request){
		String result="";
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		String id=UUID.randomUUID().toString().replace("-", "");
		CostTask costTask=new CostTask();
		costTask.setCreater(currentUser.getName());
		costTask.setId(id);
		String typeId=request.getParameter("typeId");
		String typePid=request.getParameter("typePid");
		String documentId=request.getParameter("documentId");
		//用来判读审价任务的接收时间要大于往来文件时间
		CostDocument document=costDocumentService.selectByPrimaryKey(documentId);
		if(null!=document){
			request.setAttribute("document", document);
		}
		costTask.setDocumentId(documentId);
		costTask.setName(document.getName());
		costTask.setCode(document.getSymbol());
		String dpctRelationId=request.getParameter("dpctRelationId");
		CostDpctRelation dpct=costDpctRelationService.selectByPrimaryKey(dpctRelationId);
		CostProject project=costProjectService.selectByPrimaryKey(dpct.getProjectId());
		costTask.setProjectId(project.getId());
		costTask.setAuditPriceUnit(project.getAuditPriceUnit());
		costTask.setProjectName(project.getName());
		CostContract contract =costContractService.selectByPrimaryKey(dpct.getContractId());
		if(null!=contract){
			costTask.setContractId(contract.getId());
			costTask.setContractName(contract.getName());
			costTask.setContractType(contract.getContractType());
			costTask.setContractName(contract.getName());
			costTask.setContractAmount(contract.getMainFlagAmount());
		}
		costTask.setProjectName(project.getName());
		CostTaskType costTaskType =costTaskTypeService.selectByPrimaryKey(typeId);
		CostTaskType costTaskTypePar =costTaskTypeService.selectByPrimaryKey(typePid);
		costTask.setAuditPriceType(costTaskType.getName());
		if("估概预结".equals(costTaskTypePar.getName())){
			costTask.setAuditPriceTypecn("估概预结");
			result="task/estimatePrefix-add";
		}else if("设计图纸变更".equals(costTaskTypePar.getName())||"合同变更".equals(costTaskTypePar.getName())||"费用变更".equals(costTaskTypePar.getName())){
			costTask.setAuditPriceTypecn(costTaskTypePar.getName());
			result="task/contractChange-add";
		}else if("单价审核".equals(costTaskTypePar.getName())){
			costTask.setAuditPriceTypecn("单价审核");
			result="task/priceCheck-add";
		}
		request.setAttribute("dpctRelationId", dpctRelationId);
		request.setAttribute("url", "/costTask/save");
		costTask.setStatus("新建");
		request.setAttribute("obj", costTask);
		return result;
	}
	//第一种方式
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,CostTask costTask){
		GlobalResult result=new GlobalResult();
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		String dpctRelationId= request.getParameter("dpctRelationId");
		String receiveTimeStr=request.getParameter("receiveTimeStr");
		String decideTimeStr=request.getParameter("decideTimeStr");
		String deliveryTimeStr=request.getParameter("deliveryTimeStr");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//判断是否重复
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("noLikeName", costTask.getName());
		List<CostTask> t=costTaskService.selectListByMap(map);
		if(null!=t && t.size()>0){
			result.setStatus(600);
			return result;
		}
		//判断编号是否重复
		map.clear();
		map.put("noLikeCode", costTask.getCode());
		List<CostTask> t_code=costTaskService.selectListByMap(map);
		if(null!=t_code && t_code.size()>0){
			result.setStatus(800);
			return result;
		}
		//判断是否有结算审核任务
		if("结算审核".equals(costTask.getAuditPriceType())){
			map.clear();
			String conId=costTask.getContractId();
			map.put("contractId", conId);
			map.put("auditPriceType", "结算审核");
			if(null!=conId && !"".equals(conId)){
				List<CostTask> tList=costTaskService.selectListByMap(map);
				if(null!=tList && tList.size()>0){
					result.setStatus(900);
					return result;
				}
			}
		}
		map.clear();
		CostDpctRelation dpct=costDpctRelationService.selectByPrimaryKey(dpctRelationId);
		dpct.setTaskId(costTask.getId());
		try {
			costDpctRelationService.updateByPrimaryKeySelective(dpct);
			if(receiveTimeStr!=null && !"".equals(receiveTimeStr)){
				costTask.setReceiveTime(sdf.parse(receiveTimeStr));
			}
			if(decideTimeStr!=null && !"".equals(decideTimeStr)){
				costTask.setDecideTime(sdf.parse(decideTimeStr));
			}
			if(deliveryTimeStr!=null && !"".equals(deliveryTimeStr)){
				costTask.setDeliveryTime(sdf.parse(deliveryTimeStr));
			}
			costTask.setCreater(currentUser.getName());
			costTaskService.insertSelective(costTask);
			//保存历史记录表
			saveTaskOption(costTask, currentUser);
			//在附件表新增文件夹名称（新增主材单价审批表）
			if(costTask.getAuditPriceType()!=null && ("主材定价".equals(costTask.getAuditPriceType()) || "综合单价".equals(costTask.getAuditPriceType()))){
				CostAttachment att=new CostAttachment();
				att.setId(UUID.randomUUID().toString().replace("-", ""));
				att.setTypeId(costTask.getId());
				if("主材定价".equals(costTask.getAuditPriceType())){
					att.setName("新增主材单价审批表");
				}else{
					att.setName("换算（合同外）新增项目综合单价");
				}
				att.setSuffix("文件夹");
				att.setDataType("shending");
				att.setCreater(currentUser.getName());
				att.setCreaterTime(new Date());
				costAttachmentService.insertSelective(att);
			}
			//项目的概算金额读取概算审核任务里的数据
			if("概算审核".equals(costTask.getAuditPriceType())){
				CostProject project = costProjectService.selectByPrimaryKey(costTask.getProjectId());
				Map<String,Object> map1=new HashMap<String,Object>();
				map1.put("projectId", project.getId());
				map1.put("auditPriceType","概算审核");
				List<CostTask> taskList = costTaskService.selectListByMap(map1);
				for(CostTask task:taskList){
					project.setGsGcf(task.getGcfAmount()!=null?project.getGsGcf().add(task.getGcfAmount()):project.getSumGsJe());
					project.setGsElfy(task.getElfyAmount()!=null?project.getGsElfy().add(task.getElfyAmount()):project.getGsElfy());
					project.setGsSlfy(task.getSlfyAmount()!=null?project.getGsSlfy().add(task.getSlfyAmount()):project.getGsSlfy());
					project.setSumGsJe(task.getDecideAmount()!=null?project.getSumGsJe().add(task.getDecideAmount()):project.getSumGsJe());
				}
				costProjectService.updateByPrimaryKeySelective(project);
			}
			//saveCode(costTask.getCode());
			//项目汇总
			sumProjAndCon(costTask);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	private void saveTaskOption(CostTask costTask, SysUser currentUser) {
		//插入历史表
		Map<String,Object> tomap=new HashMap<String, Object>();
		tomap.put("taskId", costTask.getId());
		tomap.put("status", costTask.getStatus());
		List<CostTaskOpinion> toList=costTaskOpinionService.selectListByMap(tomap);
		if(null!=toList&&toList.size()>0){
			CostTaskOpinion taskOpinion=toList.get(0);
			taskOpinion.setOpinion(costTask.getCheckExplain());
			taskOpinion.setUpdateTime(new Date());
			taskOpinion.setCreater(currentUser.getName());
			costTaskOpinionService.updateByPrimaryKeySelective(taskOpinion);
		}else{
			CostTaskOpinion taskOpinion=new CostTaskOpinion();
			String toid=UUID.randomUUID().toString().replace("-", "");
			taskOpinion.setId(toid);
			taskOpinion.setTaskId(costTask.getId());
			taskOpinion.setOpinion(costTask.getCheckExplain());
			taskOpinion.setUpdateTime(new Date());
			taskOpinion.setCreater(currentUser.getName());
			taskOpinion.setStatus(costTask.getStatus());
			taskOpinion.setCreateTime(new Date());
			costTaskOpinionService.insertSelective(taskOpinion);
		}
	}

	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,CostTask costTask){
		GlobalResult result=new GlobalResult();
		try {
			String receiveTimeStr=request.getParameter("receiveTimeStr");
			String decideTimeStr=request.getParameter("decideTimeStr");
			String acceptanceTimeStr=request.getParameter("acceptanceTimeStr");
			String submissionTimeStr=request.getParameter("submissionTimeStr");
			String deliveryTimeStr=request.getParameter("deliveryTimeStr");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(receiveTimeStr!=null && !"".equals(receiveTimeStr)){
				costTask.setReceiveTime(sdf.parse(receiveTimeStr));
			}
			if(decideTimeStr!=null && !"".equals(decideTimeStr)){
				costTask.setDecideTime(sdf.parse(decideTimeStr));
			}
			if(acceptanceTimeStr!=null && !"".equals(acceptanceTimeStr)){
				costTask.setAcceptanceTime(sdf.parse(acceptanceTimeStr));
			}
			if(submissionTimeStr!=null && !"".equals(submissionTimeStr)){
				costTask.setSubmissionTime(sdf.parse(submissionTimeStr));
			}
			if("".equals(deliveryTimeStr)|| null==deliveryTimeStr){
				costTask.setDeliveryTimeStr("NULL");
			}else{
				costTask.setDeliveryTime(sdf.parse(deliveryTimeStr));
			}
			if("办内审核完".equals(costTask.getStatus())){
				if(null==costTask.getTaskEndTime() || "".equals(costTask.getTaskEndTimeStr())){
					costTask.setTaskEndTime(new Date());
				}
			}
			costTaskService.updateByPrimaryKeySelective(costTask);
			//修改项目合同任务关联表
			List<CostDpctRelation> dpct= costDpctRelationService.selectByTaskId(costTask.getId());
			if(null!=dpct && dpct.size()>0){
				CostDpctRelation d=dpct.get(0);
				d.setProjectId(costTask.getProjectId());
				d.setContractId(costTask.getContractId());
				costDpctRelationService.updateByPrimaryKeySelective(d);
			}
			//保存任务审核记录表
			SysUser currentUser=shiroUtil.getInstance().currentUser();
			//保存审核历史记录表
			saveTaskOption(costTask, currentUser);
			//项目的概算金额读取概算审核任务里的数据
			if("概算审核".equals(costTask.getAuditPriceType())){
				CostProject project = costProjectService.selectByPrimaryKey(costTask.getProjectId());
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("projectId", project.getId());
				map.put("auditPriceType","概算审核");
				List<CostTask> taskList = costTaskService.selectListByMap(map);
				for(CostTask t:taskList){
					project.setGsGcf(t.getGcfAmount()!=null?project.getGsGcf().add(t.getGcfAmount()):project.getSumGsJe());
					project.setGsElfy(t.getElfyAmount()!=null?project.getGsElfy().add(t.getElfyAmount()):project.getGsElfy());
					project.setGsSlfy(t.getSlfyAmount()!=null?project.getGsSlfy().add(t.getSlfyAmount()):project.getGsSlfy());
					project.setSumGsJe(t.getDecideAmount()!=null?project.getSumGsJe().add(t.getDecideAmount()):project.getSumGsJe());
				}
				costProjectService.updateByPrimaryKeySelective(project);
			}
			//项目汇总
			sumProjAndCon(costTask);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	private void sumProjAndCon(CostTask costTask) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("projectId", costTask.getProjectId());
		CostProject project=costProjectService.selectByPrimaryKey(costTask.getProjectId());
		if("估概预结".equals(costTask.getAuditPriceTypecn()) ){
			if("招标清单/控制价审核".equals(costTask.getAuditPriceType())){
				map.put("auditPriceType", costTask.getAuditPriceType());
				BigDecimal  amount=costProjectService.getTaskSumDecideAmount(map);
				if(amount==null ){
					amount = new BigDecimal(0);
				}
				project.setSumKzjJe(amount);
				costProjectService.updateByPrimaryKeySelective(project);
			}else if("结算审核".equals(costTask.getAuditPriceType())){
				map.put("auditPriceType", costTask.getAuditPriceType());
				BigDecimal  amount=costProjectService.getTaskSumDecideAmount(map);
				if(amount==null ){
					amount = new BigDecimal(0);
				}
				project.setSumJsjJe(amount);
				/*//汇总项目的结余资金=项目的概算金额-项目结算金额
				if(null!=project.getSumGsJe()){
					BigDecimal sumJyJe= project.getSumGsJe().subtract(amount);
					project.setSumJyJe(sumJyJe);
				}*/
				costProjectService.updateByPrimaryKeySelective(project);
				//汇总合同结算价
				String contractId=costTask.getContractId();
				CostContract contract=costContractService.selectByPrimaryKey(contractId);
				if(null!=contractId && !"".equals(contractId)){
					map.put("contractId", contractId);
					BigDecimal  conAmount=costProjectService.getTaskSumDecideAmount(map);
					if(conAmount==null ){
						conAmount = new BigDecimal(0);
					}
					contract.setSettlementAmount(conAmount);
					costContractService.updateByPrimaryKeySelective(contract);
				}
			}else if("施工图预算审核".equals(costTask.getAuditPriceType())){
				map.put("auditPriceType", costTask.getAuditPriceType());
				BigDecimal  amount=costProjectService.getTaskSumDecideAmount(map);
				if(amount==null ){
					amount = new BigDecimal(0);
				}
				project.setSumYsJe(amount);
				costProjectService.updateByPrimaryKeySelective(project);
			}
		}else if("设计图纸变更".equals(costTask.getAuditPriceTypecn())){
			map.put("auditPriceTypeCn", costTask.getAuditPriceTypecn());
			BigDecimal  amount=costProjectService.getTaskSumDecideAmount(map);
			if(amount==null ){
				amount = new BigDecimal(0);
			}
			project.setSumTzBgJe(amount);
			costProjectService.updateByPrimaryKeySelective(project);
		}else if("合同变更".equals(costTask.getAuditPriceTypecn())){
			map.put("auditPriceTypeCn", costTask.getAuditPriceTypecn());
			BigDecimal  amount=costProjectService.getTaskSumDecideAmount(map);
			if(amount==null ){
				amount = new BigDecimal(0);
			}
			project.setSumBgjJe(amount);
			/*//汇总项目的结余资金=项目的概算金额-∑（主合同金额+从合同金额）-∑合同变更定审金额（未关联从合同的）
			BigDecimal contractAmount= costProjectService.getContractSumAmount(map);
			if(contractAmount==null ){
				contractAmount = new BigDecimal(0);
			}
			BigDecimal sumJyJe= project.getSumGsJe().subtract(contractAmount).subtract(amount);
			if(null!=project.getSumGsJe() && project.getSumJsjJe()==null){
				project.setSumJyJe(sumJyJe);
			}*/
			costProjectService.updateByPrimaryKeySelective(project);
			//汇总合同变更价金额
			String contractId=costTask.getContractId();
			CostContract contract=costContractService.selectByPrimaryKey(contractId);
			if(null!=contractId && !"".equals(contractId)){
				map.put("contractId", contractId);
				BigDecimal  conAmount=costProjectService.getTaskSumDecideAmount(map);
				if(null!=conAmount){
					contract.setChangeAmount(conAmount);
				}else{
					contract.setChangeAmount(new BigDecimal(0));
				}
				costContractService.updateByPrimaryKeySelective(contract);
			}
			if(project.getSumGsJe()!=null && project.getSumGsJe().compareTo(new BigDecimal(0))>0){
				//汇总项目的结余资金:结余资金=概算金额-sum(该项目下未结算合同的合同金额+未结算合同的合同变更）-sum（该项目下已结算合同的结算金额）
				BigDecimal noJsContractAmount = new BigDecimal(0);
				BigDecimal noJsChangeContractAmount = new BigDecimal(0);
				map.clear();
				map.put("projectId", project.getId());
				List<CostContract> conList = costContractService.selectListByMap(map);
				for(CostContract c:conList){
					Map<String,Object> ma = new HashMap<String,Object>();
					ma.put("contractId", c.getId());
					List<CostTask> taskList2 = costTaskService.selectListByMap(ma);
					boolean js = false;
					for(CostTask task:taskList2){
						if("结算审核".equals(task.getAuditPriceType())){
							js = true;
							break;
						}
					}
					if(js==false){
						if(c.getContractAmount()!=null){
							noJsContractAmount = noJsContractAmount.add(c.getContractAmount());
						}
						if(c.getChangeAmountSum()!=null){
							noJsChangeContractAmount = noJsChangeContractAmount.add(c.getChangeAmountSum());
						}
					}
				}
				BigDecimal sumJyJe= project.getSumGsJe().subtract(noJsContractAmount.add(noJsChangeContractAmount).add(project.getSumJsjJe()));
				project.setSumJyJe(sumJyJe);
			}else{
				project.setSumJyJe(new BigDecimal(0));
			}

		}
	}



	@ResponseBody
	@RequestMapping("/del")
	public GlobalResult del(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			for(String i:arr){
				List<CostDpctRelation> dpctRelation= costDpctRelationService.selectByTaskId(i);
				if(null !=dpctRelation && dpctRelation.size()>0){
					CostDpctRelation dpct=dpctRelation.get(0);
					dpct.setTaskId("");
					costDpctRelationService.updateByPrimaryKeySelective(dpct);
				}
				CostTask costTask= costTaskService.selectByPrimaryKey(i);
				costTaskService.deleteByPrimaryKey(i);
				//项目汇总
				sumProjAndCon(costTask);
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	//估概预结导出
	@ResponseBody
	@RequestMapping("/exportTaskGa")
	public void exportTaskGa(HttpServletRequest request,CostTask task,HttpServletResponse response){
		List<CostTask> list =new ArrayList<CostTask>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					CostTask doc=costTaskService.selectByPrimaryKey(id);
					list.add(doc);
				}
			}else{
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("auditPriceTypecn", "估概预结");
				list= costTaskMapper.selectListByMap(map);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = Global.TAS_START_TIME;
			String endTime = Global.TAS_END_TIME;
			String path=request.getSession().getServletContext().getRealPath("/template/report/taskGa_template.xlsx");
			String newPath="";
			if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/估概预结_"+startTime+"-"+endTime+".xlsx");
			}
			else{
				String dateString = formatter.format(new Date());
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/估概预结_"+dateString+".xlsx");
			}
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(CostTask l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getCode());
				cellList.add(l.getProjectName());
				cellList.add(l.getContractName());
				cellList.add(l.getName());
				cellList.add(l.getAuditPriceType());
				cellList.add(l.getContractAmount()!=null?l.getContractAmount()+"":"");
				cellList.add(l.getGiveAmount()!=null?l.getGiveAmount()+"":"");
				cellList.add(l.getMyAuditAmount()!=null?l.getMyAuditAmount()+"":"");
				cellList.add(l.getAgencyAuditAmount()!=null?l.getAgencyAuditAmount()+"":"");
				cellList.add(l.getDecideAmount()!=null?l.getDecideAmount()+"":"");
				BigDecimal decideAmount=l.getDecideAmount();
				BigDecimal contractAmount=l.getContractAmount();
				if(null==decideAmount || decideAmount.equals(BigDecimal.ZERO) || null==contractAmount || contractAmount.equals(BigDecimal.ZERO)){
					cellList.add("");
				}else{
					cellList.add((decideAmount.divide(contractAmount, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)))+""+"%");
				}
				cellList.add(l.getCreater());
				cellList.add(l.getPersonLiable());
				cellList.add(l.getAuditPriceUnit());
				cellList.add(l.getStatus());
				cellList.add(l.getFinalizeNumber());
				cellList.add(l.getReceiveTime()!=null?formatter.format(l.getReceiveTime()):"");
				cellList.add(l.getDecideTime()!=null?formatter.format(l.getDecideTime()):"");
				cellList.add(l.getCoordinateFlag());
				rowList.add(cellList);
			}
			date.put("估概预结", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//合同变更导出
	@RequestMapping("/exportTaskHt")
	@ResponseBody
	public void exportTaskHt(HttpServletRequest request,CostTask task,HttpServletResponse response){
		List<CostTask> list =new ArrayList<CostTask>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					CostTask doc=costTaskService.selectByPrimaryKey(id);
					list.add(doc);
				}
			}else{
				Map<String,Object> map=new HashMap<String, Object>();
				String arr[] = {"设计图纸变更","合同变更"};
	  			map.put("auditPriceTypeList", arr);
				list= costTaskMapper.selectListByMap(map);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = Global.TAS_START_TIME;
			String endTime = Global.TAS_END_TIME;
			String path=request.getSession().getServletContext().getRealPath("/template/report/taskHt_template.xlsx");
			String newPath="";
			if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/合同变更_"+startTime+"-"+endTime+".xlsx");
			}
			else{
				String dateString = formatter.format(new Date());
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/合同变更_"+dateString+".xlsx");
			}
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(CostTask l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getCode());
				cellList.add(l.getProjectName());
				cellList.add(l.getContractName());
				cellList.add(l.getName());
				cellList.add(l.getAuditPriceType());
				cellList.add(l.getGiveAmount()!=null?l.getGiveAmount()+"":"");
				cellList.add(l.getAgencyAuditAmount()!=null?l.getAgencyAuditAmount()+"":"");
				cellList.add(l.getCreater());
				cellList.add(l.getPersonLiable());
				cellList.add(l.getAuditPriceUnit());
				cellList.add(l.getStatus());
				cellList.add(l.getFinalizeNumber());
				cellList.add(l.getReceiveTime()!=null?formatter.format(l.getReceiveTime()):"");
				cellList.add(l.getDecideTime()!=null?formatter.format(l.getDecideTime()):"");
				cellList.add(l.getCoordinateFlag());
				rowList.add(cellList);
			}
			date.put("合同变更", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//单价审核导出
	@ResponseBody
	@RequestMapping("/exportTaskDj")
	public void exportTaskDj(HttpServletRequest request,CostTask task,HttpServletResponse response){
		List<CostTask> list =new ArrayList<CostTask>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					CostTask doc=costTaskService.selectByPrimaryKey(id);
					list.add(doc);
				}
			}else{
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("auditPriceTypecn", "单价审核");
				list= costTaskMapper.selectListByMap(map);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = Global.TAS_START_TIME;
			String endTime = Global.TAS_END_TIME;
			String path=request.getSession().getServletContext().getRealPath("/template/report/taskDj_template.xlsx");
			String newPath="";
			if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/单价审核_"+startTime+"-"+endTime+".xlsx");
			}
			else{
				String dateString = formatter.format(new Date());
				newPath=request.getSession().getServletContext().getRealPath("/template/temp/单价审核_"+dateString+".xlsx");
			}
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(CostTask l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getCode());
				cellList.add(l.getProjectName());
				cellList.add(l.getContractName());
				cellList.add(l.getName());
				cellList.add(l.getAuditPriceType());
				cellList.add(l.getGiveNumber()+"");
				cellList.add((l.getAuditNumber()==null?0:l.getAuditNumber())+"");
				//审定资料单价共享
                if(null==l.getCategory()){
                    l.setCategory("无");
                }else if("否".equals(l.getCategory())){
                    l.setCategory("已上传");
                }else if("是".equals(l.getCategory())){
                    l.setCategory("已入库");
                }
				cellList.add(l.getCategory());
				cellList.add(l.getCreater());
				cellList.add(l.getPersonLiable());
				cellList.add(l.getAuditPriceUnit());
				cellList.add(l.getStatus());
				cellList.add(l.getFinalizeNumber());
				cellList.add(l.getReceiveTime()!=null?formatter.format(l.getReceiveTime()):"");
				cellList.add(l.getDecideTime()!=null?formatter.format(l.getDecideTime()):"");
				cellList.add(l.getCoordinateFlag());
				rowList.add(cellList);
			}
			date.put("单价审核", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Description: 获取流水号
	 * @param @param prefix
	 * @param @return
	 * @return String
	 * @throws
	 * @author lixiang
	 * @date 2019-1-18上午11:49:30
	 * @Company  广东华联软件科技有限公司
	 */
	@ResponseBody
	@RequestMapping("/getNewCode")
	public synchronized String getNewCode(String prefix){
		if(null==prefix){
			prefix="";
		}
		String newCode="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currtDate=sdf.format(new Date());
		//GDHL2018001
		//String hlhtCode="2019-01-18-002";
		String hlhtCode=getCode();
		if(null==hlhtCode || "".equals(hlhtCode)){
			newCode=prefix+currtDate+"-001";
		}else{
			String hlhtCode_2=hlhtCode.substring(hlhtCode.length()-14,hlhtCode.length()-4);
			String hlhtCode_3=hlhtCode.substring(hlhtCode.length()-3,hlhtCode.length());
			//同一年
			if(currtDate.equals(hlhtCode_2)){
				int s=Integer.parseInt(hlhtCode_3);  // 首先查询出那个counter值
				s=++s;
				s=s==1000?1:s;
				String val=s<999?(
				s<10?("00"+s):(
				s<100?"0"+s:s+"")
				):"001";
				newCode=prefix+currtDate+"-"+val;
			}else{
				newCode=prefix+currtDate+"-001";
			}
		}
		//
		//saveCode(newCode);
		return newCode;
	}

	private String getCode(){
		Properties props = new Properties();
		String code="";
		try {
			props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
			 //修改值
			 code=(String) props.get("autoCode");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	private void saveCode(String code){
		Properties props = new Properties();
		try {
			props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
			 //修改值
			props.setProperty("autoCode", code);
			URL fileUrl = this.getClass().getResource("/resource/resource.properties");//得到文件路径
			FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()));
			props.store(fos, "the primary key of article table");
            fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//估概导入
	@ResponseBody
	@RequestMapping("importTaskGg")
	public GlobalResult importTaskGg(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			List<CostTask> taskList = costTaskService.importTaskGg(request);
			if(null!=taskList && taskList.size()>0){
				result.setData(taskList);
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

	//单价导入
	@ResponseBody
	@RequestMapping("importTaskDj")
	public GlobalResult importTaskDj(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			List<CostTask> taskList = costTaskService.importTaskDj(request);
			if(null!=taskList && taskList.size()>0){
				result.setData(taskList);
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

	//合同导入
	@ResponseBody
	@RequestMapping("importTaskHt")
	public GlobalResult importTaskHt(HttpServletRequest request){
		GlobalResult result=new GlobalResult();
		try {
			List<CostTask> taskList = costTaskService.importTaskHt(request);
			if(null!=taskList && taskList.size()>0){
				result.setData(taskList);
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

	/**
	 *
	 * @Description: 用来在附件表创建主材定价和综合单价文件夹
	 * @param @param request
	 * @param @return
	 * @return int
	 * @throws
	 * @author lixiang
	 * @date 2019-3-5下午3:59:14
	 * @Company  广东华联软件科技有限公司
	 */
	@ResponseBody
	@RequestMapping("test2")
	public int test(HttpServletRequest request){
		CostTaskExample example=new CostTaskExample();
		Criteria criteria = example.createCriteria();
		List<String> l=new ArrayList<String>();
		l.add("主材定价");
		l.add("综合单价");
		criteria.andAuditPriceTypeIn(l);
		List<CostTask> list=costTaskService.selectByExample(example);
		System.out.println(list.size());
		for(CostTask costTask:list){
			//在附件表新增文件夹名称（新增主材单价审批表）
			if(costTask.getAuditPriceType()!=null && ("主材定价".equals(costTask.getAuditPriceType()) || "综合单价".equals(costTask.getAuditPriceType()))){
				CostAttachment att=new CostAttachment();
				att.setId(UUID.randomUUID().toString().replace("-", ""));
				att.setTypeId(costTask.getId());
				if("主材定价".equals(costTask.getAuditPriceType())){
					att.setName("新增主材单价审批表");
				}else{
					att.setName("换算（合同外）新增项目综合单价");
				}
				att.setSuffix("文件夹");
				att.setDataType("shending");
				costAttachmentService.insertSelective(att);
			}
		}
		return list.size();
	}
	//汇总计算
	@ResponseBody
	@RequestMapping("test")
	public String testSumAmount(HttpServletRequest request){
		List<CostProject> projList=costProjectService.getList(null);
		try {
			for(CostProject project:projList){
				//项目汇总
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("projectId", project.getId());
				map.put("auditPriceType","概算审核");
				List<CostTask> taskList = costTaskService.selectListByMap(map);
				for(CostTask t:taskList){
					project.setGsGcf(t.getGcfAmount()!=null?project.getGsGcf().add(t.getGcfAmount()):project.getSumGsJe());
					project.setGsElfy(t.getElfyAmount()!=null?project.getGsElfy().add(t.getElfyAmount()):project.getGsElfy());
					project.setGsSlfy(t.getSlfyAmount()!=null?project.getGsSlfy().add(t.getSlfyAmount()):project.getGsSlfy());
					project.setSumGsJe(t.getDecideAmount()!=null?project.getSumGsJe().add(t.getDecideAmount()):project.getSumGsJe());
				}
				map.clear();
				map.put("projectId", project.getId());
				map.put("auditPriceType","招标清单/控制价审核");
				BigDecimal  kzjAmount=costProjectService.getTaskSumDecideAmount(map);
				if(null==kzjAmount){
					kzjAmount=new BigDecimal(0);
				}
				project.setSumKzjJe(kzjAmount);
				map.clear();
				map.put("projectId", project.getId());
				map.put("auditPriceType", "施工图预算审核");
				BigDecimal  ysAmount=costProjectService.getTaskSumDecideAmount(map);
				if(null==ysAmount){
					ysAmount=new BigDecimal(0);
				}
				project.setSumYsJe(ysAmount);
				map.clear();
				map.put("projectId", project.getId());
				map.put("auditPriceTypeCn", "设计图纸变更");
				BigDecimal  sjbgAmount=costProjectService.getTaskSumDecideAmount(map);
				if(null==sjbgAmount){
					ysAmount=new BigDecimal(0);
				}
				project.setSumTzBgJe(sjbgAmount);
				map.clear();
				map.put("projectId", project.getId());
				map.put("auditPriceTypeCn", "合同变更");
				BigDecimal  bgAmount=costProjectService.getTaskSumDecideAmount(map);
				if(null==bgAmount){
					bgAmount=new BigDecimal(0);
				}
				project.setSumBgjJe(bgAmount);

				//汇总合同
				map.clear();
				map.put("projectId", project.getId());
				List<CostContract> conList = costContractService.selectListByMap(map);
				for(CostContract c:conList){
					map.clear();
					map.put("projectId", project.getId());
					map.put("auditPriceType", "结算审核");
					map.put("contractId", c.getId());
					BigDecimal  conAmount=costProjectService.getTaskSumDecideAmount(map);
					if(null==conAmount){
						conAmount=new BigDecimal(0);
					}
					c.setSettlementAmount(conAmount);
					map.clear();
					map.put("projectId", project.getId());
					map.put("auditPriceTypeCn", "合同变更");
					map.put("contractId", c.getId());
					BigDecimal  htbgAmount=costProjectService.getTaskSumDecideAmount(map);
					if(null==htbgAmount){
						htbgAmount=new BigDecimal(0);
					}
					c.setChangeAmount(htbgAmount);
					costContractService.updateByPrimaryKeySelective(c);
				}
				map.clear();
				map.put("projectId", project.getId());
				map.put("auditPriceType", "结算审核");
				BigDecimal  jsAmount=costProjectService.getTaskSumDecideAmount(map);
				if(null==jsAmount){
					jsAmount=new BigDecimal(0);
				}
				project.setSumJsjJe(jsAmount);


				//汇总合同价
				map.clear();
				map.put("projectId", project.getId());
				BigDecimal contractAmount= costProjectService.getContractSumAmount(map);
				if(null==contractAmount){
					contractAmount=new BigDecimal(0);
				}
				project.setSumHtjJe(contractAmount);
				if(project.getSumGsJe()!=null && project.getSumGsJe().compareTo(new BigDecimal(0))>0){
					//汇总项目的结余资金:结余资金=概算金额-sum(该项目下未结算合同的合同金额+未结算合同的合同变更）-sum（该项目下已结算合同的结算金额）
					BigDecimal noJsContractAmount = new BigDecimal(0);
					BigDecimal noJsChangeContractAmount = new BigDecimal(0);
					for(CostContract c:conList){
						Map<String,Object> ma = new HashMap<String,Object>();
						ma.put("contractId", c.getId());
						List<CostTask> taskList2 = costTaskService.selectListByMap(ma);
						boolean js = false;
						for(CostTask task:taskList2){
							if("结算审核".equals(task.getAuditPriceType())){
								js = true;
								break;
							}
						}
						if(js==false){
							if(c.getContractAmount()!=null){
								noJsContractAmount = noJsContractAmount.add(c.getContractAmount());
							}
							if(c.getChangeAmountSum()!=null){
								noJsChangeContractAmount = noJsChangeContractAmount.add(c.getChangeAmountSum());
							}
						}
					}
					BigDecimal sumJyJe= project.getSumGsJe().subtract(noJsContractAmount.add(noJsChangeContractAmount).add(jsAmount));
					project.setSumJyJe(sumJyJe);
				}
				else{
					project.setSumJyJe(new BigDecimal(0));
				}
				/*//汇总项目的结余资金=项目的概算金额-项目结算金额
				if(null!=project.getSumGsJe() && jsAmount!=null && jsAmount.compareTo(new BigDecimal(0))>0){
					BigDecimal sumJyJe= project.getSumGsJe().subtract(jsAmount);
					project.setSumJyJe(sumJyJe);
				}
				//汇总项目的结余资金=项目的概算金额-∑（主合同金额+从合同金额）-∑合同变更定审金额（未关联从合同的）
				else if(null!=project.getSumGsJe()){
					BigDecimal sumJyJe= project.getSumGsJe().subtract(contractAmount).subtract(bgAmount);
					if(null==sumJyJe){
						sumJyJe=new BigDecimal(0);
					}
					project.setSumJyJe(sumJyJe);
				}*/



				costProjectService.updateByPrimaryKeySelective(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}


	@ResponseBody
	@RequestMapping("test3")
	public int test3(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("checkExplainNotNull", 1);
		List<CostTask> list =costTaskService.selectListByMap(map);
		System.out.println(list.size());
		for(CostTask costTask:list){
			//插入历史表
			Map<String,Object> tomap=new HashMap<String, Object>();
			tomap.put("taskId", costTask.getId());
			tomap.put("status", costTask.getStatus());
			List<CostTaskOpinion> toList=costTaskOpinionService.selectListByMap(tomap);
			if(null!=toList&&toList.size()>0){
				CostTaskOpinion taskOpinion=toList.get(0);
				taskOpinion.setOpinion(costTask.getCheckExplain());
				taskOpinion.setUpdateTime(new Date());
				taskOpinion.setCreater(costTask.getPersonLiable());
				costTaskOpinionService.updateByPrimaryKeySelective(taskOpinion);
			}else{
				CostTaskOpinion taskOpinion=new CostTaskOpinion();
				String toid=UUID.randomUUID().toString().replace("-", "");
				taskOpinion.setId(toid);
				taskOpinion.setTaskId(costTask.getId());
				taskOpinion.setOpinion(costTask.getCheckExplain());
				taskOpinion.setUpdateTime(new Date());
				taskOpinion.setCreater(costTask.getPersonLiable());
				taskOpinion.setStatus(costTask.getStatus());
				taskOpinion.setCreateTime(new Date());
				costTaskOpinionService.insertSelective(taskOpinion);
			}
		}
		return list.size();
	}


}