package org.example.game;

import org.example.game.level.util.LevelFactory;
import org.example.game.level.util.LevelLoader;

import java.nio.file.Path;

/**
 * Set of boilerplate implementations for the game
 */
public class GameFactory {

    /**
     * Returns basic game context used in demo
     * @return new game instance
     */
    public static GameContext createBasicGame() {
        return new GameContext.Builder()
                .registerLevel("main", LevelFactory::generateFirstLevel)
                .registerLevel("sublocation", (context) -> LevelLoader.loadLevel(Path.of("./src/main/resources/lvl.json"), context))
                .setInitialLevel("sublocation")
                .build();
    }

}
