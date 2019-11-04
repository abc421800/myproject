package com.cost168.costaudit.service.cost;

import com.cost168.costaudit.pojo.cost.CostContentTemplate;
import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.pojo.cost.CostUnitProject;
import com.cost168.costaudit.utils.EUDataGridResult;
import com.cost168.costaudit.utils.GlobalResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ZYL
 * @created: 2019-09-05
 */
public interface CostContentTemplateService {

    EUDataGridResult getUnitByProjectId(HttpServletRequest request, int page, int rows);

    EUDataGridResult getContentByProjectId(HttpServletRequest request, int page, int rows);

    GlobalResult importContentTemplate(HttpServletRequest request);

    void InExport(HttpServletRequest request, HttpServletResponse response);

}

