package com.github.mall.common.utils;

import java.util.concurrent.*;

/**
 * @Author HPC
 * @Description 线程工具类
 * @Date 2019/7/1 9:29
 */
public class ThreadUtils {

    /**
     * 获取线程池
     *
     * @param corePoolSize    线程池核心数
     * @return
     */
    public static Executor newFixedThreadPool(int corePoolSize) {
        return new ThreadPoolExecutor(corePoolSize, 200, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                r -> new Thread(r, "mall-pool-" + r.hashCode()),
                (r, executor) -> {
                });
    }
}
