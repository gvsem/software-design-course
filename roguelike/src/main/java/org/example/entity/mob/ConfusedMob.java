package org.example.entity.mob;

import org.example.entity.strategy.ConfusedStrategy;

import lombok.Getter;

public class ConfusedMob extends Mob {

    @Getter
    private final Mob mob;
    @Getter
    private Long endTime;

    public ConfusedMob(Mob mob, Long endTime) {
        super();
        this.mob = mob;
        this.endTime = endTime;
        this.moveStrategy = new ConfusedStrategy();
        this.moveStrategy.setLevel(mob.getMoveStrategy().getLevel());
        this.moveStrategy.setOwner(this);
        this.moveStrategy.setGameContext(mob.getMoveStrategy().getGameContext());
    }

    @Override
    public boolean tick(Long time) {
        if (endTime > 0) {
            endTime -= 1;
            return moveStrategy.tick(time);
        }
        return mob.tick(time);
    }

    @Override
    public boolean isEmojiIcon() {
        return mob.isEmojiIcon();
    }

    @Override
    public String getIcon() {
        if (endTime > 0) {
            return "\uD83E\uDD2E";
        }
        return mob.getIcon();

    }
}
