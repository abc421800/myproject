package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CostEnterprise {
    private String id;

    private String code;//企业编号

    private String simpleName;//简称

    private String name;//企业名称

    private String contacts;//联系人

    private String contactsPhone;//联系电话

    private String bidder;//投标负责人

    private String bidderPhone;//投标负责人电话

    private String fax;//传真

    private String email;//邮箱

    private String effectiveFlag;//是否有效

    private String batch;//入库批次

    private String description;//备注

    private String creater;//创建人

    private String address;//地址

    private Date createrTime;//创建时间

    private Integer deleteFlag;//是否删除标识

    private String telephone;//固话

    private String stationing;//是否驻场

    private Date enterpriseStart;//入库有效开始时间
    private String enterpriseStartStr;

    private Date enterpriseEnd;//入库有效结束时间
    private String enterpriseEndStr;
    //数据库没有的字段
    private String assessResultRk;
    private String yaohaoGradeRk;
    private String winNum;
    private BigDecimal serviceAmountRk;
    private String serviceEndTime;
    private String stationingStr;//是否驻场
    private String status;//状态
    private String r_year;//年

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getBidderPhone() {
        return bidderPhone;
    }

    public void setBidderPhone(String bidderPhone) {
        this.bidderPhone = bidderPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName == null ? null : simpleName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setEffectiveFlag(String effectiveFlag) {
        this.effectiveFlag = effectiveFlag == null ? null : effectiveFlag.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getEnterpriseStart() {
        return enterpriseStart;
    }

    public void setEnterpriseStart(Date enterpriseStart) {
        this.enterpriseStart = enterpriseStart;
    }

    public Date getEnterpriseEnd() {
        return enterpriseEnd;
    }

    public void setEnterpriseEnd(Date enterpriseEnd) {
        this.enterpriseEnd = enterpriseEnd;
    }

    public String getStationing() {
        return stationing;
    }

    public void setStationing(String stationing) {
        this.stationing = stationing;
    }

    public String getAssessResultRk() {
        return assessResultRk;
    }

    public void setAssessResultRk(String assessResultRk) {
        this.assessResultRk = assessResultRk;
    }

    public String getYaohaoGradeRk() {
        return yaohaoGradeRk;
    }

    public void setYaohaoGradeRk(String yaohaoGradeRk) {
        this.yaohaoGradeRk = yaohaoGradeRk;
    }

    public String getWinNum() {
        return winNum;
    }

    public void setWinNum(String winNum) {
        this.winNum = winNum;
    }

    public BigDecimal getServiceAmountRk() {
        return serviceAmountRk;
    }

    public void setServiceAmountRk(BigDecimal serviceAmountRk) {
        this.serviceAmountRk = serviceAmountRk;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getStationingStr() {
        return stationingStr;
    }

    public void setStationingStr(String stationingStr) {
        this.stationingStr = stationingStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getR_year() {
        return r_year;
    }

    public void setR_year(String r_year) {
        this.r_year = r_year;
    }

    public String getEnterpriseStartStr() {
        if (enterpriseStart == null) {
            return enterpriseStartStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(enterpriseStart);
            return dateString;
        }
    }

    public void setEnterpriseStartStr(String enterpriseStartStr) {
        this.enterpriseStartStr = enterpriseStartStr;
    }

    public String getEnterpriseEndStr() {
        if (enterpriseEnd == null) {
            return enterpriseEndStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(enterpriseEnd);
            return dateString;
        }
    }

    public void setEnterpriseEndStr(String enterpriseEndStr) {
        this.enterpriseEndStr = enterpriseEndStr;
    }


}