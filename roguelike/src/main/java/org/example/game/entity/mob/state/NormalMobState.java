package org.example.game.entity.mob.state;

import org.example.game.GameContext;
import org.example.game.entity.mob.Mob;
import org.example.game.entity.strategy.MoveStrategy;
import org.example.game.level.Level;

/**
 * Trivial mob state corresponding to its default move strategy
 */
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
