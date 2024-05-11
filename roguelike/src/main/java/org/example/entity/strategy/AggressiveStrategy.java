package org.example.entity.strategy;

import org.example.scene.Tickable;

public class AggressiveStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
