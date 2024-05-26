package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.scene.Console;

import java.awt.Color;

public class HealBlock extends Block {

    private boolean taken = false;
    public HealBlock() {
        super("\uD83E\uDDE1", Color.RED);
    }

    @Override
    public boolean onVisit(Entity entity, MoveDirection direction, GameContext context) {
        if (!taken) {
            entity.heal(1);
            icon = "  ";
            taken = true;
        }
        return true;
    }

    @Override
    public void draw(Console console) {
        if (!taken) {
            super.draw(console);
        } else {
            console.drawString("  ", Color.WHITE);
        }
    }

    @Override
    public boolean isEmojiIcon() {
        return true;
    }

    @Override
    public HealBlock clone() {
        return (HealBlock) super.clone();
    }

    public boolean isTaken() {
        return taken;
    }
}
