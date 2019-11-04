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
import com.cost168.costaudit.pojo.cost.CostProjectType;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.cost.CostProjectTypeService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

@Controller
@RequestMapping("/costProjectType")
public class CostProjectTypeController {
    @Autowired
    public CostProjectTypeService costProjectTypeService;

    @RequestMapping("/treeList")
    @ResponseBody
    public List<Combotree> treeList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<Combotree> list = new ArrayList<Combotree>();
        List<CostProjectType> rootList = costProjectTypeService.getRoots();
        if (!CollectionUtils.isEmpty(rootList)) {
            for (CostProjectType root : rootList) {
                Combotree tree = new Combotree();
                tree.setId(root.getId());
                tree.setText(root.getName());
                tree.setParentId(root.getPid());
                if (!CollectionUtils.isEmpty(costProjectTypeService.getChilds(root))) {
                    tree.setChildren(getChildren(root));
                } else {
                    tree.setChildren(new ArrayList<Combotree>());
                }
                list.add(tree);
            }
        }
        return list;
    }

    private List<Combotree> getChildren(CostProjectType root) {
        List<Combotree> list = new ArrayList<Combotree>();
        List<CostProjectType> functionSet = costProjectTypeService.getChilds(root);
        if (!CollectionUtils.isEmpty(functionSet)) {
            for (CostProjectType f : functionSet) {
                Combotree combotree = new Combotree();
                combotree.setId(f.getId());
                combotree.setText(f.getName());
                combotree.setParentId(f.getPid());
                if (!CollectionUtils.isEmpty(costProjectTypeService.getChilds(f))) {
                    combotree.setChildren(getChildren(f));
                } else {
                    combotree.setChildren(new ArrayList<Combotree>());
                }
                list.add(combotree);
            }
        }
        return list;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult list(HttpServletRequest request, int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        String parentId = request.getParameter("parentId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        List<CostProjectType> list = costProjectTypeService.getList(map);
        int total = costProjectTypeService.getCount(map);
        result.setRows(list);
        result.setTotal(total);
        return result;
    }

    @RequestMapping("/upd")
    @ResponseBody
    public GlobalResult upd(HttpServletRequest request, CostProjectType costProjectType) {
        GlobalResult result = new GlobalResult();
        try {
            costProjectTypeService.updateByPrimaryKeySelective(costProjectType);
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public GlobalResult save(HttpServletRequest request, CostProjectType costProjectType) {
        GlobalResult result = new GlobalResult();
        try {
            SysUser user = shiroUtil.getInstance().currentUser();
            String id = UUID.randomUUID().toString().replace("-", "");
            costProjectType.setId(id);
            costProjectType.setCreateTime(new Date());
            costProjectType.setCreater(user.getName());
            costProjectType.setDeleteFlag(1);
            costProjectTypeService.insertSelective(costProjectType);
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
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String arr[] = ids.split(",");
        try {
            for (String i : arr) {
                if (i != null && !"".equals(i)) {
                    CostProjectType costProjectType = costProjectTypeService.selectByPrimaryKey(i);
                    costProjectType.setDeleteFlag(2);
                    costProjectTypeService.updateByPrimaryKeySelective(costProjectType);
                    //costProjectTypeService.deleteByPrimaryKey(i);
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
    public Map<String, Object> findDetails(HttpServletRequest request, HttpServletResponse respones) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //获取当前展开节点ID
            String id = request.getParameter("id");
            if (null != id && id.length() > 0) {
                CostProjectType achSysEngineering = costProjectTypeService.selectByPrimaryKey(id);
                if (achSysEngineering != null) {
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


}
