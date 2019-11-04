package com.cost168.costaudit.pojo.common;

import java.util.List;

public class Combotree {

	private String id;
	private String parentId;
	private String text;
	private String state;
	private Boolean checked;
	private List<Combotree> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		if(parentId==null){
			this.setState("closed");
		}
		this.parentId = parentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Combotree> getChildren() {
		return children;
	}
	public void setChildren(List<Combotree> children) {
		this.children = children;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
}
