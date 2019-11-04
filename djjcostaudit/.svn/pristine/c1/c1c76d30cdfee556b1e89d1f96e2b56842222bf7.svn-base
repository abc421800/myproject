package com.cost168.costaudit.controller.yaohao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.utils.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.yaohao.YaohaoPunish;
import com.cost168.costaudit.service.cost.CostEnterpriseRecordService;
import com.cost168.costaudit.service.cost.CostEnterpriseService;
import com.cost168.costaudit.service.yaohao.YaohaoPunishService;

@Controller
@RequestMapping("/yaohaoPunish")
public class YaohaoPunishController {

	@Autowired
	private YaohaoPunishService yaohaoPunishService;
	@Autowired
	private CostEnterpriseRecordService costEnterpriseRecordService;
	@Autowired
	private CostEnterpriseService costEnterpriseService;

	private final Logger logger = LogManager.getLogger(YaohaoPunishController.class);



	@RequestMapping("/toPunishList")
	public String toEnterpriseList(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        String currentYear = currentDate.substring(0, 4);
        String entYear="";
		String year=costEnterpriseRecordService.selectYearList();
		if(null!=year && !"".equals(year)){
			entYear=year;
		}else{
			entYear=currentYear;
		}
		request.setAttribute("entYear", entYear);
		request.setAttribute("currentYear", currentYear);
		return "yaohao/punish/punishList";
	}

	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,YaohaoPunish yaohaoPunish, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoPunish);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    map.putAll(selectMap);
		    List<YaohaoPunish> list= yaohaoPunishService.selectListByMap(map);
		    int total= yaohaoPunishService.selectCountByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/listTz")
	public EUDataGridResult listTz(HttpServletRequest request,YaohaoPunish yaohaoPunish, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoPunish);
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    List<YaohaoPunish> list= yaohaoPunishService.selectListTzByMap(map);
		    int total= yaohaoPunishService.selectCountTzByMap(map);
		    map.put("isPage",false);

			Global.PUNISH_EXPORT_LIST=yaohaoPunishService.selectListTzByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		String url="";
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)) {
			url="/yaohaoPunish/upd";
			YaohaoPunish yaohaoPunish = yaohaoPunishService.selectByPrimaryKey(id);
			CostEnterprise ent=costEnterpriseService.selectByPrimaryKey(yaohaoPunish.getEnterpriseCode());
			if(null!=ent){
				yaohaoPunish.setEnterpriseId(ent.getId());
				yaohaoPunish.setEnterpriseName(ent.getName());
			}
			request.setAttribute("obj", yaohaoPunish);
		}else{
			url="/yaohaoPunish/save";
			YaohaoPunish yaohaoPunish=new YaohaoPunish();
			yaohaoPunish.setId(OrderUtil.buildOrderId(""));
			request.setAttribute("obj", yaohaoPunish);
		}
		request.setAttribute("url", url);
		return "yaohao/punish/add";
	}

	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,YaohaoPunish yaohaoPunish){
		GlobalResult result=new GlobalResult();
		try {
			int tepm=yaohaoPunishService.save(request,yaohaoPunish);
			if(tepm<=0){
				logger.error("插入异常!");
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,YaohaoPunish yaohaoPunish){
		GlobalResult result=new GlobalResult();
		try {
			int tepm=yaohaoPunishService.update(request,yaohaoPunish);
			if(tepm<=0){
				logger.error("更新异常!");
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public GlobalResult deleteProject(HttpServletRequest request) {
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String i:arr){
					if(i!=null && !"".equals(i)){
						YaohaoPunish pun=yaohaoPunishService.selectByPrimaryKey(i);
						pun.setDeleteFlag(2);
						yaohaoPunishService.updateByPrimaryKeySelective(pun);
					}
				}
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	//导出
	@ResponseBody
	@RequestMapping("/exportDate")
	public void exportDate(HttpServletRequest request,YaohaoPunish yaohaoPunish,HttpServletResponse response){
		List<YaohaoPunish> list =new ArrayList<YaohaoPunish>();
		String ids=request.getParameter("ids");
		String y=request.getParameter("y");
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
				    map.put("isPage",false);
				    map.put("punishYear",y);
					map.put("id", id);
					List<YaohaoPunish> winbid=yaohaoPunishService.selectListTzByMap(map);
					for (YaohaoPunish p:winbid){
                        list.add(p);
                    }
				}
			}else{
				/*map.clear();
				Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoPunish);
				map.putAll(selectMap);
				list= yaohaoPunishService.selectListTzByMap(map);*/
				list=Global.PUNISH_EXPORT_LIST;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String path=request.getSession().getServletContext().getRealPath("/template/report/yaohaopunish_template.xlsx");
			String newPath="";
			String dateString = formatter.format(new Date());
			newPath=request.getSession().getServletContext().getRealPath("/template/temp/奖惩记录导出_"+dateString+".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>();
			List<String> cellList=new ArrayList<String>();
			for(YaohaoPunish l:list){
				cellList =new ArrayList<String>();
				cellList.add(l.getEnterpriseName());
				cellList.add(l.getPunishYear());
				cellList.add(l.getEnterpriseStatus());
				cellList.add(null==l.getAssessResult()||"".equals(l.getAssessResult())?"未评定":l.getAssessResult());
				cellList.add(l.getContent());
				cellList.add(l.getPunishFlag());
				cellList.add(l.getHandleOpinion());
				cellList.add(l.getExecuteStartTimeStr());
				cellList.add(l.getExecuteEndTimeStr());
				cellList.add(l.getStatus());
				cellList.add(l.getRemark());
				cellList.add(l.getCreater());
				cellList.add(l.getCreateTimeStr());
				rowList.add(cellList);
			}
			date.put("奖惩记录", rowList);
			try {
				ExcelUtil.copyExcel(1,date,path,newPath,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
