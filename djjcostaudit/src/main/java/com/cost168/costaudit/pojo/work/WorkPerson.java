package com.cost168.costaudit.pojo.work;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkPerson {
    private String id;

    private String enterpriseId;//驻场企业ID
    private String enterpriseName;//驻场企业ID

    private String account;//账号

    private String name;//驻场人员姓名

    private String sex;//性别

    private String phone;//联系电话

    private String email;//邮箱

    private String wechat;//微信号

    private String job;//驻场服务岗位

    private Date planStartTime;//计划驻场开始时间
    private String planStartTimeStr;

    private Date planEndTime;//计划驻场结束时间
    private String planEndTimeStr;

    private Date actualStartTime;//实际驻场开始时间
    private String actualStartTimeStr;

    private Date actualEndTime;//实际驻场结束时间
    private String actualEndTimeStr;

    private Float totalDay;//累计驻场天数

    private String effectiveFlag;//是否有效

    private String creater;//创建人

    private Date createTime;//创建时间
    private String createTimeStr;

    private String remark;//备注

    private String annualLeaveTotal;//总共可休年假天数

    private String annualLeaveUseup;//已经休了多少天年假

    private Date annualLeaveStartTime;//年假开始时间
    private String annualLeaveStartTimeStr;

    private Date annualLeaveEndTime;//年假结束时间
    private String annualLeaveEndTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Float getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Float totalDay) {
        this.totalDay = totalDay;
    }

    public String getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setEffectiveFlag(String effectiveFlag) {
        this.effectiveFlag = effectiveFlag == null ? null : effectiveFlag.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAnnualLeaveTotal() {
        return annualLeaveTotal;
    }

    public void setAnnualLeaveTotal(String annualLeaveTotal) {
        this.annualLeaveTotal = annualLeaveTotal;
    }

    public String getAnnualLeaveUseup() {
        return annualLeaveUseup;
    }

    public void setAnnualLeaveUseup(String annualLeaveUseup) {
        this.annualLeaveUseup = annualLeaveUseup;
    }

    public String getPlanStartTimeStr() {
        if (planStartTime == null) {
            return planStartTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(planStartTime);
            return dateString;
        }
    }

    public void setPlanStartTimeStr(String planStartTimeStr) {
        this.planStartTimeStr = planStartTimeStr;
    }

    public String getPlanEndTimeStr() {
        if (planEndTime == null) {
            return planEndTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(planEndTime);
            return dateString;
        }
    }

    public void setPlanEndTimeStr(String planEndTimeStr) {
        this.planEndTimeStr = planEndTimeStr;
    }

    public String getActualStartTimeStr() {
        if (actualStartTime == null) {
            return actualStartTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(actualStartTime);
            return dateString;
        }
    }

    public void setActualStartTimeStr(String actualStartTimeStr) {
        this.actualStartTimeStr = actualStartTimeStr;
    }

    public String getActualEndTimeStr() {
        if (actualEndTime == null) {
            return actualEndTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(actualEndTime);
            return dateString;
        }
    }

    public void setActualEndTimeStr(String actualEndTimeStr) {
        this.actualEndTimeStr = actualEndTimeStr;
    }

    public Date getAnnualLeaveStartTime() {
        return annualLeaveStartTime;
    }

    public void setAnnualLeaveStartTime(Date annualLeaveStartTime) {
        this.annualLeaveStartTime = annualLeaveStartTime;
    }

    public Date getAnnualLeaveEndTime() {
        return annualLeaveEndTime;
    }

    public String getAnnualLeaveStartTimeStr() {
        if (annualLeaveStartTime == null) {
            return annualLeaveStartTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(annualLeaveStartTime);
            return dateString;
        }
    }

    public void setAnnualLeaveStartTimeStr(String annualLeaveStartTimeStr) {
        this.annualLeaveStartTimeStr = annualLeaveStartTimeStr;
    }


    public void setAnnualLeaveEndTime(Date annualLeaveEndTime) {
        this.annualLeaveEndTime = annualLeaveEndTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAnnualLeaveEndTimeStr() {
        if (annualLeaveEndTime == null) {
            return annualLeaveEndTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(annualLeaveEndTime);
            return dateString;
        }
    }

    public void setAnnualLeaveEndTimeStr(String annualLeaveEndTimeStr) {
        this.annualLeaveEndTimeStr = annualLeaveEndTimeStr;
    }

	public String getCreateTimeStr() {
		 if (createTime == null) {
	            return createTimeStr;
	        } else {
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            String dateString = formatter.format(createTime);
	            return dateString;
	        }
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

}