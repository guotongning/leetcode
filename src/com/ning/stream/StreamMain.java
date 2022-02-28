package com.ning.stream;

import com.sun.tools.javac.util.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 stream train
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class StreamMain {
    public static void main(String[] args) {
        // test create
//        testCreate();
        // test ergodic screen matching
//        testESM();
//        testMaxName();
//        testMaxAge();
//        testMap();
//        testReduce();
    }

    private static void testReduce() {
        System.out.println(createTestData().stream().map(Person::getAge).reduce(Integer::sum).orElse(0));
    }

    private static void testMap() {
        createTestData().stream().map(person -> person.getName().toUpperCase(Locale.ROOT)).collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void testMaxAge() {
        List<Person> people = createTestData();
        Person p = people.stream().max(Comparator.comparing(Person::getAge)).orElse(new Person());
        System.out.println("年纪最大的是：" + p.getName());
    }

    private static void testMaxName() {
        List<Person> people = createTestData();
        Person p = people.stream().max(Comparator.comparing(person -> person.getName().length())).orElse(new Person());
        System.out.println("名字最长的人是：" + p.getName());
    }

    private static void testESM() {
        List<Person> people = createTestData();
        System.out.printf("遍历筛选结果：%s%n", toStringStream(people.stream().filter(p -> p.getAge() > 25)));
        Optional<Person> first = people.stream().filter(p -> p.getAge() > 25).findFirst();
        System.out.printf("匹配到的第一个：%s%n", first.orElse(null));
        Optional<Person> any = people.stream().filter(p -> p.getAge() > 25).findAny();
        System.out.printf("匹配任意一个：%s%n", any.orElse(null));
        boolean match = people.stream().filter(p -> p.getAge() > 25).anyMatch(p -> p.getAge() == 23);
        System.out.printf("是否有人的年纪是23：%s%n", match);
    }

    private static void testCreate() {
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = StreamCreator.create(integers);
        Stream<Integer> stream3 = StreamCreator.create(collection);
        Stream<Integer> parallel1 = StreamCreator.createParallel(integers);
        Stream<Integer> parallel2 = StreamCreator.createParallel(collection);

        Stream<Integer> stream1 = StreamCreator.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> iterator = StreamCreator.iterator(1, x -> x + 2).limit(6);
        Stream<Double> generate = StreamCreator.generate(Math::random).limit(6);

        Assert.checkNonNull(stream1, "stream1 is null!!!");
        Assert.checkNonNull(stream2, "stream2 is null!!!");
        Assert.checkNonNull(parallel1, "parallel1 is null!!!");
        Assert.checkNonNull(stream3, "stream3 is null!!!");
        Assert.checkNonNull(parallel2, "parallel2 is null!!!");
        Assert.checkNonNull(iterator, "iterator is null!!!");
        Assert.checkNonNull(generate, "generate is null!!!");
        System.out.printf("stream1 ======> %s%n", toStringStream(stream1));
        System.out.printf("stream2 ======> %s%n", toStringStream(stream2));
        System.out.printf("parallel1 ======> %s%n", toStringStream(parallel1));
        System.out.printf("stream3 ======> %s%n", toStringStream(stream3));
        System.out.printf("parallel2 ======> %s%n", toStringStream(parallel2));
        System.out.printf("iterator ======> %s%n", toStringStream(iterator));
        System.out.printf("generate ======> %s%n", toStringStream(generate));
    }

    private static <T> String toStringStream(Stream<T> stream) {
        StringBuilder builder = new StringBuilder();
        stream.forEach(i -> builder.append(i).append(","));
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }

    private static List<Person> createTestData() {
        return Arrays.asList(
                new Person("Tom", 23, Gender.MALE, 1234.56, Area.NEW_YORK),
                new Person("Jack", 24, Gender.MALE, 1234.56, Area.WASHINGTON),
                new Person("Lily", 25, Gender.FEMALE, 1234.56, Area.WASHINGTON),
                new Person("Anni", 26, Gender.FEMALE, 1234.56, Area.NEW_YORK),
                new Person("Owen", 27, Gender.MALE, 1234.56, Area.NEW_YORK),
                new Person("Alisa", 28, Gender.FEMALE, 1234.56, Area.NEW_YORK)
        );
    }
}
