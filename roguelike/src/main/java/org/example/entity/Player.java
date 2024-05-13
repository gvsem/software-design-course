package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import org.example.inventory.ActiveInventory;
import org.example.inventory.Inventory;


@Getter
public class Player extends Entity {
    @Setter
    private int xp;
    private int level;
    private final Inventory inventory = new Inventory();
    private final ActiveInventory activeInventory = new ActiveInventory();
    
    
    public Player() {
        super(10);
        xp = 0;
        level = 1;
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }

    @Override
    public String getIcon() {
        return "\uD83D\uDE00";
    }

    @Override
    public int getStrength() {
        return super.getStrength() * level;
    }
    
    
    @Override
    public String getId() {
        return "player";
    }
    
    
    public void incLevel(int delta) {
        level += delta;
    }
}
