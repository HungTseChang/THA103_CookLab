package com.cooklab.listener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebListener
public class ProductKeyWordListemer implements ServletContextListener {
	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// 定時執行緒池中執行緒的數量
		scheduler = Executors.newScheduledThreadPool(1);

		long currentTime = System.currentTimeMillis();
		long initialDelay;
		int hour = 2;
		int minute = 0;
		int second = 0;

		// 初始時間
		initialDelay = computeInitialDelay(hour, minute, second);

		// 照固定的速率執行方法，按照指定的時間間隔執行方法。
		// 任務的執行頻率不受執行時間的影響
		//
//		scheduler.scheduleAtFixedRate(() -> {
//			generateDailyRandomProductList();
		// 初始時間 間隔時間 單位
//		}, initialDelay, 24, TimeUnit.HOURS);

		// 按照固定的延遲時間執行方法，方法執行完成後等待一段時間再次執行
		scheduler.scheduleWithFixedDelay(() -> {
			generateDailyHotProductList();
			// 延遲時間 間隔時間 單位
		}, 0, 1, TimeUnit.MINUTES);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		if (scheduler != null) {
			scheduler.shutdown();
		}
	}

	// 延遲間隔
	private long computeInitialDelay(int targetHour, int targetMinute, int targetSecond) {
		// 當前系統時間
		long now = System.currentTimeMillis();

		// 建立目標時間
		Calendar targetTime = Calendar.getInstance();
		targetTime.set(Calendar.HOUR_OF_DAY, targetHour);
		targetTime.set(Calendar.MINUTE, targetMinute);
		targetTime.set(Calendar.SECOND, targetSecond);

		// 超過現在時間 日期+1
		if (now > targetTime.getTimeInMillis()) {
			targetTime.add(Calendar.DAY_OF_MONTH, 1);
		}

		// 還差多少時間
		return targetTime.getTimeInMillis() - now;
	}

	private void generateDailyHotProductList() {
		System.out.println("監聽器 - 產生商品熱搜關鍵字");

		List<String> topSearchProductNames = getTopSearchProductNamesFromDatabase();
		JedisPool jedisPool = JedisUtil.getJedisPool();
		Jedis jedis = jedisPool.getResource();

		try {
			jedis.select(3);
			jedis.del("daily_random_product_names");

			for (String productName : topSearchProductNames) {
				jedis.sadd("daily_random_product_names", productName);
			}
			System.out.println("存储到Redis成功!");

		} finally {
			jedis.close();
		}
	}

	private List<String> getTopSearchProductNamesFromDatabase() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			session.beginTransaction();

			ProductService productSvc = new ProductService();
			List<ProductVO> listproduct = productSvc.findTopSearchCountProduct();
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			List<String> topSearchProductNames = new ArrayList<>();

			for (ProductVO item : listproduct) {
				Timestamp shelfTimes = item.getShelfTime();
				Timestamp offsaleTimes = item.getOffsaleTime();
				if (shelfTimes == null || offsaleTimes == null
						|| (shelfTimes.before(currentTime) && currentTime.before(offsaleTimes))) {
					String Proudctname = item.getProductName();
					topSearchProductNames.add(Proudctname);
				}

			}

			session.getTransaction().commit();
			return topSearchProductNames;

		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		return null;
	}
}
