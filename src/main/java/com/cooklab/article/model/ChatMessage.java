package com.cooklab.article.model;


import java.sql.Timestamp;

public class ChatMessage { //這邊是對應chat.jsp，addListener()的JSON物件
	private String type;
	private String sender;
	private String message;
	private Timestamp  time;

	public ChatMessage(String type, String sender, String message ) {
		this.type = type;
		this.sender = sender;
		this.message = message;
//		this.time = time;
		
	}

	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
	
}
