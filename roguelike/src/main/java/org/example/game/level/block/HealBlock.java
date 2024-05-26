package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;
import org.example.scene.util.Console;

import java.awt.Color;

/**
 * Block that heals entities that move towards it
 */
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

    /**
     * @return true, if this block is used and does not heal any more
     */
    public boolean isTaken() {
        return taken;
    }
}
