package org.warmsheep.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 交易模式
 */
public enum TransMode {
	/**
	 * 1、看涨
	 */
	UP(1, "up"),
	/**
	 * 2、看跌
	 */
	DOWN(2, "down"),
	/**
	 * 3、观望
	 */
	CENTER(3, "center");
	
	private String value;
	private int key;

	private TransMode(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static TransMode getByIndex(Integer index) {
		if (null == index)
			return null;
		for (TransMode at : TransMode.values()) {
			if (at.key == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static TransMode getByValue(String value) {
		if (StringUtils.isBlank(value))
			return null;
		for (TransMode at : TransMode.values()) {
			if (at.value.equals(value))
				return at;
		}
		return null;
	}

	@Override
	public String toString() {
		return this.key + ":" + this.value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}
