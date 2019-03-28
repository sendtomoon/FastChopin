package com.sendtomoon.chopin;

import com.sendtomoon.chopin.service.MainService;

public class StartupApp {
	public static void main(String[] args) {
		System.err.println("Starting----------------");
		MainService main = new MainService();
		main.mainService();
	}
}
