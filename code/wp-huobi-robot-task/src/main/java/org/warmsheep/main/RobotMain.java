package org.warmsheep.main;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.warmsheep.constants.PropertiesConstants;
import org.warmsheep.dao.IMarketDao;
import org.warmsheep.dao.impl.MarketDao;
import org.warmsheep.entity.Ticker;
import org.warmsheep.enums.BuySellType;
import org.warmsheep.enums.CoinType;
import org.warmsheep.enums.TransMode;
import org.warmsheep.enums.TransType;
import org.warmsheep.huobi.BaseService;
import org.warmsheep.huobi.HuobiService;
import org.warmsheep.schedule.bean.Strategy;
import org.warmsheep.util.ConvertUtil;
import org.warmsheep.util.DateUtils;
import org.warmsheep.vo.AccountInfoVo;
import org.warmsheep.vo.BuyMessageVo;
import org.warmsheep.vo.MessageVo;
import org.warmsheep.vo.OrderInfoVo;
import org.warmsheep.vo.OrderVo;
import org.warmsheep.vo.RealTimeData;
import org.warmsheep.vo.TickerVo;

import com.alibaba.fastjson.JSONObject;
import com.sun.mail.handlers.image_gif;

import freemarker.template.utility.DateUtil;

public class RobotMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(RobotMain.class);
	
	private static OrderInfoVo order;	//委托中的订单
	
	
	
	
	private static Strategy currentStrategy = null;
	private static BigDecimal ltcTransCount = BigDecimal.valueOf(500);
	
	public static void main(String[] args) {
		try
		{
			System.setProperty("user.timezone","Asia/Shanghai");
			TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			        "classpath:spring/spring-context.xml");
			context.start();
		}
		catch (Exception e)
		{
			LOGGER.error("== context start error:", e);
		}
		LOGGER.info("App Start!");
		HuobiService service = new HuobiService(PropertiesConstants.HUOBI_ACCESS_KEY,PropertiesConstants.HUOBI_SECRET_KEY,PropertiesConstants.HUOBI_API_URL);
		
		//看涨策略
		Strategy upStrategy = new Strategy();
		upStrategy.setTransMode(TransMode.UP.getKey());
		upStrategy.setCoinType(CoinType.LTC.getKey());
		upStrategy.setStopProfitPrice(BigDecimal.valueOf(26.3));
		upStrategy.setStopLossPrice(BigDecimal.valueOf(24.9));
		
		//看跌策略
		Strategy downStrategy = new Strategy();
		downStrategy.setTransMode(TransMode.DOWN.getKey());
		downStrategy.setCoinType(CoinType.LTC.getKey());
		downStrategy.setStopProfitPrice(BigDecimal.valueOf(25.6));
		downStrategy.setStopLossPrice(BigDecimal.valueOf(27.4));
		
		
		//观望策略
		Strategy centerStrategy  = new Strategy();
		centerStrategy.setTransMode(TransMode.CENTER.getKey());
		centerStrategy.setCoinType(CoinType.LTC.getKey());
		centerStrategy.setUpCommingPrice(BigDecimal.valueOf(25.3));
		centerStrategy.setDownCommingPrice(BigDecimal.valueOf(26.7));
			
		currentStrategy = downStrategy;
		while (true) {
			String realTimeJson =  null;
			BigDecimal btcCurrentPrice = null;
			BigDecimal ltcCurrentPrice = null;
			try {
//				realTimeJson = service.getRealTimeData(CoinType.LTC.getKey());
//				RealTimeData btcRealTimeData = JSONObject.parseObject(realTimeJson,RealTimeData.class);
//				Ticker btcTicker = ConvertUtil.convertTicker(btcRealTimeData);
//				marketDao.settingLTCMarket(btcTicker);
				
				realTimeJson = service.getRealTimeData(CoinType.LTC.getKey());
				RealTimeData ltcRealTimeData = JSONObject.parseObject(realTimeJson,RealTimeData.class);
				Ticker ltcTicker = ConvertUtil.convertTicker(ltcRealTimeData);
				marketDao.settingLTCMarket(ltcTicker);
				
				
				
//				btcCurrentPrice = btcTicker.getLast();
//				LOGGER.info("[" + DateUtils.getReqDate() + "],LTC当前价格:" + btcCurrentPrice);
				
				ltcCurrentPrice = ltcTicker.getLast();
				LOGGER.info("当前模式:"+TransMode.getByIndex(currentStrategy.getTransMode()).getValue()+"[" + DateUtils.getReqDate() + "],LTC当前价格:" + ltcCurrentPrice);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}
			
			
			try {
				//看涨,达到止盈，卖出
				if(currentStrategy.getTransMode() == TransMode.UP.getKey() && ltcCurrentPrice.compareTo(currentStrategy.getStopProfitPrice()) >= 0){
					//订单未结束,取消订单
					if(order != null){
						boolean cancelResult = cancelOrderInfo(service);
						if(cancelResult == true){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(ltcTransCount);
						order.setOrder_price(ltcCurrentPrice);
						order.setType(BuySellType.SELL.getKey());
						order.setStatus(0);
						// 市价卖出
						String sellResultJson = service.sell(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看涨获利卖出!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
					
					
				} 
				//看涨，下跌，止损，卖出
				else if(currentStrategy.getTransMode() == TransMode.UP.getKey() && ltcCurrentPrice.compareTo(currentStrategy.getStopLossPrice()) <= 0){
					//订单未结束,取消订单
					if(order != null){
						boolean cancelResult = cancelOrderInfo(service);
						if(cancelResult == true){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(ltcTransCount);
						order.setOrder_price(ltcCurrentPrice);
						order.setType(BuySellType.SELL.getKey());
						order.setStatus(0);
						// 限价卖出
						String sellResultJson = service.sell(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看涨止损卖出!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
				}
				//看空，价格下跌，盈利，买入
				else if(currentStrategy.getTransMode() == TransMode.DOWN.getKey() && ltcCurrentPrice.compareTo(currentStrategy.getStopProfitPrice()) <= 0){
					//订单未结束,取消订单
					if(order != null){
						boolean cancelResult = cancelOrderInfo(service);
						if(cancelResult == true){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(ltcTransCount);
						order.setOrder_price(ltcCurrentPrice);
						order.setType(BuySellType.BUY.getKey());
						order.setStatus(0);
						// 提交限价单接口 1btc 2ltc
						String buyResultJson = service.buy(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看空获利买入!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
				}
				//看空，价格上涨，止损，买入
				else if(currentStrategy.getTransMode() == TransMode.DOWN.getKey() && ltcCurrentPrice.compareTo(currentStrategy.getStopLossPrice()) >= 0){
					//订单未结束,取消订单
					if(order != null){
						boolean cancelResult = cancelOrderInfo(service);
						if(cancelResult == true){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(ltcTransCount);
						order.setOrder_price(ltcCurrentPrice);
						order.setType(BuySellType.BUY.getKey());
						order.setStatus(0);
						// 提交限价单接口 1btc 2ltc
						String buyResultJson = service.buy(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看空获利卖出!id" + order.getId() + ",价格:"+order.getOrder_price());
					}
				}
				//观望，低点，看涨买入
				else if(currentStrategy.getTransMode() == TransMode.CENTER.getKey() && ltcCurrentPrice.compareTo(currentStrategy.getUpCommingPrice()) <= 0){
					//订单未结束,取消订单
					if(order != null){
						boolean cancelResult = cancelOrderInfo(service);
						if(cancelResult == true){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(ltcTransCount);
						order.setOrder_price(ltcCurrentPrice);
						order.setType(BuySellType.BUY.getKey());
						order.setStatus(0);
						// 提交限价单接口 1btc 2ltc
						String buyResultJson = service.buy(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("观望低点做多买入!id" + order.getId() + ",价格:"+order.getOrder_price());
					}
				}
				//观望，高点，看空卖出
				else if(currentStrategy.getTransMode() == TransMode.CENTER.getKey() && ltcCurrentPrice.compareTo(currentStrategy.getDownCommingPrice()) >= 0){
					//订单未结束,取消订单
					if(order != null){
						boolean cancelResult = cancelOrderInfo(service);
						if(cancelResult == true){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(ltcTransCount);
						order.setOrder_price(ltcCurrentPrice);
						order.setType(BuySellType.SELL.getKey());
						order.setStatus(0);
						// 限价卖出
						String sellResultJson = service.sell(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("观望高点做空卖出!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
				}
				if(order != null){
					Thread.sleep(2000L);
					OrderInfoVo realTimeOrderInfo = getOrderInfo(service);
					if(realTimeOrderInfo.getStatus() == 2){
						LOGGER.info("订单成交!id" + order.getId() + ",价格:"+order.getOrder_price());
						if(TransMode.DOWN.getKey() == currentStrategy.getTransMode() || TransMode.UP.getKey() == currentStrategy.getTransMode()){
							currentStrategy = centerStrategy;
						} else {
							
						}
						order = null;
					} 
				}
				
				Thread.sleep(5000L);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
	
	}
	
	/**
	 * 获取订单
	 * @param service
	 * @return
	 * @throws Exception
	 */
	private static OrderInfoVo getOrderInfo(HuobiService service) throws Exception{
		String orderInfoJson = service.getOrderInfo(CoinType.LTC.getKey(), order.getId(), TransType.ORDER_INFO.getValue());
		OrderInfoVo realTimeOrderInfo = JSONObject.parseObject(orderInfoJson,OrderInfoVo.class);
		return realTimeOrderInfo;
	}
	
	/**
	 * 取消订单
	 * @param service
	 * @return
	 * @throws Exception
	 */
	private static boolean cancelOrderInfo(HuobiService service) throws Exception{
		OrderInfoVo realTimeOrderInfo = getOrderInfo(service);
		if(realTimeOrderInfo.getStatus() != 2){
			LOGGER.info("取消订单!id" + order.getId());
			String cancelResult = service.cancelOrder(CoinType.LTC.getKey(), order.getId(), TransType.CANCEL_ORDER.getValue());
			LOGGER.info("取消订单结果:" + cancelResult);
		}
		return true;
	}
	
	private static IMarketDao marketDao = new MarketDao();
}
