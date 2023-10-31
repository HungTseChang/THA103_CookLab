package com.cooklab.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

public class SingletonServletContextListener implements ServletContextListener {
	
	private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	HibernateUtil.getSessionFactory();
    	JedisUtil.getJedisPool();
    	timer = new Timer();
		timer.schedule(new RecipeViewCounTask(), 0, 60*60*1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	timer.cancel();
    	HibernateUtil.shutdown();
    	JedisUtil.shutdownJedisPool();
    }
}

