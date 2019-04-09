package com.sendtomoon.chopin.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void test1() throws Exception {
		String html = "[stock=000004]<dddd>[CARD=000005]<p data-fund-code=\"000001\">\r\n"
				+ "<text data-stock-code=\"000002\" data-fund-code=\"000003\" />\r\n" + "</p>";
		String regex = "(?:\\[(fund|CARD|stock)=([^\\]\\/]+)[^\\[]*\\])|(?:(data-(fund|stock)-code)=\"(\\w+)\")";
		RegexTest.match(html, regex);
	}

	public static void match(String source, String attr) {
		Matcher m = Pattern.compile(attr).matcher(source);
		while (m.find()) {
			System.err.println(m.toString());
			System.err.println(m.groupCount());
			System.err.println(m.group(1) + "|" + m.group(2) + "|" + m.group(3) + "|" + m.group(4) + "|" + m.group(5));
		}
	}
}
