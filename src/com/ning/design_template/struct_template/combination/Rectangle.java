package com.ning.design_template.struct_template.combination;

import java.awt.*;

public class Rectangle extends BaseShape {
    public int width;
    public int height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void paint(Graphics graphics) {
        paint(graphics, x, y);
    }

    @Override
    public void paint(Graphics graphics, int x, int y) {
        super.paint(graphics, x, y);
        graphics.drawRect(x, y, getWidth() - 1, getHeight() - 1);
    }
}
