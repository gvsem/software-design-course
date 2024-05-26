package org.example.entity.mob.state;

import org.example.GameContext;
import org.example.entity.mob.Mob;
import org.example.entity.strategy.CowardHealStrategy;
import org.example.entity.strategy.MoveStrategy;
import org.example.entity.strategy.NeutralStrategy;
import org.example.level.Level;

public class NormalMobState extends MobState {

    public NormalMobState(Mob mob, Level level, GameContext gameContext) {
        super(mob.getDefaultMoveStrategy(), mob, level, gameContext);
    }

    public NormalMobState(MoveStrategy strategy) {
        super(strategy.getClass(), strategy.getOwner(), strategy.getLevel(), strategy.getGameContext());
    }

    @Override
    public boolean tick(Long time) {
        return strategy.tick(time);
    }

    @Override
    public MobState nextState() {
        if (strategy.getOwner().getHp() < 0.4 * strategy.getOwner().getMaxHp()) {
            return new WeakMobState(strategy);
        } else {
            return this;
        }
    }

    @Override
    public MobState clone() {
        return new NormalMobState(strategy);
    }
}
