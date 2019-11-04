package com.cost168.costaudit.pojo.work;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkProcessApply {
    private String id;

    private String code;

    private String proposer;
    private String proposerName;

    private String nextperson;
    private String nextpersonName;

    private String enterpriseId;
    private String enterpriseName;

    private String status;

    private Date createTime;
    private String createTimeStr;

    private String content;

    private String type;
    
    private String morAftAll;

    private Date leaveStartTime;
    private String leaveStartTimeStr;

    private Date leaveEndTime;
	private String leaveEndTimeStr;
    
    private Float leaveDays;
    
    private Float kxnj;

    private String relatedId;
    private String relatedName;

    private String remark;


    private String redesign;//重启标记

    
    
    //数据库没有字段
    private String opinion;//意见
    private String proJob;
    private String proPhone;//申请人联系电话
    private String annualLeaveSure;//可休年假


    public String getRedesign() {
        return redesign;
    }

    public void setRedesign(String redesign) {
        this.redesign = redesign;
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

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public String getNextperson() {
        return nextperson;
    }

    public void setNextperson(String nextperson) {
        this.nextperson = nextperson == null ? null : nextperson.trim();
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(Date leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Date leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public Float getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Float leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId == null ? null : relatedId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getLeaveStartTimeStr() {
		if(leaveStartTime==null){
			return leaveStartTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(leaveStartTime);
			return dateString;
		}	
	}

	public void setLeaveStartTimeStr(String leaveStartTimeStr) {
		this.leaveStartTimeStr = leaveStartTimeStr;
	}

	public String getLeaveEndTimeStr() {
		if(leaveEndTime==null){
			return leaveStartTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(leaveEndTime);
			return dateString;
		}	
	}

	public void setLeaveEndTimeStr(String leaveEndTimeStr) {
		this.leaveEndTimeStr = leaveEndTimeStr;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getMorAftAll() {
		return morAftAll;
	}

	public void setMorAftAll(String morAftAll) {
		this.morAftAll = morAftAll;
	}

	public String getNextpersonName() {
		return nextpersonName;
	}

	public void setNextpersonName(String nextpersonName) {
		this.nextpersonName = nextpersonName;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getCreateTimeStr() {
		if(createTime==null){
			return createTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(createTime);
			return dateString;
		}	
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getProJob() {
		return proJob;
	}

	public void setProJob(String proJob) {
		this.proJob = proJob;
	}

	public String getProPhone() {
		return proPhone;
	}

	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}

	public String getAnnualLeaveSure() {
		return annualLeaveSure;
	}

	public void setAnnualLeaveSure(String annualLeaveSure) {
		this.annualLeaveSure = annualLeaveSure;
	}

	public Float getKxnj() {
		return kxnj;
	}

	public void setKxnj(Float kxnj) {
		this.kxnj = kxnj;
	}
    
}