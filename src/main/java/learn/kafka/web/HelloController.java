package learn.kafka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.kafka.service.MessageConsumer;
import learn.kafka.service.MessageProducer;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	@Autowired
	MessageProducer producer;
	
	@Autowired
	MessageConsumer consumer;

    @GetMapping(path = "/hello")
    public String hello() {
    	for(int i = 0; i < 10; i ++) {
    		producer.produceMessage(i + ": Hello Ankit, Kafka learning");
    	}
    	
    	consumer.consume();
    	
        return new String("Hello World!");
    }
}
