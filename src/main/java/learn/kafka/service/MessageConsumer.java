package learn.kafka.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageConsumer {
	
	String topicName = "test";
	String key = "key";
	
	
	public void consume() {
	
	Properties props = new Properties();
	props.put("bootstrap.servers", "localhost:9092");
	props.put("group.id", "consumer_group");
	props.put("key.deserializer"," org.apache.kafka.common.serialization.StringDeserializer");
	props.put("value.deserializer", " org.apache.kafka.common.serialization.StringDeserializer");
	
	KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	consumer.subscribe(Arrays.asList(topicName));
	
	while(true) {
		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
		for(ConsumerRecord<String, String> message : records) {
			System.out.print(message);
		}
	}
	}
	
	
}
