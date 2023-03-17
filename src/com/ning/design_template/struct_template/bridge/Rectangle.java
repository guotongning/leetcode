package com.ning.design_template.struct_template.bridge;

public class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("draw rectangle: ");
        color.fill();
    }
}
