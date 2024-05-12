package learn.kafka.dto;

import lombok.Data;

@Data
public class Message {
	
	String message ;
	Message(String message){
		this.message = message;
	}
	
	String getMessage() {
		return this.message;
	}
	
	void setMessage(String text){
		this.message = text;
	}

}
