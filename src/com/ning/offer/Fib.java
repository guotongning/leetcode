package com.ning.offer;

public class Fib {
    public static void main(String[] args) {
//        int n = 100;
//        System.out.println(fib(n + 1));
        System.out.println("ohZfw1At9kn4j_KIYbDkK-XL-Fco".length());
    }

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
