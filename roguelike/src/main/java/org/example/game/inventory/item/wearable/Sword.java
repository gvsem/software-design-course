package org.example.game.inventory.item.wearable;

import org.example.game.inventory.item.WearableItem;

import java.awt.Color;
import java.util.Random;

/**
 * An instrument increasing strength of a player
 */
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
