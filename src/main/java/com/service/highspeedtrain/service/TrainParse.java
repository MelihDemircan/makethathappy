package com.service.highspeedtrain.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.service.highspeedtrain.data.Tren;

public class TrainParse {

	public List<Tren> getFormParams(String html) {

		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementById("mainTabView:gidisSeferTablosu_data");
		Elements trensChild = loginform.children();
		List<Tren> trenList = new ArrayList<>();
		for (Element trenElement : trensChild) {

			Tren tren = new Tren();

			String saat = trenElement.getElementsByClass("seferSorguTableBuyuk").get(0).text();
			tren.setSaat(saat);
			
			Elements typeElement = ((Element) trenElement.childNodes().get(4)).getElementsByTag("option");
			for (Element element : typeElement) {
				if (element.text().contains("2+2 Pulman (Ekonomi)")) {
					String pulman = element.childNode(0).toString()
							.split("2\\+2 Pulman \\(Ekonomi\\) \\(")[1];
					// System.out.println("Pulman : " + pulman.substring(0, pulman.length() - 1));
					tren.setPulman(pulman.substring(0, pulman.length() - 1));
				} else if (element.text().contains("2+2 ECONOMY YEMEKLİ (Ekonomi)")) {
					String yemekli = element.childNode(0).toString()
							.split("2\\+2 ECONOMY YEMEKLİ \\(Ekonomi\\) \\(")[1];
					// System.out.println("Yemekli : " + yemekli.substring(0, yemekli.length() - 1));
					tren.setYemekli(yemekli.substring(0, yemekli.length() - 1));
				} else if (element.text().contains("2+1 Pulman (Business)")) {
					String business = element.childNode(0).toString()
							.split("2\\+1 Pulman \\(Business\\) \\(")[1];
					// System.out.println("Business : " + business.substring(0, business.length() - 1));
					tren.setBusiness(business.substring(0, business.length() - 1));
				}

			}
			trenList.add(tren);
		}
		return trenList;
	}
}
