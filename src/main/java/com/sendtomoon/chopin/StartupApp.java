package com.sendtomoon.chopin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sendtomoon.chopin.service.MainService;

public class StartupApp {

	private static ApplicationContext ac = null;

	static {
		ac = new ClassPathXmlApplicationContext("root-config.xml");
	}

	final static Logger logger = LoggerFactory.getLogger(StartupApp.class);

	public static void main(String[] args) {
		logger.info("Starting----------------");
		MainService main = ac.getBean(MainService.class);
		main.mainService();
		logger.info("End---------------------");
	}
}
