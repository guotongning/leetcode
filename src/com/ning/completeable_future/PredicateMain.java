package com.ning.completeable_future;

import java.util.function.Predicate;

public class PredicateMain {
    public static void main(String[] args) {
        System.out.println(check("success", p -> p.length() > 3));
        System.out.println(check(10, p -> p > 5));
        System.out.println(check(101, p -> p > 0, p -> p < 200, p -> p > 50, p -> p < 102));
    }


    @SafeVarargs
    private static <T> boolean check(T t, Predicate<T>... predicates) {
        int count = 0;
        boolean check = true;
        for (Predicate<T> predicate : predicates) {
            check = check && predicate.test(t);
            if (++count >= predicates.length) {
                return check;
            }
        }
        return false;
    }
}
