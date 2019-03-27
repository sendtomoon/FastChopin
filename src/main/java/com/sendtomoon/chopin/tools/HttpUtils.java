package com.sendtomoon.chopin.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;

public class HttpUtils {

	public static void get(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			System.out.println("Executing get request " + httpGet.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream inStream = entity.getContent();
					try {
						inStream.read();
					} catch (IOException ex) {
						throw ex;
					} finally {
						inStream.close();
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	public static Map<String, Object> post(String url, String request, String proxyUrl, Map<String, String> header,
			CookieStore cookie) throws Exception {
		Map<String, Object> respMap = new HashMap<String, Object>();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookie).build();
		try {
			HttpPost httpPost = new HttpPost(url);
			HttpEntity entity = new StringEntity(request);
			httpPost.setEntity(entity);
			// 设置头信息
			if (MapUtils.isNotEmpty(header)) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}

			if (StringUtils.isNotBlank(proxyUrl)) {
				HttpUtils.setProxy(httpPost, proxyUrl);
			}
			System.out.println("Executing post request " + httpPost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity respEntity = response.getEntity();
				if (respEntity != null) {
					InputStream inStream = respEntity.getContent();
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
						StringBuilder strber = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							strber.append(line);
						}
						respMap.put("response", strber.toString());
						respMap.put("cookie", cookie);
						System.err.println(JSON.toJSONString(cookie));
						return respMap;
					} catch (IOException ex) {
						throw ex;
					} finally {
						inStream.close();
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return null;
	}

	private static void setProxy(HttpPost httpPost, String proxyUrl) {
		String[] arr = proxyUrl.split(":");
		HttpHost httpHost = new HttpHost(arr[0], Integer.valueOf(arr[1]));
		RequestConfig config = RequestConfig.custom().setProxy(httpHost).build();
		httpPost.setConfig(config);
	}

}
