package com.service.highspeedtrain.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.highspeedtrain.data.Parametre;
import com.service.highspeedtrain.service.GidisTimer;
import com.service.highspeedtrain.service.MailService;

@RestController
@RequestMapping("/train")
public class TrainController {

	@Autowired
	private MailService mailService;

	@GetMapping(path = "/start")
	public ResponseEntity startTimer() {
		
		GidisTimer te1 = new GidisTimer();
		Timer t = new Timer();
		t.scheduleAtFixedRate(te1, 0, 10000);
		
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}

	@GetMapping(path = "/watches")
	public ResponseEntity getWatches() {

		try {
			Parametre parametre = new Parametre("Eski%C5%9Fehir", "%C4%B0stanbul(Pendik)", "10.09.2019");
			String response = GetPageContent(
					"https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf?"
							+ parametre.toString());

			String exampleXML = response;
			InputStream stream = new ByteArrayInputStream(exampleXML.getBytes("UTF-8"));
			Document document = new SAXBuilder().build(stream);

			Element rootNode = document.getRootElement();
			Element list = rootNode.getChild("changes");

			if (list.getContent().size() == 2) {
				Element javax = (Element) list.getContent(1);

				if ("javax.faces.ViewState".equals(javax.getAttribute("id").getValue())) {

					if (javax.getContent().size() == 2) {

						String cdata = javax.getContent(1).getValue();
						return new ResponseEntity(cdata, HttpStatus.OK);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity(Boolean.FALSE, HttpStatus.OK);
	}

	private String GetPageContent(String url) throws Exception {

		List<String> cookies = null;
		String USER_AGENT = "Mozilla/5.0";

		URL obj = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

		// default is GET
		conn.setRequestMethod("GET");

		conn.setUseCaches(false);

		// act like a browser
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : cookies) {
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

		return response.toString();
	}

}
