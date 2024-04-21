package org.example.scene;

import java.awt.Color;

public interface Console {
    void drawEmoji(String emoji);
    void drawString(String text, Color color);
    int width();
    int height();
}
