package com.cost168.costaudit.pojo.yaohao.param;

import com.cost168.costaudit.pojo.yaohao.YaohaoAssess;

import java.util.Date;

/**
 * @description: 综合考核页面需要的参数
 * @author: ZYL
 * @created: 2019-07-08
 */
public class YaohaoAssessParam extends YaohaoAssess {
    //入库企业id
    private String costEnterpriseId;
    //入库企业名字
    private String enterpriseName;
    //企业简称
    private String simpleName;
    //入库状态
    private String effectiveFlag;
    //入库企业状态记录表状态(cost_enterprise_record)
    private String status;
    //服务开始时间
    private Date startTime;
    //服务结束时间
    private Date endTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setEffectiveFlag(String effectiveFlag) {
        this.effectiveFlag = effectiveFlag;
    }

    public String getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setCostEnterpriseId(String costEnterpriseId) {
        this.costEnterpriseId = costEnterpriseId;
    }

    public String getCostEnterpriseId() {
        return costEnterpriseId;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {

        this.enterpriseName = enterpriseName;
    }
}
