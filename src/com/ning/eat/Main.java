package com.ning.eat;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        //求字符串长度，处理逻辑：String类的length方法。
        Integer length = handle("success", String::length);
        System.out.println(length);
        //将字符串转成数字，处理逻辑：Integer类的parseInt方法。
        Integer result = handle("123", Integer::parseInt);
        System.out.println(result);
        //累加字符串中每个字母对应的ASCII码值
        Integer ascSum = handle("abc", Main::add);
        System.out.println(ascSum);
    }

    /**
     * 将一个字符串处理成一个数字的方法
     *
     * @param string   需要处理的字符串
     * @param function 处理这个字符串的具体逻辑
     * @return 处理结果
     */
    public static Integer handle(String string, Function<String, Integer> function) {
        //调用处理字符串的方法，将需要处理的字符串传入。
        return function.apply(string);
    }

    /**
     * 自定义的处理逻辑：累加字符串中每个字母对应的ASCII码值
     *
     * @param string 需要处理的字符串
     * @return ASCII码值之和
     */
    public static int add(String string) {
        int result = 0;
        for (char c : string.toCharArray()) {
            result += c;
        }
        return result;
    }

}
