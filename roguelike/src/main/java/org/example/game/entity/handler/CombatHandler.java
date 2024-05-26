package org.example.game.entity.handler;


import org.example.game.entity.Entity;
import org.example.game.entity.Player;
import org.example.game.entity.mob.Mob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to process fighting events
 */
public class CombatHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CombatHandler.class);

    public static void fight(Entity a, Entity b) {
        LOGGER.debug("Combat: {}(hp={}, strength={}) vs {}(hp={}, strength={})",
                a.getId(), a.getHp(), a.getStrength(), b.getId(), b.getHp(), b.getStrength());

        if (a.getClass().equals(b.getClass())) {
            return;
        }

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


        if (a.isDead() && a instanceof Mob) {
            ((Mob) a).getMobState().getStrategy().getLevel().removeMob((Mob) a);
        }

        if (b.isDead() && b instanceof Mob) {
            ((Mob) b).getMobState().getStrategy().getLevel().removeMob((Mob) b);
        }

        if (player == null)
            return;
        
        XPHandler.addXP(player, mob);

    }
}
