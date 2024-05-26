package org.example.game.entity.handler;


import org.example.game.entity.Player;
import org.example.game.entity.mob.Mob;

/**
 * Class to process XP events
 */
public class XPHandler {
    public static void addXP(Player player, Mob mob) {
        if (!mob.isDead())
            return;
        
        player.setXp(player.getXp() + mob.getStrength());
        
        int levelDif = xpToLevel(player.getXp()) - player.getLevel();
        player.incLevel(levelDif);
    }
    
    public static int xpToLevel(int xp) {
        return (int) Math.floor(Math.log(xp) / Math.log(2));
    }
}
