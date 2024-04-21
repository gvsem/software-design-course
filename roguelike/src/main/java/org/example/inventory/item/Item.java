package org.example.inventory.item;

import org.example.scene.Drawable;

import java.awt.Color;

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
