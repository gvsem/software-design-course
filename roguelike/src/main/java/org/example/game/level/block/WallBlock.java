package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;

import java.awt.Color;

/**
 * Block that represents unwalkable wall
 */
public class WallBlock extends Block {
    public WallBlock() {
        super("##", Color.BLUE);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        return false;
    }
}
