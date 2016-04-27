package org.warmsheep.constants;

import org.springframework.stereotype.Service;
import org.warmsheep.util.properties.ConfigReader;

@Service("propertiesConstants")
public class PropertiesConstants {

	public static final String HUOBI_API_URL_PRO = "huobi.api.url"; 
	
	public static String HUOBI_API_URL = ConfigReader.getContextProperty(PropertiesConstants.HUOBI_API_URL_PRO);
	
	//火币现货配置信息
	public static String HUOBI_ACCESS_KEY = "";
	public static String HUOBI_SECRET_KEY = "";
}
