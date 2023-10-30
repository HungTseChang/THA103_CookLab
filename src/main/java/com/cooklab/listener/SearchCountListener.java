package com.cooklab.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class SearchCountListener implements ServletContextListener {

    private RedisSearchCountTask task;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        task = new RedisSearchCountTask();
        task.start(); //啟動排程
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (task != null) {
            task.stop(); // 關閉排程
        }
    }
}
