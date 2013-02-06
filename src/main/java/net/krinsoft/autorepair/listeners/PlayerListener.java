package net.krinsoft.autorepair.listeners;

import net.krinsoft.autorepair.util.Parser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author krinsdeath
 */
@SuppressWarnings("unused")
public class PlayerListener implements Listener {

    //@EventHandler
    void playerInteract(PlayerInteractEvent event) {
        // make sure the event isn't canceled
        if (event.isCancelled()) { return; }
        // make sure the player triggered the event successfully
        if (event.getPlayer() == null) { return; }
        Player player = event.getPlayer();
        // check that the player is carrying a proper tool and type for his permissions
        if (player.getItemInHand() != null) {
            String tool = Parser.getTool(player.getItemInHand().getType().name());
            String type = Parser.getType(player.getItemInHand().getType().name());
            if (tool != null && type != null && player.hasPermission("autorepair." + type + "." + tool)) {
                player.getItemInHand().setDurability((short) 0);
            }
        }
    }

    @EventHandler
    void itemBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        if (player == null) { return; }
        ItemStack item = event.getBrokenItem();
        if (item == null) { return; }
        String tool = Parser.getTool(item.getType().name());
        String type = Parser.getType(item.getType().name());
        if (tool != null && type != null && player.hasPermission("autorepair." + type + "." + tool)) {
            item.setDurability((short) 0);
            item.setAmount(item.getAmount() + 1);
            player.updateInventory();
        }
    }

}
