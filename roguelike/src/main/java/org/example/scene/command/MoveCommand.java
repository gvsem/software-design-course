package org.example.scene.command;

import org.example.GameContext;
import org.example.entity.MoveDirection;
import org.example.scene.GameScene;

public class MoveCommand extends Command {

    private final MoveDirection direction;

    public MoveCommand(MoveDirection direction) {
        this.direction = direction;
    }

    @Override
    public void run(GameScene scene, GameContext game) {
        game.getCurrentLevel().tryMove(game, game.getPlayer(), direction);
    }

    @Override
    public String description() {
        return "Move: " + direction;
    }

}
