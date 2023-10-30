package com.cooklab.listener;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;

public class RecipeViewCountListener implements ServletContextListener {
	private Timer timer;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer();
		timer.schedule(new RecipeViewCounTask(), 0, 60*60*1000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		;
	}

	public class RecipeViewCounTask extends TimerTask {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		@Override
		public void run() {
			try {
				Jedis jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(9);
				Map<String, String> mapRecipeViewCount = jedis.hgetAll("recipeViewCount");
				factory.getCurrentSession().beginTransaction();
				mapRecipeViewCount.forEach((key, value) -> new RecipeServiceIm().updateViewCount(key, value));
				factory.getCurrentSession().getTransaction().commit();
				jedis.del("recipeViewCount");
				jedis.close();
			} catch (Exception e) {
				factory.getCurrentSession().getTransaction().rollback();
				e.printStackTrace();
			}
		}

	}
}
