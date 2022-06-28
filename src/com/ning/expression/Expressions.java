package com.ning.expression;

/**
 * 构建算数表达式
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 0.0.1
 */
public class Expressions {

    private String result;

    public static Expressions builder() {
        return new Expressions();
    }

    public Expressions exp(String expression) {
        this.result = new Expression(expression).resolve();
        return this;
    }

    public Expressions then(String thenExpression) {
        if (this.result == null || this.result.length() == 0) {
            throw new RuntimeException("前置结果不存在！");
        }
        this.result = new Expression(this.result + thenExpression).resolve();
        return this;
    }

    public String resolve() {
        return result;
    }
}
