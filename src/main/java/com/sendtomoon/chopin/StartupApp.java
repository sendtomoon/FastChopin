package com.sendtomoon.chopin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sendtomoon.chopin.service.MainService;

public class StartupApp {

	static {
		Properties prop = new Properties();
		InputStream in = StartupApp.class.getClassLoader().getResourceAsStream("system.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("chopin.log.dir", prop.getProperty("chopin.log.dir"));
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("root-config.xml");
		MainService main = ac.getBean(MainService.class);
		main.mainService();
	}
}
