package org.warmsheep.dao;

import org.warmsheep.entity.Ticker;


public interface IMarketDao {

	public void settingBTCMarket(Ticker ticker);
	
	public Ticker getBTCMarket();
	
	public void settingLTCMarket(Ticker ticker);
	
	public Ticker getLTCMarket();
}
