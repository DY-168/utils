package com.spacedo.demo.concurrent.myblockquue;

public class MyQueue1 {
    private String[] data = new String[10];
    private int getIndex = 0;
    private int putIndex = 0;
    private int size = 0;

    public synchronized void put(String element) {
        System.out.println("put()1");
        wait1();
        System.out.println("put()2");
        notify();
    }

    public synchronized void s(String element) {
        System.out.println("s()1");
        wait1();
        System.out.println("s2");
        notify();
    }

    private void wait1() {
        try {
            System.out.println("wait");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String get() {
        System.out.println("get()");
        notify();
        wait1();
        return "1";
    }

    public static void main(String[] args) throws InterruptedException {
        MyQueue1 queue = new MyQueue1();
        Thread a = new Thread() {
            @Override
            public void run() {
                queue.put("1");
            }
        };
        Thread b = new Thread() {
            @Override
            public void run() {
                queue.get();
            }
        };
        Thread c = new Thread() {
            @Override
            public void run() {
                queue.s("1");
            }
        };
        Thread d = new Thread() {
            @Override
            public void run() {
                queue.get();
            }
        };
        a.start();
        c.start();
        Thread.sleep(1000);
        b.start();
        d.start();
    }
}