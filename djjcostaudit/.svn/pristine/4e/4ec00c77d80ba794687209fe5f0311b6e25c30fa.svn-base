package com.cost168.costaudit.pojo.cost;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CostProjectPeriod {
    private String id;

    private String projectId;

    private String name;

    private Date nodeTime;
    private String nodeTimeStr;

    private Integer num;

    private String description;

    private String updatePerson;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getNodeTime() {
        return nodeTime;
    }

    public void setNodeTime(Date nodeTime) {
        this.nodeTime = nodeTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getNodeTimeStr() {
		if(nodeTime==null){
			return nodeTimeStr;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(nodeTime);
			return dateString;
		}	
	}

	public void setNodeTimeStr(String nodeTimeStr) {
		this.nodeTimeStr = nodeTimeStr;
	}
    
}