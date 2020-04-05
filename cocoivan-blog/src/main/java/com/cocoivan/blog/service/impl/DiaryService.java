package com.cocoivan.blog.service.impl;

import com.example.demo.domain.Diary;
import com.example.demo.domain.DiaryExample;
import com.example.demo.enumdata.EnumDiaryType;
import com.example.demo.mapper.DiaryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiaryService {

	@Resource
	private DiaryMapper diaryMapper;

	@Cacheable(value = "common", key = "'diary_'+#diaryId", unless = "#diaryId == null")
	public Diary getDiaryById (Long diaryId) {
		return diaryMapper.selectByPrimaryKey(diaryId);
	}

	@Cacheable(value = "common", key = "'diary_page'",condition = "#page == 1 or #page == null")
	public List<Diary> getDiaryByPage (Integer page, Integer size) {
		int start = 0;
		if (size == null)
			size = 10;
		if (page != null) {
			page = page <= 0 ? 1 : page;
			start = (page - 1) * size;
		}
		DiaryExample example = new DiaryExample();
		example.setOrderByClause("diary_id desc limit "+ start + ","+ size);
		return diaryMapper.selectByExampleWithBLOBs(example);
	}

	@Cacheable(value = "common", key = "'diary_all'")
	public List<Diary> getDiarys () {
		DiaryExample example = new DiaryExample();
		example.setOrderByClause("diary_id desc");
		return diaryMapper.selectByExampleWithBLOBs(example);
	}

	@Transactional
	@Caching(evict={@CacheEvict(value = "common", key="'diary_page'",condition="#diary!=null")
			, @CacheEvict(value = "common", key="'diary_all'",condition="#diary!=null")
			, @CacheEvict(value = "common", key="'diary_'+#diary.diaryId",condition="#diary.diaryId!=null")})
	public int save (Diary diary) {
		if (diary == null) return 0;
		if (diary.getDiaryId() == null) {
			return insert(diary);
		}
		return update(diary);
	}

	public int insert (Diary diary) {
		if (StringUtils.isBlank(diary.getAuthor()))diary.setAuthor("Cocoivan");
		diary.setDiaryType(EnumDiaryType.VIDEO.getValue());
		return diaryMapper.insertSelective(diary);
	}

	public int update (Diary diary) {
		return diaryMapper.updateByPrimaryKeySelective(diary);
	}
}
