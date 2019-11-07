package com.service.highspeedtrain.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class HttpClientExample {

	private List<String> cookies;

	public String sendPostGetView(String nereden, String nereye, String tarih) throws Exception {
		String url = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";

		HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

		// add reuqest header
		httpClient.setRequestMethod("POST");
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "biletAramaForm=biletAramaForm"
				+ "&btnSeferSorgula=btnSeferSorgula"
				// + "&javax.faces.ViewState=" + URLEncoder.encode("-3653684613586726248:3715915168865301011", "UTF-8")
				+ "&javax.faces.partial.ajax=true"
				+ "&javax.faces.partial.execute=btnSeferSorgula+biletAramaForm"
				+ "&javax.faces.partial.render=msg"
				+ "&javax.faces.source=btnSeferSorgula"
				+ "&nereden=" + nereden
				+ "&nereye=" + nereye
				+ "&syolcuSayisi_input=1"
				+ "&tipradioIslemTipi=1"
				+ "&tipradioSeyehatTuru=1"
				+ "&trCalGid_input=" + tarih;

		httpClient.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
			wr.writeBytes(urlParameters);
			wr.flush();
		}

		int responseCode = httpClient.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(httpClient.getInputStream()))) {

			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			// print result

			String a = response.toString();
			// a.split("update")
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(new InputSource(new StringReader(a)));
			String viewState = doc.getElementsByTagName("update").item(1).getFirstChild().getTextContent();

			cookies = httpClient.getHeaderFields().get("Set-Cookie");

			return viewState;
		}

	}

	public void sendPostRequest(String viewState, String nereden, String nereye, String tarih) throws Exception {

		String url = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";

		HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

		// add reuqest header
		httpClient.setRequestMethod("POST");
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				httpClient.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		String urlParameters = "biletAramaForm=biletAramaForm"
				+ "&btnSeferSorgula=btnSeferSorgula"
				+ "&javax.faces.ViewState=" + URLEncoder.encode(viewState, "UTF-8")
				+ "&javax.faces.partial.ajax=true"
				+ "&javax.faces.partial.execute=btnSeferSorgula+biletAramaForm"
				+ "&javax.faces.partial.render=msg"
				+ "&javax.faces.source=btnSeferSorgula"
				+ "&nereden=" + nereden
				+ "&nereye=" + nereye
				+ "&syolcuSayisi_input=1"
				+ "&tipradioIslemTipi=1"
				+ "&tipradioSeyehatTuru=1"
				+ "&trCalGid_input=" + tarih;

		httpClient.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
			wr.writeBytes(urlParameters);
			wr.flush();
		}

		int responseCode = httpClient.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(httpClient.getInputStream()))) {

			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			// print result
			System.out.println(response.toString());

		}

	}

	public String sendPostGetTrain() throws Exception {

		String url = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/int_sat_001.jsf";

		HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

		// add reuqest header
		httpClient.setRequestMethod("POST");
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				httpClient.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		httpClient.setDoOutput(true);

		int responseCode = httpClient.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(httpClient.getInputStream()))) {

			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			return response.toString();
		}

	}

}
