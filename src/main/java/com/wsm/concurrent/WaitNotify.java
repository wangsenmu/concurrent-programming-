package com.wsm.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangsm on 2018/6/4.
 */
public class WaitNotify {


    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Wait(), "wait thread");
        A.start();
        TimeUnit.SECONDS.sleep(2);
        Thread B = new Thread(new Notify(), "notify thread");
        B.start();
    }

    static class Wait implements Runnable {
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true");
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println(Thread.currentThread() + " flag is false");
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            synchronized (lock) {
                flag = false;
                lock.notifyAll();
                try {
                    TimeUnit.SECONDS.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
