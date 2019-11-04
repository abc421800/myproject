package com.cost168.costaudit.service.cost.impl;

import com.cost168.costaudit.mapper.cost.CostAttachmentMapper;
import com.cost168.costaudit.mapper.cost.CostContentTemplateMapper;
import com.cost168.costaudit.mapper.cost.CostProjectMapper;
import com.cost168.costaudit.mapper.cost.CostUnitProjectMapper;
import com.cost168.costaudit.pojo.cost.CostAttachment;
import com.cost168.costaudit.pojo.cost.CostContentTemplate;
import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.pojo.cost.CostUnitProject;
import com.cost168.costaudit.service.cost.CostContentTemplateService;
import com.cost168.costaudit.service.cost.CostProjectService;
import com.cost168.costaudit.utils.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 模版实现层
 * @author: ZYL
 * @created: 2019-09-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CostContentTemplateServiceImpl implements CostContentTemplateService {

    @Resource
    private CostAttachmentMapper costAttachmentMapper;
    @Resource
    private CostContentTemplateMapper contentTemplateMapper;
    @Resource
    private CostUnitProjectMapper costUnitProjectMapper;
    @Resource
    private CostProjectService costProjectService;

    @Resource
    private CostProjectMapper costProjectMapper;
    private final Logger LOG = LogManager.getLogger(CostContentTemplateServiceImpl.class);

    @Override
    public EUDataGridResult getUnitByProjectId(HttpServletRequest request, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        String projId = request.getParameter("projId");
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("pid", projId);
        map.put("isPage", true);
        map.put("curPage", rows*(page-1));
        map.put("pageSize", rows);
        CostUnitProject costUnitProject=new CostUnitProject();
        BigDecimal SubProjectCost=new BigDecimal(0.00);
        BigDecimal StepItemCost=new BigDecimal(0.00);
        BigDecimal OtherProjectFee=new BigDecimal(0.00);
        BigDecimal FeesTaxes=new BigDecimal(0.00);
        BigDecimal Count=new BigDecimal(0.00);
        BigDecimal UnitProjectPercen=new BigDecimal(0.00);
        BigDecimal CoveredArea=new BigDecimal(0.00);
        BigDecimal UnilateralIndicators=new BigDecimal(0.00);
        List<CostUnitProject> list = null;
        for(int i=1;i<=2;i++){
            if(i==2){
                list = costUnitProjectMapper.getUnitByProjectIdList(map);
            }else {
                list = costUnitProjectMapper.getUnitByProjectId(map);
            }
            for (CostUnitProject u:list){
                SubProjectCost=SubProjectCost.add(u.getSubProjectCost());
                StepItemCost=StepItemCost.add(u.getStepItemCost());
                OtherProjectFee=OtherProjectFee.add(u.getOtherProjectFee());
                FeesTaxes=FeesTaxes.add(u.getFeesTaxes());
                Count=Count.add(u.getCount());
                UnitProjectPercen=UnitProjectPercen.add(u.getUnitProjectPercen());
                CoveredArea=CoveredArea.add(u.getCoveredArea());
                UnilateralIndicators=UnilateralIndicators.add(u.getUnilateralIndicators());
            }
            costUnitProject.setNumber(null);
            costUnitProject.setName("合计");
            costUnitProject.setSubProjectCost(SubProjectCost);
            costUnitProject.setStepItemCost(StepItemCost);
            costUnitProject.setOtherProjectFee(OtherProjectFee);
            costUnitProject.setFeesTaxes(FeesTaxes);
            costUnitProject.setCount(Count);
            costUnitProject.setUnitProjectPercen(UnitProjectPercen);
            costUnitProject.setCoveredArea(CoveredArea);
            costUnitProject.setUnilateralIndicators(UnilateralIndicators);
            list.add(costUnitProject);
            if (i==1){
                Global.UNIT_EXPORT_LIST = list;
            }
        }
        int total = contentTemplateMapper.countByExample(map);
        result.setTotal(total);
        result.setRows(list);
        return result;
    }

    @Override
    public EUDataGridResult getContentByProjectId(HttpServletRequest request,int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        String projId = request.getParameter("projId");
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("pid", projId);
        map.put("isPage", true);
        map.put("curPage", rows*(page-1));
        map.put("pageSize", rows);
        List<CostContentTemplate> list1 = contentTemplateMapper.getContentByProjectIdList(map);
        List<CostContentTemplate> list = contentTemplateMapper.getContentByProjectId(map);
        Global.CONTENT_EXPORT_LIST = list;
        int total = contentTemplateMapper.countByExample(map);
        result.setRows(list1);
        result.setTotal(total);
        return result;
    }
    @Override
    public GlobalResult importContentTemplate(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        try {
            String attId = request.getParameter("id");
            String Id = request.getParameter("projectId");
            CostAttachment att = new CostAttachment();
            att.setId(attId);
            att.setCategory("已发布");
            costAttachmentMapper.updateById(att);
            att=new CostAttachment();
            att = costAttachmentMapper.selectByPrimaryKey(attId);
            Properties props = new Properties();
            props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
            String path = (String) props.get("fileupload");
            path = path.substring(0, 2) + att.getUrl();
            //读取exc
            HashMap<String, HashMap<Integer, ArrayList<Object>>> excelMap = ExcelUtil.readExcel(4, path, 0);
            HashMap<String, HashMap<Integer, ArrayList<Object>>> excelMap2 = ExcelUtil.readExcel(2, path, 1);
              //遍历excel的sheet
            //遍历excel的sheet
            for (Map.Entry<String, HashMap<Integer, ArrayList<Object>>> excel : excelMap2.entrySet()) {
                //取出一个sheet内容
                HashMap<Integer, ArrayList<Object>> sheetMap = excel.getValue();
                ArrayList<Object> row = null;
                CostUnitProject costUnitProject=null;
                CostContentTemplate costContentTemplate=null;
                //遍历一个sheet里的每行数据
                for(Map.Entry<Integer, ArrayList<Object>> sheet : sheetMap.entrySet()){
                    row = sheet.getValue();
                    if(row.get(0).toString().equals("…")){
                        continue;
                    }
                    costContentTemplate =new CostContentTemplate();
                    String id=UUID.randomUUID().toString().replace("-", "");
                    costContentTemplate.setId(id);
                    costContentTemplate.setProjectId(Id);
                    costContentTemplate.setNumber(row.get(0)!=null?row.get(0).toString():"");
                    costContentTemplate.setName(row.get(1)!=null?row.get(1).toString():"");
                    costContentTemplate.setContent(row.get(2)!=null?row.get(2).toString():"");
                    costContentTemplate.setUnit(row.get(3)!=null?row.get(3).toString():"");
                    costContentTemplate.setRemark(row.get(4)!=null?row.get(4).toString():"");
                    contentTemplateMapper.insertSelective(costContentTemplate);
                }

            }
            BigDecimal bg=new BigDecimal("0.00");
            for (Map.Entry<String, HashMap<Integer, ArrayList<Object>>> excel : excelMap.entrySet()) {
                //取出一个sheet内容
                HashMap<Integer, ArrayList<Object>> sheetMap = excel.getValue();
                ArrayList<Object> row = null;
                CostUnitProject costUnitProject=null;
                CostContentTemplate costContentTemplate=null;
                //遍历一个sheet里的每行数据
                for(Map.Entry<Integer, ArrayList<Object>> sheet : sheetMap.entrySet()){
                    row = sheet.getValue();
                    if(row.get(0).toString().equals("…")||row.get(1).toString().equals("合计")){
                        continue;
                    }
                    costUnitProject=new CostUnitProject();
                    costUnitProject.setProjectId(Id);
                    costUnitProject.setNumber(row.get(0)!=null&&row.get(0)!=""?Double.parseDouble(row.get(0).toString()):0);
                    costUnitProject.setName(row.get(1)!=null?row.get(1).toString():"");
                    costUnitProject.setSubProjectCost(row.get(2)!=null? bg = new BigDecimal(row.get(2).toString()).setScale(2,RoundingMode.HALF_UP) :null);
                    costUnitProject.setStepItemCost(row.get(3)!=null?bg = new BigDecimal(row.get(3).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setOtherProjectFee(row.get(4)!=null?bg = new BigDecimal(row.get(4).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setFeesTaxes(row.get(5)!=null?bg = new BigDecimal(row.get(5).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setCount(row.get(6)!=null?bg = new BigDecimal(row.get(6).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setUnitProjectPercen(row.get(7)!=null?bg = new BigDecimal(row.get(7).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setCoveredArea(row.get(8)!=null?bg =new BigDecimal(row.get(8).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setUnilateralIndicators(row.get(9)!=null?bg = new BigDecimal(row.get(9).toString()).setScale(2,RoundingMode.HALF_UP):null);
                    costUnitProject.setRemark(row.get(10)!=null?row.get(10).toString():"");
                    costUnitProjectMapper.insertSelective(costUnitProject);
                }
            }
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }


    @Override
    public void InExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            CostProject l=costProjectService.selectByPrimaryKey(id);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            CostProject l=Global.PROJECT_EXPORT;
            List<CostUnitProject> costUnitProjectList=Global.UNIT_EXPORT_LIST;
            List<CostContentTemplate> costContentTemplateList=Global.CONTENT_EXPORT_LIST;
//            CostProject project=Global.PROJECT_EXPORT;
            String path = request.getSession().getServletContext().getRealPath("/template/report/项目名称指标导出_YYYY-MM-DD.xlsx");
            String newPath = "";
            String dateString = formatter.format(new Date());
            newPath = request.getSession().getServletContext().getRealPath("/template/report/项目名称指标导出_" + dateString + ".xlsx");

            Map<String, List<List<String>>> date = new HashMap<String, List<List<String>>>();
            List<List<String>> rowList = new ArrayList<List<String>>();
            List<String> cellList = new ArrayList<String>();
            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getName());
            cellList.add("");
            cellList.add("");
            cellList.add("");
            cellList.add("");
            cellList.add(l.getCode());
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getArea());
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getProjectIntroduction());
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add(l.getIllustration());
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getUnit());
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getXjGsCost()!=null?l.getXjGsCost()+l.getCostUnit():"");
            cellList.add("");
            cellList.add(l.getKyGsCost()!=null?l.getKyGsCost()+l.getCostUnit():"");
            cellList.add("");
            cellList.add(l.getGsCost()!=null?l.getGsCost()+l.getCostUnit():"");
            cellList.add("");
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getGsArchitectural()!=null?l.getGsArchitectural()+l.getArchitecturalUnit():"");
            cellList.add("");
            cellList.add(l.getKyGsArchitectural()!=null?l.getKyGsArchitectural()+l.getArchitecturalUnit():"");
            cellList.add("");
            cellList.add(l.getGsArchitectural()!=null?l.getGsArchitectural()+l.getArchitecturalUnit():"");
            cellList.add("");
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getXjGsInstallation()!=null?l.getXjGsInstallation()+l.getInstallationUnit():"");
            cellList.add("");
            cellList.add(l.getKyGsInstallation()!=null?l.getKyGsInstallation()+l.getInstallationUnit():"");
            cellList.add("");
            cellList.add(l.getGsInstallation()!=null?l.getGsInstallation()+l.getInstallationUnit():"");
            cellList.add("");
            rowList.add(cellList);

            cellList = new ArrayList<String>();
            cellList.add("");
            cellList.add(l.getOutdoorCost()!=null?l.getOutdoorCost()+l.getOutdoorUnit():"");
            cellList.add("");
            cellList.add(l.getOutdoorKyGs()!=null?l.getOutdoorKyGs()+l.getOutdoorUnit():"");
            cellList.add("");
            cellList.add(l.getOutdoorGsCost()!=null?l.getOutdoorGsCost()+l.getOutdoorUnit():"");
            cellList.add("");
            rowList.add(cellList);
            date.put("项目概况", rowList);

            Map<String, List<List<String>>> date1 = new HashMap<String, List<List<String>>>();
            rowList = new ArrayList<List<String>>();
            for (CostContentTemplate cot : costContentTemplateList) {
                cellList = new ArrayList<String>();
                cellList.add(cot.getNumber()!=null?cot.getNumber():"");
                cellList.add(cot.getName()!=null?cot.getName():"");
                cellList.add(cot.getContent()!=null?cot.getContent():"");
                cellList.add(cot.getUnit()!=null?cot.getUnit():"");
                cellList.add(cot.getRemark()!=null?cot.getRemark():"");
                rowList.add(cellList);
            }


            date1.put("钢筋、混凝土、模板含量表",rowList);

            Map<String, List<List<String>>> date2 = new HashMap<String, List<List<String>>>();
            rowList = new ArrayList<List<String>>();
            for (CostUnitProject cup : costUnitProjectList) {
                cellList = new ArrayList<String>();
                cellList.add(cup.getNumber()!=null?cup.getNumber().toString():"");
                cellList.add(cup.getName()!=null?cup.getName():"");
                cellList.add(cup.getSubProjectCost()!=null?cup.getSubProjectCost().toString():"");
                cellList.add(cup.getStepItemCost()!=null?cup.getStepItemCost().toString():"");
                cellList.add(cup.getOtherProjectFee()!=null?cup.getOtherProjectFee().toString():"");
                cellList.add(cup.getFeesTaxes()!=null?cup.getFeesTaxes().toString():"");
                cellList.add(cup.getCount()!=null?cup.getCount().toString():"");
                cellList.add(cup.getUnitProjectPercen()!=null?cup.getUnitProjectPercen().toString():"");
                cellList.add(cup.getCoveredArea()!=null?cup.getCoveredArea().toString():"");
                cellList.add(cup.getUnilateralIndicators()!=null?cup.getUnilateralIndicators().toString():"");
                cellList.add(cup.getRemark()!=null?cup.getRemark():"");
                rowList.add(cellList);
            }
            cellList = new ArrayList<String>();
            date2.put("单位工程汇总表",rowList);
            ExcelUtil.copyExcel1(0,2,2,date,date1,date2, path, newPath, request, response);
            ExcelUtil.downLoad(newPath,"",request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
