package com.cost168.costaudit.service.cost.impl;

import com.cost168.costaudit.mapper.cost.*;
import com.cost168.costaudit.pojo.cost.*;
import com.cost168.costaudit.pojo.cost.CostProjectExample.Criteria;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostProjectPeriodService;
import com.cost168.costaudit.service.cost.CostProjectService;
import com.cost168.costaudit.service.cost.CostProjectTypeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.ExcelUtil;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.vo.cost.ContractJsCountVo;
import com.cost168.costaudit.vo.cost.ProjectJsCountVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostProjectServiceImpl implements CostProjectService {

    @Autowired
    private CostProjectMapper costProjectMapper;
    @Autowired
    private CostProjectStakeholderMapper costProjectStakeholderMapper;
    @Autowired
    private CostProjectPeriodMapper costProjectPeriodMapper;
    @Autowired
    private CostAttachmentMapper costAttachmentMapper;
    @Autowired
    private CostContractMapper costContractMapper;
    @Autowired
    private CostTaskMapper costTaskMapper;
    @Autowired
    private CostDpctRelationMapper costDpctRelationMapper;
    @Autowired
    private CostProjectTypeService costProjectTypeService;
    @Autowired
    private CostProjectPeriodService costProjectPeriodService;

    @Override
    public List<CostProject> selectByExample(CostProjectExample example) {
        return costProjectMapper.selectByExample(example);
    }

    @Override
    public CostProject selectByPrimaryKey(String id) {
        return costProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return costProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CostProject record) {
        costProjectMapper.insertSelective(record);
        return 1;
    }

    @Override
    public int updateByPrimaryKeySelective(CostProject record) {
        costProjectMapper.updateByPrimaryKeySelective(record);
        return 1;
    }

    @Override
    public List<CostProject> getList(Map<String, Object> map) {
        List<CostProject> list = costProjectMapper.getList(map);
        for (CostProject c : list) {
            c.setProjectClassification("");
            if (c.getProjectClassificationId() != null && !"".equals(c.getProjectClassificationId())) {
                String arr[] = c.getProjectClassificationId().split(",");
                for (String i : arr) {
                    CostProjectType type = costProjectTypeService.selectByPrimaryKey(i);
                    if (type != null) {
                        c.setProjectClassification(c.getProjectClassification() + type.getName() + ",");
                    }
                }
            }
            if (c.getProjectClassification().length() > 0) {
                c.setProjectClassification(c.getProjectClassification().substring(0, c.getProjectClassification().length() - 1));
            }
        }
        return list;
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return costProjectMapper.getCount(map);
    }

    @Override
    public int deleteProject(String id) {
        CostProjectStakeholderExample stakeholdereExample = new CostProjectStakeholderExample();
        com.cost168.costaudit.pojo.cost.CostProjectStakeholderExample.Criteria stakeholderCriteria = stakeholdereExample.createCriteria();
        stakeholderCriteria.andProjectIdEqualTo(id);
        costProjectStakeholderMapper.deleteByExample(stakeholdereExample);

        CostProjectPeriodExample preriodExample = new CostProjectPeriodExample();
        com.cost168.costaudit.pojo.cost.CostProjectPeriodExample.Criteria periodCriteria = preriodExample.createCriteria();
        periodCriteria.andProjectIdEqualTo(id);
        costProjectPeriodMapper.deleteByExample(preriodExample);

        CostAttachmentExample attachmentExample = new CostAttachmentExample();
        com.cost168.costaudit.pojo.cost.CostAttachmentExample.Criteria attachmentCriteria = attachmentExample.createCriteria();
        attachmentCriteria.andTypeIdEqualTo(id);
        costAttachmentMapper.deleteByExample(attachmentExample);

        CostContractExample contractExample = new CostContractExample();
        com.cost168.costaudit.pojo.cost.CostContractExample.Criteria contractCriteria = contractExample.createCriteria();
        contractCriteria.andProjectIdEqualTo(id);
        costContractMapper.deleteByExample(contractExample);

        CostTaskExample taskExample = new CostTaskExample();
        com.cost168.costaudit.pojo.cost.CostTaskExample.Criteria taskCriteria = taskExample.createCriteria();
        taskCriteria.andProjectIdEqualTo(id);
        costTaskMapper.deleteByExample(taskExample);

        CostDpctRelationExample dpctRelationExample = new CostDpctRelationExample();
        com.cost168.costaudit.pojo.cost.CostDpctRelationExample.Criteria dpctRelationCriteria = dpctRelationExample.createCriteria();
        dpctRelationCriteria.andProjectIdEqualTo(id);
        costDpctRelationMapper.deleteByExample(dpctRelationExample);

        costProjectMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public List<ProjectJsCountVo> getProjectJsList(Map<String, Object> map, int page, int rows) {
        String auditPriceType = (String) map.get("auditPriceType");
        List<ProjectJsCountVo> list = null;
        if ("结算审核".equals(auditPriceType)) {
            list = costProjectMapper.getProjectJsList(map);
        } else {
            list = costProjectMapper.getProjectJsListAll(map);
            /*for (ProjectJsCountVo p2 : list) {
                //重新汇总合同金额
            	map.put("projectId", p2.getProjectId());
                String amount = costProjectMapper.getContractAmount(map);
                if (null != amount && !"".equals(amount)) {
                    p2.setContractAmount(new BigDecimal(amount));
                }
            }*/
        }
        int i = (page - 1) * rows + 1;
        for (ProjectJsCountVo p : list) {
            p.setPriority(i + "");
            i++;
        }
        return list;
    }

    @Override
    public List<ProjectJsCountVo> getProjectJsContractList(Map<String, Object> map) {
        List<ProjectJsCountVo> list = new ArrayList<ProjectJsCountVo>();
        String index = (String) map.get("index");
        try {
            int in = Integer.parseInt(index);
            String auditPriceType = (String) map.get("auditPriceType");
            List<ProjectJsCountVo> l = null;
            if ("结算审核".equals(auditPriceType)) {
                l = costProjectMapper.getProjectJsContractList(map);
            } else {
                l = costProjectMapper.getProjectJsContractListAll(map);
            }
            int i = 1;
            Map<String, Object> map2 = new HashMap<String, Object>();
            Map<String, Object> map3 = new HashMap<String, Object>();
            for (ProjectJsCountVo p : l) {
                p.setPriority(in + "." + i);
                String jsProgress = p.getStatus() != null ? p.getStatus() : "";
                //当前任务是审结的，只读取当前状态
                if (jsProgress.equals("审结")) {
                } else {
                    if (p.getCheckExplain() != null && !"".equals(p.getCheckExplain())) {
                        jsProgress = p.getStatus() + ";" + p.getCheckExplain();
                    }
                    if (p.getProgressDescription() != null && !"".equals(p.getProgressDescription())) {
                        jsProgress = jsProgress + ";" + p.getProgressDescription();
                    }
                }
                p.setJsProgress(jsProgress);
                map2.clear();
                map2.put("parentId", p.getContractId());
                map3.clear();
                map3.put("contractId", p.getContractId());
                //是否有审价任务
                int temp = costTaskMapper.selectCountByMap(map3);
                p.setTemp(temp);
                List<ProjectJsCountVo> chlidList = costProjectMapper.getProjectJsContractChildrenList(map2);
                int j = 1;
                for (ProjectJsCountVo c : chlidList) {
                    c.setPriority(p.getPriority() + "." + j);
                    String jsProgress2 = c.getStatus() != null ? c.getStatus() : "";
                    if (c.getCheckExplain() != null && !"".equals(c.getCheckExplain())) {
                        jsProgress2 = c.getStatus() + ";" + c.getCheckExplain();
                    }
                    if (c.getProgressDescription() != null && !"".equals(c.getProgressDescription())) {
                        jsProgress2 = jsProgress2 + ";" + c.getProgressDescription();
                    }
                    c.setJsProgress(jsProgress2);
                    j++;
                }
                list.add(p);
                if (chlidList.size() > 0) {
                    //list.addAll(chlidList);
                }
                i++;
            }
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public int getProjectJsCount(Map<String, Object> map) {
        return costProjectMapper.getProjectJsCount(map);
    }

    @Override
    public int getProjectJsCountAll(Map<String, Object> map) {
        return costProjectMapper.getProjectJsCountAll(map);
    }

    @Override
    public List<ContractJsCountVo> getContractJsList(Map<String, Object> map, int page, int rows) {
        List<ContractJsCountVo> list = costProjectMapper.getContractJsList(map);
        int i = (page - 1) * rows + 1;
        DecimalFormat df = new DecimalFormat("#.00");
        for (ContractJsCountVo c : list) {
            c.setPriority(i + "");
            i++;
            //金额以万元显示，并四舍五入
            if (c.getContractAmount() != null) {
                double temp = c.getContractAmount().doubleValue();
                temp = Double.parseDouble(df.format(temp));
                c.setContractAmount(new BigDecimal(temp));
            }
            if (c.getSsContractAmount() != null) {
                double temp2 = c.getSsContractAmount().doubleValue();
                temp2 = Double.parseDouble(df.format(temp2));
                c.setSsContractAmount(new BigDecimal(temp2));
            }
            if (c.getSsWsContractAmount() != null) {
                double temp3 = c.getSsWsContractAmount().doubleValue();
                temp3 = Double.parseDouble(df.format(temp3));
                c.setSsWsContractAmount(new BigDecimal(temp3));
            }
            if (c.getSsZsContractAmount() != null) {
                double temp4 = c.getSsZsContractAmount().doubleValue();
                temp4 = Double.parseDouble(df.format(temp4));
                c.setSsZsContractAmount(new BigDecimal(temp4));
            }
            if (c.getDsContractAmount() != null) {
                double temp5 = c.getDsContractAmount().doubleValue();
                temp5 = Double.parseDouble(df.format(temp5));
                c.setDsContractAmount(new BigDecimal(temp5));
            }
        }
        return list;
    }

    @Override
    public int getContractJsCount(Map<String, Object> map) {
        return costProjectMapper.getContractJsCount(map);
    }

    @Override
    public List<ContractJsCountVo> getContractJsGroup(Map<String, Object> map) {
        List<ContractJsCountVo> list = null;
        String index = (String) map.get("index");
        try {
            int in = Integer.parseInt(index);
            list = costProjectMapper.getContractJsGroup(map);
            int i = 1;
            for (ContractJsCountVo c : list) {
                c.setPriority(in + "." + i);
                i++;
            }
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public List<CostProject> importProject(HttpServletRequest request) {
        List<CostProject> list = new ArrayList<CostProject>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("file");
            if (files.size() > 0) {
                MultipartFile file = files.get(0);
                String fileName = file.getOriginalFilename();
                //源文件后缀
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                String nowDate = fmt.format(new Date());
                //新文件名称
                Properties props = new Properties();
                props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
                String path = (String) props.get("fileupload");
                path = path + "project/" + nowDate + "/";
                File filePath = new File(path + fileName);
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                file.transferTo(filePath);
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                Workbook wb = null;
                InputStream stream = new FileInputStream(filePath);
                if (suffix.equals("xls")) {
                    POIFSFileSystem fs = new POIFSFileSystem(stream);
                    wb = new HSSFWorkbook(fs);
                } else if (suffix.equals("xlsx")) {
                    wb = new XSSFWorkbook(stream);
                }
                Sheet sheet = wb.getSheetAt(0);
                if (sheet != null) {
                    int num = sheet.getLastRowNum();
                    CostProject p = null;
                    CostProject resut = null;
                    for (int i = 1; i <= num; i++) {
                        // 循环输出表格中的内容
                        Row row = sheet.getRow(i);
                        if (row != null) {
                            Cell cell1 = row.getCell(1);
                            resut = new CostProject();
                            if (null != cell1) {
                                try {
                                    CostProject proj = selectByCode(row.getCell(1).toString());
                                    if (null != proj) {
                                        proj.setName(row.getCell(0).toString());
                                        proj.setProjectOwner(row.getCell(2).toString());
                                        proj.setProjectOwnerPhone(row.getCell(3).toString());
                                        proj.setPersonLiableId(row.getCell(4).toString());
                                        proj.setLxpfNumber(row.getCell(5).toString());
                                        proj.setProjectCategoryId(row.getCell(6).toString());
                                        Cell cell7 = row.getCell(7);
                                        if (null != cell7 && !"".equals(cell7)) {
                                            cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                                            String content = cell7.getStringCellValue();
                                            proj.setProjectClassificationId(content);
                                        }
                                        proj.setProjectNode(row.getCell(8).toString());
                                        Cell cell9 = row.getCell(9);
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        if (cell9 != null && !"".equals(cell9.toString())) {
                                            if (cell9.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell9)) {
                                                Date date = HSSFDateUtil.getJavaDate(cell9.getNumericCellValue());
                                                String value = sdf.format(date);
                                                proj.setNodeTime(sdf.parse(value));
                                            }
                                        }
                                        Cell cell10 = row.getCell(10);
                                        if (cell10 != null && !"".equals(cell10.toString())) {
                                            if (cell10.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell10)) {
                                                Date date = HSSFDateUtil.getJavaDate(cell10.getNumericCellValue());
                                                String value = sdf.format(date);
                                                proj.setPlanOverTime(sdf.parse(value));
                                            }
                                        }

                                        if (null != row.getCell(11) && !"".equals(row.getCell(11))) {
                                            Double je = ExcelUtil.getValue(row.getCell(11));
                                            BigDecimal bdje = new BigDecimal(je);
                                            bdje.setScale(2, BigDecimal.ROUND_HALF_UP);
                                            proj.setXjGsJe(bdje);
                                        }
                                        if (row.getCell(12).toString() != null && !"".equals(row.getCell(12).toString())) {
                                            Double je = ExcelUtil.getValue(row.getCell(12));
                                            proj.setKyGsJe(new BigDecimal(je).setScale(2, BigDecimal.ROUND_HALF_UP));
                                        }
                                        if (row.getCell(13).toString() != null && !"".equals(row.getCell(13).toString())) {
                                            Double je = ExcelUtil.getValue(row.getCell(13));
                                            proj.setSumGsJe(new BigDecimal(je).setScale(2, BigDecimal.ROUND_HALF_UP));
                                        }
                                        proj.setSettlementReivewMethod(row.getCell(14).toString());
                                        proj.setProjectManagementAgreement(row.getCell(15).toString());
                                        proj.setSettlementAgreement(row.getCell(16).toString());
                                        proj.setProjectLocation(row.getCell(17).toString());
                                        proj.setAllProcess(row.getCell(18).toString());
                                        proj.setProjectIntroduction(row.getCell(19).toString());
                                        proj.setDescription(row.getCell(20).toString());
                                        proj.setCreatorTime(new Date());
                                        SysUser user = shiroUtil.getInstance().currentUser();
                                        proj.setCreator(user.getName());
                                        costProjectMapper.updateByPrimaryKeySelective(proj);
											/*resut.setCode(row.getCell(1).toString());
											resut.setName(row.getCell(0).toString());
											list.add(resut);*/
                                        continue;
                                    }
                                    p = new CostProject();
                                    String projId = UUID.randomUUID().toString().replace("-", "");
                                    p.setId(projId);
                                    p.setName(row.getCell(0).toString());
                                    p.setCode(row.getCell(1).toString());
                                    p.setProjectOwner(row.getCell(2).toString());
                                    p.setProjectOwnerPhone(row.getCell(3).toString());
                                    p.setPersonLiableId(row.getCell(4).toString());
                                    p.setLxpfNumber(row.getCell(5).toString());
                                    p.setProjectCategoryId(row.getCell(6).toString());
                                    Cell cell7 = row.getCell(7);
                                    if (null != cell7 && !"".equals(cell7)) {
                                        cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                                        String content = cell7.getStringCellValue();
                                        p.setProjectClassificationId(content);
                                    }
                                    p.setProjectNode(row.getCell(8).toString());
                                    Cell cell9 = row.getCell(9);
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    if (cell9 != null && !"".equals(cell9.toString())) {
                                        if (cell9.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell9)) {
                                            Date date = HSSFDateUtil.getJavaDate(cell9.getNumericCellValue());
                                            String value = sdf.format(date);
                                            p.setNodeTime(sdf.parse(value));
                                        }
                                    }
                                    Cell cell10 = row.getCell(10);
                                    if (cell10 != null && !"".equals(cell10.toString())) {
                                        if (cell10.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell10)) {
                                            Date date = HSSFDateUtil.getJavaDate(cell10.getNumericCellValue());
                                            String value = sdf.format(date);
                                            p.setPlanOverTime(sdf.parse(value));
                                        }
                                    }

                                    if (null != row.getCell(11) && !"".equals(row.getCell(11))) {
                                        Double je = ExcelUtil.getValue(row.getCell(11));
                                        BigDecimal bdje = new BigDecimal(je);
                                        bdje.setScale(2, BigDecimal.ROUND_HALF_UP);
                                        p.setXjGsJe(bdje);
                                    }
                                    if (row.getCell(12).toString() != null && !"".equals(row.getCell(12).toString())) {
                                        Double je = ExcelUtil.getValue(row.getCell(12));
                                        p.setKyGsJe(new BigDecimal(je).setScale(2, BigDecimal.ROUND_HALF_UP));
                                    }
                                    if (row.getCell(13).toString() != null && !"".equals(row.getCell(13).toString())) {
                                        Double je = ExcelUtil.getValue(row.getCell(13));
                                        p.setSumGsJe(new BigDecimal(je).setScale(2, BigDecimal.ROUND_HALF_UP));
                                    }
                                    p.setSettlementReivewMethod(row.getCell(14).toString());
                                    p.setProjectManagementAgreement(row.getCell(15).toString());
                                    p.setSettlementAgreement(row.getCell(16).toString());
                                    p.setProjectLocation(row.getCell(17).toString());
                                    p.setAllProcess(row.getCell(18).toString());
                                    p.setProjectIntroduction(row.getCell(19).toString());
                                    p.setDescription(row.getCell(20).toString());
                                    p.setCreatorTime(new Date());
                                    SysUser user = shiroUtil.getInstance().currentUser();
                                    p.setCreator(user.getName());
                                    costProjectMapper.insertSelective(p);
                                    costProjectPeriodService.initData(p.getId());
                                } catch (Exception e) {
                                    resut.setCode(row.getCell(1).toString());
                                    resut.setName(row.getCell(0).toString());
                                    list.add(resut);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //public static String[] xslColumnName = {"项目代号","项目名称","项目业主","建设管理协议","结算方式约定","结算评审方式","创建人","项目负责人","项建估算工程费（万元）","项建估算二类费用（万元）","项建估算三类费用（万元）","可研估算工程费（万元）","可研估算二类费用（万元）","可研估算三类费用（万元）","工程类别","项目分类","当前节点","节点控制日期","概算金额（元）","预算金额（元）","控制价（元）","合同价（元）","变更（元）","结算（元）"};
    public void exportProject(String[] columnName, String[] columnTitle, OutputStream out, List<CostProject> list) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //列宽设为自适应
        if (columnName.length > 0) {
            for (int column = 0; column < columnTitle.length; column++) {
                sheet.autoSizeColumn(column);
            }
        }
        int index = 0;
		/*HSSFCellStyle titleStyle = hssfWorkbook.createCellStyle();
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setWrapText(true);//自动换行
		//设置居中
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		//生成字体
		HSSFFont titlefont = hssfWorkbook.createFont();
		titlefont.setFontName("宋体");
		titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titlefont.setFontHeightInPoints((short)20);
		//把字体应有到当前的样式
		titleStyle.setFont(titlefont);
		//合并第一行title并赋值title
		HSSFRow row = sheet.createRow(index++);
		row.setHeight((short)(25*20));
		HSSFCell titleCell = row.createCell(0);
	    titleCell.setCellStyle(titleStyle);
	    titleCell.setCellValue(title);*/
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//自动换行
        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
        //style.setFillBackgroundColor(HSSFColor.AQUA.index);//设置背景色
        //生成字体
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontHeightInPoints((short) 11);
        //把字体应有到当前的样式
        style.setFont(font);
        HSSFRow row = sheet.createRow(index++);
        for (int i = 0; i < columnTitle.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            String head = columnTitle[i].replace("</br>", "");//matchTableHeader(columnName[i]);
            cell.setCellValue(head);
        }
        for (CostProject p : list) {
            row = sheet.createRow(index++);
            for (int j = 0; j < columnName.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                String value = matchTableValue(columnName[j], p);
                cell.setCellValue(value);
            }
        }
        hssfWorkbook.write(out);
        hssfWorkbook.close();
    }

    public String matchTableValue(String columnName, CostProject p) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String column = null;
        if ("code".equals(columnName)) column = p.getCode();
        if ("name".equals(columnName)) column = p.getName();
        if ("projectOwner".equals(columnName)) column = p.getProjectOwner();
        if ("projectManagementAgreement".equals(columnName)) column = p.getProjectManagementAgreement();
        if ("settlementAgreement".equals(columnName)) column = p.getSettlementAgreement();
        if ("settlementReivewMethod".equals(columnName)) column = p.getSettlementReivewMethod();
        if ("creator".equals(columnName)) column = p.getCreator();
        if ("personLiableId".equals(columnName)) column = p.getPersonLiableId();
        if ("xjGsGcf".equals(columnName))
            column = p.getXjGsGcf() != null ? p.getXjGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("xjGsElfy".equals(columnName))
            column = p.getXjGsElfy() != null ? p.getXjGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("xjGsSlfy".equals(columnName))
            column = p.getXjGsSlfy() != null ? p.getXjGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("kyGsGcf".equals(columnName))
            column = p.getKyGsGcf() != null ? p.getKyGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("kyGsElfy".equals(columnName))
            column = p.getKyGsElfy() != null ? p.getKyGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("kyGsSlfy".equals(columnName))
            column = p.getKyGsSlfy() != null ? p.getKyGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("projectCategoryId".equals(columnName)) column = p.getProjectCategoryId();
        if ("projectClassification".equals(columnName)) column = p.getProjectClassification();
        if ("projectNode".equals(columnName)) column = p.getProjectNode();
        if ("nodeTime".equals(columnName)) column = p.getNodeTime() != null ? formatter.format(p.getNodeTime()) : "";
        if ("sumGsJe".equals(columnName))
            column = p.getSumGsJe() != null ? p.getSumGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("sumYsJe".equals(columnName))
            column = p.getSumYsJe() != null ? p.getSumYsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("sumKzjJe".equals(columnName))
            column = p.getSumKzjJe() != null ? p.getSumKzjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("sumHtjJe".equals(columnName))
            column = p.getSumHtjJe() != null ? p.getSumHtjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("sumBgjJe".equals(columnName))
            column = p.getSumBgjJe() != null ? p.getSumBgjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        if ("sumJsjJe".equals(columnName))
            column = p.getSumJsjJe() != null ? p.getSumJsjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "";
        return column;
    }

    @Override
    public CostProject selectByCode(String code) {
        CostProjectExample example = new CostProjectExample();
        Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<CostProject> cList = costProjectMapper.selectByExample(example);
        if (null != cList && cList.size() > 0) {
            return cList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public BigDecimal getTaskSumDecideAmount(Map<String, Object> map) {
        return costProjectMapper.getTaskSumDecideAmount(map);
    }

    @Override
    public BigDecimal getContractSumAmount(Map<String, Object> map) {
        return costProjectMapper.getContractSumAmount(map);
    }

    @Override
    public List<ProjectJsCountVo> getProjectJsContractChildrenList(
            Map<String, Object> map) {
        // TODO Auto-generated method stub
        return costProjectMapper.getProjectJsContractChildrenList(map);
    }

    public void exportProject(String path, OutputStream out, List<CostProject> list) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Workbook wb = null;
        try {
            try {
                FileInputStream fis = new FileInputStream(path);
                String ext = path.substring(path.lastIndexOf(".") + 1);
                if (ext.equalsIgnoreCase("xls")) {//03版本及以下
                    wb = new HSSFWorkbook(fis);
                } else if (ext.equalsIgnoreCase("xlsx")) {//07版本及以上
                    wb = new XSSFWorkbook(fis);
                }
            } catch (Exception e) {
                throw new Exception("系统未知的Excel版本", e);
            } finally {
            }
            Sheet sheet = wb.getSheetAt(0);
            int index = 2;//开始行
            //汇总行设置格式
            CellStyle sumStyle = wb.createCellStyle();
            //设置水平对齐方式
            sumStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            //设置垂直对齐方式
            sumStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            sumStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            sumStyle.setFillBackgroundColor(HSSFColor.AQUA.index);//设置背景色
            sumStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            sumStyle.setBorderRight((short) 1);
            sumStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //sumStyle.setWrapText(true);//自动换行
            Font contentFont = wb.createFont();
            contentFont.setFontName("宋体");
            //contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            contentFont.setFontHeightInPoints((short) 11);
            //contentFont.setColor(HSSFColor.GREY_50_PERCENT.index);
            sumStyle.setFont(contentFont);
            //项目行设置格式
            CellStyle projStyle = wb.createCellStyle();
            //设置水平对齐方式
            projStyle.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐方式
            projStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            projStyle.setFillForegroundColor(IndexedColors.WHITE.index);
            projStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            projStyle.setBorderRight((short) 1);
            projStyle.setFont(contentFont);
            projStyle.setBorderRight((short) 1);
            projStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            projStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            projStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            projStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //projStyle.setWrapText(true);//自动换行
            projStyle.setFont(contentFont);
            //金额列设置格式
            CellStyle jeStyle = wb.createCellStyle();
            //设置水平对齐方式
            jeStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            //设置垂直对齐方式
            jeStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            jeStyle.setFillForegroundColor(IndexedColors.WHITE.index);
            jeStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            jeStyle.setBorderRight((short) 1);
            jeStyle.setFont(contentFont);
            jeStyle.setBorderRight((short) 1);
            jeStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            jeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            jeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            jeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //jeStyle.setWrapText(true);//自动换行
            jeStyle.setFont(contentFont);
            CostProject s = new CostProject();
            s.setXjGsJe(new BigDecimal(0));
            s.setXjGsGcf(new BigDecimal(0));
            s.setXjGsElfy(new BigDecimal(0));
            s.setXjGsSlfy(new BigDecimal(0));
            s.setKyGsJe(new BigDecimal(0));
            s.setKyGsGcf(new BigDecimal(0));
            s.setKyGsElfy(new BigDecimal(0));
            s.setKyGsSlfy(new BigDecimal(0));
            s.setSumGsJe(new BigDecimal(0));
            s.setGsGcf(new BigDecimal(0));
            s.setGsElfy(new BigDecimal(0));
            s.setGsSlfy(new BigDecimal(0));
            s.setSumYsJe(new BigDecimal(0));
            s.setSumKzjJe(new BigDecimal(0));
            s.setSumHtjJe(new BigDecimal(0));
            s.setSumBgjJe(new BigDecimal(0));
            s.setSumJsjJe(new BigDecimal(0));
            s.setSumJyJe(new BigDecimal(0));
            s.setSumTzBgJe(new BigDecimal(0));
            for (CostProject l : list) {
                s.setXjGsJe(l.getXjGsJe() != null ? s.getXjGsJe().add(l.getXjGsJe()) : s.getXjGsJe());
                s.setXjGsGcf(l.getXjGsGcf() != null ? s.getXjGsGcf().add(l.getXjGsGcf()) : s.getXjGsGcf());
                s.setXjGsElfy(l.getXjGsElfy() != null ? s.getXjGsElfy().add(l.getXjGsElfy()) : s.getXjGsElfy());
                s.setXjGsSlfy(l.getXjGsSlfy() != null ? s.getXjGsSlfy().add(l.getXjGsSlfy()) : s.getXjGsSlfy());
                s.setKyGsJe(l.getKyGsJe() != null ? s.getKyGsJe().add(l.getKyGsJe()) : s.getKyGsJe());
                s.setKyGsGcf(l.getKyGsGcf() != null ? s.getKyGsGcf().add(l.getKyGsGcf()) : s.getKyGsGcf());
                s.setKyGsElfy(l.getKyGsElfy() != null ? s.getKyGsElfy().add(l.getKyGsElfy()) : s.getKyGsElfy());
                s.setKyGsSlfy(l.getKyGsSlfy() != null ? s.getKyGsSlfy().add(l.getKyGsSlfy()) : s.getKyGsSlfy());
                s.setSumGsJe(l.getSumGsJe() != null ? s.getSumGsJe().add(l.getSumGsJe()) : s.getSumGsJe());
                s.setGsGcf(l.getGsGcf() != null ? s.getGsGcf().add(l.getGsGcf()) : s.getGsGcf());
                s.setGsElfy(l.getGsElfy() != null ? s.getGsElfy().add(l.getGsElfy()) : s.getGsElfy());
                s.setGsSlfy(l.getGsSlfy() != null ? s.getGsSlfy().add(l.getGsSlfy()) : s.getGsSlfy());
                s.setSumYsJe(l.getSumYsJe() != null ? s.getSumYsJe().add(l.getSumYsJe()) : s.getSumYsJe());
                s.setSumKzjJe(l.getSumKzjJe() != null ? s.getSumKzjJe().add(l.getSumKzjJe()) : s.getSumKzjJe());
                s.setSumHtjJe(l.getSumHtjJe() != null ? s.getSumHtjJe().add(l.getSumHtjJe()) : s.getSumHtjJe());
                s.setSumBgjJe(l.getSumBgjJe() != null ? s.getSumBgjJe().add(l.getSumBgjJe()) : s.getSumBgjJe());
                s.setSumJsjJe(l.getSumJsjJe() != null ? s.getSumJsjJe().add(l.getSumJsjJe()) : s.getSumJsjJe());
                s.setSumJyJe(l.getSumJyJe() != null ? s.getSumJyJe().add(l.getSumJyJe()) : s.getSumJyJe());
                s.setSumTzBgJe(l.getSumTzBgJe() != null ? s.getSumTzBgJe().add(l.getSumTzBgJe()) : s.getSumTzBgJe());

                Row row = sheet.createRow(index);
                Cell cell = row.createCell(0);
                cell.setCellStyle(projStyle);
                cell.setCellValue(l.getCode());
                Cell cell1 = row.createCell(1);
                cell1.setCellStyle(projStyle);
                cell1.setCellValue(l.getName());
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(projStyle);
                cell2.setCellValue(l.getProjectOwner());
                Cell cell3 = row.createCell(3);
                cell3.setCellStyle(projStyle);
                cell3.setCellValue(l.getProjectManagementAgreement());
                Cell cell4 = row.createCell(4);
                cell4.setCellStyle(projStyle);
                cell4.setCellValue(l.getSettlementAgreement());
                Cell cell5 = row.createCell(5);
                cell5.setCellStyle(projStyle);
                cell5.setCellValue(l.getSettlementReivewMethod());
                Cell cell6 = row.createCell(6);
                cell6.setCellStyle(projStyle);
                cell6.setCellValue(l.getCreator());
                Cell cell7 = row.createCell(7);
                cell7.setCellStyle(projStyle);
                cell7.setCellValue(l.getPersonLiableId());
                Cell cell8 = row.createCell(8);
                cell8.setCellStyle(jeStyle);
                cell8.setCellValue(l.getXjGsJe() != null ? l.getXjGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell9 = row.createCell(9);
                cell9.setCellStyle(jeStyle);
                cell9.setCellValue(l.getXjGsGcf() != null ? l.getXjGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell10 = row.createCell(10);
                cell10.setCellStyle(jeStyle);
                cell10.setCellValue(l.getXjGsElfy() != null ? l.getXjGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell11 = row.createCell(11);
                cell11.setCellStyle(jeStyle);
                cell11.setCellValue(l.getXjGsSlfy() != null ? l.getXjGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell12 = row.createCell(12);
                cell12.setCellStyle(jeStyle);
                cell12.setCellValue(l.getKyGsJe() != null ? l.getKyGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell13 = row.createCell(13);
                cell13.setCellStyle(jeStyle);
                cell13.setCellValue(l.getKyGsGcf() != null ? l.getKyGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");

                Cell cell14 = row.createCell(14);
                cell14.setCellStyle(jeStyle);
                cell14.setCellValue(l.getKyGsElfy() != null ? l.getKyGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell15 = row.createCell(15);
                cell15.setCellStyle(jeStyle);
                cell15.setCellValue(l.getKyGsSlfy() != null ? l.getKyGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell16 = row.createCell(16);
                cell16.setCellStyle(projStyle);
                cell16.setCellValue(l.getProjectClassification());
                Cell cell17 = row.createCell(17);
                cell17.setCellStyle(projStyle);
                cell17.setCellValue(l.getProjectNode() != null ? l.getProjectNode() : "");
                Cell cell18 = row.createCell(18);
                cell18.setCellStyle(projStyle);
                cell18.setCellValue(l.getNodeTime() != null ? formatter.format(l.getNodeTime()) : "");
                Cell cell19 = row.createCell(19);
                cell19.setCellStyle(jeStyle);
                cell19.setCellValue(l.getSumGsJe() != null ? l.getSumGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell20 = row.createCell(20);
                cell20.setCellStyle(jeStyle);
                cell20.setCellValue(l.getGsGcf() != null ? l.getGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell21 = row.createCell(21);
                cell21.setCellStyle(jeStyle);
                cell21.setCellValue(l.getGsElfy() != null ? l.getGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell22 = row.createCell(22);
                cell22.setCellStyle(jeStyle);
                cell22.setCellValue(l.getGsSlfy() != null ? l.getGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell23 = row.createCell(23);
                cell23.setCellStyle(jeStyle);
                cell23.setCellValue(l.getSumYsJe() != null ? l.getSumYsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell24 = row.createCell(24);
                cell24.setCellStyle(jeStyle);
                cell24.setCellValue(l.getSumKzjJe() != null ? l.getSumKzjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell25 = row.createCell(25);
                cell25.setCellStyle(jeStyle);
                cell25.setCellValue(l.getSumHtjJe() != null ? l.getSumHtjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell26 = row.createCell(26);
                cell26.setCellStyle(jeStyle);
                cell26.setCellValue(l.getSumTzBgJe() != null ? l.getSumTzBgJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell27 = row.createCell(27);
                cell27.setCellStyle(jeStyle);
                cell27.setCellValue(l.getSumBgjJe() != null ? l.getSumBgjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell28 = row.createCell(28);
                cell28.setCellStyle(jeStyle);
                cell28.setCellValue(l.getSumJyJe() != null ? l.getSumJyJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell29 = row.createCell(29);
                cell29.setCellStyle(jeStyle);
                cell29.setCellValue(l.getSumJsjJe() != null ? l.getSumJsjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                index++;
            }
            Row row = sheet.createRow(index + 1);
            Cell cell = row.createCell(0);
            cell.setCellStyle(sumStyle);
            cell.setCellValue("汇总");
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(sumStyle);
            cell1.setCellValue("");
            Cell cell2 = row.createCell(2);
            cell2.setCellStyle(sumStyle);
            cell2.setCellValue("");
            Cell cell3 = row.createCell(3);
            cell3.setCellStyle(sumStyle);
            cell3.setCellValue("");
            Cell cell4 = row.createCell(4);
            cell4.setCellStyle(sumStyle);
            cell4.setCellValue("");
            Cell cell5 = row.createCell(5);
            cell5.setCellStyle(sumStyle);
            cell5.setCellValue("");
            Cell cell6 = row.createCell(6);
            cell6.setCellStyle(sumStyle);
            cell6.setCellValue("");
            Cell cell7 = row.createCell(7);
            cell7.setCellStyle(sumStyle);
            cell7.setCellValue("");
            Cell cell8 = row.createCell(8);
            cell8.setCellStyle(sumStyle);
            cell8.setCellValue(s.getXjGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell9 = row.createCell(9);
            cell9.setCellStyle(sumStyle);
            cell9.setCellValue(s.getXjGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell10 = row.createCell(10);
            cell10.setCellStyle(sumStyle);
            cell10.setCellValue(s.getXjGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell11 = row.createCell(11);
            cell11.setCellStyle(sumStyle);
            cell11.setCellValue(s.getXjGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell12 = row.createCell(12);
            cell12.setCellStyle(sumStyle);
            cell12.setCellValue(s.getKyGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell13 = row.createCell(13);
            cell13.setCellStyle(sumStyle);
            cell13.setCellValue(s.getKyGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell14 = row.createCell(14);
            cell14.setCellStyle(sumStyle);
            cell14.setCellValue(s.getKyGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell15 = row.createCell(15);
            cell15.setCellStyle(sumStyle);
            cell15.setCellValue(s.getKyGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell16 = row.createCell(16);
            cell16.setCellStyle(sumStyle);
            cell16.setCellValue("");
            Cell cell17 = row.createCell(17);
            cell17.setCellStyle(sumStyle);
            cell17.setCellValue("");
            Cell cell18 = row.createCell(18);
            cell18.setCellStyle(sumStyle);
            cell18.setCellValue("");
            Cell cell19 = row.createCell(19);
            cell19.setCellStyle(sumStyle);
            cell19.setCellValue(s.getSumGsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell20 = row.createCell(20);
            cell20.setCellStyle(sumStyle);
            cell20.setCellValue(s.getGsGcf().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell21 = row.createCell(21);
            cell21.setCellStyle(sumStyle);
            cell21.setCellValue(s.getGsElfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell22 = row.createCell(22);
            cell22.setCellStyle(sumStyle);
            cell22.setCellValue(s.getGsSlfy().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell23 = row.createCell(23);
            cell23.setCellStyle(sumStyle);
            cell23.setCellValue(s.getSumYsJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell24 = row.createCell(24);
            cell24.setCellStyle(sumStyle);
            cell24.setCellValue(s.getSumKzjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell25 = row.createCell(25);
            cell25.setCellStyle(sumStyle);
            cell25.setCellValue(s.getSumHtjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell26 = row.createCell(26);
            cell26.setCellStyle(sumStyle);
            cell26.setCellValue(s.getSumTzBgJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell27 = row.createCell(27);
            cell27.setCellStyle(sumStyle);
            cell27.setCellValue(s.getSumBgjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell28 = row.createCell(28);
            cell28.setCellStyle(sumStyle);
            cell28.setCellValue(s.getSumJyJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell29 = row.createCell(29);
            cell29.setCellStyle(sumStyle);
            cell29.setCellValue(s.getSumJsjJe().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            wb.write(out);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportProjectJsList(String path, OutputStream out, List<ProjectJsCountVo> list, Map<String, Object> map) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            FileInputStream fis = new FileInputStream(path);
            String ext = path.substring(path.lastIndexOf(".") + 1);
            Workbook wb = null;
            try {
                if (ext.equalsIgnoreCase("xls")) {//03版本及以下
                    wb = new HSSFWorkbook(fis);
                } else if (ext.equalsIgnoreCase("xlsx")) {//07版本及以上
                    wb = new XSSFWorkbook(fis);
                }
            } catch (Exception e) {
                throw new Exception("系统未知的Excel版本", e);
            } finally {
                if (null != fis) fis.close();
            }
            Sheet sheet = wb.getSheetAt(0);
            int index = 2;
            //项目行设置格式
            CellStyle projstyle = wb.createCellStyle();
            //设置水平对齐方式
            projstyle.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐方式
            projstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            projstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            projstyle.setFillBackgroundColor(HSSFColor.AQUA.index);//设置背景色
            projstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            projstyle.setBorderRight((short) 1);
            projstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            projstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            projstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            projstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            projstyle.setWrapText(true);//自动换行
            Font contentFont = wb.createFont();
            contentFont.setFontName("宋体");
            //contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            contentFont.setFontHeightInPoints((short) 11);
            //contentFont.setColor(HSSFColor.GREY_50_PERCENT.index);
            projstyle.setFont(contentFont);
            //合同行设置格式
            CellStyle htstyle = wb.createCellStyle();
            //设置水平对齐方式
            htstyle.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐方式
            htstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景为白色
            htstyle.setFillForegroundColor(IndexedColors.WHITE.index);
            htstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            htstyle.setBorderRight((short) 1);
            htstyle.setFont(contentFont);
            htstyle.setBorderRight((short) 1);
            htstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            htstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            htstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            htstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            htstyle.setWrapText(true);   //自动换行
            //汇总行设置格式
            CellStyle sumStyle = wb.createCellStyle();
            //设置水平对齐方式
            sumStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            //设置垂直对齐方式
            sumStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            sumStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            sumStyle.setFillBackgroundColor(HSSFColor.AQUA.index);//设置背景色
            sumStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            sumStyle.setBorderRight((short) 1);
            sumStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            sumStyle.setFont(contentFont);
            ProjectJsCountVo pj = new ProjectJsCountVo();
            BigDecimal b = new BigDecimal(0);
            pj.setContractAmount(b);
            pj.setGiveAmount(b);
            pj.setMyAuditAmount(b);
            pj.setAgencyAuditAmount(b);
            pj.setDecideAmount(b);
            pj.setDecideAmountPercent(b);
            //计算定审合同数
            int countDecideFlag = 0;
            //计算项目数
            int countProject = 0;
            //计算合同数
            int countProjectJsCountVo = 0;
            Map<String, Object> map2 = new HashMap<String, Object>();
            //项目
            for (ProjectJsCountVo p : list) {
                countProject++;
                //新建行
                Row row = sheet.createRow(index++);
                //新建列
                Cell cell = row.createCell(0);
                cell.setCellStyle(projstyle);
                cell.setCellValue(p.getPriority());
                Cell cell2 = row.createCell(1);
                cell2.setCellStyle(projstyle);
                cell2.setCellValue(p.getProjectCode());
                Cell cell3 = row.createCell(2);
                cell3.setCellStyle(projstyle);
                cell3.setCellValue(p.getProjectName());
                Cell cell4 = row.createCell(3);
                cell4.setCellStyle(projstyle);
                cell4.setCellValue("");
                Cell cell5 = row.createCell(4);
                cell5.setCellStyle(projstyle);
                cell5.setCellValue(p.getContractAmount() != null ? p.getContractAmount() + "" : "");
                Cell cell6 = row.createCell(5);
                cell6.setCellStyle(projstyle);
                cell6.setCellValue(p.getGiveAmount() != null ? p.getGiveAmount() + "" : "");
                Cell cell7 = row.createCell(6);
                cell7.setCellStyle(projstyle);
                cell7.setCellValue(p.getMyAuditAmount() != null ? p.getMyAuditAmount() + "" : "");
                Cell cell8 = row.createCell(7);
                cell8.setCellStyle(projstyle);
                cell8.setCellValue(p.getAgencyAuditAmount() != null ? p.getAgencyAuditAmount() + "" : "");
                Cell cell9 = row.createCell(8);
                cell9.setCellStyle(projstyle);
                cell9.setCellValue(p.getDecideAmount() != null ? p.getDecideAmount() + "" : "");
                Cell cell10 = row.createCell(9);
                cell10.setCellStyle(projstyle);
                cell10.setCellValue("");
                Cell cell11 = row.createCell(10);
                cell11.setCellStyle(projstyle);
                cell11.setCellValue("");
                Cell cell12 = row.createCell(11);
                cell12.setCellStyle(projstyle);
                cell12.setCellValue("");
                Cell cell13 = row.createCell(12);
                cell13.setCellStyle(projstyle);
                cell13.setCellValue(p.getDecideAmountPercent() != null ? p.getDecideAmountPercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "");
                Cell cell14 = row.createCell(13);
                cell14.setCellStyle(projstyle);
                cell14.setCellValue("");
                Cell cell15 = row.createCell(14);
                cell15.setCellStyle(projstyle);
                cell15.setCellValue("");
                Cell cell16 = row.createCell(15);
                cell16.setCellStyle(projstyle);
                cell16.setCellValue("");
                Cell cell17 = row.createCell(16);
                cell17.setCellStyle(projstyle);
                cell17.setCellValue("");
                Cell cell18 = row.createCell(17);
                cell18.setCellStyle(projstyle);
                cell18.setCellValue("");
                Cell cell19 = row.createCell(18);
                cell19.setCellStyle(projstyle);
                cell19.setCellValue("");
                Cell cell20 = row.createCell(19);
                cell20.setCellStyle(projstyle);
                cell20.setCellValue("");
                Cell cell21 = row.createCell(20);
                cell21.setCellStyle(projstyle);
                cell21.setCellValue("");
                Cell cell22 = row.createCell(21);
                cell22.setCellStyle(projstyle);
                cell22.setCellValue("");
                Cell cell23 = row.createCell(22);
                cell23.setCellStyle(projstyle);
                cell23.setCellValue("");
                Cell cell24 = row.createCell(23);
                cell24.setCellStyle(projstyle);
                cell24.setCellValue("");
                Cell cell25 = row.createCell(24);
                cell25.setCellStyle(projstyle);
                cell25.setCellValue("");
                Cell cell26 = row.createCell(25);
                cell26.setCellStyle(projstyle);
                cell26.setCellValue("");
                Cell cell27 = row.createCell(26);
                cell27.setCellStyle(projstyle);
                cell27.setCellValue("");
                Cell cell28 = row.createCell(27);
                cell28.setCellStyle(projstyle);
                cell28.setCellValue("");
                Cell cell29 = row.createCell(28);
                cell29.setCellStyle(projstyle);
                cell29.setCellValue(p.getPersonLiable());
                Cell cell30 = row.createCell(29);
                cell30.setCellStyle(projstyle);
                Cell cell31 = row.createCell(30);
                cell31.setCellStyle(projstyle);
                cell31.setCellValue("");
                Cell cell32 = row.createCell(31);
                cell32.setCellStyle(projstyle);
                cell32.setCellValue("");
                //Map<String,Object> map=new HashMap<String,Object>();
                map.put("projectId", p.getProjectId());
                map.put("index", Integer.parseInt(p.getPriority()) + "");
                List<ProjectJsCountVo> list2 = this.getProjectJsContractList(map);
                //合同
                for (ProjectJsCountVo c : list2) {
                    countProjectJsCountVo++;
                    Row row1 = sheet.createRow(index++);
                    Cell cell_0 = row1.createCell(0);
                    cell_0.setCellStyle(htstyle);
                    cell_0.setCellValue(c.getPriority());
                    Cell cell_2 = row1.createCell(1);
                    cell_2.setCellStyle(htstyle);
                    cell_2.setCellValue(c.getContractCode());
                    Cell cell_3 = row1.createCell(2);
                    cell_3.setCellStyle(htstyle);
                    cell_3.setCellValue(c.getContractName());
                    Cell cell_4 = row1.createCell(3);
                    cell_4.setCellStyle(htstyle);
                    cell_4.setCellValue(c.getPartyB());
                    Cell cell_5 = row1.createCell(4);
                    cell_5.setCellStyle(htstyle);
                    cell_5.setCellValue(c.getContractAmount() != null ? c.getContractAmount() + "" : "");
                    Cell cell_6 = row1.createCell(5);
                    cell_6.setCellStyle(htstyle);
                    cell_6.setCellValue(c.getGiveAmount() != null ? c.getGiveAmount() + "" : "");
                    Cell cell_7 = row1.createCell(6);
                    cell_7.setCellStyle(htstyle);
                    cell_7.setCellValue(c.getMyAuditAmount() != null ? c.getMyAuditAmount() + "" : "");
                    Cell cell_8 = row1.createCell(7);
                    cell_8.setCellStyle(htstyle);
                    cell_8.setCellValue(c.getAgencyAuditAmount() != null ? c.getAgencyAuditAmount() + "" : "");
                    Cell cell_9 = row1.createCell(8);
                    cell_9.setCellStyle(htstyle);
                    cell_9.setCellValue(c.getDecideAmount() != null ? c.getDecideAmount() + "" : "");
                    Cell cell_10 = row1.createCell(9);
                    cell_10.setCellStyle(htstyle);
                    cell_10.setCellValue(c.getOwnerApproval());
                    Cell cell_11 = row1.createCell(10);
                    cell_11.setCellStyle(htstyle);
                    cell_11.setCellValue(c.getDecideTime() != null ? formatter.format(c.getDecideTime()) : "");
                    Cell cell_12 = row1.createCell(11);
                    cell_12.setCellStyle(htstyle);
                    cell_12.setCellValue(c.getReviewReport());
                    Cell cell_13 = row1.createCell(12);
                    cell_13.setCellStyle(htstyle);
                    cell_13.setCellValue(c.getDecideAmountPercent() != null ? c.getDecideAmountPercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "");
                    Cell cell_14 = row1.createCell(13);
                    cell_14.setCellStyle(htstyle);
                    cell_14.setCellValue(c.getSettlement());
                    Cell cell_15 = row1.createCell(14);
                    cell_15.setCellStyle(htstyle);
                    cell_15.setCellValue(c.getPartyBContacts());
                    Cell cell_16 = row1.createCell(15);
                    cell_16.setCellStyle(htstyle);
                    cell_16.setCellValue(c.getExecutiveDepartment());
                    Cell cell_17 = row1.createCell(16);
                    cell_17.setCellStyle(htstyle);
                    cell_17.setCellValue(c.getExecutiveDepartmentContacts());
                    Cell cell_18 = row1.createCell(17);
                    cell_18.setCellStyle(htstyle);
                    cell_18.setCellValue(c.getAcceptanceTime() != null ? formatter.format(c.getAcceptanceTime()) : "");
                    Cell cell_19 = row1.createCell(18);
                    cell_19.setCellStyle(htstyle);
                    cell_19.setCellValue(c.getFinancial());
                    //是否外送
                    Cell cell_20 = row1.createCell(19);
                    cell_20.setCellStyle(htstyle);
                    cell_20.setCellValue(c.getDeliveryFlag());
                    //是否定审
                    Cell cell_21 = row1.createCell(20);
                    cell_21.setCellStyle(htstyle);
                    cell_21.setCellValue(c.getDecideFlag());
                    Cell cell_22 = row1.createCell(21);
                    cell_22.setCellStyle(htstyle);
                    cell_22.setCellValue(c.getRecordNumber());
                    Cell cell_23 = row1.createCell(22);
                    cell_23.setCellStyle(htstyle);
                    cell_23.setCellValue(c.getContractType());
                    Cell cell_24 = row1.createCell(23);
                    cell_24.setCellStyle(htstyle);
                    cell_24.setCellValue(c.getEntrustNumber());
                    Cell cell_25 = row1.createCell(24);
                    cell_25.setCellStyle(htstyle);
                    cell_25.setCellValue(c.getAuditPriceUnit());
                    Cell cell_26 = row1.createCell(25);
                    cell_26.setCellStyle(htstyle);
                    cell_26.setCellValue(c.getAuditPriceUnitContacts());
                    Cell cell_27 = row1.createCell(26);
                    cell_27.setCellStyle(htstyle);
                    cell_27.setCellValue(c.getSendTime() != null ? formatter.format(c.getSendTime()) : "");
                    Cell cell_28 = row1.createCell(27);
                    cell_28.setCellStyle(htstyle);
                    cell_28.setCellValue(c.getAcceptTime() != null ? formatter.format(c.getAcceptTime()) : "");
                    Cell cell_29 = row1.createCell(28);
                    cell_29.setCellStyle(htstyle);
                    cell_29.setCellValue(c.getPersonLiable());
                    Cell cell_30 = row1.createCell(29);
                    cell_30.setCellStyle(htstyle);
                    cell_30.setCellValue(c.getNumber());
                    Cell cell_31 = row1.createCell(30);
                    cell_31.setCellStyle(htstyle);
                    cell_31.setCellValue(c.getReturnReason());
                    //结算进展
                    Cell cell_32 = row1.createCell(31);
                    cell_32.setCellStyle(htstyle);
                    cell_32.setCellValue(c.getJsProgress());
                    if (c.getDecideFlag().equals("是")) {
                        countDecideFlag++;
                    }
                    map2.clear();
                    map2.put("contractId", c.getContractId());
                    //查询当前合同是否有审价任务
                    int temp = costTaskMapper.selectCountByMap(map2);
                    //不需要结算的合同，是否外送 和 是否已经定审 默认为“否”，结算进展默认“审结”
                    if ("不需要".equals(c.getSettlement())) {
                        //是否外送
                        cell_20.setCellValue("否");
                        //是否定审
                        cell_21.setCellValue("是");
                        //结算进展
                        cell_32.setCellValue("审结");
                        //设置份数为0
                        cell_29.setCellValue("0份");
                    } else if ("需要".equals(c.getSettlement()) && temp < 1) {
                        //没有审价任务且需结算的合同，是否外送 和 是否已经定审 默认为“否”，结算进展默认“新建”
                        cell_20.setCellStyle(htstyle);
                        cell_20.setCellValue("否");
                        cell_21.setCellStyle(htstyle);
                        cell_21.setCellValue("否");
                        cell_32.setCellStyle(htstyle);
                        cell_32.setCellValue("新建");
                    }
                }
                //汇总计算
                pj.setContractAmount(p.getContractAmount() == null ? pj.getContractAmount() : pj.getContractAmount().add(p.getContractAmount()));
                pj.setGiveAmount(p.getGiveAmount() == null ? pj.getGiveAmount() : pj.getGiveAmount().add(p.getGiveAmount()));
                pj.setMyAuditAmount(p.getMyAuditAmount() == null ? pj.getMyAuditAmount() : pj.getMyAuditAmount().add(p.getMyAuditAmount()));
                pj.setAgencyAuditAmount(p.getAgencyAuditAmount() == null ? pj.getAgencyAuditAmount() : pj.getAgencyAuditAmount().add(p.getAgencyAuditAmount()));
                pj.setDecideAmount(p.getDecideAmount() == null ? pj.getDecideAmount() : pj.getDecideAmount().add(p.getDecideAmount()));
            }
            pj.setDecideFlag(countDecideFlag + "");
            Row row = sheet.createRow(1);
            Cell cell0 = row.createCell(0);
            cell0.setCellStyle(sumStyle);
            cell0.setCellValue("汇总");
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(sumStyle);
            cell1.setCellValue(countProject);
            Cell cell2 = row.createCell(2);
            cell2.setCellStyle(sumStyle);
            cell2.setCellValue(countProjectJsCountVo);
            Cell cell3 = row.createCell(3);
            cell3.setCellStyle(sumStyle);
            cell3.setCellValue("");
            Cell cell4 = row.createCell(4);
            cell4.setCellStyle(sumStyle);
            cell4.setCellValue(pj.getContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell5 = row.createCell(5);
            cell5.setCellStyle(sumStyle);
            cell5.setCellValue(pj.getGiveAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell6 = row.createCell(6);
            cell6.setCellStyle(sumStyle);
            cell6.setCellValue(pj.getMyAuditAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell7 = row.createCell(7);
            cell7.setCellStyle(sumStyle);
            cell7.setCellValue(pj.getAgencyAuditAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell8 = row.createCell(8);
            cell8.setCellStyle(sumStyle);
            cell8.setCellValue(pj.getDecideAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "");
            Cell cell9 = row.createCell(9);
            cell9.setCellStyle(sumStyle);
            cell9.setCellValue("");
            Cell cell10 = row.createCell(10);
            cell10.setCellStyle(sumStyle);
            cell10.setCellValue("");
            Cell cell11 = row.createCell(11);
            cell11.setCellStyle(sumStyle);
            cell11.setCellValue("");
            Cell cell12 = row.createCell(12);
            cell12.setCellStyle(sumStyle);
            cell12.setCellValue(pj.getDecideAmount().divide(pj.getContractAmount(), 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%");
            Cell cell13 = row.createCell(13);
            cell13.setCellStyle(sumStyle);
            cell13.setCellValue("");
            Cell cell14 = row.createCell(14);
            cell14.setCellStyle(sumStyle);
            cell14.setCellValue("");
            Cell cell15 = row.createCell(15);
            cell15.setCellStyle(sumStyle);
            cell15.setCellValue("");
            Cell cell16 = row.createCell(16);
            cell16.setCellStyle(sumStyle);
            cell16.setCellValue("");
            Cell cell17 = row.createCell(17);
            cell17.setCellStyle(sumStyle);
            cell17.setCellValue("");
            Cell cell18 = row.createCell(18);
            cell18.setCellStyle(sumStyle);
            cell18.setCellValue("");
            Cell cell19 = row.createCell(19);
            cell19.setCellStyle(sumStyle);
            cell19.setCellValue("");
            Cell cell20 = row.createCell(20);
            cell20.setCellStyle(sumStyle);
            cell20.setCellValue(pj.getDecideFlag());
            Cell cell21 = row.createCell(21);
            cell21.setCellStyle(sumStyle);
            cell21.setCellValue("");
            Cell cell22 = row.createCell(22);
            cell22.setCellStyle(sumStyle);
            cell22.setCellValue("");
            Cell cell23 = row.createCell(23);
            cell23.setCellStyle(sumStyle);
            cell23.setCellValue("");
            Cell cell24 = row.createCell(24);
            cell24.setCellStyle(sumStyle);
            cell24.setCellValue("");
            Cell cell25 = row.createCell(25);
            cell25.setCellStyle(sumStyle);
            cell25.setCellValue("");
            Cell cell26 = row.createCell(26);
            cell26.setCellStyle(sumStyle);
            cell26.setCellValue("");
            Cell cell27 = row.createCell(27);
            cell27.setCellStyle(sumStyle);
            cell27.setCellValue("");
            Cell cell28 = row.createCell(28);
            cell28.setCellStyle(sumStyle);
            cell28.setCellValue("");
            Cell cell29 = row.createCell(29);
            cell29.setCellStyle(sumStyle);
            cell29.setCellValue("");
            Cell cell30 = row.createCell(30);
            cell30.setCellStyle(sumStyle);
            cell30.setCellValue("");
            Cell cell31 = row.createCell(31);
            cell31.setCellStyle(sumStyle);
            cell31.setCellValue("");
            wb.write(out);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
	public void exportContractJsList(List<ContractJsCountVo> list, String path,
			String newPath, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
		List<List<String>> rowList=new ArrayList<List<String>>();
		List<String> cellList=new ArrayList<String>();
		for(ContractJsCountVo p :list){
			cellList =new ArrayList<String>();
			cellList.add(p.getPriority());
			cellList.add(p.getProjectName());
			cellList.add("");
			cellList.add(p.getContractCount()+"");
			cellList.add(p.getContractAmount()!=null?p.getContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getSsContractCount()+"");
			cellList.add(p.getSsContractAmount()!=null?p.getSsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getSsWsContractCount()+"");
			cellList.add(p.getSsWsContractAmount()!=null?p.getSsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getSsZsContractCount()+"");
			cellList.add(p.getSsZsContractAmount()!=null?p.getSsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getDsContractCount()+"");
			cellList.add(p.getDsContractAmount()!=null?p.getDsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getDsWsContractCount()+"");
			cellList.add(p.getDsWsContractAmount()!=null?p.getDsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getDsZsContractCount()+"");
			cellList.add(p.getDsZsContractAmount()!=null?p.getDsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
			cellList.add(p.getSsJePercent()!=null?p.getSsJePercent().setScale(2, BigDecimal.ROUND_HALF_UP)+"%":"");
			cellList.add(p.getSsCountPercent()!=null?p.getSsCountPercent().setScale(2, BigDecimal.ROUND_HALF_UP)+"%":"");
			cellList.add(p.getHtJsPercent()!=null?p.getHtJsPercent().setScale(2, BigDecimal.ROUND_HALF_UP)+"%":"");
			cellList.add(p.getMemo());
			rowList.add(cellList);

			Map<String,Object> map=new HashMap<String,Object>();
		    map.put("projectId", p.getProjectId());
		    map.put("index", Integer.parseInt(p.getPriority())+"");
		    List<ContractJsCountVo> list2 = this.getContractJsGroup(map);
		    for(ContractJsCountVo c:list2){
		    	cellList =new ArrayList<String>();
		    	cellList.add(c.getPriority());
		    	cellList.add("");
		    	cellList.add(c.getExecutiveDepartment());
		    	cellList.add(c.getContractCount()+"");
				cellList.add(c.getContractAmount()!=null?c.getContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getSsContractCount()+"");
				cellList.add(c.getSsContractAmount()!=null?c.getSsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getSsWsContractCount()+"");
				cellList.add(c.getSsWsContractAmount()!=null?c.getSsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getSsZsContractCount()+"");
				cellList.add(c.getSsZsContractAmount()!=null?c.getSsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getDsContractCount()+"");
				cellList.add(c.getDsContractAmount()!=null?c.getDsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getDsWsContractCount()+"");
				cellList.add(c.getDsWsContractAmount()!=null?c.getDsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getDsZsContractCount()+"");
				cellList.add(c.getDsZsContractAmount()!=null?c.getDsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP)+"":"");
				cellList.add(c.getSsJePercent()!=null?c.getSsJePercent().setScale(2, BigDecimal.ROUND_HALF_UP)+"%":"");
				cellList.add(c.getSsCountPercent()!=null?c.getSsCountPercent().setScale(2, BigDecimal.ROUND_HALF_UP)+"%":"");
				cellList.add(c.getHtJsPercent()!=null?c.getHtJsPercent().setScale(2, BigDecimal.ROUND_HALF_UP)+"%":"");
				cellList.add(c.getMemo());
				rowList.add(cellList);
		    }
		}
		date.put("合同结算统计表", rowList);
		try {
			ExcelUtil.copyExcel(3,date,path,newPath,request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
    @Override
    public void exportContractJsList(String tableName, List<ContractJsCountVo> list, String path, OutputStream out) {
        try {
            FileInputStream fis = new FileInputStream(path);
            String ext = path.substring(path.lastIndexOf(".") + 1);
            Workbook wb = null;
            try {
                if (ext.equalsIgnoreCase("xls")) {//03版本及以下
                    wb = new HSSFWorkbook(fis);
                } else if (ext.equalsIgnoreCase("xlsx")) {//07版本及以上
                    wb = new XSSFWorkbook(fis);
                }
            } catch (Exception e) {
                throw new Exception("系统未知的Excel版本", e);
            } finally {
                if (null != fis) fis.close();
            }
            Sheet sheet = wb.getSheetAt(0);
            wb.setSheetName(0, tableName);
            int index = 4;
            //项目行设置格式
            CellStyle projstyle = wb.createCellStyle();
            //设置水平对齐方式
            projstyle.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐方式
            projstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            projstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            projstyle.setFillBackgroundColor(HSSFColor.AQUA.index);//设置背景色
            projstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            projstyle.setBorderRight((short) 1);
            projstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            projstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            projstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            projstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            projstyle.setWrapText(true);//自动换行
            Font contentFont = wb.createFont();
            contentFont.setFontName("宋体");
            //contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            contentFont.setFontHeightInPoints((short) 11);
            //contentFont.setColor(HSSFColor.GREY_50_PERCENT.index);
            projstyle.setFont(contentFont);
            //合同行设置格式
            CellStyle htstyle = wb.createCellStyle();
            //设置水平对齐方式
            htstyle.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐方式
            htstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            htstyle.setFillForegroundColor(IndexedColors.WHITE.index);
            htstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            htstyle.setBorderRight((short) 1);
            htstyle.setFont(contentFont);
            htstyle.setBorderRight((short) 1);
            htstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            htstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            htstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            htstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //htstyle.setWrapText(true);//自动换行
            //汇总行设置格式
            CellStyle sumStyle = wb.createCellStyle();
            //设置水平对齐方式
            sumStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            //设置垂直对齐方式
            sumStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            //设置背景
            sumStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            sumStyle.setFillBackgroundColor(HSSFColor.AQUA.index);//设置背景色
            sumStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            sumStyle.setBorderRight((short) 1);
            sumStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            sumStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            sumStyle.setFont(contentFont);
            int projCount = 0;
            ContractJsCountVo s = new ContractJsCountVo();
            BigDecimal zero = new BigDecimal(0);
            s.setContractCount(0);
            s.setContractAmount(zero);
            s.setSsContractCount(0);
            s.setSsContractAmount(zero);
            s.setSsWsContractCount(0);
            s.setSsWsContractAmount(zero);
            s.setSsZsContractCount(0);
            s.setSsZsContractAmount(zero);
            s.setDsContractCount(0);
            s.setDsContractAmount(zero);
            s.setDsContractCount(0);
            s.setDsWsContractAmount(zero);
            s.setDsWsContractCount(0);
            s.setDsZsContractCount(0);
            s.setDsZsContractAmount(zero);

            s.setSsJePercent(zero);
            s.setSsCountPercent(zero);
            s.setHtJsPercent(zero);

            for (ContractJsCountVo p : list) {
                projCount++;
                Row row = sheet.createRow(index++);
                Cell cell = row.createCell(0);
                cell.setCellStyle(projstyle);
                cell.setCellValue(p.getPriority());
                Cell cell1 = row.createCell(1);
                cell1.setCellStyle(projstyle);
                cell1.setCellValue(p.getProjectName());
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(projstyle);
                cell2.setCellValue(p.getContractCount() + "");
                Cell cell3 = row.createCell(3);
                cell3.setCellStyle(projstyle);
                cell3.setCellValue(p.getContractAmount() != null ? p.getContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell4 = row.createCell(4);
                cell4.setCellStyle(projstyle);
                cell4.setCellValue(p.getSsContractCount() + "");
                Cell cell5 = row.createCell(5);
                cell5.setCellStyle(projstyle);
                cell5.setCellValue(p.getSsContractAmount() != null ? p.getSsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell6 = row.createCell(6);
                cell6.setCellStyle(projstyle);
                cell6.setCellValue(p.getSsWsContractCount() + "");
                Cell cell7 = row.createCell(7);
                cell7.setCellStyle(projstyle);
                cell7.setCellValue(p.getSsWsContractAmount() != null ? p.getSsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell8 = row.createCell(8);
                cell8.setCellStyle(projstyle);
                cell8.setCellValue(p.getSsZsContractCount() + "");
                Cell cell9 = row.createCell(9);
                cell9.setCellStyle(projstyle);
                cell9.setCellValue(p.getSsZsContractAmount() != null ? p.getSsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell10 = row.createCell(10);
                cell10.setCellStyle(projstyle);
                cell10.setCellValue(p.getDsContractCount() + "");
                Cell cell11 = row.createCell(11);
                cell11.setCellStyle(projstyle);
                cell11.setCellValue(p.getDsContractAmount() != null ? p.getDsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell12 = row.createCell(12);
                cell12.setCellStyle(projstyle);
                cell12.setCellValue(p.getDsWsContractCount() + "");
                Cell cell13 = row.createCell(13);
                cell13.setCellStyle(projstyle);
                cell13.setCellValue(p.getDsWsContractAmount() != null ? p.getDsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                Cell cell14 = row.createCell(14);
                cell14.setCellStyle(projstyle);
                cell14.setCellValue(p.getDsZsContractCount() + "");
                Cell cell15 = row.createCell(15);
                cell15.setCellStyle(projstyle);
                cell15.setCellValue(p.getDsZsContractAmount() != null ? p.getDsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");

                //p.setSsJePercent(p.getSsJePercent().divide(new BigDecimal(p.getChildrenSize()), 2, BigDecimal.ROUND_HALF_UP));
               // p.setSsCountPercent(p.getSsCountPercent().divide(new BigDecimal(p.getChildrenSize()), 2, BigDecimal.ROUND_HALF_UP));
                //p.setHtJsPercent(p.getHtJsPercent().divide(new BigDecimal(p.getChildrenSize()), 2, BigDecimal.ROUND_HALF_UP));

                Cell cell16 = row.createCell(16);
                cell16.setCellStyle(projstyle);
                cell16.setCellValue(p.getSsJePercent() != null ? p.getSsJePercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "");
                Cell cell17 = row.createCell(17);
                cell17.setCellStyle(projstyle);
                cell17.setCellValue(p.getSsCountPercent() != null ? p.getSsCountPercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "");
                Cell cell18 = row.createCell(18);
                cell18.setCellStyle(projstyle);
                cell18.setCellValue(p.getHtJsPercent() != null ? p.getHtJsPercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "");
                Cell cell19 = row.createCell(19);
                cell19.setCellStyle(projstyle);
                cell19.setCellValue(p.getMemo());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("projectId", p.getProjectId());
                map.put("index", Integer.parseInt(p.getPriority()) + "");
                List<ContractJsCountVo> list2 = this.getContractJsGroup(map);
                for (ContractJsCountVo c : list2) {
                    Row row1 = sheet.createRow(index++);
                    Cell cell_0 = row1.createCell(0);
                    cell_0.setCellStyle(htstyle);
                    cell_0.setCellValue(c.getPriority());
                    Cell cell_1 = row1.createCell(1);
                    cell_1.setCellStyle(htstyle);
                    cell_1.setCellValue(c.getExecutiveDepartment());
                    Cell cell_2 = row1.createCell(2);
                    cell_2.setCellStyle(htstyle);
                    /*if (null == c.getMyAuditAmont() || new BigDecimal("0.00").equals(c.getMyAuditAmont())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setSsContractCount(c.getContractCount());
                        }
                    } else {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setSsContractCount(c.getSsContractCount() + c.getSumZscount());
                        }
                    }*/
                    cell_2.setCellValue(c.getContractCount() + "");
                    Cell cell_3 = row1.createCell(3);
                    cell_3.setCellStyle(htstyle);
                    cell_3.setCellValue(c.getContractAmount() != null ? c.getContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_4 = row1.createCell(4);
                    cell_4.setCellStyle(htstyle);
                    cell_4.setCellValue(c.getSsContractCount() + "");
                    Cell cell_5 = row1.createCell(5);
                    cell_5.setCellStyle(htstyle);
                    cell_5.setCellValue(c.getSsContractAmount() != null ? c.getSsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_6 = row1.createCell(6);
                    cell_6.setCellStyle(htstyle);
                    cell_6.setCellValue(c.getSsWsContractCount() + "");
                    Cell cell_7 = row1.createCell(7);
                    cell_7.setCellStyle(htstyle);
                    cell_7.setCellValue(c.getSsWsContractAmount() != null ? c.getSsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_8 = row1.createCell(8);
                    cell_8.setCellStyle(htstyle);
                    /*if (null == c.getMyAuditAmont() || new BigDecimal("0.00").equals(c.getMyAuditAmont())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setSsZsContractCount(c.getContractCount());
                        }
                    } else {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setSsZsContractCount(c.getSsZsContractCount() + c.getSumZscount());
                        }
                    }*/
                    cell_8.setCellValue(c.getSsZsContractCount() + "");
                    Cell cell_9 = row1.createCell(9);
                    cell_9.setCellStyle(htstyle);
                    cell_9.setCellValue(c.getSsZsContractAmount() != null ? c.getSsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_10 = row1.createCell(10);
                    cell_10.setCellStyle(htstyle);
                    /*if (null == c.getMyAuditAmont() || new BigDecimal("0.00").equals(c.getMyAuditAmont())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setDsContractCount(c.getContractCount());
                        }
                    } else {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setDsContractCount(c.getDsContractCount() + c.getSumZscount());
                        }
                    }*/
                    cell_10.setCellValue(c.getDsContractCount() + "");
                    Cell cell_11 = row1.createCell(11);
                    cell_11.setCellStyle(htstyle);
                    cell_11.setCellValue(c.getDsContractAmount() != null ? c.getDsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_12 = row1.createCell(12);
                    cell_12.setCellStyle(htstyle);
                    cell_12.setCellValue(c.getDsWsContractCount() + "");
                    Cell cell_13 = row1.createCell(13);
                    cell_13.setCellStyle(htstyle);
                    cell_13.setCellValue(c.getDsWsContractAmount() != null ? c.getDsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_14 = row1.createCell(14);
                    cell_14.setCellStyle(htstyle);
                    /*if (null == c.getDecideAmount() || new BigDecimal("0.00").equals(c.getDecideAmount())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setDsZsContractCount(c.getContractCount());
                        }
                    } else {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setDsZsContractCount(c.getDsZsContractCount() + c.getSumZscount());
                        }
                    }*/
                    cell_14.setCellValue(c.getDsZsContractCount() + "");
                    Cell cell_15 = row1.createCell(15);
                    cell_15.setCellStyle(htstyle);
                    cell_15.setCellValue(c.getDsZsContractAmount() != null ? c.getDsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
                    Cell cell_16 = row1.createCell(16);
                    cell_16.setCellStyle(htstyle);
                    if (null == c.getMyAuditAmont() || new BigDecimal("0.00").equals(c.getMyAuditAmont())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setSsJePercent(new BigDecimal("100"));
                        }
                    }
                    cell_16.setCellValue(c.getSsJePercent() != null ? c.getSsJePercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "0.00%");
                    Cell cell_17 = row1.createCell(17);
                    cell_17.setCellStyle(htstyle);
                    if (null == c.getMyAuditAmont() || new BigDecimal("0.00").equals(c.getMyAuditAmont())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setSsCountPercent(new BigDecimal("100"));
                        }
                    }
                    cell_17.setCellValue(c.getSsCountPercent() != null ? c.getSsCountPercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "0.00%");
                    Cell cell_18 = row1.createCell(18);
                    cell_18.setCellStyle(htstyle);
                    if (null == c.getMyAuditAmont() || new BigDecimal("0.00").equals(c.getMyAuditAmont())) {
                        if (c.getSettlementStr().indexOf("不需要") >= 0) {
                            c.setHtJsPercent(new BigDecimal("100"));
                        }
                    }
                    cell_18.setCellValue(c.getHtJsPercent() != null ? c.getHtJsPercent().setScale(2, BigDecimal.ROUND_HALF_UP) + "%" : "0.00%");
                    Cell cell_19 = row1.createCell(19);
                    cell_19.setCellStyle(htstyle);
                    cell_19.setCellValue(c.getMemo());
                }
                s.setContractCount(s.getContractCount() + p.getContractCount());
                s.setContractAmount(p.getContractAmount() != null ? s.getContractAmount().add(p.getContractAmount()) : s.getContractAmount());
                s.setSsContractCount(s.getSsContractCount() + p.getSsContractCount());
                s.setSsContractAmount(p.getSsContractAmount() != null ? s.getSsContractAmount().add(p.getSsContractAmount()) : s.getSsContractAmount());
                s.setSsWsContractCount(s.getSsWsContractCount() + p.getSsWsContractCount());
                s.setSsWsContractAmount(p.getSsWsContractAmount() != null ? s.getSsWsContractAmount().add(p.getSsWsContractAmount()) : s.getSsWsContractAmount());
                s.setSsZsContractCount(s.getSsZsContractCount() + p.getSsZsContractCount());
                s.setSsZsContractAmount(p.getSsZsContractAmount() != null ? s.getSsZsContractAmount().add(p.getSsZsContractAmount()) : s.getSsZsContractAmount());
                s.setDsContractCount(s.getDsContractCount() + p.getDsContractCount());
                s.setDsContractAmount(p.getDsContractAmount() != null ? s.getDsContractAmount().add(p.getDsContractAmount()) : s.getDsContractAmount());
                s.setDsWsContractCount(s.getDsWsContractCount() + p.getDsWsContractCount());
                s.setDsWsContractAmount(p.getDsWsContractAmount() != null ? s.getDsWsContractAmount().add(p.getDsWsContractAmount()) : s.getDsWsContractAmount());
                s.setDsZsContractCount(s.getDsZsContractCount() + p.getDsZsContractCount());
                s.setDsZsContractAmount(p.getDsZsContractAmount() != null ? s.getDsZsContractAmount().add(p.getDsZsContractAmount()) : s.getDsZsContractAmount());
            
                //
                //s.setSsJePercent((p.getSsJePercent() == null ? s.getSsJePercent() : s.getSsJePercent().add(p.getSsJePercent())).divide(new BigDecimal(p.getChildrenSize()), 2, BigDecimal.ROUND_HALF_UP));
                //s.setSsCountPercent((p.getSsCountPercent() == null ? p.getSsCountPercent() : p.getSsCountPercent().add(p.getSsCountPercent())).divide(new BigDecimal(p.getChildrenSize()), 2, BigDecimal.ROUND_HALF_UP));
                //s.setHtJsPercent((p.getHtJsPercent() == null ? p.getHtJsPercent() : p.getHtJsPercent().add(p.getHtJsPercent())).divide(new BigDecimal(p.getChildrenSize()), 2, BigDecimal.ROUND_HALF_UP));
                s.setSsJePercent((p.getSsJePercent() == null ? s.getSsJePercent() : s.getSsJePercent().add(p.getSsJePercent())));
                s.setSsCountPercent((p.getSsCountPercent() == null ? s.getSsCountPercent() : s.getSsCountPercent().add(p.getSsCountPercent())));
                s.setHtJsPercent((p.getHtJsPercent() == null ? s.getHtJsPercent() : s.getHtJsPercent().add(p.getHtJsPercent())));
            }
            BigDecimal ssContractAmount=s.getSsContractAmount().multiply(new BigDecimal(10000).setScale(2, BigDecimal.ROUND_HALF_UP));
            BigDecimal dsContractAmount=s.getDsContractAmount().multiply(new BigDecimal(10000).setScale(2, BigDecimal.ROUND_HALF_UP));
            BigDecimal contractCount=new BigDecimal(s.getContractCount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal dsContractCount=new BigDecimal(s.getDsContractCount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal ssContractCount=new BigDecimal(s.getSsContractCount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            
            BigDecimal temSsJePercent=(dsContractAmount).divide(ssContractAmount,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            BigDecimal temSsCountPercent=dsContractCount.divide(ssContractCount,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            BigDecimal temHtJsPercent=dsContractCount.divide(contractCount,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            
            
//            if (s.getSsContractAmount().compareTo(zero) > 0) {
//                s.setSsJePercent(s.getDsContractAmount().divide(s.getSsContractAmount(), 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100)));
//            }
//            if (s.getSsContractCount() > 0) {
//                s.setSsCountPercent(new BigDecimal(s.getDsContractCount() / s.getSsContractCount() * 100));
//            }
//            if (s.getContractCount() > 0) {
//                s.setHtJsPercent(new BigDecimal(s.getDsContractCount() / s.getContractCount() * 100));
//            }
            Row row1 = sheet.createRow(3);
            Cell cell_0 = row1.createCell(0);
            cell_0.setCellStyle(sumStyle);
            cell_0.setCellValue("汇总");
            Cell cell_1 = row1.createCell(1);
            cell_1.setCellStyle(sumStyle);
            cell_1.setCellValue(projCount);
            Cell cell_2 = row1.createCell(2);
            cell_2.setCellStyle(sumStyle);
            cell_2.setCellValue(s.getContractCount() + "");
            Cell cell_3 = row1.createCell(3);
            cell_3.setCellStyle(sumStyle);
            cell_3.setCellValue(s.getContractAmount() != null ? s.getContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_4 = row1.createCell(4);
            cell_4.setCellStyle(sumStyle);
            cell_4.setCellValue(s.getSsContractCount() + "");
            Cell cell_5 = row1.createCell(5);
            cell_5.setCellStyle(sumStyle);
            cell_5.setCellValue(s.getSsContractAmount() != null ? s.getSsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_6 = row1.createCell(6);
            cell_6.setCellStyle(sumStyle);
            cell_6.setCellValue(s.getSsWsContractCount() + "");
            Cell cell_7 = row1.createCell(7);
            cell_7.setCellStyle(sumStyle);
            cell_7.setCellValue(s.getSsWsContractAmount() != null ? s.getSsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_8 = row1.createCell(8);
            cell_8.setCellStyle(sumStyle);
            cell_8.setCellValue(s.getSsZsContractCount() + "");
            Cell cell_9 = row1.createCell(9);
            cell_9.setCellStyle(sumStyle);
            cell_9.setCellValue(s.getSsZsContractAmount() != null ? s.getSsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_10 = row1.createCell(10);
            cell_10.setCellStyle(sumStyle);
            cell_10.setCellValue(s.getDsContractCount() + "");
            Cell cell_11 = row1.createCell(11);
            cell_11.setCellStyle(sumStyle);
            cell_11.setCellValue(s.getDsContractAmount() != null ? s.getDsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_12 = row1.createCell(12);
            cell_12.setCellStyle(sumStyle);
            cell_12.setCellValue(s.getDsWsContractCount() + "");
            Cell cell_13 = row1.createCell(13);
            cell_13.setCellStyle(sumStyle);
            cell_13.setCellValue(s.getDsWsContractAmount() != null ? s.getDsWsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_14 = row1.createCell(14);
            cell_14.setCellStyle(sumStyle);
            cell_14.setCellValue(s.getDsZsContractCount() + "");
            Cell cell_15 = row1.createCell(15);
            cell_15.setCellStyle(sumStyle);
            cell_15.setCellValue(s.getDsZsContractAmount() != null ? s.getDsZsContractAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "" : "");
            Cell cell_16 = row1.createCell(16);
            cell_16.setCellStyle(sumStyle);
            cell_16.setCellValue(temSsJePercent!=null?temSsJePercent.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%" : "");
            Cell cell_17 = row1.createCell(17);
            cell_17.setCellStyle(sumStyle);
            cell_17.setCellValue(temSsCountPercent!=null?temSsCountPercent.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%" : "");
            Cell cell_18 = row1.createCell(18);
            cell_18.setCellStyle(sumStyle);
            cell_18.setCellValue(temHtJsPercent!=null?temHtJsPercent.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%" : "");
            Cell cell_19 = row1.createCell(19);
            cell_19.setCellStyle(sumStyle);
            cell_19.setCellValue("");
            wb.write(out);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void exportProjectZb(List<CostProject> list, String path, String newPath, HttpServletRequest request, HttpServletResponse response) {
        Map<String, List<List<String>>> date = new HashMap<String, List<List<String>>>();
        List<List<String>> rowList = new ArrayList<List<String>>();
        List<String> cellList = new ArrayList<String>();
        for (CostProject l : list) {
            cellList = new ArrayList<String>();
            cellList.add(l.getCode());
            cellList.add(l.getName());
            cellList.add(l.getProjectClassification());
            cellList.add(l.getArea());
            cellList.add(l.getPersonLiableId());
            cellList.add(l.getUnit());
            cellList.add(l.getXjGsCost() != null ? l.getXjGsCost().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getCostUnit() : "");
            cellList.add(l.getXjGsArchitectural() != null ? l.getXjGsArchitectural().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getArchitecturalUnit() : "");
            cellList.add(l.getXjGsInstallation() != null ? l.getXjGsInstallation().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getInstallationUnit() : "");
            cellList.add(l.getOutdoorCost() != null ? l.getOutdoorCost().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getOutdoorUnit() : "");

            cellList.add(l.getKyGsCost() != null ? l.getKyGsCost().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getCostUnit() : "");
            cellList.add(l.getKyGsArchitectural() != null ? l.getKyGsArchitectural().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getArchitecturalUnit() : "");
            cellList.add(l.getKyGsInstallation() != null ? l.getKyGsInstallation().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getInstallationUnit() : "");
            cellList.add(l.getOutdoorKyGs() != null ? l.getOutdoorKyGs().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getOutdoorUnit() : "");

            cellList.add(l.getGsCost() != null ? l.getGsCost().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getCostUnit() : "");
            cellList.add(l.getGsArchitectural() != null ? l.getGsArchitectural().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getArchitecturalUnit() : "");
            cellList.add(l.getGsInstallation() != null ? l.getGsInstallation().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getInstallationUnit() : "");
            cellList.add(l.getOutdoorGsCost() != null ? l.getOutdoorGsCost().setScale(2, BigDecimal.ROUND_HALF_UP) + l.getOutdoorUnit() : "");

            rowList.add(cellList);
        }
        date.put("项目指标", rowList);
        try {
            ExcelUtil.copyExcel(2, date, path, newPath, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public GlobalResult saveProject(CostProject project) {
        GlobalResult result = new GlobalResult();
        String projId = "";
        try {
            CostProjectExample e = new CostProjectExample();
            com.cost168.costaudit.pojo.cost.CostProjectExample.Criteria criteria = e.createCriteria();
            criteria.andCodeEqualTo(project.getCode());
            List<CostProject> list = this.selectByExample(e);
            if (list.size() == 0) {
                SysUser user = shiroUtil.getInstance().currentUser();
                projId = UUID.randomUUID().toString().replace("-", "");
                project.setId(projId);
                project.setCreatorTime(new Date());
                project.setCreator(user.getName());
                if (project.getSumGsJe() == null || project.getSumGsJe().compareTo(new BigDecimal(0)) != 1) {//不大于0，取工程费+二类费用+三类费用的和
                    if (project.getGsGcf() == null) {
                        project.setGsGcf(new BigDecimal(0));
                    }
                    if (project.getGsElfy() == null) {
                        project.setGsElfy(new BigDecimal(0));
                    }
                    if (project.getGsSlfy() == null) {
                        project.setGsSlfy(new BigDecimal(0));
                    }
                    project.setSumGsJe(project.getGsGcf().add(project.getGsElfy()).add(project.getGsSlfy()));
                }
                if (project.getXjGsJe() == null || project.getXjGsJe().compareTo(new BigDecimal(0)) != 1) {//不大于0，取工程费+二类费用+三类费用的和
                    if (project.getXjGsGcf() == null) {
                        project.setXjGsGcf(new BigDecimal(0));
                    }
                    if (project.getXjGsElfy() == null) {
                        project.setXjGsElfy(new BigDecimal(0));
                    }
                    if (project.getXjGsSlfy() == null) {
                        project.setXjGsSlfy(new BigDecimal(0));
                    }
                    project.setXjGsJe(project.getXjGsGcf().add(project.getXjGsElfy()).add(project.getXjGsSlfy()));
                }
                if (project.getKyGsJe() == null || project.getKyGsJe().compareTo(new BigDecimal(0)) != 1) {//不大于0，取工程费+二类费用+三类费用的和
                    if (project.getKyGsGcf() == null) {
                        project.setKyGsGcf(new BigDecimal(0));
                    }
                    if (project.getKyGsElfy() == null) {
                        project.setKyGsElfy(new BigDecimal(0));
                    }
                    if (project.getKyGsSlfy() == null) {
                        project.setKyGsSlfy(new BigDecimal(0));
                    }
                    project.setKyGsJe(project.getKyGsGcf().add(project.getKyGsElfy()).add(project.getKyGsSlfy()));
                }
                this.insertSelective(project);
                costProjectPeriodService.initData(projId);
                result.setStatus(200);
                result.setMsg(projId);
            } else {
                result.setStatus(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public GlobalResult updateProject(CostProject project) {
        GlobalResult result = new GlobalResult();
        try {
            CostProjectExample e = new CostProjectExample();
            com.cost168.costaudit.pojo.cost.CostProjectExample.Criteria criteria = e.createCriteria();
            criteria.andCodeEqualTo(project.getCode());
            List<CostProject> list = this.selectByExample(e);
            CostProject p = this.selectByPrimaryKey(project.getId());
            if (list.size() == 0 || p.getCode().equals(project.getCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (project.getNodeTimeStr() != null && !"".equals(project.getNodeTimeStr())) {
                    String nodeTimeStr = project.getNodeTimeStr() + " 01:09:06";
                    project.setNodeTime(sdf.parse(nodeTimeStr));
                }

                if (project.getPlanOverTimeStr() != null && !"".equals(project.getPlanOverTimeStr())) {
                    String planOverTimeStr = project.getPlanOverTimeStr() + " 01:09:06";
                    project.setPlanOverTime(sdf.parse(planOverTimeStr));
                }
                if (project.getSumGsJe() == null || project.getSumGsJe().compareTo(new BigDecimal(0)) != 1) {//不大于0，取工程费+二类费用+三类费用的和
                    if (project.getGsGcf() == null) {
                        project.setGsGcf(new BigDecimal(0));
                    }
                    if (project.getGsElfy() == null) {
                        project.setGsElfy(new BigDecimal(0));
                    }
                    if (project.getGsSlfy() == null) {
                        project.setGsSlfy(new BigDecimal(0));
                    }
                    project.setSumGsJe(project.getGsGcf().add(project.getGsElfy()).add(project.getGsSlfy()));
                }
                if (project.getXjGsJe() == null || project.getXjGsJe().compareTo(new BigDecimal(0)) != 1) {//不大于0，取工程费+二类费用+三类费用的和
                    if (project.getXjGsGcf() == null) {
                        project.setXjGsGcf(new BigDecimal(0));
                    }
                    if (project.getXjGsElfy() == null) {
                        project.setXjGsElfy(new BigDecimal(0));
                    }
                    if (project.getXjGsSlfy() == null) {
                        project.setXjGsSlfy(new BigDecimal(0));
                    }
                    project.setXjGsJe(project.getXjGsGcf().add(project.getXjGsElfy()).add(project.getXjGsSlfy()));
                }
                if (project.getKyGsJe() == null || project.getKyGsJe().compareTo(new BigDecimal(0)) != 1) {//不大于0，取工程费+二类费用+三类费用的和
                    if (project.getKyGsGcf() == null) {
                        project.setKyGsGcf(new BigDecimal(0));
                    }
                    if (project.getKyGsElfy() == null) {
                        project.setKyGsElfy(new BigDecimal(0));
                    }
                    if (project.getKyGsSlfy() == null) {
                        project.setKyGsSlfy(new BigDecimal(0));
                    }
                    project.setKyGsJe(project.getKyGsGcf().add(project.getKyGsElfy()).add(project.getKyGsSlfy()));
                }
                this.updateByPrimaryKeySelective(project);
                result.setStatus(200);
            } else {
                result.setStatus(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public List<ContractJsCountVo> getContractJsList2(Map<String, Object> map,
                                                      int page, int rows) {
        List<ContractJsCountVo> list = costProjectMapper.getContractJsList2(map);
        int i = (page - 1) * rows + 1;
        DecimalFormat df = new DecimalFormat("#.00");
        for (ContractJsCountVo c : list) {
            c.setPriority(i + "");
            i++;
            //金额以万元显示，并四舍五入
            if (c.getContractAmount() != null) {
                double temp = c.getContractAmount().doubleValue();
                temp = Double.parseDouble(df.format(temp));
                c.setContractAmount(new BigDecimal(temp));
            }
            if (c.getSsContractAmount() != null) {
                double temp2 = c.getSsContractAmount().doubleValue();
                temp2 = Double.parseDouble(df.format(temp2));
                c.setSsContractAmount(new BigDecimal(temp2));
            }
            if (c.getSsWsContractAmount() != null) {
                double temp3 = c.getSsWsContractAmount().doubleValue();
                temp3 = Double.parseDouble(df.format(temp3));
                c.setSsWsContractAmount(new BigDecimal(temp3));
            }
            if (c.getSsZsContractAmount() != null) {
                double temp4 = c.getSsZsContractAmount().doubleValue();
                temp4 = Double.parseDouble(df.format(temp4));
                c.setSsZsContractAmount(new BigDecimal(temp4));
            }
            if (c.getDsContractAmount() != null) {
                double temp5 = c.getDsContractAmount().doubleValue();
                temp5 = Double.parseDouble(df.format(temp5));
                c.setDsContractAmount(new BigDecimal(temp5));
            }
        }
        return list;
    }

    @Override
    public int getContractJsCount2(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return costProjectMapper.getContractJsCount2(map);
    }

    @Override
    public String getAAA(String projectId) {
        return costProjectMapper.getAAA(projectId);
    }

    @Override
    public String getBBB(String id) {
        return costProjectMapper.getBBB(id);
    }

    @Override
    public String getContractAmount(Map<String, Object> map) {
        return costProjectMapper.getContractAmount(map);
    }
}
