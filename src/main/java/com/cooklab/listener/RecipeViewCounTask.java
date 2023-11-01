package com.cooklab.listener;

import java.util.Map;
import java.util.TimerTask;

import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;

public class RecipeViewCounTask extends TimerTask {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	@Override
	public void run() {
		try {
			factory.getCurrentSession().beginTransaction();
			System.out.println("rollback"); //測試
			Jedis jedis = JedisUtil.getJedisPool().getResource();
			jedis.select(9);
			Map<String, String> mapRecipeViewCount = jedis.hgetAll("recipeViewCount");
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
