package com.sendtomoon.chopin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sendtomoon.chopin.service.MainService;

public class StartupApp {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("root-config.xml");
		MainService main = ac.getBean(MainService.class);
		main.mainService();
 	}
}
