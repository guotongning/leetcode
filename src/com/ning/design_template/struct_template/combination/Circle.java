package com.ning.design_template.struct_template.combination;

import java.awt.*;

public class Circle extends BaseShape {
    public int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getWidth() {
        return radius * 2;
    }

    @Override
    public int getHeight() {
        return radius * 2;
    }

    public void paint(Graphics graphics) {
        paint(graphics, x, y);
    }

    @Override
    public void paint(Graphics graphics, int x, int y) {
        super.paint(graphics, x, y);
        graphics.drawOval(x, y, getWidth() - 1, getHeight() - 1);
    }
}
