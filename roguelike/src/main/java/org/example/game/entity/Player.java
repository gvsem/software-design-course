package org.example.game.entity;


import lombok.Getter;
import lombok.Setter;
import org.example.game.inventory.ActiveInventory;
import org.example.game.inventory.Inventory;

/**
 * Entity corresponding to the playing actor within the game
 */
@Getter
public class Player extends Entity {
    @Setter
    private int xp;
    private int level;
    private final Inventory inventory = new Inventory();
    private final ActiveInventory activeInventory = new ActiveInventory();
    
    
    public Player() {
        super(20);
        xp = 0;
        level = 1;
    }
    
    
    @Override
    public long getHp() {
        return Math.min(getMaxHp(), super.getHp() + activeInventory.getHpImprovement());
    }
    
    
    @Override
    public int getStrength() {
        return super.getStrength() * level + activeInventory.getStrengthImprovement();
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
    public String getId() {
        return "player";
    }

    /**
     * Increment player level by delta
     * @param delta positive value
     */
    public void incLevel(int delta) {
        level += delta;
    }
}
