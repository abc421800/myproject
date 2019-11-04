package com.cost168.costaudit.service.yaohao.impl;

import com.cost168.costaudit.mapper.cost.CostEnterpriseMapper;
import com.cost168.costaudit.mapper.cost.CostEnterpriseRecordMapper;
import com.cost168.costaudit.mapper.yaohao.YaohaoAssessMapper;
import com.cost168.costaudit.mapper.yaohao.YaohaoAutocodeMapper;
import com.cost168.costaudit.mapper.yaohao.YaohaoCandidateMapper;
import com.cost168.costaudit.pojo.cost.CostEnterprise;
import com.cost168.costaudit.pojo.cost.CostEnterpriseRecord;
import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.pojo.yaohao.YaohaoAssess;
import com.cost168.costaudit.pojo.yaohao.YaohaoAutocode;
import com.cost168.costaudit.pojo.yaohao.YaohaoCandidate;
import com.cost168.costaudit.pojo.yaohao.param.YaohaoAssessParam;
import com.cost168.costaudit.service.yaohao.YaohaoAssessService;
import com.cost168.costaudit.shiro.shiroUtil;
import com.cost168.costaudit.utils.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 综合考核服务层
 * @author: ZYL
 * @created: 2019-07-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class YaohaoAssessServiceImpl implements YaohaoAssessService {
    @Resource
    private YaohaoAssessMapper assessMapper;
    @Resource
    private CostEnterpriseMapper costEnterpriseMapper;
    @Resource
    private CostEnterpriseRecordMapper recordMapper;
    @Resource
    private YaohaoCandidateMapper yaohaoCandidateMapper;
    @Resource
    private YaohaoAutocodeMapper yaohaoAutocodeMapper;

    private final Logger LOG = LogManager.getLogger(YaohaoAssessServiceImpl.class);

    @Override
    public YaohaoAssess selectByPrimaryKey(String id) {
        return assessMapper.selectByPrimaryKey(id);
    }

    @Override
    public int selectCountByMap(Map<String, Object> map) {
        return assessMapper.selectCountByMap(map);
    }

    /**
     * created by ZYL on 2019/7/10
     * 批量删除
     */
    @Override
    public GlobalResult deleteByPrimaryKey(HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        String ids = request.getParameter("ids");
        String[] arr = ids.split(",");
        int temp = 0;
        for (String id : arr) {
            if (id != null && !"".equals(id)) {
                temp += assessMapper.deleteByPrimaryKey(id);
            }
        }
        if (temp > 0) {
            result.setStatus(200);
            result.setMsg("删除成功！");
        } else {
            LOG.error("删除异常！");
            result.setStatus(500);
            result.setMsg("删除失败！");
        }
        return result;
    }

    @Override
    public GlobalResult insert(YaohaoAssessParam assessParam, HttpServletRequest request) {
        GlobalResult result = new GlobalResult();
        Map<String, Object> map = new HashMap<String, Object>();
        //插入评分
        String entStatus = request.getParameter("entStatus");
        assessParam.setRukuStatus(entStatus);
        assessParam.setCreateTime(new Date());
        if ("暂停".equals(entStatus)) {
            assessParam.setYaohaoGrade("第二档");
            assessParam.setRukuStatus("在库");
            assessParam.setAssessResult(null);
        }
        int temp = assessMapper.insert(assessParam);
        if (temp <= 0) {
            LOG.error("插入异常！");
        }
        map.put("year", assessParam.getYear());
        map.put("assessResultNotEqNull", "assessResultNotEqNull");
        List<YaohaoAssessParam> list = assessMapper.selectListByMap(map);
        YaohaoAssessParam last = list.get(list.size() - 1);
        int tep = 1;
        for (YaohaoAssessParam a : list) {
            a.setScoreRank(tep);
            if (a.getAssessScore() > 65) {
                if (tep <= 5) {
                    a.setAssessResult("第一档");
                    a.setYaohaoGrade("第一档");
                    a.setRukuStatus("在库");
                } else {
                    //第五名的分数
                    double scope = list.get(4).getAssessScore();
                    if (scope == a.getAssessScore()) {
                        a.setAssessResult("第一档");
                        a.setYaohaoGrade("第一档");
                        a.setRukuStatus("在库");
                    } else {
                        a.setAssessResult("第二档");
                        a.setYaohaoGrade("第二档");
                        a.setRukuStatus("在库");
                    }
                }
                temp += assessMapper.updateByPrimaryKeySelective(a);
            } else if (a.getAssessScore() <= 65) {
                if (last.getEnterpriseCode().equals(a.getEnterpriseCode())) {
                    a.setAssessResult("不合格");
                    a.setYaohaoGrade(null);
                    a.setRukuStatus("出库");
                } else {
                    if (a.getAssessScore() == last.getAssessScore()) {
                        a.setAssessResult("不合格");
                        a.setYaohaoGrade(null);
                        a.setRukuStatus("出库");
                    } else {
                        a.setAssessResult("第二档");
                        a.setYaohaoGrade("第二档");
                        a.setRukuStatus("在库");
                    }
                }
                temp += assessMapper.updateByPrimaryKeySelective(a);
            }
            //更新得分排名
            if (a.getEnterpriseCode().equals(assessParam.getEnterpriseCode())) {
                YaohaoAssess asse = assessMapper.selectByPrimaryKey(assessParam.getId());
                asse.setScoreRank(a.getScoreRank());
                YaohaoAssessParam p = new YaohaoAssessParam();
                p.setId(asse.getId());
                p.setScoreRank(asse.getScoreRank());
                p.setAssessScore(a.getAssessScore());
                int u = assessMapper.updateByPrimaryKeySelective(p);
                if (u <= 0) {
                    LOG.error("更新异常！");
                }
            }
            tep++;
        }
        if (temp > 0) {
            result.setStatus(200);
            result.setMsg("插入成功！");
        } else {
            LOG.error("插入异常！");
            result.setStatus(500);
            result.setMsg("插入成功！");
        }
        return result;
    }

    /**
     * created by ZYL on 2019/7/18
     * 跳转新增或者修改页面
     */
    @Override
    public String toEdit(HttpServletRequest request) {
        SysUser currentUser = shiroUtil.getInstance().currentUser();
        String url = "";
        String id = request.getParameter("id");
        String scoreRankStr = request.getParameter("scoreRankStr");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        String regYear = currentDate.substring(0, 4);
        if (null != id && !"".equals(id)) {
            //修改
            url = "/assess/update";
            YaohaoAssessParam param = assessMapper.queryOne(id);
            param.setScoreRank(Integer.parseInt(scoreRankStr));
            request.setAttribute("obj", param);
            request.setAttribute("url", url);
            return "/yaohao/assess/detail";
        } else {
            //新增
            url = "/assess/insert";
            YaohaoAssessParam param = new YaohaoAssessParam();
            param.setId(OrderUtil.buildOrderId(""));
            param.setCreater(currentUser.getName());
            param.setYear(regYear);
            request.setAttribute("obj", param);
            request.setAttribute("url", url);
            return "/yaohao/assess/assessAdd";
        }
    }

    /**
     * created by ZYL on 2019/7/17
     * 更新综合考核单
     */
    @Override
    public GlobalResult updateByPrimaryKeySelective(YaohaoAssessParam assessParam) {
        GlobalResult result = new GlobalResult();
        Map<String, Object> map = new HashMap<String, Object>();
        assessParam.setCreateTime(new Date());
        int temp = assessMapper.updateByPrimaryKey(assessParam);
        if (temp <= 0) {
            LOG.error("更新异常！");
        }
        map.put("year", assessParam.getYear());
        map.put("assessResultNotEqNull", "assessResultNotEqNull");
        List<YaohaoAssessParam> list = assessMapper.selectListByMap(map);
        YaohaoAssessParam last = list.get(list.size() - 1);
        int tep = 1;
        for (YaohaoAssessParam a : list) {
            if (a.getAssessScore() > 65) {
                if (tep <= 5) {
                    a.setAssessResult("第一档");
                    a.setRukuStatus("在库");
                    a.setYaohaoGrade("第一档");
                } else {
                    a.setAssessResult("第二档");
                    a.setYaohaoGrade("第二档");
                    a.setRukuStatus("在库");
                }
                temp += assessMapper.updateByPrimaryKeySelective(a);
            } else if (a.getAssessScore() == 0) {
                a.setAssessResult("未评定");
                a.setYaohaoGrade("");
                a.setRukuStatus("暂停");
            } else if (a.getAssessScore() <= 65) {
                if (last.getEnterpriseCode().equals(a.getEnterpriseCode())) {
                    a.setAssessResult("不合格");
                    a.setYaohaoGrade(null);
                    a.setRukuStatus("出库");
                } else {
                    if (a.getAssessScore() == last.getAssessScore()) {
                        a.setAssessResult("不合格");
                        a.setRukuStatus("出库");
                        a.setYaohaoGrade(null);
                    } else {
                        a.setAssessResult("第二档");
                        a.setYaohaoGrade("第二档");
                        a.setRukuStatus("在库");
                    }
                }
            }
            temp += assessMapper.updateByPrimaryKeySelective(a);
            tep++;
        }
        if (temp > 0) {
            result.setStatus(200);
            result.setMsg("更新成功！");
        } else {
            LOG.error("更新异常！");
            result.setStatus(500);
            result.setMsg("更新失败！");
        }
        return result;
    }

    /**
     * created by ZYL on 2019/7/10
     * 获取所有的考核年份
     */
    @Override
    public String queryYear(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        String currentYear = currentDate.substring(0, 4);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("years", assessMapper.queryYear());
        return "/yaohao/assess/list";
    }

    /**
     * created by ZYL on 2019/7/9
     * 根据条件检索数据
     */
    @Override
    public EUDataGridResult selectListByMap(HttpServletRequest request, int page, int rows, Map<String, Object> map) {
        EUDataGridResult result = new EUDataGridResult();
        String enterpriseName = request.getParameter("enterpriseName");
        String rukuStatus = request.getParameter("rukuStatus");
        String year = request.getParameter("year");
        String assessResult = request.getParameter("assessResult");
        String yaohaoGrade = request.getParameter("yaohaoGrade");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        map.put("enterpriseName", enterpriseName);
        map.put("rukuStatus", rukuStatus);
        if (year != null) {
            map.put("year", year);
        } else {
            Calendar date = Calendar.getInstance();
            String yearStr = String.valueOf(date.get(Calendar.YEAR));
            map.put("year", yearStr);
        }
        map.put("assessResult", assessResult);
        map.put("yaohaoGrade", yaohaoGrade);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        for (int i = 1; i <= 2; i++) {
            if (i == 2) {
                map.put("isPage", false);
            }
            List<YaohaoAssessParam> list = assessMapper.selectListByMap(map);
            int temp = 1;
            //设置排名
            for (YaohaoAssessParam list2 : list) {
                list2.setScoreRankStr(temp);
                temp++;
            }
            if (i == 2) {
               /* List<YaohaoAssessParam> list1=new ArrayList<YaohaoAssessParam>();
                for(YaohaoAssessParam ap:list){
                    if(ap.getStartTime()!=null||!ap.getStartTime().equals("")){

                        GlobalResult result1=addYaohaoNameBtnShow(request,ap.getStartTime().toString());
                        if(result1.getStatus()==200){
                            list1.add(ap);
                        }
                    }
                }*/
                Global.ASSESS_EXPORT_LIST = list;
            } else {
                result.setRows(list);
            }
        }
        int total = assessMapper.selectCountByMap(map);
        result.setTotal(total);
        return result;
    }

    /**
     * created by ZYL on 2019/7/17
     * 查询有效时间内的入库企业
     */
    @Override
    public EUDataGridResult queryEnterpriseByParam(HttpServletRequest request, int page, int rows, Map<String, Object> map) {
        EUDataGridResult result = new EUDataGridResult();
        List<YaohaoAssessParam> assessParamList = null;
        String enterpriseName = request.getParameter("enterpriseName");
        String startTime = request.getParameter("startTime");
        if (null != startTime && !"".equals(startTime)) {
            map.put("startTime", startTime + "-01-01");
            map.put("year", startTime);
        }
        map.put("enterpriseName", enterpriseName);
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        assessParamList = assessMapper.queryEnterpriseByParam(map);
        if (assessParamList != null) {
            int temp = assessMapper.countEnterpriseByParam(map);
            if (assessParamList.size() > 0 && temp > 0) {
                result.setRows(assessParamList);
                result.setTotal(temp);
            } else {
                result.setRows(assessParamList);
                result.setTotal(temp);
                LOG.error("查询失败！");
            }
        }
        return result;
    }

    /**
     * Created by ZYL on 2019/8/16
     * 判断当年是否考核完毕
     */
    @Override
    public GlobalResult addYaohaoNameBtnShow(HttpServletRequest request, String startTime) {
        GlobalResult result = new GlobalResult();
        Map<String, Object> map = new HashMap<String, Object>();
        //获取当前年
        Calendar cale = Calendar.getInstance();
        int currentYear = cale.get(Calendar.YEAR);
        if (startTime != null && startTime != "") {
            map.put("year", startTime);
            map.put("startTime", startTime + "-01-01");
            if (Integer.parseInt(startTime) == currentYear) {
                int total = assessMapper.countEnterpriseByParam(map);
                if (total == 0) {
                    result.setStatus(200);
                    result.setMsg("当年的综合考评已经完成！");
                } else {
                    result.setStatus(500);
                    result.setMsg("当年的综合考评未完成！");
                }
            } else {
                LOG.info("无法判断不同年份是否已经完成综合考评！");
                result.setStatus(500);
                result.setMsg("无法判断不同年份是否已经考评完成！");
            }
        } else {
            LOG.error("无法获取参数！");
            result.setStatus(501);
            result.setMsg("参数设置错误！");
        }
        return result;
    }

    /**
     * Created by ZYL on 2019/8/19
     * 考核完成后自动推送摇号名单
     */
    @Override
    public GlobalResult addYaohaoName(HttpServletRequest request, String startTime) {
        GlobalResult result = new GlobalResult();
        Map<String, Object> map = new HashMap<String, Object>();
        YaohaoCandidate can = null;
        SysUser user = shiroUtil.getInstance().currentUser();
        YaohaoAutocode code = yaohaoAutocodeMapper.selectByPrimaryKey("1");
        //判断摇号名单是否已经推送
        int temp = 0;
        if (null != startTime && !"".equals(startTime)) {
            map.put("year", startTime);
            map.put("startTime", startTime + "-01-01");
            List<YaohaoAssessParam> paramList = assessMapper.selectListByMap(map);
            CostEnterpriseRecord record = new CostEnterpriseRecord();
            CostEnterpriseRecord record2 = new CostEnterpriseRecord();
            int i = 0;
            //推送摇号名单
            if (null != paramList && paramList.size() > 0) {
                for (YaohaoAssessParam assessParam : paramList) {
                    //增加下一年入库企业入库状态
                    i++;
                    map.clear();
                    map.put("year", Integer.parseInt(startTime) + 1 + "");
                    map.put("enterpriseId", assessParam.getCostEnterpriseId());
                    List<CostEnterpriseRecord> crList = recordMapper.selectListByMap(map);
                    if (null != crList && crList.size() > 0) {
                        record2 = crList.get(0);
                        record2.setStatus(assessParam.getRukuStatus());
                        record2.setCreateTime(new Date());
                        record2.setOperator(user.getName());
                        temp += recordMapper.updateByPrimaryKeySelective(record2);
                        if (temp <= i) {
                            LOG.error("更新异常！");
                        }
                    } else {
                        record.setId(OrderUtil.buildOrderId(""));
                        record.setEnterpriseId(assessParam.getCostEnterpriseId());
                        record.setStatus(assessParam.getRukuStatus());
                        record.setCreateTime(new Date());
                        record.setOperator(user.getName());
                        record.setYear(Integer.parseInt(startTime) + 1 + "");
                        temp += recordMapper.insertSelective(record);
                        if (temp <= i) {
                            LOG.error("插入异常！");
                        }
                    }

                }
            }
            //在库企业推送到摇号名单
            map.clear();
            map.put("effectiveFlag", "在库");
            map.put("batch", Integer.parseInt(startTime) + 1 + "");
            List<CostEnterprise> eList = costEnterpriseMapper.selectListByMap(map);
            if (null != eList && eList.size() > 0) {
                for (CostEnterprise e : eList) {
                    i++;
                    //插入第二档
                    can = new YaohaoCandidate();
                    can.setId(OrderUtil.buildOrderId(""));
                    can.setCreater(user.getName());
                    can.setEnterpriseCode(e.getCode());
                    can.setCreateTime(new Date());
                    can.setYaohaoYear(Integer.parseInt(startTime) + 1 + "");
                    can.setYaohaoGrade("第二档");
                    can.setRemoveFlag("1");
                    can.setRoundNum(code.getLunNumB());
                    temp += yaohaoCandidateMapper.insertSelective(can);
                    if (temp <= i) {
                        LOG.error("插入异常！");
                    }
                }
                for (CostEnterprise e : eList) {
                    i++;
                    //插入第一档
                    if ("第一档".equals(e.getYaohaoGradeRk())) {
                        can = new YaohaoCandidate();
                        can.setId(OrderUtil.buildOrderId(""));
                        can.setCreater(user.getName());
                        can.setEnterpriseCode(e.getCode());
                        can.setCreateTime(new Date());
                        can.setYaohaoYear(Integer.parseInt(startTime) + 1 + "");
                        can.setYaohaoGrade("第一档");
                        can.setRoundNum(code.getLunNumA());
                        can.setRemoveFlag("1");
                        temp += yaohaoCandidateMapper.insertSelective(can);
                        if (temp <= i) {
                            LOG.error("插入异常！");
                        }
                    }
                }
            }
        }
        if (temp > 0) {
            result.setStatus(200);
            result.setMsg("插入成功！");
        } else {
            LOG.error("插入异常！");
            result.setStatus(500);
            result.setMsg("插入失败！");
        }
        return result;
    }

    /**
     * created by ZYL on 2019/7/9
     * 综合考核导出excel表格
     */
    @Override
    public EUDataGridResult exportExcel(HttpServletRequest request, HttpServletResponse response, String year) {
        EUDataGridResult result = new EUDataGridResult();
        List<YaohaoAssessParam> list = new ArrayList<YaohaoAssessParam>();
        Map<String, Object> selectMap = new HashMap<String, Object>();
        String costEnterpriseIds = request.getParameter("costEnterpriseIds");
        String scoreRankStrs = request.getParameter("scoreRankStrs");
        String[] arr2 = new String[0];
        if (null != costEnterpriseIds && !"".equals(costEnterpriseIds) && null != scoreRankStrs && !"".equals(scoreRankStrs)) {
            String[] arr = costEnterpriseIds.split(",");
            arr2 = scoreRankStrs.split(",");
            for (String costEnterpriseId : arr) {
                selectMap.put("costEnterpriseId", costEnterpriseId);
                YaohaoAssessParam param = assessMapper.selectOne(selectMap);
                list.add(param);
            }
        } else {
            if (year != null) {
                selectMap.put("year", year);
            }
            list = assessMapper.selectListByMap(selectMap);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String path = request.getSession().getServletContext().getRealPath("/template/report/assess_template.xlsx");
        String newPath = "";
        String dateString = formatter.format(new Date());
        newPath = request.getSession().getServletContext().getRealPath("/template/temp/综合考核_" + dateString + ".xlsx");
        Map<String, List<List<String>>> date = new HashMap<String, List<List<String>>>();
        List<List<String>> rowList = new ArrayList<List<String>>();
        List<String> cellList = new ArrayList<String>();
        int temp = 1;
        int temp2 = 0;
        for (YaohaoAssessParam param : list) {
            cellList = new ArrayList<String>();
            cellList.add(param.getEnterpriseName());
            cellList.add(param.getRukuStatus());
            cellList.add(param.getYear());
            cellList.add(param.getAssessScore() + "");
            if (arr2.length > 0) {
                cellList.add(arr2[temp2] + "");
            } else {
                cellList.add(temp + "");
            }
            cellList.add(param.getAssessResult());
            cellList.add(param.getYaohaoGrade());
            cellList.add(param.getRater());
            cellList.add(formatter.format(param.getCreateTime()));
            cellList.add(param.getCreater());
            rowList.add(cellList);
            temp++;
            temp2++;
        }
        date.put("综合考核", rowList);
        try {
            ExcelUtil.copyExcel(1, date, path, newPath, request, response);
            LOG.error("excel异常！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<YaohaoAssess> selectList(Map<String, Object> map) {
        return assessMapper.selectList(map);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return assessMapper.selectCount(map);
    }

    @Override
    public EUDataGridResult assessList(HttpServletRequest request, int page, int rows, Map<String, Object> map) {
        EUDataGridResult result = new EUDataGridResult();
        String enterpriseCode = request.getParameter("enterpriseCode");
        map.put("isPage", true);
        map.put("curPage", rows * (page - 1));
        map.put("pageSize", rows);
        map.put("enterpriseCode", enterpriseCode);
        List<YaohaoAssess> list = selectList(map);
        int total = selectCount(map);
        result.setRows(list);
        result.setTotal(total);
        return result;
    }
}
