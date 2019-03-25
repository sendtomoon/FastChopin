package com.sendtomoon.chopin.tests;

import org.junit.Test;

import com.sendtomoon.chopin.tools.HttpUtils;

public class HttpUtilsTests {

	@Test
	public void getTest() throws Exception {
		HttpUtils.get("http://httpbin.org/get");
	}

	@Test
	public void postTest() throws Exception {
		String result = HttpUtils.post("http://192.168.0.1/login.cgi",
				"group_id=&action_mode=&action_script=&action_wait=5&current_page=Main_Login.asp&next_page=index.asp&login_authorization=bGJ0NDI1OjU5MTMyMTU=",
				null);
		System.err.println(result);
	}
}
