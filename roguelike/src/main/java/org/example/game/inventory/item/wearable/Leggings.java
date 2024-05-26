package org.example.game.inventory.item.wearable;

import org.example.game.inventory.item.WearableItem;

import java.awt.Color;
import java.util.Random;

/**
 * A wearable part of gameplay equipment
 */
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

    @Override
    public WearableType getWearableType() {
        return WearableType.Leggings;
    }
}
