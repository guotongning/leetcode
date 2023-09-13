package com.ning.dynamic_proxy;

import java.lang.reflect.Proxy;

public class Main {

    private static final Person rtzPlus = (Person) Proxy.newProxyInstance(RTZ.class.getClassLoader(), RTZ.class.getInterfaces(), new RTZPlus(new RTZ()));

    public static void main(String[] args) {
        rtzPlus.eat("shit");
    }

}
