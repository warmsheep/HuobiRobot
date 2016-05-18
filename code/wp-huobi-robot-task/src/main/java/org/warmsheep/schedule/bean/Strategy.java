package org.warmsheep.schedule.bean;

import java.math.BigDecimal;

/**
 * 策略基础类
 * @author Warmsheep
 *
 */
public class Strategy {

	private int coinType;		//货币类型
	private int transMode;		//交易模式	1、看涨 2、看空 3、观望
	
	private BigDecimal stopProfitPrice;		//止盈价格
	private BigDecimal stopLossPrice;		//止损价格
	
	private BigDecimal upCommingPrice;		//看涨入场价格
	private BigDecimal downCommingPrice;	//看空入场价格
	
	private BigDecimal transCount;			//交易数量
	
	/**
	 * 货币类型
	 * @return
	 */
	public int getCoinType() {
		return coinType;
	}
	/**
	 * 货币类型
	 * @param coinType
	 */
	public void setCoinType(int coinType) {
		this.coinType = coinType;
	}
	/**
	 * 交易模式	1、看涨 2、看空 3、观望
	 * @return
	 */
	public int getTransMode() {
		return transMode;
	}
	/**
	 * 交易模式	1、看涨 2、看空 3、观望
	 * @param transMode
	 */
	public void setTransMode(int transMode) {
		this.transMode = transMode;
	}
	/**
	 * 看涨入场价格
	 * @return
	 */
	public BigDecimal getUpCommingPrice() {
		return upCommingPrice;
	}
	/**
	 * 看涨入场价格
	 * @param upCommingPrice
	 */
	public void setUpCommingPrice(BigDecimal upCommingPrice) {
		this.upCommingPrice = upCommingPrice;
	}
	/**
	 * 看空入场价格
	 * @return
	 */
	public BigDecimal getDownCommingPrice() {
		return downCommingPrice;
	}
	/**
	 * 看空入场价格
	 * @param downCommingPrice
	 */
	public void setDownCommingPrice(BigDecimal downCommingPrice) {
		this.downCommingPrice = downCommingPrice;
	}
	/**
	 * 止盈价格
	 * @return
	 */
	public BigDecimal getStopProfitPrice() {
		return stopProfitPrice;
	}
	/**
	 * 止盈价格
	 * @param stopProfitPrice
	 */
	public void setStopProfitPrice(BigDecimal stopProfitPrice) {
		this.stopProfitPrice = stopProfitPrice;
	}
	/**
	 * 止损价格
	 * @return
	 */
	public BigDecimal getStopLossPrice() {
		return stopLossPrice;
	}
	/**
	 * 止损价格
	 * @param stopLossPrice
	 */
	public void setStopLossPrice(BigDecimal stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
	}
	/**
	 * 交易数量
	 * @return
	 */
	public BigDecimal getTransCount() {
		return transCount;
	}
	/**
	 * 交易数量
	 * @param transCount
	 */
	public void setTransCount(BigDecimal transCount) {
		this.transCount = transCount;
	}
	
	
}
