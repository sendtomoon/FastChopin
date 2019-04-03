package com.sendtomoon.chopin.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sendtomoon.chopin.ip.GetIP;

@Component
public class MainService {

	@Autowired
	private GetIP getip;

	@Autowired
	private ReNewDNS renew;

	private final Logger logger = LogManager.getLogger(this.getClass());

	public void mainService() {
		logger.info("Starting----------------");
		renew.renew(getip.ip());
		logger.info("End---------------------");
	}

}
