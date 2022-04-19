package com.ning.offer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 三个线程顺序打印输出：1 2 3 1 2 3
 *
 * @author <a href="guotongning@126.com">Nicholas</a>
 * @since 1.0.0
 * Created on 2021/5/11 22:38
 */
public class ThreadDemo {
    private static final Semaphore A = new Semaphore(1);
    private static final Semaphore B = new Semaphore(0);
    private static final Semaphore C = new Semaphore(0);

    public static void main(String[] args) {
//        new A().start();
//        new B().start();
//        new C().start();
        System.out.println("44121646".hashCode()%1000);
    }

    static class A extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    A.acquire();
                    System.out.println("1");
                    TimeUnit.SECONDS.sleep(1);
                    B.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class B extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    B.acquire();
                    System.out.println("2");
                    TimeUnit.SECONDS.sleep(1);
                    C.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class C extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    C.acquire();
                    System.out.println("3");
                    TimeUnit.SECONDS.sleep(1);
                    A.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
