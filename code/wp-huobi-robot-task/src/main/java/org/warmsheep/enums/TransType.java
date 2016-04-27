package org.warmsheep.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 交易类型
 */
public enum TransType {
	/**
	 * 1、限价买入
	 */
	BUY(1, "buy"),
	/**
	 * 2、市价买入
	 */
	BUY_MARKET(2, "buy_market"),
	/**
	 * 3、取消订单
	 */
	CANCEL_ORDER(3, "cancel_order"),
	/**
	 * 4、查询账户信息
	 */
	ACCOUNT_INFO(4, "get_account_info"),
	/**
	 * 5、查询个人最新10条成交订单
	 */
	NEW_DEAL_ORDERS(5, "get_new_deal_orders"),
	/**
	 * 6、根据trade_id查询oder_id
	 */
	ORDER_ID_BY_TRADE_ID(6, "get_order_id_by_trade_id"),
	/**
	 * 7、获取所有正在进行的委托
	 */
	GET_ORDERS(7, "get_orders"),
	/**
	 * 8、获取订单详情
	 */
	ORDER_INFO(8, "order_info"),
	/**
	 * 9、限价卖出
	 */
	SELL(9, "sell"),
	/**
	 * 10、市价卖出
	 */
	SELL_MARKET(10, "sell_market");
	
	private String value;
	private int key;

	private TransType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static TransType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (TransType at : TransType.values()) {
			if (at.key == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static TransType getByValue(String value) {
		if (StringUtils.isBlank(value))
			return null;
		for (TransType at : TransType.values()) {
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
