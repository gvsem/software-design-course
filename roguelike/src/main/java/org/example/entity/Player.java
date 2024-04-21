package org.example.entity;

import org.example.inventory.ActiveInventory;
import org.example.inventory.Inventory;

import lombok.Getter;

public class Player extends Entity {

    @Getter
    private final Inventory inventory = new Inventory();

    @Getter
    private final ActiveInventory activeInventory = new ActiveInventory();

    public Player() {
        super(10);
    }
}
