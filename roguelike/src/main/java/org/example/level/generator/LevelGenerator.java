package org.example.level.generator;

import org.example.level.Level;
import org.example.level.block.LeaveLevelBlock;
import org.example.level.block.WallBlock;
import org.example.level.util.Position;

public class LevelGenerator {

    public static Level generateMainLevel() {
        Level level = new Level(100, 100);
        level.getMap()[45][45] = new WallBlock();
        level.setPlayerPosition(new Position(50, 50));
        return level;
    }

    public static Level generateBasicLevel() {
        Level level = new Level(100, 100);
        level.getMap()[45][45] = new WallBlock();
        level.getMap()[66][66] = new LeaveLevelBlock();
        level.setPlayerPosition(new Position(50, 50));
        return level;
    }

}
