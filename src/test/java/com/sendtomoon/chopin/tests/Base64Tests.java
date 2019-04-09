package com.sendtomoon.chopin.tests;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Tests {

	@Test
	public void test1() {
		String str1 = "lbt425:5913215";
		String str2 = Base64.encodeBase64String(str1.getBytes());
		System.err.println(str2);
	}

}
