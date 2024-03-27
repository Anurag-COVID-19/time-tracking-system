package com.punch.punchmanagementservice;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class PunchManagementServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(PunchManagementServiceApplication.class.getSimpleName());


	public static void main(String[] args) {
		SpringApplication.run(PunchManagementServiceApplication.class, args);

		//logger.info("......Hello Kafka testing......");
		//create a producer properties
		/*Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");*/

		//set producer properties
		/*properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());*/

		//create the producer
		//KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		//create a producer record
		/*ProducerRecord<String, String> producerRecord =
				new ProducerRecord<>("demo_java_topic", "hello kafka from java");*/

		//send data
		//producer.send(producerRecord);

		//tell the producer to send all data and block until done --synchronous
		//producer.flush();

		//producer.close();
	}



}
