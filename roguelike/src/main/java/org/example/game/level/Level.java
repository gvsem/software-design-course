package org.example.game.level;

import org.example.game.GameContext;
import org.example.game.entity.Entity;
import org.example.game.entity.MoveDirection;
import org.example.game.entity.handler.CombatHandler;
import org.example.game.entity.mob.ConfusedMob;
import org.example.game.entity.mob.Mob;
import org.example.game.level.block.Block;
import org.example.game.level.util.Position;
import org.example.scene.util.Console;
import org.example.scene.util.Drawable;
import org.example.scene.util.Tickable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

/**
 * Level is a 2d field consisting of blocks
 */
public class Level implements Drawable, Tickable, Cloneable {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Getter
    private final Block[][] map;

    @Getter @Setter
    private Map<String, Position> position = new ConcurrentHashMap<>();

    @Getter
    private final Map<String, Entity> entities = new ConcurrentHashMap<>();

    @Getter
    private final Entity[][] entityPositionCache;

    @Getter
    private final GameContext gameContext;

    /**
     * Constructs empty level corresponding to the game instance
     * @param gameContext current game
     * @param width of level map
     * @param height of level map
     */
    public Level(GameContext gameContext, Integer width, Integer height) {
        this.map = new Block[width][height];
        this.entityPositionCache = new Entity[width][height];
        this.gameContext = gameContext;
    }

    /**
     * Constructs level from block 2d array
     * @param gameContext current game
     * @param map 2d array of nullable blocks
     */
    public Level(GameContext gameContext, Block[][] map) {
        this.map = map;
        this.entityPositionCache = new Entity[map.length][map[0].length];
        this.gameContext = gameContext;
    }

    /**
     * @return width of the level map
     */
    public int getWidth() {
        return this.map[0].length;
    }

    /**
     * @return height of the level map
     */
    public int getHeight() {
        return this.map.length;
    }

    /**
     * Set initial player position and place him on the map
     * @param position initial position
     * @return current level instance
     */
    public synchronized Level spawnPlayer(Position position) {
        this.entities.put("player", gameContext.getPlayer());
        this.setEntityPosition("player", position);
        return this;
    }

    /**
     * @return current player position
     */
    public synchronized Position getPlayerPosition() {
        return this.position.get("player");
    }

    /**
     * @return true, if player is dead
     */
    public boolean playerIsDead() {
        return gameContext.getPlayer().isDead();
    }

    /**
     * Spawn mob on the level
     * @param entity new mob
     * @param position position on the level map
     */
    public synchronized void spawn(Mob entity, Position position) {
        if (0 <= position.x() && position.x() < getWidth() && 0 <= position.y() && position.y() < getHeight()) {
            if (this.entityPositionCache[position.x()][position.y()] != null) {
                return;
            }
            this.entities.put(entity.getId(), entity);
            this.setEntityPosition(entity.getId(), position);
        }
    }

    /**
     * Removes mob from the level, including level position cache
     * @param mob current mob instance
     */
    public void removeMob(Mob mob) {
        String entityId = mob.getId();
        if (this.position.containsKey(entityId)) {
            int x, y;
            x = this.position.get(entityId).x();
            y = this.position.get(entityId).y();
            this.entityPositionCache[x][y] = null;
        }
        this.position.remove(entityId);
    }

    /**
     * Try moving entity towards certain direction
     * @param context game instance
     * @param entity moved entity
     * @param direction desired direction
     * @return true, if block accepted the move and allowed transition
     */
    public synchronized boolean tryMove(GameContext context, Entity entity, MoveDirection direction) {
        if (!this.position.containsKey(entity.getId())) {
            return false;
        }

        int x = this.position.get(entity.getId()).x();
        int y = this.position.get(entity.getId()).y();
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        if ((0 <= x) && (x < getWidth()) && (0 <= y) && (y < getHeight())) {
            if (entityPositionCache[x][y] != null) {
                CombatHandler.fight(entity, entityPositionCache[x][y]);
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
                       if (entityPositionCache[l + x][t + y] != null) {
                       entityPositionCache[l + x][t + y].draw(console);
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
    }

    @Override
    public synchronized boolean tick(Long time) {
        boolean changed = false;
        for (Entity mob : this.entities.values()) {
            if (mob instanceof Mob) {
                changed |= ((Mob) mob).tick(time);
            }
        }
        return changed;
    }

    private synchronized void setEntityPosition(String entityId, Position position) {
        if (this.position.containsKey(entityId)) {
            int x, y;
            x = this.position.get(entityId).x();
            y = this.position.get(entityId).y();
            this.entityPositionCache[x][y] = null;
        }
        this.position.put(entityId, position);
        this.entityPositionCache[position.x()][position.y()] = this.entities.get(entityId);
    }
}
