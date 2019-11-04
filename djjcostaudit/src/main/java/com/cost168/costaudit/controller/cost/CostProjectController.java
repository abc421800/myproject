package com.cost168.costaudit.controller.cost;

import com.cost168.costaudit.pojo.cost.*;
import com.cost168.costaudit.pojo.sys.SysOrg;
import com.cost168.costaudit.service.cost.*;
import com.cost168.costaudit.service.sys.SysOrgService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.ExcelUtil;
import com.cost168.costaudit.utils.Global;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.utils.JsonUtils;
import com.cost168.costaudit.vo.cost.ContractJsCountVo;
import com.cost168.costaudit.vo.cost.ProjectJsCountVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName: CostDocumentController
 *
 * @author zhongm
 * @Description: TODO
 * @date 2018-12-6上午11:16:10
 * @Company 广东华联软件科技有限公司
 */

@Controller
@RequestMapping("/costProject")
public class CostProjectController {
    @Autowired
    private CostProjectService costProjectService;
    @Autowired
    private CostProjectPeriodService costProjectPeriodService;
    @Autowired
    private CostDocumentService costDocumentService;
    @Autowired
    private CostTaskService costTaskService;
    @Autowired
    private CostTaskOpinionService costTaskOpinionService;
    @Autowired
    private CostContractService costContractService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    public CostProjectTypeService costProjectTypeService;


