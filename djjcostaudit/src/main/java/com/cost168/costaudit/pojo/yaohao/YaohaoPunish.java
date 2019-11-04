package com.cost168.costaudit.pojo.yaohao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YaohaoPunish {
    private String id;

    private String enterpriseCode;

    private String punishFlag;
    

    private String punishYear;

    private String content;

    private String handleOpinion;

    private Date executeStartTime;
    private String executeStartTimeStr;

    private Date executeEndTime;
    private String executeEndTimeStr;

    private String status;

    private String creater;

    private Date creareTime;
    private String creareTimeStr;

    private String remark;
    
    private Integer deleteFlag;//是否删除标识
    
    //用来显示
    private String enterpriseId;
    private String enterpriseName;
    private String enterpriseStatus;
    private String assessResult;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getPunishFlag() {
        return punishFlag;
    }

    public void setPunishFlag(String punishFlag) {
        this.punishFlag = punishFlag == null ? null : punishFlag.trim();
    }

    public String getPunishYear() {
        return punishYear;
    }

    public void setPunishYear(String punishYear) {
        this.punishYear = punishYear == null ? null : punishYear.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHandleOpinion() {
        return handleOpinion;
    }

    public void setHandleOpinion(String handleOpinion) {
        this.handleOpinion = handleOpinion == null ? null : handleOpinion.trim();
    }

    public Date getExecuteStartTime() {
        return executeStartTime;
    }

    public void setExecuteStartTime(Date executeStartTime) {
        this.executeStartTime = executeStartTime;
    }

    public Date getExecuteEndTime() {
        return executeEndTime;
    }

    public void setExecuteEndTime(Date executeEndTime) {
        this.executeEndTime = executeEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreareTime() {
        return creareTime;
    }

    public void setCreareTime(Date creareTime) {
        this.creareTime = creareTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
	
	public String getCreateTimeStr() {
        if (creareTime == null) {
            return creareTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(creareTime);
            return dateString;
        }
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.creareTimeStr = createTimeStr;
    }

	public String getExecuteStartTimeStr() {
		if (executeStartTime == null) {
            return executeStartTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(executeStartTime);
            return dateString;
        }
	}

	public void setExecuteStartTimeStr(String executeStartTimeStr) {
		this.executeStartTimeStr = executeStartTimeStr;
	}

	public String getExecuteEndTimeStr() {
		if (executeEndTime == null) {
            return executeEndTimeStr;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(executeEndTime);
            return dateString;
        }
	}

	public void setExecuteEndTimeStr(String executeEndTimeStr) {
		this.executeEndTimeStr = executeEndTimeStr;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseStatus() {
		return enterpriseStatus;
	}

	public void setEnterpriseStatus(String enterpriseStatus) {
		this.enterpriseStatus = enterpriseStatus;
	}

	public String getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
    
}