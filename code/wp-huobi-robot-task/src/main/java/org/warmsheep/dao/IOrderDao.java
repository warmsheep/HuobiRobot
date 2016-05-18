package org.warmsheep.dao;

import org.warmsheep.entity.OrderInfo;

public interface IOrderDao {

	/**
	 * 创建订单
	 * @param orderInfo
	 * @return
	 */
	public OrderInfo createOrder(OrderInfo orderInfo);
	
	public OrderInfo updateOrder(OrderInfo orderIno);
	
	/**
	 * 查询当前订单
	 * @return
	 */
	public OrderInfo getCurrentOrder();
	
	public void finishOrder();
}
