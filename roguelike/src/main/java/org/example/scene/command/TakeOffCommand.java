package org.example.scene.command;

import static org.example.Event.TAKE_OFF_BOOTS;
import static org.example.Event.TAKE_OFF_HELMET;
import static org.example.Event.TAKE_OFF_LEGGINGS;
import static org.example.Event.TAKE_OFF_PLATE;
import static org.example.Event.TAKE_OFF_SWORD;

import org.example.GameContext;
import org.example.entity.Player;
import org.example.inventory.ActiveInventory;
import org.example.inventory.item.Item;
import org.example.inventory.item.WearableItem;
import org.example.inventory.item.wearable.Poison;
import org.example.level.util.Position;
import org.example.scene.GameScene;

import java.util.List;

public class TakeOffCommand extends Command {

    private WearableItem.WearableType type;

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
