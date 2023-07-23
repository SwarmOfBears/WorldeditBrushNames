package me.swarmofbears.worldeditbrushnames;

import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldeditBrushNames extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new CommandListener(this.getLogger()), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
