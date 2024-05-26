package org.example.entity.mob.state;

import org.example.GameContext;
import org.example.entity.mob.Mob;
import org.example.entity.strategy.MoveStrategy;
import org.example.level.Level;
import org.example.scene.Tickable;

import java.lang.reflect.InvocationTargetException;

import lombok.Getter;

public abstract class MobState implements Tickable, Cloneable {

    @Getter
    protected final MoveStrategy strategy;

    protected MobState(Class<? extends MoveStrategy> clazz, Mob mob, Level level, GameContext gameContext) {
        try {
            this.strategy = clazz.getConstructor().newInstance();
            this.strategy.setOwner(mob);
            this.strategy.setLevel(level);
            this.strategy.setGameContext(gameContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract boolean tick(Long time);
    public abstract MobState nextState();

    @Override
    public MobState clone() {
        try {
            return (MobState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
