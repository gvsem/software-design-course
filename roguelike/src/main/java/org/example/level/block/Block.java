package org.example.level.block;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.scene.Drawable;

import java.awt.Color;

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
