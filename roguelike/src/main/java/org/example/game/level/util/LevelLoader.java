package org.example.game.level.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.game.GameContext;
import org.example.game.inventory.item.wearable.Boots;
import org.example.game.inventory.item.wearable.Helmet;
import org.example.game.inventory.item.wearable.Leggings;
import org.example.game.inventory.item.wearable.Plate;
import org.example.game.inventory.item.wearable.Sword;
import org.example.game.level.Level;
import org.example.game.level.block.Block;
import org.example.game.level.block.CactusBlock;
import org.example.game.level.block.EmptyBlock;
import org.example.game.level.block.EnterLevelBlock;
import org.example.game.level.block.HealBlock;
import org.example.game.level.block.ItemBlock;
import org.example.game.level.block.LeaveLevelBlock;
import org.example.game.level.block.WallBlock;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelLoader reads level preset from json file, whose schema is described in 'level.json'
 */
public class LevelLoader {

    public static Level loadLevel(Path file, GameContext gameContext) {
        try (InputStream is = Files.newInputStream(file)) {
            ObjectMapper mapper = new ObjectMapper();
            org.example.level.pojo.Level json = mapper.readValue(is, org.example.level.pojo.Level.class);

            List<List<org.example.level.pojo.Block>> map = json.getMap();
            ArrayList<ArrayList<Block>> realMap = new ArrayList<>(map.size());

            for (List<org.example.level.pojo.Block> row : map) {
                ArrayList<Block> realRow = new ArrayList<>();
                for (org.example.level.pojo.Block block : row) {
                    Block thatBlock = null;
                    switch (block.getType().toString()) {
                        case "EmptyBlock" -> thatBlock = new EmptyBlock();
                        case "EnterLevelBlock" -> thatBlock = new EnterLevelBlock(block.getLevelIdToEnter());
                        case "ItemBlock" -> {
                            thatBlock = switch (block.getItemName()) {
                                case "Helmet" -> new ItemBlock(new Helmet());
                                case "Leggings" -> new ItemBlock(new Leggings());
                                case "Sword" -> new ItemBlock(new Sword());
                                case "Plate" -> new ItemBlock(new Plate());
                                case "Boots" -> new ItemBlock(new Boots());
                                default -> thatBlock;
                            };
                        }
                        case "WallBlock" -> thatBlock = new WallBlock();
                        case "LeaveLevelBlock" -> thatBlock = new LeaveLevelBlock();
                        case "CactusBlock" -> thatBlock = new CactusBlock();
                        case "HealBlock" -> thatBlock = new HealBlock();
                    }
                    realRow.add(thatBlock);
                }
                realMap.add(realRow);
            }
            org.example.level.pojo.Position position = json.getPosition();
            Position realPosition = new Position(position.getX(), position.getY());
            return new Level(gameContext, realMap.stream().map(e -> e.toArray(new Block[realMap.get(0).size()])).toArray(Block[][]::new))
                    .spawnPlayer(realPosition);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
