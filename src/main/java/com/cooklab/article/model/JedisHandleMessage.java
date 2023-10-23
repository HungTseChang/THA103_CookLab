package com.cooklab.article.model;

import java.util.Collections;
import java.util.List;

import com.cooklab.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage { //Redis的DAO，對Redis的操作
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)
	
	//JedisPool老師另外寫了redius的連線池，檔名JedisPoolUtil.java，可以直接用?
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	//redius一筆資料，就是一筆聊天資料，使用list存放資料是因為有順序且可以重複
	public static List<String> getHistoryMsg(String userName) {
		String key = new StringBuilder(userName).append(":").toString();
//		//sender就是key，也就是userName，使用append來串接訊息
		Jedis jedis = null;
		jedis = pool.getResource();//getResource取得連線池物件
		List<String> historyData = jedis.lrange(key, 0, -1);//拿出全部歷史訊息
		jedis.close();
		return historyData;
	}
	
	
	
//	public static List<String> getHistoryMsg(String userName) {
//	    String key = new StringBuilder(userName).append(":").toString();
//	    Jedis jedis = null;
//	    
//	    try {
//	        jedis = pool.getResource(); // 获取连接
//	        if (jedis != null) {
//	            List<String> historyData = jedis.lrange(key, 0, -1); // 获取历史消息
//	            return historyData;
//	        } else {
//	            // 连接获取失败
//	            System.err.println("Failed to obtain a Redis connection.");
//	            return Collections.emptyList(); // 返回一个空列表或者其他适当的值
//	        }
//	    } finally {
//	        if (jedis != null) {
//	            jedis.close(); // 最后记得关闭连接
//	        }
//	    }
//	}

	
	//一個JSON物件就是一筆訊息
	public static void saveChatMessage(String sender, String message) {
		
		String senderKey = new StringBuilder(sender).append(":").toString();
		//群聊儲存對話資料，大家都是發訊者同時也是收訊者，擇一存就好
		Jedis jedis = pool.getResource();
		jedis.select(13);//選擇哪一個db存放
//		jedis.expire(senderKey, 60);//這樣可以設定資料存活時間
		jedis.rpush(senderKey, message);

		jedis.close();
	}

}
