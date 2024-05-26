package org.example.game.level.util;

import org.example.game.entity.MoveDirection;

/**
 * Utility class to represent position on the map
 * @param x
 * @param y
 */
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
