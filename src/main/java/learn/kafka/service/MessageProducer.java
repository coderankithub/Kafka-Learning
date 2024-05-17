package learn.kafka.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.Callback;
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
		try {
			
			// Asynchronous call 
			producer.send(record, new MyProducerCallBack());
			
			// Synchronous call 
//			RecordMetadata metaData = producer.send(record).get();
//			System.out.println("Message is sent to partition no: " + metaData.partition()
//			+ " and offset :" + metaData.offset());
			System.out.println("Synchronous producer completed.");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Synchronous producer failed.");
		}finally {
			producer.close();
		}
		System.out.print("Producer has sent the message");
	}
	
	class MyProducerCallBack implements Callback{

		@Override
		public void onCompletion(RecordMetadata metadata, Exception exception) {
			if(exception!= null) {
				System.out.print("Asynchronous Produer failed with an exception");
			}else {
				System.out.print("Asynchronous Produer call success:");
			}
			
		}}
	

}

