package net.krinsoft.autorepair.listeners;

import net.krinsoft.autorepair.util.Parser;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 *
 * @author krinsdeath
 */
@SuppressWarnings("unused")
public class EntityListener implements Listener {

    @EventHandler
    void entityDamage(EntityDamageEvent event) {
        // make sure the event isn't canceled
        if (event.isCancelled()) { return; }
        // make sure the event was triggered by a player
        if (!(event.getEntity() instanceof Player)) { return; }
        Player player = (Player) event.getEntity();
        Material armor = null;
        String type = null, tool = null;
        // check for a helmet, and repair it if possible
        if (player.getInventory().getHelmet() != null) {
            armor = player.getInventory().getHelmet().getType();
            type = Parser.getType(armor.name());
            if (player.hasPermission("autorepair." + type + ".helmet")) {
                player.getInventory().getHelmet().setDurability((short) 0);
            }
        }
        // check for a chestplate, and repair it if possible
        if (player.getInventory().getChestplate() != null) {
            armor = player.getInventory().getChestplate().getType();
            type = Parser.getType(armor.name());
            if (player.hasPermission("autorepair." + type + ".chestplate")) {
                player.getInventory().getChestplate().setDurability((short) 0);
            }
        }
        // check for leggings, and repair them if possible
        if (player.getInventory().getLeggings() != null) {
            armor = player.getInventory().getLeggings().getType();
            type = Parser.getType(armor.name());
            if (player.hasPermission("autorepair." + type + ".leggings")) {
                player.getInventory().getLeggings().setDurability((short) 0);
            }
        }
        // check for boots, and repair them if possible
        if (player.getInventory().getBoots() != null) {
            armor = player.getInventory().getBoots().getType();
            type = Parser.getType(armor.name());
            if (player.hasPermission("autorepair." + type + ".boots")) {
                player.getInventory().getBoots().setDurability((short) 0);
            }
        }
        // check if this damage event was an attack by another entity
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
            // check if the triggerer was a player, and make sure his hands aren't empty
            if (e.getDamager() instanceof Player && ((Player) e.getDamager()).getItemInHand() != null) {
                Material weapon = ((Player) e.getDamager()).getItemInHand().getType();
                // parse the tool and type into usable strings
                type = Parser.getType(weapon.name());
                tool = Parser.getTool(weapon.name());
                // check for a repairable tool, and whether the player has permission to auto repair it
                if (type != null && tool != null && player.hasPermission("autorepair." + type + "." + tool)) {
                    ((Player) e.getDamager()).getItemInHand().setDurability((short) 0);
                }
            }
        }
    }

}
