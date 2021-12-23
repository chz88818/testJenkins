package com.chz.jenkins.socket;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/11/25/10:42
 * @Description: 线程池工具类
 */
public class ThreadPoolUtil {


    /**
     * 核心线程数
     */
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    /**
     * 最大线程数
     */
    public static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 2;

    /**
     * 线程缓冲队列
     */
    private static final BlockingQueue<Runnable> BUF_QUEUE = new ArrayBlockingQueue<>(50);

    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
            6, TimeUnit.SECONDS, BUF_QUEUE, new NamedThreadFactory("随便"),new ThreadPoolExecutor.DiscardPolicy());

    static {
        THREAD_POOL.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getPool() {
        return THREAD_POOL;

    }
    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            if (null == name || name.isEmpty()) {
                name = "pool";
            }
            namePrefix = name+"-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}


