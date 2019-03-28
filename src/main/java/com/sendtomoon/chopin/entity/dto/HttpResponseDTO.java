package com.sendtomoon.chopin.entity.dto;

import org.apache.http.client.CookieStore;

public class HttpResponseDTO {

	private String response;

	private CookieStore cookie;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public CookieStore getCookie() {
		return cookie;
	}

	public void setCookie(CookieStore cookie) {
		this.cookie = cookie;
	}

}
