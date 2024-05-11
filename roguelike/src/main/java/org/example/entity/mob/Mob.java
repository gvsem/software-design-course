package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.strategy.MoveStrategy;
import org.example.entity.strategy.NeutralStrategy;
import org.example.level.Level;
import org.example.level.util.Position;
import org.example.scene.Drawable;
import org.example.scene.Tickable;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Mob extends Entity implements Drawable, Tickable {

    @Getter @Setter
    private MoveStrategy moveStrategy = new NeutralStrategy();

    @Getter
    protected String id = UUID.randomUUID().toString().substring(0, 5);

    private String icon = "  ";

    protected Mob(Long initialHp) {super(initialHp); }

    protected Mob() {
        super(0);
    }

    public static class Builder {
        private final Mob object;
        private final Level level;

        private final GameContext gameContext;

        public Builder(Long initialHp, Level level, GameContext gameContext) {
            this.object = new Mob(initialHp);
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
        return moveStrategy.tick(time);
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
