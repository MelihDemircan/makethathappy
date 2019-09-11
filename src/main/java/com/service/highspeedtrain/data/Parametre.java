package com.service.highspeedtrain.data;

import java.util.ArrayList;

public class Parametre extends ArrayList<String> {

	public Parametre(String nereden, String nereye, String gidisTarih) {
		add("javax.faces.partial.ajax=true");
		add("&javax.faces.source=btnSeferSorgula");
		add("&javax.faces.partial.execute=btnSeferSorgula+biletAramaForm");
		add("&javax.faces.partial.render=msg");
		add("&btnSeferSorgula=btnSeferSorgula");
		add("&biletAramaForm=biletAramaForm");
		add("&tipradioIslemTipi=0");
		add("&nereden=" + nereden);
		add("&trCalGid_input=" + gidisTarih);
		add("&tipradioSeyehatTuru=1");
		add("&nereye=" + nereye);
		add("&syolcuSayisi_input=1");
		// add("&javax.faces.ViewState=7720274641158763554%3A7835593288067688993");

	}

	@Override
	public String toString() {
		String listString = "";

		for (String s : this) {
			listString += s;
		}

		return listString;
	}
}