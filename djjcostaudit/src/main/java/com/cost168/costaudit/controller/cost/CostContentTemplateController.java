package com.cost168.costaudit.controller.cost;

import com.cost168.costaudit.service.cost.CostContentTemplateService;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 钢筋、混凝土、模板含量控制层
 * @author: ZYL
 * @created: 2019-09-05
 */
@Controller
@RequestMapping("/costProject/costContentTemplate")
public class CostContentTemplateController {
    @Resource
    private CostContentTemplateService costContentTemplateService;

    @ResponseBody
    @RequestMapping("/importContentTemplate")
    public GlobalResult importContentTemplate(HttpServletRequest request) {
        return costContentTemplateService.importContentTemplate(request);
    }

    @ResponseBody
    @RequestMapping("/InExport")
    public void InExport(HttpServletRequest request, HttpServletResponse response) {
        costContentTemplateService.InExport(request, response);
    }
    @ResponseBody
    @RequestMapping("/getContentByProjectId")
    public  EUDataGridResult getContentByProjectId(HttpServletRequest request, int page, int rows){
        return costContentTemplateService.getContentByProjectId(request,page,rows);
    }
    @ResponseBody
    @RequestMapping("/getUnitByProjectId")
    public  EUDataGridResult getUnitByProjectId(HttpServletRequest request, int page, int rows){
        return costContentTemplateService.getUnitByProjectId(request,page,rows);
    }
}
