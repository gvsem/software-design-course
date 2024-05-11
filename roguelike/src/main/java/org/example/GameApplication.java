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

import org.example.scene.Console;
import org.example.scene.GameScene;
import org.example.util.KeyStrokeToEventMapper;
import org.example.view.Colors;
import org.w3c.dom.Text;

import java.awt.Color;
import java.io.IOException;


public class GameApplication implements Console {
    private GameScene scene = null;
    private Terminal terminal = null;
    private Screen screen = null;
    private TextGraphics textGraphics = null;
    
    private final int WIDTH = 80;
    private final int HEIGHT = 24;

    public GameApplication(GameContext context) {
        scene = new GameScene(context);
        try {
            terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(WIDTH, HEIGHT))
                    .createTerminal();

            terminal.setCursorVisible(false);
            terminal.setBackgroundColor(Colors.WHITE);
            terminal.setForegroundColor(Colors.WHITE);

            screen = new TerminalScreen(terminal);
            screen.startScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(new RenderTask()).start();
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

                            scene.submitEvent(KeyStrokeToEventMapper.map(keyStroke));
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
        } catch (IOException e) {}
    }
    
    public static void main(String[] args) throws IOException {
        GameApplication app = new GameApplication(GameFactory.createBasicGame());
    }
    
    
    @Override
    public void drawEmoji(int row, int col, String emoji) {
        textGraphics.setCharacter(col, row, TextCharacter.fromString(emoji)[0]);
    }
    
    
    @Override
    public void drawEmoji(String text) {
        try {
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 255, 255));
            textGraphics.setForegroundColor(new TextColor.RGB(255, 255, 255));
            textGraphics.setCharacter(terminal.getCursorPosition(), TextCharacter.fromString(text)[0]);
        } catch (IOException e) {}
    }
    
    
    @Override
    public void drawString(int row, int col, String text, Color color) {
        try {
            terminal.setForegroundColor(new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue()));
            textGraphics.putString(col, row, text);
        } catch (IOException e) {
        }
    }
    
    
    @Override
    public void drawString(String text, Color color) {
        try {
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 255, 255));
            textGraphics.setForegroundColor(new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue()));
            textGraphics.putString(terminal.getCursorPosition(), text);
        } catch (IOException e) {}
    }
    
    @Override
    public void drawString(String text, Color color, Color background) {
        try {
            textGraphics.setBackgroundColor(new TextColor.RGB(background.getRed(), background.getGreen(), background.getBlue()));
            textGraphics.setForegroundColor(new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue()));
            textGraphics.putString(terminal.getCursorPosition(), text);
        } catch (IOException e) {}
    }
    
    @Override
    public void drawString(int row, int col, String text, Color color, Color background) {
        try {
            textGraphics.setBackgroundColor(new TextColor.RGB(background.getRed(), background.getGreen(), background.getBlue()));
            terminal.setForegroundColor(new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue()));
            textGraphics.putString(col, row, text);
        } catch (IOException e) {
        }
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
}
