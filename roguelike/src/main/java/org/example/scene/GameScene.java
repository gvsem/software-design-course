package org.example.scene;


import lombok.Getter;
import org.example.Event;
import org.example.GameContext;
import org.example.entity.MoveDirection;
import org.example.entity.Player;
import org.example.inventory.ActiveInventory;
import org.example.inventory.item.Item;
import org.example.view.StatePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class GameScene implements Drawable {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Getter
    private boolean running = false;
    private boolean aboutToQuit = false;
    
    private final GameContext game;
    private final StatePanel statePanel;
    
    
    public GameScene(GameContext game) {
        this.game = game;
        this.statePanel = new StatePanel(game);
        this.running = true;
    }
    
    
    public void submitEvent(Event event) {
        LOGGER.debug("Got event: {}", event);
        
        if (aboutToQuit) {
            if (event == Event.QUIT)
                running = false;
            else
                aboutToQuit = false;
        }
        
        if (event == null)
            return;
        
        switch (event) {
            case QUIT -> aboutToQuit = true;
            case GAME_LEFT, GAME_RIGHT, GAME_UP, GAME_DOWN -> {
                MoveDirection direction = switch (event) {
                    case GAME_RIGHT -> MoveDirection.RIGHT;
                    case GAME_UP -> MoveDirection.UP;
                    case GAME_DOWN -> MoveDirection.DOWN;
                    default -> MoveDirection.LEFT;
                };
                game.getCurrentLevel().tryMove(game, game.getPlayer(), direction);
            }
            case INVENTORY_LEFT -> statePanel.decFocusedInventoryTile();
            case INVENTORY_RIGHT -> statePanel.incFocusedInventoryTile();
            case PUT_ON -> {
                final Player player = game.getPlayer();
                final List<Item> items = player.getInventory().getItems();
                final int itemToPutOnIdx = statePanel.getFocusedInventoryItemIdx();
                
                if (itemToPutOnIdx >= items.size())
                    return;
                
                final Item swapped = player.getActiveInventory().swap(items.get(itemToPutOnIdx));
                if (swapped == null)
                    items.remove(itemToPutOnIdx);
                else
                    items.set(itemToPutOnIdx, swapped);
            }
            case TAKE_OFF_HELMET -> {
                final Player player = game.getPlayer();
                final ActiveInventory activeInventory = player.getActiveInventory();
                
                if (activeInventory.getHelmet() == null)
                    return;
                
                player.getInventory().getItems().add(activeInventory.getHelmet());
                activeInventory.setHelmet(null);
            }
            case TAKE_OFF_PLATE -> {
                final Player player = game.getPlayer();
                final ActiveInventory activeInventory = player.getActiveInventory();
                
                if (activeInventory.getPlate() == null)
                    return;
                
                player.getInventory().getItems().add(activeInventory.getPlate());
                activeInventory.setPlate(null);
            }
            case TAKE_OFF_LEGGINGS -> {
                final Player player = game.getPlayer();
                final ActiveInventory activeInventory = player.getActiveInventory();
                
                if (activeInventory.getLeggings() == null)
                    return;
                
                player.getInventory().getItems().add(activeInventory.getLeggings());
                activeInventory.setLeggings(null);
            }
            case TAKE_OFF_BOOTS -> {
                final Player player = game.getPlayer();
                final ActiveInventory activeInventory = player.getActiveInventory();
                
                if (activeInventory.getBoots() == null)
                    return;
                
                player.getInventory().getItems().add(activeInventory.getHelmet());
                activeInventory.setBoots(null);
            }
            case TAKE_OFF_SWORD -> {
                final Player player = game.getPlayer();
                final ActiveInventory activeInventory = player.getActiveInventory();
                
                if (activeInventory.getSword() == null)
                    return;
                
                player.getInventory().getItems().add(activeInventory.getSword());
                activeInventory.setSword(null);
            }
            default -> {
            }
        }
    }
    
    
    @Override
    public void draw(Console console) {
        this.game.getCurrentLevel().draw(console);
        this.statePanel.draw(console);
    }
}
