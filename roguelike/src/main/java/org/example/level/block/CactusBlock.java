package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.inventory.item.Item;
import org.example.scene.Console;

import java.awt.Color;

public class CactusBlock extends Block {

    public CactusBlock() {
        super("\uD83C\uDF35", Color.RED);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        context.getPlayer().hit(1);
        return false;
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }

    @Override
    public CactusBlock clone() {
        return (CactusBlock) super.clone();
    }
}
