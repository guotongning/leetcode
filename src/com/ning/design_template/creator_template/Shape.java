package com.ning.design_template.creator_template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Shape implements Cloneable {
    private String type;

    public abstract void draw();

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class ShapeCache {
    private static final Map<String, Shape> cache = new HashMap<>();

    static {
        cache.put("rectangle", new Rectangle());
        cache.put("square", new Square());
        cache.put("circle", new Circle());
    }

    public static void main(String[] args) {
        Shape rectangle = getShape("rectangle");
        rectangle.draw();

        Shape square = getShape("square");
        square.draw();

        Shape circle1 = getShape("circle");
        circle1.draw();

        Shape circle2 = getShape("circle");
        circle2.setType("my circle");
        System.out.println(circle1.getType());
        System.out.println(circle2.getType());
    }

    public static Shape getShape(String shapeId) {
        Shape cachedShape = cache.get(shapeId);
        return (Shape) cachedShape.clone();
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        this.setType("Rectangle");
    }

    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square extends Shape {
    public Square() {
        this.setType("Square");
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle extends Shape {

    public Circle() {
        this.setType("Circle");
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
