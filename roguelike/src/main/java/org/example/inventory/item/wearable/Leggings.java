package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;
import java.util.Random;


public class Leggings extends WearableItem {
    public Leggings() {
        super(new Random().nextInt(2) + 1, 0);
    }
    
    
    @Override
    public String getIcon() {
        return "/\\";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
