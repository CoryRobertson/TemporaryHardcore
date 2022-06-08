package com.github.coryrobertson.temporaryhardcore;

import com.github.coryrobertson.temporaryhardcore.listener.DieEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class TemporaryHardcore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info(ChatColor.GREEN + "------------------ Enabled " + this.getName());
        getServer().getPluginManager().registerEvents(new DieEventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(ChatColor.RED + "------------------- Disabled " + this.getName());

    }




}
