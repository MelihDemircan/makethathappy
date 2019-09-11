package com.service.highspeedtrain.service;

import java.util.TimerTask;

public class GidisTimer extends TimerTask {

	private static int count = 1;

	public GidisTimer() {
	}

	@Override
	public void run() {
		System.out.println(count++);
		Gidis gidis = new Gidis();
		try {
			System.out.println("Start Timer");
			gidis.star();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}