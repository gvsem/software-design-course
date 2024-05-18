package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;

import java.awt.Color;

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
