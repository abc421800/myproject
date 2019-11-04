package com.cost168.costaudit.service.cost.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cost168.costaudit.mapper.cost.CostDpctRelationMapper;
import com.cost168.costaudit.mapper.cost.CostTaskMapper;
import com.cost168.costaudit.pojo.cost.CostContract;
import com.cost168.costaudit.pojo.cost.CostDocument;
import com.cost168.costaudit.pojo.cost.CostDpctRelation;
import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.pojo.cost.CostTaskExample;
import com.cost168.costaudit.pojo.cost.CostTaskExample.Criteria;
import com.cost168.costaudit.pojo.cost.CostTaskType;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostContractService;
import com.cost168.costaudit.service.cost.CostDocumentService;
import com.cost168.costaudit.service.cost.CostProjectService;
import com.cost168.costaudit.service.cost.CostTaskService;
import com.cost168.costaudit.service.cost.CostTaskTypeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.ExcelUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostTaskServiceImpl implements CostTaskService{

	@Autowired
	private CostTaskMapper costTaskMapper;
	@Autowired
	private CostDpctRelationMapper costDpctRelationMapper;
	@Autowired
	private CostTaskTypeService costTaskTypeService;
	@Autowired
	private CostProjectService costProjectService;
	@Autowired
	private CostContractService costContractService;
	@Autowired
	private CostDocumentService costDocumentService;

	
	@Override
	public List<CostTask> selectByExample(CostTaskExample example) {
		return costTaskMapper.selectByExample(example);
	}

	@Override
	public CostTask selectByPrimaryKey(String id) {
		return costTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostTask record) {
		record.setCreateTime(new Date());
		return costTaskMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostTask record) {
		return costTaskMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CostTask> selectByDocId(String docId) {
		CostTaskExample example=new CostTaskExample();
		Criteria criteria = example.createCriteria();
		criteria.andDocumentIdEqualTo(docId);
		return costTaskMapper.selectByExample(example);
	}

	@Override
	public List<CostTask> selectListByMap(Map<String, Object> map) {
		return costTaskMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costTaskMapper.selectCountByMap(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costTaskMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertSelectiveMap(CostTask costTask, CostDpctRelation dpct,Map<String, Object> map) {
		costTask.setCreateTime(new Date());
		costTaskMapper.insert(costTask);
		costDpctRelationMapper.insertSelective(dpct);
	}

	//估概导入
	@Override
	public List<CostTask> importTaskGg(HttpServletRequest request) {
		List<CostTask> listTask=new ArrayList<CostTask>();
		try{
			 SysUser currentUser=shiroUtil.getInstance().currentUser();
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					//源文件后缀
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					//新文件名称
					Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
					String path=(String) props.get("fileupload");
					path=path+"project/"+nowDate+"/";
					File filePath=new File(path+fileName);
					if (!filePath.exists()) {    
						filePath.mkdirs();    
					}
					file.transferTo(filePath); 
					String suffix=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
					Workbook wb = null;
					InputStream stream = new FileInputStream(filePath);
					if (suffix.equals("xls")) {
						POIFSFileSystem fs = new POIFSFileSystem(stream);
						wb = new HSSFWorkbook(fs);
					} else if (suffix.equals("xlsx")) {
						wb = new XSSFWorkbook(stream);
					}
					Sheet sheet = wb.getSheetAt(0);
					if(sheet!=null){
						int num = sheet.getLastRowNum();
						CostTask tt =null;
						CostTask resut=null;
						for (int i = 1; i <= num; i++) {
							// 循环输出表格中的内容
							Row row = sheet.getRow(i);
							if(row!=null){
								 Cell cell0=row.getCell(0);
								 if(null!=cell0){
							    resut=new CostTask();
								try {
									//去重复
									CostTask task = selectByTaskCode(row.getCell(3).toString());
									
									if(null!=task){
										
										if(row.getCell(0).toString()!=null && !"".equals(row.getCell(0).toString())){
									    	CostProject proj = costProjectService.selectByCode(row.getCell(0).toString());
									    	if(proj!=null){
									    		task.setProjectId(proj.getId());
									    		task.setProjectName(proj.getName());
									    	}
									    }
									    if(null!=row.getCell(1).toString() && !"".equals(row.getCell(1).toString())){
									    	CostContract contract = costContractService.selectByContractCode(row.getCell(1).toString());
									    	if(null!=contract){
									    		task.setContractId(contract.getId());
									    		task.setContractName(contract.getName());
									    	}
									    }
									    if(row.getCell(2).toString()!=null && !"".equals(row.getCell(2).toString())){
									    	CostDocument d = costDocumentService.selectBySymbol(row.getCell(2).toString());
									    	if(d!=null){
									    		task.setDocumentId(d.getId());
									    	}
									    }
									    task.setName(null!=row.getCell(4)?row.getCell(4).toString():null);
									    if(null!=row.getCell(5) && !"".equals(row.getCell(5))){
									    	String auditPriceType=row.getCell(5).toString();
									    	task.setAuditPriceType(auditPriceType);
									    	/*CostTaskType costTaskType=costTaskTypeService.getCostTaskTypeLeafByName(auditPriceType);
									    	if(null!=costTaskType && null!=costTaskType.getPid()){
									    		CostTaskType costTaskTypeParemt=costTaskTypeService.selectByPrimaryKey(costTaskType.getPid());
									    		if(null!=costTaskTypeParemt){
									    			p.setAuditPriceTypecn(costTaskTypeParemt.getName());
									    		}
									    	}*/
									    	task.setAuditPriceTypecn("估概预结");
									    }
									    
										if(null!=row.getCell(6) && !"".equals(row.getCell(6))){
											Double taskAmount=ExcelUtil.getValue(row.getCell(6));
											BigDecimal bdTaskAmount=new BigDecimal(taskAmount); 
											bdTaskAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
											task.setGiveAmount(bdTaskAmount);
										}
									    if(row.getCell(7)!=null && !"".equals(row.getCell(7).toString())){
									    	Double myAuditAmount=ExcelUtil.getValue(row.getCell(7));
									    	task.setMyAuditAmount(new BigDecimal(myAuditAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
									    }
									    if(row.getCell(8)!=null && !"".equals(row.getCell(8).toString())){
									    	Double agencyAuditAmountunt=ExcelUtil.getValue(row.getCell(8));
									    	task.setAgencyAuditAmount(new BigDecimal(agencyAuditAmountunt).setScale(2, BigDecimal.ROUND_HALF_UP));
									    }
									    if(row.getCell(9)!=null && !"".equals(row.getCell(9).toString())){
									    	Double decideAmount=ExcelUtil.getValue(row.getCell(9));
									    	task.setDecideAmount(new BigDecimal(decideAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
									    }
									    task.setFinalizeNumber(null!=row.getCell(10)?row.getCell(10).toString():null);
									    task.setDeliveryFlag(null!=row.getCell(11)?row.getCell(11).toString():null);
									    task.setEntrustNumber(null!=row.getCell(12)?row.getCell(12).toString():null);
									    task.setAuditPriceUnit(null!=row.getCell(13)?row.getCell(13).toString():null);
									    
									    Cell cell14 =row.getCell(14);
									    if (cell14 != null && !"".equals(cell14.toString())) { 
									    	if (cell14.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell14)) {
									    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
									    		Date date = HSSFDateUtil.getJavaDate(cell14.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setDecideTime(sdf.parse(value));
									    	} 
									    }
									    task.setStatus(null!=row.getCell(15)?row.getCell(15).toString():null);
									    task.setReviewReportn(null!=row.getCell(16)?row.getCell(16).toString():null);
									    
									    
									    //p.setUmber(null!=row.getCell(18)?row.getCell(18).toString():null);
									   
									    task.setPersonLiable(null!=row.getCell(17)?row.getCell(17).toString():null);
									    
									    Cell cell18 =row.getCell(18);
									    if (cell18 != null && !"".equals(cell18.toString())) { 
									    	if (cell18.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell18)) {
									    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
									    		Date date = HSSFDateUtil.getJavaDate(cell18.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setReceiveTime(sdf.parse(value));
									    	} 
									    }
									    task.setFixedFinancial(null!=row.getCell(19)?row.getCell(19).toString():null);
									    Cell cell20 =row.getCell(20);
									    if (cell20 != null && !"".equals(cell20.toString())) { 
									    	if (cell20.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell20)) {
									    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
									    		Date date = HSSFDateUtil.getJavaDate(cell20.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setSubmissionTime(sdf.parse(value));
									    	} 
									    }
									    task.setProgressDescription(null!=row.getCell(21)?row.getCell(21).toString():null);
									    task.setCheckExplain(null!=row.getCell(22)?row.getCell(22).toString():null);
									    task.setRetrial(null!=row.getCell(23)?row.getCell(23).toString():null);
									    Cell cell24 =row.getCell(24);
									    if (cell24 != null && !"".equals(cell24.toString())) { 
									    	if (cell24.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell24)) {
									    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
									    		Date date = HSSFDateUtil.getJavaDate(cell24.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setAcceptanceTime(sdf.parse(value));
									    	} 
									    }
									    task.setFinancial(null!=row.getCell(25)?row.getCell(25).toString():null);
									    task.setCreater(currentUser.getName());
									    task.setCreateTime(new Date());
									    costTaskMapper.updateByPrimaryKeySelective(task);
										continue;
									}
									CostTask p = new CostTask();
								    String taskId = UUID.randomUUID().toString().replace("-", "");
								    p.setId(taskId);
								    if(row.getCell(0).toString()!=null && !"".equals(row.getCell(0).toString())){
								    	CostProject proj = costProjectService.selectByCode(row.getCell(0).toString());
								    	if(proj!=null){
								    		p.setProjectId(proj.getId());
								    		p.setProjectName(proj.getName());
								    	}
								    }
								    if(null!=row.getCell(1).toString() && !"".equals(row.getCell(1).toString())){
								    	CostContract contract = costContractService.selectByContractCode(row.getCell(1).toString());
								    	if(null!=contract){
								    		p.setContractId(contract.getId());
								    		p.setContractName(contract.getName());
								    	}
								    }
								    if(row.getCell(2).toString()!=null && !"".equals(row.getCell(2).toString())){
								    	CostDocument d = costDocumentService.selectBySymbol(row.getCell(2).toString());
								    	if(d!=null){
								    		p.setDocumentId(d.getId());
								    	}
								    }
								    p.setCode(null!=row.getCell(3)?row.getCell(3).toString():null);
								    p.setName(null!=row.getCell(4)?row.getCell(4).toString():null);
								    if(null!=row.getCell(5) && !"".equals(row.getCell(5))){
								    	String auditPriceType=row.getCell(5).toString();
								    	p.setAuditPriceType(auditPriceType);
								    	/*CostTaskType costTaskType=costTaskTypeService.getCostTaskTypeLeafByName(auditPriceType);
								    	if(null!=costTaskType && null!=costTaskType.getPid()){
								    		CostTaskType costTaskTypeParemt=costTaskTypeService.selectByPrimaryKey(costTaskType.getPid());
								    		if(null!=costTaskTypeParemt){
								    			p.setAuditPriceTypecn(costTaskTypeParemt.getName());
								    		}
								    	}*/
								    	p.setAuditPriceTypecn("估概预结");
								    }
								    
									if(null!=row.getCell(6) && !"".equals(row.getCell(6))){
										Double taskAmount=ExcelUtil.getValue(row.getCell(6));
										BigDecimal bdTaskAmount=new BigDecimal(taskAmount); 
										bdTaskAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
										p.setGiveAmount(bdTaskAmount);
									}
								    if(row.getCell(7)!=null && !"".equals(row.getCell(7).toString())){
								    	Double myAuditAmount=ExcelUtil.getValue(row.getCell(7));
								    	p.setMyAuditAmount(new BigDecimal(myAuditAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
								    }
								    if(row.getCell(8)!=null && !"".equals(row.getCell(8).toString())){
								    	Double agencyAuditAmountunt=ExcelUtil.getValue(row.getCell(8));
								    	p.setAgencyAuditAmount(new BigDecimal(agencyAuditAmountunt).setScale(2, BigDecimal.ROUND_HALF_UP));
								    }
								    if(row.getCell(9)!=null && !"".equals(row.getCell(9).toString())){
								    	Double decideAmount=ExcelUtil.getValue(row.getCell(9));
								    	p.setDecideAmount(new BigDecimal(decideAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
								    }
								    p.setFinalizeNumber(null!=row.getCell(10)?row.getCell(10).toString():null);
								    p.setDeliveryFlag(null!=row.getCell(11)?row.getCell(11).toString():null);
								    p.setEntrustNumber(null!=row.getCell(12)?row.getCell(12).toString():null);
								    p.setAuditPriceUnit(null!=row.getCell(13)?row.getCell(13).toString():null);
								    
								    Cell cell14 =row.getCell(14);
								    if (cell14 != null && !"".equals(cell14.toString())) { 
								    	if (cell14.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell14)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell14.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setDecideTime(sdf.parse(value));
								    	} 
								    }
								    p.setStatus(null!=row.getCell(15)?row.getCell(15).toString():null);
								    p.setReviewReportn(null!=row.getCell(16)?row.getCell(16).toString():null);
								    
								    
								    //p.setUmber(null!=row.getCell(18)?row.getCell(18).toString():null);
								   
								    p.setPersonLiable(null!=row.getCell(17)?row.getCell(17).toString():null);
								    
								    Cell cell18 =row.getCell(18);
								    if (cell18 != null && !"".equals(cell18.toString())) { 
								    	if (cell18.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell18)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell18.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setReceiveTime(sdf.parse(value));
								    	} 
								    }
								    p.setFixedFinancial(null!=row.getCell(19)?row.getCell(19).toString():null);
								    Cell cell20 =row.getCell(20);
								    if (cell20 != null && !"".equals(cell20.toString())) { 
								    	if (cell20.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell20)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell20.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setSubmissionTime(sdf.parse(value));
								    	} 
								    }
								    p.setProgressDescription(null!=row.getCell(21)?row.getCell(21).toString():null);
								    p.setCheckExplain(null!=row.getCell(22)?row.getCell(22).toString():null);
								    p.setRetrial(null!=row.getCell(23)?row.getCell(23).toString():null);
								    Cell cell24 =row.getCell(24);
								    if (cell24 != null && !"".equals(cell24.toString())) { 
								    	if (cell24.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell24)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell24.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setAcceptanceTime(sdf.parse(value));
								    	} 
								    }
								    p.setFinancial(null!=row.getCell(25)?row.getCell(25).toString():null);
								    
								    
								    p.setCreater(currentUser.getName());
								    p.setCreateTime(new Date());
								    costTaskMapper.insertSelective(p);
								    CostDpctRelation r = new  CostDpctRelation();
								    r.setId(UUID.randomUUID().toString().replace("-", ""));
								    r.setTaskId(p.getId());
								    r.setContractId(p.getContractId());
								    r.setProjectId(p.getProjectId());
								    r.setDocumentId(p.getDocumentId());
								    r.setCreateTime(new Date());
								    costDpctRelationMapper.insertSelective(r);
								    
								 } catch (Exception e) {
									 resut.setCode(row.getCell(3).toString());
										listTask.add(resut);
								}
							}
						  }
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return listTask;
		}

	//单价导入
	@Override
	public List<CostTask> importTaskDj(HttpServletRequest request) {
		List<CostTask> listTask=new ArrayList<CostTask>();
		try{
			 SysUser currentUser=shiroUtil.getInstance().currentUser();
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					//源文件后缀
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					//新文件名称
					Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
					String path=(String) props.get("fileupload");
					path=path+"project/"+nowDate+"/";
					File filePath=new File(path+fileName);
					if (!filePath.exists()) {    
						filePath.mkdirs();    
					}
					file.transferTo(filePath); 
					String suffix=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
					Workbook wb = null;
					InputStream stream = new FileInputStream(filePath);
					if (suffix.equals("xls")) {
						POIFSFileSystem fs = new POIFSFileSystem(stream);
						wb = new HSSFWorkbook(fs);
					} else if (suffix.equals("xlsx")) {
						wb = new XSSFWorkbook(stream);
					}
					Sheet sheet = wb.getSheetAt(0);
					if(sheet!=null){
						int num = sheet.getLastRowNum();
						CostTask a =null;
						CostTask resut=null;
						for (int i = 1; i <= num; i++) {
							// 循环输出表格中的内容
							Row row = sheet.getRow(i);
							if(row!=null){
							 Cell cell0=row.getCell(0);
							 if(null!=cell0){
								resut=new CostTask();
								try {
									//去重复
									CostTask task = selectByTaskCode(row.getCell(1).toString());
									if(null!=task){
										 if(row.getCell(0).toString()!=null && !"".equals(row.getCell(0).toString())){
									    	CostProject proj = costProjectService.selectByCode(row.getCell(0).toString());
									    	if(proj!=null){
									    		task.setProjectId(proj.getId());
									    		task.setProjectName(proj.getName());
									    	}
									    }
									    if(null!=row.getCell(1).toString() && !"".equals(row.getCell(1).toString())){
									    	CostContract contract = costContractService.selectByContractCode(row.getCell(1).toString());
									    	if(null!=contract){
									    		task.setContractId(contract.getId());
									    		task.setContractName(contract.getName());
									    	}
									    }
									    if(row.getCell(2).toString()!=null && !"".equals(row.getCell(2).toString())){
									    	CostDocument d = costDocumentService.selectBySymbol(row.getCell(2).toString());
									    	if(d!=null){
									    		task.setDocumentId(d.getId());
									    	}
									    }
									    task.setName(null!=row.getCell(4)?row.getCell(4).toString():null);
									    if(null!=row.getCell(5) && !"".equals(row.getCell(5))){
									    	String auditPriceType=row.getCell(5).toString();
									    	task.setAuditPriceType(auditPriceType);
									    	/*CostTaskType costTaskType=costTaskTypeService.getCostTaskTypeLeafByName(auditPriceType);
									    	if(null!=costTaskType && null!=costTaskType.getPid()){
									    		CostTaskType costTaskTypeParemt=costTaskTypeService.selectByPrimaryKey(costTaskType.getPid());
									    		if(null!=costTaskTypeParemt){
									    			a.setAuditPriceTypecn(costTaskTypeParemt.getName());
									    		}
									    	}*/
									    	task.setAuditPriceTypecn("单价审核");
									    }
									    
									    Cell cell6=row.getCell(6);
									    if(null!=cell6 && !"".equals(cell6.toString().trim())){
									    	cell6.setCellType(HSSFCell.CELL_TYPE_STRING);  
									    	String content = cell6.getStringCellValue();
									    	task.setGiveNumber(Integer.parseInt(content));
									    }
									    Cell cell7=row.getCell(7);
									    if(null!=cell7 && !"".equals(cell7.toString().trim())){
									    	cell7.setCellType(HSSFCell.CELL_TYPE_STRING);  
									    	String content = cell7.getStringCellValue();
									    	task.setAuditNumber(Integer.parseInt(content));
									    }
									    task.setInlineNumber(null!=row.getCell(8)?row.getCell(8).toString():null);
									    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
									    Cell cell9 =row.getCell(9);
									    if (cell9 != null && !"".equals(cell9.toString())) { 
									    	if (cell9.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell9)) {
									    		Date date = HSSFDateUtil.getJavaDate(cell9.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setReceiveTime(sdf.parse(value));
									    	} 
									    }
									    Cell cell10 =row.getCell(10);
									    if (cell10 != null && !"".equals(cell10.toString())) { 
									    	if (cell10.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell10)) {
									    		Date date = HSSFDateUtil.getJavaDate(cell10.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setDecideTime(sdf.parse(value));
									    	}
									    }
									    task.setStatus(null!=row.getCell(11)?row.getCell(11).toString():null);
									    task.setPersonLiable(null!=row.getCell(12)?row.getCell(12).toString():null);
									    task.setReviewReportn(null!=row.getCell(13)?row.getCell(13).toString():null);
									    //task.setUmber(null!=row.getCell(14)?row.getCell(14).toString():null);
									    task.setProgressDescription(null!=row.getCell(14)?row.getCell(14).toString():null);
									    task.setCheckExplain(null!=row.getCell(15)?row.getCell(15).toString():null);
									    task.setApproval(null!=row.getCell(16)?row.getCell(16).toString():null);
									    Cell cell17 =row.getCell(17);
									    if (cell17 != null && !"".equals(cell17.toString())) { 
									    	if (cell17.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell17)) {
									    		Date date = HSSFDateUtil.getJavaDate(cell17.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setDeliveryTime(sdf.parse(value));
									    	}
									    }
									    task.setCreater(currentUser.getName());
									    task.setCreateTime(new Date());
									    costTaskMapper.updateByPrimaryKeySelective(task);
										continue;
									}
								    a = new CostTask();
								    String taskId = UUID.randomUUID().toString().replace("-", "");
								    a.setId(taskId);
								    if(row.getCell(0).toString()!=null && !"".equals(row.getCell(0).toString())){
								    	CostProject proj = costProjectService.selectByCode(row.getCell(0).toString());
								    	if(proj!=null){
								    		a.setProjectId(proj.getId());
								    		a.setProjectName(proj.getName());
								    	}
								    }
								    if(null!=row.getCell(1).toString() && !"".equals(row.getCell(1).toString())){
								    	CostContract contract = costContractService.selectByContractCode(row.getCell(1).toString());
								    	if(null!=contract){
								    		a.setContractId(contract.getId());
								    		a.setContractName(contract.getName());
								    	}
								    }
								    if(row.getCell(2).toString()!=null && !"".equals(row.getCell(2).toString())){
								    	CostDocument d = costDocumentService.selectBySymbol(row.getCell(2).toString());
								    	if(d!=null){
								    		a.setDocumentId(d.getId());
								    	}
								    }
								    a.setCode(null!=row.getCell(3)?row.getCell(3).toString():null);
								    a.setName(null!=row.getCell(4)?row.getCell(4).toString():null);
								    if(null!=row.getCell(5) && !"".equals(row.getCell(5))){
								    	String auditPriceType=row.getCell(5).toString();
								    	a.setAuditPriceType(auditPriceType);
								    	/*CostTaskType costTaskType=costTaskTypeService.getCostTaskTypeLeafByName(auditPriceType);
								    	if(null!=costTaskType && null!=costTaskType.getPid()){
								    		CostTaskType costTaskTypeParemt=costTaskTypeService.selectByPrimaryKey(costTaskType.getPid());
								    		if(null!=costTaskTypeParemt){
								    			a.setAuditPriceTypecn(costTaskTypeParemt.getName());
								    		}
								    	}*/
								    	a.setAuditPriceTypecn("单价审核");
								    }
								    
								    Cell cell6=row.getCell(6);
								    if(null!=cell6 && !"".equals(cell6.toString().trim())){
								    	cell6.setCellType(HSSFCell.CELL_TYPE_STRING);  
								    	String content = cell6.getStringCellValue();
								    	a.setGiveNumber(Integer.parseInt(content));
								    }
								    Cell cell7=row.getCell(7);
								    if(null!=cell7 && !"".equals(cell7.toString().trim())){
								    	cell7.setCellType(HSSFCell.CELL_TYPE_STRING);  
								    	String content = cell7.getStringCellValue();
								    	a.setAuditNumber(Integer.parseInt(content));
								    }
								    a.setInlineNumber(null!=row.getCell(8)?row.getCell(8).toString():null);
								    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    Cell cell9 =row.getCell(9);
								    if (cell9 != null && !"".equals(cell9.toString())) { 
								    	if (cell9.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell9)) {
								    		Date date = HSSFDateUtil.getJavaDate(cell9.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		a.setReceiveTime(sdf.parse(value));
								    	} 
								    }
								    Cell cell10 =row.getCell(10);
								    if (cell10 != null && !"".equals(cell10.toString())) { 
								    	if (cell10.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell10)) {
								    		Date date = HSSFDateUtil.getJavaDate(cell10.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		a.setDecideTime(sdf.parse(value));
								    	} 
								    }
								    a.setStatus(null!=row.getCell(11)?row.getCell(11).toString():null);
								    a.setPersonLiable(null!=row.getCell(12)?row.getCell(12).toString():null);
								    a.setReviewReportn(null!=row.getCell(13)?row.getCell(13).toString():null);
								    //a.setUmber(null!=row.getCell(14)?row.getCell(14).toString():null);
								    a.setProgressDescription(null!=row.getCell(14)?row.getCell(14).toString():null);
								    a.setCheckExplain(null!=row.getCell(15)?row.getCell(15).toString():null);
								    a.setApproval(null!=row.getCell(16)?row.getCell(16).toString():null);
								    Cell cell17 =row.getCell(17);
								    if (cell17 != null && !"".equals(cell17.toString())) { 
								    	if (cell17.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell17)) {
								    		Date date = HSSFDateUtil.getJavaDate(cell17.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		a.setDeliveryTime(sdf.parse(value));
								    	}
								    }
								    a.setCreater(currentUser.getName());
								    a.setCreateTime(new Date());
								    costTaskMapper.insertSelective(a);
								    CostDpctRelation r = new  CostDpctRelation();
								    r.setId(UUID.randomUUID().toString().replace("-", ""));
								    r.setTaskId(a.getId());
								    r.setContractId(a.getContractId());
								    r.setProjectId(a.getProjectId());
								    r.setDocumentId(a.getDocumentId());
								    r.setCreateTime(new Date());
								    costDpctRelationMapper.insertSelective(r);
								    
								} catch (Exception e) {
									 resut.setCode(row.getCell(3).toString());
									 listTask.add(resut);
								}  
							 }  
								    
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return listTask;
		}

	//合同导入
	@Override
	public List<CostTask> importTaskHt(HttpServletRequest request) {
		List<CostTask> listTask=new ArrayList<CostTask>();
		try{
			 SysUser currentUser=shiroUtil.getInstance().currentUser();
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					//源文件后缀
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					//新文件名称
					Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
					String path=(String) props.get("fileupload");
					path=path+"project/"+nowDate+"/";
					File filePath=new File(path+fileName);
					if (!filePath.exists()) {    
						filePath.mkdirs();    
					}
					file.transferTo(filePath); 
					String suffix=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
					Workbook wb = null;
					InputStream stream = new FileInputStream(filePath);
					if (suffix.equals("xls")) {
						POIFSFileSystem fs = new POIFSFileSystem(stream);
						wb = new HSSFWorkbook(fs);
					} else if (suffix.equals("xlsx")) {
						wb = new XSSFWorkbook(stream);
					}
					Sheet sheet = wb.getSheetAt(3);
					if(sheet!=null){
						int num = sheet.getLastRowNum();
						CostTask h =null;
						CostTask resut=null;
						for (int i = 1; i <= num; i++) {
							// 循环输出表格中的内容
							Row row = sheet.getRow(i);
							if(row!=null){
								 Cell cell0=row.getCell(0);
								 if(null!=cell0){
									 resut=new CostTask();
									 try {
									 //去重复
									CostTask task = selectByTaskCode(row.getCell(3).toString());
									if(null!=task){
										if(row.getCell(0).toString()!=null && !"".equals(row.getCell(0).toString())){
									    	CostProject proj = costProjectService.selectByCode(row.getCell(0).toString());
									    	if(proj!=null){
									    		task.setProjectId(proj.getId());
									    		task.setProjectName(proj.getName());
									    	}
									    }
									    if(null!=row.getCell(1).toString() && !"".equals(row.getCell(1).toString())){
									    	CostContract contract = costContractService.selectByContractCode(row.getCell(1).toString());
									    	if(null!=contract){
									    		task.setContractId(contract.getId());
									    		task.setContractName(contract.getName());
									    	}
									    }
									    if(row.getCell(2).toString()!=null && !"".equals(row.getCell(2).toString())){
									    	CostDocument d = costDocumentService.selectBySymbol(row.getCell(2).toString());
									    	if(d!=null){
									    		task.setDocumentId(d.getId());
									    	}
									    }
									    task.setName(row.getCell(4).toString());
									    String auditPriceType=row.getCell(5).toString();
									    if(null!=auditPriceType && !"".equals(auditPriceType)){
									    	if("设计变更费用".equals(auditPriceType) || "现场签证".equals(auditPriceType) || "其他".equals(auditPriceType)){
									    		task.setAuditPriceTypecn("合同变更");
									    	}else{
									    		task.setAuditPriceTypecn("设计图纸变更");
									    	}
									    	task.setAuditPriceType(auditPriceType);
									    	/*CostTaskType costTaskType=costTaskTypeService.getCostTaskTypeLeafByName(auditPriceType);
									    	if(null!=costTaskType && null!=costTaskType.getPid()){
									    		CostTaskType costTaskTypeParemt=costTaskTypeService.selectByPrimaryKey(costTaskType.getPid());
									    		if(null!=costTaskTypeParemt){
									    			h.setAuditPriceTypecn(costTaskTypeParemt.getName());
									    		}
									    	}*/
									    }
									    task.setInlineNumber(null!=row.getCell(6)?row.getCell(6).toString():null);
										if(null!=row.getCell(7) && !"".equals(row.getCell(7))){
											Double taskAmount=ExcelUtil.getValue(row.getCell(7));
											BigDecimal bdTaskAmount=new BigDecimal(taskAmount); 
											bdTaskAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
											task.setGiveAmount(bdTaskAmount);
										}
									    if(row.getCell(8)!=null && !"".equals(row.getCell(8))){
									    	Double agencyAuditAmount=ExcelUtil.getValue(row.getCell(8));
									    	task.setAgencyAuditAmount(new BigDecimal(agencyAuditAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
									    }
									    if(row.getCell(9)!=null && !"".equals(row.getCell(9))){
									    	Double decideAmount=ExcelUtil.getValue(row.getCell(9));
									    	task.setDecideAmount(new BigDecimal(decideAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
									    }
									    task.setStatus(null!=row.getCell(10)?row.getCell(10).toString():null);
									    
									    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
									    Cell cell11 =row.getCell(11);
									    if (cell11 != null && !"".equals(cell11.toString())) { 
									    	if (cell11.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell11)) {
									    		Date date = HSSFDateUtil.getJavaDate(cell11.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setDecideTime(sdf.parse(value));
									    	} 
									    }
									   
									    task.setPersonLiable(null!=row.getCell(12)?row.getCell(12).toString():null);
									    task.setFinalizeNumber(null!=row.getCell(13)?row.getCell(13).toString():null);
									    Cell cell14 =row.getCell(14);
									    if (cell14 != null && !"".equals(cell14.toString())) { 
									    	if (cell14.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell14)) {
									    		Date date = HSSFDateUtil.getJavaDate(cell14.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setReceiveTime(sdf.parse(value));
									    	} 
									    }
									    task.setReviewReportn(null!=row.getCell(15)?row.getCell(15).toString():null);
									    //task.setUmber(null!=row.getCell(17)?row.getCell(17).toString():null);
									    task.setProgressDescription(null!=row.getCell(16)?row.getCell(16).toString():null);
									    task.setCheckExplain(null!=row.getCell(17)?row.getCell(17).toString():null);
									    Cell cell18 =row.getCell(18);
									    if (cell18 != null && !"".equals(cell18.toString())) { 
									    	if (cell18.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell18)) {
									    		Date date = HSSFDateUtil.getJavaDate(cell18.getNumericCellValue());
									    		String value = sdf.format(date); 
									    		task.setDeliveryTime(sdf.parse(value));
									    	}
									    }
									    task.setWhereabouts(null!=row.getCell(19)?row.getCell(19).toString():null);
									    task.setRetrial(null!=row.getCell(20)?row.getCell(20).toString():null);
									    task.setCreater(currentUser.getName());
									    task.setCreateTime(new Date());
									    costTaskMapper.updateByPrimaryKeySelective(task);
										continue;
									}
									h = new CostTask();
								   
								    String taskId = UUID.randomUUID().toString().replace("-", "");
								    h.setId(taskId);
								    if(row.getCell(0).toString()!=null && !"".equals(row.getCell(0).toString())){
								    	CostProject proj = costProjectService.selectByCode(row.getCell(0).toString());
								    	if(proj!=null){
								    		h.setProjectId(proj.getId());
								    		h.setProjectName(proj.getName());
								    	}
								    }
								    if(null!=row.getCell(1).toString() && !"".equals(row.getCell(1).toString())){
								    	CostContract contract = costContractService.selectByContractCode(row.getCell(1).toString());
								    	if(null!=contract){
								    		h.setContractId(contract.getId());
								    		h.setContractName(contract.getName());
								    	}
								    }
								    if(row.getCell(2).toString()!=null && !"".equals(row.getCell(2).toString())){
								    	CostDocument d = costDocumentService.selectBySymbol(row.getCell(2).toString());
								    	if(d!=null){
								    		 h.setDocumentId(d.getId());
								    	}
								    }
								    h.setCode(null!=row.getCell(3)?row.getCell(3).toString():null);
								    h.setName(row.getCell(4).toString());
								    String auditPriceType=row.getCell(5).toString();
								    if(null!=auditPriceType && !"".equals(auditPriceType)){
								    	if("设计变更费用".equals(auditPriceType) || "现场签证".equals(auditPriceType) || "其他".equals(auditPriceType)){
								    		h.setAuditPriceTypecn("合同变更");
								    	}else{
								    		h.setAuditPriceTypecn("设计图纸变更");
								    	}
								    	h.setAuditPriceType(auditPriceType);
								    	/*CostTaskType costTaskType=costTaskTypeService.getCostTaskTypeLeafByName(auditPriceType);
								    	if(null!=costTaskType && null!=costTaskType.getPid()){
								    		CostTaskType costTaskTypeParemt=costTaskTypeService.selectByPrimaryKey(costTaskType.getPid());
								    		if(null!=costTaskTypeParemt){
								    			h.setAuditPriceTypecn(costTaskTypeParemt.getName());
								    		}
								    	}*/
								    }
								    h.setInlineNumber(null!=row.getCell(6)?row.getCell(6).toString():null);
									if(null!=row.getCell(7) && !"".equals(row.getCell(7))){
										Double taskAmount=ExcelUtil.getValue(row.getCell(7));
										BigDecimal bdTaskAmount=new BigDecimal(taskAmount); 
										bdTaskAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
										h.setGiveAmount(bdTaskAmount);
									}
								    if(row.getCell(8)!=null && !"".equals(row.getCell(8))){
								    	Double agencyAuditAmount=ExcelUtil.getValue(row.getCell(8));
								    	h.setAgencyAuditAmount(new BigDecimal(agencyAuditAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
								    }
								    if(row.getCell(9)!=null && !"".equals(row.getCell(9))){
								    	Double decideAmount=ExcelUtil.getValue(row.getCell(9));
								    	h.setDecideAmount(new BigDecimal(decideAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
								    }
								    h.setStatus(null!=row.getCell(10)?row.getCell(10).toString():null);
								    
								    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    Cell cell11 =row.getCell(11);
								    if (cell11 != null && !"".equals(cell11.toString())) { 
								    	if (cell11.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell11)) {
								    		Date date = HSSFDateUtil.getJavaDate(cell11.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		h.setDecideTime(sdf.parse(value));
								    	} 
								    }
								   
								    h.setPersonLiable(null!=row.getCell(12)?row.getCell(12).toString():null);
								    h.setFinalizeNumber(null!=row.getCell(13)?row.getCell(13).toString():null);
								    Cell cell14 =row.getCell(14);
								    if (cell14 != null && !"".equals(cell14.toString())) { 
								    	if (cell14.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell14)) {
								    		Date date = HSSFDateUtil.getJavaDate(cell14.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		h.setReceiveTime(sdf.parse(value));
								    	} 
								    }
								    h.setReviewReportn(null!=row.getCell(15)?row.getCell(15).toString():null);
								    //task.setUmber(null!=row.getCell(17)?row.getCell(17).toString():null);
								    h.setProgressDescription(null!=row.getCell(16)?row.getCell(16).toString():null);
								    h.setCheckExplain(null!=row.getCell(17)?row.getCell(17).toString():null);
								    Cell cell18 =row.getCell(18);
								    if (cell18 != null && !"".equals(cell18.toString())) { 
								    	if (cell18.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell18)) {
								    		Date date = HSSFDateUtil.getJavaDate(cell18.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		h.setDeliveryTime(sdf.parse(value));
								    	}
								    }
								    h.setWhereabouts(null!=row.getCell(19)?row.getCell(19).toString():null);
								    h.setRetrial(null!=row.getCell(20)?row.getCell(20).toString():null);
								    h.setCreater(currentUser.getName());
								    h.setCreateTime(new Date());
								    costTaskMapper.insertSelective(h);
								    CostDpctRelation r = new  CostDpctRelation();
								    r.setId(UUID.randomUUID().toString().replace("-", ""));
								    r.setTaskId(h.getId());
								    r.setContractId(h.getContractId());
								    r.setProjectId(h.getProjectId());
								    r.setDocumentId(h.getDocumentId());
								    r.setCreateTime(new Date());
								    costDpctRelationMapper.insertSelective(r);
								 }catch(Exception e){
									 resut.setCode(row.getCell(3).toString());
									 listTask.add(resut);
								 }
							}
						}
					  }
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return listTask;
		}

	
	@Override
	public CostTask selectByTaskCode(String code) {
		CostTaskExample example=new CostTaskExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<CostTask> cList=costTaskMapper.selectByExample(example);
		if(null!=cList && cList.size()>0){
			return cList.get(0);
		}else{
			return null;
		}
	}
}
