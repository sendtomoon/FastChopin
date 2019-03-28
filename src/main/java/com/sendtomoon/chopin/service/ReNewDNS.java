package com.sendtomoon.chopin.service;

import java.util.HashMap;
import java.util.Map;

import com.sendtomoon.chopin.entity.dto.HttpResponseDTO;
import com.sendtomoon.chopin.tools.HttpUtils;

public class ReNewDNS {

	public void renew(final String ip) {
		String requestBody = "[{\"data\":\"" + ip + "\"}]";
		String renew_addr = "https://api.godaddy.com/v1/domains/sendtomoon.com/records/A/mozart";
		try {
			HttpResponseDTO dto = HttpUtils.put(renew_addr, requestBody, this.getHeader());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Map<String, String> getHeader() {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
		header.put("Content-Type", "application/json");
		return header;
	}
}
