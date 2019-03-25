package com.sendtomoon.chopin.tests;

import org.junit.Test;

import com.sendtomoon.chopin.tools.HttpUtils;

public class HttpUtilsTests {

	@Test
	public void getTest() throws Exception {
		HttpUtils.get("http://httpbin.org/get");
	}
}
