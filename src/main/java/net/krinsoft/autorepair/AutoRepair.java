package net.krinsoft.autorepair;

import net.krinsoft.autorepair.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * AutoRepair
 * @version 1.0
 * @author krinsdeath
 */
public class AutoRepair extends JavaPlugin {

    private PlayerListener pListener;

    @Override
    public void onEnable() {
        long startup = System.currentTimeMillis();
        // register event listeners...
        registerListeners();
        // register the actual events
        registerEvents();

        // let's tell everyone we're enabled D:
        log("Enabled successfully in " + (System.currentTimeMillis() - startup) + "ms");
    }

    @Override
    public void onDisable() {
        // clean up the variables
        pListener = null;
        log("Disabled successfully.");
    }

    private void registerListeners() {
        this.pListener = new PlayerListener();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(pListener, this);
    }

    public void log(String message) {
        getLogger().info(message);
    }

}
