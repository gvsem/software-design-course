package org.example.game.inventory.item.wearable;

import org.example.game.inventory.item.WearableItem;

import java.awt.Color;
import java.util.Random;

/**
 * A wearable part of gameplay equipment
 */
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

    @Override
    public WearableType getWearableType() {
        return WearableType.Plate;
    }

}
