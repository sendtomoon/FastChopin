package com.sendtomoon.chopin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sendtomoon.chopin.ip.GetIP;

@Component
public class MainService {

	@Autowired
	private GetIP getip;

	@Autowired
	private ReNewDNS renew;

	public void mainService() {
		renew.renew(getip.ip());
	}

}
