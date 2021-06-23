package com.quantumfluctuation;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        /*Sort sort1 = new Sort();
        sort1.secoud_name="小李";
        sort1.NAME="排序算法类被改名了";
        System.out.println(sort1.secoud_name);
        System.out.println(sort1.NAME);

        Sort sort2 = new Sort();
        System.out.println(sort2.secoud_name);
        System.out.println(sort2.NAME);*/

        Sort insertSort = new Sort("插入排序算法");
        System.out.println(insertSort.secoud_name);

/*        sort1.NAME="排序算法类被改名了";
        System.out.println(Sort.NAME);
        System.out.println(sort1.NAME);
        System.out.println(sort2.NAME);*/


        /*ThreadLocal<Integer> local = new ThreadLocal<>();
        local.set(1);
        local.get();

        int poolSize =3;
        PriorityBlockingQueue<Runnable> workQueue = new PriorityBlockingQueue<>(6);
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                r.run();
            }
        };

        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger mThreadNum = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
                System.out.println(t.getName() + " has been created");
                return t;
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                poolSize,
                poolSize,
                0L,
                TimeUnit.MILLISECONDS,
                workQueue,
                threadFactory,
                rejectedExecutionHandler
        );
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            Runner runner = new Runner();
            runner.i=i;
            executor.execute(runner);
        }*/

        /*Config config = new Config();
        //config.useClusterServers().addNodeAddress("127.0.0.1:6379");
        config.useSingleServer().setAddress("redis://r-p2ue6ec1f7656e44.redis.rds.aliyuncs.com:6379").setPassword("Zw8rSmbH");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("123");
        lock.lock();
        lock.unlock();*/


        /*ExecutorService executor = Executors.newSingleThreadExecutor();
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        @SuppressWarnings("unchecked")
        final List<Integer> integers = Collections.synchronizedList(new ArrayList<>());
        for (int i=0; i<10 ; i++){
            final int a = i;
            executor.execute(new Runnable(){
                @Override
                public void run() {
                    try{
                        System.out.println(a);
                        integers.add(a);
                        Thread.sleep(1000);
                    } catch(Exception e){

                    } finally{
                        countDownLatch.countDown();
                    }
                }});
        }
        //等待所有线程执行完毕
        countDownLatch.await();
        executor.shutdown();
        System.out.println("end");*/

        /*Map<Long, BigDecimal> map = new HashMap<Long, BigDecimal>(){
            {
                put(1000096L, new BigDecimal("275000"));
            }
        };
        System.out.println(map.get(1000096L));
        System.out.println(map.get(1L));*/
    }
}

class Runner implements Runnable, Comparable{
    public int i ;

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void run() {
        System.out.println("---"+i);
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
