package com.cocoivan.base.enumdata;

import java.util.HashMap;
import java.util.Map;

public enum EnumDiaryType {
	//diary
	DIARY(1000, "diary"),
	//photo
	PHOTO(2000, "photo"),
	//video
	VIDEO(3000, "video");
	private int value;
	private String desc;

	private EnumDiaryType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	private final static Map<Integer, EnumDiaryType> ENUM_MAP = new HashMap<>();

	static {
		registerEnum(EnumDiaryType.values());
	}

	public static EnumDiaryType fromValue(int valueType) {
		EnumDiaryType enm = ENUM_MAP.get(valueType);
		return enm;
	}

	protected static void registerEnum(EnumDiaryType[] enums) {
		if (enums != null) {
			for (EnumDiaryType enm : enums) {
				int key = enm.getValue();
				EnumDiaryType old = ENUM_MAP.put(key, enm);
				if (old != null) {
					throw new RuntimeException("repeated value:" + old.name());
				}
			}
		}
	}
}
