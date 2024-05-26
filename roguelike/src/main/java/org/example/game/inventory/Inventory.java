package org.example.game.inventory;

import org.example.game.inventory.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory is a list of unequipped items belonging to the player
 */
public class Inventory {

    /**
     * @return current list of player's items
     */
    public List<Item> getItems() {
        return items;
    }

    private final List<Item> items = new ArrayList<>();

}
