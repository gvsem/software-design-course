package org.example.game.entity.strategy;

import org.example.game.level.util.Position;

/**
 * Strategy to stay away from the player
 */
public class CowardStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        if (time % ticksToPassOneBlock != 0) {
            return false;
        }

        Position playerPosition = level.getPlayerPosition();
        if (getLevel().tryMove(gameContext, owner, directionTowards(playerPosition).inverse())) {
            return true;
        }

        return tryMoveIfOriginalFailed(directionTowards(playerPosition).inverse());

    }
}
