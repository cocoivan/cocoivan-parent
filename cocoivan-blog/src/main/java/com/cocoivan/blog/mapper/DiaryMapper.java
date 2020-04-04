package com.cocoivan.blog.mapper;

import com.cocoivan.blog.domain.Diary;
import com.cocoivan.blog.domain.DiaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiaryMapper {
    long countByExample(DiaryExample example);

    int deleteByExample(DiaryExample example);

    int deleteByPrimaryKey(Long diaryId);

    int insert(Diary record);

    int insertSelective(Diary record);

    List<Diary> selectByExampleWithBLOBs(DiaryExample example);

    List<Diary> selectByExample(DiaryExample example);

    Diary selectByPrimaryKey(Long diaryId);

    int updateByExampleSelective(@Param("record") Diary record,
			@Param("example") DiaryExample example);

    int updateByExampleWithBLOBs(@Param("record") Diary record,
			@Param("example") DiaryExample example);

    int updateByExample(@Param("record") Diary record, @Param("example") DiaryExample example);

    int updateByPrimaryKeySelective(Diary record);

    int updateByPrimaryKeyWithBLOBs(Diary record);

    int updateByPrimaryKey(Diary record);

    void batchInsert(@Param("items") List<Diary> items);
}