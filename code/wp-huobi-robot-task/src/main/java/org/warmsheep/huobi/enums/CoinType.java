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
	

	private String name;
	private int index;

	private CoinType(int index, String name) {
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static CoinType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (CoinType at : CoinType.values()) {
			if (at.index == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static CoinType getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (CoinType at : CoinType.values()) {
			if (at.name.equals(name))
				return at;
		}
		return null;
	}

	@Override
	public String toString() {
		return this.index + ":" + this.name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
