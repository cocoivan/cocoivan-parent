package com.cocoivan.blog.model;

import java.util.Date;

public class DiaryVO {

	private Long diaryId;

	private String title;

	private String coverImg;

	private String shortBrief;

	private Integer diaryType;

	private String author;

	private Date ctime;

	private Date mtime;

	private String content;

	private static final long serialVersionUID = 1L;

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
		this.title = title == null ? null : title.trim();
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg == null ? null : coverImg.trim();
	}

	public String getShortBrief() {
		return shortBrief;
	}

	public void setShortBrief(String shortBrief) {
		this.shortBrief = shortBrief == null ? null : shortBrief.trim();
	}

	public Integer getDiaryType() {
		return diaryType;
	}

	public void setDiaryType(Integer diaryType) {
		this.diaryType = diaryType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", diaryId=").append(diaryId);
		sb.append(", title=").append(title);
		sb.append(", coverImg=").append(coverImg);
		sb.append(", shortBrief=").append(shortBrief);
		sb.append(", diaryType=").append(diaryType);
		sb.append(", author=").append(author);
		sb.append(", ctime=").append(ctime);
		sb.append(", mtime=").append(mtime);
		sb.append(", content=").append(content);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}
