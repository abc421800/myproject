package com.cost168.costaudit.service.cost.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.cost168.costaudit.mapper.cost.CostDocumentMapper;
import com.cost168.costaudit.pojo.cost.CostDocument;
import com.cost168.costaudit.pojo.cost.CostDpctRelation;
import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.pojo.cost.CostDocumentExample.Criteria;
import com.cost168.costaudit.pojo.cost.CostDocumentExample;
import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.service.cost.CostDocumentService;
import com.cost168.costaudit.service.cost.CostDpctRelationService;
import com.cost168.costaudit.service.cost.CostProjectService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostDocumentServiceImpl implements CostDocumentService{
	
	
	@Autowired
	private CostDocumentMapper costDocumentMapper;
	
	@Autowired
	private CostProjectService costProjectService;

	@Autowired
	private CostDpctRelationService costDpctRelationService;
	
	@Override
	public CostDocument selectByPrimaryKey(String id) {
		return costDocumentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostDocument record) {
		return costDocumentMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostDocument record) {
		return costDocumentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CostDocument> selectListByMap(Map<String, Object> map) {
		return costDocumentMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costDocumentMapper.selectCountByMap(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costDocumentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CostDocument> selectRelationDocListByMap(Map<String, Object> map) {
		return costDocumentMapper.selectRelationDocListByMap(map);
	}

	@Override
	public int selectRelationDocCountByMap(Map<String, Object> map) {
		return costDocumentMapper.selectRelationDocCountByMap(map);
	}

	//锟斤拷锟斤拷正式的
	@Override
	@SuppressWarnings("null")
	public List<CostDocument> importDocument(HttpServletRequest request) {
		List<CostDocument> listDocument=new ArrayList<CostDocument>();
		try{
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					//源锟侥硷拷锟斤拷缀
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					//锟斤拷锟侥硷拷锟斤拷锟�
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
					Sheet sheet = wb.getSheetAt(2);
					if(sheet!=null){
						int num = sheet.getLastRowNum();
						CostDocument p =null;
						CostDocument resut=null;
						for (int i = 1; i <= num; i++) {
							// 循环输出表格中的内容
							Row row = sheet.getRow(i);
							if(row!=null){
								resut=new CostDocument();
								try {
									//去重复
									if(row.getCell(1)!=null && !"".equals(row.getCell(1).toString())){
										CostDocument doc = selectBySymbol(row.getCell(1).toString());
										if(null!=doc){
											doc.setName(null!=row.getCell(2)?row.getCell(2).toString():null);
											doc.setComeGoUnit(null!=row.getCell(3)?row.getCell(3).toString():null);
											doc.setComeGoFlag(null!=row.getCell(4)?row.getCell(4).toString():null);
											    
										    Cell cell5 =row.getCell(5);// 获取第一行第一列的单元格
										    if (cell5 != null) { 
										    	if (cell5.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell5)) {
										    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
										    		Date date = HSSFDateUtil.getJavaDate(cell5.getNumericCellValue());
										    		String value = sdf.format(date); 
										    		System.out.println(value);
										    		doc.setDocumentTime(sdf.parse(value));
										    	} 
										    }
										    //doc.setPersonLiable(null!=row.getCell(6)?row.getCell(6).toString():null);
										    doc.setRegistrant(null!=row.getCell(6)?row.getCell(6).toString():null);
										    doc.setAuditPriceFlag(null!=row.getCell(7)?row.getCell(7).toString():null);
										    Cell cell8 =row.getCell(8);
										    if (cell8 != null) { 
										    	if (cell8.getCellTypeEnum() != CellType.STRING &&HSSFDateUtil.isCellDateFormatted(cell8)) {
										    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
										    		Date date = HSSFDateUtil.getJavaDate(cell8.getNumericCellValue());
										    		String value = sdf.format(date); 
										    		doc.setRegistrantTime(sdf.parse(value));
										    	} 
										    }
										    doc.setNumber(null!=row.getCell(9)?row.getCell(9).toString():null);
										    doc.setDescription(null!=row.getCell(10)?row.getCell(10).toString():null);
										    doc.setOpinion(null!=row.getCell(11)?row.getCell(11).toString():null);
										    costDocumentMapper.updateByPrimaryKeySelective(doc);
										    //更新关联表
										    Cell cell0 =row.getCell(0);
										    if(null!=cell0 && !"".equals(cell0.toString())){
										    	CostProject proj= costProjectService.selectByCode(cell0.toString());
										    	if(null!=proj){
										    		List<CostDpctRelation> dpctList=costDpctRelationService.selectByProjectIdAndDocId(proj.getId(),doc.getId());
										    		if(null!=dpctList&&dpctList.size()>0){
										    			CostDpctRelation costDpctRelation= dpctList.get(0);
										    			costDpctRelation.setProjectId(proj.getId());
										    			costDpctRelationService.updateByPrimaryKeySelective(costDpctRelation);
										    		}else{
											    		CostDpctRelation costDpctRelation=new CostDpctRelation();
														String id=UUID.randomUUID().toString().replace("-", "");
														costDpctRelation.setId(id);
														costDpctRelation.setProjectId(proj.getId());
														costDpctRelation.setDocumentId(doc.getId());
														costDpctRelationService.insertSelective(costDpctRelation);
											    	}
										    	}
										    	
										    }
											continue;
										}
									}
									p = new CostDocument();
								    String projId = UUID.randomUUID().toString().replace("-", "");
								    p.setId(projId);
								    p.setSymbol(null!=row.getCell(1)?row.getCell(1).toString():null);
								    
								    
								    p.setName(null!=row.getCell(2)?row.getCell(2).toString():null);
								    p.setComeGoUnit(null!=row.getCell(3)?row.getCell(3).toString():null);
								    p.setComeGoFlag(null!=row.getCell(4)?row.getCell(4).toString():null);
								    
								    Cell cell5 =row.getCell(5);// 获取第一行第一列的单元格
								    if (cell5 != null && !"".equals(cell5.toString())) { 
								    	if (cell5.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell5)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell5.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setDocumentTime(sdf.parse(value));
								    		} 
								    }
								    //p.setPersonLiable(null!=row.getCell(6)?row.getCell(6).toString():null);
								    p.setRegistrant(null!=row.getCell(6)?row.getCell(6).toString():null);
								    p.setAuditPriceFlag(null!=row.getCell(7)?row.getCell(7).toString():null);
								    Cell cell8 =row.getCell(8);
								    if (cell8 != null) { 
								    	if (cell8.getCellTypeEnum() != CellType.STRING &&HSSFDateUtil.isCellDateFormatted(cell8)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell8.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setRegistrantTime(sdf.parse(value));
								    	} 
								    }
								    p.setNumber(null!=row.getCell(9)?row.getCell(9).toString():null);
								    p.setDescription(null!=row.getCell(10)?row.getCell(10).toString():null);
								    p.setOpinion(null!=row.getCell(11)?row.getCell(11).toString():null);
								    costDocumentMapper.insertSelective(p);
								    //更新关联表
								    Cell cell0 =row.getCell(0);
								    if(null!=cell0 && !"".equals(cell0.toString())){
								    	CostProject proj= costProjectService.selectByCode(cell0.toString());
								    	if(null!=proj){
								    		List<CostDpctRelation> dpctList=costDpctRelationService.selectByProjectIdAndDocId(proj.getId(),projId);
								    		if(null!=dpctList&&dpctList.size()>0){
								    			CostDpctRelation costDpctRelation= dpctList.get(0);
								    			costDpctRelation.setProjectId(proj.getId());
								    			costDpctRelationService.updateByPrimaryKeySelective(costDpctRelation);
								    		}else{
									    		CostDpctRelation costDpctRelation=new CostDpctRelation();
												String id=UUID.randomUUID().toString().replace("-", "");
												costDpctRelation.setId(id);
												costDpctRelation.setProjectId(proj.getId());
												costDpctRelation.setDocumentId(projId);
												costDpctRelationService.insertSelective(costDpctRelation);
									    	}
								    	}
								    	
								    }
								} catch (Exception e) {
									 resut.setSymbol(row.getCell(1).toString());
									 listDocument.add(resut);
								}  
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return listDocument;
		}
	
	//临时导入
	@Override
	@SuppressWarnings("null")
	public List<CostDocument> importDocument2(HttpServletRequest request) {
		List<CostDocument> listDocument=new ArrayList<CostDocument>();
		try{
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					//源锟侥硷拷锟斤拷缀
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					//锟斤拷锟侥硷拷锟斤拷锟�
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
					Sheet sheet = wb.getSheetAt(2);
					if(sheet!=null){
						int num = sheet.getLastRowNum();
						CostDocument p =null;
						CostDocument resut=null;
						for (int i = 1; i <= num; i++) {
							// 循环输出表格中的内容
							Row row = sheet.getRow(i);
							if(row!=null){
								resut=new CostDocument();
								Cell cell0=row.getCell(0);
								if(null!=cell0){
								try {
									//去重复
									if(row.getCell(1)!=null && !"".equals(row.getCell(1).toString())){
										CostDocument doc =costDocumentMapper.selectByPrimaryKey(row.getCell(0).toString());
										if(null!=doc){
											doc.setName(null!=row.getCell(2)?row.getCell(2).toString():null);
											doc.setComeGoUnit(null!=row.getCell(3)?row.getCell(3).toString():null);
											doc.setComeGoFlag(null!=row.getCell(4)?row.getCell(4).toString():null);
											    
										    Cell cell5 =row.getCell(5);// 获取第一行第一列的单元格
										    if (cell5 != null) { 
										    	if (cell5.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell5)) {
										    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
										    		Date date = HSSFDateUtil.getJavaDate(cell5.getNumericCellValue());
										    		String value = sdf.format(date); 
										    		System.out.println(value);
										    		doc.setDocumentTime(sdf.parse(value));
										    	} 
										    }
										    //doc.setPersonLiable(null!=row.getCell(6)?row.getCell(6).toString():null);
										    doc.setRegistrant(null!=row.getCell(6)?row.getCell(6).toString():null);
										    doc.setAuditPriceFlag(null!=row.getCell(7)?row.getCell(7).toString():null);
										    Cell cell8 =row.getCell(8);
										    if (cell8 != null) { 
										    	if (cell8.getCellTypeEnum() != CellType.STRING &&HSSFDateUtil.isCellDateFormatted(cell8)) {
										    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
										    		Date date = HSSFDateUtil.getJavaDate(cell8.getNumericCellValue());
										    		String value = sdf.format(date); 
										    		doc.setRegistrantTime(sdf.parse(value));
										    	} 
										    }
										    doc.setNumber(null!=row.getCell(9)?row.getCell(9).toString():null);
										    doc.setDescription(null!=row.getCell(10)?row.getCell(10).toString():null);
										    doc.setOpinion(null!=row.getCell(11)?row.getCell(11).toString():null);
										    costDocumentMapper.updateByPrimaryKeySelective(doc);
											continue;
										}
									}
									p = new CostDocument();
								    String projId = UUID.randomUUID().toString().replace("-", "");
								    p.setId(projId);
								    p.setSymbol(null!=row.getCell(1)?row.getCell(1).toString():null);
								    
								    
								    p.setName(null!=row.getCell(2)?row.getCell(2).toString():null);
								    p.setComeGoUnit(null!=row.getCell(3)?row.getCell(3).toString():null);
								    p.setComeGoFlag(null!=row.getCell(4)?row.getCell(4).toString():null);
								    
								    Cell cell5 =row.getCell(5);// 获取第一行第一列的单元格
								    if (cell5 != null && !"".equals(cell5.toString())) { 
								    	if (cell5.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell5)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell5.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setDocumentTime(sdf.parse(value));
								    		} 
								    }
								    //p.setPersonLiable(null!=row.getCell(6)?row.getCell(6).toString():null);
								    p.setRegistrant(null!=row.getCell(6)?row.getCell(6).toString():null);
								    p.setAuditPriceFlag(null!=row.getCell(7)?row.getCell(7).toString():null);
								    Cell cell8 =row.getCell(8);
								    if (cell8 != null) { 
								    	if (cell8.getCellTypeEnum() != CellType.STRING &&HSSFDateUtil.isCellDateFormatted(cell8)) {
								    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								    		Date date = HSSFDateUtil.getJavaDate(cell8.getNumericCellValue());
								    		String value = sdf.format(date); 
								    		p.setRegistrantTime(sdf.parse(value));
								    	} 
								    }
								    p.setNumber(null!=row.getCell(9)?row.getCell(9).toString():null);
								    p.setDescription(null!=row.getCell(10)?row.getCell(10).toString():null);
								    p.setOpinion(null!=row.getCell(11)?row.getCell(11).toString():null);
								    costDocumentMapper.insertSelective(p);
								  
								} catch (Exception e) {
									 resut.setSymbol(row.getCell(1).toString());
									 listDocument.add(resut);
								} 
							  }
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return listDocument;
		}
	
	@Override
	public CostDocument selectBySymbol(String symbol) {
		CostDocumentExample example=new CostDocumentExample();
		Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		List<CostDocument> cList=costDocumentMapper.selectByExample(example);
		if(null!=cList && cList.size()>0){
			return cList.get(0);
		}else{
			return null;
		}
	}
	

}
