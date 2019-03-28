package com.sendtomoon.chopin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sendtomoon.chopin.ip.GetIP;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

@Component
public class MainService {

	@Autowired
	private GetIP getip;

	@Autowired
	private ReNewDNS renew;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void mainService() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
		logger.info("Starting----------------");
		renew.renew(getip.ip());
		logger.info("End---------------------");
	}

}
