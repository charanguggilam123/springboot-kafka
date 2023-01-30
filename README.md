# spring-boot-kafka

This project is a poc to demonstrate how to publish and listn to events in kafka

Below are required 
1. producer
	1. ProducerFactory: which needs map of properties to connect and key,value serializers and any additional config
	2. kafka template: A template to perform operations to publish events. It needs ProducerFactory as input to its constructor
2. consumer
	1. ConsumerFactory: which needs map of properties to connect and key,value deserializers and any additional config
	2. : To listen message we need a method with @KafkaListener which needs details of atleast group id. Alo it needs a containerFactory of type KafkaListenerContainerFactory to perform just like how kafka template needs producer factory. So we creat bean of that interface using a kafka provided impl of same using ConcurrentKafkaListenerContainerFactory which needs consumerFactory to be set
	

GOTCHA/s faced
1. Trusted packages issue: As long as package structure of producer and consumer are same,you might not face this error but in reality that won't be possible because two sides of kafka are implemented by multiple teams and naming convention can vary. So to resolve this please preform below
	1. don't consider the type config: This can be done 2 ways. one on producer and other or consumer
		producer end: spring.json.add.type.headers=false
		consumer end: spring.json.use.type.headers = false
	2.  provide default type of event at consumer end because we use jsondeserializer: spring.json.value.default.type = Customer.class.getName()


Refs: https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/#boot-features-kafka-extra-props