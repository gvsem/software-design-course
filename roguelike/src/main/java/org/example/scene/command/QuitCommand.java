package org.example.scene.command;

import org.example.GameContext;
import org.example.entity.MoveDirection;
import org.example.scene.GameScene;

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
