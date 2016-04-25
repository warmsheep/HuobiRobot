package org.warmsheep.huobi.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 货币种类
 */
public enum CoinType {
	/**
	 * 1、比特币
	 */
	BTC(1, "比特币"),
	/**
	 * 2、莱特币
	 */
	LTC(2, "莱特币");
	

	private String value;
	private int key;

	private CoinType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static CoinType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (CoinType at : CoinType.values()) {
			if (at.key == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static CoinType getByValue(String value) {
		if (StringUtils.isBlank(value))
			return null;
		for (CoinType at : CoinType.values()) {
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
