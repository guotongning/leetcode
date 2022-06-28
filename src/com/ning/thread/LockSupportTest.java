package com.ning.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 测试 LockSupport.park() 方法
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class LockSupportTest {

    public static class A extends Thread {

        @Override
        public void run() {
            System.out.println("Thread A running");
            LockSupport.parkNanos(1);
            System.out.println("Thread A terminated");
        }
    }


    public static void main(String[] args) {
        A t1 = new A();
        t1.start();
        System.out.println("Thread A started,但是在内部进行了 park");
        LockSupport.unpark(t1);
        System.out.println("LockSupport 进行了un park");
    }
}
