package com.cooklab.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cooklab.util.JedisPoolUtil;

@WebListener
public class JedisInitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		JedisPoolUtil.getJedisPool();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context ended");
		JedisPoolUtil.shutdownJedisPool();
	}

}
