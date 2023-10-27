package com.cooklab.article.model;


import java.sql.Timestamp;

public class ChatMessage { //這邊是對應chat.jsp，addListener()的JSON物件
	private String room;
	private String userName;
	private String message;
	private Timestamp  time;

	public ChatMessage(String room, String userName, String message ) {
		this.room = room;
		this.userName = userName;
		this.message = message;
//		this.time = time;
		
	}

	
	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public String getuserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
