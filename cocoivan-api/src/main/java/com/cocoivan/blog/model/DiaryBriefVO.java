package com.cocoivan.blog.model;

public class DiaryBriefVO {

	private Long diaryId;

	private String title;

	private String shortBrief;

	private Integer diaryType;

	private String coverImg;

	private String date;

	private String author;

	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortBrief() {
		return shortBrief;
	}

	public void setShortBrief(String shortBrief) {
		this.shortBrief = shortBrief;
	}

	public Integer getDiaryType() {
		return diaryType;
	}

	public void setDiaryType(Integer diaryType) {
		this.diaryType = diaryType;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
