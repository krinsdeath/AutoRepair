package net.krinsoft.autorepair;

import net.krinsoft.autorepair.listeners.BlockListener;
import net.krinsoft.autorepair.listeners.EntityListener;
import net.krinsoft.autorepair.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * AutoRepair
 * @version 1.0
 * @author krinsdeath
 */
public class AutoRepair extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("AutoRepair");

    private PlayerListener pListener;
    private BlockListener bListener;
    private EntityListener eListener;

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
        eListener = null;
        bListener = null;
        log("Disabled successfully.");
    }

    private void registerListeners() {
        this.pListener = new PlayerListener();
        this.bListener = new BlockListener();
        this.eListener = new EntityListener();
    }

    private void registerEvents() {
        // get the plugin manager instance
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(bListener, this);
        pm.registerEvents(pListener, this);
        pm.registerEvents(eListener, this);

    }

    public void log(String message) {
        message = "[" + this + "] " + message;
        LOGGER.info(message);
    }

}
