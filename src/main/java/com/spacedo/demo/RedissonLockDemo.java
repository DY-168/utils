package com.spacedo.demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonLockDemo {
    public static void main(String[] args) {
        lockDemo();
    }

    private static void lockDemo() {
        RedissonClient redissonClient = getRedissonClient();
        RLock lock = redissonClient.getLock("123");
        lock.lock();
        lock.unlock();
    }

    private static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("127.0.0.1:6379");
        config.useSingleServer().setAddress("redis://r-p2ue6ec1f7656e44.redis.rds.aliyuncs.com:6379").setPassword("Zw8rSmbH");
        return Redisson.create(config);
    }
}
