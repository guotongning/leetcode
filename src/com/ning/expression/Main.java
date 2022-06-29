package com.ning.expression;

/**
 * 测试计算表达式解析
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
//        expression();
        String resolve = Expressions.builder()
                .exp("(1 + 1) * 2 / 3")
                .then("*3")
                .then("*1.13")
                .resolve();
        System.out.println(resolve);
    }

    private static void expression() {
        String price = "15";
        String sellCount = "16";
        String base = "109 + 8 + 10 + 5 + 23.6";
        String resultSell = new Expression(price + "*" + sellCount).resolve();
        String resultBase = new Expression(base).resolve();
        System.out.printf("净收入 = 售价[%s] * 个数[%s] = %s\n", price, sellCount, resultSell);
        System.out.printf("成本 = %s = %s\n", base, resultBase);
        String resultProfit = new Expression(resultSell + "-" + resultBase).resolve();
        System.out.println("盈利 = " + resultProfit);
    }

}
