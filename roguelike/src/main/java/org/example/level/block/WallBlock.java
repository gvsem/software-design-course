package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;

import java.awt.Color;

public class WallBlock extends Block {
    public WallBlock() {
        super("##", Color.BLUE);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        return false;
    }
}
