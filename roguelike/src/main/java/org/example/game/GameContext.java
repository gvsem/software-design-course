package org.example.game;

import org.example.game.entity.Player;
import org.example.game.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import lombok.Getter;

/**
 * Class corresponding to a game instance - a set of player, level stack and level register
 * GameContext processes player teleportations between levels of the game
 */
public class GameContext {

    @Getter
    private final Player player = new Player();

    @Getter
    private final List<Level> levelStack = new ArrayList<>();

    @Getter
    private final Map<String, Level> levelCollection = new HashMap<>();

    public Level getCurrentLevel() {
        return levelStack.get(levelStack.size() - 1);
    }

    /**
     * Teleport player to newly created level
     * @param levelId identifier of previously declared level
     */
    public void enterLevel(String levelId) {
        if (getLevelCollection().containsKey(levelId)) {
            getLevelStack().add(getLevelCollection().get(levelId).clone());
        } else {
            throw new RuntimeException("Level not found");
        }
    }

    /**
     * Leave currently visited level
     * @apiNote this action does not reset level and its state
     */
    public void leaveLevel() {
        if (getLevelStack().size() >= 2) {
            getLevelStack().remove(getLevelStack().size() - 1);
        }
    }

    private GameContext() {}

    /**
     * A builder for the game that lets customize level register and choose initial level of the game
     */
    public static class Builder {

        private GameContext context = new GameContext();

        /**
         * Register new game level
         * @param id unique level identifier for this game
         * @param generator function to generate level using GameContext
         * @return instance of builder
         */
        public Builder registerLevel(String id, Function<GameContext, Level> generator) {
            context.levelCollection.put(id, generator.apply(context));
            return this;
        }

        /**
         * Set initial game level on which player is spawned
         * @param id previously declared game level
         * @return instance of builder
         */
        public Builder setInitialLevel(String id) {
            context.levelStack.clear();
            context.levelStack.add(context.levelCollection.get(id).clone());
            return this;
        }

        /**
         * Build game
         * @return new game instance
         */
        public GameContext build() {
            return context;
        }

    }

}
