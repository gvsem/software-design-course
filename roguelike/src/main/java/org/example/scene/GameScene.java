package org.example.scene;


import lombok.Getter;
import org.example.Event;
import org.example.GameContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameScene {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Getter
    private boolean running = false;
    private boolean aboutToQuit = false;
    
    
    public GameScene(GameContext game) {
        this.running = true;
    }
    
    
    public void submitEvent(Event event) {
        LOGGER.debug("Got event: {}", event);
        
        if (aboutToQuit) {
            if (event == Event.QUIT)
                running = false;
            else
                aboutToQuit = false;
        }
        
        if (event == null)
            return;
        
        switch (event) {
            case QUIT -> aboutToQuit = true;
            default -> {
            }
        }
    }
}
