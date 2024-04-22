package org.example.view;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.GameContext;
import org.example.inventory.ActiveInventory;
import org.example.inventory.item.Item;
import org.example.inventory.item.wearable.Boots;
import org.example.inventory.item.wearable.Helmet;
import org.example.inventory.item.wearable.Sword;
import org.example.scene.Console;
import org.example.scene.Drawable;

import java.awt.*;


@RequiredArgsConstructor
public class StatePanel implements Drawable {
    private static final int INVENTORY_SIZE = 5;
    private static final int MARGIN = 2;
    private final GameContext game;
    private int curInventoryItem = 0;
    
    
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
        
        for (int i = 0; i < 3; i++) {
            col = startCol;
            row += 1;
            for (int j = 0; j < len + 1; j++) {
                console.drawString(row, col, Borders.VERTICAL, Color.WHITE);
                col += 5;
            }
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
    }
    
    
    private void drawPlayerState(int startRow, int startCol, Console console) {
    
    }
    
    
    private int widthOfNTiles(int n) {
        return 5 * n + 1;
    }
    
    
    @Override
    public void draw(Console console) {
        final int startRow = console.height() - console.inventoryHeight();
        final int width = console.width();
        
        int col = 0;
        drawActiveInventory(startRow, col, console);
        col += widthOfNTiles(5 + MARGIN);
        drawInventory(startRow, col, console);
        col += widthOfNTiles(INVENTORY_SIZE + MARGIN);
        drawPlayerState(startRow, col, console);
    }
}
