package com.github.coryrobertson.temporaryhardcore;

import com.github.coryrobertson.temporaryhardcore.listener.DieEventListener;
import com.github.coryrobertson.temporaryhardcore.listener.PlayerJoinEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class TemporaryHardcore extends JavaPlugin {

    public static PlayerStatesSave playerStatesSave;

    public static final String DEATH_MESSAGE = "You have died!!!";

    public static final int BAN_LENGTH_MILIS = 15000;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info(ChatColor.GREEN + "------------------ Enabled " + this.getName());
        playerStatesSave = new PlayerStatesSave();
        getServer().getPluginManager().registerEvents(new DieEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(ChatColor.RED + "------------------- Disabled " + this.getName());
        playerStatesSave.saveRecords();
    }




}
