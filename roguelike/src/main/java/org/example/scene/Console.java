package org.example.scene;

import java.awt.Color;

public interface Console {
    void drawEmoji(int row, int col, String emoji);
    void drawEmoji(String emoji);
    void drawString(int row, int col, String text, Color color);
    void drawString(String text, Color color);
    int width();
    int height();

    default int inventoryHeight() {return 5;}

}
