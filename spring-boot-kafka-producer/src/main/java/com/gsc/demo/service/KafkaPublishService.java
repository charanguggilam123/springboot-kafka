package com.gsc.demo.service;

import com.gsc.demo.domain.CustomerInput;

public interface KafkaPublishService {
	
	String publishEvent(CustomerInput input,String topic);

}
