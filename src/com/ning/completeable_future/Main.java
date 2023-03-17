package com.ning.completeable_future;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {

    public static final ExecutorService EXECUTOR = new ThreadPoolExecutor(16, 64, 50,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(500), new NamesThreadFactory("test"), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        test7();
    }

    public static void test1() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            doSomething("do something", 2);
            return "done";
        }, EXECUTOR);
        String result = future.get();
        printResult("finish result = " + result);
    }

    public static void test2() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> doSomething("do something", 3), EXECUTOR);
        Void result = future.get();
        printResult(result);
    }

    public static void test3() throws Exception {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    doSomething("supplyAsync", 3);
                    return "done";
                }, EXECUTOR)
                .thenApply(result -> {
                    doSomething("thenApply", 3);
                    return result + "-v1";
                })
                .thenApplyAsync(result -> {
                    doSomething("thenApplyAsync", 2);
                    return result + "-v2";
                }, EXECUTOR);
        String result = future.get();
        printResult(result);
    }

    public static void test4() throws Exception {
        String result = CompletableFuture
                .supplyAsync(() -> {
                    doSomething("task1", 2);
                    return "task1";
                }, EXECUTOR)
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                            doSomething("task2", 2);
                            return "task2";
                        }, EXECUTOR),
                        (task1Result, task2Result) -> String.format(Thread.currentThread().getName() + "%s AND %s", task1Result, task2Result)
                        , EXECUTOR
                ).get();
        System.out.println(result);
    }

    public static void test5() throws Exception {
        String res = CompletableFuture
                .supplyAsync(() -> {
                    doSomething("呜呜呜", 3);
                    return "Hello";
                }, EXECUTOR)
                .thenCompose(result ->
                        CompletableFuture.supplyAsync(
                                () -> {
                                    doSomething("嘟嘟嘟", 3);
                                    return result + " World";
                                }, EXECUTOR)
                ).get();
        System.out.println(res);
    }

    public static void test6() {
        ArrayList<String> list = new ArrayList<>();
        CompletableFuture.supplyAsync(() -> {
            doSomething("任务1", 2);
            return "hello";
        }, EXECUTOR).thenAcceptAsync(result -> {
            doSomething("任务2", 2);
            System.out.println(result + " world");
            list.add("1");
        }, EXECUTOR);
        System.out.println("main");
        System.out.println(list.get(0));
    }

    public static void test7() {
        System.out.println("嘟嘟嘟");
        CompletableFuture<Void> allOf = CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> {
            doSomething("咕咕咕", 2);
            return "1";
        }, EXECUTOR), CompletableFuture.supplyAsync(() -> {
            doSomething("呜呜呜", 2);
            return "2";
        }, EXECUTOR));
        allOf.join();
        System.out.println("嘟嘟嘟");
    }

    private static void printResult(Object result) {
        System.out.println("finish result = " + result);
    }

    private static void doSomething(final String s, int seconds) {
        IntStream.range(0, seconds).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                if (i == seconds - 1) {
                    System.out.printf("worker:\t%s task:\t%s state:\t%s \r\n", Thread.currentThread().getName(), s, "done");
                } else {
                    System.out.printf("worker:\t%s task:\t%s state:\t%s \r\n", Thread.currentThread().getName(), s, "doing");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
