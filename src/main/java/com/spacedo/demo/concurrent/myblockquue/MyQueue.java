package com.spacedo.demo.concurrent.myblockquue;

import lombok.SneakyThrows;

public class MyQueue {
    private String[] data = new String[10];
    private int getIndex = 0;
    private int putIndex = 0;
    private int size = 0;
    private Object putLock = new Object();
    private Object getLock = new Object();

    @SneakyThrows
    public void put(String element) {
        System.out.println("put("+element+")#start");
        synchronized (MyQueue.class){
            System.out.println("put("+element+")#putlock.start");
            if (size==data.length) {
                System.out.println("put("+element+")#wait");
                synchronized (getLock){
                    System.out.println("put("+element+")#notify.get");
                    getLock.notify();
                }
                System.out.println("put("+element+")#wait");
                putLock.wait();
                System.out.println("put("+element+")#wait.after.put");
                put(element);
            } else {
                System.out.println("put("+element+")#put");
                size++;
                data[size]=element;
                synchronized (getLock){
                    System.out.println("put("+element+")#notify.get");
                    getLock.notify();
                }
            }
            System.out.println("put("+element+")#putlock.end");
        }
        System.out.println("put("+element+")#end");
    }

    @SneakyThrows
    public synchronized String get() {
        System.out.println("get()#start");
        synchronized (getLock){
            if (size==0) {
                System.out.println("get()#wait");
                synchronized (putLock){
                    System.out.println("get()#notify.put");
                    notify();
                }
                System.out.println("get()#wait");
                getLock.wait();
                System.out.println("get()#wait.after.get");
                get();
            } else {
                System.out.println("get()#get");
                size--;
                System.out.println("get()#getlock.end");
                synchronized (putLock){
                    System.out.println("get()#notify.put");
                    putLock.notify();
                }
                return data[size];
            }
        }
        return "s";
    }

}