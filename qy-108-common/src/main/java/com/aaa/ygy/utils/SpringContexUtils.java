package com.aaa.ygy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: qy-108
 * @description: utils
 * 获取spring的容器工具类
 * @author: ygy
 * @create: 2020-05-12-2020/5/12 18:28
 */
public class SpringContexUtils implements ApplicationContextAware {

    private SpringContexUtils() {

    }

    private static ApplicationContext APPLICATIONCONTEXT = null;
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATIONCONTEXT = applicationContext;

    }

    public static ApplicationContext getApplicationContext() {
        Lock lock = READ_WRITE_LOCK.readLock();
        lock.lock();
        try {
            if (null != APPLICATIONCONTEXT) {
                return APPLICATIONCONTEXT;
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }
}