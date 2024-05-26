package org.example.game.entity.strategy;

import org.example.game.level.util.Position;

/**
 * Strategy to reach player
 */
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
