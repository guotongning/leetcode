package com.ning.completeable_future;


import java.util.function.Function;

public class FunctionMain {
    public static void main(String[] args) {

        Function<Integer, Integer> f1 = i -> i + 1;

        Function<Integer, Integer> f2 = i -> i * 2;

        System.out.println(f1.apply(4));

        System.out.println(f1.compose(f2).apply(4));

        System.out.println(f2.compose(f1).apply(4));

        System.out.println(f1.andThen(f2).apply(4));

        System.out.println(f2.andThen(f1).apply(4));
    }
}
