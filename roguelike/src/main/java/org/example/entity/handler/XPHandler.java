package org.example.entity.handler;


import org.example.entity.Player;
import org.example.entity.mob.Mob;


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
