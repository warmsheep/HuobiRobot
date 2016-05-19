package org.warmsheep.main;

import java.math.BigDecimal;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.warmsheep.constants.PropertiesConstants;
import org.warmsheep.dao.IMarketDao;
import org.warmsheep.dao.IOrderDao;
import org.warmsheep.dao.IUserDao;
import org.warmsheep.dao.impl.MarketDao;
import org.warmsheep.dao.impl.OrderDao;
import org.warmsheep.entity.Ticker;
import org.warmsheep.entity.User;
import org.warmsheep.enums.CoinType;
import org.warmsheep.enums.OrderStatus;
import org.warmsheep.enums.TransMode;
import org.warmsheep.enums.TransType;
import org.warmsheep.framework.db.exception.BaseCoreException;
import org.warmsheep.huobi.HuobiService;
import org.warmsheep.schedule.BasicStrategySchedule;
import org.warmsheep.schedule.bean.Strategy;
import org.warmsheep.service.IUserService;
import org.warmsheep.util.ConvertUtil;
import org.warmsheep.util.DateUtils;
import org.warmsheep.vo.AccountInfoVo;
import org.warmsheep.vo.RealTimeData;

import com.alibaba.fastjson.JSONObject;

public class RobotMain {

	
	
	private static Strategy currentStrategy = null;
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try
		{
			System.setProperty("user.timezone","Asia/Shanghai");
			TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
			context = new ClassPathXmlApplicationContext(
			        "classpath:spring/spring-context.xml");
			context.start();
		}
		catch (Exception e)
		{
			LOGGER.error("== context start error:", e);
		}
		LOGGER.info("App Start!");
		IUserService userService = context.getBean(IUserService.class);
		User user = null;
		HuobiService service = null;
		
		try {
			user = userService.getById(1L);
			service = new HuobiService(user.getHuobiAccessKey(),user.getHuobiSecretKey(),PropertiesConstants.HUOBI_API_URL);
			String accountInfoJson = service.getAccountInfo(TransType.ACCOUNT_INFO.getValue());
			AccountInfoVo accountInfoVo = JSONObject.parseObject(accountInfoJson,AccountInfoVo.class);
			user = ConvertUtil.convertUser(accountInfoVo, user);
			userService.updateById(user);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		//看涨策略
		Strategy upStrategy = new Strategy();
		upStrategy.setTransMode(TransMode.UP.getKey());
		upStrategy.setCoinType(CoinType.LTC.getKey());
		upStrategy.setStopProfitPrice(BigDecimal.valueOf(26.3));
		upStrategy.setStopLossPrice(BigDecimal.valueOf(24.9));
		upStrategy.setTransCount(BigDecimal.valueOf(500));
		
		//看跌策略
		Strategy downStrategy = new Strategy();
		downStrategy.setTransMode(TransMode.DOWN.getKey());
		downStrategy.setCoinType(CoinType.LTC.getKey());
		downStrategy.setStopProfitPrice(BigDecimal.valueOf(25.6));
		downStrategy.setStopLossPrice(BigDecimal.valueOf(27.4));
		downStrategy.setTransCount(BigDecimal.valueOf(500));
		
		
		//观望策略
		Strategy centerStrategy  = new Strategy();
		centerStrategy.setTransMode(TransMode.CENTER.getKey());
		centerStrategy.setCoinType(CoinType.LTC.getKey());
		centerStrategy.setUpCommingPrice(BigDecimal.valueOf(25.2));
		centerStrategy.setDownCommingPrice(BigDecimal.valueOf(26.7));
		centerStrategy.setTransCount(BigDecimal.valueOf(500));
			
		currentStrategy = centerStrategy;
		while (true) {
			String realTimeJson =  null;
			BigDecimal ltcCurrentPrice = null;
			Ticker ltcTicker = null;
			try {
				realTimeJson = service.getRealTimeData(CoinType.LTC.getKey());
				RealTimeData ltcRealTimeData = JSONObject.parseObject(realTimeJson,RealTimeData.class);
				ltcTicker = ConvertUtil.convertTicker(ltcRealTimeData);
				marketDao.settingLTCMarket(ltcTicker);
				
				ltcCurrentPrice = ltcTicker.getLast();
				LOGGER.info("当前模式:"+TransMode.getByIndex(currentStrategy.getTransMode()).getValue()+"[" + DateUtils.getReqTime() + "],LTC当前价格:" + ltcCurrentPrice);
				
			} catch (Exception e) {
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
			int orderStatus = -1;
			try {
				orderStatus = basicStrategySchedule.execute(ltcTicker, currentStrategy);
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(orderStatus == OrderStatus.FINISH.getKey()){
				if(currentStrategy.getTransMode() == TransMode.UP.getKey() || currentStrategy.getTransMode() == TransMode.DOWN.getKey()){
					currentStrategy = centerStrategy;
				}
				String accountInfoJson;
				try {
					accountInfoJson = service.getAccountInfo(TransType.ACCOUNT_INFO.getValue());
					AccountInfoVo accountInfoVo = JSONObject.parseObject(accountInfoJson,AccountInfoVo.class);
					user = ConvertUtil.convertUser(accountInfoVo, user);
					userService.updateById(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(RobotMain.class);
	private static BasicStrategySchedule basicStrategySchedule = new BasicStrategySchedule();
	private static IMarketDao marketDao = new MarketDao();
	private static IOrderDao orderDao = new OrderDao();
}
