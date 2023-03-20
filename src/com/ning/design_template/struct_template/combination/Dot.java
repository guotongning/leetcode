package com.ning.design_template.struct_template.combination;

import java.awt.*;

public class Dot extends BaseShape {
    private final int DOT_SIZE = 3;

    public Dot(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getWidth() {
        return DOT_SIZE;
    }

    @Override
    public int getHeight() {
        return DOT_SIZE;
    }

    @Override
    public void paint(Graphics graphics) {
        paint(graphics, x, y);
    }

    @Override
    public void paint(Graphics graphics, int x, int y) {
        super.paint(graphics, x, y);
        graphics.fillRect(x - 1, y - 1, getWidth(), getHeight());
    }
}
