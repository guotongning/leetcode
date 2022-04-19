package com.ning.expression;

/**
 * 测试计算表达式解析
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        String expression = "2 * 11 - 5 * 2 / (1 + 1) * 2";
        String result = new Expression(expression).resolve();
        System.out.printf("%s = %s%n", expression, result);
    }
}
