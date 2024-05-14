package org.example.view;


import lombok.RequiredArgsConstructor;
import org.example.GameContext;
import org.example.entity.Player;
import org.example.inventory.ActiveInventory;
import org.example.inventory.item.Item;
import org.example.inventory.item.WearableItem;
import org.example.scene.Console;
import org.example.scene.Drawable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;


@RequiredArgsConstructor
public class StatePanel implements Drawable {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final int INVENTORY_SIZE = 7;
    private static final int MARGIN = 4;
    private static final int TILE_SIZE = 5;
    private static final int TILE_CENTER_OFFSET = (TILE_SIZE - 2) / 2;
    private final GameContext game;
    private int focusedInventoryTile = 0;
    private int curInventoryItemFrom = 0;
    
    
    public void decFocusedInventoryTile() {
        LOGGER.debug("Dec-ing focusedInventoryTile: focusedInventoryTile={}, curInventoryItemFrom={}", focusedInventoryTile, curInventoryItemFrom);
        focusedInventoryTile--;
        
        if (focusedInventoryTile == -1) {
            focusedInventoryTile = 0;
            curInventoryItemFrom = Math.max(0, curInventoryItemFrom - 1);
        }
        LOGGER.debug("Dec-ed focusedInventoryTile: focusedInventoryTile={}, curInventoryItemFrom={}", focusedInventoryTile, curInventoryItemFrom);
    }
    
    
    public void incFocusedInventoryTile() {
        LOGGER.debug("Inc-ing focusedInventoryTile: focusedInventoryTile={}, curInventoryItemFrom={}", focusedInventoryTile, curInventoryItemFrom);
        focusedInventoryTile++;
        
        if (focusedInventoryTile == INVENTORY_SIZE) {
            focusedInventoryTile = INVENTORY_SIZE - 1;
            curInventoryItemFrom = Math.min(Math.max(0, game.getPlayer().getInventory().getItems().size() - INVENTORY_SIZE), curInventoryItemFrom + 1);
        }
        LOGGER.debug("Inc-ed focusedInventoryTile: focusedInventoryTile={}, curInventoryItemFrom={}", focusedInventoryTile, curInventoryItemFrom);
    }
    
    
    public int getFocusedInventoryItemIdx() {
        return curInventoryItemFrom + focusedInventoryTile;
    }
    
    
    private void drawHorizontal(int startRow, int startCol, int len, Console console) {
        for (int i = 0; i < len; i++)
            console.drawString(startRow, startCol + i, Borders.HORIZONTAL, Colors.BACKGROUND);
    }
    
    
    private void drawTable(int startRow, int startCol, int len, Console console) {
        int row = startRow;
        int col = startCol;
        
        console.drawString(row, col, Borders.TOP_LEFT, Colors.BACKGROUND);
        col += 1;
        for (int i = 0; i < len - 1; i++) {
            drawHorizontal(row, col, 4, console);
            col += 4;
            
            console.drawString(row, col, Borders.TOP_MIDDLE, Colors.BACKGROUND);
            col += 1;
        }
        drawHorizontal(row, col, 4, console);
        col += 4;
        console.drawString(row, col, Borders.TOP_RIGHT, Colors.BACKGROUND);
        col += 1;
        
        row += 1;
        for (int i = 0; i < TILE_SIZE - 2; i++) {
            col = startCol;
            console.drawString(row, col, Borders.VERTICAL, Colors.BACKGROUND);
            col++;
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < TILE_SIZE - 1; k++) {
                    console.drawString(row, col, " ", Colors.BACKGROUND);
                    col++;
                }
                console.drawString(row, col, Borders.VERTICAL, Colors.BACKGROUND);
                col++;
            }
            row += 1;
        }
        
        col = startCol;
        console.drawString(row, col, Borders.BOTTOM_LEFT, Colors.BACKGROUND);
        col += 1;
        for (int i = 0; i < len - 1; i++) {
            drawHorizontal(row, col, 4, console);
            col += 4;
            
            console.drawString(row, col, Borders.BOTTOM_MIDDLE, Colors.BACKGROUND);
            col += 1;
        }
        drawHorizontal(row, col, 4, console);
        col += 4;
        console.drawString(row, col, Borders.BOTTOM_RIGHT, Colors.BACKGROUND);
        col += 1;
    }
    
    
    private void drawItem(int startRow, int startCol, Item item, boolean focused, Console console) {
        final Color background = focused? Color.GRAY : Colors.BACKGROUND;
        
        if (item instanceof WearableItem wearableItem) {
            final int hpImprovement = wearableItem.getHpImprovement();
            final int strengthImprovement = wearableItem.getStrengthImprovement();
            if (hpImprovement > 0)
                console.drawString(startRow, startCol, String.format("+%dh", wearableItem.getHpImprovement()), Colors.TEXT, background);
            if (strengthImprovement > 0)
                console.drawString(startRow + TILE_SIZE - 3, startCol, String.format("+%ds", wearableItem.getStrengthImprovement()), Colors.TEXT, background);
        }
        
        int row = startRow + TILE_CENTER_OFFSET;
        int col = startCol + TILE_CENTER_OFFSET;
        if (item.isEmojiIcon())
            console.drawEmoji(row, col, item.getIcon(), background);
        else
            console.drawString(row, col, item.getIcon(), Colors.TEXT, background);
    }
    
    
    private void drawItem(int startRow, int startCol, Item item, Console console) {
        drawItem(startRow, startCol, item, false, console);
    }
    
    
    private void drawActiveInventory(int startRow, int startCol, Console console) {
        drawTable(startRow, startCol, 5, console);
        
        ActiveInventory inventory = game.getPlayer().getActiveInventory();
        final int row = startRow + 1;
        int col = startCol + 1;
        if (inventory.getHelmet() != null)
            drawItem(row, col, inventory.getHelmet(), console);
        col += TILE_SIZE;
        if (inventory.getPlate() != null)
            drawItem(row, col, inventory.getPlate(), console);
        col += TILE_SIZE;
        if (inventory.getLeggings() != null)
            drawItem(row, col, inventory.getLeggings(), console);
        col += TILE_SIZE;
        if (inventory.getBoots() != null)
            drawItem(row, col, inventory.getBoots(), console);
        col += TILE_SIZE;
        if (inventory.getSword() != null)
            drawItem(row, col, inventory.getSword(), console);
        col += TILE_SIZE;
    }
    
    
    private void drawInventory(int startRow, int startCol, Console console) {
        drawTable(startRow, startCol, INVENTORY_SIZE, console);
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 4; col++)
                console.drawString(startRow + 1 + row, startCol + 1 + TILE_SIZE * focusedInventoryTile + col, " ", Colors.BACKGROUND, Color.GRAY);
        
        List<Item> inventory = game.getPlayer().getInventory().getItems();
        if (inventory.size() > curInventoryItemFrom + INVENTORY_SIZE)
            inventory = inventory.subList(curInventoryItemFrom, INVENTORY_SIZE);
        
        int row = startRow + 1;
        int col = startCol + 1;
        for (int itemIdx = 0; itemIdx < inventory.size(); itemIdx++) {
            drawItem(row, col, inventory.get(itemIdx), itemIdx == focusedInventoryTile, console);
            col += TILE_SIZE;
        }
    }
    
    
    private void drawPlayerState(int startRow, int startCol, Console console) {
        for (int row = 0; row < console.inventoryHeight(); row++)
            console.drawString(startRow + row, startCol, "           ", Colors.BACKGROUND);
        
        final Player player = game.getPlayer();
        
        int row = startRow;
        int col = startCol;
        
        console.drawString(row, col, String.format("XP: %d", player.getXp()), Colors.TEXT, Colors.BACKGROUND);
        row++;
        console.drawString(row, col, String.format("Lvl: %d", player.getXp()), Colors.TEXT, Colors.BACKGROUND);
        row++;
        console.drawString(row, col, String.format("Strength: %d", player.getStrength()), Colors.TEXT, Colors.BACKGROUND);
        row++;
        
        final long maxHp = player.getMaxHp();
        final long hp = player.getHp();
        for (int i = 0; i < hp; i++) {
            console.drawEmoji(row, col, "🩷");
            col += 2;
            if (col - startCol >= 10) {
                col = startCol;
                row += 1;
            }
        }
        for (int i = 0; i < maxHp - hp; i++) {
            console.drawEmoji(row, col, "💔");
            col += 2;
            if (col - startCol >= 10) {
                col = startCol;
                row += 1;
            }
        }
    }
    
    
    private int widthOfNTiles(int n) {
        return TILE_SIZE * n + 1;
    }
    
    
    @Override
    public void draw(Console console) {
        console.drawString("", Colors.TEXT, Colors.BACKGROUND);
        final int startRow = console.height() - console.inventoryHeight();
        final int width = console.width();
        
        int col = 0;
        drawActiveInventory(startRow, col, console);
        col += widthOfNTiles(5) + MARGIN;
        drawPlayerState(startRow, col, console);
        col += 10 + MARGIN;
        drawInventory(startRow, col, console);
        col += widthOfNTiles(INVENTORY_SIZE + MARGIN);
    }
}
