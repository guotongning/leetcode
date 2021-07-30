package com.ning.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class ThreadTest {

    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();
    private static int count = 0;

    public static void main(String[] args) {
        new A().start();
        new B().start();
        new C().start();
    }

    static class A extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0) {
                        A.await();
                    }
                    System.out.println("A");
                    count++;
                    B.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1) {
                        B.await();
                    }
                    System.out.println("B");
                    count++;
                    C.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class C extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2) {
                        C.await();
                    }
                    System.out.println("C");
                    count++;
                    A.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
