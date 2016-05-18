package org.warmsheep.dao.impl;

import org.warmsheep.dao.IMarketDao;
import org.warmsheep.entity.Ticker;


public class MarketDao implements IMarketDao{

	public void settingBTCMarket(Ticker ticker){
		this.btcTicker = ticker;
		this.btcTicker.setTime(System.currentTimeMillis());
	}
	
	public Ticker getBTCMarket(){
		return btcTicker;
	}
	
	public void settingLTCMarket(Ticker ticker){
		this.ltcTicker = ticker;
		this.ltcTicker.setTime(System.currentTimeMillis());
	}
	
	public Ticker getLTCMarket(){
		return ltcTicker;
	}
	
	
	private static Ticker btcTicker = null;
	private static Ticker ltcTicker = null;

}
