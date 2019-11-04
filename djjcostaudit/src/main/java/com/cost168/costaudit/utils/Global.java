package com.cost168.costaudit.utils;

import java.util.ArrayList;
import java.util.List;

import com.cost168.costaudit.controller.cost.CostProjectStakelholderController;
import com.cost168.costaudit.pojo.cost.*;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.work.WorkPerson;
import com.cost168.costaudit.pojo.work.WorkProcessApply;
import com.cost168.costaudit.pojo.work.vo.WorkRegisterVo;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidate;
import com.cost168.costaudit.pojo.yaohao.YaohaoOrder;
import com.cost168.costaudit.pojo.yaohao.YaohaoPunish;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbid;
import com.cost168.costaudit.pojo.yaohao.param.YaohaoAssessParam;
import com.cost168.costaudit.vo.cost.ContractJsCountVo;
import com.cost168.costaudit.vo.cost.ProjectJsCountVo;



/**
 * 
 * ClassName: Global 
 * @Description: 全局
 * @author lixiang
 * @date 2018-12-10 上午 10:50:25
 * @Company  广东华联软件科技有限公司
 */
public class Global {

	public static String PERMISSIONSTR = null;
	@SuppressWarnings("serial")
	public static List<String> PREVIEW_JPG_1 = new ArrayList<String>(){{add("jpg");add("png");add("txt");add("pdf");add("xml");}};
	@SuppressWarnings("serial")
	public static List<String> PREVIEW_DOC_2 = new ArrayList<String>(){{add("doc");add("docx");}};
	@SuppressWarnings("serial")
	public static List<String> PREVIEW_XLS_3 = new ArrayList<String>(){{add("xls");add("xlsx");}};
	
	//项目
	public static List<CostProject> PRO_EXPORT_LIST =null;
	public static String PRO_START_TIME =null;
	public static String PRO_END_TIME =null;
	//合同
	public static List<CostContract> CON_EXPORT_LIST =null;
	public static String CON_START_TIME =null;
	public static String CON_END_TIME =null;
	//往来文件
	public static List<CostDocument> DOC_EXPORT_LIST =null;
	public static String DOC_START_TIME =null;
	public static String DOC_END_TIME =null;
	//审价任务
	public static List<CostTask> TAS_EXPORT_LIST =null;
	public static String TAS_START_TIME =null;
	public static String TAS_END_TIME =null;
	//入库企业
	public static List<CostEnterprise> ENT_EXPORT_LIST =null;
	//用户列表
	public static List<SysUser> USE_EXPORT_LIST =null;
	//单价库
	public static List<CostPriceLibrary> PRI_EXPORT_LIST =null;
	public static String PRI_START_TIME =null;
	public static String PRI_END_TIME =null;
	//统计查询：项目结算台账
	public static List<ProjectJsCountVo> PROJECT_JSCOUNT_EXPORT_LIST =null;
	public static List<ContractJsCountVo> CONTRACT_JSCOUNT_EXPORT_LIST =null;
	public static List<ContractJsCountVo> CONTRACT_JSCOUNT_EXPORT_LIST2 =null;
	//考勤管理
	public static List<WorkRegisterVo> REG_EXPORT_LIST =null;
	//驻场企业，人员
	public static List<WorkEnterprise> WORKENT_EXPORT_LIST =null;
	public static List<WorkPerson> PER_EXPORT_LIST =null;
	//请假
	public static List<WorkProcessApply> PROCESS_EXPORT_LIST =null;


	public static List<CostProjectStakeholder> PROJECT_EXPORT_LIST =null;
	public static List<CostUnitProject>  UNIT_EXPORT_LIST=null;
	public static List<CostContentTemplate> CONTENT_EXPORT_LIST=null;
	public static  CostProject PROJECT_EXPORT = null;
	public static List<YaohaoCandidate> CANDIDATE_EXPORT_LIST=null;
	public static List<YaohaoOrder> ORDER_EXPORT_LIST=null;
	public static List<YaohaoWinbid> WINBID_EXPORT_LIST=null;
	public static List<YaohaoAssessParam> ASSESS_EXPORT_LIST=null;
	public static List<YaohaoPunish> PUNISH_EXPORT_LIST=null;
}
