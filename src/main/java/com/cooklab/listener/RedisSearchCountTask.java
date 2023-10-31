package com.cooklab.listener;

import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSearchCountTask {
	private final Timer timer = new Timer();

	public void start() {
		// (執行方法,首次執行延遲的時間,方法間隔時間)
		timer.schedule(new SyncTask(), 0, 60);
	}

	public void stop() {
		timer.cancel();
	}

	class SyncTask extends TimerTask {
		@Override
		public void run() {

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();

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
							System.out.println("搜尋次數Redis成功.");
						}
					}
				}
				transaction.commit();
				jedis.close();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
			}

		}
	}
}
