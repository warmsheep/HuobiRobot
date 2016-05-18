package org.warmsheep.schedule;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.warmsheep.constants.PropertiesConstants;
import org.warmsheep.dao.IOrderDao;
import org.warmsheep.dao.impl.OrderDao;
import org.warmsheep.entity.OrderInfo;
import org.warmsheep.entity.Ticker;
import org.warmsheep.enums.BuySellType;
import org.warmsheep.enums.CoinType;
import org.warmsheep.enums.OrderStatus;
import org.warmsheep.enums.TransMode;
import org.warmsheep.enums.TransType;
import org.warmsheep.huobi.HuobiService;
import org.warmsheep.schedule.bean.Strategy;
import org.warmsheep.vo.BuyMessageVo;

import com.alibaba.fastjson.JSONObject;

public class BasicStrategySchedule {

	public int execute(Ticker currentTicker, Strategy currentStrategy){
		try {
			BigDecimal ltcCurrentPrice = currentTicker.getLast();
			OrderInfo order = orderDao.getCurrentOrder();
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
					order = new OrderInfo();
					order.setOrder_amount(currentStrategy.getTransCount());
					order.setOrder_price(ltcCurrentPrice);
					order.setType(BuySellType.SELL.getKey());
					order.setStatus(0);
					// 市价卖出
					String sellResultJson = service.sell(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
					BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
					order.setId(buyMessageVo.getId());
					orderDao.createOrder(order);
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
					order = new OrderInfo();
					order.setOrder_amount(currentStrategy.getTransCount());
					order.setOrder_price(ltcCurrentPrice);
					order.setType(BuySellType.SELL.getKey());
					order.setStatus(0);
					// 限价卖出
					String sellResultJson = service.sell(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
					BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
					order.setId(buyMessageVo.getId());
					orderDao.createOrder(order);
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
					order = new OrderInfo();
					order.setOrder_amount(currentStrategy.getTransCount());
					order.setOrder_price(ltcCurrentPrice);
					order.setType(BuySellType.BUY.getKey());
					order.setStatus(0);
					// 提交限价单接口 1btc 2ltc
					String buyResultJson = service.buy(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
					BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
					order.setId(buyMessageVo.getId());
					orderDao.createOrder(order);
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
					order = new OrderInfo();
					order.setOrder_amount(currentStrategy.getTransCount());
					order.setOrder_price(ltcCurrentPrice);
					order.setType(BuySellType.BUY.getKey());
					order.setStatus(0);
					// 提交限价单接口 1btc 2ltc
					String buyResultJson = service.buy(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
					BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
					order.setId(buyMessageVo.getId());
					orderDao.createOrder(order);
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
					order = new OrderInfo();
					order.setOrder_amount(currentStrategy.getTransCount());
					order.setOrder_price(ltcCurrentPrice);
					order.setType(BuySellType.BUY.getKey());
					order.setStatus(0);
					// 提交限价单接口 1btc 2ltc
					String buyResultJson = service.buy(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
					BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
					order.setId(buyMessageVo.getId());
					orderDao.createOrder(order);
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
					order = new OrderInfo();
					order.setOrder_amount(currentStrategy.getTransCount());
					order.setOrder_price(ltcCurrentPrice);
					order.setType(BuySellType.SELL.getKey());
					order.setStatus(0);
					// 限价卖出
					String sellResultJson = service.sell(CoinType.LTC.getKey(), ltcCurrentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
					BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
					order.setId(buyMessageVo.getId());
					orderDao.createOrder(order);
					LOGGER.info("观望高点做空卖出!id" + order.getId()+ ",价格:"+order.getOrder_price());
				}
			}
			
			if(order != null){
				Thread.sleep(2000L);
				OrderInfo realTimeOrderInfo = getOrderInfo(service);
				if(realTimeOrderInfo.getStatus() == 2){
					LOGGER.info("订单成交!id" + order.getId() + ",价格:"+order.getOrder_price());
					orderDao.finishOrder();
				} else {
					orderDao.updateOrder(realTimeOrderInfo);
				}
				return realTimeOrderInfo.getStatus();
			} else {
				return OrderStatus.UNKONW.getKey();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OrderStatus.UNKONW.getKey();
	}
	
	/**
	 * 取消订单
	 * @param service
	 * @return
	 * @throws Exception
	 */
	private boolean cancelOrderInfo(HuobiService service) throws Exception{
		OrderInfo realTimeOrderInfo = getOrderInfo(service);
		OrderInfo order = orderDao.getCurrentOrder();
		if(realTimeOrderInfo.getStatus() != 2){
			LOGGER.info("取消订单!id" + order.getId());
			String cancelResult = service.cancelOrder(CoinType.LTC.getKey(), order.getId(), TransType.CANCEL_ORDER.getValue());
			LOGGER.info("取消订单结果:" + cancelResult);
		}
		return true;
	}
	
	/**
	 * 获取订单
	 * @param service
	 * @return
	 * @throws Exception
	 */
	private OrderInfo getOrderInfo(HuobiService service) throws Exception{
		OrderInfo order = orderDao.getCurrentOrder();
		String orderInfoJson = service.getOrderInfo(CoinType.LTC.getKey(), order.getId(), TransType.ORDER_INFO.getValue());
		OrderInfo realTimeOrderInfo = JSONObject.parseObject(orderInfoJson,OrderInfo.class);
		return realTimeOrderInfo;
	}
	
	private IOrderDao orderDao = new OrderDao();
	private HuobiService service = new HuobiService(PropertiesConstants.HUOBI_ACCESS_KEY,PropertiesConstants.HUOBI_SECRET_KEY,PropertiesConstants.HUOBI_API_URL);
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicStrategySchedule.class);
}
