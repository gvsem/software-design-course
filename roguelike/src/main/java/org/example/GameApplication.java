package org.example;

import org.example.scene.GameScene;

public class GameApplication {

    private GameScene scene = null;

    public GameApplication(GameContext context) {

    }

    public void run() {
        while (true) {
            // TODO: render scene, accept keyboard events etc.
        }
    }

    public static void main(String[] args) {
        new GameApplication(GameFactory.createBasicGame()).run();
    }

}
