package com.service.highspeedtrain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GidisTimer extends TimerTask {

	private static int count = 1;

	public GidisTimer() {}

	@Override
	public void run() {
		System.out.println(count++);



		try {
			final String tarih = "08.11.2019";
			final String nereden = "İstanbul(Pendik)";
			final String nereye = "Eskişehir";

			List<String> saatler = new ArrayList<>();
			// saatler.add("13:58");
			// saatler.add("15:57");
			saatler.add("17:44");
			saatler.add("18:21");
			saatler.add("19:31");
			Gidis gidis = new Gidis();
			System.out.println("Start Donus Timer");
			gidis.start(nereden, nereye, tarih, saatler);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			final String tarih = "10.11.2019";
			final String nereden = "Eskişehir";
			final String nereye = "İstanbul(Pendik)";
			List<String> saatler = new ArrayList<>();
//			saatler.add("16:18");
			saatler.add("18:31");
			saatler.add("20:11");
			saatler.add("20:38");
			Gidis gidis = new Gidis();
			System.out.println("Start Gidis Timer");
			gidis.start(nereden, nereye, tarih, saatler);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

//		try {
//			final String tarih = "25.10.2019";
//			final String nereden = "İstanbul(Pendik)";
//			final String nereye = "Eskişehir";
//
//			List<String> saatler = new ArrayList<>();
//			// saatler.add("15:57");
//			// saatler.add("17:44");
//			// saatler.add("18:21");
//			saatler.add("19:31");
//			Gidis gidis = new Gidis();
//			System.out.println("Start Donus Timer");
//			gidis.start(nereden, nereye, tarih, saatler);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		try {
//			final String tarih = "26.10.2019";
//			final String nereden = "İstanbul(Pendik)";
//			final String nereye = "Eskişehir";
//
//			List<String> saatler = new ArrayList<>();
//			// saatler.add("12:1sss3");
//			saatler.add("13:15");
//			saatler.add("13:58");
//			saatler.add("15:57");
//			saatler.add("17:44");
//			saatler.add("18:21");
//			saatler.add("19:31");
//			Gidis gidis = new Gidis();
//			System.out.println("Start Donus Timer");
//			gidis.start(nereden, nereye, tarih, saatler);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

	}
}
