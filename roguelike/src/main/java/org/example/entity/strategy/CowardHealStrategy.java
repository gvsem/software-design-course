package org.example.entity.strategy;

import org.example.entity.MoveDirection;
import org.example.level.block.HealBlock;
import org.example.level.util.Position;

import java.util.Map;
import java.util.Optional;

public class CowardHealStrategy extends CowardStrategy {
    @Override
    public boolean tick(Long time) {

        if (time % (1.2 * ticksToPassOneBlock) != 0) {
            return false;
        }

        double minDistance = 100000;
        Optional<Position> pos = Optional.empty();

        Position mobPosition = level.getPosition().get(owner.getId());

        for (int i = 0; i < level.getMap().length; ++i) {
            for (int j = 0; j < level.getMap()[i].length; ++j) {
                if (level.getMap()[i][j] instanceof HealBlock && !((HealBlock) level.getMap()[i][j]).isTaken()) {
                    double distance = Math.pow(Math.abs(j - mobPosition.x()), 2) + Math.pow(Math.abs(i - mobPosition.y()), 2);
                    if (distance < minDistance) {
                        minDistance = distance;
                        pos = Optional.of(new Position(j, i));
                    }
                }
            }
        }

        if (pos.isPresent()) {
            if (time % (1.2 * ticksToPassOneBlock) != 0) {
                return false;
            }
            if (directionTowards(pos.get()).equals(directionTowards(level.getPlayerPosition()))) {
                return tryMoveIfOriginalFailed(directionTowards(pos.get()));
            } else {
                return getLevel().tryMove(gameContext, owner, directionTowards(pos.get())) ||
                        tryMoveIfOriginalFailed(directionTowards(pos.get()));
            }
        } else {
            return super.tick(time);
        }

    }
}
