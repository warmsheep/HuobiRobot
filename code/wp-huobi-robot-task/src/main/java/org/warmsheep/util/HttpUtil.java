package org.warmsheep.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	/**
     * 默认编码
     */
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static HttpParams httpParams;
    private static PoolingClientConnectionManager connectionManager;
    private static HttpClient client = null;
    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 800;
    /**
     * 获取连接的最大等待时间
     */
    public final static int WAIT_TIMEOUT = 60000;
    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 400;
    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 10000;
    /**
     * 读取超时时间
     */
    public final static int READ_TIMEOUT = 10000;

    static {
        httpParams = new BasicHttpParams();
        // 设置连接超时时间
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
        // 设置读取超时时间
        HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);
        connectionManager = new PoolingClientConnectionManager();
        // 设置最大连接数
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        // 设置每个路由最大连接数
        connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
        client = new DefaultHttpClient(connectionManager, httpParams);
    }

    /**
     * 以POST方式发送一个HTTP请求，当响应状态码为200时将响应内容以字符串返回。
     * <p>
     * 此方法采用指定编码。目前只不支持文件传输。
     * @param <T>
     * 
     * @param url 请求地址。
     * @param data 请求数据。
     * @param encoding 指定编码。
     * @return http响应内容。<br/>
     *         当请求失败或url有问题时返回null。
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static <T> T post(String url, Map<String, Object> data, ResponseHandler<T> rh) throws ClientProtocolException,
            IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(data != null){
        	 for (Map.Entry<String, Object> me : data.entrySet()) {
                 if (me.getValue() != null) {
                     nvps.add(new BasicNameValuePair(me.getKey(), me.getValue().toString()));
                 }
             }
        }
        return post(url, new UrlEncodedFormEntity(nvps, DEFAULT_ENCODING), rh);
    }

    /**
     * 以POST方式发起一个请求，响应结果在用户的 ResponseHandler 处理
     * 
     * @param url
     * @param httpEntity
     * @param rh
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static <T> T post(String url, HttpEntity httpEntity, ResponseHandler<T> rh) throws ClientProtocolException,
            IOException {
        HttpPost method = new HttpPost(url);
        method.setEntity(httpEntity);
        // 发送请求
        try {
            return client.execute(method, rh);
        } finally {
            method.releaseConnection();
        }
    }


    public static String post(String url, HttpEntity httpEntity) throws ClientProtocolException, IOException {
        return post(url, httpEntity, DEFAULT_ENCODING);
    }

    public static String post(String url, HttpEntity httpEntity, String encoding) throws ClientProtocolException,
            IOException {
        HttpPost postMethod = new HttpPost(url);
        postMethod.setEntity(httpEntity);
        // 发送请求
        try {
            HttpResponse httpResponse = client.execute(postMethod);
            return EntityUtils.toString(httpResponse.getEntity(), encoding);// 取出应答字符串
        } finally {
            postMethod.releaseConnection();
        }
    }
}
