package org.example.entity.strategy;

public class ConfusedStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
