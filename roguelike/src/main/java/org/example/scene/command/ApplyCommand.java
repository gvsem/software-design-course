package org.example.scene.command;

import org.example.GameContext;
import org.example.entity.Player;
import org.example.inventory.item.Item;
import org.example.inventory.item.wearable.Poison;
import org.example.level.util.Position;
import org.example.scene.GameScene;

import java.util.List;

public class ApplyCommand extends Command {

    public ApplyCommand() {}

    @Override
    public void run(GameScene scene, GameContext game) {
        final Player player = game.getPlayer();
        final List<Item> items = player.getInventory().getItems();
        final int itemToPutOnIdx = scene.statePanel.getFocusedInventoryItemIdx();

        if (itemToPutOnIdx >= items.size())
            return;

        if (items.get(itemToPutOnIdx) instanceof Poison) {
            Position playerPosition = game.getCurrentLevel().getPlayerPosition();
            int playerX = playerPosition.x();
            int playerY = playerPosition.y();
            for (int y = playerY - 3; y < playerX + 3; y++) {
                for (int x = playerX - 3; x < playerX + 3; x++) {
                    game.getCurrentLevel().tryConfuse(x, y);
                }
            }
            items.remove(itemToPutOnIdx);
            return;
        }

        final Item swapped = player.getActiveInventory().swap(items.get(itemToPutOnIdx));
        if (swapped == null)
            items.remove(itemToPutOnIdx);
        else
            items.set(itemToPutOnIdx, swapped);
    }

    @Override
    public String description() {
        return "Apply";
    }
}
