package org.example.inventory.item.wearable;

import org.example.inventory.item.WearableItem;

import java.awt.*;

public class Poison extends WearableItem {
    public Poison() {
        super(2,2);
    }

    @Override
    public String getIcon() {
        return "\uD83E\uDDEA";
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
