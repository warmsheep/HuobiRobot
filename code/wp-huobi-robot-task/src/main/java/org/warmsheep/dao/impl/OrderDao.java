package org.warmsheep.dao.impl;

import org.warmsheep.dao.IOrderDao;
import org.warmsheep.entity.OrderInfo;

public class OrderDao implements IOrderDao {

	@Override
	public OrderInfo createOrder(OrderInfo orderInfo) {
		currentOrderInfo = orderInfo;
		return currentOrderInfo;
	}
	
	@Override
	public OrderInfo updateOrder(OrderInfo orderInfo) {
		currentOrderInfo = orderInfo;
		return currentOrderInfo;
	}

	@Override
	public OrderInfo getCurrentOrder() {
		return currentOrderInfo;
	}
	
	@Override
	public void finishOrder() {
		currentOrderInfo = null;
	}

	private static OrderInfo currentOrderInfo;

	
}
