package com.ning.design_template.struct_template.bridge;

public class Main {
    public static void main(String[] args) {
        Shape red = new Rectangle(new Red());
        red.draw();
        Shape green = new Rectangle(new Green());
        green.draw();
        Shape cirRed = new Circular(new Green());
        cirRed.draw();
    }
}
