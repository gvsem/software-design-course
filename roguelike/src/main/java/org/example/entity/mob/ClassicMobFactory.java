package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.strategy.AggressiveStrategy;
import org.example.entity.strategy.ConfusedStrategy;
import org.example.entity.strategy.CowardStrategy;
import org.example.entity.strategy.NeutralStrategy;
import org.example.level.Level;

public class ClassicMobFactory extends MobFactory {

    public Mob createKiller(Level level, GameContext gameContext) {
        return new Mob.Builder(Mob.class, 10L, 2, level, gameContext).setMoveStrategy(new AggressiveStrategy()).setIcon("\uD83E\uDD80").setId("killer").build();
    }

    public Mob createStander(Level level, GameContext gameContext) {
        return new Mob.Builder(Mob.class, 10L, 0, level, gameContext).setMoveStrategy(new NeutralStrategy()).setIcon("\uD83D\uDC70").setId("stander").build();
    }

    public Mob createCoward(Level level, GameContext gameContext) {
        return new Mob.Builder(Mob.class, 10L, 1, level, gameContext).setMoveStrategy(new CowardStrategy()).setIcon("\uD83D\uDC2D").setId("coward").build();
    }

    @Override
    public Mob createReplicable(Level level, GameContext gameContext) {
        return new Mob.Builder(ReplicableMob.class, 2L, 1, level, gameContext).setMoveStrategy(new ConfusedStrategy()).setIcon("\uD83C\uDF44").build();
    }
}
