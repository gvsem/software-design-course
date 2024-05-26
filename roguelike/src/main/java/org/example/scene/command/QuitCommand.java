package org.example.scene.command;

import org.example.game.GameContext;
import org.example.scene.GameScene;

/**
 * Command to quit game
 */
public class QuitCommand extends Command {

    public QuitCommand() {}

    @Override
    public void run(GameScene scene, GameContext game) {
        scene.quit();
    }

    @Override
    public String description() {
        return "Quit";
    }
}
