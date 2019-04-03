package com.sendtomoon.chopin.ip;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sendtomoon.chopin.entity.dto.HttpResponseDTO;
import com.sendtomoon.chopin.entity.dto.RouterLoginDTO;
import com.sendtomoon.chopin.tools.HttpUtils;

@Component
public class GetIP {

	private String ipAddr;

	@Value("${router.login.url}")
	private String ROUTER_LOGIN_URL;

	@Value("${router.status.url}")
	private String ROUTER_STATUS_URL;

	@Value("${router.password}")
	private String password;

	private String IP_FIELD = "wan0_ipaddr";

	private final Logger logger = LogManager.getLogger(this.getClass());

	public String ip() {
		this.mainService();
		return ipAddr;
	}

	private void mainService() {
		CookieStore cookie = new BasicCookieStore();
		HttpResponseDTO resultLogin = null;
		HttpResponseDTO resultStatus = null;
		RouterLoginDTO loginDTO = new RouterLoginDTO();
		loginDTO.setAction_wait("5");
		loginDTO.setCurrent_page("Main_Login.asp");
		loginDTO.setNext_page("index.asp");
		loginDTO.setLogin_authorization(Base64.encodeBase64String(password.getBytes()));
		try {
			resultLogin = HttpUtils.post(ROUTER_LOGIN_URL, JSONObject.toJSONString(loginDTO), null, this.getHeader(),
					cookie);
			cookie = resultLogin.getCookie();
			resultStatus = HttpUtils.post(ROUTER_STATUS_URL, null, null, this.getHeader(), cookie);
		} catch (Exception e) {
			logger.error("mainService-error:" + e, e);
			return;
		}
		Document doc = Jsoup.parse(resultStatus.getResponse());
		Elements eles = doc.getElementsContainingOwnText(IP_FIELD);
		ipAddr = eles.text().split("=")[1];
		logger.info("IP Address is:" + ipAddr);
	}

	private Map<String, String> getHeader() {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		header.put("Cache-Control", "max-age=0");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Host", "192.168.0.1");
		header.put("Origin", "http://192.168.0.1");
		header.put("Proxy-Connection", "keep-alive");
		header.put("Referer", "http://192.168.0.1/Main_Login.asp");
		return header;
	}

}
