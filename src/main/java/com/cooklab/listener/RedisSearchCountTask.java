package com.cooklab.listener;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSearchCountTask {
	private final Timer timer = new Timer();

	public void start() {
		// (執行方法,首次執行延遲的時間,方法間隔時間)
		timer.schedule(new SyncTask(), 0, 18000);
	}

	public void stop() {
		timer.cancel();
	}

	class SyncTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("搜尋次數排程");
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				JedisPool jedisPool = JedisUtil.getJedisPool();
				Jedis jedis = jedisPool.getResource();

				String productNoKey = "product_searchCount";

				String productNoValue = jedis.get(productNoKey);

				System.out.println("productNoValue :" + productNoValue);

				jedis.select(2);

				Map<String, String> mapProductViewCount = jedis.hgetAll("product_searchCount");
				mapProductViewCount.forEach((key, value) -> new ProductService().updateSearchCount(key, value));

				session.getTransaction().commit();
				jedis.del("product_searchCount");
				jedis.close();
			} catch (Exception e) {

				session.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}

		}
	}
}
