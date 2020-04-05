package com.cocoivan.blog.service.impl;

import com.cocoivan.base.enumdata.EnumBlogType;
import com.cocoivan.blog.domain.Blog;
import com.cocoivan.blog.domain.BlogExample;
import com.cocoivan.blog.mapper.BlogMapper;
import com.cocoivan.blog.service.BlogService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogMapper blogMapper;

	@Cacheable(value = "common", key = "'blog_'+#blogId", unless = "#blogId == null")
	public Blog getBlogById (Long blogId) {
		return blogMapper.selectByPrimaryKey(blogId);
	}

	@Cacheable(value = "common", key = "'blog_page'",condition = "#page == 1 or #page == null")
	public List<Blog> getBlogByPage (Integer page, Integer size) {
		int start = 0;
		if (size == null)
			size = 10;
		if (page != null) {
			page = page <= 0 ? 1 : page;
			start = (page - 1) * size;
		}
		BlogExample example = new BlogExample();
		example.setOrderByClause("blog_id desc limit "+ start + ","+ size);
		return blogMapper.selectByExampleWithBLOBs(example);
	}

	@Cacheable(value = "common", key = "'blog_all'")
	public List<Blog> getBlogs () {
		BlogExample example = new BlogExample();
		example.setOrderByClause("blog_id desc");
		return blogMapper.selectByExampleWithBLOBs(example);
	}

	@Transactional
	@Caching(evict={@CacheEvict(value = "common", key="'blog_page'",condition="#blog!=null")
			, @CacheEvict(value = "common", key="'blog_all'",condition="#blog!=null")
			, @CacheEvict(value = "common", key="'blog_'+#blog.blogId",condition="#blog.blogId!=null")})
	public int save (Blog blog) {
		if (blog == null) return 0;
		if (blog.getBlogId() == null) {
			return insert(blog);
		}
		return update(blog);
	}

	public int insert (Blog blog) {
		if (StringUtils.isEmpty(blog.getAuthor()))blog.setAuthor("Cocoivan");
		if (blog.getBlogType() == null)blog.setBlogType(EnumBlogType.MISCELLANEOUS.getValue());
		return blogMapper.insertSelective(blog);
	}

	public int update (Blog blog) {
		return blogMapper.updateByPrimaryKeySelective(blog);
	}
}
