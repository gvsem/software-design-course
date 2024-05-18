package org.example.level.util;

import org.example.entity.MoveDirection;

public record Position(int x, int y) implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Position diff(Position other) {
        return new Position(this.x - other.x, this.y - other.y);
    }

    public Position add(MoveDirection direction) {
        return switch (direction) {
            case UP -> new Position(this.x, this.y - 1);
            case DOWN -> new Position(this.x, this.y + 1);
            case LEFT -> new Position(this.x - 1, this.y);
            case RIGHT -> new Position(this.x + 1, this.y);
        };
    }

}
