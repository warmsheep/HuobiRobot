package org.warmsheep.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ticker implements Serializable {

	private BigDecimal open;	//开盘价
	
	private BigDecimal vol;		//成交量
	
	private String symbol;		//类型
	
	private BigDecimal last;	//当前价
	
	private BigDecimal buy;		//买1
	
	private BigDecimal sell;	//卖1
	
	private BigDecimal high;	//最高价
	
	private BigDecimal low;		//最低价
	
	private Long time;			//时间

	/**
	 * 开盘价
	 * @return
	 */
	public BigDecimal getOpen() {
		return open;
	}
	/**
	 * 开盘价
	 * @param open
	 */
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	/**
	 * 成交量
	 * @return
	 */
	public BigDecimal getVol() {
		return vol;
	}
	/**
	 * 成交量
	 * @param vol
	 */
	public void setVol(BigDecimal vol) {
		this.vol = vol;
	}
	/**
	 * 类型
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * 类型
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * 当前价
	 * @return
	 */
	public BigDecimal getLast() {
		return last;
	}
	/**
	 * 当前价
	 * @param last
	 */
	public void setLast(BigDecimal last) {
		this.last = last;
	}
	/**
	 * 买1
	 * @return
	 */
	public BigDecimal getBuy() {
		return buy;
	}
	/**
	 * 买1
	 * @param buy
	 */
	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}
	/**
	 * 卖1
	 * @return
	 */
	public BigDecimal getSell() {
		return sell;
	}
	/**
	 * 卖1
	 * @param sell
	 */
	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}
	/**
	 * 最高价
	 * @return
	 */
	public BigDecimal getHigh() {
		return high;
	}
	/**
	 * 最高价
	 * @param high
	 */
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	/**
	 * 最低价
	 * @return
	 */
	public BigDecimal getLow() {
		return low;
	}
	/**
	 * 最低价
	 * @param low
	 */
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	/**
	 * 时间
	 * @return
	 */
	public Long getTime() {
		return time;
	}
	/**
	 * 时间
	 * @param time
	 */
	public void setTime(Long time) {
		this.time = time;
	}
	
	
}
