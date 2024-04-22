package org.example.level.generator;

import org.example.inventory.item.Item;
import org.example.inventory.item.wearable.*;
import org.example.level.Level;
import org.example.level.block.LeaveLevelBlock;
import org.example.level.block.Block;
import org.example.level.block.EmptyBlock;
import org.example.level.block.ItemBlock;
import org.example.level.block.WallBlock;
import org.example.level.util.Position;

public class LevelGenerator {

    public static Level generateMainLevel() {
        Integer WIDTH = 100;
        Integer HEIGHT = 100;
        Integer NUMBER_OF_ITEMS = 10;
        Integer NUMBER_OF_WALLS = 50;
        Block[][] map = new Block[HEIGHT][WIDTH];

        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            if (map[y][x] == null) {
                Item item = new Boots();
                int itemType = (int) (Math.random() * 5);
                item = switch (itemType) {
                    case 0 -> new Boots();
                    case 1 -> new Helmet();
                    case 2 -> new Leggings();
                    case 3 -> new Plate();
                    case 4 -> new Sword();
                    default -> item;
                };
                map[y][x] = new ItemBlock(item);

            }
        }

        for (int i = 0; i < NUMBER_OF_WALLS; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            if (map[y][x] == null) {
                map[y][x] = new WallBlock();
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (map[y][x] == null) {
                    map[y][x] = new EmptyBlock();
                }
            }
        }


        return new Level(new Position(0, 0), map);
    }

    public static Level generateBasicLevel() {
        Level level = new Level(100, 100);
        level.getMap()[45][45] = new WallBlock();
        level.getMap()[66][66] = new LeaveLevelBlock();
        level.setPlayerPosition(new Position(50, 50));
        return level;
    }

}
