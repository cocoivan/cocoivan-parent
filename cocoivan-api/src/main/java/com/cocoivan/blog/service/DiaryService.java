package com.cocoivan.blog.service;

import com.cocoivan.blog.domain.Diary;

import java.util.List;

public interface DiaryService {

	Diary getDiaryById (Long diaryId);

	List<Diary> getDiaryByPage (Integer page, Integer size);

	List<Diary> getDiarys ();

	int save (Diary diary);

	int insert (Diary diary);

	int update (Diary diary);
}
