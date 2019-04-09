package com.sendtomoon.chopin.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sendtomoon.chopin.data.mongodb.MongoDAO;
import com.sendtomoon.chopin.entity.dto.HttpResponseDTO;
import com.sendtomoon.chopin.entity.dto.RequestRenewDTO;
import com.sendtomoon.chopin.tools.HttpUtils;

@Component
public class ReNewDNS {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Value("${renew.addr}")
	String renew_addr;

	@Autowired
	private MongoDAO dao;

	public void renew(final String ip) {
		RequestRenewDTO[] param = { new RequestRenewDTO(ip) };
		String requestBody = JSONObject.toJSONString(param);
		try {
			HttpResponseDTO dto = HttpUtils.put(renew_addr, requestBody, this.getHeader());
			logger.info("Renew info is:" + JSON.toJSONString(dto));
			dao.add(dto);
		} catch (Exception e) {
			logger.error("renew-error" + e, e);
		}
	}

	private Map<String, String> getHeader() {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
		header.put("Content-Type", "application/json");
		return header;
	}
}
