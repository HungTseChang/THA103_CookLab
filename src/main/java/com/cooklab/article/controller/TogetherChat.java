package com.cooklab.article.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.cooklab.article.model.ChatMessage;
import com.cooklab.article.model.JedisHandleMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

//下方類似@WebServlet("/xxxx")用於註冊
@ServerEndpoint("/TogetherWS")
public class TogetherChat {

	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	Gson gson =new Gson();
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
		//下面直接讀取資redis讀取歷史資料
		
		String room= "Article";
//		List<String> historyData = JedisHandleMessage.getHistoryMsg(room);
//		String historyMsg = gson.toJson(historyData);
//	    ChatMessage cmHistory = new ChatMessage(room, userName,historyMsg);
//	    //發送歷史訊息到對話窗
//	    userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
		List<String> historyData = JedisHandleMessage.getHistoryMsg(room);

		for (String data : historyData) {
		    JsonObject historyObj = gson.fromJson(data, JsonObject.class);

		    String userName1 = historyObj.get("userName").getAsString();
		    String message = historyObj.get("message").getAsString();

		    
		    ChatMessage cmHistory = new ChatMessage(room, userName1, message);
		    userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
		}

	
	    
	    
	}


	@OnMessage
	public void onMessage(Session userSession, String message) {
		
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String userName = chatMessage.getuserName();
		String room =chatMessage.getRoom();

		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);//傳前台
				JedisHandleMessage.saveChatMessage(room,userName, message);
			}
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
}




//@ServerEndpoint("/TogetherWS/{userName}")
//public class TogetherChat {
//
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
//	Gson gson =new Gson();
//	@OnOpen
//	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
//		
//		connectedSessions.add(userSession);
//		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
//		System.out.println(text);
//		//下面直接讀取資redis讀取歷史資料
//		List<String> historyData = JedisHandleMessage.getHistoryMsg(userName);
//
//		String historyMsg = gson.toJson(historyData);
//	    ChatMessage cmHistory = new ChatMessage(userName, historyMsg);
//	    //發送歷史訊息到對話窗
//	    userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
	    
//	}
//
//
//	@OnMessage
//	public void onMessage(Session userSession, String message) {
//		
//		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
//		String sender = chatMessage.getSender();
//		
//		
//
//		for (Session session : connectedSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//				JedisHandleMessage.saveChatMessage(sender, message);
//			}
//		System.out.println("Message received: " + message);
//	}
//
//	@OnClose
//	public void onClose(Session userSession, CloseReason reason) {
//		connectedSessions.remove(userSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
//				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
//	}
//
//	@OnError
//	public void onError(Session userSession, Throwable e) {
//		System.out.println("Error: " + e.toString());
//	}
//}
