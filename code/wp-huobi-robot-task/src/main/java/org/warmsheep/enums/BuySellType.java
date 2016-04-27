package org.warmsheep.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 交易类型 1限价买 2限价卖 3市价买 4市价卖
 */
public enum BuySellType {
	/**
	 * 1、限价买入
	 */
	BUY(1, "buy"),
	/**
	 * 2、限价卖出
	 */
	SELL(2, "sell"),
	/**
	 * 3、市价买入
	 */
	BUY_MARKET(3, "buy_market"),
	/**
	 * 4、市价卖出
	 */
	SELL_MARKET(4, "sell_market");
	
	
	
	private String value;
	private int key;

	private BuySellType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static BuySellType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (BuySellType at : BuySellType.values()) {
			if (at.key == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static BuySellType getByValue(String value) {
		if (StringUtils.isBlank(value))
			return null;
		for (BuySellType at : BuySellType.values()) {
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
