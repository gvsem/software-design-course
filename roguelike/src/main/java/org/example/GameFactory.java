package org.example;

import org.example.level.generator.LevelGenerator;
import org.example.level.loader.LevelLoader;

import java.nio.file.Path;

public class GameFactory {

    public static GameContext createBasicGame() {
        return new GameContext.Builder()
                .registerLevel("main", LevelGenerator.generateBasicLevel())
                .registerLevel("sublocation", LevelLoader.loadLevel(Path.of("./src/main/resources/lvl.json")))
                .setInitialLevel("main")
                .build();
    }

}
