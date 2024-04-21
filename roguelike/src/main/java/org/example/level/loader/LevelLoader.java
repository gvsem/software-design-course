package org.example.level.loader;

import org.example.level.Level;

import java.nio.file.Path;

public class LevelLoader {

    public static Level loadLevel(Path file) {
        return new Level(100, 100);
    }

}
