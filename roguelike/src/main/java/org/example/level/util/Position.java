package org.example.level.util;

public record Position(int x, int y) implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Position diff(Position other) {
        return new Position(this.x - other.x, this.y - other.y);
    }
}
