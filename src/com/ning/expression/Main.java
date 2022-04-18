package com.ning.expression;

/**
 * 测试计算表达式解析
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        String expression = "((3 - 0.3) - (  12 + 5) / (3.7 - 2) * (1.3 / 0.5) + 4) * 2";
        double result = new Expression(expression).resolve();
        System.out.printf("%s = %s%n", expression, result);
    }
}
