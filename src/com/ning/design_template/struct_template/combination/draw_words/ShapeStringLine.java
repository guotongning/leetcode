package com.ning.design_template.struct_template.combination.draw_words;


import com.ning.design_template.struct_template.combination.BaseShape;
import com.ning.design_template.struct_template.combination.Shape;

import java.awt.*;

public class ShapeStringLine extends BaseShape {
    /**
     * 直线的基本图形
     */
    private final Shape shape;
    /**
     * 直线的绘画方向，相对于初识点的方向
     */
    private final Direction direction;
    /**
     * 基本图形的数量
     */
    private final int steps;

    public ShapeStringLine(Shape shape, Direction direction, int steps) {
        super(shape.getX(), shape.getY(), shape.getColor());
        this.shape = shape;
        this.direction = direction;
        this.steps = steps;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public void setXY(int x, int y) {
        shape.setXY(x, y);
    }

    @Override
    public int getWidth() {
        switch (direction) {
            case LEFT:
            case RIGHT:
            case UPPER_LEFT:
            case UPPER_RIGHT:
            case LOWER_LEFT:
            case LOWER_RIGHT:
                return shape.getWidth() * steps;
            case UPPER:
            case LOWER:
                return shape.getWidth();
        }
        return 0;
    }

    @Override
    public int getHeight() {
        switch (direction) {
            case LEFT:
            case RIGHT:
                return shape.getHeight();
            case UPPER:
            case LOWER:
            case UPPER_LEFT:
            case UPPER_RIGHT:
            case LOWER_LEFT:
            case LOWER_RIGHT:
                return shape.getHeight() * steps;
        }
        return 0;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        int startX = x;
        int startY = y;
        int steps = this.steps;
        while (steps > 0) {
            shape.paint(graphics, startX, startY);
            steps--;
            startX = steppingX(startX);
            startY = steppingY(startY);
        }
    }

    public int endX() {
        int startX = x;
        int steps = this.steps;
        while (steps > 1) {
            steps--;
            startX = steppingX(startX);
        }
        return startX;
    }

    public int endY() {
        int startY = y;
        int steps = this.steps;
        while (steps > 1) {
            steps--;
            startY = steppingY(startY);
        }
        return startY;
    }

    private int steppingY(int y) {
        switch (direction) {
            case UPPER:
            case UPPER_LEFT:
            case UPPER_RIGHT:
                return y - shape.getHeight();
            case LOWER:
            case LOWER_LEFT:
            case LOWER_RIGHT:
                return y + shape.getHeight();
        }
        return y;
    }

    public int steppingX(int x) {
        switch (direction) {
            case LEFT:
            case UPPER_LEFT:
            case LOWER_LEFT:
                return x - shape.getWidth();
            case RIGHT:
            case UPPER_RIGHT:
            case LOWER_RIGHT:
                return x + shape.getWidth();
        }
        return x;
    }
}
