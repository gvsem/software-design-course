package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;

import java.awt.Color;

/**
 * Block that teleports player to the selected level
 */
public class EnterLevelBlock extends Block {

    private final String levelId;

    public EnterLevelBlock(String levelId) {
        super("\uD83D\uDEAA", Color.BLACK);
        this.levelId = levelId;
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        if (entity.getId().equals("player")) {
            context.enterLevel(levelId);
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }

    @Override
    public EnterLevelBlock clone() {
        return (EnterLevelBlock) super.clone();
    }
}
