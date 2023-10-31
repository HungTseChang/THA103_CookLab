package com.cooklab.listener;

import java.util.Map;
import java.util.TimerTask;

import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.util.JedisUtil;

import redis.clients.jedis.Jedis;

public class RecipeViewCounTask extends TimerTask {

	@Override
	public void run() {
		try {
			Jedis jedis = JedisUtil.getJedisPool().getResource();
			jedis.select(9);
			Map<String, String> mapRecipeViewCount = jedis.hgetAll("recipeViewCount");
			mapRecipeViewCount.forEach((key, value) -> new RecipeServiceIm().updateViewCount(key, value));
			jedis.del("recipeViewCount");
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
