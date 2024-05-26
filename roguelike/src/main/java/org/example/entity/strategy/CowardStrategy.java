package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.level.util.Position;

import java.lang.Math;

import java.util.Vector;

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
