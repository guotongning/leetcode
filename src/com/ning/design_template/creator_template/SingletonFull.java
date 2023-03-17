package com.ning.design_template.creator_template;

public class SingletonFull {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println(SingletonFull.getInstance());
        }
    }
    private static final SingletonFull instance = new SingletonFull();

    public static SingletonFull getInstance() {
        return instance;
    }
}
