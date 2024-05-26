package org.example.entity;

import java.util.Random;

public enum MoveDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static MoveDirection random() {
        int k = new Random().nextInt(4);
        if (k == 0) {
            return UP;
        } else if (k == 1) {
            return DOWN;
        } else if (k == 2) {
            return LEFT;
        } else {
            return RIGHT;
        }
    }

    public MoveDirection inverse() {
        return switch(this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

    public MoveDirection next() {
        return switch(this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            case RIGHT -> DOWN;
        };
    }

    public MoveDirection prev() {
        return switch(this) {
            case UP -> LEFT;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            case RIGHT -> UP;
        };
    }


}
