package com.cocoivan.blog.domain;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
    private Long blogId;

    private String title;

    private String coverImg;

    private String shortBrief;

    private Integer blogType;

    private String author;

    private Date ctime;

    private Date mtime;

    private String content;

    private static final long serialVersionUID = 1L;

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

    public Integer getBlogType() {
        return blogType;
    }

    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
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
        sb.append(", blogId=").append(blogId);
        sb.append(", title=").append(title);
        sb.append(", coverImg=").append(coverImg);
        sb.append(", shortBrief=").append(shortBrief);
        sb.append(", blogType=").append(blogType);
        sb.append(", author=").append(author);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}