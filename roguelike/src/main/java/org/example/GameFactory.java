package org.example;

import org.example.level.LevelFactory;
import org.example.level.LevelGenerator;
import org.example.level.LevelLoader;

import java.nio.file.Path;

public class GameFactory {

    public static GameContext createBasicGame() {
        return new GameContext.Builder()
                .registerLevel("main", LevelFactory::generateFirstLevel)
                .registerLevel("sublocation", (context) -> LevelLoader.loadLevel(Path.of("./src/main/resources/lvl.json"), context))
                .setInitialLevel("sublocation")
                .build();
    }

}
