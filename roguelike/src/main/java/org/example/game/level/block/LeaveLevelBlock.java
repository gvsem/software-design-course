package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;

import java.awt.Color;

/**
 * Block that teleports player down the level stack
 */
public class LeaveLevelBlock extends Block {

    public LeaveLevelBlock() {
        super("\uD83D\uDEAA", Color.BLACK);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        if (entity.getId().equals("player")) {
            context.leaveLevel();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }
}
