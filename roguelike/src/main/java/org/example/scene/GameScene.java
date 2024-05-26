package org.example.scene;

import lombok.Getter;
import org.example.game.GameContext;
import org.example.scene.command.Command;
import org.example.scene.command.QuitCommand;
import org.example.scene.util.Commandable;
import org.example.scene.util.Console;
import org.example.scene.util.Drawable;
import org.example.scene.util.Tickable;
import org.example.controller.view.Colors;
import org.example.controller.view.StatePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;


public class GameScene implements Drawable, Tickable, Commandable {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Getter
    private boolean running = false;
    private boolean aboutToQuit = false;
    
    private final GameContext game;
    public final StatePanel statePanel;
    
    public GameScene(GameContext game) {
        this.game = game;
        this.statePanel = new StatePanel(game);
        this.running = true;
    }

    @Override
    public void submit(Command command) {
        if (command == null || game.getCurrentLevel().playerIsDead()) {
            return;
        }

        LOGGER.debug("Got event: {}", command.description());

        if (game.getCurrentLevel().playerIsDead()) {
            return;
        }

        if (aboutToQuit) {
            if (command instanceof QuitCommand)
                running = false;
            else
                aboutToQuit = false;
        }

        command.run(this, this.game);
    }
    
    
    @Override
    public void draw(Console console) {

        this.game.getCurrentLevel().draw(console);
        this.statePanel.draw(console);
        
        if (aboutToQuit) {
            final String quitMsg = "Hit ESC again to quit or any other key to continue.";
            console.drawString(
                    console.height() / 2,
                    (console.width() - quitMsg.length()) / 2,
                    quitMsg,
                    Colors.TEXT,
                    Colors.BACKGROUND
            );
        }

        if (this.game.getCurrentLevel().playerIsDead()) {
            final String quitMsg = "Game over, bro.";
            console.drawString(
                    console.height() / 2,
                    (console.width() - quitMsg.length()) / 2,
                    quitMsg,
                    Color.BLACK,
                    Color.WHITE
            );
        }
    }

    @Override
    public boolean tick(Long time) {
        return game.getCurrentLevel().tick(time);
    }

    public void quit() {
        aboutToQuit = true;
    }
}
