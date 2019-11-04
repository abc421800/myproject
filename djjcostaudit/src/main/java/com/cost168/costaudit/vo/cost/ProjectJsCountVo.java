package com.cost168.costaudit.vo.cost;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectJsCountVo {
	private String projectId;
	private String projectName;
	private String projectCode;
	private String contractId;
	private String taskId;
	private String contractCode;
	private String contractName;
	private String partyB;//合同乙方
	private BigDecimal contractAmount;//合同金额
	private BigDecimal giveAmount;//送审金额
	//是否有乙方申报结算金额
	private String giveFlag;
	private BigDecimal myAuditAmount;//我办审核金额
	private BigDecimal agencyAuditAmount;//中介审核金额
	private BigDecimal decideAmount;//定审金额
	private BigDecimal decideAmountPercent;//定审金额占合同金额百分比
	private String personLiable;//责任人/经办人
	private String ownerApproval;//财局/业主批文
	private Date decideTime;//审定日期
	private String partyBContacts;//乙方联系人
	private String executiveDepartment;//合同执行部门
	private String executiveDepartmentContacts;//合同执行部门经办人联系方式
	private String deliveryFlag;//是否外送
	private String decideFlag;//是否定审
	private String contractType;//合同类型
	private String entrustNumber;//委托单号
	private String auditPriceUnit;//评审单位
	private String auditPriceUnitContacts;//评审单位联系人
	private Date sendTime;//资料送审日期
	private Date acceptTime;//资料接收日期
    private String returnReason;//退审原因
    private String memo;//备注
    private String reviewReport;//评审报告书
    private String number;//份数
    private String recordNumber;//已定审且不属于财政评审的结算发函财局备案文号
    private String priority;//序号
    private BigDecimal jsCountPercent;//合同结算完成率”=∑合同数（结算任务）/∑（“需要结算”的合同数）
    private String jsProgress;//结算概况：调取结算审核任务列“退审文号及原因”及审核说明）
    private String mainFlag;//合同主/从属性：主合同、从合同
    private Date acceptanceTime;//预受理日期
    private String financial;//发函财局
    private String status;//任务状态
    private String checkExplain;//审核说明
    private String progressDescription;//任务备注
    private String settlement;
    //新增
    private BigDecimal contractAmountPro;//合同金额
	//是否有审价任务
	private int temp;

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getTemp() {
		return temp;
	}

	public String getGiveFlag() {
		return giveFlag;
	}

	public void setGiveFlag(String giveFlag) {
		this.giveFlag = giveFlag;

	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getSettlement() {
		return settlement;
	}

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getPartyB() {
		return partyB;
	}
	public void setPartyB(String partyB) {
		this.partyB = partyB;
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
	public BigDecimal getDecideAmountPercent() {
		return decideAmountPercent;
	}
	public void setDecideAmountPercent(BigDecimal decideAmountPercent) {
		this.decideAmountPercent = decideAmountPercent;
	}
	
	public String getPersonLiable() {
		return personLiable;
	}
	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	public Date getDecideTime() {
		return decideTime;
	}
	public void setDecideTime(Date decideTime) {
		this.decideTime = decideTime;
	}
	public String getPartyBContacts() {
		return partyBContacts;
	}
	public void setPartyBContacts(String partyBContacts) {
		this.partyBContacts = partyBContacts;
	}
	public String getExecutiveDepartment() {
		return executiveDepartment;
	}
	public void setExecutiveDepartment(String executiveDepartment) {
		this.executiveDepartment = executiveDepartment;
	}
	public String getDeliveryFlag() {
		return deliveryFlag;
	}
	public void setDeliveryFlag(String deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}
	public String getDecideFlag() {
		return decideFlag;
	}
	public void setDecideFlag(String decideFlag) {
		this.decideFlag = decideFlag;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getEntrustNumber() {
		return entrustNumber;
	}
	public void setEntrustNumber(String entrustNumber) {
		this.entrustNumber = entrustNumber;
	}
	public String getAuditPriceUnit() {
		return auditPriceUnit;
	}
	public void setAuditPriceUnit(String auditPriceUnit) {
		this.auditPriceUnit = auditPriceUnit;
	}
	public String getAuditPriceUnitContacts() {
		return auditPriceUnitContacts;
	}
	public void setAuditPriceUnitContacts(String auditPriceUnitContacts) {
		this.auditPriceUnitContacts = auditPriceUnitContacts;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReviewReport() {
		return reviewReport;
	}
	public void setReviewReport(String reviewReport) {
		this.reviewReport = reviewReport;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getExecutiveDepartmentContacts() {
		return executiveDepartmentContacts;
	}
	public void setExecutiveDepartmentContacts(String executiveDepartmentContacts) {
		this.executiveDepartmentContacts = executiveDepartmentContacts;
	}
	public String getOwnerApproval() {
		return ownerApproval;
	}
	public void setOwnerApproval(String ownerApproval) {
		this.ownerApproval = ownerApproval;
	}
	public String getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public BigDecimal getJsCountPercent() {
		return jsCountPercent;
	}
	public void setJsCountPercent(BigDecimal jsCountPercent) {
		this.jsCountPercent = jsCountPercent;
	}
	public String getJsProgress() {
		return jsProgress;
	}
	public void setJsProgress(String jsProgress) {
		this.jsProgress = jsProgress;
	}
	public String getMainFlag() {
		return mainFlag;
	}
	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCheckExplain() {
		return checkExplain;
	}
	public void setCheckExplain(String checkExplain) {
		this.checkExplain = checkExplain;
	}
	public String getProgressDescription() {
		return progressDescription;
	}
	public void setProgressDescription(String progressDescription) {
		this.progressDescription = progressDescription;
	}

	public BigDecimal getContractAmountPro() {
		return contractAmountPro;
	}

	public void setContractAmountPro(BigDecimal contractAmountPro) {
		this.contractAmountPro = contractAmountPro;
	}
    
}
