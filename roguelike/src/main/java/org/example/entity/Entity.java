package org.example.entity;


import org.example.scene.Drawable;

import lombok.Getter;


@Getter
public abstract class Entity implements Drawable {
    private final long maxHp;
    protected long hp;
    private int strength;
    private boolean isConfused = false;
    
    
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

    public void confuse() {
        isConfused = true;
    }
}
