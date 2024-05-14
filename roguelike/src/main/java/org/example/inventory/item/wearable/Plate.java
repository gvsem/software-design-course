package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;
import java.util.Random;


public class Plate extends WearableItem {
    public Plate() {
        super(new Random().nextInt(3) + 2, new Random().nextInt(2));
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
