package com.cooklab.listener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//@WebListener
public class ProductKeyWordListemer implements ServletContextListener {
	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		scheduler = Executors.newScheduledThreadPool(1);

		// 设置每天定时执行的时间，这里设置为每天的凌晨2点
		int hour = 2;
		int minute = 0;
		int second = 0;

		// 获取当前时间
		long currentTime = System.currentTimeMillis();
		long initialDelay;

		// 计算距离下一个执行时间的延迟
		initialDelay = computeInitialDelay(hour, minute, second);

		// 定时执行任务
//		scheduler.scheduleAtFixedRate(() -> {
//			generateDailyRandomProductList();
//		}, initialDelay, 24, TimeUnit.HOURS);
		
		scheduler.scheduleWithFixedDelay(() -> {
		    // 在这里编写你的任务逻辑，每次任务完成后会等待10分钟再次触发
		    // 例如，你可以在这里调用生成每日随机商品列表的方法
		    generateDailyRandomProductList();
		}, 0, 10, TimeUnit.MINUTES);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		if (scheduler != null) {
			scheduler.shutdown();
		}
	}

	// 计算距离下一个执行时间的延迟
	private long computeInitialDelay(int targetHour, int targetMinute, int targetSecond) {
		long now = System.currentTimeMillis();
		Calendar targetTime = Calendar.getInstance();
		targetTime.set(Calendar.HOUR_OF_DAY, targetHour);
		targetTime.set(Calendar.MINUTE, targetMinute);
		targetTime.set(Calendar.SECOND, targetSecond);

		if (now > targetTime.getTimeInMillis()) {
			// 如果当前时间已经超过了目标时间，就将目标时间推迟到明天
			targetTime.add(Calendar.DAY_OF_MONTH, 1);
		}

		return targetTime.getTimeInMillis() - now;
	}

	// 编写生成每日随机商品列表的方法
	private void generateDailyRandomProductList() {
		System.out.println("監聽器 - 產生隨機商品熱搜關鍵字");
		ProductService productSvc = new ProductService();
		List<ProductVO> listproduct = productSvc.getAll();
		List<String> allProductNames = listproduct.stream().map(ProductVO::getProductName).collect(Collectors.toList());

		Random random = new Random();
		List<String> randomProductNames = new ArrayList<>();

		while (randomProductNames.size() < 6 && !allProductNames.isEmpty()) {
			int randomIndex = random.nextInt(allProductNames.size());
			String randomProductName = allProductNames.remove(randomIndex);
			randomProductNames.add(randomProductName);
		}

		JedisPool jedisPool = JedisUtil.getJedisPool();
		Jedis jedis = jedisPool.getResource();

		try {
			jedis.select(3);
	        jedis.del("daily_random_product_names");
	
			for (String productName : randomProductNames) {
				jedis.sadd("daily_random_product_names", productName);
			}
			System.out.println("存储到Redis成功!");

		} finally {
			jedis.close(); // 记得关闭Jedis连接，归还到连接池
		}
	}
}