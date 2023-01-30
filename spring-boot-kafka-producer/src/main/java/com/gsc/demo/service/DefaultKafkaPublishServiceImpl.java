package com.gsc.demo.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.gsc.demo.domain.CustomerEvent;
import com.gsc.demo.domain.CustomerInput;
import com.gsc.demo.exception.KafkaPublishException;

@Service
public class DefaultKafkaPublishServiceImpl implements KafkaPublishService {

	@Autowired
	private KafkaTemplate<String, CustomerEvent> kafkaTemplate;

	@Override
	public String publishEvent(CustomerInput input,String topic) {
		CustomerEvent event = transformCustomerInput(input);
		ListenableFuture<SendResult<String, CustomerEvent>> resp;
		try {
		if(topic!=null)
			resp=kafkaTemplate.send(topic, input.getCustomerNumber(), event);
		else
			resp=kafkaTemplate.sendDefault(input.getCustomerNumber(), event);
		RecordMetadata metadata = resp.get().getRecordMetadata();
		return String.format("Event sucesfully published to partition %s ",metadata.partition());
		
		}catch(Exception e) {
			Thread.currentThread().interrupt();
			throw new KafkaPublishException(e);
			
		}

	}

	private CustomerEvent transformCustomerInput(CustomerInput input) {
		return  new CustomerEvent(input.getCustomerNumber(), input.getFirstName() +" "+ input.getLastName(),
				input.getBirthDate(), input.getEmail(), Long.valueOf(input.getMobileNumber()), input.getAddress());


	}

}
