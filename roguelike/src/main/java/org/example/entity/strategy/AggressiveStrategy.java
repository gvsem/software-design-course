package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.level.util.Position;
import org.example.scene.Tickable;

public class AggressiveStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        if (time % ticksToPassOneBlock != 0) {
            return false;
        }

        Position playerPosition = level.getPlayerPosition();
        return getLevel().tryMove(gameContext, owner, directionTowards(playerPosition));
    }
}
