package com.gsc.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsc.demo.domain.CustomerInput;
import com.gsc.demo.service.KafkaPublishService;

@RestController
public class CustomerController {
	
	@Autowired
	private KafkaPublishService service;
	
	
	@PostMapping("/post-event")
	String publishKafkaEvent(@RequestBody @Valid CustomerInput inputEvent,
			@RequestParam(required = false) String topic) {
		
		return service.publishEvent(inputEvent,topic);
		
	}

}
