package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.mob.state.MobState;
import org.example.entity.mob.state.NormalMobState;
import org.example.entity.strategy.MoveStrategy;
import org.example.entity.strategy.NeutralStrategy;
import org.example.level.Level;
import org.example.level.util.Position;
import org.example.scene.Drawable;
import org.example.scene.Tickable;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Mob extends Entity implements Tickable {

    @Getter
    protected Class<? extends MoveStrategy> defaultMoveStrategy = NeutralStrategy.class;

    @Getter @Setter
    protected MobState mobState = null;

    @Getter @Setter
    protected String id = UUID.randomUUID().toString().substring(0, 5);

    @Setter
    protected String icon = "  ";

    protected Mob(Long initialHp, int strength) {super(initialHp, strength); }

    protected Mob() {
        super(0);
    }

    public static class Builder {
        private final Mob object;
        private final Level level;
        private final GameContext gameContext;
        private MoveStrategy strategy;

        public Builder(Class<? extends Mob> clazz, Long initialHp, int strength, Level level, GameContext gameContext) {
            try {
                var constructor = clazz.getDeclaredConstructor(Long.class, int.class);
                constructor.setAccessible(true);
                this.object = constructor.newInstance(initialHp, strength);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.level = level;
            this.gameContext = gameContext;
        }

        public Builder setMoveStrategy(MoveStrategy strategy) {
            strategy.setOwner(object);
            strategy.setLevel(level);
            strategy.setGameContext(gameContext);
            this.strategy = strategy;
            object.defaultMoveStrategy = strategy.getClass();
            return this;
        }

        public Builder setIcon(String icon) {
            object.icon = icon;
            return this;
        }

        public Builder setId(String id) {
            object.id = id;
            return this;
        }

        public Mob build() {
            if (strategy == null) {
                strategy = new NeutralStrategy();
                strategy.setOwner(object);
                strategy.setLevel(level);
                strategy.setGameContext(gameContext);
            }
            object.setMobState(new NormalMobState(strategy));
            return object;
        }
    }

    @Override
    public boolean tick(Long time) {
        if (!isDead()) {
            boolean result = mobState.tick(time);
            mobState = mobState.nextState();
            return result;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
