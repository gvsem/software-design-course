package org.example.level.generator;

import org.example.GameContext;
import org.example.entity.mob.MobFactory;
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

    public static Level generateMainLevel(GameContext gameContext) {
        Integer WIDTH = 30;
        Integer HEIGHT = 30;
        Integer NUMBER_OF_ITEMS = 10;
        Integer NUMBER_OF_WALLS = 30;
        Block[][] map = new Block[HEIGHT][WIDTH];

        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            if (map[y][x] == null) {
                Item item = new Boots();
                int itemType = (int) (Math.random() * 6);
                item = switch (itemType) {
                    case 0 -> new Boots();
                    case 1 -> new Helmet();
                    case 2 -> new Leggings();
                    case 3 -> new Plate();
                    case 4 -> new Sword();
                    case 5 -> new Poison();
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
        int x = 0;
        int y = 0;
        while (x == 0 && y == 0 || map[y][x] == null) {
            x = (int) (Math.random() * WIDTH/4);
            y = (int) (Math.random() * HEIGHT/4);
            map[y][x] = new LeaveLevelBlock();
        }

        Level level = new Level(gameContext, map)
                .spawnPlayer(new Position(0, 0));
        return level
                .spawn(MobFactory.createKiller(level, gameContext), new Position(WIDTH / 2, HEIGHT / 2))
                .spawn(MobFactory.createStander(level, gameContext), new Position(WIDTH / 2 - 5, HEIGHT / 2 - 5))
                .spawn(MobFactory.createCoward(level, gameContext), new Position(WIDTH / 2 + 5, HEIGHT / 2 + 5));
    }

    public static Level generateBasicLevel(GameContext gameContext) {
        Level level = new Level(gameContext, 100, 100);
        level.getMap()[45][45] = new WallBlock();
        level.getMap()[66][66] = new LeaveLevelBlock();
        level.spawnPlayer(new Position(50, 50));
        return level;
    }

}
