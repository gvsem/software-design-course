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

public abstract class MobFactory  {

    public abstract Mob createKiller(Level level, GameContext gameContext);

    public abstract Mob createStander(Level level, GameContext gameContext);

    public abstract Mob createCoward(Level level, GameContext gameContext);

    public abstract Mob createReplicable(Level level, GameContext gameContext);
}
