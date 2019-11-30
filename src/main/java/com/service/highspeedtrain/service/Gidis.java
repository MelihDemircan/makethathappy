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

	
	public static final String site = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";
	public static final String resultSite = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/int_sat_001.jsf";

	

	public void start(String nereden, String nereye, String tarih, List<String> saatler) throws Exception {

		ConnetionPra http = new ConnetionPra();

		CookieHandler.setDefault(new CookieManager());

		Parametre parametre = new Parametre(URLEncoder.encode(nereden, "UTF-8"), URLEncoder.encode(nereye, "UTF-8"), URLEncoder.encode(tarih, "UTF-8"));

		String resultParam = http.GetPageContent(site, parametre);

		parametre.add("&javax.faces.ViewState="
				+ URLEncoder.encode(resultParam.split("update")[3].split("\\[")[2].split("]")[0], "UTF-8"));

		System.out.println(parametre.toString());
		String resultTren = http.GetPageContent(site, parametre);

		String resultTrenList = http.Seferler(resultSite);

		List<Tren> trenList = http.getFormParams(resultTrenList);

		List<Tren> trenSaatler = trenList.stream().filter(tren -> saatler.contains(tren.getSaat()))
				.collect(Collectors.toList());

		System.out.println("Trenler : " + trenList.stream().map(Object::toString).collect(Collectors.joining(",")));
		System.out.println("Saatler : " + saatler.stream().map(Object::toString).collect(Collectors.joining(",")));
		System.out.println(
				"Trenler Saatler : " + trenSaatler.stream().map(Object::toString).collect(Collectors.joining(",")));

		for (Tren tren : trenSaatler) {
			if (tren != null && tren.isPulman()) {
				GidisTimer.countSend++;
				System.out.println("Sefer Bulundu " + nereden + "/" + nereye + "/" + tarih + "/" + tren.getSaat());

				System.out.println("Sefer Bulundu " + tren.getSaat());
				mailService.send(nereden + "/" + nereye + " (" + tren.getSaat() + ") (" + tarih + ") " + "("
						+ tren.getPulman() + ")");
			}
			
			if (tren != null && tren.isBusiness()) {
				GidisTimer.countSend++;
				System.out.println("Sefer Bulundu " + nereden + "/" + nereye + "/" + tarih + "/" + tren.getSaat());

				System.out.println("Sefer Bulundu " + tren.getSaat());
				mailService.send("Business " + nereden + "/" + nereye + " (" + tren.getSaat() + ") (" + tarih + ") " + "("
						+ tren.getPulman() + ")");
			}
		}
		System.out.println("•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
	}
}
