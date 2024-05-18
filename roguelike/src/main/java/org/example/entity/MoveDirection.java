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

}
