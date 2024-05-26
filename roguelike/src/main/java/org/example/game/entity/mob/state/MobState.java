package org.example.game.entity.mob.state;

import org.example.game.GameContext;
import org.example.game.entity.mob.Mob;
import org.example.game.entity.strategy.MoveStrategy;
import org.example.game.level.Level;
import org.example.scene.util.Tickable;

import lombok.Getter;

/**
 * Class to represent state – a wrapper of move strategy that can initiate mob state change
 */
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
