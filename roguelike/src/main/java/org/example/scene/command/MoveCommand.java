package org.example.scene.command;

import org.example.game.GameContext;
import org.example.game.entity.MoveDirection;
import org.example.scene.GameScene;

/**
 * Command to move player on the level
 */
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
