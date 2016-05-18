package org.warmsheep.util;

import org.warmsheep.entity.Ticker;
import org.warmsheep.vo.RealTimeData;

public class ConvertUtil {

	public static Ticker convertTicker(RealTimeData realTimeData){
		Ticker ticker = new Ticker();
		ticker.setBuy(realTimeData.getTicker().getBuy());
		ticker.setHigh(realTimeData.getTicker().getHigh());
		ticker.setLast(realTimeData.getTicker().getLast());
		ticker.setLow(realTimeData.getTicker().getLow());
		ticker.setOpen(realTimeData.getTicker().getOpen());
		ticker.setSell(realTimeData.getTicker().getSell());
		ticker.setSymbol(realTimeData.getTicker().getSymbol());
		ticker.setVol(realTimeData.getTicker().getVol());
		ticker.setTime(realTimeData.getTime());
		return ticker;
	}
}

