package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.strategy.AggressiveStrategy;
import org.example.entity.strategy.CowardStrategy;
import org.example.entity.strategy.MoveStrategy;
import org.example.entity.strategy.NeutralStrategy;
import org.example.level.Level;
import org.example.level.util.Position;
import org.example.scene.Tickable;

import lombok.Getter;
import lombok.Setter;

public class MobFactory  {

    public static Mob createKiller(Level level, GameContext gameContext) {
        return new Mob.Builder(10L, level, gameContext).setMoveStrategy(new AggressiveStrategy()).setIcon("\uD83E\uDD80").setId("killer").build();
    }

    public static Mob createStander(Level level, GameContext gameContext) {
        return new Mob.Builder(10L, level, gameContext).setMoveStrategy(new NeutralStrategy()).setIcon("\uD83D\uDC70").setId("stander").build();
    }

    public static Mob createCoward(Level level, GameContext gameContext) {
        return new Mob.Builder(10L, level, gameContext).setMoveStrategy(new CowardStrategy()).setIcon("\uD83D\uDC2D").setId("coward").build();
    }
}
