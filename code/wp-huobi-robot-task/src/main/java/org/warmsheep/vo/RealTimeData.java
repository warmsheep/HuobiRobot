package org.warmsheep.vo;

import java.io.Serializable;

public class RealTimeData implements Serializable {

	private Long time;
	private TickerVo ticker;
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public TickerVo getTicker() {
		return ticker;
	}
	public void setTicker(TickerVo ticker) {
		this.ticker = ticker;
	}
	
	
}
