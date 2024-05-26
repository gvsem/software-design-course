package org.example.game.entity.mob.factory;

import org.example.game.GameContext;
import org.example.game.entity.mob.Mob;
import org.example.game.level.Level;

/**
 * Base factory class to create mob of certain behavior types
 */
public abstract class MobFactory  {

    public abstract Mob createKiller(Level level, GameContext gameContext);

    public abstract Mob createStander(Level level, GameContext gameContext);

    public abstract Mob createCoward(Level level, GameContext gameContext);

    public abstract Mob createReplicable(Level level, GameContext gameContext);
}
