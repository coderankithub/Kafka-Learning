package com.example.kafka.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.KafkaProducer;
@Slf4j
@Service
public class MessageProducer {
	
	String topicName = "test";
	String key = "key";
	
	
	public void produceMessage(String message) {
		System.out.println("Producer is sending message to consumer, Message is: " + message);
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer"," org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", " org.apache.kafka.common.serialization.StringSerializer");
		
		Producer<String, String> producer = new KafkaProducer<>(props);
		ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, message);
		producer.send(record);
		producer.close();
		System.out.print("Producer has sent the message");
	}
	

}
