package org.example.game.inventory.item;

import org.example.scene.util.Drawable;

/**
 * Item is something that can be stored in inventory
 */
public abstract class Item implements Drawable, Cloneable {

    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
