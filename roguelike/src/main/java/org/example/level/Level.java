package org.example.level;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.entity.Player;
import org.example.level.block.Block;
import org.example.level.util.Position;
import org.example.scene.Console;
import org.example.scene.Drawable;

import java.awt.Color;

import lombok.Getter;
import lombok.Setter;

public class Level implements Drawable, Cloneable {

    @Getter
    private Block[][] map;

    @Getter @Setter
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

    public boolean tryMove(GameContext context, Entity entity, MoveDirection direction) {
        int x = this.getPlayerPosition().x();
        int y = this.getPlayerPosition().y();
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        if ((0 <= x) && (x < getWidth()) && (0 <= y) && (y < getHeight())) {
            if (map[y][x] == null || map[y][x].onVisit(entity, direction, context)) {
                setPlayerPosition(new Position(x, y));
                return true;
            }
        }
        return false;
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
       int l = playerPosition.x() - (console.width() / 2) / 2;
       int t = playerPosition.y() - (console.height() / 2);
       for (int y = 0; y < console.height(); y++) {
           for (int x = 0; x < console.width() / 2; x++) {
               if (0 <= l + x && l + x < getWidth() && 0 <= t + y &&  t + y < getHeight()) {
                   if (l + x == playerPosition.x() && t + y == playerPosition.y()) {
                       console.drawEmoji("\uD83D\uDE00");
                   } else if (map[t + y][l + x] != null) {
                       map[t + y][l + x].draw(console);
                   } else {
                       console.drawString("  ", Color.WHITE);
                   }
               } else {
                   console.drawString("  ", Color.BLUE, Color.BLUE);
               }
           }
           console.nextLine();
       }
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
