package com.cost168.costaudit.controller.yaohao;

import com.cost168.costaudit.pojo.yaohao.param.YaohaoAssessParam;
import com.cost168.costaudit.service.yaohao.YaohaoAssessService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description: 综合考核控制层
 * @author: ZYL
 * @created: 2019-07-08
 */
@Controller
@RequestMapping("/assess")
public class YaohaoAssessController {
    @Resource
    private YaohaoAssessService assessService;

    @RequestMapping("/toList")
    public String queryYear(HttpServletRequest request) {
        return assessService.queryYear(request);
    }

    @ResponseBody
    @RequestMapping("/list")
    public EUDataGridResult selectList(int page, int rows, HttpServletRequest request, Map<String, Object> map) {
        return assessService.selectListByMap(request, page, rows, map);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpServletRequest request) {
        return assessService.toEdit(request);
    }

    @ResponseBody
    @RequestMapping("/update")
    public GlobalResult update(YaohaoAssessParam assessParam) {
        return assessService.updateByPrimaryKeySelective(assessParam);
    }

    @ResponseBody
    @RequestMapping("/insert")
    public GlobalResult insert(YaohaoAssessParam assessParam, HttpServletRequest request) {
        return assessService.insert(assessParam, request);
    }

    @ResponseBody
    @RequestMapping("/exportExcel")
    public EUDataGridResult exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String year) {
        return assessService.exportExcel(request, response, year);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public GlobalResult delete(HttpServletRequest request) {
        return assessService.deleteByPrimaryKey(request);
    }

    @ResponseBody
    @RequestMapping("/queryEnterprise")
    public EUDataGridResult queryEnterpriseByParam(HttpServletRequest request, int page, int rows, Map<String, Object> map) {
        return assessService.queryEnterpriseByParam(request, page, rows, map);
    }

    @ResponseBody
    @RequestMapping("/addYaohaoNameBtnShow")
    public GlobalResult addYaohaoNameBtnShow(HttpServletRequest request, String startTime) {
        return assessService.addYaohaoNameBtnShow(request, startTime);
    }

    @ResponseBody
    @RequestMapping("/addYaohaoName")
    public GlobalResult addYaohaoName(HttpServletRequest request, String startTime) {
        return assessService.addYaohaoName(request, startTime);
    }
}
