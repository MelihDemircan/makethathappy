package com.service.highspeedtrain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GidisTimer extends TimerTask {

	private static int count = 1;
	public static int countSendEkonomi = 0;
	public static int countSendBusiness = 0;

	public GidisTimer() {}

	@Override
	public void run() {
		System.out.println(count++ + " / " + countSendEkonomi + " / " + countSendBusiness);

		//
		try {
			final String tarih = "23.02.2020";
			final String nereye = "İstanbul(Bostancı)";
			final String nereden = "Eskişehir";
			List<String> saatler = new ArrayList<>();
			saatler.add("16:58");
			saatler.add("18:36");
			saatler.add("19:55");
			saatler.add("20:38");
			Gidis gidis = new Gidis();
			System.out.println("Start Gidis Timer");
			gidis.start(nereden, nereye, tarih, saatler);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		// //
		// try {
		// final String tarih = "28.01.2020";
		// final String nereden = "Eskişehir";
		// final String nereye = "Ankara Gar";
		//
		// List<String> saatler = new ArrayList<>();
		//// saatler.add("18:38");
		//// saatler.add("20:07");
		// saatler.add("20:25");
		// saatler.add("21:09");
		// Gidis gidis = new Gidis();
		// System.out.println("Start Donus Timer");
		// gidis.start(nereden, nereye, tarih, saatler);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		////// Konya Konya YHT

		// try {
		// final String tarih = "24.01.2020";
		// final String nereden = "İstanbul(Pendik)";
		// final String nereye = "Konya YHT";
		// List<String> saatler = new ArrayList<>();
		// // saatler.add("12:13");
		// // saatler.add("13:15");
		// // saatler.add("13:58");
		// // saatler.add("15:57");
		//// saatler.add("17:28");
		//// saatler.add("18:22");
		// saatler.add("19:32");
		// Gidis gidis = new Gidis();
		// System.out.println("Start Gidis Timer");
		// gidis.start(nereden, nereye, tarih, saatler);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		// try {
		// final String tarih = "26.01.2020";
		// final String nereden = "Konya YHT";
		// final String nereye = "İstanbul(Pendik)";
		//
		// List<String> saatler = new ArrayList<>();
		//// saatler.add("16:58");
		//// saatler.add("18:36");
		//// saatler.add("19:55");
		// saatler.add("18:10");
		// Gidis gidis = new Gidis();
		// System.out.println("Start Donus Timer");
		// gidis.start(nereden, nereye, tarih, saatler);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		// try {
		// final String tarih = "08.12.2019";
		// final String nereden = "İstanbul(Pendik)";
		// final String nereye = "Eskişehir";
		// List<String> saatler = new ArrayList<>();
		// // saatler.add("12:13");
		// // saatler.add("13:15");
		// // saatler.add("13:58");
		// // saatler.add("15:57");
		// saatler.add("17:44");
		// saatler.add("18:21");
		// saatler.add("19:31");
		// Gidis gidis = new Gidis();
		// System.out.println("Start Gidis Timer");
		// gidis.start(nereden, nereye, tarih, saatler);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		// try {
		// final String tarih = "25.10.2019";
		// final String nereden = "İstanbul(Pendik)";
		// final String nereye = "Eskişehir";
		//
		// List<String> saatler = new ArrayList<>();
		// // saatler.add("15:57");
		// // saatler.add("17:44");
		// // saatler.add("18:21");
		// saatler.add("19:31");
		// Gidis gidis = new Gidis();
		// System.out.println("Start Donus Timer");
		// gidis.start(nereden, nereye, tarih, saatler);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		//
		// try {
		// final String tarih = "26.10.2019";
		// final String nereden = "İstanbul(Pendik)";
		// final String nereye = "Eskişehir";
		//
		// List<String> saatler = new ArrayList<>();
		// // saatler.add("12:1sss3");
		// saatler.add("13:15");
		// saatler.add("13:58");
		// saatler.add("15:57");
		// saatler.add("17:44");
		// saatler.add("18:21");
		// saatler.add("19:31");
		// Gidis gidis = new Gidis();
		// System.out.println("Start Donus Timer");
		// gidis.start(nereden, nereye, tarih, saatler);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

	}
}
