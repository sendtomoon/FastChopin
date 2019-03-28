package com.sendtomoon.chopin.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sendtomoon.chopin.ip.GetIP;

public class MainService {

	private static ApplicationContext ac = null;

	static {
		ac = new ClassPathXmlApplicationContext("root-config.xml");
	}

	public void mainService() {
		GetIP ip = new GetIP();
		ReNewDNS renew = new ReNewDNS();
		renew.renew(ip.ip());
	}

}
