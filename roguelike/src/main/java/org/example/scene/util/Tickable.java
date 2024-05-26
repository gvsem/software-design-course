package org.example.scene.util;

import java.awt.Color;

/**
 * Interface to represent object reacting to game timer events
 */
public interface Tickable {
    /**
     * Notify object about new game tick
     * @param time current monotonically increasing counter
     * @return true, if object did react somehow to this tick
     */
    boolean tick(Long time);
}
