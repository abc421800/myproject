package com.cost168.costaudit.controller.cost;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cost168.costaudit.pojo.common.Combotree;
import com.cost168.costaudit.pojo.cost.CostTaskType;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostTaskTypeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

/**
 * 
 * ClassName: CostTaskTypeController 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-13上午11:37:40
 * @Company  广东华联软件科技有限公司
 */
@Controller
@RequestMapping("/costTaskType")
public class CostTaskTypeController {
	
	
	@Autowired
	private CostTaskTypeService costTaskTypeService;
	
	@ResponseBody
	@RequestMapping("/treeList")
	public List<Combotree> treeList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<Combotree> list = new ArrayList<Combotree>();
		List<CostTaskType> rootList = costTaskTypeService.getRoots();
		if(!CollectionUtils.isEmpty(rootList)){
			for(CostTaskType root:rootList){
				Combotree tree=new Combotree();
				tree.setId(root.getId());
				tree.setText(root.getName());
			    tree.setParentId(root.getPid());
				if(!CollectionUtils.isEmpty(costTaskTypeService.getChilds(root))){
					tree.setChildren(getChildren(root));
				}else{
					tree.setChildren(new ArrayList<Combotree>());
				}
				list.add(tree);
			}
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/costTaskTypeList")
	public List<CostTaskType> costTaskTypeList() {
		List<CostTaskType> lsit = costTaskTypeService.selectByExample(null);
		return lsit;
	}
	
	private List<Combotree> getChildren(CostTaskType root){
		List<Combotree> list = new ArrayList<Combotree>();
		List<CostTaskType> functionSet = costTaskTypeService.getChilds(root);
		if(!CollectionUtils.isEmpty(functionSet)){
			for(CostTaskType f:functionSet){
			    Combotree combotree=new Combotree();
			    combotree.setId(f.getId());
			    combotree.setText(f.getName());
			    combotree.setParentId(f.getPid());
				if(!CollectionUtils.isEmpty(costTaskTypeService.getChilds(f))){
					combotree.setChildren(getChildren(f));
				}else{
					combotree.setChildren(new ArrayList<Combotree>());
				}
				list.add(combotree);
			}
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/treeListByPid")
	public List<Combotree> treeListByPid(@RequestParam(value="id", defaultValue="0")String id) {
		List<Combotree> list = new ArrayList<Combotree>();
		CostTaskType parent=costTaskTypeService.selectByPrimaryKey(id);
		List<CostTaskType> rootList= costTaskTypeService.getChilds(parent);
		if(!CollectionUtils.isEmpty(rootList)){
			for(CostTaskType root:rootList){
				Combotree tree=new Combotree();
				tree.setId(root.getId());
				tree.setText(root.getName());
			    tree.setParentId(root.getPid());
			    tree.setState("closed");
				if(!CollectionUtils.isEmpty(costTaskTypeService.getChilds(root))){
					tree.setChildren(getChildren(root));
				}else{
					tree.setChildren(new ArrayList<Combotree>());
				}
				list.add(tree);
			}
		}
		return list;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult list(HttpServletRequest request, int page, int rows){
		EUDataGridResult result=new EUDataGridResult();
		String parentId = request.getParameter("parentId");
		Map<String,Object> map=new HashMap<String,Object>();
		String deleteFlag=request.getParameter("deleteFlag");
		if(null!=deleteFlag&& !"".equals(deleteFlag)){
			map.put("deleteFlag", deleteFlag);
		}
		map.put("parentId", parentId);
		map.put("isPage", true);
	    map.put("curPage", rows*(page-1));
	    map.put("pageSize", rows);
		List<CostTaskType> list = costTaskTypeService.getList(map);
		int total = costTaskTypeService.getCount(map);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public GlobalResult upd(HttpServletRequest request,CostTaskType costTaskType){
		GlobalResult result=new GlobalResult();
		try {
			costTaskTypeService.updateByPrimaryKeySelective(costTaskType);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public GlobalResult save(HttpServletRequest request,CostTaskType costTaskType){
		GlobalResult result=new GlobalResult();
		try {
			String id = UUID.randomUUID().toString().replace("-", "");
			SysUser user=shiroUtil.getInstance().currentUser();
			costTaskType.setId(id);
			costTaskType.setDeleteFlag(1);
			costTaskType.setCreateTime(new Date());
			costTaskType.setCreater(user.getName());
			costTaskTypeService.insertSelective(costTaskType);
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public GlobalResult del(HttpServletRequest request) {
		GlobalResult result=new GlobalResult();
		String ids=request.getParameter("ids");
		String arr[]=ids.split(",");
		try {
			CostTaskType costTaskType=null;
			for(String i:arr){
				if(i!=null && !"".equals(i)){
					costTaskType=new CostTaskType();
					costTaskType.setId(i);
					costTaskType.setDeleteFlag(2);
					costTaskTypeService.updateByPrimaryKeySelective(costTaskType);
				}
			}
			result.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	/**
	 * @Description: 获取单个节点详情
	 */
	@RequestMapping("/findDetails")
	@ResponseBody
	public Map<String,Object> findDetails(HttpServletRequest request,HttpServletResponse respones){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//获取当前展开节点ID
			String id = request.getParameter("id");
			if(null != id && id.length() > 0){
				CostTaskType achSysEngineering = costTaskTypeService.selectByPrimaryKey(id);
				if(achSysEngineering != null){
					map.put("id", achSysEngineering.getId());
					map.put("name", achSysEngineering.getName());
					map.put("pid", achSysEngineering.getPid());
					map.put("num", achSysEngineering.getNum());
					map.put("description", achSysEngineering.getDescription());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/listByName")
	public List<CostTaskType> listByName(HttpServletRequest request){
		String name=request.getParameter("name");
		List<CostTaskType> list=null;
		CostTaskType taskType=costTaskTypeService.getCostTaskTypeByName(name);
		if(taskType!=null){
			list=costTaskTypeService.getChilds(taskType);
		}
		return list;
	}
	
}
