package org.example.inventory;

import org.example.inventory.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<Item> getItems() {
        return items;
    }

    private final List<Item> items = new ArrayList<>();

}
