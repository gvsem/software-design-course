package org.example.entity;


import org.example.level.util.Position;

import lombok.Getter;
import lombok.Setter;


@Getter
public abstract class Entity {
    private final long maxHp;

    protected long hp;

    public Entity(long initialHp) {
        this.maxHp = initialHp;
        this.hp = this.maxHp;
    }

    public abstract String getId();
}
