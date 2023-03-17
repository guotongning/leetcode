package com.ning.design_template.struct_template.bridge;

public class Circular extends Shape {
    public Circular(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("draw circular: ");
        color.fill();
    }
}
