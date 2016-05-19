package org.warmsheep.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.warmsheep.entity.OrderInfo;
import org.warmsheep.entity.Ticker;
import org.warmsheep.entity.User;
import org.warmsheep.vo.AccountInfoVo;
import org.warmsheep.vo.OrderInfoVo;
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
	
	public static OrderInfo convertOrderIno(OrderInfoVo orderInfoVo){
		OrderInfo orderInfo = new OrderInfo();
		try {
			BeanUtils.copyProperties(orderInfo, orderInfoVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return orderInfo;
	}
	
	public static User convertUser(AccountInfoVo accountInfoVo,User user){
		user.setAvailableBtcDisplay(accountInfoVo.getAvailable_btc_display());
		user.setAvailableCnyDisplay(accountInfoVo.getAvailable_cny_display());
		user.setAvailableLtcDisplay(accountInfoVo.getAvailable_ltc_display());
		user.setFrozenBtcDisplay(accountInfoVo.getFrozen_btc_display());
		user.setFrozenCnyDisplay(accountInfoVo.getFrozen_cny_display());
		user.setFrozenLtcDisplay(accountInfoVo.getFrozen_ltc_display());
		user.setLoanBtcDisplay(accountInfoVo.getLoan_btc_display());
		user.setLoanCnyDisplay(accountInfoVo.getLoan_cny_display());
		user.setLoanLtcDisplay(accountInfoVo.getLoan_ltc_display());
		user.setTotal(accountInfoVo.getTotal());
		user.setNetAsset(accountInfoVo.getNet_asset());
		return user;
	}
}

