package com.cost168.costaudit.service.work.impl;

import com.cost168.costaudit.mapper.work.WorkEnterpriseMapper;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.WorkEnterprise;
import com.cost168.costaudit.pojo.work.WorkEnterpriseExample;
import com.cost168.costaudit.pojo.work.WorkEnterpriseExample.Criteria;
import com.cost168.costaudit.service.work.WorkEnterpriseService;
import com.cost168.costaudit.shiro.shiroUtil;

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


@Service
@Transactional(rollbackFor = Exception.class)
public class WorkEnterpriseServiceImpl implements WorkEnterpriseService {
    @Autowired
    private WorkEnterpriseMapper workEnterpriseMapper;

    @Override
    public int insertSelective(WorkEnterprise record) {
        return workEnterpriseMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return workEnterpriseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkEnterprise> selectByExample(WorkEnterpriseExample example) {
        return workEnterpriseMapper.selectByExample(example);
    }

    @Override
    public WorkEnterprise selectByPrimaryKey(String id) {
        return workEnterpriseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkEnterprise workEnterprise) {
        return workEnterpriseMapper.updateByPrimaryKeySelective(workEnterprise);
    }

    @Override
    public List<WorkEnterprise> selectListByMap(Map<String, Object> map) {
        return workEnterpriseMapper.selectListByMap(map);
    }

    @Override
    public int selectCountByMap(Map<String, Object> map) {
        return workEnterpriseMapper.selectCountByMap(map);
    }
    
    @Override
	public List<WorkEnterprise> importWorkEnt(HttpServletRequest request) {
    	List<WorkEnterprise> listDocument=new ArrayList<WorkEnterprise>();
    	SysUser user = shiroUtil.getInstance().currentUser();
		try{
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
					path=path+"WorkEnterprise/"+nowDate+"/";
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
						WorkEnterprise p =null;
						WorkEnterprise resut=null;
						for (int i = 1; i <= num; i++) {
							// 循环输出表格中的内容
							Row row = sheet.getRow(i);
							if(row!=null){
								resut=new WorkEnterprise();
								try {
									//去重复
									if(row.getCell(0)!=null && !"".equals(row.getCell(0).toString())){
										WorkEnterprise we = selectByWorkEnterpriseName(row.getCell(0).toString());
										if(null==we){
											    p = new WorkEnterprise();
									            String enteId = UUID.randomUUID().toString().replace("-", "");
									            p.setId(enteId);
									            p.setName(row.getCell(0).toString());
									            p.setType(row.getCell(1).toString());
									            p.setPersonLiable(row.getCell(2).toString());
									            Cell cell3=row.getCell(3);
											    if(null!=cell3 && !"".equals(cell3)){
											    	cell3.setCellType(HSSFCell.CELL_TYPE_STRING);  
											    	String content = cell3.getStringCellValue();
											    	p.setPhone(content);
											    }
									            p.setTelephone(row.getCell(4).toString());
									            p.setEmail(row.getCell(5).toString());
									            p.setAddress(row.getCell(6).toString());
									            Cell cell7 =row.getCell(7);
											    if (cell7 != null && !"".equals(cell7.toString())) {
											    	if (cell7.getCellTypeEnum() != CellType.STRING &&HSSFDateUtil.isCellDateFormatted(cell7)) {
											    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
											    		Date date = HSSFDateUtil.getJavaDate(cell7.getNumericCellValue());
											    		String value = sdf.format(date); 
											    		p.setServiceStart(sdf.parse(value));
											    	} 
											    }
											    Cell cell8 =row.getCell(8);
											    if (cell8 != null && !"".equals(cell8.toString())) {
											    	if (cell8.getCellTypeEnum() != CellType.STRING &&HSSFDateUtil.isCellDateFormatted(cell8)) {
											    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
											    		Date date = HSSFDateUtil.getJavaDate(cell8.getNumericCellValue());
											    		String value = sdf.format(date); 
											    		p.setServiceEnd(sdf.parse(value));
											    	}
											    }
									            p.setEffectiveFlag(row.getCell(9).toString());
									            p.setRemark(row.getCell(10).toString());
									            p.setCreater(user.getName());
									            workEnterpriseMapper.insertSelective(p);
										}else{
											resut.setName(row.getCell(0).toString());
											listDocument.add(resut);
										}
									}
								}catch (Exception e) {
									 resut.setName(row.getCell(0).toString());
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

	@Override
	public WorkEnterprise selectByWorkEnterpriseName(String name) {
		WorkEnterpriseExample example=new WorkEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<WorkEnterprise> userList=workEnterpriseMapper.selectByExample(example);
		if(null!=userList && userList.size()>0){
			return userList.get(0);
		}else{
			return null;
		}
	}
}
