package com.service.highspeedtrain.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.highspeedtrain.data.Tren;

@Component
public class Gidis {

	@Autowired
	private MailService mailService = new MailService();

	public void start(String nereden, String nereye, String tarih, List<String> saatler) throws Exception {

		HttpClientExample obj = new HttpClientExample();

		String viewState = obj.sendPostGetView(URLEncoder.encode(nereden, "UTF-8"), URLEncoder.encode(nereye, "UTF-8"), URLEncoder.encode(tarih, "UTF-8"));
		obj.sendPostRequest(viewState, URLEncoder.encode(nereden, "UTF-8"), URLEncoder.encode(nereye, "UTF-8"), URLEncoder.encode(tarih, "UTF-8"));
		String response = obj.sendPostGetTrain();
		List<Tren> trenList = new TrainParse().getFormParams(response);

		List<Tren> trenSaatler = trenList.stream().filter(tren -> saatler.contains(tren.getSaat()))
				.collect(Collectors.toList());

		System.out.println("Trenler : " + trenList.stream().map(Object::toString).collect(Collectors.joining(",")));
		System.out.println("Saatler : " + saatler.stream().map(Object::toString).collect(Collectors.joining(",")));
		System.out.println(
				"Trenler Saatler : " + trenSaatler.stream().map(Object::toString).collect(Collectors.joining(",")));

		for (Tren tren : trenSaatler) {
			if (tren != null && tren.isPulman()) {
				System.out.println("Sefer Bulundu " + nereden + "/" + nereye + "/" + tarih + "/" + tren.getSaat());

				System.out.println("Sefer Bulundu " + tren.getSaat());
				mailService.send(nereden + "/" + nereye + " (" + tren.getSaat() + ") (" + tarih + ") " + "("
						+ tren.getPulman() + ")");
			}
		}
		System.out.println("•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
	}
}
