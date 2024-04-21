package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.inventory.item.Item;

public class ItemBlock extends Block {

    private final Item prototype;

    public ItemBlock(Item item) {
        super(item.getIcon(), item.getColor());
        this.prototype = item;
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        context.getPlayer().getInventory().getItems().add(prototype.clone());
        return false;
    }

    @Override
    public ItemBlock clone() {
        return (ItemBlock) super.clone();
    }
}
