package org.example;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.scene.GameScene;
import org.example.util.KeyStrokeToEventMapper;
import org.example.view.Colors;

import java.io.IOException;


public class GameApplication {
    private GameScene scene = null;
    
    
    public GameApplication(GameContext context) {
        scene = new GameScene(context);
    }
    
    
    public void run() throws IOException {
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(80, 24))
                .createTerminal();
        terminal.setCursorVisible(false);
        terminal.setBackgroundColor(Colors.BLACK);
        terminal.setForegroundColor(Colors.WHITE);
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        
        while (scene.isRunning()) {
            KeyStroke keyStroke = screen.pollInput();
            
            if (keyStroke == null)
                continue;
            
            scene.submitEvent(KeyStrokeToEventMapper.map(keyStroke));
        }
        
        screen.stopScreen();
    }
    
    
    public static void main(String[] args) throws IOException {
        new GameApplication(GameFactory.createBasicGame()).run();
    }
    
}
