package com.cost168.costaudit.service.cost.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cost168.costaudit.mapper.cost.CostEnterpriseMapper;
import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.cost.CostEnterpriseExample;
import com.cost168.costaudit.pojo.cost.CostEnterpriseExample.Criteria;
import com.cost168.costaudit.service.cost.CostEnterpriseService;
import com.cost168.costaudit.utils.ExcelUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostEnterpriseServiceImpl implements CostEnterpriseService {
	
	@Autowired
	private CostEnterpriseMapper costEnterpriseMapper;

	@Override
	public List<CostEnterprise> selectByExample(CostEnterpriseExample example) {
		CostEnterpriseExample e=new CostEnterpriseExample();
		e.setOrderByClause("creater_time desc");
		return costEnterpriseMapper.selectByExample(e);
	}

	@Override
	public CostEnterprise selectByPrimaryKey(String id) {
		return costEnterpriseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CostEnterprise record) {
		return costEnterpriseMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CostEnterprise record) {
		return costEnterpriseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return costEnterpriseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CostEnterprise> selectListByMap(Map<String, Object> map) {
		return costEnterpriseMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return costEnterpriseMapper.selectCountByMap(map);
	}

	@Override
	public int importEnterprise(HttpServletRequest request) {
		try{
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
					//读取exc
					HashMap<String, HashMap<Integer, ArrayList<Object>>> excelMap = ExcelUtil.readExcel(1,path + fileName, 0);
					//遍历excel的sheet
					for(Entry<String, HashMap<Integer, ArrayList<Object>>> excel : excelMap.entrySet()){
						//取出一个sheet内容
						HashMap<Integer, ArrayList<Object>> sheetMap = excel.getValue();
						ArrayList<Object> row = null;
						//遍历一个sheet里的每行数据
						for(Entry<Integer, ArrayList<Object>> sheet : sheetMap.entrySet()){
							row = sheet.getValue();
							if(row!=null){
								if (null == row.get(0) || row.get(1) == null || row.get(2) == null) {
									break;
								} else {
								    CostEnterprise p = new CostEnterprise();
								    String enteId = UUID.randomUUID().toString().replace("-", "");
								    p.setId(enteId);
								    p.setCode(row.get(0).toString().replace(".00", ""));
								    p.setSimpleName(row.get(1).toString());
								    p.setName(row.get(2).toString());
								    p.setContacts(row.get(3).toString());
								    p.setContactsPhone(row.get(4).toString().replace(".00", ""));
								    p.setAddress(row.get(5).toString());
								    p.setFax(row.get(6).toString());
								    p.setEmail(row.get(7).toString());
								    p.setEffectiveFlag(row.get(8).toString());
								    p.setBatch(row.get(9).toString().replace(".00", ""));
								    p.setCreater(row.get(10).toString());
								    p.setDescription(row.get(11).toString());
								    p.setCreaterTime(new Date());
								    costEnterpriseMapper.insertSelective(p);
								}
							}
						}
					}
					//
				}
				return 200;
			}catch(Exception e){
				e.printStackTrace();
				return 500;
			}
		}
	
	@Override
	public CostEnterprise selectByCostEnterpriseName(String name) {
		CostEnterpriseExample example=new CostEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<CostEnterprise> userList=costEnterpriseMapper.selectByExample(example);
		if(null!=userList && userList.size()>0){
			return userList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public CostEnterprise selectByCostEnterpriseCode(String code) {
		CostEnterpriseExample example=new CostEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<CostEnterprise> userList=costEnterpriseMapper.selectByExample(example);
		if(null!=userList && userList.size()>0){
			return userList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		return costEnterpriseMapper.selectCount(map);
	}

	@Override
	public List<CostEnterprise> selectList(Map<String, Object> map) {
		return costEnterpriseMapper.selectList(map);
	}

}
