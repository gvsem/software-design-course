package org.example.scene.command;

import org.example.game.GameContext;
import org.example.scene.GameScene;

/**
 * Command is an incapsulated game action produced by I/O
 */
public abstract class Command {

    /**
     * Execute game action
     * @param scene current scene
     * @param game game context
     */
    public abstract void run(GameScene scene, GameContext game);

    /**
     * @return human readable description of the command
     */
    public abstract String description();

}
