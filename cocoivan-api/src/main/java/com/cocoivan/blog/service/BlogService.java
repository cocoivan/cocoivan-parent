package com.cocoivan.blog.service;

import com.cocoivan.blog.domain.Blog;

import java.util.List;

public interface BlogService {

	Blog getBlogById (Long blogId);

	List<Blog> getBlogByPage (Integer page, Integer size);

	List<Blog> getBlogs ();

	int save (Blog blog);

	int insert (Blog blog);

	int update (Blog blog);
}
