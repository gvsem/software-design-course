package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;
import java.util.Random;


public class Boots extends WearableItem {
    public Boots() {
        super(new Random().nextInt(2) + 1, 0);
    }
    
    
    @Override
    public String getIcon() {
        return "db";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public WearableType getWearableType() {
        return WearableType.Boots;
    }
}
