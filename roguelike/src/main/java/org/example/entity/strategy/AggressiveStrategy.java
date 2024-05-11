package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.level.util.Position;
import org.example.scene.Tickable;

public class AggressiveStrategy extends MoveStrategy {
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
            if (projX > 0) md = MoveDirection.RIGHT;
            else md = MoveDirection.LEFT;
        } else {
            if (projY > 0) md = MoveDirection.UP;
            else md = MoveDirection.DOWN;
        }

        return getLevel().tryMove(gameContext, owner, md);
    }
}
