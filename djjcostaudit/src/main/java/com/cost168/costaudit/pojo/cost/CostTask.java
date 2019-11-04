package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CostTask {
    private String id;

    private String documentId;//来往文件id

    private String projectId;//项目id

    private String projectName;//项目名称
    private String projectNameShow;//项目名称

    private String contractId;//合同id
    private String contractCongId;//从合同id

    private String contractName;//合同名称
    private String contractNameShow;
    
    private String code;//审价编号

    private String name;//审价任务名称

    private String auditPriceType;//审价类型

    private String auditPriceTypecn;//审价类型归类

    private BigDecimal contractAmount;//合同金额
    private BigDecimal contractAmount2;//合同金额

    private BigDecimal giveAmount;//送审金额

    private BigDecimal myAuditAmount;//我办审核金额

    private BigDecimal agencyAuditAmount;//中介审核金额

    private BigDecimal decideAmount;//定审金额

    private String finalizeNumber;//定案文号

    private String deliveryFlag;//是否外送

    private String entrustNumber;//委托单号

    private String auditPriceUnit;//审价单位

    private String status;//状态

    private String personLiable;//负责人

    private String contractType;//合同类型

    private Date receiveTime;//送审日期
    private String receiveTimeStr;

    private Date decideTime;//审定日期
    private String decideTimeStr;
    
    private String progressDescription;//进度概况

    private Integer giveNumber;//送审条数

    private Integer auditNumber;//审核条数

    private String inlineNumber;//内联单号

    private String agencyUnit;//中介单位

    private String coordinateFlag;//是否协调

    private String creater;
    
    private String checkExplain;//审核说明
    
    private String reviewReportn;//评审报告书
    
    private String umber;//份数
    private String number;//份数

    private Date createTime;//创建时间
    private String createTimeStr;
    
    private BigDecimal gcfAmount;
    
    private BigDecimal elfyAmount;
    
    private BigDecimal slfyAmount;
    
    private String whereabouts;//去向
    
    private String retrial;//退审文号以及原因
    
    private Date acceptanceTime;//预受理日期
    private String acceptanceTimeStr;
    
    private String financial;//发函财局
    
    private Date submissionTime;//资料送审日期
    private String submissionTimeStr;
    
    private String fixedFinancial;//已定审且不属于财政评审的结算发函财局备案文号
    
    private String approval;//审批表编号
    
    private Date deliveryTime;//送出日期
    private String deliveryTimeStr;
    
    private Date taskEndTime;
    private String taskEndTimeStr;
    //文件归属类别 项目 合同 审价任务
    private String category;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId == null ? null : documentId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuditPriceType() {
        return auditPriceType;
    }

    public void setAuditPriceType(String auditPriceType) {
        this.auditPriceType = auditPriceType == null ? null : auditPriceType.trim();
    }

    public String getAuditPriceTypecn() {
        return auditPriceTypecn;
    }

    public void setAuditPriceTypecn(String auditPriceTypecn) {
        this.auditPriceTypecn = auditPriceTypecn == null ? null : auditPriceTypecn.trim();
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getGiveAmount() {
        return giveAmount;
    }

    public void setGiveAmount(BigDecimal giveAmount) {
        this.giveAmount = giveAmount;
    }

    public BigDecimal getMyAuditAmount() {
        return myAuditAmount;
    }

    public void setMyAuditAmount(BigDecimal myAuditAmount) {
        this.myAuditAmount = myAuditAmount;
    }

    public BigDecimal getAgencyAuditAmount() {
        return agencyAuditAmount;
    }

    public void setAgencyAuditAmount(BigDecimal agencyAuditAmount) {
        this.agencyAuditAmount = agencyAuditAmount;
    }

    public BigDecimal getDecideAmount() {
        return decideAmount;
    }

    public void setDecideAmount(BigDecimal decideAmount) {
        this.decideAmount = decideAmount;
    }

    public String getFinalizeNumber() {
        return finalizeNumber;
    }

    public void setFinalizeNumber(String finalizeNumber) {
        this.finalizeNumber = finalizeNumber == null ? null : finalizeNumber.trim();
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag == null ? null : deliveryFlag.trim();
    }

    public String getEntrustNumber() {
        return entrustNumber;
    }

    public void setEntrustNumber(String entrustNumber) {
        this.entrustNumber = entrustNumber == null ? null : entrustNumber.trim();
    }

    public String getAuditPriceUnit() {
        return auditPriceUnit;
    }

    public void setAuditPriceUnit(String auditPriceUnit) {
        this.auditPriceUnit = auditPriceUnit == null ? null : auditPriceUnit.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable == null ? null : personLiable.trim();
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getDecideTime() {
        return decideTime;
    }

    public void setDecideTime(Date decideTime) {
        this.decideTime = decideTime;
    }

    public String getProgressDescription() {
        return progressDescription;
    }

    public void setProgressDescription(String progressDescription) {
        this.progressDescription = progressDescription == null ? null : progressDescription.trim();
    }

    public Integer getGiveNumber() {
        return giveNumber;
    }

    public void setGiveNumber(Integer giveNumber) {
        this.giveNumber = giveNumber;
    }

    public Integer getAuditNumber() {
        return auditNumber;
    }

    public void setAuditNumber(Integer auditNumber) {
        this.auditNumber = auditNumber;
    }

    public String getInlineNumber() {
        return inlineNumber;
    }

    public void setInlineNumber(String inlineNumber) {
        this.inlineNumber = inlineNumber == null ? null : inlineNumber.trim();
    }

    public String getAgencyUnit() {
        return agencyUnit;
    }

    public void setAgencyUnit(String agencyUnit) {
        this.agencyUnit = agencyUnit == null ? null : agencyUnit.trim();
    }

    public String getCoordinateFlag() {
        return coordinateFlag;
    }

    public void setCoordinateFlag(String coordinateFlag) {
        this.coordinateFlag = coordinateFlag == null ? null : coordinateFlag.trim();
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

	public String getReceiveTimeStr() {
		if(receiveTime==null){
			return receiveTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(receiveTime);
			return dateString;
		}	
	}

	public void setReceiveTimeStr(String receiveTimeStr) {
		this.receiveTimeStr = receiveTimeStr;
	}

	public String getDecideTimeStr() {
		if(decideTime==null){
			return decideTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(decideTime);
			return dateString;
		}	
	}

	public void setDecideTimeStr(String decideTimeStr) {
		this.decideTimeStr = decideTimeStr;
	}

	public String getCreateTimeStr() {
		if(createTime==null){
			return createTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(createTime);
			return dateString;
		}	
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public BigDecimal getGcfAmount() {
		return gcfAmount;
	}

	public void setGcfAmount(BigDecimal gcfAmount) {
		this.gcfAmount = gcfAmount;
	}

	public BigDecimal getElfyAmount() {
		return elfyAmount;
	}

	public void setElfyAmount(BigDecimal elfyAmount) {
		this.elfyAmount = elfyAmount;
	}

	public BigDecimal getSlfyAmount() {
		return slfyAmount;
	}

	public void setSlfyAmount(BigDecimal slfyAmount) {
		this.slfyAmount = slfyAmount;
	}

	public String getCheckExplain() {
		return checkExplain;
	}

	public void setCheckExplain(String checkExplain) {
		this.checkExplain = checkExplain;
	}

	public String getReviewReportn() {
		return reviewReportn;
	}

	public void setReviewReportn(String reviewReportn) {
		this.reviewReportn = reviewReportn;
	}

	public String getUmber() {
		return umber;
	}

	public void setUmber(String umber) {
		this.umber = umber;
	}

	public String getProjectNameShow() {
		return projectNameShow;
	}

	public void setProjectNameShow(String projectNameShow) {
		this.projectNameShow = projectNameShow;
	}

	public String getContractNameShow() {
		return contractNameShow;
	}

	public void setContractNameShow(String contractNameShow) {
		this.contractNameShow = contractNameShow;
	}

	public String getWhereabouts() {
		return whereabouts;
	}

	public void setWhereabouts(String whereabouts) {
		this.whereabouts = whereabouts;
	}

	public String getRetrial() {
		return retrial;
	}

	public void setRetrial(String retrial) {
		this.retrial = retrial;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContractCongId() {
		return contractCongId;
	}

	public void setContractCongId(String contractCongId) {
		this.contractCongId = contractCongId;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getTaskEndTimeStr() {
		if(taskEndTime==null){
			return taskEndTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(taskEndTime);
			return dateString;
		}	
	}

	public void setTaskEndTimeStr(String taskEndTimeStr) {
		this.taskEndTimeStr = taskEndTimeStr;
	}

	public Date getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}

	public String getFinancial() {
		return financial;
	}

	public void setFinancial(String financial) {
		this.financial = financial;
	}

	public String getAcceptanceTimeStr() {
		if(acceptanceTime==null){
			return acceptanceTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(acceptanceTime);
			return dateString;
		}
	}

	public void setAcceptanceTimeStr(String acceptanceTimeStr) {
		this.acceptanceTimeStr = acceptanceTimeStr;
	}

	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getSubmissionTimeStr() {
		if(submissionTime==null){
			return submissionTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(submissionTime);
			return dateString;
		}
	}

	public void setSubmissionTimeStr(String submissionTimeStr) {
		this.submissionTimeStr = submissionTimeStr;
	}

	public String getFixedFinancial() {
		return fixedFinancial;
	}

	public void setFixedFinancial(String fixedFinancial) {
		this.fixedFinancial = fixedFinancial;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryTimeStr() {
		if(deliveryTime==null){
			return deliveryTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(deliveryTime);
			return dateString;
		}
	}

	public void setDeliveryTimeStr(String deliveryTimeStr) {
		this.deliveryTimeStr = deliveryTimeStr;
	}

	public BigDecimal getContractAmount2() {
		return contractAmount2;
	}

	public void setContractAmount2(BigDecimal contractAmount2) {
		this.contractAmount2 = contractAmount2;
	}


    
}