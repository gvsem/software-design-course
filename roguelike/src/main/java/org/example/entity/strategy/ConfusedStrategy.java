package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.level.util.Position;

import java.util.Random;

public class ConfusedStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        if (currentTick % ticksToPassOneBlock != 0) {
            currentTick += 1;
            return false;
        }
        currentTick = 0;

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
