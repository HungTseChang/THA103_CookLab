package com.cooklab.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cooklab.util.HibernateUtil;
import com.cooklab.util.JedisUtil;

public class SingletonServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	HibernateUtil.getSessionFactory();
    	JedisUtil.getJedisPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	HibernateUtil.shutdown();
    	JedisUtil.shutdownJedisPool();
    }
}

