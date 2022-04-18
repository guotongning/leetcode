package com.ning.expression;

/**
 * 测试计算表达式解析
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        String expression = "(1.1 + 2) * 3！ - (2 + (3 - 1.5) * 2) / 2";
        double result = new Expression(expression).resolve();
        System.out.printf("%s = %s%n", expression, result);
    }
}
