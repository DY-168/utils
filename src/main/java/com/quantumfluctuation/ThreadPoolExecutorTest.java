package com.quantumfluctuation;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    @SneakyThrows
    public static void main(String[] args) {
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
                poolSize*3,
                100L,
                TimeUnit.MILLISECONDS,
                workQueue,
                threadFactory,
                rejectedExecutionHandler
        );
        //executor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            Runner runner = new Runner();
            runner.i=i;
            executor.execute(runner);
        }
        for (;;){
            Thread.sleep(1000);
            System.out.println(executor.getActiveCount());
        }
    }
}
