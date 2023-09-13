package com.ning.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RTZPlus implements InvocationHandler {

    private final Person target;

    public RTZPlus(Person target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("RTZ 准备开始吃东西");
        Object invoke = method.invoke(target, args);
        System.out.println("RTZ 吃完了");
        return invoke;
    }
}
