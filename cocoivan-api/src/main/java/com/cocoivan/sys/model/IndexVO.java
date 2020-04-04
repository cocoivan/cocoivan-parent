package com.cocoivan.sys.model;

import java.util.List;

public class IndexVO {

	private UserVO userVO;

	private String bannerDescription;

	private List<BlogBriefVO> blogBriefVOS;

	private List<DiaryBriefVO> diaryBriefVOS;

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public String getBannerDescription() {
		return bannerDescription;
	}

	public void setBannerDescription(String bannerDescription) {
		this.bannerDescription = bannerDescription;
	}

	public List<BlogBriefVO> getBlogBriefVOS() {
		return blogBriefVOS;
	}

	public void setBlogBriefVOS(List<BlogBriefVO> blogBriefVOS) {
		this.blogBriefVOS = blogBriefVOS;
	}

	public List<DiaryBriefVO> getDiaryBriefVOS() {
		return diaryBriefVOS;
	}

	public void setDiaryBriefVOS(List<DiaryBriefVO> diaryBriefVOS) {
		this.diaryBriefVOS = diaryBriefVOS;
	}
}
