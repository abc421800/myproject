package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CostPriceLibrary {
    private String id;

    private String taskId;//任务id

    private String typeLibrary;//类型库/标识是综合单价库还是主材单价库或者其他单价库

    private String taskName;//任务名称

    private String taskCode;//审价任务编号

    private String taskPersonLiable;//经办人

    private String projectId;//项目id
    
    private String projectName;//项目名称
    
    private String contractId;//合同id

    private String contractName;//合同名称

    private String contractExeDepartment;//合同执行部门

    private String documentName;//往来文件名称

    private String name;//名称

    private String code;//

    private String feature;//清单特征/规格

    private String engineeringNumber;//工程数量/使用数量

    private String usePosition;//使用部位

    private String basis;//新增依据

    private String biddingBrand;//投标品牌

    private String useBrand;//使用品牌

    private String unit;//计量单位

    private BigDecimal contractingPrice;//承包单位申报单价

    private BigDecimal supervisorPrice;//监理单位审核单价

    private BigDecimal settlementPrice;//结算审价部审核单价

    private String description;//备注

    private Date createTime;//创建时间
    private String createTimeStr;

    private Date updateTime;//修改时间
    private String updateTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getTypeLibrary() {
        return typeLibrary;
    }

    public void setTypeLibrary(String typeLibrary) {
        this.typeLibrary = typeLibrary == null ? null : typeLibrary.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public String getTaskPersonLiable() {
        return taskPersonLiable;
    }

    public void setTaskPersonLiable(String taskPersonLiable) {
        this.taskPersonLiable = taskPersonLiable == null ? null : taskPersonLiable.trim();
    }

    public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getContractExeDepartment() {
        return contractExeDepartment;
    }

    public void setContractExeDepartment(String contractExeDepartment) {
        this.contractExeDepartment = contractExeDepartment == null ? null : contractExeDepartment.trim();
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName == null ? null : documentName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public String getEngineeringNumber() {
        return engineeringNumber;
    }

    public void setEngineeringNumber(String engineeringNumber) {
        this.engineeringNumber = engineeringNumber == null ? null : engineeringNumber.trim();
    }

    public String getUsePosition() {
        return usePosition;
    }

    public void setUsePosition(String usePosition) {
        this.usePosition = usePosition == null ? null : usePosition.trim();
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    public String getBiddingBrand() {
        return biddingBrand;
    }

    public void setBiddingBrand(String biddingBrand) {
        this.biddingBrand = biddingBrand == null ? null : biddingBrand.trim();
    }

    public String getUseBrand() {
        return useBrand;
    }

    public void setUseBrand(String useBrand) {
        this.useBrand = useBrand == null ? null : useBrand.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getContractingPrice() {
        return contractingPrice;
    }

    public void setContractingPrice(BigDecimal contractingPrice) {
        this.contractingPrice = contractingPrice;
    }

    public BigDecimal getSupervisorPrice() {
        return supervisorPrice;
    }

    public void setSupervisorPrice(BigDecimal supervisorPrice) {
        this.supervisorPrice = supervisorPrice;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

	public String getUpdateTimeStr() {
		if(updateTime==null){
			return updateTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(updateTime);
			return dateString;
		}	
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
    
}