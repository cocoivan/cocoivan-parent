package com.cocoivan.blog.mapper;

import com.cocoivan.blog.domain.Blog;
import com.cocoivan.blog.domain.BlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Long blogId);

    int updateByExampleSelective(@Param("record") Blog record,
			@Param("example") BlogExample example);

    int updateByExampleWithBLOBs(@Param("record") Blog record,
			@Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    void batchInsert(@Param("items") List<Blog> items);
}