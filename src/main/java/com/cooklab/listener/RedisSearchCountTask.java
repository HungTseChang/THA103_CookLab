package com.cooklab.listener;

import java.util.Timer;
import java.util.TimerTask;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSearchCountTask {
	private final Timer timer = new Timer();

	public void start() {
		timer.schedule(new SyncTask(), 0, 300);
	}

	public void stop() {
		timer.cancel();
	}

	class SyncTask extends TimerTask {
		@Override
		public void run() {
			try {
				JedisPool jedisPool = JedisUtil.getJedisPool();
				Jedis jedis = jedisPool.getResource();

				// 从 Redis 中获取 productNo（假设 productNo 是 Redis 中的键）
				String productNoKey = "productNo"; // Redis 中存储 productNo 的键名
				String productNoValue = jedis.get(productNoKey);

				if (productNoValue != null) {
					int productNo = Integer.parseInt(productNoValue);
					// 从 Redis 中获取 searchCount
					jedis.select(2);
					String productKey = "product:" + productNo;
					String searchCount = jedis.hget(productKey, "searchCount");
					// 在这里，你可以调用你的 DAO 或服务来获取指定 productNo 的 ProductVO
					ProductService productSvc = new ProductService();
					ProductVO productVO = productSvc.getOneProduct(productNo);

					if (productVO != null) {
						// 更新 ProductVO 中的 searchCount
						productVO.setSearchCount(Integer.parseInt(searchCount));
						// 保存更新到数据库
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
