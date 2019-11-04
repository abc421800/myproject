package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;

public class CostUnitProject {
    private String id;

    private Double number;

    private String name;

    private String projectId;

    private BigDecimal subProjectCost;

    private BigDecimal stepItemCost;

    private BigDecimal otherProjectFee;

    private BigDecimal feesTaxes;

    private BigDecimal count;

    private BigDecimal unitProjectPercen;

    private BigDecimal coveredArea;

    private BigDecimal unilateralIndicators;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getSubProjectCost() {
        return subProjectCost;
    }

    public void setSubProjectCost(BigDecimal subProjectCost) {
        this.subProjectCost = subProjectCost;
    }

    public BigDecimal getStepItemCost() {
        return stepItemCost;
    }

    public void setStepItemCost(BigDecimal stepItemCost) {
        this.stepItemCost = stepItemCost;
    }

    public BigDecimal getOtherProjectFee() {
        return otherProjectFee;
    }

    public void setOtherProjectFee(BigDecimal otherProjectFee) {
        this.otherProjectFee = otherProjectFee;
    }

    public BigDecimal getFeesTaxes() {
        return feesTaxes;
    }

    public void setFeesTaxes(BigDecimal feesTaxes) {
        this.feesTaxes = feesTaxes;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getUnitProjectPercen() {
        return unitProjectPercen;
    }

    public void setUnitProjectPercen(BigDecimal unitProjectPercen) {
        this.unitProjectPercen = unitProjectPercen;
    }

    public BigDecimal getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(BigDecimal coveredArea) {
        this.coveredArea = coveredArea;
    }

    public BigDecimal getUnilateralIndicators() {
        return unilateralIndicators;
    }

    public void setUnilateralIndicators(BigDecimal unilateralIndicators) {
        this.unilateralIndicators = unilateralIndicators;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}