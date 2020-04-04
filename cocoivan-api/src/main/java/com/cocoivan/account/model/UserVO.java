package com.cocoivan.account.model;

public class UserVO {

	private Long uid;

	private String name;

	private String avatar;

	private Integer userType;

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
