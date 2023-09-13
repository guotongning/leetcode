package com.ning.dynamic_proxy;

public class RTZ implements Person {

    @Override
    public void eat(String food) {
        if (food == null || food.length() == 0) {
            throw new RuntimeException("不能吃空气");
        }
        System.out.printf("RTZ正在吃%s...\r\n", food);
    }
}
