package org.example.scene;

import java.awt.Color;

public interface Drawable {

    String getIcon();
    Color getColor();

    default void draw(Console console) {
        console.drawString(getIcon(), getColor());
    }
}
