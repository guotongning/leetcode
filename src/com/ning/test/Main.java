package com.ning.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Person.createProxy(new Student()).hello("kawasaki");
        Person.createProxy(new Teacher(), new PersonProcessorImpl()).hello("nicholas");
    }
}


interface Person {
    void hello(String name);

    static Person createProxy(Person p) {
        return (Person) Proxy.newProxyInstance(p.getClass().getClassLoader(), p.getClass().getInterfaces(), new InvocationHandlerImpl(p));
    }

    static Person createProxy(Person p, Processor processor) {
        return (Person) Proxy.newProxyInstance(p.getClass().getClassLoader(), p.getClass().getInterfaces(), new InvocationHandlerImpl(p, processor));
    }
}

class Teacher implements Person {
    @Override
    public void hello(String name) {
        System.out.printf("我是老师,hello %s%n", name);
    }
}

class Student implements Person {

    @Override
    public void hello(String name) {
        System.out.printf("我是学生,hello %s%n", name);
    }
}

class InvocationHandlerImpl implements InvocationHandler {

    private final Object target;
    private final Processor processor;

    public InvocationHandlerImpl(Object target) {
        this.target = target;
        processor = new Processor() {
        };
    }

    public InvocationHandlerImpl(Object target, Processor processor) {
        this.target = target;
        this.processor = processor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        processor.preProcessor();
        Object returnValue = method.invoke(target, args);
        processor.afterProcessor();
        return returnValue;
    }
}

interface Processor {
    default void preProcessor() {
        System.out.println("我是默认前置处理");
    }

    default void afterProcessor() {
        System.out.println("我是默认后置处理");
    }
}

class PersonProcessorImpl implements Processor {
    @Override
    public void preProcessor() {
        System.out.println("我是自定义前置处理");
    }

    @Override
    public void afterProcessor() {
        System.out.println("我是自定义后置处理");
    }
}
