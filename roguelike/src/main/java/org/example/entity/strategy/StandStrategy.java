package org.example.entity.strategy;

public class StandStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
