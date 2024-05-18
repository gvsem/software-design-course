package org.example.entity.mob;

import org.example.entity.strategy.ConfusedStrategy;
import org.example.entity.strategy.MoveStrategy;

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

    @Override
    public boolean isConfused() {
        return mob.isConfused();
    }

    @Override
    public int getStrength() {
        return mob.getStrength();
    }

    @Override
    public long getHp() {
        return mob.getHp();
    }

    @Override
    public long getMaxHp() {
        return mob.getMaxHp();
    }

    @Override
    public void hit(int hp) {
        mob.hit(hp);
    }

    @Override
    public void heal(int hp) {
        mob.heal(hp);
    }

    @Override
    public boolean isDead() {
        return mob.isDead();
    }

    @Override
    public void confuse() {
        mob.confuse();
    }

    @Override
    public void setMoveStrategy(MoveStrategy moveStrategy) {
        mob.setMoveStrategy(moveStrategy);
    }

    @Override
    public String getId() {
        return mob.getId();
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return mob.getMoveStrategy();
    }

    @Override
    public void setId(String id) {
        mob.setId(id);
    }
}
