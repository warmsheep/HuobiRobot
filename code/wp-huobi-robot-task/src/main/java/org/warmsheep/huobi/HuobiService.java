package org.warmsheep.huobi;

import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.warmsheep.enums.CoinType;

public class HuobiService extends BaseService{
	
	private String huobiAccessKey;
	private String huobiSecretKey;
	private String huobiApiUrl;
	
	public HuobiService(String huobiAccessKey,String huobiSecretKey,String huobiApiUrl){
		this.huobiAccessKey = huobiAccessKey;
		this.huobiSecretKey = huobiSecretKey;
		this.huobiApiUrl = huobiApiUrl;
	}

	/**
	 * 下单接口
	 * 
	 * @param coinType
	 * @param price
	 * @param amount
	 * @param tradePassword
	 * @param tradeid
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String buy(int coinType, String price, String amount, String tradePassword, Integer tradeid, String method)
			throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("price", price);
		paraMap.put("amount", amount);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		if (StringUtils.isNotEmpty(tradePassword)) {
			paraMap.put("trade_password", tradePassword);
		}
		if (null != tradeid) {
			paraMap.put("trade_id", tradeid);
		}
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 提交市价单接口
	 * 
	 * @param coinType
	 * @param amount
	 * @param tradePassword
	 * @param tradeid
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String buyMarket(int coinType, String amount, String tradePassword, Integer tradeid, String method)
			throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("amount", amount);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		if (StringUtils.isNotEmpty(tradePassword)) {
			paraMap.put("trade_password", tradePassword);
		}
		if (null != tradeid) {
			paraMap.put("trade_id", tradeid);
		}
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 撤销订单
	 * 
	 * @param coinType
	 * @param id
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String cancelOrder(int coinType, long id, String method) throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("id", id);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 获取账号详情
	 * 
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String getAccountInfo(String method) throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 查询个人最新10条成交订单
	 * 
	 * @param coinType
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String getNewDealOrders(int coinType, String method) throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 根据trade_id查询oder_id
	 * 
	 * @param coinType
	 * @param tradeid
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String getOrderIdByTradeId(int coinType, long tradeid, String method) throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("trade_id", tradeid);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 获取所有正在进行的委托
	 * 
	 * @param coinType
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String getOrders(int coinType, String method) throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 获取订单详情
	 * 
	 * @param coinType
	 * @param id
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String getOrderInfo(int coinType, long id, String method) throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("id", id);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 限价卖出
	 * 
	 * @param coinType
	 * @param price
	 * @param amount
	 * @param tradePassword
	 * @param tradeid
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String sell(int coinType, String price, String amount, String tradePassword, Integer tradeid, String method)
			throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("price", price);
		paraMap.put("amount", amount);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		if (StringUtils.isNotEmpty(tradePassword)) {
			paraMap.put("trade_password", tradePassword);
		}
		if (null != tradeid) {
			paraMap.put("trade_id", tradeid);
		}
		return post(paraMap, huobiApiUrl);
	}

	/**
	 * 市价卖出
	 * 
	 * @param coinType
	 * @param amount
	 * @param tradePassword
	 * @param tradeid
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String sellMarket(int coinType, String amount, String tradePassword, Integer tradeid, String method)
			throws Exception {
		TreeMap<String, Object> paraMap = new TreeMap<String, Object>();
		paraMap.put("method", method);
		paraMap.put("created", getTimestamp());
		paraMap.put("access_key", huobiAccessKey);
		paraMap.put("secret_key", huobiSecretKey);
		paraMap.put("coin_type", coinType);
		paraMap.put("amount", amount);
		String md5 = sign(paraMap);
		paraMap.remove("secret_key");
		paraMap.put("sign", md5);
		if (StringUtils.isNotEmpty(tradePassword)) {
			paraMap.put("trade_password", tradePassword);
		}
		if (null != tradeid) {
			paraMap.put("trade_id", tradeid);
		}
		return post(paraMap, huobiApiUrl);
	}
	
	/**
	 * 获取一分钟线
	 * @return
	 * @throws Exception
	 */
	public String getMinuteData(int coinType)throws Exception {
		if(coinType == CoinType.BTC.getKey()){
			return post(null, "http://api.huobi.com/staticmarket/btc_kline_001_json.js");
		} else if(coinType == CoinType.LTC.getKey()){
			return post(null, "http://api.huobi.com/staticmarket/ltc_kline_001_json.js");
		}
		return null;
	}
	/**
	 * 获取实时价格
	 * @return
	 * @throws Exception
	 */
	public String getRealTimeData(int coinType)throws Exception {
		if(coinType == CoinType.BTC.getKey()){
			return post(null, "http://api.huobi.com/staticmarket/ticker_btc_json.js");
		}else if(coinType == CoinType.LTC.getKey()){
			return post(null, "http://api.huobi.com/staticmarket/ticker_ltc_json.js");
		}
		return null;
	}
}
