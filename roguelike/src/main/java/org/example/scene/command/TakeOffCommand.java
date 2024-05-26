package org.example.scene.command;

import org.example.game.GameContext;
import org.example.game.entity.Player;
import org.example.game.inventory.ActiveInventory;
import org.example.game.inventory.item.Item;
import org.example.game.inventory.item.WearableItem;
import org.example.scene.GameScene;

import java.util.List;

/**
 * Command to take off equipped inventory item
 */
public class TakeOffCommand extends Command {

    private final WearableItem.WearableType type;

    public TakeOffCommand(WearableItem.WearableType type) {this.type = type;}

    @Override
    public void run(GameScene scene, GameContext game) {
        final Player player = game.getPlayer();
        final ActiveInventory activeInventory = player.getActiveInventory();
        final List<Item> inventoryItems = player.getInventory().getItems();

        WearableItem item = activeInventory.get(type);
        if (item == null) {
            return;
        }

        inventoryItems.add(item);
        activeInventory.remove(type);
    }

    @Override
    public String description() {
        return "Take off: " + type;
    }
}
