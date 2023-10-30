package com.cooklab.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cooklab.question.model.QuestionDTO;
import com.cooklab.question.service.QuestionHService;
import com.cooklab.util.JedisUtil;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebListener
public class QuestionRedisCacheListener implements ServletContextListener {
	private ScheduledExecutorService scheduler;
	private static String prefix = "Group";

	public QuestionRedisCacheListener() {
	}

	public void contextInitialized(ServletContextEvent sce) {

		scheduler = Executors.newScheduledThreadPool(1);
		System.out.println("常見問題清單準備中");
		// Lambda語法實作Runnable介面
		scheduler.scheduleAtFixedRate(() -> {
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			jedis.select(8);
			Gson gson = new Gson();
			QuestionHService qsvc = new QuestionHService();
//			for (int i = 1; i <= 6; i++) {
//				System.out.println("迴圈啟動");
//				List<QuestionDTO> list = qsvc.getByGroup(i);
//				System.out.println(i);
//				for (int j = 0; j < list.size(); j++) {
//					jedis.lpush(prefix + i, gson.toJson(list.get(j)));
//					System.out.println("迴圈執行中");
//				}
//			}
			List<QuestionDTO> list = qsvc.getByGroup(1);
			jedis.lpush(prefix + 1, gson.toJson(list));
			jedis.close();
			System.out.println("常見問題清單已更新至Redis");
		}, 0, 10, TimeUnit.SECONDS); // 每10秒執行一次，視情況變更頻率
	}

	public void contextDestroyed(ServletContextEvent sce) {
		scheduler.shutdown();
	}
}
