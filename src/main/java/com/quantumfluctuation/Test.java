package com.quantumfluctuation;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
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



        ExecutorService executor = Executors.newSingleThreadExecutor();
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
        System.out.println("end");

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
