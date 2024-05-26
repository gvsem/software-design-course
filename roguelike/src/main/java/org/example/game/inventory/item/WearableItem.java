package org.example.game.inventory.item;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents an object that can be equipped
 */
@RequiredArgsConstructor
@Getter
public abstract class WearableItem extends Item {
    private final int hpImprovement;
    private final int strengthImprovement;

    public enum WearableType {
        Helmet,
        Plate,
        Leggings,
        Boots,
        Sword,
        NotWearable
    }

    /**
     * Get wearable item type
     * @return type
     */
    public abstract WearableType getWearableType();

}
