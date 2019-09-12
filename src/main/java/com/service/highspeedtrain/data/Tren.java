package com.service.highspeedtrain.data;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Tren {

	private String saat;
	private String pulman;
	private String yemekli;
	private String business;

	public boolean isPulman() {
		return !pulman.equals("0");
	}
	
	public boolean isBusiness() {
		return !business.equals("0");
	}

}
