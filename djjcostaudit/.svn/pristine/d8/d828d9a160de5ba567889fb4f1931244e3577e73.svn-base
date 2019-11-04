package com.cost168.costaudit.pojo.work;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkRegister {
    private String id;

    private String personId;

    private String enterpriseId;

    private String regYearMonth;

    private String regYear;

    private String regMonth;

    private String morning;

    private String afternoon;

    private String regDay;

    private Float workingDay;

    private Date createTime;
    
    private String creater;

    private Date updateTime;

    private String remark;
    
    private String leaveprocess;

    private Date registerTime;
    private String registerTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getRegYearMonth() {
        return regYearMonth;
    }

    public void setRegYearMonth(String regYearMonth) {
        this.regYearMonth = regYearMonth == null ? null : regYearMonth.trim();
    }

    public String getRegYear() {
        return regYear;
    }

    public void setRegYear(String regYear) {
        this.regYear = regYear == null ? null : regYear.trim();
    }

    public String getRegMonth() {
        return regMonth;
    }

    public void setRegMonth(String regMonth) {
        this.regMonth = regMonth == null ? null : regMonth.trim();
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning == null ? null : morning.trim();
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon == null ? null : afternoon.trim();
    }

    public String getRegDay() {
        return regDay;
    }

    public void setRegDay(String regDay) {
        this.regDay = regDay == null ? null : regDay.trim();
    }

    public Float getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(Float workingDay) {
        this.workingDay = workingDay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

	public String getRegisterTimeStr() {
		if(registerTime==null){
			return registerTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(registerTime);
			return dateString;
		}	
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getLeaveprocess() {
		return leaveprocess;
	}

	public void setLeaveprocess(String leaveprocess) {
		this.leaveprocess = leaveprocess;
	}
}