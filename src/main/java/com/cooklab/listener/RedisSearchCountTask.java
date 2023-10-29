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
		//(執行方法,首次執行延遲的時間,方法間隔時間)
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
