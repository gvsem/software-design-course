package org.example.scene.command;

import org.example.game.GameContext;
import org.example.game.entity.Player;
import org.example.game.entity.mob.ConfusedMob;
import org.example.game.entity.mob.Mob;
import org.example.game.inventory.item.Item;
import org.example.game.inventory.item.wearable.Poison;
import org.example.game.level.Level;
import org.example.game.level.util.Position;
import org.example.scene.GameScene;

import java.util.List;

/**
 * Command to do something with selected inventory item
 */
public class ApplyCommand extends Command {

    public ApplyCommand() {}

    @Override
    public void run(GameScene scene, GameContext game) {
        final Player player = game.getPlayer();
        final List<Item> items = player.getInventory().getItems();
        final int itemToPutOnIdx = scene.statePanel.getFocusedInventoryItemIndex();

        if (itemToPutOnIdx >= items.size())
            return;

        if (items.get(itemToPutOnIdx) instanceof Poison) {
            Position playerPosition = game.getCurrentLevel().getPlayerPosition();
            int playerX = playerPosition.x();
            int playerY = playerPosition.y();
            for (int y = playerY - 3; y < playerX + 3; y++) {
                for (int x = playerX - 3; x < playerX + 3; x++) {

                    Level level = game.getCurrentLevel();

                    if (!((x < 0) || x > level.getWidth() || (y < 0) || (y > level.getHeight()))
                            && !(new Position(x, y)).equals(level.getPlayerPosition())
                            && level.getEntityPositionCache()[x][y] != null) {
                        Mob mob = (Mob) level.getEntityPositionCache()[x][y];
                        Mob confusedMob = new ConfusedMob(mob, (long) 100);
                        confusedMob.setId(mob.getId());
                        level.getEntityPositionCache()[x][y] = confusedMob;
                        level.getEntities().put(mob.getId(), confusedMob);
                    }

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
