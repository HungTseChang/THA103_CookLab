package com.cooklab.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class searchCountListener implements ServletContextListener {

    private RedisSearchCountTask task;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        task = new RedisSearchCountTask();
        task.start(); // 启动定时任务
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (task != null) {
            task.stop(); // 停止定时任务
        }
    }
}
