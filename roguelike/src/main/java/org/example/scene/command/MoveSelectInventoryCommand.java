package org.example.scene.command;

import org.example.game.GameContext;
import org.example.scene.GameScene;

/**
 * Command to navigate within player inventory
 */
public class MoveSelectInventoryCommand extends Command {

    public enum MoveSelectType {
        Left,
        Right
    }
    private final MoveSelectType type;

    public MoveSelectInventoryCommand(MoveSelectType type) {this.type = type;}

    @Override
    public void run(GameScene scene, GameContext game) {
        switch (type) {
            case Left -> scene.statePanel.moveInventorySelectorLeft();
            case Right -> scene.statePanel.moveInventorySelectorRight();
        }
    }

    @Override
    public String description() {
        return "Move inventory selector: " + type;
    }
}
