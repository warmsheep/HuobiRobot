package org.warmsheep.entity;

import java.math.BigDecimal;

/**
 * 订单详情
 * @author Warmsheep
 *
 */
public class OrderInfo {

	private Long id;					//ID
	private Integer type;				//交易类型 1限价买 2限价卖 3市价买 4市价卖
	private BigDecimal order_price;		//委托价格
	private BigDecimal order_amount;	//委托数量
	private BigDecimal processed_amount;//已经完成的数量（市价买单，代表成交金额）
	private BigDecimal vot;				//交易额
	private BigDecimal fee;				//手续费
	private BigDecimal total;			//交易总额
	private Integer status;				//状态 0未成交　1部分成交　2已完成　3已取消 4废弃（该状态已不再使用） 5异常 6部分成交已取消 7队列中
	
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
	 * 交易额
	 * @return
	 */
	public BigDecimal getVot() {
		return vot;
	}
	/**
	 * 交易额
	 * @param vot
	 */
	public void setVot(BigDecimal vot) {
		this.vot = vot;
	}
	/**
	 * 手续费
	 * @return
	 */
	public BigDecimal getFee() {
		return fee;
	}
	/**
	 * 手续费
	 * @param fee
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	/**
	 * 交易总额
	 * @return
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * 交易总额
	 * @param total
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * 状态 0未成交　1部分成交　2已完成　3已取消 4废弃（该状态已不再使用） 5异常 6部分成交已取消 7队列中
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 状态 0未成交　1部分成交　2已完成　3已取消 4废弃（该状态已不再使用） 5异常 6部分成交已取消 7队列中
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
