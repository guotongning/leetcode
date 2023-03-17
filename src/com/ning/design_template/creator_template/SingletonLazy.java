package com.ning.design_template.creator_template;

public class SingletonLazy {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println(SingletonLazy.getInstance());
        }
    }
    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
