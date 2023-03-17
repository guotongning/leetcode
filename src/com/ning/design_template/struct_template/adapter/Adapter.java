package com.ning.design_template.struct_template.adapter;

public class Adapter implements Target {

    public static void main(String[] args) {
        Target target = new Adapter(new Thing());
        target.request();
    }

    private final Thing thing;

    public Adapter(Thing thing) {
        this.thing = thing;
    }

    @Override
    public void request() {
        thing.doSomeThing();
    }
}
