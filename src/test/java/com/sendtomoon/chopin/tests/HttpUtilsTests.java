package com.sendtomoon.chopin.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.Test;

import com.sendtomoon.chopin.entity.dto.HttpResponseDTO;
import com.sendtomoon.chopin.tools.HttpUtils;

public class HttpUtilsTests {

	@Test
	public void getTest() throws Exception {
		HttpUtils.get("http://httpbin.org/get");
	}

	@Test
	public void postTest() {
		CookieStore cookie = new BasicCookieStore();

		HttpResponseDTO result = null;
		try {
			result = HttpUtils.post("http://192.168.0.1/login.cgi",
					"group_id=&action_mode=&action_script=&action_wait=5&current_page=Main_Login.asp&next_page=index.asp&login_authorization=bGJ0NDI1OjU5MTMyMTU=",
					null, getH1(), cookie);
			System.err.println(result.getResponse());
			result = HttpUtils.post("http://192.168.0.1/ajax_status.xml?hash=0.24395379430691233", "", null, getH1(),
					cookie);
			System.err.println(result.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Map<String, String> getH1() {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		header.put("Cache-Control", "max-age=0");
		header.put("Content-Type", "application/x-www-form-urlencoded");
//		header.put("Cookie",
//				"traffic_warning_NaN=2018.7:1; apps_last=; hwaddr=0C:9D:92:49:B7:70; awrtm_wlrefresh=0; dhcp_sortcol=1; dhcp_sortmet=1; clickedItem_tab=0");
//		header.put("DNT", "1");
		header.put("Host", "192.168.0.1");
		header.put("Origin", "http://192.168.0.1");
		header.put("Proxy-Connection", "keep-alive");
		header.put("Referer", "http://192.168.0.1/Main_Login.asp");
//		header.put("Upgrade-Insecure-Requests", "1");
//		header.put("User-Agent",
//				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
		return header;
	}

}
