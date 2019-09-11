package com.service.highspeedtrain.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Ranga Karanam", "http://www.in28minutes.com",
			"in28minutes@gmail.com");

//	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title", "Awesome API Description", "1.0",
//			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Turkcell Ecommerce Microservices", "Ecommerce REST API", "1.0.0",
				"https://www.turkcell.com.tr",
				new Contact("Ecommerce Team", "https://www.turkcell.com.tr", "TEAM-DCSD-ECOMMERCE@turkcell.entp.tgc"),
				"Turkcell", "https://www.turkcell.com.tr", Collections.emptyList());
	}
}