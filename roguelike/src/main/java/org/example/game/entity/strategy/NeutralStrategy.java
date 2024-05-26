package org.example.game.entity.strategy;

/**
 * Trivial strategy to stand and do nothing
 */
public class NeutralStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
