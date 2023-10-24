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

//下方類似@WebServlet("/xxxx")用於註冊
@ServerEndpoint("/TogetherWS/{userName}")
public class TogetherChat {
	//此Session是WebSocket的Session，類似執行緒的概念  //回傳已同步處理的set集合，使用.synchronizedSet來處理
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	/*群聊的set 裝大家的訊息，不用分對象*/
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	
	Gson gson =new Gson();
	@OnOpen //前端的onopen執行這這邊也會跟著執行              //用集合收集線上所有的user
	public void onOpen(@PathParam("userName") String sender, Session userSession) throws IOException {
		connectedSessions.add(userSession);//這個集合收集所有線上使用者的連線
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), sender);
		System.out.println(text);
		

		
	}//@PathParam("userName")此範例沒有使用到，是用於1對1的聊天

	
	
	
	
	@OnMessage  //這個@OnMessage是由WebSocket所提供其他的@也是 Session 就是發送者的Session 
	public void onMessage(Session userSession, String message) {//message 就是前端送進來的json字串
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		

		//發送歷史訊息到對話窗
		
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender , historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}
		System.out.println("Message received: " + message);
		
		for (Session session : connectedSessions) {//用迴圈把Session的資料一一拿出
			if (session.isOpen())//session.isOpen()打開持續維持通訊
				//.getAsyncRemote()表示用非同步的方式，.sendText再把裡面的文字資料一一送給聊天室所有人
				session.getAsyncRemote().sendText(message);
				JedisHandleMessage.saveChatMessage(sender, message);
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
