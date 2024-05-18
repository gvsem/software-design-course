package org.example.level;

import org.example.GameContext;
import org.example.entity.mob.ClassicMobFactory;
import org.example.entity.mob.Mob;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import lombok.Getter;
import lombok.Setter;

public class LevelGenerator {

    @Getter @Setter
    private Integer width = 30;
    @Getter @Setter
    private Integer height = 30;
    @Getter @Setter
    private Integer numberOfWalls = 30;
    @Getter @Setter
    private Integer numberOfLeaveLevelBlocks = 1;
    @Getter @Setter
    private Position spawnPlayerPosition = new Position(0, 0);
    @Getter @Setter
    private Integer numberOfItems = 10;
    @Getter @Setter
    private MobFactory mobFactory = new ClassicMobFactory();

    private record MobGeneratorTask (
        BiFunction<Level, GameContext, Mob> mobGenerator,
        Position position
    ) {}

    private final List<MobGeneratorTask> mobGeneratorTasks = new ArrayList<>();

    public LevelGenerator addMob(BiFunction<Level, GameContext, Mob> mobGenerator, Position position) {
        mobGeneratorTasks.add(new MobGeneratorTask(mobGenerator, position));
        return this;
    }

    public LevelGenerator addKiller(Position position) {
        mobGeneratorTasks.add(new MobGeneratorTask(mobFactory::createKiller, position));
        return this;
    }

    public LevelGenerator addStander(Position position) {
        mobGeneratorTasks.add(new MobGeneratorTask(mobFactory::createStander, position));
        return this;
    }

    public LevelGenerator addCoward(Position position) {
        mobGeneratorTasks.add(new MobGeneratorTask(mobFactory::createCoward, position));
        return this;
    }

    public LevelGenerator addReplicable(Position position) {
        mobGeneratorTasks.add(new MobGeneratorTask(mobFactory::createReplicable, position));
        return this;
    }

    public Level build(GameContext gameContext) {

        Block[][] map = new Block[height][width];

        for (int i = 0; i < numberOfItems; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
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

        for (int i = 0; i < numberOfWalls; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            if (map[y][x] == null) {
                map[y][x] = new WallBlock();
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == null) {
                    map[y][x] = new EmptyBlock();
                }
            }
        }

        for (int i = 0; i < numberOfLeaveLevelBlocks; i++) {
            int x = 0;
            int y = 0;
            while (x == 0 && y == 0 || map[y][x] == null) {
                x = (int) (Math.random() * width/4);
                y = (int) (Math.random() * height/4);
                map[y][x] = new LeaveLevelBlock();
            }
        }


        Level level = new Level(gameContext, map)
                .spawnPlayer(spawnPlayerPosition);

        for (var task : mobGeneratorTasks) {
            level.spawn(task.mobGenerator.apply(level, gameContext), task.position);
        }

        return level;
    }

}
