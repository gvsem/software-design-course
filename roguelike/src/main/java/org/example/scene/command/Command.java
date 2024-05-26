package org.example.scene.command;

import org.example.GameContext;
import org.example.scene.GameScene;

public abstract class Command {

    public abstract void run(GameScene scene, GameContext game);

    public abstract String description();

}
