package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;

public class Plate extends WearableItem {
    public Plate() {
        super(4, 0);
    }
    
    
    @Override
    public String getIcon() {
        return "[]";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
