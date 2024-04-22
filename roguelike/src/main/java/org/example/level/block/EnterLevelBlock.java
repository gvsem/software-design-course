package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;

import java.awt.Color;

public class EnterLevelBlock extends Block {

    private final String levelId;

    public EnterLevelBlock(String levelId) {
        super("\uD83D\uDEAA", Color.BLACK);
        this.levelId = levelId;
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        context.enterLevel(levelId);
        return true;
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
