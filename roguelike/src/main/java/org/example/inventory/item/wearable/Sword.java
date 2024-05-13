package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;
import org.example.scene.Console;

import java.awt.Color;

public class Sword extends WearableItem {
    public Sword() {
        super(0, 3);
    }
    
    
    @Override
    public String getIcon() {
        return "\uD83D\uDDE1";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }
}
