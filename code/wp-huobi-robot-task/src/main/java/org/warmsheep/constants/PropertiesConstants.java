package org.warmsheep.constants;

import org.springframework.stereotype.Service;
import org.warmsheep.util.properties.ConfigReader;

@Service("propertiesConstants")
public class PropertiesConstants {

	public static final String HUOBI_API_URL_PRO = "huobi.api.url"; 
	
	public static String HUOBI_API_URL = "http://api.huobi.com/apiv3";
	
	
}
