package org.example.entity;

public abstract class Entity {
    private final long maxHp;

    protected long hp;

    public Entity(long initialHp) {
        this.maxHp = initialHp;
        this.hp = this.maxHp;
    }
}
