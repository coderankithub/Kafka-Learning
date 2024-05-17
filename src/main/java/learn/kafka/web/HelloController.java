package learn.kafka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.kafka.service.MessageProducer;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	@Autowired
	MessageProducer producer;

    @GetMapping(path = "/hello")
    public String hello() {
    	producer.produceMessage("Hello Ankit, Kafka learning");
        return new String("Hello World!");
    }
}
