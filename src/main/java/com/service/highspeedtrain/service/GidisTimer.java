package com.service.highspeedtrain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GidisTimer extends TimerTask {

	private static int count = 1;

	public GidisTimer() {
	}

	@Override
	public void run() {
		System.out.println(count++);

		try {
			final String tarih = "04.10.2019";
			final String nereden = "Eskişehir";
			final String nereye = "İstanbul(Pendik)";
			List<String> saatler = new ArrayList<>();
			saatler.add("14:06");
			Gidis gidis = new Gidis();
			System.out.println("Start Gidis Timer");
			gidis.start(nereden, nereye, tarih, saatler);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			final String tarih = "06.10.2019";
			final String nereden = "İstanbul(Pendik)";
			final String nereye = "Eskişehir";

			List<String> saatler = new ArrayList<>();
			saatler.add("17:44");
			saatler.add("18:21");
			saatler.add("19:31");
			Gidis gidis = new Gidis();
			System.out.println("Start Donus Timer");
			gidis.start(nereden, nereye, tarih, saatler);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}