package org.example.entity;


import lombok.Getter;


@Getter
public abstract class Entity {
    private final long maxHp;
    protected long hp;
    private int strength;
    
    
    public Entity(long initialHp, int initialStrength) {
        this.maxHp = initialHp;
        this.hp = this.maxHp;
        this.strength = initialStrength;
    }
    
    
    public Entity(long initialHp) {
        this(initialHp, 1);
    }
    
    
    public abstract String getId();
    
    
    public void hit(int hp) {
        this.hp -= hp;
    }
    
    
    public void heal(int hp) {
        this.hp = Math.min(getMaxHp(), this.hp + hp);
    }
    
    
    public boolean isDead() {
        return getHp() <= 0;
    }
}
