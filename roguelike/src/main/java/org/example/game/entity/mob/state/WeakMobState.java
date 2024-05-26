package org.example.game.entity.mob.state;

import org.example.game.GameContext;
import org.example.game.entity.mob.Mob;
import org.example.game.entity.strategy.CowardHealStrategy;
import org.example.game.entity.strategy.MoveStrategy;
import org.example.game.level.Level;

/**
 * Weak mob state corresponding to strategy of finding healing when hp is low
 */
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
