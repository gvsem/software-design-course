package org.example.entity.strategy;

import org.example.entity.mob.Mob;
import org.example.level.Level;
import org.example.scene.Tickable;

import lombok.Getter;
import lombok.Setter;

public abstract class MoveStrategy implements Tickable {

    @Getter @Setter
    protected Mob owner = null;

    @Getter @Setter
    protected Level level = null;

    @Override
    public boolean tick(Long time) {
        return false;
    }
}
