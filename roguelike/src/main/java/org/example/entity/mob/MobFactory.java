package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.strategy.AggressiveStrategy;
import org.example.entity.strategy.MoveStrategy;
import org.example.level.Level;
import org.example.level.util.Position;
import org.example.scene.Tickable;

import lombok.Getter;
import lombok.Setter;

public class MobFactory  {

    public static Mob createKiller(Position position, Level level, GameContext gameContext) {
        return new Mob.Builder(10L, level, gameContext).setMoveStrategy(new AggressiveStrategy()).setPosition(position).build();
    }
}
