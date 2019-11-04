package com.cost168.costaudit.service.work.impl;

import com.cost168.costaudit.mapper.work.WorkProcessApplyMapper;
import com.cost168.costaudit.mapper.work.WorkRegisterMapper;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.work.WorkPerson;
import com.cost168.costaudit.pojo.work.WorkProcessApply;
import com.cost168.costaudit.pojo.work.WorkRegister;
import com.cost168.costaudit.pojo.work.WorkRegisterExample;
import com.cost168.costaudit.pojo.work.vo.WorkRegisterVo;
import com.cost168.costaudit.service.work.WorkPersonService;
import com.cost168.costaudit.service.work.WorkRegisterService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.GlobalResult;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkRegisterServiceImpl implements WorkRegisterService {

    @Autowired
    private WorkRegisterMapper workRegisterMapper;

    @Autowired
    private WorkPersonService workPersonService;

    @Autowired
	private WorkProcessApplyMapper workProcessApplyMapper;

    @Override
    public List<WorkRegister> selectByExample(WorkRegisterExample example) {
        return workRegisterMapper.selectByExample(example);
    }

    @Override
    public WorkRegister selectByPrimaryKey(String id) {
        return workRegisterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(WorkRegister record) {
        return workRegisterMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkRegister record) {
        return workRegisterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return workRegisterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkRegisterVo> selectListByMap(Map<String, Object> map) {
    	List<WorkRegisterVo> voList=workRegisterMapper.selectListByMap(map);
    	for(WorkRegisterVo vo:voList){
    		setQjLink1_15(vo);
    		setQjLink16_30(vo);
    	}
        return voList;
    }

	private void setQjLink1_15(WorkRegisterVo vo) {
		//设置1号的请假链接
		if((null!=vo.getMorning_1()&&vo.getMorning_1().split("-").length>=2)||
				(null!=vo.getAfternoon_1()&&vo.getAfternoon_1().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_1()){
				 qjId=vo.getMorning_1().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_1().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_1()){
						String type=vo.getAfternoon_1().split("-")[0];
						vo.setAfternoon_1(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_1()){
						String type=vo.getMorning_1().split("-")[0];
						vo.setMorning_1(type);
					}
				}
			}
			
		}
		//设置2号的请假链接
		if((null!=vo.getMorning_2()&&vo.getMorning_2().split("-").length>=2)||
				(null!=vo.getAfternoon_2()&&vo.getAfternoon_2().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_2()){
				 qjId=vo.getMorning_2().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_2().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_2()){
						String type=vo.getAfternoon_2().split("-")[0];
						vo.setAfternoon_2(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_2()){
						String type=vo.getMorning_2().split("-")[0];
						vo.setMorning_2(type);
					}
				}
			}
		}
		//设置3号的请假链接
		if((null!=vo.getMorning_3()&&vo.getMorning_3().split("-").length>=2)||
				(null!=vo.getAfternoon_3()&&vo.getAfternoon_3().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_3()){
				 qjId=vo.getMorning_3().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_3().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_3()){
						String type=vo.getAfternoon_3().split("-")[0];
						vo.setAfternoon_3(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_3()){
						String type=vo.getMorning_3().split("-")[0];
						vo.setMorning_3(type);
					}
				}
			}
		}
		//设置4号的请假链接
		if((null!=vo.getMorning_4()&&vo.getMorning_4().split("-").length>=2)||
				(null!=vo.getAfternoon_4()&&vo.getAfternoon_4().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_4()){
				 qjId=vo.getMorning_4().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_4().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_4()){
						String type=vo.getAfternoon_4().split("-")[0];
						vo.setAfternoon_4(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_4()){
						String type=vo.getMorning_4().split("-")[0];
						vo.setMorning_4(type);
					}
				}
			}
		}
		//设置5号的请假链接
		if((null!=vo.getMorning_5()&&vo.getMorning_5().split("-").length>=2)||
				(null!=vo.getAfternoon_5()&&vo.getAfternoon_5().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_5()){
				 qjId=vo.getMorning_5().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_5().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_5()){
						String type=vo.getAfternoon_5().split("-")[0];
						vo.setAfternoon_5(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_5()){
						String type=vo.getMorning_5().split("-")[0];
						vo.setMorning_5(type);
					}
				}
			}
		}
		//设置6号的请假链接
		if((null!=vo.getMorning_6()&&vo.getMorning_6().split("-").length>=2)||
				(null!=vo.getAfternoon_6()&&vo.getAfternoon_6().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_6()){
				 qjId=vo.getMorning_6().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_6().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_6()){
						String type=vo.getAfternoon_6().split("-")[0];
						vo.setAfternoon_6(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_6()){
						String type=vo.getMorning_6().split("-")[0];
						vo.setMorning_6(type);
					}
				}
			}
		}
		//设置7号的请假链接
		if((null!=vo.getMorning_7()&&vo.getMorning_7().split("-").length>=2)||
				(null!=vo.getAfternoon_7()&&vo.getAfternoon_7().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_7()){
				 qjId=vo.getMorning_7().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_7().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_7()){
						String type=vo.getAfternoon_7().split("-")[0];
						vo.setAfternoon_7(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_7()){
						String type=vo.getMorning_7().split("-")[0];
						vo.setMorning_7(type);
					}
				}
			}
		}
		//设置8号的请假链接
		if((null!=vo.getMorning_8()&&vo.getMorning_8().split("-").length>=2)||
				(null!=vo.getAfternoon_8()&&vo.getAfternoon_8().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_8()){
				 qjId=vo.getMorning_8().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_8().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_8()){
						String type=vo.getAfternoon_8().split("-")[0];
						vo.setAfternoon_8(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_8()){
						String type=vo.getMorning_8().split("-")[0];
						vo.setMorning_8(type);
					}
				}
			}
		}
		//设置9号的请假链接
		if((null!=vo.getMorning_9()&&vo.getMorning_9().split("-").length>=2)||
				(null!=vo.getAfternoon_9()&&vo.getAfternoon_9().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_9()){
				 qjId=vo.getMorning_9().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_9().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_9()){
						String type=vo.getAfternoon_9().split("-")[0];
						vo.setAfternoon_9(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_9()){
						String type=vo.getMorning_9().split("-")[0];
						vo.setMorning_9(type);
					}
				}
			}
		}
		//设置10号的请假链接
		if((null!=vo.getMorning_10()&&vo.getMorning_10().split("-").length>=2)||
				(null!=vo.getAfternoon_10()&&vo.getAfternoon_10().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_10()){
				 qjId=vo.getMorning_10().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_10().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_10()){
						String type=vo.getAfternoon_10().split("-")[0];
						vo.setAfternoon_10(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_10()){
						String type=vo.getMorning_10().split("-")[0];
						vo.setMorning_10(type);
					}
				}
			}
		}
		//设置11号的请假链接
		if((null!=vo.getMorning_11()&&vo.getMorning_11().split("-").length>=2)||
				(null!=vo.getAfternoon_11()&&vo.getAfternoon_11().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_11()){
				 qjId=vo.getMorning_11().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_11().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_11()){
						String type=vo.getAfternoon_11().split("-")[0];
						vo.setAfternoon_11(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_11()){
						String type=vo.getMorning_11().split("-")[0];
						vo.setMorning_11(type);
					}
				}
			}
		}
		//设置12号的请假链接
		if((null!=vo.getMorning_12()&&vo.getMorning_12().split("-").length>=2)||
				(null!=vo.getAfternoon_12()&&vo.getAfternoon_12().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_12()){
				 qjId=vo.getMorning_12().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_12().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_12()){
						String type=vo.getAfternoon_12().split("-")[0];
						vo.setAfternoon_12(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_12()){
						String type=vo.getMorning_12().split("-")[0];
						vo.setMorning_12(type);
					}
				}
			}
		}
		//设置13号的请假链接
		if((null!=vo.getMorning_13()&&vo.getMorning_13().split("-").length>=2)||
				(null!=vo.getAfternoon_13()&&vo.getAfternoon_13().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_13()){
				 qjId=vo.getMorning_13().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_13().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_13()){
						String type=vo.getAfternoon_13().split("-")[0];
						vo.setAfternoon_13(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_13()){
						String type=vo.getMorning_13().split("-")[0];
						vo.setMorning_13(type);
					}
				}
			}
		}
		//设置14号的请假链接
		if((null!=vo.getMorning_14()&&vo.getMorning_14().split("-").length>=2)||
				(null!=vo.getAfternoon_14()&&vo.getAfternoon_14().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_14()){
				 qjId=vo.getMorning_14().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_14().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_14()){
						String type=vo.getAfternoon_14().split("-")[0];
						vo.setAfternoon_14(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_14()){
						String type=vo.getMorning_14().split("-")[0];
						vo.setMorning_14(type);
					}
				}
			}
		}
		//设置15号的请假链接
		if((null!=vo.getMorning_15()&&vo.getMorning_15().split("-").length>=2)||
				(null!=vo.getAfternoon_15()&&vo.getAfternoon_15().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_15()){
				 qjId=vo.getMorning_15().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_15().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_15()){
						String type=vo.getAfternoon_15().split("-")[0];
						vo.setAfternoon_15(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_15()){
						String type=vo.getMorning_15().split("-")[0];
						vo.setMorning_15(type);
					}
				}
			}
		}
	}	
	private void setQjLink16_30(WorkRegisterVo vo) {	
		//设置16号的请假链接
		if((null!=vo.getMorning_16()&&vo.getMorning_16().split("-").length>=2)||
				(null!=vo.getAfternoon_16()&&vo.getAfternoon_16().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_16()){
				 qjId=vo.getMorning_16().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_16().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_16()){
						String type=vo.getAfternoon_16().split("-")[0];
						vo.setAfternoon_16(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_16()){
						String type=vo.getMorning_16().split("-")[0];
						vo.setMorning_16(type);
					}
				}
			}
		}
		//设置17号的请假链接
		if((null!=vo.getMorning_17()&&vo.getMorning_17().split("-").length>=2)||
				(null!=vo.getAfternoon_17()&&vo.getAfternoon_17().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_17()){
				 qjId=vo.getMorning_17().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_17().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_17()){
						String type=vo.getAfternoon_17().split("-")[0];
						vo.setAfternoon_17(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_17()){
						String type=vo.getMorning_17().split("-")[0];
						vo.setMorning_17(type);
					}
				}
			}
		}
		//设置18号的请假链接
		if((null!=vo.getMorning_18()&&vo.getMorning_18().split("-").length>=2)||
				(null!=vo.getAfternoon_18()&&vo.getAfternoon_18().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_18()){
				 qjId=vo.getMorning_18().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_18().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_18()){
						String type=vo.getAfternoon_18().split("-")[0];
						vo.setAfternoon_18(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_18()){
						String type=vo.getMorning_18().split("-")[0];
						vo.setMorning_18(type);
					}
				}
			}
		}
		//设置19号的请假链接
		if((null!=vo.getMorning_19()&&vo.getMorning_19().split("-").length>=2)||
				(null!=vo.getAfternoon_19()&&vo.getAfternoon_19().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_19()){
				 qjId=vo.getMorning_19().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_19().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_19()){
						String type=vo.getAfternoon_19().split("-")[0];
						vo.setAfternoon_19(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_19()){
						String type=vo.getMorning_19().split("-")[0];
						vo.setMorning_19(type);
					}
				}
			}
		}
		//设置20号的请假链接
		if((null!=vo.getMorning_20()&&vo.getMorning_20().split("-").length>=2)||
				(null!=vo.getAfternoon_20()&&vo.getAfternoon_20().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_20()){
				 qjId=vo.getMorning_20().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_20().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_20()){
						String type=vo.getAfternoon_20().split("-")[0];
						vo.setAfternoon_20(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_20()){
						String type=vo.getMorning_20().split("-")[0];
						vo.setMorning_20(type);
					}
				}
			}
		}
		//设置21号的请假链接
		if((null!=vo.getMorning_21()&&vo.getMorning_21().split("-").length>=2)||
				(null!=vo.getAfternoon_21()&&vo.getAfternoon_21().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_21()){
				 qjId=vo.getMorning_21().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_21().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_21()){
						String type=vo.getAfternoon_21().split("-")[0];
						vo.setAfternoon_21(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_21()){
						String type=vo.getMorning_21().split("-")[0];
						vo.setMorning_21(type);
					}
				}
			}
		}
		//设置22号的请假链接
		if((null!=vo.getMorning_22()&&vo.getMorning_22().split("-").length>=2)||
				(null!=vo.getAfternoon_22()&&vo.getAfternoon_22().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_22()){
				 qjId=vo.getMorning_22().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_22().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_22()){
						String type=vo.getAfternoon_22().split("-")[0];
						vo.setAfternoon_22(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_22()){
						String type=vo.getMorning_22().split("-")[0];
						vo.setMorning_22(type);
					}
				}
			}
		}
		//设置23号的请假链接
		if((null!=vo.getMorning_23()&&vo.getMorning_23().split("-").length>=2)||
				(null!=vo.getAfternoon_23()&&vo.getAfternoon_23().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_23()){
				 qjId=vo.getMorning_23().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_23().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_23()){
						String type=vo.getAfternoon_23().split("-")[0];
						vo.setAfternoon_23(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_23()){
						String type=vo.getMorning_23().split("-")[0];
						vo.setMorning_23(type);
					}
				}
			}
		}
		//设置24号的请假链接
		if((null!=vo.getMorning_24()&&vo.getMorning_24().split("-").length>=2)||
				(null!=vo.getAfternoon_24()&&vo.getAfternoon_24().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_24()){
				 qjId=vo.getMorning_24().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_24().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_24()){
						String type=vo.getAfternoon_24().split("-")[0];
						vo.setAfternoon_24(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_24()){
						String type=vo.getMorning_24().split("-")[0];
						vo.setMorning_24(type);
					}
				}
			}
		}
		//设置25号的请假链接
		if((null!=vo.getMorning_25()&&vo.getMorning_25().split("-").length>=2)||
				(null!=vo.getAfternoon_25()&&vo.getAfternoon_25().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_25()){
				 qjId=vo.getMorning_25().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_25().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_25()){
						String type=vo.getAfternoon_25().split("-")[0];
						vo.setAfternoon_25(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_25()){
						String type=vo.getMorning_25().split("-")[0];
						vo.setMorning_25(type);
					}
				}
			}
		}
		//设置26号的请假链接
		if((null!=vo.getMorning_26()&&vo.getMorning_26().split("-").length>=2)||
				(null!=vo.getAfternoon_26()&&vo.getAfternoon_26().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_26()){
				 qjId=vo.getMorning_26().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_26().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_26()){
						String type=vo.getAfternoon_26().split("-")[0];
						vo.setAfternoon_26(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_26()){
						String type=vo.getMorning_26().split("-")[0];
						vo.setMorning_26(type);
					}
				}
			}
		}
		if((null!=vo.getMorning_27()&&vo.getMorning_27().split("-").length>=2)||
				(null!=vo.getAfternoon_27()&&vo.getAfternoon_27().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_27()){
				 qjId=vo.getMorning_27().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_27().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_27()){
						String type=vo.getAfternoon_27().split("-")[0];
						vo.setAfternoon_27(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_27()){
						String type=vo.getMorning_27().split("-")[0];
						vo.setMorning_27(type);
					}
				}
			}
		}
		
		if((null!=vo.getMorning_28()&&vo.getMorning_28().split("-").length>=2)||
				(null!=vo.getAfternoon_28()&&vo.getAfternoon_28().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_28()){
				 qjId=vo.getMorning_28().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_28().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_28()){
						String type=vo.getAfternoon_28().split("-")[0];
						vo.setAfternoon_28(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_28()){
						String type=vo.getMorning_28().split("-")[0];
						vo.setMorning_28(type);
					}
				}
			}
		}
		
		if((null!=vo.getMorning_29()&&vo.getMorning_29().split("-").length>=2)||
				(null!=vo.getAfternoon_29()&&vo.getAfternoon_29().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_29()){
				 qjId=vo.getMorning_29().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_29().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_29()){
						String type=vo.getAfternoon_29().split("-")[0];
						vo.setAfternoon_29(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_29()){
						String type=vo.getMorning_29().split("-")[0];
						vo.setMorning_29(type);
					}
				}
			}
		}
		//设置30号的请假链接
		if((null!=vo.getMorning_30()&&vo.getMorning_30().split("-").length>=2)||
				(null!=vo.getAfternoon_30()&&vo.getAfternoon_30().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_30()){
				 qjId=vo.getMorning_30().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_30().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_30()){
						String type=vo.getAfternoon_30().split("-")[0];
						vo.setAfternoon_30(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_30()){
						String type=vo.getMorning_30().split("-")[0];
						vo.setMorning_30(type);
					}
				}
			}
		}
		//设置31号的请假链接
		if((null!=vo.getMorning_31()&&vo.getMorning_31().split("-").length>=2)||
				(null!=vo.getAfternoon_31()&&vo.getAfternoon_31().split("-").length>=2)){
			String qjId="";
			if(null!=vo.getMorning_31()){
				 qjId=vo.getMorning_31().split("-")[1];
			}else{
				 qjId=vo.getAfternoon_31().split("-")[1];
			}
			WorkProcessApply wr= workProcessApplyMapper.selectByPrimaryKey(qjId);
			if(null!=wr){
				if("上午".equals(wr.getMorAftAll())){
					if(null!=vo.getAfternoon_31()){
						String type=vo.getAfternoon_31().split("-")[0];
						vo.setAfternoon_31(type);
					}
				}
				if("下午".equals(wr.getMorAftAll())){
					if(null!=vo.getMorning_31()){
						String type=vo.getMorning_31().split("-")[0];
						vo.setMorning_31(type);
					}
				}
			}
		}
		
	}

    @Override
    public int selectCountByMap(Map<String, Object> map) {
        return workRegisterMapper.selectCountByMap(map);
    }

    @Override
    public List<WorkRegister> selectByDay(Map<String, Object> map) {
        return workRegisterMapper.selectByDay(map);
    }

    @Override
    public String selectYearList() {
        return workRegisterMapper.selectYearList();
    }
    @Override
    public WorkRegister selectByLeaveprocess(String  leaveprocess){
        return workRegisterMapper.selectByLeaveprocess(leaveprocess);
    }

    @Override
    public String selectMonthList() {
        return workRegisterMapper.selectMonthList();
    }

    @Override
    public String selectDayStr(Map<String, Object> map) {
        return workRegisterMapper.selectDayStr(map);
    }

    //设置人员的年休假,如果有年休假就累加，没有就什么也不做
    private void setVa(WorkPerson wp, String am, String pm) {
        //可休年假=总年假-已经休掉的年假
        Float aunual=Float.parseFloat(wp.getAnnualLeaveTotal())-Float.parseFloat(wp.getAnnualLeaveUseup());
        if ("年休假".equals(am) && "年休假".equals(pm)) {
            //如果excel的年假大于可休年假则不导入
            if (1.0f <= aunual) {
                wp.setAnnualLeaveUseup(Float.parseFloat(wp.getAnnualLeaveUseup()) + 1.0f + "");
            }
        } else if ("年休假".equals(am) && !"年休假".equals(pm)) {
            if (0.5f <= aunual) {
                wp.setAnnualLeaveUseup(Float.parseFloat(wp.getAnnualLeaveUseup()) + 0.5f + "");
            }
        } else if (!"年休假".equals(am) && "年休假".equals(pm)) {
            if (0.5f <= aunual) {
                wp.setAnnualLeaveUseup(Float.parseFloat(wp.getAnnualLeaveUseup()) + 0.5f + "");
            }
        }
        workPersonService.updateByPrimaryKeySelective(wp);
    }

    /**
     * created by ZYL on 2019/6/12
     * 考勤管理导入
     */
    @Override
    public GlobalResult attendanceImport(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("file");
            if (files.size() > 0) {
                MultipartFile file = files.get(0);
                String fileName = file.getOriginalFilename();
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                String nowDate = fmt.format(new Date());
                Properties props = new Properties();
                props.load(this.getClass().getResourceAsStream("/resource/resource.properties"));
                String path = (String) props.get("fileupload");
                path = path + "attendance" + nowDate + "/";
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
                    int index = sheet.getLastRowNum();
                    WorkRegister workRegister = null;
                    SysUser user = shiroUtil.getInstance().currentUser();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    for (int i = 2; i <= index; i++) {
                        Row row = sheet.getRow(i);
                        if (row != null) {
                            Cell cell2 = row.getCell(2);
                            String name = cell2.toString();
                            WorkPerson wp = workPersonService.selectByName(name);
                            //判断名字是否存在，不存在则不插入数据
                            if (null != wp) {
                                //1号
                                //先查register ，根据 人id 年月 日
                                Map<String, Object> selectMap = new HashMap<String, Object>();
                                selectMap.put("personId", wp.getAccount());
                                selectMap.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap.put("regDay", "1");
                                List<WorkRegister> workRegisters = workRegisterMapper.selectByDay(selectMap);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters.size() > 0) {
                                    workRegisters.get(0).setMorning(row.getCell(3).toString());
                                    workRegisters.get(0).setAfternoon(row.getCell(4).toString());
                                    if ("上班".equals(row.getCell(3).toString()) && "上班".equals(row.getCell(4).toString())) {
                                        workRegisters.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(3).toString()) && !"上班".equals(row.getCell(4).toString())) {
                                        workRegisters.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(3).toString()) && "上班".equals(row.getCell(4).toString())) {
                                        workRegisters.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters.get(0).setCreater(user.getName());
                                    workRegisters.get(0).setUpdateTime(date);
                                    workRegisters.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters.get(0));
                                    //设置人员的年休假,如果1号有年休假就累加，没有就什么也不做
                                    setVa(wp, row.getCell(3).toString(), row.getCell(4).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id = UUID.randomUUID().toString().replace("-", "");
                                    String year = row.getCell(0).toString().replace(".0", "");
                                    String month = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth.length() == 1) {
                                        zhMonth = "0" + zhMonth;
                                    }
                                    workRegister.setId(id);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year + month);
                                    workRegister.setRegYear(year);
                                    workRegister.setRegMonth(month);
                                    workRegister.setMorning(row.getCell(3).toString());
                                    workRegister.setAfternoon(row.getCell(4).toString());
                                    workRegister.setRegDay("1");
                                    if ("上班".equals(row.getCell(3).toString()) && "上班".equals(row.getCell(4).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(3).toString()) && !"上班".equals(row.getCell(4).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(3).toString()) && "上班".equals(row.getCell(4).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year + "-" + zhMonth + "-" + "01");
//                                    Cell cell65 = row.getCell(65);
//                                    //判断备注是否为null
//                                    if (null != cell65) {
//                                        String remark1 = row.getCell(65).toString();
//                                        workRegister.setRemark(null != remark1 ? remark1 : "");
//                                    }
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    //设置人员的年休假,如果1号有年休假就累加，没有就什么也不做
                                    setVa(wp, row.getCell(3).toString(), row.getCell(4).toString());
                                }
                                //2号
                                Map<String, Object> selectMap2 = new HashMap<String, Object>();
                                selectMap2.put("personId", wp.getAccount());
                                selectMap2.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap2.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap2.put("regDay", "2");
                                List<WorkRegister> workRegisters2 = workRegisterMapper.selectByDay(selectMap2);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters2.size() > 0) {
                                    workRegisters2.get(0).setMorning(row.getCell(5).toString());
                                    workRegisters2.get(0).setAfternoon(row.getCell(6).toString());
                                    if ("上班".equals(row.getCell(5).toString()) && "上班".equals(row.getCell(6).toString())) {
                                        workRegisters2.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(5).toString()) && !"上班".equals(row.getCell(6).toString())) {
                                        workRegisters2.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(5).toString()) && "上班".equals(row.getCell(6).toString())) {
                                        workRegisters2.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters2.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters2.get(0).setCreater(user.getName());
                                    workRegisters2.get(0).setUpdateTime(date);
                                    workRegisters2.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters2.get(0));
                                    setVa(wp, row.getCell(5).toString(), row.getCell(6).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id2 = UUID.randomUUID().toString().replace("-", "");
                                    String year2 = row.getCell(0).toString().replace(".0", "");
                                    String month2 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth2 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth2.length() == 1) {
                                        zhMonth2 = "0" + zhMonth2;
                                    }
                                    workRegister.setId(id2);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year2 + month2);
                                    workRegister.setRegYear(year2);
                                    workRegister.setRegMonth(month2);
                                    workRegister.setMorning(row.getCell(5).toString());
                                    workRegister.setAfternoon(row.getCell(6).toString());
                                    workRegister.setRegDay("2");
                                    if ("上班".equals(row.getCell(5).toString()) && "上班".equals(row.getCell(6).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(5).toString()) && !"上班".equals(row.getCell(6).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(5).toString()) && "上班".equals(row.getCell(6).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year2 + "-" + zhMonth2 + "-" + "02");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(5).toString(), row.getCell(6).toString());
                                }
                                //3号
                                Map<String, Object> selectMap3 = new HashMap<String, Object>();
                                selectMap3.put("personId", wp.getAccount());
                                selectMap3.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap3.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap3.put("regDay", "3");
                                List<WorkRegister> workRegisters3 = workRegisterMapper.selectByDay(selectMap3);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters3.size() > 0) {
                                    workRegisters3.get(0).setMorning(row.getCell(7).toString());
                                    workRegisters3.get(0).setAfternoon(row.getCell(8).toString());
                                    if ("上班".equals(row.getCell(7).toString()) && "上班".equals(row.getCell(8).toString())) {
                                        workRegisters3.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(7).toString()) && !"上班".equals(row.getCell(8).toString())) {
                                        workRegisters3.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(7).toString()) && "上班".equals(row.getCell(8).toString())) {
                                        workRegisters3.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters3.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters3.get(0).setCreater(user.getName());
                                    workRegisters3.get(0).setUpdateTime(date);
                                    workRegisters3.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters3.get(0));
                                    setVa(wp, row.getCell(7).toString(), row.getCell(8).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id3 = UUID.randomUUID().toString().replace("-", "");
                                    String year3 = row.getCell(0).toString().replace(".0", "");
                                    String month3 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth3 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth3.length() == 1) {
                                        zhMonth3 = "0" + zhMonth3;
                                    }
                                    workRegister.setId(id3);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year3 + month3);
                                    workRegister.setRegYear(year3);
                                    workRegister.setRegMonth(month3);
                                    workRegister.setMorning(row.getCell(7).toString());
                                    workRegister.setAfternoon(row.getCell(8).toString());
                                    workRegister.setRegDay("3");
                                    if ("上班".equals(row.getCell(7).toString()) && "上班".equals(row.getCell(8).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(7).toString()) && !"上班".equals(row.getCell(8).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(7).toString()) && "上班".equals(row.getCell(8).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year3 + "-" + zhMonth3 + "-" + "03");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(7).toString(), row.getCell(8).toString());
                                }
                                //4号
                                Map<String, Object> selectMap4 = new HashMap<String, Object>();
                                selectMap4.put("personId", wp.getAccount());
                                selectMap4.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap4.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap4.put("regDay", "4");
                                List<WorkRegister> workRegisters4 = workRegisterMapper.selectByDay(selectMap4);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters4.size() > 0) {
                                    workRegisters4.get(0).setMorning(row.getCell(9).toString());
                                    workRegisters4.get(0).setAfternoon(row.getCell(10).toString());
                                    if ("上班".equals(row.getCell(9).toString()) && "上班".equals(row.getCell(10).toString())) {
                                        workRegisters4.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(9).toString()) && !"上班".equals(row.getCell(10).toString())) {
                                        workRegisters4.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(9).toString()) && "上班".equals(row.getCell(10).toString())) {
                                        workRegisters4.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters4.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters4.get(0).setCreater(user.getName());
                                    workRegisters4.get(0).setUpdateTime(date);
                                    workRegisters4.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters4.get(0));
                                    setVa(wp, row.getCell(9).toString(), row.getCell(9).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id4 = UUID.randomUUID().toString().replace("-", "");
                                    String year4 = row.getCell(0).toString().replace(".0", "");
                                    String month4 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth4 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth4.length() == 1) {
                                        zhMonth4 = "0" + zhMonth4;
                                    }
                                    workRegister.setId(id4);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year4 + month4);
                                    workRegister.setRegYear(year4);
                                    workRegister.setRegMonth(month4);
                                    workRegister.setMorning(row.getCell(9).toString());
                                    workRegister.setAfternoon(row.getCell(10).toString());
                                    workRegister.setRegDay("4");
                                    if ("上班".equals(row.getCell(9).toString()) && "上班".equals(row.getCell(10).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(9).toString()) && !"上班".equals(row.getCell(10).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(9).toString()) && "上班".equals(row.getCell(10).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year4 + "-" + zhMonth4 + "-" + "04");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(9).toString(), row.getCell(9).toString());
                                }
                                //5号
                                Map<String, Object> selectMap5 = new HashMap<String, Object>();
                                selectMap5.put("personId", wp.getAccount());
                                selectMap5.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap5.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap5.put("regDay", "5");
                                List<WorkRegister> workRegisters5 = workRegisterMapper.selectByDay(selectMap5);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters5.size() > 0) {
                                    workRegisters5.get(0).setMorning(row.getCell(11).toString());
                                    workRegisters5.get(0).setAfternoon(row.getCell(12).toString());
                                    if ("上班".equals(row.getCell(11).toString()) && "上班".equals(row.getCell(12).toString())) {
                                        workRegisters5.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(11).toString()) && !"上班".equals(row.getCell(12).toString())) {
                                        workRegisters5.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(11).toString()) && "上班".equals(row.getCell(12).toString())) {
                                        workRegisters5.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters5.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters5.get(0).setCreater(user.getName());
                                    workRegisters5.get(0).setUpdateTime(date);
                                    workRegisters5.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters5.get(0));
                                    setVa(wp, row.getCell(11).toString(), row.getCell(12).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id5 = UUID.randomUUID().toString().replace("-", "");
                                    String year5 = row.getCell(0).toString().replace(".0", "");
                                    String month5 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth5 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth5.length() == 1) {
                                        zhMonth5 = "0" + zhMonth5;
                                    }
                                    workRegister.setId(id5);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year5 + month5);
                                    workRegister.setRegYear(year5);
                                    workRegister.setRegMonth(month5);
                                    workRegister.setMorning(row.getCell(11).toString());
                                    workRegister.setAfternoon(row.getCell(12).toString());
                                    workRegister.setRegDay("5");
                                    if ("上班".equals(row.getCell(11).toString()) && "上班".equals(row.getCell(11).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(11).toString()) && !"上班".equals(row.getCell(12).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(11).toString()) && "上班".equals(row.getCell(12).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year5 + "-" + zhMonth5 + "-" + "05");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(11).toString(), row.getCell(12).toString());
                                }
                                //6号
                                Map<String, Object> selectMap6 = new HashMap<String, Object>();
                                selectMap6.put("personId", wp.getAccount());
                                selectMap6.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap6.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap6.put("regDay", "6");
                                List<WorkRegister> workRegisters6 = workRegisterMapper.selectByDay(selectMap6);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters6.size() > 0) {
                                    workRegisters6.get(0).setMorning(row.getCell(13).toString());
                                    workRegisters6.get(0).setAfternoon(row.getCell(14).toString());
                                    if ("上班".equals(row.getCell(13).toString()) && "上班".equals(row.getCell(14).toString())) {
                                        workRegisters6.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(13).toString()) && !"上班".equals(row.getCell(14).toString())) {
                                        workRegisters6.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(13).toString()) && "上班".equals(row.getCell(14).toString())) {
                                        workRegisters6.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters6.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters6.get(0).setCreater(user.getName());
                                    workRegisters6.get(0).setUpdateTime(date);
                                    workRegisters6.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters6.get(0));
                                    setVa(wp, row.getCell(13).toString(), row.getCell(14).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id6 = UUID.randomUUID().toString().replace("-", "");
                                    String year6 = row.getCell(0).toString().replace(".0", "");
                                    String month6 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth6 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth6.length() == 1) {
                                        zhMonth6 = "0" + zhMonth6;
                                    }
                                    workRegister.setId(id6);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year6 + month6);
                                    workRegister.setRegYear(year6);
                                    workRegister.setRegMonth(month6);
                                    workRegister.setMorning(row.getCell(13).toString());
                                    workRegister.setAfternoon(row.getCell(14).toString());
                                    workRegister.setRegDay("6");
                                    if ("上班".equals(row.getCell(13).toString()) && "上班".equals(row.getCell(14).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(13).toString()) && !"上班".equals(row.getCell(14).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(13).toString()) && "上班".equals(row.getCell(14).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year6 + "-" + zhMonth6 + "-" + "06");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(13).toString(), row.getCell(14).toString());
                                }
                                //7号
                                Map<String, Object> selectMap7 = new HashMap<String, Object>();
                                selectMap7.put("personId", wp.getAccount());
                                selectMap7.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap7.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap7.put("regDay", "7");
                                List<WorkRegister> workRegisters7 = workRegisterMapper.selectByDay(selectMap7);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters7.size() > 0) {
                                    workRegisters7.get(0).setMorning(row.getCell(15).toString());
                                    workRegisters7.get(0).setAfternoon(row.getCell(16).toString());
                                    if ("上班".equals(row.getCell(15).toString()) && "上班".equals(row.getCell(16).toString())) {
                                        workRegisters7.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(15).toString()) && !"上班".equals(row.getCell(16).toString())) {
                                        workRegisters7.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(15).toString()) && "上班".equals(row.getCell(16).toString())) {
                                        workRegisters7.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters7.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters7.get(0).setCreater(user.getName());
                                    workRegisters7.get(0).setUpdateTime(date);
                                    workRegisters7.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters7.get(0));
                                    setVa(wp, row.getCell(15).toString(), row.getCell(16).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id7 = UUID.randomUUID().toString().replace("-", "");
                                    String year7 = row.getCell(0).toString().replace(".0", "");
                                    String month7 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth7 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth7.length() == 1) {
                                        zhMonth7 = "0" + zhMonth7;
                                    }
                                    workRegister.setId(id7);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year7 + month7);
                                    workRegister.setRegYear(year7);
                                    workRegister.setRegMonth(month7);
                                    workRegister.setMorning(row.getCell(15).toString());
                                    workRegister.setAfternoon(row.getCell(16).toString());
                                    workRegister.setRegDay("7");
                                    if ("上班".equals(row.getCell(15).toString()) && "上班".equals(row.getCell(16).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(15).toString()) && !"上班".equals(row.getCell(16).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(15).toString()) && "上班".equals(row.getCell(16).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year7 + "-" + zhMonth7 + "-" + "07");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(15).toString(), row.getCell(16).toString());
                                }
                                //8号
                                Map<String, Object> selectMap8 = new HashMap<String, Object>();
                                selectMap8.put("personId", wp.getAccount());
                                selectMap8.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap8.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap8.put("regDay", "8");
                                List<WorkRegister> workRegisters8 = workRegisterMapper.selectByDay(selectMap8);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters8.size() > 0) {
                                    workRegisters8.get(0).setMorning(row.getCell(17).toString());
                                    workRegisters8.get(0).setAfternoon(row.getCell(18).toString());
                                    if ("上班".equals(row.getCell(17).toString()) && "上班".equals(row.getCell(18).toString())) {
                                        workRegisters8.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(17).toString()) && !"上班".equals(row.getCell(18).toString())) {
                                        workRegisters8.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(17).toString()) && "上班".equals(row.getCell(18).toString())) {
                                        workRegisters8.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters8.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters8.get(0).setCreater(user.getName());
                                    workRegisters8.get(0).setUpdateTime(date);
                                    workRegisters8.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters8.get(0));
                                    setVa(wp, row.getCell(17).toString(), row.getCell(18).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id8 = UUID.randomUUID().toString().replace("-", "");
                                    String year8 = row.getCell(0).toString().replace(".0", "");
                                    String month8 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth8 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth8.length() == 1) {
                                        zhMonth8 = "0" + zhMonth8;
                                    }
                                    workRegister.setId(id8);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year8 + month8);
                                    workRegister.setRegYear(year8);
                                    workRegister.setRegMonth(month8);
                                    workRegister.setMorning(row.getCell(17).toString());
                                    workRegister.setAfternoon(row.getCell(18).toString());
                                    workRegister.setRegDay("8");
                                    if ("上班".equals(row.getCell(17).toString()) && "上班".equals(row.getCell(18).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(17).toString()) && !"上班".equals(row.getCell(18).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(17).toString()) && "上班".equals(row.getCell(18).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year8 + "-" + zhMonth8 + "-" + "08");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(17).toString(), row.getCell(18).toString());
                                }
                                //9号
                                Map<String, Object> selectMap9 = new HashMap<String, Object>();
                                selectMap9.put("personId", wp.getAccount());
                                selectMap9.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap9.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap9.put("regDay", "9");
                                List<WorkRegister> workRegisters9 = workRegisterMapper.selectByDay(selectMap9);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters9.size() > 0) {
                                    workRegisters9.get(0).setMorning(row.getCell(19).toString());
                                    workRegisters9.get(0).setAfternoon(row.getCell(20).toString());
                                    if ("上班".equals(row.getCell(19).toString()) && "上班".equals(row.getCell(20).toString())) {
                                        workRegisters9.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(19).toString()) && !"上班".equals(row.getCell(20).toString())) {
                                        workRegisters9.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(19).toString()) && "上班".equals(row.getCell(20).toString())) {
                                        workRegisters9.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters9.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters9.get(0).setCreater(user.getName());
                                    workRegisters9.get(0).setUpdateTime(date);
                                    workRegisters9.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters9.get(0));
                                    setVa(wp, row.getCell(19).toString(), row.getCell(20).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id9 = UUID.randomUUID().toString().replace("-", "");
                                    String year9 = row.getCell(0).toString().replace(".0", "");
                                    String month9 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth9 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth9.length() == 1) {
                                        zhMonth9 = "0" + zhMonth9;
                                    }
                                    workRegister.setId(id9);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year9 + month9);
                                    workRegister.setRegYear(year9);
                                    workRegister.setRegMonth(month9);
                                    workRegister.setMorning(row.getCell(19).toString());
                                    workRegister.setAfternoon(row.getCell(20).toString());
                                    workRegister.setRegDay("9");
                                    if ("上班".equals(row.getCell(19).toString()) && "上班".equals(row.getCell(20).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(19).toString()) && !"上班".equals(row.getCell(20).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(19).toString()) && "上班".equals(row.getCell(20).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year9 + "-" + zhMonth9 + "-" + "09");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(19).toString(), row.getCell(20).toString());
                                }
                                //10号
                                Map<String, Object> selectMap10 = new HashMap<String, Object>();
                                selectMap10.put("personId", wp.getAccount());
                                selectMap10.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap10.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap10.put("regDay", "10");
                                List<WorkRegister> workRegisters10 = workRegisterMapper.selectByDay(selectMap10);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters10.size() > 0) {
                                    workRegisters10.get(0).setMorning(row.getCell(21).toString());
                                    workRegisters10.get(0).setAfternoon(row.getCell(22).toString());
                                    if ("上班".equals(row.getCell(21).toString()) && "上班".equals(row.getCell(22).toString())) {
                                        workRegisters10.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(21).toString()) && !"上班".equals(row.getCell(22).toString())) {
                                        workRegisters10.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(21).toString()) && "上班".equals(row.getCell(22).toString())) {
                                        workRegisters10.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters10.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters10.get(0).setCreater(user.getName());
                                    workRegisters10.get(0).setUpdateTime(date);
                                    workRegisters10.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters10.get(0));
                                    setVa(wp, row.getCell(21).toString(), row.getCell(22).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id10 = UUID.randomUUID().toString().replace("-", "");
                                    String year10 = row.getCell(0).toString().replace(".0", "");
                                    String month10 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth10 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth10.length() == 1) {
                                        zhMonth10 = "0" + zhMonth10;
                                    }
                                    workRegister.setId(id10);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year10 + month10);
                                    workRegister.setRegYear(year10);
                                    workRegister.setRegMonth(month10);
                                    workRegister.setMorning(row.getCell(21).toString());
                                    workRegister.setAfternoon(row.getCell(22).toString());
                                    workRegister.setRegDay("10");
                                    if ("上班".equals(row.getCell(21).toString()) && "上班".equals(row.getCell(22).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(21).toString()) && !"上班".equals(row.getCell(22).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(21).toString()) && "上班".equals(row.getCell(22).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year10 + "-" + zhMonth10 + "-" + "10");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(21).toString(), row.getCell(22).toString());
                                }
                                //11号
                                Map<String, Object> selectMap11 = new HashMap<String, Object>();
                                selectMap11.put("personId", wp.getAccount());
                                selectMap11.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap11.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap11.put("regDay", "11");
                                List<WorkRegister> workRegisters11 = workRegisterMapper.selectByDay(selectMap11);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters11.size() > 0) {
                                    workRegisters11.get(0).setMorning(row.getCell(23).toString());
                                    workRegisters11.get(0).setAfternoon(row.getCell(24).toString());
                                    if ("上班".equals(row.getCell(23).toString()) && "上班".equals(row.getCell(24).toString())) {
                                        workRegisters11.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(23).toString()) && !"上班".equals(row.getCell(24).toString())) {
                                        workRegisters11.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(23).toString()) && "上班".equals(row.getCell(24).toString())) {
                                        workRegisters11.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters11.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters11.get(0).setCreater(user.getName());
                                    workRegisters11.get(0).setUpdateTime(date);
                                    workRegisters11.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters11.get(0));
                                    setVa(wp, row.getCell(23).toString(), row.getCell(24).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id11 = UUID.randomUUID().toString().replace("-", "");
                                    String year11 = row.getCell(0).toString().replace(".0", "");
                                    String month11 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth11 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth11.length() == 1) {
                                        zhMonth11 = "0" + zhMonth11;
                                    }
                                    workRegister.setId(id11);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year11 + month11);
                                    workRegister.setRegYear(year11);
                                    workRegister.setRegMonth(month11);
                                    workRegister.setMorning(row.getCell(23).toString());
                                    workRegister.setAfternoon(row.getCell(24).toString());
                                    workRegister.setRegDay("11");
                                    if ("上班".equals(row.getCell(23).toString()) && "上班".equals(row.getCell(24).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(23).toString()) && !"上班".equals(row.getCell(24).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(23).toString()) && "上班".equals(row.getCell(24).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year11 + "-" + zhMonth11 + "-" + "11");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(23).toString(), row.getCell(24).toString());
                                }
                                //12号
                                Map<String, Object> selectMap12 = new HashMap<String, Object>();
                                selectMap12.put("personId", wp.getAccount());
                                selectMap12.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap12.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap12.put("regDay", "12");
                                List<WorkRegister> workRegisters12 = workRegisterMapper.selectByDay(selectMap12);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters12.size() > 0) {
                                    workRegisters12.get(0).setMorning(row.getCell(25).toString());
                                    workRegisters12.get(0).setAfternoon(row.getCell(26).toString());
                                    if ("上班".equals(row.getCell(25).toString()) && "上班".equals(row.getCell(26).toString())) {
                                        workRegisters12.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(25).toString()) && !"上班".equals(row.getCell(26).toString())) {
                                        workRegisters12.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(25).toString()) && "上班".equals(row.getCell(26).toString())) {
                                        workRegisters12.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters12.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters12.get(0).setCreater(user.getName());
                                    workRegisters12.get(0).setUpdateTime(date);
                                    workRegisters12.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters12.get(0));
                                    setVa(wp, row.getCell(25).toString(), row.getCell(26).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id12 = UUID.randomUUID().toString().replace("-", "");
                                    String year12 = row.getCell(0).toString().replace(".0", "");
                                    String month12 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth12 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth12.length() == 1) {
                                        zhMonth12 = "0" + zhMonth12;
                                    }
                                    workRegister.setId(id12);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year12 + month12);
                                    workRegister.setRegYear(year12);
                                    workRegister.setRegMonth(month12);
                                    workRegister.setMorning(row.getCell(25).toString());
                                    workRegister.setAfternoon(row.getCell(26).toString());
                                    workRegister.setRegDay("12");
                                    if ("上班".equals(row.getCell(25).toString()) && "上班".equals(row.getCell(26).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(25).toString()) && !"上班".equals(row.getCell(26).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(25).toString()) && "上班".equals(row.getCell(26).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year12 + "-" + zhMonth12 + "-" + "12");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(25).toString(), row.getCell(26).toString());
                                }
                                //13号
                                Map<String, Object> selectMap13 = new HashMap<String, Object>();
                                selectMap13.put("personId", wp.getAccount());
                                selectMap13.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap13.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap13.put("regDay", "13");
                                List<WorkRegister> workRegisters13 = workRegisterMapper.selectByDay(selectMap13);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters13.size() > 0) {
                                    workRegisters13.get(0).setMorning(row.getCell(27).toString());
                                    workRegisters13.get(0).setAfternoon(row.getCell(28).toString());
                                    if ("上班".equals(row.getCell(27).toString()) && "上班".equals(row.getCell(28).toString())) {
                                        workRegisters13.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(27).toString()) && !"上班".equals(row.getCell(28).toString())) {
                                        workRegisters13.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(27).toString()) && "上班".equals(row.getCell(28).toString())) {
                                        workRegisters13.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters13.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters13.get(0).setCreater(user.getName());
                                    workRegisters13.get(0).setUpdateTime(date);
                                    workRegisters13.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters13.get(0));
                                    setVa(wp, row.getCell(27).toString(), row.getCell(28).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id13 = UUID.randomUUID().toString().replace("-", "");
                                    String year13 = row.getCell(0).toString().replace(".0", "");
                                    String month13 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth13 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth13.length() == 1) {
                                        zhMonth13 = "0" + zhMonth13;
                                    }
                                    workRegister.setId(id13);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year13 + month13);
                                    workRegister.setRegYear(year13);
                                    workRegister.setRegMonth(month13);
                                    workRegister.setMorning(row.getCell(27).toString());
                                    workRegister.setAfternoon(row.getCell(28).toString());
                                    workRegister.setRegDay("13");
                                    if ("上班".equals(row.getCell(27).toString()) && "上班".equals(row.getCell(28).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(27).toString()) && !"上班".equals(row.getCell(28).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(27).toString()) && "上班".equals(row.getCell(28).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year13 + "-" + zhMonth13 + "-" + "13");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(27).toString(), row.getCell(28).toString());
                                }
                                //14号
                                Map<String, Object> selectMap14 = new HashMap<String, Object>();
                                selectMap14.put("personId", wp.getAccount());
                                selectMap14.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap14.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap14.put("regDay", "14");
                                List<WorkRegister> workRegisters14 = workRegisterMapper.selectByDay(selectMap14);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters14.size() > 0) {
                                    workRegisters14.get(0).setMorning(row.getCell(29).toString());
                                    workRegisters14.get(0).setAfternoon(row.getCell(30).toString());
                                    if ("上班".equals(row.getCell(29).toString()) && "上班".equals(row.getCell(30).toString())) {
                                        workRegisters14.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(29).toString()) && !"上班".equals(row.getCell(30).toString())) {
                                        workRegisters14.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(29).toString()) && "上班".equals(row.getCell(30).toString())) {
                                        workRegisters14.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters14.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters14.get(0).setCreater(user.getName());
                                    workRegisters14.get(0).setUpdateTime(date);
                                    workRegisters14.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters14.get(0));
                                    setVa(wp, row.getCell(29).toString(), row.getCell(30).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id14 = UUID.randomUUID().toString().replace("-", "");
                                    String year14 = row.getCell(0).toString().replace(".0", "");
                                    String month14 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth14 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth14.length() == 1) {
                                        zhMonth14 = "0" + zhMonth14;
                                    }
                                    workRegister.setId(id14);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year14 + month14);
                                    workRegister.setRegYear(year14);
                                    workRegister.setRegMonth(month14);
                                    workRegister.setMorning(row.getCell(29).toString());
                                    workRegister.setAfternoon(row.getCell(30).toString());
                                    workRegister.setRegDay("14");
                                    if ("上班".equals(row.getCell(29).toString()) && "上班".equals(row.getCell(30).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(29).toString()) && !"上班".equals(row.getCell(30).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(29).toString()) && "上班".equals(row.getCell(30).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year14 + "-" + zhMonth14 + "-" + "14");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(29).toString(), row.getCell(30).toString());
                                }
                                //15号
                                Map<String, Object> selectMap15 = new HashMap<String, Object>();
                                selectMap15.put("personId", wp.getAccount());
                                selectMap15.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap15.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap15.put("regDay", "15");
                                List<WorkRegister> workRegisters15 = workRegisterMapper.selectByDay(selectMap15);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters15.size() > 0) {
                                    workRegisters15.get(0).setMorning(row.getCell(31).toString());
                                    workRegisters15.get(0).setAfternoon(row.getCell(32).toString());
                                    if ("上班".equals(row.getCell(31).toString()) && "上班".equals(row.getCell(32).toString())) {
                                        workRegisters15.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(31).toString()) && !"上班".equals(row.getCell(32).toString())) {
                                        workRegisters15.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(31).toString()) && "上班".equals(row.getCell(32).toString())) {
                                        workRegisters15.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters15.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters15.get(0).setCreater(user.getName());
                                    workRegisters15.get(0).setUpdateTime(date);
                                    workRegisters15.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters15.get(0));
                                    setVa(wp, row.getCell(31).toString(), row.getCell(32).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id15 = UUID.randomUUID().toString().replace("-", "");
                                    String year15 = row.getCell(0).toString().replace(".0", "");
                                    String month15 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth15 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth15.length() == 1) {
                                        zhMonth15 = "0" + zhMonth15;
                                    }
                                    workRegister.setId(id15);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year15 + month15);
                                    workRegister.setRegYear(year15);
                                    workRegister.setRegMonth(month15);
                                    workRegister.setMorning(row.getCell(31).toString());
                                    workRegister.setAfternoon(row.getCell(32).toString());
                                    workRegister.setRegDay("15");
                                    if ("上班".equals(row.getCell(31).toString()) && "上班".equals(row.getCell(32).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(31).toString()) && !"上班".equals(row.getCell(32).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(31).toString()) && "上班".equals(row.getCell(32).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year15 + "-" + zhMonth15 + "-" + "15");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(31).toString(), row.getCell(32).toString());
                                }
                                //16号
                                Map<String, Object> selectMap16 = new HashMap<String, Object>();
                                selectMap16.put("personId", wp.getAccount());
                                selectMap16.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap16.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap16.put("regDay", "16");
                                List<WorkRegister> workRegisters16 = workRegisterMapper.selectByDay(selectMap16);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters16.size() > 0) {
                                    workRegisters16.get(0).setMorning(row.getCell(33).toString());
                                    workRegisters16.get(0).setAfternoon(row.getCell(34).toString());
                                    if ("上班".equals(row.getCell(33).toString()) && "上班".equals(row.getCell(34).toString())) {
                                        workRegisters16.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(33).toString()) && !"上班".equals(row.getCell(34).toString())) {
                                        workRegisters16.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(33).toString()) && "上班".equals(row.getCell(34).toString())) {
                                        workRegisters16.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters16.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters16.get(0).setCreater(user.getName());
                                    workRegisters16.get(0).setUpdateTime(date);
                                    workRegisters16.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters16.get(0));
                                    setVa(wp, row.getCell(33).toString(), row.getCell(34).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id16 = UUID.randomUUID().toString().replace("-", "");
                                    String year16 = row.getCell(0).toString().replace(".0", "");
                                    String month16 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth16 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth16.length() == 1) {
                                        zhMonth16 = "0" + zhMonth16;
                                    }
                                    workRegister.setId(id16);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year16 + month16);
                                    workRegister.setRegYear(year16);
                                    workRegister.setRegMonth(month16);
                                    workRegister.setMorning(row.getCell(33).toString());
                                    workRegister.setAfternoon(row.getCell(34).toString());
                                    workRegister.setRegDay("16");
                                    if ("上班".equals(row.getCell(33).toString()) && "上班".equals(row.getCell(34).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(33).toString()) && !"上班".equals(row.getCell(34).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(33).toString()) && "上班".equals(row.getCell(34).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year16 + "-" + zhMonth16 + "-" + "16");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(33).toString(), row.getCell(34).toString());
                                }
                                //17号
                                Map<String, Object> selectMap17 = new HashMap<String, Object>();
                                selectMap17.put("personId", wp.getAccount());
                                selectMap17.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap17.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap17.put("regDay", "17");
                                List<WorkRegister> workRegisters17 = workRegisterMapper.selectByDay(selectMap17);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters17.size() > 0) {
                                    workRegisters17.get(0).setMorning(row.getCell(35).toString());
                                    workRegisters17.get(0).setAfternoon(row.getCell(36).toString());
                                    if ("上班".equals(row.getCell(35).toString()) && "上班".equals(row.getCell(36).toString())) {
                                        workRegisters17.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(35).toString()) && !"上班".equals(row.getCell(36).toString())) {
                                        workRegisters17.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(35).toString()) && "上班".equals(row.getCell(36).toString())) {
                                        workRegisters17.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters17.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters17.get(0).setCreater(user.getName());
                                    workRegisters17.get(0).setUpdateTime(date);
                                    workRegisters17.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters17.get(0));
                                    setVa(wp, row.getCell(35).toString(), row.getCell(36).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id17 = UUID.randomUUID().toString().replace("-", "");
                                    String year17 = row.getCell(0).toString().replace(".0", "");
                                    String month17 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth17 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth17.length() == 1) {
                                        zhMonth17 = "0" + zhMonth17;
                                    }
                                    workRegister.setId(id17);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year17 + month17);
                                    workRegister.setRegYear(year17);
                                    workRegister.setRegMonth(month17);
                                    workRegister.setMorning(row.getCell(35).toString());
                                    workRegister.setAfternoon(row.getCell(36).toString());
                                    workRegister.setRegDay("17");
                                    if ("上班".equals(row.getCell(35).toString()) && "上班".equals(row.getCell(36).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(35).toString()) && !"上班".equals(row.getCell(36).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(35).toString()) && "上班".equals(row.getCell(36).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year17 + "-" + zhMonth17 + "-" + "17");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(35).toString(), row.getCell(36).toString());
                                }
                                //18号
                                Map<String, Object> selectMap18 = new HashMap<String, Object>();
                                selectMap18.put("personId", wp.getAccount());
                                selectMap18.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap18.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap18.put("regDay", "18");
                                List<WorkRegister> workRegisters18 = workRegisterMapper.selectByDay(selectMap18);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters18.size() > 0) {
                                    workRegisters18.get(0).setMorning(row.getCell(37).toString());
                                    workRegisters18.get(0).setAfternoon(row.getCell(38).toString());
                                    if ("上班".equals(row.getCell(37).toString()) && "上班".equals(row.getCell(38).toString())) {
                                        workRegisters18.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(37).toString()) && !"上班".equals(row.getCell(38).toString())) {
                                        workRegisters18.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(37).toString()) && "上班".equals(row.getCell(38).toString())) {
                                        workRegisters18.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters18.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters18.get(0).setCreater(user.getName());
                                    workRegisters18.get(0).setUpdateTime(date);
                                    workRegisters18.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters18.get(0));
                                    setVa(wp, row.getCell(37).toString(), row.getCell(38).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id18 = UUID.randomUUID().toString().replace("-", "");
                                    String year18 = row.getCell(0).toString().replace(".0", "");
                                    String month18 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth18 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth18.length() == 1) {
                                        zhMonth18 = "0" + zhMonth18;
                                    }
                                    workRegister.setId(id18);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year18 + month18);
                                    workRegister.setRegYear(year18);
                                    workRegister.setRegMonth(month18);
                                    workRegister.setMorning(row.getCell(37).toString());
                                    workRegister.setAfternoon(row.getCell(38).toString());
                                    workRegister.setRegDay("18");
                                    if ("上班".equals(row.getCell(37).toString()) && "上班".equals(row.getCell(38).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(37).toString()) && !"上班".equals(row.getCell(38).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(37).toString()) && "上班".equals(row.getCell(38).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year18 + "-" + zhMonth18 + "-" + "18");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(37).toString(), row.getCell(38).toString());
                                }
                                //19号
                                Map<String, Object> selectMap19 = new HashMap<String, Object>();
                                selectMap19.put("personId", wp.getAccount());
                                selectMap19.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap19.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap19.put("regDay", "19");
                                List<WorkRegister> workRegisters19 = workRegisterMapper.selectByDay(selectMap19);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters19.size() > 0) {
                                    workRegisters19.get(0).setMorning(row.getCell(39).toString());
                                    workRegisters19.get(0).setAfternoon(row.getCell(40).toString());
                                    if ("上班".equals(row.getCell(39).toString()) && "上班".equals(row.getCell(40).toString())) {
                                        workRegisters19.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(39).toString()) && !"上班".equals(row.getCell(40).toString())) {
                                        workRegisters19.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(39).toString()) && "上班".equals(row.getCell(40).toString())) {
                                        workRegisters19.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters19.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters19.get(0).setCreater(user.getName());
                                    workRegisters19.get(0).setUpdateTime(date);
                                    workRegisters19.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters19.get(0));
                                    setVa(wp, row.getCell(39).toString(), row.getCell(40).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id19 = UUID.randomUUID().toString().replace("-", "");
                                    String year19 = row.getCell(0).toString().replace(".0", "");
                                    String month19 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth19 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth19.length() == 1) {
                                        zhMonth19 = "0" + zhMonth19;
                                    }
                                    workRegister.setId(id19);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year19 + month19);
                                    workRegister.setRegYear(year19);
                                    workRegister.setRegMonth(month19);
                                    workRegister.setMorning(row.getCell(39).toString());
                                    workRegister.setAfternoon(row.getCell(40).toString());
                                    workRegister.setRegDay("19");
                                    if ("上班".equals(row.getCell(39).toString()) && "上班".equals(row.getCell(40).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(39).toString()) && !"上班".equals(row.getCell(40).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(39).toString()) && "上班".equals(row.getCell(40).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year19 + "-" + zhMonth19 + "-" + "19");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(39).toString(), row.getCell(40).toString());
                                }
                                //20号
                                Map<String, Object> selectMap20 = new HashMap<String, Object>();
                                selectMap20.put("personId", wp.getAccount());
                                selectMap20.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap20.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap20.put("regDay", "20");
                                List<WorkRegister> workRegisters20 = workRegisterMapper.selectByDay(selectMap20);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters20.size() > 0) {
                                    workRegisters20.get(0).setMorning(row.getCell(41).toString());
                                    workRegisters20.get(0).setAfternoon(row.getCell(42).toString());
                                    if ("上班".equals(row.getCell(41).toString()) && "上班".equals(row.getCell(42).toString())) {
                                        workRegisters20.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(41).toString()) && !"上班".equals(row.getCell(42).toString())) {
                                        workRegisters20.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(41).toString()) && "上班".equals(row.getCell(42).toString())) {
                                        workRegisters20.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters20.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters20.get(0).setCreater(user.getName());
                                    workRegisters20.get(0).setUpdateTime(date);
                                    workRegisters20.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters20.get(0));
                                    setVa(wp, row.getCell(41).toString(), row.getCell(42).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id20 = UUID.randomUUID().toString().replace("-", "");
                                    String year20 = row.getCell(0).toString().replace(".0", "");
                                    String month20 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth20 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth20.length() == 1) {
                                        zhMonth20 = "0" + zhMonth20;
                                    }
                                    workRegister.setId(id20);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year20 + month20);
                                    workRegister.setRegYear(year20);
                                    workRegister.setRegMonth(month20);
                                    workRegister.setMorning(row.getCell(41).toString());
                                    workRegister.setAfternoon(row.getCell(42).toString());
                                    workRegister.setRegDay("20");
                                    if ("上班".equals(row.getCell(41).toString()) && "上班".equals(row.getCell(42).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(41).toString()) && !"上班".equals(row.getCell(42).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(41).toString()) && "上班".equals(row.getCell(42).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year20 + "-" + zhMonth20 + "-" + "20");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(41).toString(), row.getCell(42).toString());
                                }
                                //21号
                                Map<String, Object> selectMap21 = new HashMap<String, Object>();
                                selectMap21.put("personId", wp.getAccount());
                                selectMap21.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap21.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap21.put("regDay", "21");
                                List<WorkRegister> workRegisters21 = workRegisterMapper.selectByDay(selectMap21);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters21.size() > 0) {
                                    workRegisters21.get(0).setMorning(row.getCell(43).toString());
                                    workRegisters21.get(0).setAfternoon(row.getCell(44).toString());
                                    if ("上班".equals(row.getCell(43).toString()) && "上班".equals(row.getCell(44).toString())) {
                                        workRegisters21.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(43).toString()) && !"上班".equals(row.getCell(44).toString())) {
                                        workRegisters21.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(43).toString()) && "上班".equals(row.getCell(44).toString())) {
                                        workRegisters21.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters21.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters21.get(0).setCreater(user.getName());
                                    workRegisters21.get(0).setUpdateTime(date);
                                    workRegisters21.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters21.get(0));
                                    setVa(wp, row.getCell(43).toString(), row.getCell(44).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id21 = UUID.randomUUID().toString().replace("-", "");
                                    String year21 = row.getCell(0).toString().replace(".0", "");
                                    String month21 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth21 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth21.length() == 1) {
                                        zhMonth21 = "0" + zhMonth21;
                                    }
                                    workRegister.setId(id21);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year21 + month21);
                                    workRegister.setRegYear(year21);
                                    workRegister.setRegMonth(month21);
                                    workRegister.setMorning(row.getCell(43).toString());
                                    workRegister.setAfternoon(row.getCell(44).toString());
                                    workRegister.setRegDay("21");
                                    if ("上班".equals(row.getCell(43).toString()) && "上班".equals(row.getCell(44).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(43).toString()) && !"上班".equals(row.getCell(44).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(43).toString()) && "上班".equals(row.getCell(44).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year21 + "-" + zhMonth21 + "-" + "21");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(43).toString(), row.getCell(44).toString());
                                }
                                //22号
                                Map<String, Object> selectMap22 = new HashMap<String, Object>();
                                selectMap22.put("personId", wp.getAccount());
                                selectMap22.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap22.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap22.put("regDay", "22");
                                List<WorkRegister> workRegisters22 = workRegisterMapper.selectByDay(selectMap22);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters22.size() > 0) {
                                    workRegisters22.get(0).setMorning(row.getCell(45).toString());
                                    workRegisters22.get(0).setAfternoon(row.getCell(46).toString());
                                    if ("上班".equals(row.getCell(45).toString()) && "上班".equals(row.getCell(46).toString())) {
                                        workRegisters22.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(45).toString()) && !"上班".equals(row.getCell(46).toString())) {
                                        workRegisters22.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(45).toString()) && "上班".equals(row.getCell(46).toString())) {
                                        workRegisters22.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters22.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters22.get(0).setCreater(user.getName());
                                    workRegisters22.get(0).setUpdateTime(date);
                                    workRegisters22.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters22.get(0));
                                    setVa(wp, row.getCell(45).toString(), row.getCell(46).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id22 = UUID.randomUUID().toString().replace("-", "");
                                    String year22 = row.getCell(0).toString().replace(".0", "");
                                    String month22 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth22 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth22.length() == 1) {
                                        zhMonth22 = "0" + zhMonth22;
                                    }
                                    workRegister.setId(id22);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year22 + month22);
                                    workRegister.setRegYear(year22);
                                    workRegister.setRegMonth(month22);
                                    workRegister.setMorning(row.getCell(45).toString());
                                    workRegister.setAfternoon(row.getCell(46).toString());
                                    workRegister.setRegDay("22");
                                    if ("上班".equals(row.getCell(45).toString()) && "上班".equals(row.getCell(46).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(45).toString()) && !"上班".equals(row.getCell(46).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(45).toString()) && "上班".equals(row.getCell(46).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year22 + "-" + zhMonth22 + "-" + "22");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(45).toString(), row.getCell(46).toString());
                                }
                                //23号
                                Map<String, Object> selectMap23 = new HashMap<String, Object>();
                                selectMap23.put("personId", wp.getAccount());
                                selectMap23.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap23.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap23.put("regDay", "23");
                                List<WorkRegister> workRegisters23 = workRegisterMapper.selectByDay(selectMap23);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters23.size() > 0) {
                                    workRegisters23.get(0).setMorning(row.getCell(47).toString());
                                    workRegisters23.get(0).setAfternoon(row.getCell(48).toString());
                                    if ("上班".equals(row.getCell(47).toString()) && "上班".equals(row.getCell(48).toString())) {
                                        workRegisters23.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(47).toString()) && !"上班".equals(row.getCell(48).toString())) {
                                        workRegisters23.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(47).toString()) && "上班".equals(row.getCell(48).toString())) {
                                        workRegisters23.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters23.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters23.get(0).setCreater(user.getName());
                                    workRegisters23.get(0).setUpdateTime(date);
                                    workRegisters23.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters23.get(0));
                                    setVa(wp, row.getCell(47).toString(), row.getCell(48).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id23 = UUID.randomUUID().toString().replace("-", "");
                                    String year23 = row.getCell(0).toString().replace(".0", "");
                                    String month23 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth23 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth23.length() == 1) {
                                        zhMonth23 = "0" + zhMonth23;
                                    }
                                    workRegister.setId(id23);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year23 + month23);
                                    workRegister.setRegYear(year23);
                                    workRegister.setRegMonth(month23);
                                    workRegister.setMorning(row.getCell(47).toString());
                                    workRegister.setAfternoon(row.getCell(48).toString());
                                    workRegister.setRegDay("23");
                                    if ("上班".equals(row.getCell(47).toString()) && "上班".equals(row.getCell(48).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(47).toString()) && !"上班".equals(row.getCell(48).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(47).toString()) && "上班".equals(row.getCell(48).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year23 + "-" + zhMonth23 + "-" + "23");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(47).toString(), row.getCell(48).toString());
                                }
                                //24号
                                Map<String, Object> selectMap24 = new HashMap<String, Object>();
                                selectMap24.put("personId", wp.getAccount());
                                selectMap24.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap24.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap24.put("regDay", "24");
                                List<WorkRegister> workRegisters24 = workRegisterMapper.selectByDay(selectMap24);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters24.size() > 0) {
                                    workRegisters24.get(0).setMorning(row.getCell(49).toString());
                                    workRegisters24.get(0).setAfternoon(row.getCell(50).toString());
                                    if ("上班".equals(row.getCell(49).toString()) && "上班".equals(row.getCell(50).toString())) {
                                        workRegisters24.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(49).toString()) && !"上班".equals(row.getCell(50).toString())) {
                                        workRegisters24.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(49).toString()) && "上班".equals(row.getCell(50).toString())) {
                                        workRegisters24.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters24.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters24.get(0).setCreater(user.getName());
                                    workRegisters24.get(0).setUpdateTime(date);
                                    workRegisters24.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters24.get(0));
                                    setVa(wp, row.getCell(49).toString(), row.getCell(50).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id24 = UUID.randomUUID().toString().replace("-", "");
                                    String year24 = row.getCell(0).toString().replace(".0", "");
                                    String month24 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth24 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth24.length() == 1) {
                                        zhMonth24 = "0" + zhMonth24;
                                    }
                                    workRegister.setId(id24);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year24 + month24);
                                    workRegister.setRegYear(year24);
                                    workRegister.setRegMonth(month24);
                                    workRegister.setMorning(row.getCell(49).toString());
                                    workRegister.setAfternoon(row.getCell(50).toString());
                                    workRegister.setRegDay("24");
                                    if ("上班".equals(row.getCell(49).toString()) && "上班".equals(row.getCell(50).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(49).toString()) && !"上班".equals(row.getCell(50).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(49).toString()) && "上班".equals(row.getCell(50).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year24 + "-" + zhMonth24 + "-" + "24");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(49).toString(), row.getCell(50).toString());
                                }
                                //25号
                                Map<String, Object> selectMap25 = new HashMap<String, Object>();
                                selectMap25.put("personId", wp.getAccount());
                                selectMap25.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap25.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap25.put("regDay", "25");
                                List<WorkRegister> workRegisters25 = workRegisterMapper.selectByDay(selectMap25);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters25.size() > 0) {
                                    workRegisters25.get(0).setMorning(row.getCell(51).toString());
                                    workRegisters25.get(0).setAfternoon(row.getCell(52).toString());
                                    if ("上班".equals(row.getCell(51).toString()) && "上班".equals(row.getCell(52).toString())) {
                                        workRegisters25.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(51).toString()) && !"上班".equals(row.getCell(52).toString())) {
                                        workRegisters25.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(51).toString()) && "上班".equals(row.getCell(52).toString())) {
                                        workRegisters25.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters25.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters25.get(0).setCreater(user.getName());
                                    workRegisters25.get(0).setUpdateTime(date);
                                    workRegisters25.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters25.get(0));
                                    setVa(wp, row.getCell(51).toString(), row.getCell(52).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id25 = UUID.randomUUID().toString().replace("-", "");
                                    String year25 = row.getCell(0).toString().replace(".0", "");
                                    String month25 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth25 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth25.length() == 1) {
                                        zhMonth25 = "0" + zhMonth25;
                                    }
                                    workRegister.setId(id25);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year25 + month25);
                                    workRegister.setRegYear(year25);
                                    workRegister.setRegMonth(month25);
                                    workRegister.setMorning(row.getCell(51).toString());
                                    workRegister.setAfternoon(row.getCell(52).toString());
                                    workRegister.setRegDay("25");
                                    if ("上班".equals(row.getCell(51).toString()) && "上班".equals(row.getCell(52).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(51).toString()) && !"上班".equals(row.getCell(52).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(51).toString()) && "上班".equals(row.getCell(52).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year25 + "-" + zhMonth25 + "-" + "25");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(51).toString(), row.getCell(52).toString());
                                }
                                //26号
                                Map<String, Object> selectMap26 = new HashMap<String, Object>();
                                selectMap26.put("personId", wp.getAccount());
                                selectMap26.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap26.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap26.put("regDay", "26");
                                List<WorkRegister> workRegisters26 = workRegisterMapper.selectByDay(selectMap26);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters26.size() > 0) {
                                    workRegisters26.get(0).setMorning(row.getCell(53).toString());
                                    workRegisters26.get(0).setAfternoon(row.getCell(54).toString());
                                    if ("上班".equals(row.getCell(53).toString()) && "上班".equals(row.getCell(54).toString())) {
                                        workRegisters26.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(53).toString()) && !"上班".equals(row.getCell(54).toString())) {
                                        workRegisters26.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(53).toString()) && "上班".equals(row.getCell(54).toString())) {
                                        workRegisters26.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters26.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters26.get(0).setCreater(user.getName());
                                    workRegisters26.get(0).setUpdateTime(date);
                                    workRegisters26.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters26.get(0));
                                    setVa(wp, row.getCell(53).toString(), row.getCell(54).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id26 = UUID.randomUUID().toString().replace("-", "");
                                    String year26 = row.getCell(0).toString().replace(".0", "");
                                    String month26 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth26 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth26.length() == 1) {
                                        zhMonth26 = "0" + zhMonth26;
                                    }
                                    workRegister.setId(id26);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year26 + month26);
                                    workRegister.setRegYear(year26);
                                    workRegister.setRegMonth(month26);
                                    workRegister.setMorning(row.getCell(53).toString());
                                    workRegister.setAfternoon(row.getCell(54).toString());
                                    workRegister.setRegDay("26");
                                    if ("上班".equals(row.getCell(53).toString()) && "上班".equals(row.getCell(54).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(53).toString()) && !"上班".equals(row.getCell(54).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(53).toString()) && "上班".equals(row.getCell(54).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year26 + "-" + zhMonth26 + "-" + "26");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(53).toString(), row.getCell(54).toString());
                                }
                                //27号
                                Map<String, Object> selectMap27 = new HashMap<String, Object>();
                                selectMap27.put("personId", wp.getAccount());
                                selectMap27.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap27.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap27.put("regDay", "27");
                                List<WorkRegister> workRegisters27 = workRegisterMapper.selectByDay(selectMap27);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters27.size() > 0) {
                                    workRegisters27.get(0).setMorning(row.getCell(55).toString());
                                    workRegisters27.get(0).setAfternoon(row.getCell(56).toString());
                                    if ("上班".equals(row.getCell(55).toString()) && "上班".equals(row.getCell(56).toString())) {
                                        workRegisters27.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(55).toString()) && !"上班".equals(row.getCell(56).toString())) {
                                        workRegisters27.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(55).toString()) && "上班".equals(row.getCell(56).toString())) {
                                        workRegisters27.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters27.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters27.get(0).setCreater(user.getName());
                                    workRegisters27.get(0).setUpdateTime(date);
                                    workRegisters27.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters27.get(0));
                                    setVa(wp, row.getCell(55).toString(), row.getCell(56).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id27 = UUID.randomUUID().toString().replace("-", "");
                                    String year27 = row.getCell(0).toString().replace(".0", "");
                                    String month27 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth27 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth27.length() == 1) {
                                        zhMonth27 = "0" + zhMonth27;
                                    }
                                    workRegister.setId(id27);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year27 + month27);
                                    workRegister.setRegYear(year27);
                                    workRegister.setRegMonth(month27);
                                    workRegister.setMorning(row.getCell(55).toString());
                                    workRegister.setAfternoon(row.getCell(56).toString());
                                    workRegister.setRegDay("27");
                                    if ("上班".equals(row.getCell(55).toString()) && "上班".equals(row.getCell(56).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(55).toString()) && !"上班".equals(row.getCell(56).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(55).toString()) && "上班".equals(row.getCell(56).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year27 + "-" + zhMonth27 + "-" + "27");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(55).toString(), row.getCell(56).toString());
                                }
                                //28号
                                Map<String, Object> selectMap28 = new HashMap<String, Object>();
                                selectMap28.put("personId", wp.getAccount());
                                selectMap28.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap28.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap28.put("regDay", "28");
                                List<WorkRegister> workRegisters28 = workRegisterMapper.selectByDay(selectMap28);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters28.size() > 0) {
                                    workRegisters28.get(0).setMorning(row.getCell(57).toString());
                                    workRegisters28.get(0).setAfternoon(row.getCell(58).toString());
                                    if ("上班".equals(row.getCell(57).toString()) && "上班".equals(row.getCell(58).toString())) {
                                        workRegisters28.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(57).toString()) && !"上班".equals(row.getCell(58).toString())) {
                                        workRegisters28.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(57).toString()) && "上班".equals(row.getCell(58).toString())) {
                                        workRegisters28.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters28.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters28.get(0).setCreater(user.getName());
                                    workRegisters28.get(0).setUpdateTime(date);
                                    workRegisters28.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters28.get(0));
                                    setVa(wp, row.getCell(57).toString(), row.getCell(58).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id28 = UUID.randomUUID().toString().replace("-", "");
                                    String year28 = row.getCell(0).toString().replace(".0", "");
                                    String month28 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth28 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth28.length() == 1) {
                                        zhMonth28 = "0" + zhMonth28;
                                    }
                                    workRegister.setId(id28);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year28 + month28);
                                    workRegister.setRegYear(year28);
                                    workRegister.setRegMonth(month28);
                                    workRegister.setMorning(row.getCell(57).toString());
                                    workRegister.setAfternoon(row.getCell(58).toString());
                                    workRegister.setRegDay("28");
                                    if ("上班".equals(row.getCell(57).toString()) && "上班".equals(row.getCell(58).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(57).toString()) && !"上班".equals(row.getCell(58).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(57).toString()) && "上班".equals(row.getCell(58).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year28 + "-" + zhMonth28 + "-" + "28");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(57).toString(), row.getCell(58).toString());
                                }
                                //29号
                                Map<String, Object> selectMap29 = new HashMap<String, Object>();
                                selectMap29.put("personId", wp.getAccount());
                                selectMap29.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap29.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap29.put("regDay", "29");
                                List<WorkRegister> workRegisters29 = workRegisterMapper.selectByDay(selectMap29);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters29.size() > 0) {
                                    workRegisters29.get(0).setMorning(row.getCell(59).toString());
                                    workRegisters29.get(0).setAfternoon(row.getCell(60).toString());
                                    if ("上班".equals(row.getCell(59).toString()) && "上班".equals(row.getCell(60).toString())) {
                                        workRegisters29.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(59).toString()) && !"上班".equals(row.getCell(60).toString())) {
                                        workRegisters29.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(59).toString()) && "上班".equals(row.getCell(60).toString())) {
                                        workRegisters29.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters29.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters29.get(0).setCreater(user.getName());
                                    workRegisters29.get(0).setUpdateTime(date);
                                    workRegisters29.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters29.get(0));
                                    setVa(wp, row.getCell(59).toString(), row.getCell(60).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id29 = UUID.randomUUID().toString().replace("-", "");
                                    String year29 = row.getCell(0).toString().replace(".0", "");
                                    String month29 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth29 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth29.length() == 1) {
                                        zhMonth29 = "0" + zhMonth29;
                                    }
                                    workRegister.setId(id29);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year29 + month29);
                                    workRegister.setRegYear(year29);
                                    workRegister.setRegMonth(month29);
                                    workRegister.setMorning(row.getCell(59).toString());
                                    workRegister.setAfternoon(row.getCell(60).toString());
                                    workRegister.setRegDay("29");
                                    if ("上班".equals(row.getCell(59).toString()) && "上班".equals(row.getCell(60).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(59).toString()) && !"上班".equals(row.getCell(60).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(59).toString()) && "上班".equals(row.getCell(60).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year29 + "-" + zhMonth29 + "-" + "29");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(59).toString(), row.getCell(60).toString());
                                }
                                //30号
                                Map<String, Object> selectMap30 = new HashMap<String, Object>();
                                selectMap30.put("personId", wp.getAccount());
                                selectMap30.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                selectMap30.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                selectMap30.put("regDay", "30");
                                List<WorkRegister> workRegisters30 = workRegisterMapper.selectByDay(selectMap30);
                                //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                if (workRegisters30.size() > 0) {
                                    workRegisters30.get(0).setMorning(row.getCell(61).toString());
                                    workRegisters30.get(0).setAfternoon(row.getCell(62).toString());
                                    if ("上班".equals(row.getCell(61).toString()) && "上班".equals(row.getCell(62).toString())) {
                                        workRegisters30.get(0).setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(61).toString()) && !"上班".equals(row.getCell(62).toString())) {
                                        workRegisters30.get(0).setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(61).toString()) && "上班".equals(row.getCell(62).toString())) {
                                        workRegisters30.get(0).setWorkingDay(0.5f);
                                    } else {
                                        workRegisters30.get(0).setWorkingDay(0.0f);
                                    }
                                    workRegisters30.get(0).setCreater(user.getName());
                                    workRegisters30.get(0).setUpdateTime(date);
                                    workRegisters30.get(0).setRemark("");
                                    workRegisterMapper.updateByPrimaryKeySelective(workRegisters30.get(0));
                                    setVa(wp, row.getCell(61).toString(), row.getCell(62).toString());
                                } else {
                                    //否则新增
                                    workRegister = new WorkRegister();
                                    String id30 = UUID.randomUUID().toString().replace("-", "");
                                    String year30 = row.getCell(0).toString().replace(".0", "");
                                    String month30 = row.getCell(1).toString().replace(".0", "");
                                    String zhMonth30 = row.getCell(1).toString().replace(".0", "");
                                    if (zhMonth30.length() == 1) {
                                        zhMonth30 = "0" + zhMonth30;
                                    }
                                    workRegister.setId(id30);
                                    workRegister.setPersonId(wp.getAccount());
                                    workRegister.setEnterpriseId(wp.getEnterpriseId());
                                    workRegister.setRegYearMonth(year30 + month30);
                                    workRegister.setRegYear(year30);
                                    workRegister.setRegMonth(month30);
                                    workRegister.setMorning(row.getCell(61).toString());
                                    workRegister.setAfternoon(row.getCell(62).toString());
                                    workRegister.setRegDay("30");
                                    if ("上班".equals(row.getCell(61).toString()) && "上班".equals(row.getCell(62).toString())) {
                                        workRegister.setWorkingDay(1.0f);
                                    } else if ("上班".equals(row.getCell(61).toString()) && !"上班".equals(row.getCell(62).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else if (!"上班".equals(row.getCell(61).toString()) && "上班".equals(row.getCell(62).toString())) {
                                        workRegister.setWorkingDay(0.5f);
                                    } else {
                                        workRegister.setWorkingDay(0.0f);
                                    }
                                    workRegister.setCreater(user.getName());
                                    workRegister.setCreateTime(date);
                                    Date regDateStr = sdf.parse(year30 + "-" + zhMonth30 + "-" + "30");
                                    workRegister.setRegisterTime(regDateStr);
                                    workRegisterMapper.insertSelective(workRegister);
                                    setVa(wp, row.getCell(61).toString(), row.getCell(62).toString());
                                }
                                //如果31号无考勤情况，则不插入数据
                                if ("".equals(row.getCell(63).toString()) && "".equals(row.getCell(64).toString())) {
                                } else {
                                    Map<String, Object> selectMap31 = new HashMap<String, Object>();
                                    selectMap31.put("personId", wp.getAccount());
                                    selectMap31.put("regYear", row.getCell(0).toString().replace(".0", ""));
                                    selectMap31.put("regMonth", row.getCell(1).toString().replace(".0", ""));
                                    selectMap31.put("regDay", "31");
                                    List<WorkRegister> workRegisters31 = workRegisterMapper.selectByDay(selectMap31);
                                    //如果登记表有相同一个人的数据，则按照excel表的数据更新数据库
                                    if (workRegisters31.size() > 0) {
                                        workRegisters31.get(0).setMorning(row.getCell(63).toString());
                                        workRegisters31.get(0).setAfternoon(row.getCell(64).toString());
                                        if ("上班".equals(row.getCell(63).toString()) && "上班".equals(row.getCell(64).toString())) {
                                            workRegisters31.get(0).setWorkingDay(1.0f);
                                        } else if ("上班".equals(row.getCell(63).toString()) && !"上班".equals(row.getCell(64).toString())) {
                                            workRegisters31.get(0).setWorkingDay(0.5f);
                                        } else if (!"上班".equals(row.getCell(63).toString()) && "上班".equals(row.getCell(64).toString())) {
                                            workRegisters31.get(0).setWorkingDay(0.5f);
                                        } else {
                                            workRegisters31.get(0).setWorkingDay(0.0f);
                                        }
                                        workRegisters31.get(0).setCreater(user.getName());
                                        workRegisters31.get(0).setUpdateTime(date);
                                        workRegisters31.get(0).setRemark("");
                                        workRegisterMapper.updateByPrimaryKeySelective(workRegisters31.get(0));
                                        setVa(wp, row.getCell(63).toString(), row.getCell(64).toString());
                                    } else {
                                        //否则新增
                                        workRegister = new WorkRegister();
                                        String id31 = UUID.randomUUID().toString().replace("-", "");
                                        String year31 = row.getCell(0).toString().replace(".0", "");
                                        String month31 = row.getCell(1).toString().replace(".0", "");
                                        String zhMonth31 = row.getCell(1).toString().replace(".0", "");
                                        if (zhMonth31.length() == 1) {
                                            zhMonth31 = "0" + zhMonth31;
                                        }
                                        workRegister.setId(id31);
                                        workRegister.setPersonId(wp.getAccount());
                                        workRegister.setEnterpriseId(wp.getEnterpriseId());
                                        workRegister.setRegYearMonth(year31 + month31);
                                        workRegister.setRegYear(year31);
                                        workRegister.setRegMonth(month31);
                                        workRegister.setMorning(row.getCell(63).toString());
                                        workRegister.setAfternoon(row.getCell(64).toString());
                                        workRegister.setRegDay("31");
                                        if ("上班".equals(row.getCell(63).toString()) && "上班".equals(row.getCell(64).toString())) {
                                            workRegister.setWorkingDay(1.0f);
                                        } else if ("上班".equals(row.getCell(63).toString()) && !"上班".equals(row.getCell(64).toString())) {
                                            workRegister.setWorkingDay(0.5f);
                                        } else if (!"上班".equals(row.getCell(63).toString()) && "上班".equals(row.getCell(64).toString())) {
                                            workRegister.setWorkingDay(0.5f);
                                        } else {
                                            workRegister.setWorkingDay(0.0f);
                                        }
                                        workRegister.setCreater(user.getName());
                                        workRegister.setCreateTime(date);
                                        Date regDateStr = sdf.parse(year31 + "-" + zhMonth31 + "-" + "31");
                                        workRegister.setRegisterTime(regDateStr);
                                        workRegisterMapper.insertSelective(workRegister);
                                        setVa(wp, row.getCell(63).toString(), row.getCell(64).toString());
                                    }
                                }
                                result.setStatus(200);
                            } else {
                                System.out.println("找不到该驻场人员!");
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateByLeaveprocess(WorkRegister record) {
        // TODO Auto-generated method stub
        return workRegisterMapper.updateByLeaveprocess(record);
    }

}
