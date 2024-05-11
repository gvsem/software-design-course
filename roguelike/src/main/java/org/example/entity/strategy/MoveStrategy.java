package org.example.entity.strategy;

import org.example.scene.Tickable;

public abstract class MoveStrategy implements Tickable {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