    @RequestMapping("/getProjectlist")
    @ResponseBody
    public EUDataGridResult getProjectlist(HttpServletRequest request, CostProject project, int page, int rows) {
        //SysUser user= shiroUtil.getInstance().currentUser();
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String projectClassificationIds = request.getParameter("projectClassificationId");
        Map<String, Object> selectMap = JsonUtils.object2Map(project);
        map.putAll(selectMap);
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
            Global.PRO_START_TIME = startTime;
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
            Global.PRO_END_TIME = endTime;
        }
        if (projectClassificationIds != null && !"".equals(projectClassificationIds)) {
            String arrcat[] = projectClassificationIds.split(",");
            map.put("projClassificationId", arrcat);
            map.put("projClassificationIdLength", arrcat.length);
        }
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        List<CostProject> list = costProjectService.getList(map);
        result.setRows(list);
        Global.PRO_EXPORT_LIST = list;
        int total = costProjectService.getCount(map);
        result.setTotal(total);
        List<CostProject> footer = new ArrayList<CostProject>();
        CostProject s = new CostProject();
        BigDecimal zero = new BigDecimal(0);
        s.setXjGsJe(zero);
        s.setXjGsGcf(zero);
        s.setXjGsElfy(zero);
        s.setXjGsSlfy(zero);
        s.setKyGsJe(zero);
        s.setKyGsGcf(zero);
        s.setKyGsElfy(zero);
        s.setKyGsSlfy(zero);
        s.setSumGsJe(zero);
        s.setGsGcf(zero);
        s.setGsElfy(zero);
        s.setGsSlfy(zero);
        s.setSumYsJe(zero);
        s.setSumKzjJe(zero);
        s.setSumHtjJe(zero);
        s.setSumBgjJe(zero);
        s.setSumJsjJe(zero);
        s.setSumJyJe(zero);
        s.setSumTzBgJe(zero);
        s.setCode("汇总");
        for (CostProject c : list) {
            s.setXjGsJe(c.getXjGsJe() != null ? s.getXjGsJe().add(c.getXjGsJe()) : s.getXjGsJe());
            s.setXjGsGcf(c.getXjGsGcf() != null ? s.getXjGsGcf().add(c.getXjGsGcf()) : s.getXjGsGcf());
            s.setXjGsElfy(c.getXjGsElfy() != null ? s.getXjGsElfy().add(c.getXjGsElfy()) : s.getXjGsElfy());
            s.setXjGsSlfy(c.getXjGsSlfy() != null ? s.getXjGsSlfy().add(c.getXjGsSlfy()) : s.getXjGsSlfy());
            s.setKyGsJe(c.getKyGsJe() != null ? s.getKyGsJe().add(c.getKyGsJe()) : s.getKyGsJe());
            s.setKyGsGcf(c.getKyGsGcf() != null ? s.getKyGsGcf().add(c.getKyGsGcf()) : s.getKyGsGcf());
            s.setKyGsElfy(c.getKyGsElfy() != null ? s.getKyGsElfy().add(c.getKyGsElfy()) : s.getKyGsElfy());
            s.setKyGsSlfy(c.getKyGsSlfy() != null ? s.getKyGsSlfy().add(c.getKyGsSlfy()) : s.getKyGsSlfy());
            s.setSumGsJe(c.getSumGsJe() != null ? s.getSumGsJe().add(c.getSumGsJe()) : s.getSumGsJe());
            s.setGsGcf(c.getGsGcf() != null ? s.getGsGcf().add(c.getGsGcf()) : s.getGsGcf());
            s.setGsElfy(c.getGsElfy() != null ? s.getGsElfy().add(c.getGsElfy()) : s.getGsElfy());
            s.setGsSlfy(c.getGsSlfy() != null ? s.getGsSlfy().add(c.getGsSlfy()) : s.getGsSlfy());
            s.setSumYsJe(c.getSumYsJe() != null ? s.getSumYsJe().add(c.getSumYsJe()) : s.getSumYsJe());
            s.setSumKzjJe(c.getSumKzjJe() != null ? s.getSumKzjJe().add(c.getSumKzjJe()) : s.getSumKzjJe());
            s.setSumHtjJe(c.getSumHtjJe() != null ? s.getSumHtjJe().add(c.getSumHtjJe()) : s.getSumHtjJe());
            s.setSumBgjJe(c.getSumBgjJe() != null ? s.getSumBgjJe().add(c.getSumBgjJe()) : s.getSumBgjJe());
            s.setSumJsjJe(c.getSumJsjJe() != null ? s.getSumJsjJe().add(c.getSumJsjJe()) : s.getSumJsjJe());
            s.setSumJyJe(c.getSumJyJe() != null ? s.getSumJyJe().add(c.getSumJyJe()) : s.getSumJyJe());
            s.setSumTzBgJe(c.getSumTzBgJe() != null ? s.getSumTzBgJe().add(c.getSumTzBgJe()) : s.getSumTzBgJe());
        }
        footer.add(s);
        result.setFooter(footer);
        return result;
    }

    @RequestMapping("/editProject")
    public String editProject(HttpServletRequest request, ModelMap model) {
        String projId = request.getParameter("projId");
        if (projId != null && !"".equals(projId)) {
            CostProject project = costProjectService.selectByPrimaryKey(projId);
            //判断项目分类是否有删除部分
            String pType = project.getProjectClassificationId();
            StringBuffer sb = new StringBuffer();
            if (null != pType) {
                String arr[] = pType.split(",");
                for (String s : arr) {
                    CostProjectType type = costProjectTypeService.selectByPrimaryKey(s);
                    if (null != type && 1 == type.getDeleteFlag()) {
                        sb.append(s).append(",");
                    }
                }
            }
            if (sb.length() > 0) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            project.setProjectClassificationId(sb.toString());
            model.put("project", project);
        }
        CostProjectPeriodExample e = new CostProjectPeriodExample();
        com.cost168.costaudit.pojo.cost.CostProjectPeriodExample.Criteria criteria = e.createCriteria();
        criteria.andProjectIdEqualTo(projId);
        e.setOrderByClause(" num asc");
        List<CostProjectPeriod> nodeList = costProjectPeriodService.selectByExample(e);
        model.put("nodeList", nodeList);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projId);
        map.put("auditPriceType", "概算审核");
        boolean gaisEdit = true;
        List<CostTask> taskList = costTaskService.selectListByMap(map);
        if (taskList.size() > 0) {
            gaisEdit = false;
        }
        model.put("gaisEdit", gaisEdit);
        return "project/proEdit";
    }

    @RequestMapping("/saveProject")
    @ResponseBody
    public GlobalResult saveProject(HttpServletRequest request, CostProject project) {
        GlobalResult result = costProjectService.saveProject(project);
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateProject")
    public GlobalResult updateProject(HttpServletRequest request, CostProject project) {
        GlobalResult result = costProjectService.updateProject(project);
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteProject")
    public GlobalResult deleteProject(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String arr[] = ids.split(",");
        try {
            for (String i : arr) {
                if (i != null && !"".equals(i)) {
                    costProjectService.deleteProject(i);
                }
            }
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    /**
     * Created by zhouyanlv on 2019/7/26
     * 统计查询是否显示
     */
    @ResponseBody
    @RequestMapping("/updateShowFlag")
    public GlobalResult updateShowFlag(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String status2 = request.getParameter("showFlag2");
        String arr[] = ids.split(",");
        try {
            for (String i : arr) {
                if (i != null && !"".equals(i)) {
                    CostProject p = costProjectService.selectByPrimaryKey(i);
                    p.setShowFlag2(status2);
                    costProjectService.updateByPrimaryKeySelective(p);
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
    @RequestMapping("/getDocumentList")
    public EUDataGridResult getDocumentList(HttpServletRequest request, CostDocument costDocument, int page, int rows) throws ParseException {
        EUDataGridResult result = new EUDataGridResult();
        String projId = request.getParameter("projId");
        Map<String, Object> map = new HashMap<String, Object>();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Map<String, Object> selectMap = JsonUtils.object2Map(costDocument);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.putAll(selectMap);
        map.put("projectId", projId);
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        List<CostDocument> list = costDocumentService.selectRelationDocListByMap(map);
        int total = costDocumentService.selectRelationDocCountByMap(map);
        result.setRows(list);
        result.setTotal(total);
        return result;
    }

    @RequestMapping("/exportProject")
    @ResponseBody
    public void exportProject(HttpServletRequest request, CostProject project, HttpServletResponse response) {
        List<CostProject> list = new ArrayList<CostProject>();
        String ids = request.getParameter("ids");
        try {
            ServletOutputStream out = response.getOutputStream();
            if (null != ids && !"".equals(ids)) {
                String arr[] = ids.split(",");
                for (String id : arr) {
                    CostProject doc = costProjectService.selectByPrimaryKey(id);
                    list.add(doc);
                }
            } else {
                list = costProjectService.getList(null);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = Global.PRO_START_TIME;
            String endTime = Global.PRO_END_TIME;
            String path = request.getSession().getServletContext().getRealPath("/template/report/project_template.xlsx");
            String fileName = "";
            if (null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)) {
                fileName = "项目台账_" + startTime + "-" + endTime + ".xlsx";
            } else {
                String dateString = formatter.format(new Date());
                fileName = "项目台账_" + dateString + ".xlsx";
            }
            String downloadFileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attactment; fileName=" + downloadFileName);
            costProjectService.exportProject(path, out, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //结算台账
    @RequestMapping("/getProjectJsList")
    @ResponseBody
    public EUDataGridResult getProjectJsList(HttpServletRequest request, ProjectJsCountVo projJsVo, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> selectMap = JsonUtils.object2Map(projJsVo);
        String decideAmountPercentFlag = request.getParameter("decideAmountPercentFlag");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String receiveTimeStart = request.getParameter("receiveTimeStart");
        String receiveTimeEnd = request.getParameter("receiveTimeEnd");
        String orgId = request.getParameter("orgId");
        String projectClassificationIds = request.getParameter("projectClassificationId");
        map.putAll(selectMap);
        if (null != orgId && !"".equals(orgId)) {
            map.put("selectDepartMent", orgId);
        }
        if (null != decideAmountPercentFlag && !"".equals(decideAmountPercentFlag)) {
            map.put("decideAmountPercentFlag", decideAmountPercentFlag);
        }
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
        }
        if (null != receiveTimeStart && !"".equals(receiveTimeStart)) {
            map.put("receiveTimeStart", receiveTimeStart + " 00:00:00");
        }
        if (null != receiveTimeEnd && !"".equals(receiveTimeEnd)) {
            map.put("receiveTimeEnd", receiveTimeEnd + " 24:00:00");
        }
        if (null != projectClassificationIds && !"".equals(projectClassificationIds)) {
            String arrcat[] = projectClassificationIds.split(",");
            map.put("projClassificationId", arrcat);
            map.put("projClassificationIdLength", arrcat.length);
        }
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        //判断是否查结算
        String auditPriceType = request.getParameter("auditPriceType");
        map.put("auditPriceType", auditPriceType);
        //判断除了是否结算以外还带有别的条件，带条件为false，不带条件为true
        boolean flag = (null == projJsVo.getProjectName() || "".equals(projJsVo.getProjectName()))
                && (null == projJsVo.getContractName() || "".equals(projJsVo.getContractName()))
                && (null == projJsVo.getContractCode() || "".equals(projJsVo.getContractCode()))
                && (null == projJsVo.getPartyB() || "".equals(projJsVo.getPartyB()))
                && (null == orgId || "".equals(orgId))
                && (null == projJsVo.getContractType() || "".equals(projJsVo.getContractType()))
                && (null == receiveTimeStart || "".equals(receiveTimeStart))
                && (null == receiveTimeEnd || "".equals(receiveTimeEnd))
                && (null == startTime || "".equals(startTime))
                && (null == endTime || "".equals(endTime))
                && (null == projJsVo.getRecordNumber() || "".equals(projJsVo.getRecordNumber()))
                && (null == projJsVo.getOwnerApproval() || "".equals(projJsVo.getOwnerApproval()))
                && (null == projJsVo.getDeliveryFlag() || "".equals(projJsVo.getDeliveryFlag()))
                && (null == projJsVo.getDecideFlag() || "".equals(projJsVo.getDecideFlag()))
                && (null == decideAmountPercentFlag || "".equals(decideAmountPercentFlag))
                && (null == projJsVo.getGiveFlag() || "".equals(projJsVo.getGiveFlag()))
                && (null == decideAmountPercentFlag || "".equals(decideAmountPercentFlag))
                && (null == projectClassificationIds || "".equals(projectClassificationIds));
        if (flag) {
        } else {
            map.put("condition", "true");
        }
        List<ProjectJsCountVo> list = costProjectService.getProjectJsList(map, page, rows);
        int total = 0;
        if ("结算审核".equals(auditPriceType)) {
            total = costProjectService.getProjectJsCount(map);
        } else {
            total = costProjectService.getProjectJsCountAll(map);
        }
        List<ProjectJsCountVo> footer = new ArrayList<ProjectJsCountVo>();
        ProjectJsCountVo p = new ProjectJsCountVo();
        BigDecimal b = new BigDecimal(0);
        p.setProjectCode("汇总");
        p.setContractAmount(b);
        p.setGiveAmount(b);
        p.setMyAuditAmount(b);
        p.setAgencyAuditAmount(b);
        p.setDecideAmount(b);
        BigDecimal decideAmountPercent = new BigDecimal(100);
        //List<String> str=new ArrayList<String>();
        for (ProjectJsCountVo p2 : list) {
            if (null == p2.getSettlement()) {
            } else if (p2.getSettlement().equals("不需要")) {
                p2.setDecideAmountPercent(decideAmountPercent);
            }
            p.setContractAmount(p2.getContractAmount() == null ? p.getContractAmount() : p.getContractAmount().add(p2.getContractAmount()));
            p.setGiveAmount(p2.getGiveAmount() == null ? p.getGiveAmount() : p.getGiveAmount().add(p2.getGiveAmount()));
            p.setMyAuditAmount(p2.getMyAuditAmount() == null ? p.getMyAuditAmount() : p.getMyAuditAmount().add(p2.getMyAuditAmount()));
            p.setAgencyAuditAmount(p2.getAgencyAuditAmount() == null ? p.getAgencyAuditAmount() : p.getAgencyAuditAmount().add(p2.getAgencyAuditAmount()));
            p.setDecideAmount(p2.getDecideAmount() == null ? p.getDecideAmount() : p.getDecideAmount().add(p2.getDecideAmount()));
        }
        result.setRows(list);
        result.setTotal(total);
        Global.PROJECT_JSCOUNT_EXPORT_LIST = list;
        footer.add(p);
        result.setFooter(footer);
        return result;
    }
    
    @RequestMapping("/getProjectJsContractList")
    @ResponseBody
    public EUDataGridResult getProjectJsContractList(HttpServletRequest request, ProjectJsCountVo projJsVo) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> selectMap = JsonUtils.object2Map(projJsVo);
        String auditPriceType = request.getParameter("auditPriceType");
        Map<String, Object> map = new HashMap<String, Object>();
        String projectId = request.getParameter("projectId");
        String index = request.getParameter("index");
        map.putAll(selectMap);
        map.put("projectId", projectId);
        map.put("index", index);
        map.put("auditPriceType", auditPriceType);
        String decideAmountPercentFlag = request.getParameter("decideAmountPercentFlag");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String receiveTimeStart = request.getParameter("receiveTimeStart");
        String receiveTimeEnd = request.getParameter("receiveTimeEnd");
        String orgId = request.getParameter("orgId");
        if (null != orgId && !"".equals(orgId)) {
            map.put("selectDepartMent", orgId);
        }
        if (null != decideAmountPercentFlag && !"".equals(decideAmountPercentFlag)) {
            map.put("decideAmountPercentFlag", decideAmountPercentFlag);
        }
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
        }
        if (null != receiveTimeStart && !"".equals(receiveTimeStart)) {
            map.put("receiveTimeStart", receiveTimeStart + " 00:00:00");
        }
        if (null != receiveTimeEnd && !"".equals(receiveTimeEnd)) {
            map.put("receiveTimeEnd", receiveTimeEnd + " 24:00:00");
        }
        List<ProjectJsCountVo> list = costProjectService.getProjectJsContractList(map);
        BigDecimal b = new BigDecimal(100.00);
        for (ProjectJsCountVo p : list) {
            if (null == p.getContractAmount() || p.getContractAmount().intValue() == 0) {
                p.setDecideAmountPercent(b);
            }
        }
        result.setRows(list);
        return result;
    }

    //合同台账统计表
    @RequestMapping("/getContractJsList")
    @ResponseBody
    public EUDataGridResult getContractJsList(HttpServletRequest request, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        String contractCode = request.getParameter("contractCode");
        String contractName = request.getParameter("contractName");
        String partyB = request.getParameter("partyB");
        String contractType = request.getParameter("contractType");
        String decidedStartTime = request.getParameter("startTime2");
        String decidedEndTime = request.getParameter("endTime2");
        String deliveryFlag = request.getParameter("deliveryFlag");
        String decideFlag = request.getParameter("decideFlag");
        String recordNumber = request.getParameter("recordNumber");
        String ownerApproval = request.getParameter("ownerApproval");
        String receiveTimeStart = request.getParameter("receiveTimeStart");
        String receiveTimeEnd = request.getParameter("receiveTimeEnd");
        String decideAmountPercentFlag = request.getParameter("decideAmountPercentFlag");
        String giveFlag = request.getParameter("giveFlag");
        String orgId = request.getParameter("orgId");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String projectName = request.getParameter("projectName");
        String auditPriceType = request.getParameter("auditPriceType");
        String projectClassificationIds = request.getParameter("projectClassificationId");
        if (projectName != null && !"".equals(projectName)) {
            map.put("projectName", projectName);
        }
        if (null != orgId && !"".equals(orgId)) {
            map.put("selectDepartMent", orgId);
        }
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
        }
        if (projectClassificationIds != null && !"".equals(projectClassificationIds)) {
            String arrcat[] = projectClassificationIds.split(",");
            map.put("projClassificationId", arrcat);
            map.put("projClassificationIdLength", arrcat.length);
        }
        map.put("contractCode", contractCode);
        map.put("contractName", contractName);
        map.put("partyB", partyB);
        map.put("contractType", contractType);
        map.put("deliveryFlag", deliveryFlag);
        map.put("decideFlag", decideFlag);
        map.put("decideAmountPercentFlag", decideAmountPercentFlag);
        map.put("receiveTimeStart", receiveTimeStart);
        map.put("receiveTimeEnd", receiveTimeEnd);
        map.put("decidedStartTime", decidedStartTime);
        map.put("decidedEndTime", decidedEndTime);
        map.put("ownerApproval", ownerApproval);
        map.put("recordNumber", recordNumber);
        map.put("giveFlag", giveFlag);
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        List<ContractJsCountVo> list = null;
        int total = 0;
        if ("结算审核".equals(auditPriceType)) {
            list = costProjectService.getContractJsList2(map, page, rows);
            total = costProjectService.getContractJsCount2(map);
        } else {
            //查所有的合同
            list = costProjectService.getContractJsList(map, page, rows);
            total = costProjectService.getContractJsCount(map);
        }
        result.setRows(list);
        result.setTotal(total);
        Global.CONTRACT_JSCOUNT_EXPORT_LIST = list;
        List<ContractJsCountVo> footer = new ArrayList<ContractJsCountVo>();
        ContractJsCountVo c = new ContractJsCountVo();
        BigDecimal b = new BigDecimal(0);
        c.setPriority("汇总");
        c.setContractCount(0);
        c.setContractAmount(b);
        c.setSsContractCount(0);
        c.setSsContractAmount(b);
        c.setSsWsContractCount(0);
        c.setSsWsContractAmount(b);
        c.setSsZsContractCount(0);
        c.setSsZsContractAmount(b);
        c.setDsContractCount(0);
        c.setDsContractAmount(b);
        c.setDsWsContractCount(0);
        c.setDsWsContractAmount(b);
        c.setDsZsContractCount(0);
        c.setDsZsContractAmount(b);
        //页脚百分比汇总
        c.setSsJePercent(b);
        c.setSsCountPercent(b);
        c.setHtJsPercent(b);

        for (ContractJsCountVo c2 : list) {
        	
	        	Map<String, Object> map22 = new HashMap<String, Object>();
	            BigDecimal sshtje = new BigDecimal(0);
	            BigDecimal sszsje = new BigDecimal(0);
	            BigDecimal dshtje = new BigDecimal(0);
	            BigDecimal dszsje = new BigDecimal(0);
                map22.put("projectId", c2.getProjectId());
                map22.put("index", Integer.parseInt(c2.getPriority()) + "");
                List<ContractJsCountVo> list22 = costProjectService.getContractJsGroup(map22);
                for (ContractJsCountVo vo2 : list22) {
                	sshtje=(vo2.getSsContractAmount()!=null ? sshtje.add(vo2.getSsContractAmount()):sshtje);
                   	sszsje=(vo2.getSsZsContractAmount()!=null?sszsje.add(vo2.getSsZsContractAmount()):sszsje);
                   	dshtje=(vo2.getDsContractAmount()!=null?dshtje.add(vo2.getDsContractAmount()):dshtje);
                   	dszsje=(vo2.getDsZsContractAmount()!=null?dszsje.add(vo2.getDsZsContractAmount()):dszsje);
                }
                c2.setSsContractAmount(sshtje);
                c2.setSsZsContractAmount(sszsje);
                c2.setDsContractAmount(dshtje);
                c2.setDsZsContractAmount(dszsje);
        	   /*BigDecimal sshtje = new BigDecimal(0);
               BigDecimal sszsje = new BigDecimal(0);
               BigDecimal dshtje = new BigDecimal(0);
               BigDecimal dszsje = new BigDecimal(0);
               for (ContractJsCountVo vo : list) {
               	sshtje=(null!=vo.getSsContractAmount()?vo.getSsContractAmount().add(sshtje):sshtje);
               	sszsje=(null!=vo.getSsZsContractAmount()?vo.getSsZsContractAmount().add(sshtje):sszsje);
               	dshtje=(null!=vo.getDsContractAmount()?vo.getDsContractAmount().add(sshtje):dshtje);
               	dszsje=(null!=vo.getDsZsContractAmount()?vo.getDsZsContractAmount().add(sshtje):dszsje);
               }*/
              //没有结算任务的主合同 送审定审情况汇总
           /* Map<String, Object> map22 = new HashMap<String, Object>();
            BigDecimal bd2 = new BigDecimal("0.00");
            int sshtsl = 0;
            int sszshtsl = 0;
            int dshtsl = 0;
            int dszshtsl = 0;
                map22.put("projectId", c2.getProjectId());
                map22.put("index", Integer.parseInt(c2.getPriority()) + "");
                List<ContractJsCountVo> list22 = costProjectService.getContractJsGroup(map22);
                for (ContractJsCountVo vo2 : list22) {
                    if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsContractCount(vo2.getContractCount());
                        }
                    } else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsContractCount(vo2.getSsContractCount() + vo2.getSumZscount());
                        }
                    }

                    if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsZsContractCount(vo2.getContractCount());
                        }
                    } else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsZsContractCount(vo2.getSsZsContractCount() + vo2.getSumZscount());
                        }
                    }

                    if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsContractCount(vo2.getContractCount());
                        }
                    }else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsContractCount(vo2.getDsContractCount() + vo2.getSumZscount());
                        }
                    }

                    if (null == vo2.getDecideAmount() || bd2.equals(vo2.getDecideAmount())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsZsContractCount(vo2.getContractCount());
                        }
                    }else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsZsContractCount(vo2.getDsZsContractCount() + vo2.getSumZscount());
                        }
                    }
                    sshtsl += vo2.getSsContractCount();
                    sszshtsl += vo2.getSsZsContractCount();
                    dshtsl += vo2.getDsContractCount();
                    dszshtsl += vo2.getDsZsContractCount();
                }
            c2.setSsContractCount(sshtsl);
            c2.setSsZsContractCount(sszshtsl);
            c2.setDsContractCount(dshtsl);
            c2.setDsZsContractCount(dszshtsl);*/

            //页脚汇总
            c.setContractCount(c2.getContractCount() == 0 ? c.getContractCount() : (c.getContractCount() + c2.getContractCount()));
            c.setContractAmount(c2.getContractAmount() == null ? c.getContractAmount() : c.getContractAmount().add(c2.getContractAmount()));
            c.setSsContractCount(c2.getSsContractCount() == 0 ? c.getSsContractCount() : (c.getSsContractCount() + c2.getSsContractCount()));
            c.setSsContractAmount(c2.getSsContractAmount() == null ? c.getSsContractAmount() : c.getSsContractAmount().add(c2.getSsContractAmount()));
            c.setSsWsContractCount(c2.getSsWsContractCount() == 0 ? c.getSsWsContractCount() : (c.getSsWsContractCount() + c2.getSsWsContractCount()));
            c.setSsWsContractAmount(c2.getSsWsContractAmount() == null ? c.getSsWsContractAmount() : c.getSsWsContractAmount().add(c2.getSsWsContractAmount()));
            c.setSsZsContractCount(c2.getSsZsContractCount() == 0 ? c.getSsZsContractCount() : (c.getSsZsContractCount() + c2.getSsZsContractCount()));
            c.setSsZsContractAmount(c2.getSsZsContractAmount() == null ? c.getSsZsContractAmount() : c.getSsZsContractAmount().add(c2.getSsZsContractAmount()));
            c.setDsContractCount(c2.getDsContractCount() == 0 ? c.getDsContractCount() : (c.getDsContractCount() + c2.getDsContractCount()));
            c.setDsContractAmount(c2.getDsContractAmount() == null ? c.getDsContractAmount() : c.getDsContractAmount().add(c2.getDsContractAmount()));
            c.setDsWsContractCount(c2.getDsWsContractCount() == 0 ? c.getDsWsContractCount() : (c.getDsWsContractCount() + c2.getDsWsContractCount()));
            c.setDsWsContractAmount(c2.getDsWsContractAmount() == null ? c.getDsWsContractAmount() : c.getDsWsContractAmount().add(c2.getDsWsContractAmount()));
            c.setDsZsContractCount(c2.getDsZsContractCount() == 0 ? c.getDsZsContractCount() : (c.getDsZsContractCount() + c2.getDsZsContractCount()));
            c.setDsZsContractAmount(c2.getDsZsContractAmount() == null ? c.getDsZsContractAmount() : c.getDsZsContractAmount().add(c2.getDsZsContractAmount()));

            c.setSsJePercent((c2.getSsJePercent() == null ? c.getSsJePercent() : c.getSsJePercent().add(c2.getSsJePercent())));
            c.setSsCountPercent((c2.getSsCountPercent() == null ? c.getSsCountPercent() : c.getSsCountPercent().add(c2.getSsCountPercent())));
            c.setHtJsPercent((c2.getHtJsPercent() == null ? c.getHtJsPercent() : c.getHtJsPercent().add(c2.getHtJsPercent())));
        }
        
        BigDecimal ssContractAmount=c.getSsContractAmount().multiply(new BigDecimal(10000).setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal dsContractAmount=c.getDsContractAmount().multiply(new BigDecimal(10000).setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal contractCount=new BigDecimal(c.getContractCount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal dsContractCount=new BigDecimal(c.getDsContractCount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal ssContractCount=new BigDecimal(c.getSsContractCount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        
        BigDecimal temSsJePercent=(dsContractAmount).divide(ssContractAmount,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal temSsCountPercent=dsContractCount.divide(ssContractCount,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal temHtJsPercent=dsContractCount.divide(contractCount,4, BigDecimal.ROUND_HALF_UP);
        
        c.setSsJePercent(temSsJePercent.multiply(new BigDecimal(100)));
        c.setSsCountPercent(temSsCountPercent.multiply(new BigDecimal(100)));
        c.setHtJsPercent(temHtJsPercent.multiply(new BigDecimal(100)));
        footer.add(c);
        result.setFooter(footer);
        return result;
    }

    /**
     * Created by ZYL on 2019/10/12
     * 合同台账统计表展开
     */
    @RequestMapping("/getContractJsGroup")
    @ResponseBody
    public EUDataGridResult getContractJsGroup(HttpServletRequest request) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        String projectId = request.getParameter("projectId");
        String index = request.getParameter("index");
        map.put("projectId", projectId);
        map.put("index", index);
        List<ContractJsCountVo> list = costProjectService.getContractJsGroup(map);
     
          
         BigDecimal bd2 = new BigDecimal("0.00");
         for (ContractJsCountVo vo2 : list) {
            /*if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setSsContractCount(vo2.getContractCount());
                }
            } else {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setSsContractCount(vo2.getSsContractCount() + vo2.getSumZscount());
                }
            }

            if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setSsZsContractCount(vo2.getContractCount());
                }
            } else {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setSsZsContractCount(vo2.getSsZsContractCount() + vo2.getSumZscount());
                }
            }

            if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setDsContractCount(vo2.getContractCount());
                }
            }else {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setDsContractCount(vo2.getDsContractCount() + vo2.getSumZscount());
                }
            }
            if (null == vo2.getDecideAmount() || bd2.equals(vo2.getDecideAmount())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setDsZsContractCount(vo2.getContractCount());
                }
            }else {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setDsZsContractCount(vo2.getDsZsContractCount() + vo2.getSumZscount());
                }
            }*/
            //
            if (null == vo2.getMyAuditAmont() || new BigDecimal("0.00").equals(vo2.getMyAuditAmont())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setSsJePercent(new BigDecimal("100"));
                }
            }
            if (null == vo2.getMyAuditAmont() || new BigDecimal("0.00").equals(vo2.getMyAuditAmont())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setSsCountPercent(new BigDecimal("100"));
                }
            }
            if (null == vo2.getMyAuditAmont() || new BigDecimal("0.00").equals(vo2.getMyAuditAmont())) {
                if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                    vo2.setHtJsPercent(new BigDecimal("100"));
                }
            }
        }
        if(null!=list&&list.size()>0){
        	Iterator<ContractJsCountVo> iterator = list.iterator();
        	while (iterator.hasNext()) {
        		ContractJsCountVo vo = iterator.next();
        		if (vo.getExecutiveDepartment() == null && vo.getContractCount() == 0) {
        			iterator.remove();
        		}
        	}
        }
        result.setRows(list);
        return result;
    }
    /*
     * 合同结算统计表
     */
    @RequestMapping("/getContractJsList2")
    @ResponseBody
    public EUDataGridResult getContractJsList2(HttpServletRequest request, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String projectName = request.getParameter("projectName");
        String orgId = request.getParameter("orgId");
        if (projectName != null && !"".equals(projectName)) {
            map.put("projectName", projectName);
        }
        if (null != orgId && !"".equals(orgId)) {
            map.put("selectDepartMent", orgId);
        }
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
        }
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        List<ContractJsCountVo> list = costProjectService.getContractJsList2(map, page, rows);
        result.setRows(list);
        int total = costProjectService.getContractJsCount2(map);
        result.setTotal(total);
        Global.CONTRACT_JSCOUNT_EXPORT_LIST2 = list;
        List<ContractJsCountVo> footer = new ArrayList<ContractJsCountVo>();
        ContractJsCountVo c = new ContractJsCountVo();
        BigDecimal b = new BigDecimal(0);
        c.setPriority("汇总");
        c.setContractCount(0);
        c.setContractAmount(b);
        c.setSsContractCount(0);
        c.setSsContractAmount(b);
        c.setSsWsContractCount(0);
        c.setSsWsContractAmount(b);
        c.setSsZsContractCount(0);
        c.setSsZsContractAmount(b);
        c.setDsContractCount(0);
        c.setDsContractAmount(b);
        c.setDsWsContractCount(0);
        c.setDsWsContractAmount(b);
        c.setDsZsContractCount(0);
        c.setDsZsContractAmount(b);
        for (ContractJsCountVo c2 : list) {
            c.setContractCount(c2.getContractCount() == 0 ? c.getContractCount() : (c.getContractCount() + c2.getContractCount()));
            c.setContractAmount(c2.getContractAmount() == null ? c.getContractAmount() : c.getContractAmount().add(c2.getContractAmount()));
            c.setSsContractCount(c2.getSsContractCount() == 0 ? c.getSsContractCount() : (c.getSsContractCount() + c2.getSsContractCount()));
            c.setSsContractAmount(c2.getSsContractAmount() == null ? c.getSsContractAmount() : c.getSsContractAmount().add(c2.getSsContractAmount()));
            c.setSsWsContractCount(c2.getSsWsContractCount() == 0 ? c.getSsWsContractCount() : (c.getSsWsContractCount() + c2.getSsWsContractCount()));
            c.setSsWsContractAmount(c2.getSsWsContractAmount() == null ? c.getSsWsContractAmount() : c.getSsWsContractAmount().add(c2.getSsWsContractAmount()));
            c.setSsZsContractCount(c2.getSsZsContractCount() == 0 ? c.getSsZsContractCount() : (c.getSsZsContractCount() + c2.getSsZsContractCount()));
            c.setSsZsContractAmount(c2.getSsZsContractAmount() == null ? c.getSsZsContractAmount() : c.getSsZsContractAmount().add(c2.getSsZsContractAmount()));
            c.setDsContractCount(c2.getDsContractCount() == 0 ? c.getDsContractCount() : (c.getDsContractCount() + c2.getDsContractCount()));
            c.setDsContractAmount(c2.getDsContractAmount() == null ? c.getDsContractAmount() : c.getDsContractAmount().add(c2.getDsContractAmount()));
            c.setDsWsContractCount(c2.getDsWsContractCount() == 0 ? c.getDsWsContractCount() : (c.getDsWsContractCount() + c2.getDsWsContractCount()));
            c.setDsWsContractAmount(c2.getDsWsContractAmount() == null ? c.getDsWsContractAmount() : c.getDsWsContractAmount().add(c2.getDsWsContractAmount()));
            c.setDsZsContractCount(c2.getDsZsContractCount() == 0 ? c.getDsZsContractCount() : (c.getDsZsContractCount() + c2.getDsZsContractCount()));
            c.setDsZsContractAmount(c2.getDsZsContractAmount() == null ? c.getDsZsContractAmount() : c.getDsZsContractAmount().add(c2.getDsZsContractAmount()));
        }
        footer.add(c);
        result.setFooter(footer);
        return result;
    }

    /**
     * 结算台账导出
     */
    @RequestMapping("/exportProjectJsList")
    @ResponseBody
    public void exportProjectJsList(HttpServletRequest request, HttpServletResponse response, ProjectJsCountVo projJsVo) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/template/report/project_jscount_template.xlsx");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(new Date());
        String fileName = "结算台账_" + dateString + ".xlsx";
        String downloadFileName = new String(fileName.getBytes(), "ISO8859-1");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attactment; fileName=" + downloadFileName);
        ServletOutputStream out = response.getOutputStream();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> selectMap = JsonUtils.object2Map(projJsVo);
        String decideAmountPercentFlag = request.getParameter("decideAmountPercentFlag");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String receiveTimeStart = request.getParameter("receiveTimeStart");
        String receiveTimeEnd = request.getParameter("receiveTimeEnd");
        String orgId = request.getParameter("orgId");
        String projectClassificationIds = request.getParameter("ClassificationId");
        map.putAll(selectMap);
        if (null != orgId && !"".equals(orgId)) {
            map.put("selectDepartMent", orgId);
        }
        if (null != decideAmountPercentFlag && !"".equals(decideAmountPercentFlag)) {
            map.put("decideAmountPercentFlag", decideAmountPercentFlag);
        }
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
        }
        if (null != receiveTimeStart && !"".equals(receiveTimeStart)) {
            map.put("receiveTimeStart", receiveTimeStart + " 00:00:00");
        }
        if (null != receiveTimeEnd && !"".equals(receiveTimeEnd)) {
            map.put("receiveTimeEnd", receiveTimeEnd + " 24:00:00");
        }
        if (projectClassificationIds != null && !"".equals(projectClassificationIds)) {
            String arrcat[] = projectClassificationIds.split(",");
            map.put("projClassificationId", arrcat);
            map.put("projClassificationIdLength", arrcat.length);
        }
        String auditPriceType = request.getParameter("auditPriceType");
        map.put("auditPriceType", auditPriceType);
        //判断除了是否结算以外还带有别的条件，带条件为false，不带条件为true
        boolean flag = (null == projJsVo.getProjectName() || "".equals(projJsVo.getProjectName()))
                && (null == projJsVo.getContractName() || "".equals(projJsVo.getContractName()))
                && (null == projJsVo.getContractCode() || "".equals(projJsVo.getContractCode()))
                && (null == projJsVo.getPartyB() || "".equals(projJsVo.getPartyB()))
                && (null == orgId || "".equals(orgId))
                && (null == projJsVo.getContractType() || "".equals(projJsVo.getContractType()))
                && (null == receiveTimeStart || "".equals(receiveTimeStart))
                && (null == receiveTimeEnd || "".equals(receiveTimeEnd))
                && (null == startTime || "".equals(startTime))
                && (null == endTime || "".equals(endTime))
                && (null == projJsVo.getRecordNumber() || "".equals(projJsVo.getRecordNumber()))
                && (null == projJsVo.getOwnerApproval() || "".equals(projJsVo.getOwnerApproval()))
                && (null == projJsVo.getDeliveryFlag() || "".equals(projJsVo.getDeliveryFlag()))
                && (null == projJsVo.getDecideFlag() || "".equals(projJsVo.getDecideFlag()))
                && (null == decideAmountPercentFlag || "".equals(decideAmountPercentFlag))
                && (null == projJsVo.getGiveFlag() || "".equals(projJsVo.getGiveFlag()))
                && (null == decideAmountPercentFlag || "".equals(decideAmountPercentFlag))
                && (null == projectClassificationIds || "".equals(projectClassificationIds));
        if (flag) {
        } else {
            map.put("condition", "true");
        }
        BigDecimal decideAmountPercent = new BigDecimal(100.00);
        //导出全部不分页;//Global.PROJECT_JSCOUNT_EXPORT_LIST;
        List<ProjectJsCountVo> list = costProjectService.getProjectJsList(map, 1, 0);
        for (ProjectJsCountVo pj : list) {
            if (null == pj.getSettlement()) {
            } else if (pj.getSettlement().equals("不需要")) {
                pj.setDecideAmountPercent(decideAmountPercent);
            }
            if (null == pj.getContractAmount() || pj.getContractAmount().intValue() == 0) {
                pj.setDecideAmountPercent(decideAmountPercent);
            }
        }
        costProjectService.exportProjectJsList(path, out, list, map);
    }

    //合同台账统计表导出
    @ResponseBody
    @RequestMapping("/exportContractJsList")
    public void exportContractJsList(HttpServletRequest request, HttpServletResponse response, ContractJsCountVo contractJsCountVo) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(new Date());
            ServletOutputStream out = response.getOutputStream();
            String path = request.getSession().getServletContext().getRealPath("/template/report/contract_jscount_template.xlsx");
            String auditPriceType = request.getParameter("auditPriceType");
            String projectIds = request.getParameter("projectIds");
            Map<String, Object> map = new HashMap<String, Object>();
            List<ContractJsCountVo> list = new ArrayList<ContractJsCountVo>();
            List<ContractJsCountVo> list2 = new ArrayList<ContractJsCountVo>();
            if (null != projectIds && !"".equals(projectIds)) {
                String arr[] = projectIds.split(",");
                int i = 1;
                for (String id : arr) {
                    map.put("projectId", id);
                    if ("结算审核".equals(auditPriceType)) {
                        list2 = costProjectService.getContractJsList2(map, 1, 0);
                        list2.get(0).setPriority(i + "");
                    } else {
                        //查所有的合同
                        list2 = costProjectService.getContractJsList(map, 1, 0);
                        list2.get(0).setPriority(i + "");
                    }
                    list.add(list2.get(0));
                    i++;
                    map.clear();
                }
            } else {
                Map<String, Object> selectMap = JsonUtils.object2Map(contractJsCountVo);
                map.putAll(selectMap);
                String projectName = request.getParameter("projectName");
                String projectClassificationIds = request.getParameter("classId");
                String startTime = request.getParameter("startTime");
                String endTime = request.getParameter("endTime");
                String orgId = request.getParameter("orgId");
                if (null != orgId && !"".equals(orgId)) {
                    map.put("selectDepartMent", orgId);
                }
                if (null != startTime && !"".equals(startTime)) {
                    map.put("startTime", startTime + " 00:00:00");
                }
                if (null != endTime && !"".equals(endTime)) {
                    map.put("endTime", endTime + " 24:00:00");
                }
                if (projectClassificationIds != null && !"".equals(projectClassificationIds)) {
                    String arrcat[] = projectClassificationIds.split(",");
                    map.put("projClassificationId", arrcat);
                    map.put("projClassificationIdLength", arrcat.length);
                }
                if (null != orgId && !"".equals(orgId)) {
                    map.put("selectDepartMent", orgId);
                }
                map.put("auditPriceType", auditPriceType);
                map.put("projectName", projectName);
                if ("结算审核".equals(auditPriceType)) {
                    list = costProjectService.getContractJsList2(map, 1, 0);
                } else {
                    //查所有的合同
                    list = costProjectService.getContractJsList(map, 1, 0);
                }
            }
            ContractJsCountVo c = new ContractJsCountVo();
            BigDecimal b = new BigDecimal(0);
            //页脚百分比汇总
            c.setSsJePercent(b);
            c.setSsCountPercent(b);
            c.setHtJsPercent(b);
            for (ContractJsCountVo c2 : list) {
	        	Map<String, Object> map22 = new HashMap<String, Object>();
	            BigDecimal sshtje = new BigDecimal(0);
	            BigDecimal sszsje = new BigDecimal(0);
	            BigDecimal dshtje = new BigDecimal(0);
	            BigDecimal dszsje = new BigDecimal(0);
	            map22.put("projectId", c2.getProjectId());
	            map22.put("index", Integer.parseInt(c2.getPriority()) + "");
	            List<ContractJsCountVo> list22 = costProjectService.getContractJsGroup(map22);
	            for (ContractJsCountVo vo2 : list22) {
	            	sshtje=(vo2.getSsContractAmount()!=null ? sshtje.add(vo2.getSsContractAmount()):sshtje);
	               	sszsje=(vo2.getSsZsContractAmount()!=null?sszsje.add(vo2.getSsZsContractAmount()):sszsje);
	               	dshtje=(vo2.getDsContractAmount()!=null?dshtje.add(vo2.getDsContractAmount()):dshtje);
	               	dszsje=(vo2.getDsZsContractAmount()!=null?dszsje.add(vo2.getDsZsContractAmount()):dszsje);
	            }
	            c2.setSsContractAmount(sshtje);
	            c2.setSsZsContractAmount(sszsje);
	            c2.setDsContractAmount(dshtje);
	            c2.setDsZsContractAmount(dszsje);
	            
	            c.setSsJePercent((c2.getSsJePercent() == null ? c.getSsJePercent() : c.getSsJePercent().add(c2.getSsJePercent())));
	            c.setSsCountPercent((c2.getSsCountPercent() == null ? c.getSsCountPercent() : c.getSsCountPercent().add(c2.getSsCountPercent())));
	            c.setHtJsPercent((c2.getHtJsPercent() == null ? c.getHtJsPercent() : c.getHtJsPercent().add(c2.getHtJsPercent())));
            }
            c.setSsJePercent(c.getSsJePercent().divide(new BigDecimal(list.size()), 2, BigDecimal.ROUND_HALF_UP));
            c.setSsCountPercent(c.getSsCountPercent().divide(new BigDecimal(list.size()), 2, BigDecimal.ROUND_HALF_UP));
            c.setHtJsPercent(c.getHtJsPercent().divide(new BigDecimal(list.size()), 2, BigDecimal.ROUND_HALF_UP));
            /*Map<String, Object> map22 = new HashMap<String, Object>();
            BigDecimal bd2 = new BigDecimal("0.00");
            int ssAllcount=0;
            BigDecimal ssJePercent=new BigDecimal(0);
            BigDecimal ssCountPercent=new BigDecimal(0);
            BigDecimal htJsPercent=new BigDecimal(0);
            for (ContractJsCountVo vo1 : list) {
            	int sshtsl = 0;
                int sszshtsl = 0;
                int dshtsl = 0;
                int dszshtsl = 0;
               
                map22.put("projectId", vo1.getProjectId());
                map22.put("index", Integer.parseInt(vo1.getPriority()) + "");
                List<ContractJsCountVo> list22 = costProjectService.getContractJsGroup(map22);
                //listSize=new BigDecimal(list22.size());
                for (ContractJsCountVo vo2 : list22) {
                    if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsContractCount(vo2.getContractCount());
                        }
                    } else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsContractCount(vo2.getSsContractCount() + vo2.getSumZscount());
                        }
                    }

                    if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsZsContractCount(vo2.getContractCount());
                        }
                    } else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsZsContractCount(vo2.getSsZsContractCount() + vo2.getSumZscount());
                        }
                    }

                    if (null == vo2.getMyAuditAmont() || bd2.equals(vo2.getMyAuditAmont())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsContractCount(vo2.getContractCount());
                        }
                    }else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsContractCount(vo2.getDsContractCount() + vo2.getSumZscount());
                        }
                    }
                    
                    if (null == vo2.getDecideAmount() || bd2.equals(vo2.getDecideAmount())) {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsZsContractCount(vo2.getContractCount());
                        }
                    }else {
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setDsZsContractCount(vo2.getDsZsContractCount() + vo2.getSumZscount());
                        }
                    }
                    
                    if(null==vo2.getSsJePercent()){
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsJePercent(new BigDecimal("100"));
                        }
                    }
                    if(null==vo2.getSsCountPercent()){
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setSsCountPercent(new BigDecimal("100"));
                        }
                    }
                    if(null==vo2.getHtJsPercent()){
                        if (vo2.getSettlementStr().indexOf("不需要") >= 0) {
                            vo2.setHtJsPercent(new BigDecimal("100"));
                        }
                    }
                    sshtsl += vo2.getSsContractCount();
                    sszshtsl += vo2.getSsZsContractCount();
                    dshtsl += vo2.getDsContractCount();
                    dszshtsl += vo2.getDsZsContractCount();
                    
                    ssJePercent=vo2.getSsJePercent() == null ? ssJePercent : ssJePercent.add(vo2.getSsJePercent());
                    ssCountPercent=vo2.getSsCountPercent() == null ? ssCountPercent : ssCountPercent.add(vo2.getSsCountPercent());
                    htJsPercent=vo2.getHtJsPercent() == null ? htJsPercent : htJsPercent.add(vo2.getHtJsPercent());

                }
                vo1.setChildrenSize(list22.size());
                vo1.setSsContractCount(sshtsl);
                vo1.setSsZsContractCount(sszshtsl);
                vo1.setDsContractCount(dshtsl);
                vo1.setDsZsContractCount(dszshtsl);

                vo1.setSsJePercent(ssJePercent);
                vo1.setSsCountPercent(ssCountPercent);
                vo1.setHtJsPercent(htJsPercent);
                ssAllcount+=sshtsl;
            }*/

            //System.out.println(ssAllcount);
            String fileName = "结算台账统计表_" + dateString + ".xlsx";
            String downloadFileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attactment; fileName=" + downloadFileName);
            costProjectService.exportContractJsList("结算台账统计表", list, path, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exportContractJsList2")
    @ResponseBody
    public void exportContractJsLis2(HttpServletRequest request, HttpServletResponse response, ContractJsCountVo contractJsCountVo) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(new Date());
            ServletOutputStream out = response.getOutputStream();
            String path = request.getSession().getServletContext().getRealPath("/template/report/contract_jscount_template.xlsx");
            String fileName = "合同结算统计表_" + dateString + ".xlsx";
            String downloadFileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attactment; fileName=" + downloadFileName);
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> selectMap = JsonUtils.object2Map(contractJsCountVo);
            map.putAll(selectMap);
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            String orgId = request.getParameter("orgId");
            if (null != orgId && !"".equals(orgId)) {
                map.put("selectDepartMent", orgId);
            }
            if (null != startTime && !"".equals(startTime)) {
                map.put("startTime", startTime + " 00:00:00");
            }
            if (null != endTime && !"".equals(endTime)) {
                map.put("endTime", endTime + " 24:00:00");
            }
            List<ContractJsCountVo> list = costProjectService.getContractJsList2(map, 1, 0);//导出全部不分页;//Global.CONTRACT_JSCOUNT_EXPORT_LIST2;
            costProjectService.exportContractJsList("合同结算统计表", list, path, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("importProject")
    public GlobalResult importProject(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        List<CostProject> projectList = costProjectService.importProject(request);
        try {
            if (null != projectList && projectList.size() > 0) {
                result.setData(projectList);
                result.setStatus(300);
            } else {
                result.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @RequestMapping("/exportProjectList")
    @ResponseBody
    public void exportProjectList(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = Global.PRO_START_TIME;
            String endTime = Global.PRO_END_TIME;
            List<CostProject> list = Global.PRO_EXPORT_LIST;
            String fileName = "";
            if (null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)) {
                fileName = "项目台账_" + startTime + "-" + endTime + ".xlsx";
            } else {
                String dateString = formatter.format(new Date());
                fileName = "项目台账_" + dateString + ".xlsx";
            }
            String downloadFileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attactment; fileName=" + downloadFileName);
            String column = request.getParameter("column");
            String columnTitle = request.getParameter("columnTitle");

            String[] columnName = column.split(",");
            String[] columnT = URLDecoder.decode(columnTitle, "UTF-8").split(",");
            ServletOutputStream out = response.getOutputStream();
            costProjectService.exportProject(columnName, columnT, out, list);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getProjectZblist")
    @ResponseBody
    public EUDataGridResult getProjectZblist(HttpServletRequest request, CostProject project, int page, int rows) {
        //SysUser user= shiroUtil.getInstance().currentUser();
        EUDataGridResult result = new EUDataGridResult();
        Map<String, Object> map = new HashMap<String, Object>();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String projectClassificationIds = request.getParameter("projectClassificationId");
        Map<String, Object> selectMap = JsonUtils.object2Map(project);
        map.putAll(selectMap);
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
            Global.PRO_START_TIME = startTime;
        }
        if (null != endTime && !"".equals(endTime)) {
            map.put("endTime", endTime + " 24:00:00");
            Global.PRO_END_TIME = endTime;
        }
        if (projectClassificationIds != null && !"".equals(projectClassificationIds)) {
            String arrcat[] = projectClassificationIds.split(",");
            map.put("projClassificationId", arrcat);
            map.put("projClassificationIdLength", arrcat.length);
        }
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        List<CostProject> list = costProjectService.getList(map);
        result.setRows(list);
        Global.PRO_EXPORT_LIST = list;
        int total = costProjectService.getCount(map);
        result.setTotal(total);
        return result;
    }

    @RequestMapping("/exportProjectZb")
    @ResponseBody
    public void exportProjectZb(HttpServletRequest request, CostProject project, HttpServletResponse response) {
        List<CostProject> list = new ArrayList<CostProject>();
        String ids = request.getParameter("ids");
        try {
            ServletOutputStream out = response.getOutputStream();
            if (null != ids && !"".equals(ids)) {
                String arr[] = ids.split(",");
                for (String id : arr) {
                    CostProject doc = costProjectService.selectByPrimaryKey(id);
                    list.add(doc);
                }
            } else {
                list = costProjectService.getList(null);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = Global.PRO_START_TIME;
            String endTime = Global.PRO_END_TIME;
            String path = request.getSession().getServletContext().getRealPath("/template/report/projectZb_template.xlsx");
            String newPath = "";
            if (null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)) {
                newPath = request.getSession().getServletContext().getRealPath("/template/temp/项目指标_" + startTime + "-" + endTime + ".xlsx");
            } else {
                String dateString = formatter.format(new Date());
                newPath = request.getSession().getServletContext().getRealPath("/template/temp/项目指标_" + dateString + ".xlsx");
            }
            costProjectService.exportProjectZb(list, path, newPath, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ZYL on 2019/5/28
     */
    @ResponseBody
    @RequestMapping("/testTask")
    public String testTask() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "审结");
        //获取到所有状态为审结的任务
        List<CostTask> taskList = costTaskService.selectListByMap(map);
        int i = 1;
        Calendar calendar = Calendar.getInstance();
        for (CostTask costTask : taskList) {
            if (null == costTask.getCheckExplain() || "".equals(costTask.getCheckExplain())) {
                //意见为null，直接新增一条意见为审结的意见记录
                //判断之前是否有审结的意见记录
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("status", "审结");
                map2.put("taskId", costTask.getId());
                List<CostTaskOpinion> list = costTaskOpinionService.selectListByMap(map2);
                if (null != list && list.size() > 0) {
                    //如果原来审结状态的任务有审结的意见，改为审结
                    CostTaskOpinion to = list.get(0);
                    to.setOpinion("审结");
                    costTaskOpinionService.updateByPrimaryKeySelective(to);
                } else {
                    CostTaskOpinion c = new CostTaskOpinion();
                    c.setCreater(costTask.getPersonLiable());
                    c.setId(UUID.randomUUID().toString().replace("-", ""));
                    c.setUpdateTime(new Date());
                    c.setOpinion("审结");
                    calendar.add(Calendar.SECOND, 1);
                    c.setCreateTime(calendar.getTime());
                    c.setTaskId(costTask.getId());
                    c.setStatus("审结");
                    costTaskOpinionService.insertSelective(c);
                }
            } else {
                //当前意见不为null，新增一条办内审核完的意见记录，意见为当前意见，同时新增一条审结意见
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("status", "办内审核完");
                map2.put("taskId", costTask.getId());
                List<CostTaskOpinion> list1 = costTaskOpinionService.selectListByMap(map2);
                if (null != list1 && list1.size() > 0) {
                    //如果原来审结状态的任务有审结的意见，改为审结
                    CostTaskOpinion to = list1.get(0);
                    to.setOpinion(costTask.getCheckExplain());
                    costTaskOpinionService.updateByPrimaryKeySelective(to);
                } else {
                    CostTaskOpinion c3 = new CostTaskOpinion();
                    c3.setStatus("办内审核完");
                    c3.setTaskId(costTask.getId());
                    c3.setCreater(costTask.getPersonLiable());
                    c3.setId(UUID.randomUUID().toString().replace("-", ""));
                    c3.setUpdateTime(new Date());
                    c3.setOpinion(costTask.getCheckExplain());
                    costTaskOpinionService.insertSelective(c3);
                }
                map2.clear();
                map2.put("status", "审结");
                map2.put("taskId", costTask.getId());
                List<CostTaskOpinion> list = costTaskOpinionService.selectListByMap(map2);
                //判断之前是否有审结的意见记录
                if (null != list && list.size() > 0) {
                    //如果原来审结状态的任务有审结的意见，改为审结
                    CostTaskOpinion to = list.get(0);
                    to.setOpinion("审结");
                    costTaskOpinionService.updateByPrimaryKeySelective(to);
                } else {
                    CostTaskOpinion c3 = new CostTaskOpinion();
                    c3.setStatus("审结");
                    c3.setTaskId(costTask.getId());
                    c3.setCreater(costTask.getPersonLiable());
                    c3.setId(UUID.randomUUID().toString().replace("-", ""));
                    c3.setUpdateTime(new Date());
                    calendar.add(Calendar.SECOND, 1);
                    c3.setCreateTime(calendar.getTime());
                    c3.setOpinion("审结");
                    costTaskOpinionService.insertSelective(c3);
                }
            }
            i++;
        }
        return "Hello World!";
    }


    @ResponseBody
    @RequestMapping("/aaa")
    public void exportContract(HttpServletRequest request, CostContract contract, HttpServletResponse response) {
        try {
            List<CostContract> str = new ArrayList<CostContract>();
            List<CostProject> listP = costProjectService.getList(null);
            for (CostProject p : listP) {
                //同一个项目下合同编号尾数后三位相同的合同
                try {
                    String codeStr = costProjectService.getBBB(p.getId());
                    if (null != codeStr && !"".equals(codeStr)) {
                        String arr[] = codeStr.split(",");
                        for (String code : arr) {
                            CostContract con = costContractService.selectByContractCode(code);
                            con.setProjectNameShow(p.getName());
                            str.add(con);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = Global.CON_START_TIME;
            String endTime = Global.CON_END_TIME;
            String path = request.getSession().getServletContext().getRealPath("/template/report/contract_template.xlsx");
            String newPath = "";
            if (null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)) {
                newPath = request.getSession().getServletContext().getRealPath("/template/temp/合同台账导出_" + startTime + "-" + endTime + ".xlsx");
            } else {
                String dateString = formatter.format(new Date());
                newPath = request.getSession().getServletContext().getRealPath("/template/temp/合同台账导出_" + dateString + ".xlsx");
            }
            Map<String, List<List<String>>> date = new HashMap<String, List<List<String>>>();
            List<List<String>> rowList = new ArrayList<List<String>>();
            List<String> cellList = new ArrayList<String>();
            for (CostContract l : str) {
                cellList = new ArrayList<String>();
                cellList.add(l.getProjectNameShow());
                cellList.add(l.getCode());
                cellList.add(l.getId());
                cellList.add(l.getName());
                cellList.add(l.getPartyB());
                cellList.add(l.getContractAmount() != null ? l.getContractAmount() + "" : "");
                cellList.add(l.getSettlementAmount() != null ? l.getContractAmount() + "" : "");
                String department = l.getExecutiveDepartment();
                if (department.contains(".0")) {
                    department = department.replace(".0", "");
                }
                if (null != department && !"".equals(department)) {
                    SysOrg org = sysOrgService.selectByPrimaryKey(department);
                    cellList.add(org.getName());
                } else {
                    cellList.add("");
                }
                rowList.add(cellList);
            }
            date.put("合同台账", rowList);
            try {
                ExcelUtil.copyExcel(1, date, path, newPath, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping("/bbb")
    public void exportContrac(HttpServletRequest request, CostContract contract, HttpServletResponse response) {
        try {
            List<CostContract> str = new ArrayList<CostContract>();
            List<ProjectJsCountVo> listP = Global.PROJECT_JSCOUNT_EXPORT_LIST;
            for (ProjectJsCountVo p : listP) {
                try {
                    //用来导出同一个项目下合同金额或者送审金额项目的合同
                    String codeStr = costProjectService.getAAA(p.getProjectId());
                    if (null != codeStr && !"".equals(codeStr)) {
                        String arr[] = codeStr.split(",");
                        for (String code : arr) {
                            CostContract con = costContractService.selectByContractCode(code);
                            con.setProjectNameShow(p.getNumber());
                            str.add(con);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = Global.CON_START_TIME;
            String endTime = Global.CON_END_TIME;
            String path = request.getSession().getServletContext().getRealPath("/template/report/contract_template.xlsx");
            String newPath = "";
            if (null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)) {
                newPath = request.getSession().getServletContext().getRealPath("/template/temp/合同台账导出_" + startTime + "-" + endTime + ".xlsx");
            } else {
                String dateString = formatter.format(new Date());
                newPath = request.getSession().getServletContext().getRealPath("/template/temp/合同台账导出_" + dateString + ".xlsx");
            }
            Map<String, List<List<String>>> date = new HashMap<String, List<List<String>>>();
            List<List<String>> rowList = new ArrayList<List<String>>();
            List<String> cellList = new ArrayList<String>();
            for (CostContract l : str) {
                cellList = new ArrayList<String>();
                cellList.add(l.getProjectNameShow());
                cellList.add(l.getCode());
                cellList.add(l.getId());
                cellList.add(l.getName());
                cellList.add(l.getPartyB());
                cellList.add(l.getContractAmount() != null ? l.getContractAmount() + "" : "");
                cellList.add(l.getSettlementAmount() != null ? l.getContractAmount() + "" : "");
                String department = l.getExecutiveDepartment();
                if (department.contains(".0")) {
                    department = department.replace(".0", "");
                }
                if (null != department && !"".equals(department)) {
                    SysOrg org = sysOrgService.selectByPrimaryKey(department);
                    cellList.add(org.getName());
                } else {
                    cellList.add("");
                }
                rowList.add(cellList);
            }
            date.put("合同台账", rowList);
            try {
                ExcelUtil.copyExcel(1, date, path, newPath, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Created by ZYL on 2019/8/29
     * 更新项目分类
     */
    @ResponseBody
    @RequestMapping("/updateClassification")
    public GlobalResult updateClassification(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String projectClassificationId = request.getParameter("projectClassificationId");
        String[] arr = ids.split(",");
        int temp = 0;
        for (String i : arr) {
            if (i != null && !"".equals(i)) {
                CostProject p = costProjectService.selectByPrimaryKey(i);
                String projectClassificationId2 = p.getProjectClassificationId();
                String[] arr2 = projectClassificationId2.split(",");
                String[] arr3 = projectClassificationId.split(",");
                List<String> list = new ArrayList<String>(Arrays.asList(arr3));
                for (int j = 0; j < arr2.length; j++) {
                    if (list.contains(arr2[j])) {
                    } else {
                        list.add(arr2[j]);
                    }
                }
                projectClassificationId = StringUtils.join(list, ",");
                p.setProjectClassificationId(projectClassificationId);
                temp += costProjectService.updateByPrimaryKeySelective(p);
            }
        }
        if (temp > 0) {
            result.setStatus(200);
            result.setMsg("修改项目分类成功！");
        } else {
            result.setStatus(500);
            result.setMsg("修改项目分类失败！");
        }
        return result;
    }

    /**
     * Created by ZYL on 2019/9/2
     * 跳转项目指标修改
     */
    @RequestMapping("/editZb")
    public String editZb(HttpServletRequest request, ModelMap model) {
        String projId = request.getParameter("projId");
        if (projId != null && !"".equals(projId)) {
            CostProject project = costProjectService.selectByPrimaryKey(projId);
            //判断项目分类是否有删除部分
            String pType = project.getProjectClassificationId();
            StringBuffer sb = new StringBuffer();
            if (null != pType) {
                String arr[] = pType.split(",");
                for (String s : arr) {
                    CostProjectType type = costProjectTypeService.selectByPrimaryKey(s);
                    if (null != type && 1 == type.getDeleteFlag()) {
                        sb.append(s).append(",");
                    }
                }
            }
            if (sb.length() > 0) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            project.setProjectClassificationId(sb.toString());
            model.put("project", project);
        }
        CostProjectPeriodExample e = new CostProjectPeriodExample();
        com.cost168.costaudit.pojo.cost.CostProjectPeriodExample.Criteria criteria = e.createCriteria();
        criteria.andProjectIdEqualTo(projId);
        e.setOrderByClause(" num asc");
        List<CostProjectPeriod> nodeList = costProjectPeriodService.selectByExample(e);
        model.put("nodeList", nodeList);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projId);
        map.put("auditPriceType", "概算审核");
        boolean gaisEdit = true;
        List<CostTask> taskList = costTaskService.selectListByMap(map);
        if (taskList.size() > 0) {
            gaisEdit = false;
        }
        model.put("gaisEdit", gaisEdit);
        return "project/projZbEdit";
    }
}