package com.ning.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 解析计算表达式
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Expression {

    private String expression;

    public Expression(String expression) {
        this.expression = expression;
    }

    public double resolve() {
        //去除空格
        expression = removeSpace(expression);
        //判断表达式正确性
        if (illegalExpression(expression)) {
            throw new RuntimeException(String.format("非法的表达式！ %s", expression));
        }
        return handleLinkedSource(sourcesToLinkedSource(expression2SourceList(expression)));
    }


    private List<Source> expression2SourceList(String expression) {
        char[] chars = expression.toCharArray();
        List<Source> list = new ArrayList<>();
        int index = 0;
        while (index < chars.length) {
            char c = chars[index];
            Operator operator = Operator.char2Operator(c);
            if (Operator.BRACKETS_L.equals(operator)) {
                index = readExpression(chars, index, list);
            }
            if (Operator.NUMBER.equals(operator) || Operator.SPOT.equals(operator)) {
                index = readNumber(chars, index, list);
            }
        }
        return list;
    }

    private Source sourcesToLinkedSource(List<Source> sources) {
        int index = 0;
        Source head = sources.get(index++);
        Source temp = head;
        while (index < sources.size()) {
            Source source = sources.get(index++);
            if (source != null) {
                temp.next = source;
                temp = temp.next;
            }
        }
        return head;
    }

    private double handleLinkedSource(Source head) {
        if (head == null) {
            return 0.0;
        }
        Source source = head;
        while (source != null) {
            if (Operator.EXPRESSION.equals(source.sourceType)) {
                Source expressionHead = sourcesToLinkedSource(expression2SourceList(source.value));
                double result = handleLinkedSource(expressionHead);
                source.value = result + "";
                source.sourceType = Operator.NUMBER;
            }
            source = source.next;
        }
        return handleLinkedSourceSubAndAdd(handleLinkedSourceMulAndDiv(head));
    }

    private double handleLinkedSourceSubAndAdd(Source newHead) {
        Source source = newHead;
        while (source != null) {
            Source next = source.next;
            if (next == null) {
                return Double.parseDouble(source.value);
            }
            double result = operation(Double.parseDouble(source.value), Double.parseDouble(next.value), source.connector);
            if (next.next == null) {
                return result;
            }
            source.value = result + "";
            source.connector = next.connector;
            source.next = next.next;
            source = source.next;
        }
        return 0;
    }

    private Source handleLinkedSourceMulAndDiv(Source head) {
        Source source = head;
        while (source != null) {
            if (Operator.ADD.equals(source.connector) || Operator.SUB.equals(source.connector)) {
                source = source.next;
                continue;
            }
            Source next = source.next;
            if (next == null) {
                break;
            }
            double param1 = Double.parseDouble(source.value);
            double param2 = Double.parseDouble(next.value);
            double result = operation(param1, param2, source.connector);
            source.value = result + "";
            source.connector = next.connector;
            source.sourceType = Operator.NUMBER;
            source.next = next.next;
            source = source.next;
        }
        return head;
    }

    private double operation(double param1, double param2, Operator connector) {
        switch (connector) {
            case ADD:
                return param1 + param2;
            case SUB:
                return param1 - param2;
            case MUL:
                return param1 * param2;
            case DIV:
                return param1 / param2;
            default:
                throw new IllegalArgumentException(String.format("非法的运算符！%s", connector.getSymbol()));
        }
    }

    private int readExpression(char[] chars, int index, List<Source> list) {
        StringBuilder expressionBuilder = new StringBuilder();
        Stack<Character> brackets = new Stack<>();
        boolean read = true;
        while (read) {
            char c = chars[index++];
            expressionBuilder.append(c);
            Operator operator = Operator.char2Operator(c);
            if (Operator.BRACKETS_L.equals(operator)) {
                brackets.push(c);
            }
            if (Operator.BRACKETS_R.equals(operator)) {
                brackets.pop();
                if (brackets.size() == 0) {
                    //expression read done
                    Operator connector = Operator.char2Operator(chars[index++]);
                    expressionBuilder.delete(0, 1);
                    expressionBuilder.delete(expressionBuilder.length() - 1, expressionBuilder.length());
                    list.add(new Source(expressionBuilder.toString(), Operator.EXPRESSION, connector, null));
                    read = false;
                }
            }
        }
        return index;
    }

    private int readNumber(char[] chars, int index, List<Source> list) {
        StringBuilder numberBuilder = new StringBuilder();
        while (index < chars.length) {
            char c = chars[index];
            Operator operator = Operator.char2Operator(c);
            if (Operator.NUMBER.equals(operator) || Operator.SPOT.equals(operator)) {
                numberBuilder.append(c);
                index++;
            } else {
                list.add(new Source(numberBuilder.toString(), Operator.NUMBER, Operator.char2Operator(chars[index++]), null));
                numberBuilder.delete(0, numberBuilder.length());
                break;
            }
        }
        if (numberBuilder.length() > 0) {
            list.add(new Source(numberBuilder.toString(), Operator.NUMBER, null, null));
        }
        return index;
    }

    private boolean illegalExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        Operator preOperator = null;
        for (char c : chars) {
            if (Operator.BRACKETS_L.getSymbol() == c) {
                stack.push(c);
            } else if (Operator.BRACKETS_R.getSymbol() == c) {
                if (stack.size() == 0) {
                    return true;
                }
                stack.pop();
            }
            Operator operator = Operator.char2Operator(c);
            if (operator.equals(preOperator)
                    && !Operator.BRACKETS_L.equals(operator)
                    && !Operator.BRACKETS_R.equals(operator)
                    && !Operator.NUMBER.equals(operator)) {
                return true;
            }
            preOperator = operator;
        }
        return stack.size() != 0;
    }

    private String removeSpace(String expression) {
        return expression.replaceAll(" ", "");
    }

}
