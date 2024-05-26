package org.example.entity.mob;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.entity.strategy.MoveStrategy;
import org.example.entity.strategy.NeutralStrategy;
import org.example.level.Level;
import org.example.level.util.Position;
import org.example.scene.Tickable;

import java.util.Random;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class ReplicableMob extends Mob implements Cloneable {

    public ReplicableMob(Long initialHp, int strength) {
        super(initialHp, strength);
    }

    protected double replicationProbability = 0.8;

    @Override
    public boolean tick(Long time) {
        boolean res = super.tick(time);
        if (time % 50 == 0) {
            if (new Random().nextDouble() < replicationProbability) {
                Position currPos = this.mobState.getStrategy().getLevel().getPosition().get(this.getId());
                if (currPos == null) {
                    return res;
                }
                Position newPos = currPos.add(MoveDirection.random());
                replicationProbability *= 0.5;
                ReplicableMob newMob = this.clone();
                this.mobState.getStrategy().getLevel().spawn(newMob, newPos);
            }
        }
        return res;
    }

    @Override
    protected ReplicableMob clone() {
        ReplicableMob mob = new ReplicableMob(this.getMaxHp(), this.getStrength());
        mob.replicationProbability = replicationProbability;
        mob.mobState = mobState.clone();
        mob.mobState.getStrategy().setOwner(mob);
        mob.setIcon(getIcon());
        return mob;
    }
}
