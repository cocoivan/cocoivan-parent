package com.cocoivan.blog.converter;

import com.cocoivan.blog.domain.Diary;
import com.cocoivan.blog.model.DiaryBriefVO;
import com.cocoivan.blog.model.DiaryVO;
import org.apache.commons.collections4.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DiaryConverter {

	public static DiaryBriefVO toDiaryBriefVO(Diary source) {
		if (source == null) return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DiaryBriefVO target = new DiaryBriefVO();
		target.setDiaryId(source.getDiaryId());
		target.setCoverImg(source.getCoverImg());
		target.setAuthor(source.getAuthor());
		target.setDiaryType(source.getDiaryType());
		target.setDate(format.format(source.getCtime()));
		target.setShortBrief(source.getShortBrief());
		target.setTitle(source.getTitle());
		return target;
	}

	public static DiaryVO toDiaryVO(Diary source) {
		if (source == null) return null;
		DiaryVO target = new DiaryVO();
		target.setAuthor(source.getAuthor());
		target.setContent(source.getContent());
		target.setCoverImg(source.getCoverImg());
		target.setCtime(source.getCtime());
		target.setDiaryId(source.getDiaryId());
		target.setDiaryType(source.getDiaryType());
		target.setMtime(source.getMtime());
		target.setShortBrief(source.getShortBrief());
		target.setTitle(source.getTitle());
		return target;
	}

	public static List<DiaryBriefVO> toDiaryBriefVO (List<Diary> sources) {
		List<DiaryBriefVO> diaryBriefVOs = new ArrayList<DiaryBriefVO>();
		if (CollectionUtils.isNotEmpty(sources)) {
			for (Diary diary : sources) {
				DiaryBriefVO diaryBriefVO = toDiaryBriefVO(diary);
				if (diaryBriefVO != null) diaryBriefVOs.add(diaryBriefVO);
			}
		}
		return diaryBriefVOs;
	}

	public static Diary toDiary(DiaryBriefVO source) {
		if (source == null) return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Diary target = new Diary();
		target.setDiaryId(source.getDiaryId());
		target.setCoverImg(source.getCoverImg());
		target.setAuthor(source.getAuthor());
		target.setDiaryType(source.getDiaryType());
		target.setShortBrief(source.getShortBrief());
		target.setTitle(source.getTitle());
		try {
			target.setCtime(format.parse(source.getDate()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return target;
	}

	public static Diary toDiary(DiaryVO source) {
		if (source == null) return null;
		Diary target = new Diary();
		target.setAuthor(source.getAuthor());
		target.setContent(source.getContent());
		target.setCoverImg(source.getCoverImg());
		target.setCtime(source.getCtime());
		target.setDiaryId(source.getDiaryId());
		target.setDiaryType(source.getDiaryType());
		target.setMtime(source.getMtime());
		target.setShortBrief(source.getShortBrief());
		target.setTitle(source.getTitle());
		return target;
	}
}
