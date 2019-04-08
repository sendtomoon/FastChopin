package com.sendtomoon.chopin.entity.dto;

import java.io.Serializable;

import org.apache.http.client.CookieStore;

public class HttpResponseDTO implements Serializable, java.lang.Cloneable {
	private static final long serialVersionUID = -2122525587263613649L;

	private String response;

	private CookieStore cookie;

	private String httpStatus;

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

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

}
