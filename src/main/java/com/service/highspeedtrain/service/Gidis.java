package com.service.highspeedtrain.service;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.highspeedtrain.data.Parametre;
import com.service.highspeedtrain.data.Tren;

@Component
public class Gidis {

	@Autowired
	private MailService mailService = new MailService();

	private final String tarih = "15.09.2019";
	private final String nereye  = "%C4%B0stanbul(Pendik)";
	private final String nereden = "Eski%C5%9Fehir";
	private final String site = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";
	private final String resultSite = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/int_sat_001.jsf";

	public Gidis() {
	}

	public void star() throws Exception {

		ConnetionPra http = new ConnetionPra();

		CookieHandler.setDefault(new CookieManager());

		Parametre parametre = new Parametre(nereden, nereye, tarih);

		String resultParam = http.GetPageContent(site, parametre);

		parametre.add("&javax.faces.ViewState="
				+ URLEncoder.encode(resultParam.split("update")[3].split("\\[")[2].split("]")[0], "UTF-8"));

		System.out.println(parametre.toString());
		String resultTren = http.GetPageContent(site, parametre);

		String resultTrenList = http.Seferler(resultSite);

		List<Tren> trenList = http.getFormParams(resultTrenList);

		//Gidis
//		Tren one = trenList.stream().filter(tren -> tren.getSaat().equals("17:44")).findAny().orElse(null);
//		Tren two = trenList.stream().filter(tren -> tren.getSaat().equals("18:21")).findAny().orElse(null);
//		Tren tre = trenList.stream().filter(tren -> tren.getSaat().equals("19:31")).findAny().orElse(null);

		
		//Donus
		Tren two = trenList.stream().filter(tren -> tren.getSaat().equals("20:38")).findAny().orElse(null);
		Tren tre = trenList.stream().filter(tren -> tren.getSaat().equals("20:11")).findAny().orElse(null);

		System.out.println(nereden + "/" + nereye + "/" + tarih);

		System.out.println(trenList.stream().map(Object::toString).collect(Collectors.joining(",")));

//		if (one != null && one.isPulman()) {
//			System.out.println("Sefer Bulundu 17:44");
//			mailService.send("Eskisehir Istanbul  (17:44) (" + tarih + ") " + "(" + one.getPulman() + ")");
//		} else
		if (two != null && two.isPulman()) {
			System.out.println("Sefer Bulundu 20:38");
			mailService.send("Eskisehir Istanbul  (20:38) (" + tarih + ") " + "(" + two.getPulman() + ")");
		} else if (tre != null && tre.isPulman()) {
			System.out.println("Sefer Bulundu 20:11");
			mailService.send("Eskisehir Istanbul  (20:11) (" + tarih + ") " + "(" + tre.getPulman() + ")");
		} else {
			System.out.println("Sefer Yok (Economi)");
		}

//		if (one != null && one.isBusiness()) {
//			System.out.println("Sefer Bulundu 17:44");
//			mailService.send("Eskisehir Istanbul  (17:44) (" + tarih + ") " + "(" + one.getBusiness() + ")");
//		} else
//		if (two != null && two.isBusiness()) {
//			System.out.println("Sefer Bulundu 18:21");
//			mailService.send("Eskisehir Istanbul  (18:21) (" + tarih + ") " + "(" + two.getBusiness() + ")");
//		} else if (tre != null && tre.isBusiness()) {
//			System.out.println("Sefer Bulundu 19:31");
//			mailService.send("Eskisehir Istanbul  (19:31) (" + tarih + ") " + "(" + tre.getBusiness() + ")");
//		} else {
//			System.out.println("Sefer Yok (Business)");
//		}
		System.out.println("•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
	}
}
