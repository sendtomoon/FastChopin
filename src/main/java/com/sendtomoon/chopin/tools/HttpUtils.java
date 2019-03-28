package com.sendtomoon.chopin.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSON;
import com.sendtomoon.chopin.entity.dto.HttpResponseDTO;

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

	public static HttpResponseDTO post(String url, String request, String proxyUrl, Map<String, String> header,
			CookieStore cookie) throws Exception {
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookie).build();
		try {
			HttpPost httpPost = new HttpPost(url);
			if (StringUtils.isNotBlank(request)) {
				HttpEntity entity = new StringEntity(request);
				httpPost.setEntity(entity);
			}
			// 设置头信息
			if (MapUtils.isNotEmpty(header)) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}

			if (StringUtils.isNotBlank(proxyUrl)) {
				HttpUtils.setProxy(httpPost, proxyUrl);
			}
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
						HttpResponseDTO dto = new HttpResponseDTO();
						dto.setCookie(cookie);
						dto.setResponse(strber.toString());
						return dto;
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

	public static Map<String, String> httpsClientPut(String url, String requestBody, Map<String, String> header)
			throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException,
			URISyntaxException {
		Map<String, String> respMap = new HashMap<String, String>();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setSSLContext(createIgnoreVerifySSL());
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPut put = new HttpPut(url);
		for (Entry<String, String> entry : header.entrySet()) {
			put.setHeader(entry.getKey(), entry.getValue().toString());
		}
		StringEntity se = new StringEntity(requestBody, "UTF-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		put.setEntity(se);
		// 发送请求
		HttpResponse httpResponse = closeableHttpClient.execute(put);
		// 获取响应输入流
		InputStream inStream = httpResponse.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
		StringBuilder strber = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			strber.append(line);
		}
		inStream.close();
		respMap.put("status", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
		respMap.put("responseMsg", strber.toString());
		return respMap;
	}

	private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	private static void setProxy(HttpPost httpPost, String proxyUrl) {
		String[] arr = proxyUrl.split(":");
		HttpHost httpHost = new HttpHost(arr[0], Integer.valueOf(arr[1]));
		RequestConfig config = RequestConfig.custom().setProxy(httpHost).build();
		httpPost.setConfig(config);
	}

}
