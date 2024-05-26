package org.example.scene.command;

import org.example.GameContext;
import org.example.entity.Player;
import org.example.inventory.ActiveInventory;
import org.example.inventory.item.Item;
import org.example.inventory.item.WearableItem;
import org.example.scene.GameScene;

import java.util.List;

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
            case Left -> scene.statePanel.decFocusedInventoryTile();
            case Right -> scene.statePanel.incFocusedInventoryTile();
        }
    }

    @Override
    public String description() {
        return "Move inventory selector: " + type;
    }
}
