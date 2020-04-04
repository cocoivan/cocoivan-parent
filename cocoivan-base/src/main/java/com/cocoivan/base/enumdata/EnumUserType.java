package com.cocoivan.base.enumdata;

import java.util.HashMap;
import java.util.Map;

public enum EnumUserType {

	//master
	MASTER(1000, "master"),
	//person
	PERSON(2000, "person");
	private int value;
	private String desc;

	private EnumUserType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	private final static Map<Integer, EnumUserType> ENUM_MAP = new HashMap<>();

	static {
		registerEnum(EnumUserType.values());
	}

	public static EnumUserType fromValue(int valueType) {
		EnumUserType enm = ENUM_MAP.get(valueType);
		return enm;
	}

	protected static void registerEnum(EnumUserType[] enums) {
		if (enums != null) {
			for (EnumUserType enm : enums) {
				int key = enm.getValue();
				EnumUserType old = ENUM_MAP.put(key, enm);
				if (old != null) {
					throw new RuntimeException("repeated value:" + old.name());
				}
			}
		}
	}
}
