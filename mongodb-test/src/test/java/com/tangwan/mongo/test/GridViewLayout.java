package com.tangwan.mongo.test;

import java.io.Serializable;

import org.bson.types.ObjectId;
//映射
public class GridViewLayout implements Serializable{
	private static final long serialVersionUID = -2767742790675928589L;
	/*
	 * 布局id
	 * */
	private ObjectId   id;
	/* 
	 * 布局名称
	 * */
	private String layoutName;
	/*
	 * 当前登录用户的ID
	 * */
	private String userId;
	/*
	 * 当前登录用户的ID
	 * */
	private String layoutContent;
	/*
	 * 是否默认首选
	 * */
	private Integer isDefault;//1 默认   0  非默认
	/*
	 *当前列表的ID
	 * */
	private String gridViewId;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getLayoutName() {
		return layoutName;
	}
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getGridViewId() {
		return gridViewId;
	}
	public void setGridViewId(String gridViewId) {
		this.gridViewId = gridViewId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLayoutContent() {
		return layoutContent;
	}
	public void setLayoutContent(String layoutContent) {
		this.layoutContent = layoutContent;
	}
}
