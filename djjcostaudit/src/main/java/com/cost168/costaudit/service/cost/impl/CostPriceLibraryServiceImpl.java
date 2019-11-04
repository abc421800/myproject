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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cost168.costaudit.mapper.cost.CostPriceLibraryMapper;
import com.cost168.costaudit.pojo.cost.CostPriceLibrary;
import com.cost168.costaudit.pojo.cost.CostPriceLibraryExample;
import com.cost168.costaudit.pojo.cost.CostPriceLibraryExample.Criteria;
import com.cost168.costaudit.pojo.cost.CostTask;
import com.cost168.costaudit.service.cost.CostPriceLibraryService;
import com.cost168.costaudit.service.cost.CostTaskService;
import com.cost168.costaudit.utils.ExcelUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostPriceLibraryServiceImpl implements CostPriceLibraryService {
	
	@Autowired
	private CostPriceLibraryMapper costPriceLibraryMapper;
	@Autowired
	private CostTaskService costTaskService;
	
	
	@Override
	public List<CostPriceLibrary> selectByExample(CostPriceLibraryExample example) {
		return costPriceLibraryMapper.selectByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costPriceLibraryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostPriceLibrary record) {
		return costPriceLibraryMapper.insertSelective(record);
	}

	@Override
	public CostPriceLibrary selectByPrimaryKey(String id) {
		return costPriceLibraryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CostPriceLibrary record) {
		return costPriceLibraryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CostPriceLibrary> selectListByMap(Map<String, Object> map) {
		return costPriceLibraryMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costPriceLibraryMapper.selectCountByMap(map);
	}

	@Override
	public CostPriceLibrary selectByName(String name) {
		CostPriceLibraryExample example=new CostPriceLibraryExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<CostPriceLibrary> plList=costPriceLibraryMapper.selectByExample(example);
		if(null!=plList && plList.size()>0){
			return plList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public CostPriceLibrary selectByCode(String code) {
		CostPriceLibraryExample example=new CostPriceLibraryExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<CostPriceLibrary> plList=costPriceLibraryMapper.selectByExample(example);
		if(null!=plList && plList.size()>0){
			return plList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unused")
	public List<CostPriceLibrary> importZh(HttpServletRequest request,String flagType) {
		List<CostPriceLibrary> list=new ArrayList<CostPriceLibrary>();
		try {
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 List<MultipartFile> files=multipartRequest.getFiles("file");
				if(files.size()>0){
					MultipartFile file=files.get(0);
					String fileName = file.getOriginalFilename();
					SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
					String nowDate=fmt.format(new Date());
					Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
					String path=(String) props.get("fileupload");
					path=path+"contract/"+nowDate+"/";
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
					if(null!=sheet){
						int num = sheet.getLastRowNum();
						CostPriceLibrary costPriceLibrary=null;
						CostPriceLibrary resut=null;
						for (int i = 1; i <= num; i++) {
							Row row = sheet.getRow(i);
							if(null!=row){
								 Cell cell0=row.getCell(0);
								 if(null!=cell0){
									CostTask task= costTaskService.selectByTaskCode(cell0.toString());
									resut=new CostPriceLibrary();
									
										try {
											costPriceLibrary =new CostPriceLibrary();
											costPriceLibrary.setTaskPersonLiable(task.getPersonLiable());
											String id=UUID.randomUUID().toString().replace("-", "");
											costPriceLibrary.setId(id);
											costPriceLibrary.setTaskId(task.getId());
											costPriceLibrary.setProjectId(task.getProjectId());
											costPriceLibrary.setContractId(task.getContractId());
											costPriceLibrary.setTaskCode(task.getCode());
											if("zh".equals(flagType)){
												if(null!=task){
													costPriceLibrary.setTypeLibrary(task.getAuditPriceType());
												}else{
													costPriceLibrary.setTypeLibrary("综合单价");
												}
												costPriceLibrary.setCode(null!=row.getCell(1)?row.getCell(1).toString():null);
												costPriceLibrary.setName(null!=row.getCell(2)?row.getCell(2).toString():null);
												costPriceLibrary.setFeature(null!=row.getCell(3)?row.getCell(4).toString():null);
												costPriceLibrary.setEngineeringNumber(null!=row.getCell(4)?row.getCell(4).toString().replace(".00", ""):null);
												costPriceLibrary.setBasis(null!=row.getCell(5)?row.getCell(5).toString():null);
												costPriceLibrary.setUnit(null!=row.getCell(6)?row.getCell(6).toString():null);
												Cell cell7=row.getCell(7);
												if(null!=cell7 && !"".equals(cell7)){
													Double contractingPrice = ExcelUtil.getValue(cell7);
													BigDecimal bdContractingPrice=new BigDecimal(contractingPrice); 
													bdContractingPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
													costPriceLibrary.setContractingPrice(bdContractingPrice);
												}
												Cell cell8=row.getCell(8);
												if(null!=cell8 && !"".equals(cell8)){
													 Double supervisorPrice = ExcelUtil.getValue(cell8);
													BigDecimal bdSupervisorPrice=new BigDecimal(supervisorPrice); 
													bdSupervisorPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
													costPriceLibrary.setSupervisorPrice(bdSupervisorPrice);
												}
												Cell cell9=row.getCell(9);
												if(null!=cell9 && !"".equals(cell9)){
													 Double contractAmount = ExcelUtil.getValue(cell9);
													BigDecimal bdContractingPrice=new BigDecimal(contractAmount); 
													bdContractingPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
													costPriceLibrary.setContractingPrice(bdContractingPrice);
												}
												costPriceLibrary.setDescription(row.getCell(10).toString());
											}else{
												if(null!=task){
													costPriceLibrary.setTypeLibrary(task.getAuditPriceType());
												}else{
													costPriceLibrary.setTypeLibrary("主材定价");
												}
												costPriceLibrary.setName(null!=row.getCell(1)?row.getCell(1).toString():null);
												costPriceLibrary.setFeature(null!=row.getCell(2)?row.getCell(2).toString():null);
												costPriceLibrary.setEngineeringNumber(null!=row.getCell(3)?row.getCell(3).toString().replace(".00", ""):null);
												costPriceLibrary.setUsePosition(null!=row.getCell(4)?row.getCell(4).toString():null);
												costPriceLibrary.setBasis(null!=row.getCell(5)?row.getCell(5).toString():null);
												costPriceLibrary.setBiddingBrand(null!=row.getCell(6)?row.getCell(6).toString():null);
												costPriceLibrary.setUseBrand(null!=row.getCell(7)?row.getCell(7).toString():null);
												costPriceLibrary.setUnit(null!=row.getCell(8)?row.getCell(8).toString():null);
												Cell cell9=row.getCell(9);
												if(null!=cell9 && !"".equals(cell9)){
													 Double contractAmount = ExcelUtil.getValue(cell9);
													BigDecimal bdContractingPrice=new BigDecimal(contractAmount); 
													bdContractingPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
													costPriceLibrary.setContractingPrice(bdContractingPrice);
												}
												
												Cell cell10=row.getCell(10);
												if(null!=cell10 && !"".equals(cell10)){
													 Double supervisorPrice = ExcelUtil.getValue(cell10);
													BigDecimal bdSupervisorPrice=new BigDecimal(supervisorPrice); 
													bdSupervisorPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
													costPriceLibrary.setSupervisorPrice(bdSupervisorPrice);
												}
												Cell cell11=row.getCell(11);
												if(null!=cell11 && !"".equals(cell11)){
													Double settlementPrice = ExcelUtil.getValue(cell11);
													BigDecimal bdSettlementPrice=new BigDecimal(settlementPrice); 
													bdSettlementPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
													costPriceLibrary.setSettlementPrice(bdSettlementPrice);
												}
												costPriceLibrary.setDescription(null!=row.getCell(12)?row.getCell(12).toString():null);
											}
											costPriceLibrary.setCreateTime(new Date());
											insertSelective(costPriceLibrary);
										} catch (Exception e) {
											resut.setTaskCode(cell0.toString());
											resut.setName(row.getCell(1).toString());
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



}
