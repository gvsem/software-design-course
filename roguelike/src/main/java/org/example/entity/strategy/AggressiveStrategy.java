package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.scene.Tickable;

public class AggressiveStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        // example of what to do
        return getLevel().tryMove(gameContext, owner, MoveDirection.UP);
    }
}
