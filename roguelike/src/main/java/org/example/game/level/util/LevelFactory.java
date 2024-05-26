package org.example.game.level.util;

import org.example.game.GameContext;
import org.example.game.entity.mob.factory.ClassicMobFactory;
import org.example.game.entity.mob.factory.HalloweenMobFactory;
import org.example.game.level.Level;
import org.example.game.level.block.LeaveLevelBlock;
import org.example.game.level.block.WallBlock;

import java.util.Random;

/**
 * Series of predefined levels
 */
public class LevelFactory {

    public static Level generateEmptyLevel(GameContext gameContext) {
        Level level = new Level(gameContext, 100, 100);
        level.getMap()[45][45] = new WallBlock();
        level.getMap()[66][66] = new LeaveLevelBlock();
        level.spawnPlayer(new Position(50, 50));
        return level;
    }

    public static Level generateFirstLevel(GameContext gameContext) {
        LevelGenerator gen = new LevelGenerator();
        gen.setMobFactory((new Random().nextInt() % 2 == 0) ? new ClassicMobFactory() : new HalloweenMobFactory());
        gen.addKiller(new Position(gen.getWidth() / 2, gen.getHeight() / 2));
        gen.addStander(new Position(gen.getWidth() / 2 - 5, gen.getHeight() / 2 - 5));
        gen.addCoward(new Position(gen.getWidth() / 2 + 5, gen.getHeight() / 2 + 5));
        gen.addReplicable(new Position(5, 5));
        return gen.build(gameContext);
    }

}
