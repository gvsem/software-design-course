package org.example.scene;

import java.awt.Color;

public interface Console {
    void drawEmoji(String emoji);
    void drawString(String text, Color color);
    void drawString(String text, Color color, Color background);
    void nextLine();
    int width();
    int height();

    default int inventoryHeight() {return 4;}

}
