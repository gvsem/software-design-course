package org.example.entity.strategy;

public class NeutralStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
