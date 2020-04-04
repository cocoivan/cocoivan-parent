package com.cocoivan.base.enumdata;

import java.util.HashMap;
import java.util.Map;

public enum EnumBlogType {
	//java
	JAVA(1000, "java"),
	//database
	DATABASE(2000, "database"),
	//miscellaneous
	MISCELLANEOUS(3000, "miscellaneous");
	private int value;
	private String desc;

	private EnumBlogType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	private final static Map<Integer, EnumBlogType> ENUM_MAP = new HashMap<>();

	static {
		registerEnum(EnumBlogType.values());
	}

	public static EnumBlogType fromValue(int valueType) {
		EnumBlogType enm = ENUM_MAP.get(valueType);
		return enm;
	}

	protected static void registerEnum(EnumBlogType[] enums) {
		if (enums != null) {
			for (EnumBlogType enm : enums) {
				int key = enm.getValue();
				EnumBlogType old = ENUM_MAP.put(key, enm);
				if (old != null) {
					throw new RuntimeException("repeated value:" + old.name());
				}
			}
		}
	}
}
