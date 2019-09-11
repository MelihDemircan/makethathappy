package com.service.highspeedtrain.data;

import lombok.Data;

@Data
public class Tren {

	private String saat;
	private String pulman;
	private String yemekli;
	private String business;

	public boolean isPulman() {
		return !pulman.equals("0");
	}

}
