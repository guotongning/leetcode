package com.ning.design_template.struct_template.combination.draw_words;

import com.ning.design_template.struct_template.combination.Shape;

import java.util.ArrayList;
import java.util.List;

public class Trajectory {

    private final List<ShapeStringLine> lines;


    private Trajectory(List<ShapeStringLine> lines) {
        this.lines = lines;
    }

    public ShapeStringLine[] toArray() {
        return lines.toArray(new ShapeStringLine[0]);
    }

    public static TrajectoryBuilder builder() {
        return new TrajectoryBuilder();
    }

    public static class TrajectoryBuilder {
        private final List<ShapeStringLine> lines = new ArrayList<>();

        public TrajectoryBuilder start(ShapeStringLine line) {
            lines.add(line);
            return this;
        }

        public TrajectoryBuilder then(Direction direction, int steps) {
            ShapeStringLine last = lines.get(lines.size() - 1);
            Shape shape = last.getShape();
            shape.setXY(last.endX(), last.endY());
            lines.add(new ShapeStringLine(shape, direction, steps + 1));
            return this;
        }

        public Trajectory build() {
            return new Trajectory(lines);
        }
    }
}
