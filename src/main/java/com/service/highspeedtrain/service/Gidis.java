package com.service.highspeedtrain.service;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.highspeedtrain.data.Parametre;
import com.service.highspeedtrain.data.Tren;

@Component
public class Gidis {

	@Autowired
	private MailService mailService;
	
	private final String tarih = "13.09.2019";
	private final String nereye = "%C4%B0stanbul(Pendik)";
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

		Tren ilk = trenList.stream().filter(tren -> tren.getSaat().equals("17:44") && tren.isPulman()).findAny()
				.orElse(null);
		Tren son = trenList.stream().filter(tren -> tren.getSaat().equals("18:21") && tren.isPulman()).findAny()
				.orElse(null);


			
		if (ilk != null) {
			System.out.println("Sefer Bulundu 17:44");
			mailService.send("Eskisehir Istanbul  (17:44) (" + tarih + ") " + "(" + ilk.getPulman() + ")");
		} else if (son != null) {
			System.out.println("Sefer Bulundu 18:21");
			mailService.send("Eskisehir Istanbul  (18:21) (" + tarih + ") " + "(" + son.getPulman() + ")");
		} else {
			System.out.println("Sefer Yok");
		}
		System.out.println("•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
	}
}
