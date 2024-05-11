package org.example.entity.mob;

import org.example.entity.Entity;
import org.example.entity.strategy.MoveStrategy;
import org.example.entity.strategy.StandStrategy;
import org.example.level.util.Position;
import org.example.scene.Tickable;

import lombok.Getter;
import lombok.Setter;

public class Mob extends Entity implements Tickable {

    @Getter @Setter
    private MoveStrategy moveStrategy = new StandStrategy();

    @Getter @Setter
    protected Position position = new Position(-1, -1);

    protected Mob(Long initialHp) {super(initialHp); }

    protected Mob() {
        super(0);
    }

    public static class Builder {
        private final Mob object;

        public Builder(Long initialHp) {
            object = new Mob(initialHp);
        }

        public Builder setMoveStrategy(MoveStrategy strategy) {
            object.setMoveStrategy(strategy);
            return this;
        }

        public Builder setPosition(Position position) {
            object.setPosition(position);
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
}
