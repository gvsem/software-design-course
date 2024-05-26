package org.example.entity.mob.state;

import org.example.GameContext;
import org.example.entity.mob.Mob;
import org.example.entity.strategy.CowardHealStrategy;
import org.example.entity.strategy.MoveStrategy;
import org.example.level.Level;

public class WeakMobState extends MobState {

    public WeakMobState(Mob mob, Level level, GameContext gameContext) {
        super(CowardHealStrategy.class, mob, level, gameContext);
    }

    public WeakMobState(MoveStrategy strategy) {
        super(CowardHealStrategy.class, strategy.getOwner(), strategy.getLevel(), strategy.getGameContext());
    }

    @Override
    public boolean tick(Long time) {
        return strategy.tick(time);
    }

    @Override
    public MobState nextState() {
        if (strategy.getOwner().getHp() >= 0.4 * strategy.getOwner().getMaxHp()) {
            return new NormalMobState(strategy.getOwner(), strategy.getLevel(), strategy.getGameContext());
        } else {
            return this;
        }
    }

    @Override
    public MobState clone() {
        return new WeakMobState(strategy);
    }
}
