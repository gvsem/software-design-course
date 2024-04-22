package org.example.view;


import lombok.RequiredArgsConstructor;
import org.example.GameContext;
import org.example.entity.Player;
import org.example.inventory.ActiveInventory;
import org.example.inventory.item.Item;
import org.example.scene.Console;
import org.example.scene.Drawable;

import java.awt.*;
import java.util.List;


@RequiredArgsConstructor
public class StatePanel implements Drawable {
    private static final int INVENTORY_SIZE = 7;
    private static final int MARGIN = 4;
    private final GameContext game;
    private int focusedInventoryTile = 0;
    private int curInventoryItemFrom = 0;
    
    
    public void decFocusedInventoryTile() {
        focusedInventoryTile = Math.max(0, focusedInventoryTile - 1);
    }
    
    
    public void incFocusedInventoryTile() {
        focusedInventoryTile = Math.min(INVENTORY_SIZE - 1, focusedInventoryTile + 1);
    }
    
    
    private void drawHorizontal(int startRow, int startCol, int len, Console console) {
        for (int i = 0; i < len; i++)
            console.drawString(startRow, startCol + i, "─", Color.WHITE);
    }
    
    
    private void drawTable(int startRow, int startCol, int len, Console console) {
        int row = startRow;
        int col = startCol;
        
        console.drawString(row, col, Borders.TOP_LEFT, Color.WHITE);
        col += 1;
        for (int i = 0; i < len - 1; i++) {
            drawHorizontal(row, col, 4, console);
            col += 4;
            
            console.drawString(row, col, Borders.TOP_MIDDLE, Color.WHITE);
            col += 1;
        }
        drawHorizontal(row, col, 4, console);
        col += 4;
        console.drawString(row, col, Borders.TOP_RIGHT, Color.WHITE);
        col += 1;
        
        row += 1;
        for (int i = 0; i < 3; i++) {
            col = startCol;
            for (int j = 0; j < len + 1; j++) {
                console.drawString(row, col, Borders.VERTICAL, Color.WHITE);
                col += 5;
            }
            row += 1;
        }
        
        col = startCol;
        console.drawString(row, col, Borders.BOTTOM_LEFT, Color.WHITE);
        col += 1;
        for (int i = 0; i < len - 1; i++) {
            drawHorizontal(row, col, 4, console);
            col += 4;
            
            console.drawString(row, col, Borders.BOTTOM_MIDDLE, Color.WHITE);
            col += 1;
        }
        drawHorizontal(row, col, 4, console);
        col += 4;
        console.drawString(row, col, Borders.BOTTOM_RIGHT, Color.WHITE);
        col += 1;
    }
    
    
    private void drawItem(int startRow, int startCol, Item item, Console console) {
        if (item.isEmojiIcon())
            console.drawEmoji(startRow, startCol, item.getIcon());
        else
            console.drawString(startRow, startCol, item.getIcon(), Color.WHITE);
    }
    
    
    private void drawActiveInventory(int startRow, int startCol, Console console) {
        drawTable(startRow, startCol, 5, console);
        
        ActiveInventory inventory = game.getPlayer().getActiveInventory();
        final int row = startRow + 2;
        int col = startCol + 2;
        if (inventory.getHelmet() != null)
            drawItem(row, col, inventory.getHelmet(), console);
        col += 5;
        if (inventory.getPlate() != null)
            drawItem(row, col, inventory.getPlate(), console);
        col += 5;
        if (inventory.getLeggings() != null)
            drawItem(row, col, inventory.getLeggings(), console);
        col += 5;
        if (inventory.getBoots() != null)
            drawItem(row, col, inventory.getBoots(), console);
        col += 5;
        if (inventory.getSword() != null)
            drawItem(row, col, inventory.getSword(), console);
        col += 5;
    }
    
    
    private void drawInventory(int startRow, int startCol, Console console) {
        drawTable(startRow, startCol, INVENTORY_SIZE, console);
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 4; col++)
                console.drawString(startRow + 1 + row, startCol + 1 + 5 * focusedInventoryTile + col, " ", Color.WHITE, Color.GRAY);
        
        List<Item> inventory = game.getPlayer().getInventory().getItems();
        if (inventory.size() > curInventoryItemFrom + INVENTORY_SIZE)
            inventory = inventory.subList(curInventoryItemFrom, INVENTORY_SIZE);
        
        int row = startRow + 2;
        int col = startCol + 2;
        for (Item item: inventory) {
            drawItem(row, col, item, console);
            col += 5;
        }
    }
    
    
    private void drawPlayerState(int startRow, int startCol, Console console) {
        final Player player = game.getPlayer();
        final long maxHp = player.getMaxHp();
        final long hp = player.getHp();
        
        int row = startRow;
        int col = startCol;
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
        return 5 * n + 1;
    }
    
    
    @Override
    public void draw(Console console) {
        console.drawString("", Color.BLACK, Color.WHITE);
        final int startRow = console.height() - console.inventoryHeight();
        final int width = console.width();
        
        int col = 0;
        drawActiveInventory(startRow, col, console);
        col += widthOfNTiles(5) + MARGIN;
        drawPlayerState(startRow + 3, col, console);
        col += 10 + MARGIN;
        drawInventory(startRow, col, console);
        col += widthOfNTiles(INVENTORY_SIZE + MARGIN);
    }
}
