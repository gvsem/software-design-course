package org.example.level;

import org.example.GameContext;
import org.example.GameFactory;
import org.example.entity.mob.ClassicMobFactory;
import org.example.entity.mob.HalloweenMobFactory;
import org.example.entity.mob.MobFactory;
import org.example.level.block.LeaveLevelBlock;
import org.example.level.block.WallBlock;
import org.example.level.util.Position;

import java.util.Random;

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
