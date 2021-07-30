package com.ning.thread;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 生产者 消费者
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class ThreadTest3 {

    private static final Deque<String> QUEUE = new ConcurrentLinkedDeque<>();

    //恢复消费阈值
    private static final int RESTORE_CONSUME_THRESHOLD = 20;

    //恢复生产阈值
    private static final int RESTORE_PRODUCE_THRESHOLD = 70;

    //队列最大长度
    private static final int MAX_LEN = 100;

    //用于调配生产者生产顺序
    private static final Semaphore SA = new Semaphore(1);
    private static final Semaphore SB = new Semaphore(0);
    private static final Semaphore SC = new Semaphore(0);

    public static void main(String[] args) {
        new PA("鸡蛋", 2).start();
        new PB("豆浆", 2).start();
        new PC("油条", 2).start();
        new CA("Nicholas", 10).start();
    }

    static class PA extends Thread {
        private final long pps;

        public PA(String name, long pps) {
            super(name);
            this.pps = pps;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    SA.acquire();
                    producer(this.getName(), pps);
                    SB.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    static class PB extends Thread {
        private final long pps;

        public PB(String name, long pps) {
            super(name);
            this.pps = pps;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    SB.acquire();
                    producer(this.getName(), pps);
                    SC.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class PC extends Thread {
        private final long pps;

        public PC(String name, long pps) {
            super(name);
            this.pps = pps;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    SC.acquire();
                    producer(this.getName(), pps);
                    SA.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void producer(String name, long pps) throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000 / pps / 3);
        boolean offerSuccess = QUEUE.offerFirst(name);
        int size = QUEUE.size();
        System.out.println("生产成功：" + name + ",队列长度：" + size);
        if (offerSuccess && size == MAX_LEN) {
            System.out.println("队列长度达到最大！停止生产！");
            while (QUEUE.size() != RESTORE_PRODUCE_THRESHOLD) {
                System.out.println("生产恢复检测：" + QUEUE.size() + ",恢复生产阈值：" + RESTORE_PRODUCE_THRESHOLD);
                //每秒检测是否达到恢复生产条件
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    static class CA extends Thread {
        //消费速度 consume pre seconds,最大 1000
        private final long cps;

        CA(String name, long cps) {
            super(name);
            if (cps > 1000) {
                cps = 1000;
            }
            this.cps = cps;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    TimeUnit.MILLISECONDS.sleep(1000 / cps);
                    consume();
                }
            } catch (NoSuchElementException e) {
                System.out.println("消费暂停！");
                while (QUEUE.size() < RESTORE_CONSUME_THRESHOLD) {
                    //每秒检测是否达到恢复消费条件
                    System.out.println("恢复消费检测：" + QUEUE.size() + ",恢复消费阈值：" + RESTORE_CONSUME_THRESHOLD);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() {
            String last = QUEUE.pollLast();
            if (last == null) {
                throw new NoSuchElementException("队列消费空了！");
            }
            int size = QUEUE.size();
            System.out.println(this.getName() + " 消费：" + last + ",剩余：" + size);
        }
    }

}
