package com.ning.design_template.struct_template.combination.draw_words;

import com.ning.design_template.struct_template.combination.*;
import com.ning.design_template.struct_template.combination.Rectangle;
import com.ning.design_template.struct_template.combination.Shape;

import java.awt.*;

public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();
        Shape shape1 = new Circle(10, 10, 10, Color.RED);
        Shape shape2 = new Rectangle(10, 10, 50, 50, Color.BLACK);
        Shape shape3 = new Dot(10, 10, Color.GREEN);
        ShapeStringLine stringLine = new ShapeStringLine(shape3, Direction.LOWER, 40);
        editor.loadShapes(Trajectory.builder().start(stringLine)
                .then(Direction.RIGHT, 50)
                .then(Direction.UPPER, 39)
                .build().toArray());
    }
}
