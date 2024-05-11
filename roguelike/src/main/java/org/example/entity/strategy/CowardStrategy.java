package org.example.entity.strategy;

public class CowardStrategy extends MoveStrategy {
    @Override
    public boolean tick(Long time) {
        return false;
    }
}
