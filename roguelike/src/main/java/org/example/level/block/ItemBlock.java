package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.entity.Player;
import org.example.inventory.item.Item;
import org.example.scene.Console;

import java.awt.Color;

public class ItemBlock extends Block {

    private final Item prototype;

    private boolean taken = false;

    public ItemBlock(Item item) {
        super(item.getIcon(), item.getColor());
        this.prototype = item;
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        if (!taken) {
            if (entity instanceof Player) {
                context.getPlayer().getInventory().getItems().add(prototype.clone());
            }
            this.icon = "  ";
            taken = true;
        }
        return true;
    }


    @Override
    public void draw(Console console) {
        if (!taken) {
            prototype.draw(console);
        } else {
            console.drawString("  ", Color.WHITE);
        }
    }

    @Override
    public ItemBlock clone() {
        return (ItemBlock) super.clone();
    }
}
