package org.example.scene.util;

import java.awt.Color;

public interface Drawable {

    default boolean isEmojiIcon() { return false; }
    default String getIcon() {return "  ";}
    default Color getColor() {return Color.BLACK;}

    default void draw(Console console) {
        if (isEmojiIcon()) {
            console.drawEmoji(getIcon());
        } else {
            console.drawString(getIcon(), getColor());
        }
    }
}
