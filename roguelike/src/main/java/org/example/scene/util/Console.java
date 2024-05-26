package org.example.scene.util;

import java.awt.Color;

/**
 * Interface to represent terminal UI instance independently of its implementation
 */
public interface Console {
    void drawEmoji(int row, int col, String emoji);
    void drawEmoji(String emoji);
    void drawEmoji(int row, int col, String emoji, Color background);
    void drawString(int row, int col, String text, Color color);
    void drawString(String text, Color color);
    void drawString(String text, Color color, Color background);
    void drawString(int row, int col, String text, Color color, Color background);
    void nextLine();
    int width();
    int height();

    default int inventoryHeight() {return 5;}

}
