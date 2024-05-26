package org.example.game.entity.mob.factory;

import org.example.game.GameContext;
import org.example.game.entity.mob.Mob;
import org.example.game.entity.mob.ReplicableMob;
import org.example.game.entity.strategy.AggressiveStrategy;
import org.example.game.entity.strategy.ConfusedStrategy;
import org.example.game.entity.strategy.CowardStrategy;
import org.example.game.entity.strategy.NeutralStrategy;
import org.example.game.level.Level;

/**
 * Factory of halloween mobs
 */
public class HalloweenMobFactory extends MobFactory {

    public Mob createKiller(Level level, GameContext gameContext) {
        return new Mob.Builder(Mob.class, 10L, 2, level, gameContext).setMoveStrategy(new AggressiveStrategy()).setIcon("\uD83E\uDDB7").setId("killer").build();
    }

    public Mob createStander(Level level, GameContext gameContext) {
        return new Mob.Builder(Mob.class, 10L, 0, level, gameContext).setMoveStrategy(new NeutralStrategy()).setIcon("\uD83C\uDF83").setId("stander").build();
    }

    public Mob createCoward(Level level, GameContext gameContext) {
        return new Mob.Builder(Mob.class, 10L, 1, level, gameContext).setMoveStrategy(new CowardStrategy()).setIcon("\uD83D\uDE11").setId("coward").build();
    }

    @Override
    public Mob createReplicable(Level level, GameContext gameContext) {
        return new Mob.Builder(ReplicableMob.class, 2L, 1, level, gameContext).setMoveStrategy(new ConfusedStrategy()).setIcon("\uD83C\uDF44").build();
    }

}
