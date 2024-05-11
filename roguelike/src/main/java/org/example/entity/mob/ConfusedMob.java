package org.example.entity.mob;

import org.example.entity.strategy.ConfusedStrategy;

import lombok.Getter;

public class ConfusedMob extends Mob {

    @Getter
    private final Mob mob;
    @Getter
    private final Long endTime;
    private final ConfusedStrategy moveStrategy = new ConfusedStrategy();

    public ConfusedMob(Mob mob, Long endTime) {
        super();
        this.mob = mob;
        this.endTime = endTime;
    }

    @Override
    public boolean tick(Long time) {
        if (time < endTime) {
            return moveStrategy.tick(time);
        } else {
            return mob.tick(time);
        }
    }
}
