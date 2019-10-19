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
			final String tarih = "20.10.2019";
			final String nereden = "Eskişehir";
			final String nereye = "İstanbul(Pendik)";
			List<String> saatler = new ArrayList<>();
			saatler.add("07:31");
			saatler.add("08:41");
			saatler.add("09:33");
			saatler.add("11:11");
			Gidis gidis = new Gidis();
			System.out.println("Start Gidis Timer");
			gidis.start(nereden, nereye, tarih, saatler);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			final String tarih = "20.10.2019";
			final String nereden = "İstanbul(Pendik)";
			final String nereye = "Eskişehir";

			List<String> saatler = new ArrayList<>();
			saatler.add("15:57");
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