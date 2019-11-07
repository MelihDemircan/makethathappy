package com.service.highspeedtrain.controller;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
