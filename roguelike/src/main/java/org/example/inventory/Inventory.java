package org.example.inventory;

import org.example.inventory.item.Item;
import org.example.scene.Console;
import org.example.scene.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<Item> getItems() {
        return items;
    }

    private final List<Item> items = new ArrayList<>();

}
