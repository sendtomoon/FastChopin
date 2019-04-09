package com.sendtomoon.chopin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
		System.setProperties(prop);
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("root-config.xml");
		MainService main = ac.getBean(MainService.class);
		main.mainService();
		ac.close();
	}
}
