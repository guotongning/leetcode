package com.ning.design_template.struct_template.combination;

import java.awt.*;

public interface Shape {
    int getX();

    int getY();

    void setXY(int x, int y);

    int getWidth();

    int getHeight();

    Color getColor();

    void move(int x, int y);

    boolean isInsideBounds(int x, int y);

    void select();

    void unSelect();

    boolean isSelected();

    void paint(Graphics graphics);

    void paint(Graphics graphics, int x, int y);
}
