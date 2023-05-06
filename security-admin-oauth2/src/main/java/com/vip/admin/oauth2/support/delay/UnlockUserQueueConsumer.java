package com.vip.admin.oauth2.support.delay;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.extra.spring.SpringUtil;
import com.vip.admin.oauth2.service.RbacUserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author echo
 * @version 1.0
 * @date 2023/5/6 15:21
 */
@Slf4j
@Component
public class UnlockUserQueueConsumer implements Runnable, InitializingBean, DisposableBean {

    private final RBlockingQueue<Pair<String, String>> lockedQueue;
    private final RedissonClient redissonClient;

    public UnlockUserQueueConsumer(RedissonClient redissonClient) {
        this.lockedQueue = redissonClient.getBlockingQueue("user_locked_queue");
        this.redissonClient = redissonClient;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                Pair<String, String> pair = lockedQueue.take();
                RbacUserService userService = SpringUtil.getBean(RbacUserService.class);
                userService.unlock(pair.getKey(), pair.getValue());
                log.info("用户：{} 因多次错误输入密码被锁定，已过1小时自动解锁:{}", pair.getKey(), LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        ExecutorService executor = ExecutorBuilder.create().setCorePoolSize(5).setMaxPoolSize(10).setWorkQueue(new LinkedBlockingQueue<>(100)).build();
        executor.execute(this);
    }

    @Override
    public void destroy() {
        RDelayedQueue<Pair<String, String>> delayedQueue = redissonClient.getDelayedQueue(lockedQueue);
        //在该对象不再需要的情况下，应该主动销毁。仅在相关的Redisson对象也需要关闭的时候可以不用主动销毁。
        delayedQueue.destroy();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>锁定用户延迟队列已销毁>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
