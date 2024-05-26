package org.example;


import static java.lang.System.exit;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import org.example.game.GameContext;
import org.example.game.GameFactory;
import org.example.scene.util.Console;
import org.example.scene.GameScene;
import org.example.controller.CommandMapper;
import org.example.controller.view.Colors;

import java.awt.Color;
import java.io.IOException;


/**
 * Main class initializing user interface and processing I/O events
 */
public class GameApplication implements Console {

    public static void main(String[] args) {
        GameApplication app = new GameApplication(GameFactory.createBasicGame());
    }

    private final GameScene scene;
    private final Terminal terminal;
    private final Screen screen;
    private TextGraphics textGraphics;
    
    private final int WIDTH = 80;
    private final int HEIGHT = 24;
    
    private static final TextColor BACKGROUND_TEXTCOLOR = new TextColor.RGB(Colors.BACKGROUND.getRed(), Colors.BACKGROUND.getGreen(), Colors.BACKGROUND.getBlue());
    private static final TextColor TEXT_TEXTCOLOR = new TextColor.RGB(Colors.TEXT.getRed(), Colors.TEXT.getGreen(), Colors.TEXT.getBlue());

    public GameApplication(GameContext context) {
        scene = new GameScene(context);
        try {
            terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(WIDTH, HEIGHT))
                    .createTerminal();

            terminal.setCursorVisible(false);
            terminal.setBackgroundColor(BACKGROUND_TEXTCOLOR);
            terminal.setForegroundColor(TEXT_TEXTCOLOR);

            screen = new TerminalScreen(terminal);
            screen.startScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(new RenderTask()).start();
    }
    
    @Override
    public void drawEmoji(int row, int col, String emoji) {
        textGraphics.setCharacter(col, row, TextCharacter.fromString(emoji)[0].withBackgroundColor(BACKGROUND_TEXTCOLOR));
    }
    
    @Override
    public void drawEmoji(String emoji) {
        try {
            textGraphics.setCharacter(terminal.getCursorPosition(), TextCharacter.fromString(emoji)[0].withBackgroundColor(BACKGROUND_TEXTCOLOR));
        } catch (IOException ignored) {}
    }
    
    @Override
    public void drawEmoji(int row, int col, String emoji, Color background) {
        textGraphics.setCharacter(col, row, TextCharacter.fromString(emoji)[0].withBackgroundColor(toTextColor(background)));
    }
    
    @Override
    public void drawString(int row, int col, String text, Color color) {
        try {
            terminal.setForegroundColor(toTextColor(color));
            textGraphics.putString(col, row, text);
        } catch (IOException ignored) {
        }
    }
    
    @Override
    public void drawString(String text, Color color) {
        try {
            textGraphics.setBackgroundColor(BACKGROUND_TEXTCOLOR);
            textGraphics.setForegroundColor(toTextColor(color));
            textGraphics.putString(terminal.getCursorPosition(), text);
        } catch (IOException ignored) {}
    }
    
    @Override
    public void drawString(String text, Color color, Color background) {
        try {
            textGraphics.setBackgroundColor(toTextColor(background));
            textGraphics.setForegroundColor(toTextColor(color));
            textGraphics.putString(terminal.getCursorPosition(), text);
        } catch (IOException ignored) {}
    }
    
    @Override
    public void drawString(int row, int col, String text, Color color, Color background) {
        textGraphics.setBackgroundColor(toTextColor(background));
        textGraphics.setForegroundColor(toTextColor(color));
        textGraphics.putString(col, row, text);
    }
    
    @Override
    public void nextLine() {
        try {
            terminal.setCursorPosition(0, terminal.getCursorPosition().getRow() + 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int width() {
        return WIDTH;
    }
    
    @Override
    public int height() {
        return HEIGHT;
    }

    private class RenderTask implements Runnable {
        @Override
        public void run() {
            try {
                long time = 0L;
                while (scene.isRunning()) {
                    try {
                        Thread.sleep(50);

                        KeyStroke keyStroke = screen.pollInput();
                        while (keyStroke != null) {
                            if (keyStroke.getKeyType().equals(KeyType.EOF)) {
                                exit(0);
                            }

                            scene.submit(CommandMapper.map(keyStroke));
                            keyStroke = screen.pollInput();
                        }

                        scene.tick(time++);
                        render();
                        screen.refresh();
                    } catch (InterruptedException ignored) {}
                }
                screen.stopScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void render() {
        try {
            this.terminal.clearScreen();
            textGraphics = terminal.newTextGraphics();
            textGraphics.setBackgroundColor(new TextColor.RGB(255,255,255));
            scene.draw(this);
            this.terminal.flush();
        } catch (IOException ignored) {}
    }

    private TextColor toTextColor(Color color) {
        return new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue());
    }
}
