package com.spacedo.demo.concurrent.myblockquue;

public class MyQueueS {
    private String[] data = new String[10];
    private int getIndex = 0;
    private int putIndex = 0;
    private int size = 0;

    public synchronized void put(String element) {
        System.out.println("put()");
        if (size == data.length) {
            try {
                System.out.println("wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("put");
        data[putIndex] = element;
        ++putIndex;
        if (putIndex == data.length) putIndex = 0;
        ++size;
        notify();
    }

    public synchronized String get() {
        System.out.println("get()");
        if (size == 0) {
            try {
                System.out.println("wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = data[getIndex];
        ++getIndex;
        if (getIndex == data.length) getIndex = 0;
        --size;
        notify();
        return result;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();



    }
}