package net.krinsoft.autorepair.listeners;

import net.krinsoft.autorepair.util.Parser;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author krinsdeath
 */
@SuppressWarnings("unused")
public class BlockListener implements Listener {

    //@EventHandler
    public void blockBreak(BlockBreakEvent event) {
        // make sure the event isn't canceled
        if (event.isCancelled()) { return; }
        // make sure the block was broken by a player
        if (event.getPlayer() == null) { return; }
        Player player = event.getPlayer();
        Material material = player.getItemInHand().getType();
        // parse the tool and material type into usable strings
        String type = Parser.getType(material.name());
        String tool = Parser.getTool(material.name());
        // check if the player can autorepair his current tool
        if (tool != null && type != null && player.hasPermission("autorepair." + type + "." + tool)) {
            // set the durability to 0; full tool health
            player.getItemInHand().setDurability((short) 0);
        }
    }

}
