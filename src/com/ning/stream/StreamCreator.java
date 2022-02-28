package com.ning.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * stream creator
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class StreamCreator {
    /**
     * create by collection
     */
    public static <T> Stream<T> create(Collection<T> collection) {
        return collection.stream();
    }

    /**
     * create by arr
     */
    public static <T> Stream<T> create(T[] arr) {
        return Arrays.stream(arr);
    }

    /**
     * create parallel by collection
     */
    public static <T> Stream<T> createParallel(Collection<T> collection) {
        return collection.parallelStream();
    }

    /**
     * create parallel by arr
     */
    public static <T> Stream<T> createParallel(T[] arr) {
        return Arrays.stream(arr).parallel();
    }

    /**
     * create by values
     */
    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return Stream.of(values);
    }

    /**
     * create by iterator
     */
    public static <T> Stream<T> iterator(final T seed, final UnaryOperator<T> f) {
        return Stream.iterate(seed, f);
    }

    /**
     * create by generate
     */
    public static <T> Stream<T> generate(Supplier<T> s) {
        return Stream.generate(s);
    }


}
