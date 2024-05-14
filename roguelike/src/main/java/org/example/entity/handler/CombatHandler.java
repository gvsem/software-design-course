package org.example.entity.handler;


import org.example.entity.Entity;
import org.example.entity.Player;
import org.example.entity.mob.Mob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CombatHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CombatHandler.class);
    
    
    public static void fight(Entity a, Entity b) {
        LOGGER.debug("Combat: {}(hp={}, strength={}) vs {}(hp={}, strength={})",
                a.getId(), a.getHp(), a.getStrength(), b.getId(), b.getHp(), b.getStrength());
        
        if (a.isDead() || b.isDead())
            return;
        
        a.hit(b.getStrength());
        b.hit(a.getStrength());
        
        Player player = null;
        Mob mob = null;
        if ("player".equals(a.getId())) {
            player = (Player) a;
            mob = (Mob) b;
        }
        else if ("player".equals(b.getId())) {
            player = (Player) b;
            mob = (Mob) a;
        }
        
        if (player == null)
            return;
        
        XPHandler.addXP(player, mob);
    }
}
