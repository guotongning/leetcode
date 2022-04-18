package com.ning.expression;

enum Operator {
    ADD('+'), SUB('-'), MUL('*'), DIV('/'), NUMBER('#'), SPOT('.'), BRACKETS_L('('), BRACKETS_R(')'), EXPRESSION('$');

    private final Character symbol;

    Operator(Character symbol) {
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }

    public static Operator char2Operator(char c) {
        switch (c) {
            case '+':
                return ADD;
            case '-':
                return SUB;
            case '*':
                return MUL;
            case '/':
                return DIV;
            case '.':
                return SPOT;
            case '(':
                return BRACKETS_L;
            case ')':
                return BRACKETS_R;
            default:
                return NUMBER;
        }
    }
}
