package org.example.game.entity.strategy;

import org.example.game.entity.MoveDirection;

import java.util.Random;

/**
 * Strategy to stray randomly with uniform distribution probability
 */
public class ConfusedStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        if (time % ticksToPassOneBlock != 0) {
            return false;
        }

        Random randomGenerator = new Random();
        double d = randomGenerator.nextDouble();

        MoveDirection md;

        if (d < 0.25) md = MoveDirection.UP;
        else if (d < 0.5) md = MoveDirection.RIGHT;
        else if (d < 0.75) md = MoveDirection.DOWN;
        else md = MoveDirection.LEFT;

        return getLevel().tryMove(gameContext, owner, md);
    }
}
