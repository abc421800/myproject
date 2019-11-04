package com.cost168.costaudit.pojo.cost;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CostDocument {
    private String id;

    private String symbol;//文号

    private String name;

    private String comeGoFlag;//来往标志

    private String comeGoUnit;//来往单位

    private String auditPriceFlag;//是否审价

    private Date documentTime;
    
    private String documentTimeStr;

    private String personLiable;//负责人

    private String registrant;

    private Date registrantTime;
    
    private String registrantTimeStr;

    private String description;

    private String opinion;
    
    private String number;
    
    //用来映射值
    private String contractId;
    private String contractName;
    private String projectId;
    private String projectName;
    private String taskName;
    private String dpctRelationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComeGoFlag() {
        return comeGoFlag;
    }

    public void setComeGoFlag(String comeGoFlag) {
        this.comeGoFlag = comeGoFlag == null ? null : comeGoFlag.trim();
    }

    public String getComeGoUnit() {
        return comeGoUnit;
    }

    public void setComeGoUnit(String comeGoUnit) {
        this.comeGoUnit = comeGoUnit == null ? null : comeGoUnit.trim();
    }

    public String getAuditPriceFlag() {
        return auditPriceFlag;
    }

    public void setAuditPriceFlag(String auditPriceFlag) {
        this.auditPriceFlag = auditPriceFlag == null ? null : auditPriceFlag.trim();
    }

    public Date getDocumentTime() {
        return documentTime;
    }

    public void setDocumentTime(Date documentTime) {
        this.documentTime = documentTime;
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable == null ? null : personLiable.trim();
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant == null ? null : registrant.trim();
    }

    public Date getRegistrantTime() {
        return registrantTime;
    }

    public void setRegistrantTime(Date registrantTime) {
        this.registrantTime = registrantTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

	public String getDocumentTimeStr() {
		if(documentTime==null){
			return documentTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(documentTime);
			return dateString;
		}		
	}

	public void setDocumentTimeStr(String documentTimeStr) {
		this.documentTimeStr = documentTimeStr;
	}

	public String getRegistrantTimeStr() {
		if(registrantTime==null){
			return registrantTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(registrantTime);
			return dateString;
		}	
	}

	public void setRegistrantTimeStr(String registrantTimeStr) {
		this.registrantTimeStr = registrantTimeStr;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDpctRelationId() {
		return dpctRelationId;
	}

	public void setDpctRelationId(String dpctRelationId) {
		this.dpctRelationId = dpctRelationId;
	}
    
}