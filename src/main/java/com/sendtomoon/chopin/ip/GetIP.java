package com.sendtomoon.chopin.ip;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sendtomoon.chopin.entity.dto.HttpResponseDTO;
import com.sendtomoon.chopin.tools.HttpUtils;

public class GetIP {

	private String ipAddr;

	private final String ROUTER_LOGIN_URL = "http://192.168.0.1/login.cgi";

	private final String ROUTER_STATUS_URL = "http://192.168.0.1/ajax_status.xml?hash=0.24395379430691233";

	private final String ROUTER_REQ_PARAM = "group_id=&action_mode=&action_script=&action_wait=5&current_page=Main_Login.asp&next_page=index.asp&login_authorization=bGJ0NDI1OjU5MTMyMTU=";

	public String ip() {
		this.mainService();
		return ipAddr;
	}

	private void mainService() {
		CookieStore cookie = new BasicCookieStore();
		HttpResponseDTO resultLogin = null;
		HttpResponseDTO resultStatus = null;
		try {
			resultLogin = HttpUtils.post(ROUTER_LOGIN_URL, ROUTER_REQ_PARAM, null, this.getHeader(), cookie);
			cookie = resultLogin.getCookie();
			resultStatus = HttpUtils.post(ROUTER_STATUS_URL, null, null, this.getHeader(), cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Document doc = 
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
