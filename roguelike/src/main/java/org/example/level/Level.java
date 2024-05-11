package org.example.level;

import org.example.GameContext;
import org.example.entity.Entity;
import org.example.entity.MoveDirection;
import org.example.entity.handler.CombatHandler;
import org.example.entity.mob.Mob;
import org.example.level.block.Block;
import org.example.level.util.Position;
import org.example.scene.Console;
import org.example.scene.Drawable;
import org.example.scene.Tickable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

public class Level implements Drawable, Tickable, Cloneable {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Getter
    private volatile Block[][] map;

    @Getter @Setter
    private Map<String, Position> position = new ConcurrentHashMap<>();

    @Getter
    private final Map<String, Mob> mobs = new ConcurrentHashMap<>();

    @Getter
    private final Mob[][] mobPositionCache;

    public Level(Integer width, Integer height) {
        this.map = new Block[width][height];
        this.mobPositionCache = new Mob[width][height];
    }

    public Level(Block[][] map) {
        this.map = map;
        this.mobPositionCache = new Mob[map.length][map[0].length];
    }

    public int getWidth() {
        return this.map[0].length;
    }

    public int getHeight() {
        return this.map.length;
    }

    public synchronized Level setEntityPosition(String entityId, Position position) {
        if (this.position.containsKey(entityId)) {
            int x, y;
            x = this.position.get(entityId).x();
            y = this.position.get(entityId).y();
            this.mobPositionCache[x][y] = null;
        }
        this.position.put(entityId, position);
        if (!entityId.equals("player")) {
            this.mobPositionCache[position.x()][position.y()] = this.mobs.get(entityId);
        }
        return this;
    }

    public synchronized Level spawnMob(Mob entity, Position position) {
        this.mobs.put(entity.getId(), entity);
        this.setEntityPosition(entity.getId(), position);
        return this;
    }

    public synchronized Position getPlayerPosition() {
        return this.position.get("player");
    }

    public synchronized boolean tryMove(GameContext context, Entity entity, MoveDirection direction) {
        int x = this.position.get(entity.getId()).x();
        int y = this.position.get(entity.getId()).y();
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        if ((0 <= x) && (x < getWidth()) && (0 <= y) && (y < getHeight())) {
            if (mobPositionCache[x][y] != null) {
                CombatHandler.fight(entity, mobPositionCache[x][y]);
                return false;
            } else if (map[y][x] == null || map[y][x].onVisit(entity, direction, context)) {
                LOGGER.debug("entity " + entity.getId() + " moved from (" + this.position.get(entity.getId()).x() + ", " +
                        this.position.get(entity.getId()).y() +") to (" + x + ", " + y + ")");
                setEntityPosition(entity.getId(), new Position(x, y));

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
    public synchronized void draw(Console console) {
        Position playerPosition = getPlayerPosition();
       int l = playerPosition.x() - (console.width() / 2) / 2;
       int t = playerPosition.y() - (console.height() / 2);
       for (int y = 0; y < console.height(); y++) {
           for (int x = 0; x < console.width() / 2; x++) {
               if (0 <= l + x && l + x < getWidth() && 0 <= t + y &&  t + y < getHeight()) {
                   if (l + x == playerPosition.x() && t + y == playerPosition.y()) {
                       console.drawEmoji("\uD83D\uDE00");
                   } else if (mobPositionCache[l + x][t + y] != null) {
                       mobPositionCache[l + x][t + y].draw(console);
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
        return this;
//        try {
//            Level level = (Level) super.clone();
//            level.map = map.clone();
//            level.position = (Map<String, Position>) new HashMap<>(position).clone();
//            level.position = (Map<String, Position>) new HashMap<>(position).clone();
//            return level;
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public synchronized boolean tick(Long time) {
        boolean changed = false;
        for (Mob mob : this.mobs.values()) {
            changed |= mob.tick(time);
        }
        return changed;
    }
}
