package org.warmsheep.main;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.warmsheep.constants.PropertiesConstants;
import org.warmsheep.enums.BuySellType;
import org.warmsheep.enums.CoinType;
import org.warmsheep.enums.TransType;
import org.warmsheep.huobi.BaseService;
import org.warmsheep.huobi.HuobiService;
import org.warmsheep.util.DateUtils;
import org.warmsheep.vo.AccountInfoVo;
import org.warmsheep.vo.BuyMessageVo;
import org.warmsheep.vo.MessageVo;
import org.warmsheep.vo.OrderInfoVo;
import org.warmsheep.vo.OrderVo;
import org.warmsheep.vo.RealTimeData;
import org.warmsheep.vo.TickerVo;

import com.alibaba.fastjson.JSONObject;

import freemarker.template.utility.DateUtil;

public class RobotMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(RobotMain.class);
	
	private static OrderInfoVo order;	//委托中的订单
	
	private static int trans_mode = 1; //1、看涨 2、看空
	
	//看涨模式下，盈利卖出价
	private static BigDecimal trans_mode_up_make_money_price = BigDecimal.valueOf(3055);
	//看涨模式下，止损卖出价
	private static BigDecimal trans_mode_up_stop_loss_price = BigDecimal.valueOf(2960);
	
	//看空模式下，盈利买入价
	private static BigDecimal trans_mode_down_make_money_price = BigDecimal.valueOf(2910);
	//看空模式下，止损买入价
	private static BigDecimal trans_mode_down_stop_loss_price = BigDecimal.valueOf(3080);
	
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
		try {
			
			while (true) {
				String realTimeJson = service.getRealTimeData();
				RealTimeData realTimeData = JSONObject.parseObject(realTimeJson,RealTimeData.class);
				BigDecimal currentPrice = realTimeData.getTicker().getLast();
				LOGGER.info("[" + DateUtils.getReqDate() + "],当前价格:" + currentPrice);
				//看涨,达到止盈，卖出
				if(trans_mode == 1 && currentPrice.compareTo(trans_mode_up_make_money_price) >= 0){
					//订单未结束,取消订单
					if(order != null){
						LOGGER.info("取消订单!id" + order.getId());
						String cancelResult = service.cancelOrder(CoinType.BTC.getKey(), order.getId(), TransType.CANCEL_ORDER.getValue());
						MessageVo messageVo = JSONObject.parseObject(cancelResult,MessageVo.class);
						if(messageVo.getResult().equals("success")){
							order = null;
						}
						
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(BigDecimal.valueOf(10));
						order.setOrder_price(trans_mode_up_make_money_price);
						order.setType(BuySellType.SELL.getKey());
						order.setStatus(0);
						// 市价卖出
						String sellResultJson = service.sell(CoinType.BTC.getKey(), currentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看涨获利卖出!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
					
					
				} 
				//看涨，下跌，止损，卖出
				else if(trans_mode == 1 && currentPrice.compareTo(trans_mode_up_stop_loss_price) <= 0){
					//订单未结束,取消订单
					if(order != null){
						String cancelResult = service.cancelOrder(CoinType.BTC.getKey(), order.getId(), TransType.CANCEL_ORDER.getValue());
						MessageVo messageVo = JSONObject.parseObject(cancelResult,MessageVo.class);
						if(messageVo.getResult().equals("success")){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(BigDecimal.valueOf(10));
						order.setOrder_price(trans_mode_up_stop_loss_price);
						order.setType(BuySellType.SELL.getKey());
						order.setStatus(0);
						// 限价卖出
						String sellResultJson = service.sell(CoinType.BTC.getKey(), currentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.SELL.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(sellResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看涨止损卖出!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
				}
				//看空，价格下跌，盈利，买入
				else if(trans_mode == 2 && currentPrice.compareTo(trans_mode_down_make_money_price) <= 0){
					//订单未结束,取消订单
					if(order != null){
						String cancelResult = service.cancelOrder(CoinType.BTC.getKey(), order.getId(), TransType.CANCEL_ORDER.getValue());
						MessageVo messageVo = JSONObject.parseObject(cancelResult,MessageVo.class);
						if(messageVo.getResult().equals("success")){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(BigDecimal.valueOf(10));
						order.setOrder_price(trans_mode_up_stop_loss_price);
						order.setType(BuySellType.BUY.getKey());
						order.setStatus(0);
						// 提交限价单接口 1btc 2ltc
						String buyResultJson = service.buy(CoinType.BTC.getKey(), currentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看空获利买入!id" + order.getId()+ ",价格:"+order.getOrder_price());
					}
				}
				//看空，价格上涨，止损，买入
				else if(trans_mode == 2 && currentPrice.compareTo(trans_mode_down_stop_loss_price) >= 0){
					//订单未结束,取消订单
					if(order != null){
						String cancelResult = service.cancelOrder(CoinType.BTC.getKey(), order.getId(), TransType.CANCEL_ORDER.getValue());
						MessageVo messageVo = JSONObject.parseObject(cancelResult,MessageVo.class);
						if(messageVo.getResult().equals("success")){
							order = null;
						}
					}
					if(order == null){
						order = new OrderInfoVo();
						order.setOrder_amount(BigDecimal.valueOf(10));
						order.setOrder_price(trans_mode_up_stop_loss_price);
						order.setType(BuySellType.BUY.getKey());
						order.setStatus(0);
						// 提交限价单接口 1btc 2ltc
						String buyResultJson = service.buy(CoinType.BTC.getKey(), currentPrice.toString(), order.getOrder_amount().toString(), null, null, TransType.BUY.getValue());
						BuyMessageVo buyMessageVo = JSONObject.parseObject(buyResultJson,BuyMessageVo.class);
						order.setId(buyMessageVo.getId());
						LOGGER.info("看空获利卖出!id" + order.getId() + ",价格:"+order.getOrder_price());
					}
				}
				if(order != null){
					Thread.sleep(2000L);
					String orderInfoJson = service.getOrderInfo(CoinType.BTC.getKey(), order.getId(), TransType.ORDER_INFO.getValue());
					OrderInfoVo realTimeOrderInfo = JSONObject.parseObject(orderInfoJson,OrderInfoVo.class);
					if(realTimeOrderInfo.getStatus() == 2){
						LOGGER.info("订单成交!id" + order.getId() + ",价格:"+order.getOrder_price());
						if(trans_mode == 1){
							trans_mode = 2;
						} else {
							trans_mode = 1;
						}
						
						order = null;
					}
				}
				
				Thread.sleep(5000L);
				
			}
				

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
