package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;

import java.awt.Color;

public class EmptyBlock extends Block {
    public EmptyBlock() {
        super("  ", Color.BLACK);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        return true;
    }
}
