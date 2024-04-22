package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;

public class Helmet extends WearableItem {
    @Override
    public String getIcon() {
        return "^^";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
