package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;
import org.example.scene.Console;

import java.awt.Color;
import java.util.Random;


public class Sword extends WearableItem {
    public Sword() {
        super(0, new Random().nextInt(3) + 1);
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

    @Override
    public WearableType getWearableType() {
        return WearableType.Sword;
    }
}
