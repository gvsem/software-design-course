package org.example.level.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.inventory.item.wearable.*;
import org.example.level.Level;
import org.example.level.block.*;
import org.example.level.util.Position;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LevelLoader {

    public static Level loadLevel(Path file) {
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
                        case "EmptyBlock":
                            thatBlock = new EmptyBlock();
                            break;
                        case "EntryLevelBlock":
                            thatBlock = new EnterLevelBlock(block.getLevelIdToEnter());
                            break;
                        case "ItemBlock":
                            switch (block.getItemName()) {
                                case "Helmet":
                                    thatBlock = new ItemBlock(new Helmet());
                                    break;
                                case "Leggings":
                                    thatBlock = new ItemBlock(new Leggings());
                                    break;
                                case "Sword":
                                    thatBlock = new ItemBlock(new Sword());
                                    break;
                                case "Plate":
                                    thatBlock = new ItemBlock(new Plate());
                                    break;
                                case "Boots":
                                    thatBlock = new ItemBlock(new Boots());
                                    break;
                            }
                            break;
                        case "WallBlock":
                            thatBlock = new WallBlock();
                            break;
                        case "LeaveLevelBlock":
                            thatBlock = new LeaveLevelBlock();
                            break;
                    }
                    realRow.add(thatBlock);
                }
                realMap.add(realRow);
            }
            org.example.level.pojo.Position position = json.getPosition();
            Position realPosition = new Position(position.getX(), position.getY());
            return new Level(realPosition, realMap.stream().map(e -> e.toArray(new Block[realMap.get(0).size()])).toArray(Block[][]::new));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
