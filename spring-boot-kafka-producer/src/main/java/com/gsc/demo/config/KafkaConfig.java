package com.gsc.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.gsc.demo.domain.CustomerEvent;

@Configuration
public class KafkaConfig {
	
	@Value("${kafka.bootstrap-server}")
	private String kafkaServer;
	
	@Value("${kafka.default.topic}")
	private String defaultTopic;
	
	@Bean
	ProducerFactory<String, CustomerEvent> kafkaProducer(){
		ProducerFactory<String, CustomerEvent> producerFactory = new DefaultKafkaProducerFactory<>(getKafkaProps());
		
		return producerFactory;
	}
	
	
	Map<String,Object> getKafkaProps() {
		Map<String,Object> produerProps = new HashMap<>();
		produerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		produerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		produerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		
		//10,000 -> 10 seconds -> 10000ms
		// can be verified by making your cluster down
		produerProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 10_000);
		
		// some other safety measures to reduce head over on kafka as default values are too high
		/*
		 * request.timeout.ms = 10000
		 * retries = 2147483647
		 * retry.backoff.ms = 100
		 * 
		 * So client invokes 100 times by default
		 * */
		produerProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 10_000);
		produerProps.put(ProducerConfig.RETRIES_CONFIG, 5);
		produerProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1000);
		
		
//		produerProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS,false);
		
		return produerProps;
	}
	
	@Bean
	KafkaTemplate<String, CustomerEvent> kafkaTemplate(){
		KafkaTemplate<String, CustomerEvent> template = new KafkaTemplate<>(kafkaProducer());
		template.setDefaultTopic(defaultTopic);
		System.out.println(template.getDefaultTopic());
		return template;
		
	}

}
