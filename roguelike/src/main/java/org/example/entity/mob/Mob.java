package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.Entity;
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

    @Getter @Setter
    public MoveStrategy moveStrategy = new NeutralStrategy();

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
            object.setMoveStrategy(strategy);
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
            return object;
        }
    }

    @Override
    public boolean tick(Long time) {
        if (!isDead()) {
            return moveStrategy.tick(time);
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
