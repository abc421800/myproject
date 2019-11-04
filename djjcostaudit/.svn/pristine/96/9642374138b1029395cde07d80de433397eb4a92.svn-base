package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CostContract {
    private String id;

    private String projectId;//项目id

    private String name;//合同名称

    private String projectName;//项目名称
    private String projectNameShow;//项目名称

    private String code;//合同编号

    private String auditPriceUnit;//审价单位

    private String contractType;//合同类型

    private String status;//合同状态

    private String partyB;//合同乙方

    private Date signingTime;//签订时间
    private String signingTimeStr;

    private String partyBContacts;//乙方联系人

    private String partyBPhone;//乙方联系电话

    private BigDecimal contractAmount;//合同金额

    private BigDecimal changeAmount;//合同变更金额

    private BigDecimal changeAmountSum;//合同变更金额

    private BigDecimal settlementAmount;//结算金额

    private BigDecimal temporaryAmount;//暂定金额

    private String executiveDepartment;//合同执行部门
    private String department;//合同执行部门名称

    private String operator;//经办人

    private String operatorPhone;//经办人电话

    private String personLiable;//负责人

    private String description;//合同摘要

    private String remark;//备注

    private String creater;//创建人

    private String settlement;//需结算

    private Date createTime;//创建时间
    private String createTimeStr;
    private String parentId;//父类ID
    private String mainFlag;//主/从属性：主合同、从合同
    private String priority;//序号
    private String containCong;//是否包含从合同
    private BigDecimal mainFlagAmount;//主+从合同汇总金额


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAuditPriceUnit() {
        return auditPriceUnit;
    }

    public void setAuditPriceUnit(String auditPriceUnit) {
        this.auditPriceUnit = auditPriceUnit == null ? null : auditPriceUnit.trim();
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB == null ? null : partyB.trim();
    }

    public Date getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }

    public String getPartyBContacts() {
        return partyBContacts;
    }

    public void setPartyBContacts(String partyBContacts) {
        this.partyBContacts = partyBContacts == null ? null : partyBContacts.trim();
    }

    public String getPartyBPhone() {
        return partyBPhone;
    }

    public void setPartyBPhone(String partyBPhone) {
        this.partyBPhone = partyBPhone == null ? null : partyBPhone.trim();
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getExecutiveDepartment() {
        return executiveDepartment;
    }

    public void setExecutiveDepartment(String executiveDepartment) {
        this.executiveDepartment = executiveDepartment == null ? null : executiveDepartment.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone == null ? null : operatorPhone.trim();
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable == null ? null : personLiable.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSigningTimeStr() {
        if (signingTime == null) {
            return signingTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(signingTime);
            return dateString;
        }
    }

    public void setSigningTimeStr(String signingTimeStr) {
        this.signingTimeStr = signingTimeStr;
    }

    public String getCreateTimeStr() {
        if (createTime == null) {
            return createTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString = formatter.format(createTime);
            return dateString;
        }
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }


    public BigDecimal getChangeAmountSum() {
        return changeAmountSum;
    }

    public void setChangeAmountSum(BigDecimal changeAmountSum) {
        this.changeAmountSum = changeAmountSum;
    }

    public BigDecimal getTemporaryAmount() {
        return temporaryAmount;
    }

    public void setTemporaryAmount(BigDecimal temporaryAmount) {
        this.temporaryAmount = temporaryAmount;
    }

    public String getProjectNameShow() {
        return projectNameShow;
    }

    public void setProjectNameShow(String projectNameShow) {
        this.projectNameShow = projectNameShow;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMainFlag() {
        return mainFlag;
    }

    public void setMainFlag(String mainFlag) {
        this.mainFlag = mainFlag;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContainCong() {
        return containCong;
    }

    public void setContainCong(String containCong) {
        this.containCong = containCong;
    }

    public BigDecimal getMainFlagAmount() {
        return mainFlagAmount;
    }

    public void setMainFlagAmount(BigDecimal mainFlagAmount) {
        this.mainFlagAmount = mainFlagAmount;
    }

}