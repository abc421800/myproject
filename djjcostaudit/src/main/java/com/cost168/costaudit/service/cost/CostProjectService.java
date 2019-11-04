package com.cost168.costaudit.service.cost;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cost168.costaudit.pojo.cost.CostProject;
import com.cost168.costaudit.pojo.cost.CostProjectExample;
import com.cost168.costaudit.utils.GlobalResult;
import com.cost168.costaudit.vo.cost.ContractJsCountVo;
import com.cost168.costaudit.vo.cost.ProjectJsCountVo;

public interface CostProjectService {
    List<CostProject> selectByExample(CostProjectExample example);

    CostProject selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insertSelective(CostProject record);

    int updateByPrimaryKeySelective(CostProject record);

    List<CostProject> getList(Map<String, Object> map);

    int getCount(Map<String, Object> map);

    int deleteProject(String id);

    //保存项目信息
    public GlobalResult saveProject(CostProject project);

    //修改项目信息
    public GlobalResult updateProject(CostProject project);

    //结算台账列表
    List<ProjectJsCountVo> getProjectJsList(Map<String, Object> map, int page, int rows);

    int getProjectJsCount(Map<String, Object> map);

    int getProjectJsCountAll(Map<String, Object> map);

    //结算台账项目的合同列表
    List<ProjectJsCountVo> getProjectJsContractList(Map<String, Object> map);

    List<ProjectJsCountVo> getProjectJsContractChildrenList(Map<String, Object> map);

    //合同台账统计表
    List<ContractJsCountVo> getContractJsList(Map<String, Object> map, int page, int rows);

    int getContractJsCount(Map<String, Object> map);

    //合同结算统计表
    List<ContractJsCountVo> getContractJsList2(Map<String, Object> map, int page, int rows);

    int getContractJsCount2(Map<String, Object> map);

    //合同结算按合同执行部门分组统计
    List<ContractJsCountVo> getContractJsGroup(Map<String, Object> map);

    //导入项目
    public List<CostProject> importProject(HttpServletRequest request);

    //导出项目台账
    public void exportProject(String[] columnName, String[] columnTitle, OutputStream out, List<CostProject> list) throws IOException;

    public CostProject selectByCode(String code);

    BigDecimal getTaskSumDecideAmount(Map<String, Object> map);

    BigDecimal getContractSumAmount(Map<String, Object> map);

    //导出结算台账表
    public void exportProjectJsList(String path, OutputStream out, List<ProjectJsCountVo> list, Map<String, Object> map);

    //导出项目列表
    public void exportProject(String path, OutputStream out, List<CostProject> list);

    //导出合同台账统计表
    // public void exportContractJsList(List<ContractJsCountVo> list,String path,String newPath,HttpServletRequest request,HttpServletResponse response);
    public void exportContractJsList(String tableName, List<ContractJsCountVo> list, String path, OutputStream out);

    //导出项目指标表
    public void exportProjectZb(List<CostProject> list, String path, String newPath, HttpServletRequest request, HttpServletResponse response);

    String getAAA(String projectId);

    String getBBB(String id);

    String getContractAmount(Map<String, Object> map);
}
