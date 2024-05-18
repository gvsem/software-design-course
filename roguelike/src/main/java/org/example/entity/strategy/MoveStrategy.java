package org.example.entity.strategy;

import org.example.GameContext;
import org.example.entity.mob.Mob;
import org.example.level.Level;
import org.example.scene.Tickable;

import lombok.Getter;
import lombok.Setter;

public abstract class MoveStrategy implements Tickable, Cloneable {

    @Getter @Setter
    protected Mob owner = null;

    @Getter @Setter
    protected Level level = null;

    @Getter @Setter
    protected GameContext gameContext = null;

    @Getter @Setter
    protected Integer ticksToPassOneBlock = 5;


    /**
     * Handles one game tick and returns true, if any game change has happened
     * @param time current game tick time
     * @return true, if game has changed
     */
    @Override
    public boolean tick(Long time) {
        return false;
    }

    @Override
    public MoveStrategy clone() {
        try {
            MoveStrategy clone = (MoveStrategy) super.clone();
            clone.ticksToPassOneBlock = ticksToPassOneBlock;
            clone.gameContext = gameContext;
            clone.level = level;
            clone.owner = owner;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
