package org.example.level;

import org.example.level.block.Block;
import org.example.level.util.Position;
import org.example.scene.Console;
import org.example.scene.Drawable;

import java.awt.Color;

import lombok.Getter;

public class Level implements Drawable, Cloneable {

    @Getter
    private Block[][] map;

    @Getter
    private Position playerPosition;


    public Level(Integer width, Integer height) {
        this.map = new Block[width][height];
        this.playerPosition = new Position(0, 0);
    }

    public Level(Position playerPosition, Block[][] map) {
        this.map = map;
        this.playerPosition = playerPosition;
    }

    public int getWidth() {
        return this.map[0].length;
    }

    public int getHeight() {
        return this.map.length;
    }

    @Override
    public String getIcon() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Color getColor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void draw(Console console) {
       // TODO: implement level render
    }

    @Override
    public Level clone() {
        try {
            Level level = (Level) super.clone();
            level.map = map.clone();
            level.playerPosition = (Position) playerPosition.clone();
            return level;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
