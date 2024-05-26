package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;

import java.awt.Color;

/**
 * Block that bites entity moving towards it
 */
public class CactusBlock extends Block {

    public CactusBlock() {
        super("\uD83C\uDF35", Color.RED);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        entity.hit(1);
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
