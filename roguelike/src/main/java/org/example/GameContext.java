package org.example;

import org.example.entity.Player;
import org.example.entity.mob.Mob;
import org.example.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import lombok.Getter;
import lombok.Setter;

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

    public void enterLevel(String levelId) {
        if (getLevelCollection().containsKey(levelId)) {
            getLevelStack().add(getLevelCollection().get(levelId).clone());
        } else {
            throw new RuntimeException("Level not found");
        }
    }

    public void leaveLevel() {
        if (getLevelStack().size() >= 2) {
            getLevelStack().remove(getLevelStack().size() - 1);
        }
    }

    private GameContext() {}

    public static class Builder {

        GameContext context = new GameContext();

        public Builder registerLevel(String id, Level level) {
            context.levelCollection.put(id, level);
            return this;
        }

        public Builder registerLevel(String id, Function<GameContext, Level> generator) {
            context.levelCollection.put(id, generator.apply(context));
            return this;
        }

        public Builder setInitialLevel(String id) {
            context.levelStack.clear();
            context.levelStack.add(context.levelCollection.get(id).clone());
            return this;
        }

        public GameContext build() {
            return context;
        }

    }

}
