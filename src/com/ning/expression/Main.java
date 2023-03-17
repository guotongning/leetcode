package com.ning.expression;

/**
 * 测试计算表达式解析
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        String exp = "(99654760 - 69849285)*0.02/30";
        String result = Expressions.builder()
                .exp(exp)
                .resolve();
        System.out.println(result);
    }
}
