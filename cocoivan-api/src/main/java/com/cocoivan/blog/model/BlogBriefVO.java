package com.cocoivan.blog.model;

public class BlogBriefVO {

	private Long blogId;

	private String title;

	private String shortBrief;

	private Integer blogType;

	private String date;

	private String author;

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
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

	public Integer getBlogType() {
		return blogType;
	}

	public void setBlogType(Integer blogType) {
		this.blogType = blogType;
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
