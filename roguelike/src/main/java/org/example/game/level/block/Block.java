package org.example.game.level.block;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;
import org.example.scene.util.Drawable;

import java.awt.Color;

/**
 * Block is an atomic part of level, on which entities can be placed
 */
public abstract class Block implements Drawable, Cloneable {

    public String icon;
    public final Color color;

    protected Block(String icon, Color color) {
        this.icon = icon;
        this.color = color;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Callback to handle entity's visit towards block
     *
     * @param entity    which has initiated movement towards block
     * @param direction in which this movement occurred
     * @param context   current game context
     * @return true,    if entity can proceed to this block and if the scene should be rerendered
     */
    public abstract boolean onVisit(Entity entity, MoveDirection direction, GameContext context);

    @Override
    public Block clone()  {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
