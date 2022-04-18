package com.ning.expression;

public class Source {
    public String value;
    public Operator sourceType;
    public Operator connector;
    public Source next;

    public Source(String value, Operator sourceType, Operator connector, Source next) {
        this.value = value;
        this.sourceType = sourceType;
        this.connector = connector;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("类型=[%s] 值=[%s] 计算符=[%s]", sourceType.name(), value, connector != null ? connector.name() : "null");
    }
}
