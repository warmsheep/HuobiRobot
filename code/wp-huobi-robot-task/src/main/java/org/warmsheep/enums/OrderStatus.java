package org.warmsheep.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 订单状态
 */
public enum OrderStatus {
	
	/**
	 * 0、未成交
	 */
	NOT_FINISH(0, "未成交"),
	/**
	 * 1、部分成交
	 */
	PART_FINISH(1, "部分成交"),
	/**
	 * 2、已完成
	 */
	FINISH(2, "已完成"),
	/**
	 * 3、已取消
	 */
	CANCEL(3, "已取消"),
	/**
	 * 5、异常
	 */
	EXCEPTION(5, "异常"),
	/**
	 * 6、部分成交已取消
	 */
	PART_CANCEL(6, "部分成交已取消"),
	/**
	 * 7、队列中
	 */
	QUEUE(7, "队列中"),
	/**
	 * -1、未知
	 */
	UNKONW(-1,"未知");
	
	private String value;
	private int key;

	private OrderStatus(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static OrderStatus getByIndex(Integer index) {
		if (null == index)
			return null;
		for (OrderStatus at : OrderStatus.values()) {
			if (at.key == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static OrderStatus getByValue(String value) {
		if (StringUtils.isBlank(value))
			return null;
		for (OrderStatus at : OrderStatus.values()) {
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
