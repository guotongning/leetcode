package com.ning.test;

public class Main {
    private static final String LUA = "13:4,14:1,37:4,15:1,43:1,41:4,62:1,44:4,61:1,22:4,32:4,21:1,42:1,23:1,51:4,36:1,34:1,11:4,45:1,64:4,33:4,35:4,50:1,53:4,12:1,52:4,31:1,63:1,46:1,65:1,54:1";

    public static void main(String[] args) {
        String str = "111%s1234";
        System.out.println(str);
        System.out.println(String.format(str,""));
    }
}
