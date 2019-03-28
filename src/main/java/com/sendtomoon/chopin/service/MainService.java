package com.sendtomoon.chopin.service;

import com.sendtomoon.chopin.ip.GetIP;

public class MainService {

	public void mainService() {
		GetIP ip = new GetIP();
		ReNewDNS renew = new ReNewDNS();
		renew.renew(ip.ip());
	}

}
