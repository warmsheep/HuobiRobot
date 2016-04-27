package org.warmsheep.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单
 * @author Warmsheep
 *
 */
public class OrderVo implements Serializable{

	private Long id;					//ID
	private Integer type;				//交易类型 1限价买 2限价卖 3市价买 4市价卖
	private BigDecimal order_price;		//委托价格
	private BigDecimal order_amount;	//委托数量
	private BigDecimal processed_amount;//已经完成的数量（市价买单，代表成交金额）
	private Long last_processed_time;	//最后成交时间
	private Long order_time;			//委托时间
	private Integer status;				//状态　0未成交　1部分成交　2已完成　3已取消
	
	/**
	 * ID
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 交易类型 1限价买 2限价卖 3市价买 4市价卖
	 * @return
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 交易类型 1限价买 2限价卖 3市价买 4市价卖
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 委托价格
	 * @return
	 */
	public BigDecimal getOrder_price() {
		return order_price;
	}
	/**
	 * 委托价格
	 * @param order_price
	 */
	public void setOrder_price(BigDecimal order_price) {
		this.order_price = order_price;
	}
	/**
	 * 委托数量
	 * @return
	 */
	public BigDecimal getOrder_amount() {
		return order_amount;
	}
	/**
	 * 委托数量
	 * @param order_amount
	 */
	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
	}
	/**
	 * 已经完成的数量（市价买单，代表成交金额）
	 * @return
	 */
	public BigDecimal getProcessed_amount() {
		return processed_amount;
	}
	/**
	 * 已经完成的数量（市价买单，代表成交金额）
	 * @param processed_amount
	 */
	public void setProcessed_amount(BigDecimal processed_amount) {
		this.processed_amount = processed_amount;
	}
	/**
	 * 最后成交时间
	 * @return
	 */
	public Long getLast_processed_time() {
		return last_processed_time;
	}
	/**
	 * 最后成交时间
	 * @param last_processed_time
	 */
	public void setLast_processed_time(Long last_processed_time) {
		this.last_processed_time = last_processed_time;
	}
	/**
	 * 委托时间
	 * @return
	 */
	public Long getOrder_time() {
		return order_time;
	}
	/**
	 * 委托时间
	 * @param order_time
	 */
	public void setOrder_time(Long order_time) {
		this.order_time = order_time;
	}
	/**
	 * 状态　0未成交　1部分成交　2已完成　3已取消
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 状态　0未成交　1部分成交　2已完成　3已取消
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
