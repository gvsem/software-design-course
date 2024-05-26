package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;

import java.awt.Color;

/**
 * Trivial implementation of non-existent block
 */
public class EmptyBlock extends Block {
    public EmptyBlock() {
        super("  ", Color.BLACK);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        return true;
    }
}
