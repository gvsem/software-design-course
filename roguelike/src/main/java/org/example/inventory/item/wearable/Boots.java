package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;

public class Boots extends WearableItem {
    public Boots() {
        super(1, 0);
    }
    
    
    @Override
    public String getIcon() {
        return "db";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
