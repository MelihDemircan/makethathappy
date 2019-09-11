package com.service.highspeedtrain.data;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"filter1","filter2"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

	private String filter1;
	private String filter2;
	//@JsonIgnore
	private String filter3;
}
