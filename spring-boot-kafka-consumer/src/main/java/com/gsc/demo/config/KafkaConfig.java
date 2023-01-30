package com.gsc.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.gsc.demo.entity.Customer;

@Configuration
public class KafkaConfig {

	@Value("${kafka.bootstrap-server}")
	private String kafkaServer;

	@Bean
	public ConsumerFactory<String, Customer> getKafkaConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(getConsumerProperties());
//		return new DefaultKafkaConsumerFactory<>(getConsumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Customer.class));
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory(
			ConsumerFactory<String, Customer> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
		return kafkaListenerContainerFactory;

	}

	private Map<String, Object> getConsumerProperties() {
		
		Map<String, Object> consumerProps = new HashMap<>();
		consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		
		/* when using json serializer at consumer end deserialization issue might come if
		 *  package structure and name of pojo doesn't match both ends.
		 *  So to resolve this we are first setting use type headers to false and providing
		 *  default pojo that needs to consider to deserialize
		 *  
		 *  good explanation provided: https://stackoverflow.com/questions/51688924/spring-kafka-the-class-is-not-in-the-trusted-packages
		 *  which also has ref to official spring doc i.e
		 *  https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/#boot-features-kafka-extra-props
		 *  */
		consumerProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
		consumerProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE,Customer.class.getName());
//		consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "customer-group-1");
//		consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.gsc.demo.domain.CustomerEvent");
		return consumerProps;

	}

}
