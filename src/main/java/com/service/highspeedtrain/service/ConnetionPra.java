package com.service.highspeedtrain.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.service.highspeedtrain.data.Parametre;
import com.service.highspeedtrain.data.Tren;

@Component
public class ConnetionPra {

	private List<String> cookies;
	private HttpsURLConnection conn;

	private final String USER_AGENT = "Mozilla/5.0";

	public String GetPageContent(String url) throws Exception {
		return GetPageContent(url, null);
	}

	public String GetPageContent(String url, Parametre parametre) throws Exception {

		url = url + (parametre == null ? "" : ("?" + parametre.toString()));

		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();

        SSLContext sc = SSLContext.getInstance("SSL");  
        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());  

        conn.setSSLSocketFactory(sc.getSocketFactory());
		// default is GET
		conn.setRequestMethod("GET");

		conn.setUseCaches(false);

		// act like a browser
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(conn.getHeaderFields().get("Set-Cookie"));

		return response.toString();

	}

	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

	public String Seferler(String url) throws Exception {
		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();

		// default is GET
		conn.setRequestMethod("GET");

		conn.setUseCaches(false);

		// act like a browser
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}

		 SSLContext sc = SSLContext.getInstance("SSL");  
	        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());  

	        conn.setSSLSocketFactory(sc.getSocketFactory());
	        
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(conn.getHeaderFields().get("Set-Cookie"));

		return response.toString();

	}

	public List<Tren> getFormParams(String html) throws UnsupportedEncodingException {

		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementById("mainTabView:gidisSeferTablosu_data");
		Elements trensChild = loginform.children();
		List<Tren> trenList = new ArrayList<>();
		for (Element trenElement : trensChild) {

			Tren tren = new Tren();
			for (int j = 0; j < trenElement.children().size(); j++) {
				try {
					if (j == 0) {
						// Sefer Saati

//					System.out.println(trenElement.children().get(j).getElementsByTag("span").get(0).childNode(0));
						String saat = trenElement.children().get(j).getElementsByTag("span").get(0).childNode(0)
								.toString();
						tren.setSaat(saat);

					} else if (j == 4) {
						// Doluluk Bilgisi

						if (trenElement.children().get(j).getElementsByTag("li").size() < 3)
							break;

						for (int i = 0; i < trenElement.children().get(j).getElementsByTag("li").size(); i++) {
							Element element = trenElement.children().get(j).getElementsByTag("li").get(i);

							if (i == 0) {
								String pulman = element.childNode(0).toString()
										.split("2\\+2 Pulman \\(Ekonomi\\) \\(")[1];
//								System.out.println("Pulman : " + pulman.substring(0, pulman.length() - 1));
								tren.setPulman(pulman.substring(0, pulman.length() - 1));
								if (pulman.substring(0, pulman.length() - 1) != "0") {

								}
							} else if (i == 1) {
								String yemekli = element.childNode(0).toString()
										.split("2\\+2 ECONOMY YEMEKLİ \\(Ekonomi\\) \\(")[1];
//								System.out.println("Yemekli : " + yemekli.substring(0, yemekli.length() - 1));
								tren.setYemekli(yemekli.substring(0, yemekli.length() - 1));
								if (yemekli.substring(0, yemekli.length() - 1) != "0"
										&& yemekli.substring(0, yemekli.length() - 1) != "1"
										&& yemekli.substring(0, yemekli.length() - 1) != "1") {

								}

							} else if (i == 2) {
								String business = element.childNode(0).toString()
										.split("2\\+1 Pulman \\(Business\\) \\(")[1];
//								System.out.println("Business : " + business.substring(0, business.length() - 1));
								tren.setBusiness(business.substring(0, business.length() - 1));
								if (business.substring(0, business.length() - 1) != "0") {

								}
							}
						}

					}
				} catch (Exception e) {
					System.out.println(e);
					return new ArrayList<>();
				}
			}

			trenList.add(tren);
		}
		return trenList;
	}
}