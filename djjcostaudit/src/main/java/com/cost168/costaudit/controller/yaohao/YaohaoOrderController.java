package com.cost168.costaudit.controller.yaohao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.pojo.cost.CostContract;
import com.cost168.costaudit.service.cost.CostContractService;
import com.cost168.costaudit.utils.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidateRemove;
import com.cost168.costaudit.pojo.yaohao.YaohaoOrder;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbid;
import com.cost168.costaudit.service.yaohao.YaohaoAutocodeService;
import com.cost168.costaudit.service.yaohao.YaohaoCandidateRemoveService;
import com.cost168.costaudit.service.yaohao.YaohaoCandidateService;
import com.cost168.costaudit.service.yaohao.YaohaoOrderService;
import com.cost168.costaudit.service.yaohao.YaohaoWinbidService;
import com.cost168.costaudit.shiro.shiroUtil;

/**
 * 
 * ClassName: YaohaoOrderController 
 * @Description: TODO
 * @author lixiang
 * @date 2019-7-8下午4:20:02
 * @Company 广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/yaohaoOrder")
public class YaohaoOrderController {
	
	@Autowired
	private YaohaoOrderService yaohaoOrderService;
	@Autowired
	private YaohaoAutocodeService yaohaoAutocodeService;
	@Autowired
	private YaohaoWinbidService yaohaoWinbidService;
	@Autowired
	private YaohaoCandidateService yaohaoCandidateService;
	@Autowired
	private YaohaoCandidateRemoveService yaohaoCandidateRemoveService;
	@Autowired
	public CostContractService costContractService;

	private final Logger LOG = LogManager.getLogger(YaohaoOrderController.class);

	@RequestMapping("/toOrderList")
	public String toOrderList(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        String currentYear = currentDate.substring(0, 4);
        String yaohaoYear="";
		String year=yaohaoOrderService.selectYearList();
		if(null!=year && !"".equals(year)){
			yaohaoYear=year;
		}else{
			yaohaoYear=currentYear;
		}
		request.setAttribute("yaohaoYear", yaohaoYear);
		request.setAttribute("currentYear", currentYear);
		return "yaohao/yaohaoMng/yaohaoList";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public EUDataGridResult list(HttpServletRequest request,YaohaoOrder yaohaoOrder, int page, int rows) {
		EUDataGridResult result=new EUDataGridResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Map<String,Object> selectMap=JsonUtils.object2Map(yaohaoOrder);
			String startTime =request.getParameter("startTime");
			String endTime =request.getParameter("endTime");
			if(null!=startTime && !"".equals(startTime)){
				map.put("startTime", startTime+" 00:00:00");
			}
			if(null!=endTime && !"".equals(endTime)){
				map.put("endTime", endTime+" 24:00:00");
			}
			map.putAll(selectMap);
			map.put("isPage", true);
		    map.put("curPage", rows*(page-1));
		    map.put("pageSize", rows);
		    List<YaohaoOrder> list= yaohaoOrderService.selectListByMap(map);
		    int total= yaohaoOrderService.selectCountByMap(map);
		    map.put("isPage",false);
			Global.ORDER_EXPORT_LIST=yaohaoOrderService.selectListByMap(map);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request){
		SysUser currentUser=shiroUtil.getInstance().currentUser();
		String url="";
		String id=request.getParameter("id");
		String editFlag=request.getParameter("editFlag");
		if(null!=editFlag&&!"".equals(editFlag)){
			request.setAttribute("editFlag",editFlag);
		}
		if(null!=id && !"".equals(id)) {
			url="/yaohaoOrder/upd";
			YaohaoAutocode auto= yaohaoAutocodeService.getCodeAorB();
			YaohaoOrder yaohaoOrder = yaohaoOrderService.selectByPrimaryKey(id);
			String edit_win_flag="";
			if("第二档".equals(yaohaoOrder.getYaohaoGrade())){
				if(yaohaoOrder.getLunNumber()<auto.getLunNumB()){
					edit_win_flag="n";
				}else{
					edit_win_flag="y";
				}
			}else{
				if(yaohaoOrder.getLunNumber()<auto.getLunNumA()){
					edit_win_flag="n";
				}else{
					edit_win_flag="y";
				}
			}
			List<YaohaoWinbid> winbidList1= yaohaoWinbidService.selectListByOrderCode(yaohaoOrder.getCode());
			List<YaohaoWinbid> winbidList=new ArrayList<YaohaoWinbid>();
			for (YaohaoWinbid wd:winbidList1){
				if(wd.getContractId()!=null){
					CostContract costContract=costContractService.selectByPrimaryKey(wd.getContractId());
					if(costContract!=null){
						wd.setSettlementAmount(costContract.getSettlementAmount());
						wd.setRemark(costContract.getRemark());
					}
				}
				winbidList.add(wd);
			}
	        //排除名单
			Map<String,Object> map=new HashMap<String, Object>();
            map.put("orderCode", yaohaoOrder.getCode());
			List<YaohaoCandidateRemove> crListB=yaohaoCandidateRemoveService.selectListByMap(map);
			map.clear();
			map.put("orderCode", yaohaoOrder.getCode());
			List<YaohaoCandidateRemove> crListA=yaohaoCandidateRemoveService.selectListByMap(map);
			request.setAttribute("candidateRemoveListB", crListB);
			request.setAttribute("candidateRemoveListA", crListA);
			request.setAttribute("edit_win_flag", edit_win_flag);
			request.setAttribute("winbidList", winbidList);
			request.setAttribute("add_edit", "edit");
			request.setAttribute("obj", yaohaoOrder);
		}else{
			//获取摇号单编号
			YaohaoAutocode auto= yaohaoAutocodeService.getCodeAorB();
			String codeA=auto.getCodeA();
			String codeB=auto.getCodeB();
			request.setAttribute("codeA", codeA.substring(1,codeA.length()));
			request.setAttribute("codeB", codeB.substring(1,codeB.length()));
			request.setAttribute("lunA", auto.getLunNumA());
			request.setAttribute("lunB", auto.getLunNumB());
			url="/yaohaoOrder/save";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            String yaohaoYear = currentDate.substring(0, 4);
			//第一档候选人数
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("yaohaoGrade", "第一档");
			map.put("yaohaoYear", yaohaoYear);
			map.put("roundNum", auto.getLunNumA());
			map.put("yaohaoJoin","cy");
			//map.put("bidFlagNull", "bidFlagNull");
			//map.put("removeFlag", "1");
			int yaohaoGradeA= yaohaoCandidateService.selectCountByMap(map);
			map.clear();
			map.put("yaohaoGrade", "第二档");
			map.put("yaohaoYear", yaohaoYear);
			map.put("roundNum", auto.getLunNumB());
			map.put("yaohaoJoin","cy");
			//map.put("bidFlagNull", "bidFlagNull");
			//map.put("removeFlag", "1");
			int yaohaoGradeB= yaohaoCandidateService.selectCountByMap(map);
			YaohaoOrder order=new YaohaoOrder();
			String orderId=OrderUtil.buildOrderId("");
			order.setId(orderId);
			order.setCode(auto.getCodeB());
			order.setLunNumber(auto.getLunNumB());
			order.setCreater(currentUser.getName());
            order.setYaohaoYear(yaohaoYear);
            order.setYaohaoTime(new Date());
            order.setEnterpriseNum(yaohaoGradeB);
            //排除名单
            map.clear();
            map.put("orderCode", auto.getCodeB());
			List<YaohaoCandidateRemove> crListB=yaohaoCandidateRemoveService.selectListByMap(map);
			map.clear();
			map.put("orderCode", auto.getCodeA());
			List<YaohaoCandidateRemove> crListA=yaohaoCandidateRemoveService.selectListByMap(map);
			request.setAttribute("candidateRemoveListB", crListB);
			request.setAttribute("candidateRemoveListA", crListA);
            request.setAttribute("yaohaoGradeA", yaohaoGradeA);
            request.setAttribute("yaohaoGradeB", yaohaoGradeB);
			request.setAttribute("obj", order);
			request.setAttribute("add_edit", "add");
		}
		request.setAttribute("url", url);
		return "yaohao/yaohaoMng/yaohaoDetails";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public GlobalResult save(HttpServletRequest request,YaohaoOrder yaohaoOrder){
		GlobalResult result=new GlobalResult();
		try {
			YaohaoAutocode auto= yaohaoAutocodeService.getCodeAorB();
			if("第二档".equals(yaohaoOrder.getYaohaoGrade())){
				yaohaoOrder.setLunNumber(auto.getLunNumB());
				yaohaoOrder.setCiNumber(auto.getCiNumB());
			}else{
				yaohaoOrder.setLunNumber(auto.getLunNumA());
				yaohaoOrder.setCiNumber(auto.getCiNumA());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String winbid=request.getParameter("winbid");
			yaohaoOrder.setCreateTime(new Date());
			String yaohaoTimeStr=request.getParameter("yaohaoTimeStr");
			if(null!=yaohaoTimeStr && !"".equals(yaohaoTimeStr)){
				yaohaoOrder.setYaohaoTime(sdf.parse(yaohaoTimeStr));
			}
			int status=yaohaoOrderService.registerOrder(winbid, yaohaoOrder);
			result.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}

	
	@ResponseBody
	@RequestMapping("/upd")
	public GlobalResult upd(HttpServletRequest request,YaohaoOrder yaohaoOrder){
		GlobalResult result=new GlobalResult();
		try {
			String winbid=request.getParameter("winbid");
			int status=yaohaoOrderService.updRegisterOrder(winbid, yaohaoOrder);
			if(status>=0){
				LOG.error("更新异常！");
			}
			result.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	//导出摇号台账
	@ResponseBody
	@RequestMapping("/exportYaohaoOrder")
	public void exportYaohaoOrder(HttpServletRequest request,HttpServletResponse response){
		List<YaohaoOrder> list =new ArrayList<YaohaoOrder>();
		String ids=request.getParameter("ids");
		try {
			if(null!=ids && !"".equals(ids)){
				String arr[]=ids.split(",");
				for(String id:arr){
					YaohaoOrder order=yaohaoOrderService.selectByPrimaryKey(id);
					list.add(order);
				}
			}else{
//				list= yaohaoOrderService.selectListByMap(null);
				list=Global.ORDER_EXPORT_LIST;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String path=request.getSession().getServletContext().getRealPath("/template/report/yaohaoorder_template.xlsx");
			String newPath="";
			String dateString = formatter.format(new Date());
			newPath=request.getSession().getServletContext().getRealPath("/template/temp/摇号台账_"+dateString+".xlsx");
			Map<String,List<List<String>>>  date=new HashMap<String, List<List<String>>>();
			List<List<String>> rowList=new ArrayList<List<String>>(); 
			List<String> cellList=new ArrayList<String>();
			Map<String,Object> map=new HashMap<String, Object>();
			for(YaohaoOrder l:list){
				map.clear();
				map.put("orderCode", l.getCode());
				BigDecimal bd=yaohaoWinbidService.getServiceAmountSum(map);
				cellList =new ArrayList<String>();
				cellList.add(l.getCode());
				cellList.add(l.getYaohaoYear());
				cellList.add(l.getYaohaoTimeStr());
				cellList.add(l.getYaohaoGrade());
				cellList.add(l.getEnterpriseNum()+"");
				cellList.add(l.getWinbidNum()+"");
				cellList.add(bd.toString());
				cellList.add(l.getCreater());
				rowList.add(cellList);
			}
			date.put("摇号台账", rowList);
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
