package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.Color;

public class Sword extends WearableItem {
    @Override
    public String getIcon() {
        return "\uD83D\uDDE1";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
