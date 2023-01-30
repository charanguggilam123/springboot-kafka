package com.gsc.demo.listener;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.gsc.demo.entity.Customer;

@Component
public class EventListener {

	@KafkaListener(groupId = "customer-group-1",topics = "#{'${kafka.consumer.topics}'.split(',')}",
			containerFactory = "kafkaListenerContainerFactory")
	void listener1(@Headers Map<String,Object> headers, @Payload Customer customerEvent) {
		
		System.out.println("Partition: "+headers.get(KafkaHeaders.RECEIVED_PARTITION_ID)+"event: "+customerEvent.toString());

	}
	
//	@KafkaListener(groupId = "customer-group-2",topics = "#{'${kafka.consumer.topics}'.split(',')}",
//			containerFactory = "kafkaListenerContainerFactory")
	void listener2(@Headers Map<String,String> headers, @Payload Customer customerEvent) {
		
		System.out.println("Partition: "+headers.get(KafkaHeaders.RECEIVED_PARTITION_ID)+"event: "+customerEvent.toString());

	}
	
//	@KafkaListener(groupId = "customer-group-3",topics = "#{'${kafka.consumer.topics}'.split(',')}",
//			containerFactory = "kafkaListenerContainerFactory")
	void listener3(@Headers Map<String,String> headers, @Payload Customer customerEvent) {
		
		System.out.println("Partition: "+headers.get(KafkaHeaders.RECEIVED_PARTITION_ID)+"event: "+customerEvent.toString());

	}

}
