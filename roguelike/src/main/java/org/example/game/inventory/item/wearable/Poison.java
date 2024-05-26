package org.example.game.inventory.item.wearable;

import org.example.game.inventory.item.WearableItem;

import java.awt.*;

/**
 * An item application of which causes confusion
 */
public class Poison extends WearableItem {
    public Poison() {
        super(0, 0);
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

    @Override
    public WearableType getWearableType() {
        return WearableType.NotWearable;
    }
}
