package com.cocoivan.blog.converter;

import com.cocoivan.blog.domain.Blog;
import com.cocoivan.blog.model.BlogBriefVO;
import org.apache.commons.collections4.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BlogConverter {

	public static BlogBriefVO toBlogBriefVO(Blog source) {
		if (source == null) return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		BlogBriefVO target = new BlogBriefVO();
		target.setBlogId(source.getBlogId());
		target.setAuthor(source.getAuthor());
		target.setBlogType(source.getBlogType());
		target.setDate(format.format(source.getCtime()));
		target.setShortBrief(source.getShortBrief());
		target.setTitle(source.getTitle());
		return target;
	}

	public static List<BlogBriefVO> toBlogBriefVO (List<Blog> sources) {
		List<BlogBriefVO> blogBriefVOs = new ArrayList<BlogBriefVO>();
		if (CollectionUtils.isNotEmpty(sources)) {
			for (Blog blog : sources) {
				BlogBriefVO blogBriefVO = toBlogBriefVO(blog);
				if (blogBriefVO != null) blogBriefVOs.add(blogBriefVO);
			}
		}
		return blogBriefVOs;
	}

	public static Blog toBlog(BlogBriefVO source) {
		if (source == null) return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Blog target = new Blog();
		target.setBlogId(source.getBlogId());
		target.setAuthor(source.getAuthor());
		target.setBlogType(source.getBlogType());
		try {
			target.setCtime(format.parse(source.getDate()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		target.setShortBrief(source.getShortBrief());
		target.setTitle(source.getTitle());
		return target;
	}
}
