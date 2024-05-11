package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.level.util.Position;

import java.lang.Math;

import java.util.Vector;

public class CowardStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        if (currentTick % ticksToPassOneBlock != 0) {
            currentTick += 1;
            return false;
        }
        currentTick = 0;

        Position mobPosition = owner.getPosition();
        Position playerPosition = owner.getPosition();

        Position p = playerPosition.diff(mobPosition);

        int projX = p.x();
        int projY = p.y();
        MoveDirection md;

        if (Math.abs(projX) > Math.abs(projY)) {
            if (projX > 0) md = MoveDirection.LEFT;
            else md = MoveDirection.RIGHT;
        } else {
            if (projY > 0) md = MoveDirection.DOWN;
            else md = MoveDirection.UP;
        }

        return getLevel().tryMove(gameContext, owner, md);
    }
}
