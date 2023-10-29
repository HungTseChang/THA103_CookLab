package com.cooklab.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;

public class RecipeViewCountListemer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	public class RecipeViewCounTask extends TimerTask {
		private final Timer timer = new Timer();

		public void start() {
			timer.schedule(new RecipeViewCounTask(), 0, 300);
		}

		public void stop() {
			timer.cancel();
		}

		@Override
		public void run() {
			try {
				Jedis jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(9);
				String reicpeNo = jedis.hget("recipeViewCount:)
				
				
				
				if (!jedis.lrange("recipeViewIP:" + recipeNo, 0, -1).contains(ipAddress)) {
					List<String> ipAddresses = new ArrayList<>();
					ipAddresses.add(ipAddress);
					jedis.rpush("recipeViewIP:" + recipeNo, ipAddresses.toArray(new String[0]));
					jedis.expire("recipeViewIP:" + recipeNo, 300);
					jedis.incrBy("recipeViewCount:" + recipeNo, 1);
				String productNoKey = "productNo";
				String productNoValue = jedis.get(productNoKey);

				if (productNoValue != null) {
					int productNo = Integer.parseInt(productNoValue);

					jedis.select(2);
					String productKey = "product:" + productNo;
					String searchCount = jedis.hget(productKey, "searchCount");

					ProductService productSvc = new ProductService();
					ProductVO productVO = productSvc.getOneProduct(productNo);

					if (productVO != null) {
						productVO.setSearchCount(Integer.parseInt(searchCount));

						String result = productSvc.update(productVO);
						if ("success".equals(result)) {
							System.out.println("Redis data sync to MySQL completed.");
						}
					}
				}

				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
