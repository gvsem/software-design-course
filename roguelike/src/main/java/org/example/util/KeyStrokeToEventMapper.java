package org.example.util;


import com.googlecode.lanterna.input.KeyStroke;
import org.example.Event;

import static org.example.Event.*;


public class KeyStrokeToEventMapper {
    public static Event map(KeyStroke stroke) {
        return switch (stroke.getKeyType()) {
            case ArrowUp -> GAME_UP;
            case ArrowDown -> GAME_DOWN;
            case ArrowLeft -> GAME_LEFT;
            case ArrowRight -> GAME_RIGHT;
            case Character -> switch (stroke.getCharacter()) {
                case 'A', 'a' -> INVENTORY_LEFT;
                case 'D', 'd' -> INVENTORY_RIGHT;
                case 'E', 'e' -> PUT_ON;
                case '1' -> TAKE_OFF_HELMET;
                case '2' -> TAKE_OFF_PLATE;
                case '3' -> TAKE_OFF_LEGGINGS;
                case '4' -> TAKE_OFF_BOOTS;
                case '5' -> TAKE_OFF_SWORD;
                default -> null;
            };
            case Enter -> TAKE;
            case Escape -> QUIT;
            default -> null;
        };
    }
}
