package com.github.coryrobertson.temporaryhardcore;

import com.github.coryrobertson.temporaryhardcore.listener.DieEventListener;
import com.github.coryrobertson.temporaryhardcore.listener.PlayerJoinEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class TemporaryHardcore extends JavaPlugin {

    public static PlayerStatesSave playerStatesSave;

    public static String DEATH_MESSAGE = "You have died!!!";

    public static String KICK_MESSAGE = " died.";

    public static int BAN_LENGTH_MILIS = 15000;

    public static int FUNNY_CHANCE = 5;

    public static FileConfiguration configuration;
//    public static File configurationFile;

    @Override
    public void onEnable()
    {

        //config stuff
        configuration = this.getConfig();
        configuration.addDefault("enable-lightning", false);
        configuration.addDefault("disable-for-ops", false);
        configuration.addDefault("ban-duration", 15000);
        configuration.addDefault("spawn-enemy-on-death", false);
        configuration.addDefault("change-death-message", false);
        configuration.addDefault("kick-message", "You died...");
        configuration.addDefault("death-message", " died.");
        configuration.addDefault("funny-chance", 5);
        this.saveDefaultConfig();
        FUNNY_CHANCE = configuration.getInt("funny-chance");
        KICK_MESSAGE = configuration.getString("kick-message");
        DEATH_MESSAGE = configuration.getString("death-message");
        BAN_LENGTH_MILIS = configuration.getInt("ban-duration");


        log("ban-duration: " + BAN_LENGTH_MILIS);
        playerStatesSave = new PlayerStatesSave();
        getServer().getPluginManager().registerEvents(new DieEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);

    }

    public static void log(String s)
    {
        Bukkit.getLogger().info(s);
    }

//    private void createConfig()
//    {
//        configurationFile = new File(getDataFolder(), "cfg.yml");
//        if(!configurationFile.exists())
//        {
//            configurationFile.getParentFile().mkdirs();
//            saveResource("cfg.yml", false);
//        }
//
//        configuration = new YamlConfiguration();
//        try
//        {
//            configuration.load(configurationFile);
//
//        } catch (InvalidConfigurationException | IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onDisable() {
//        Bukkit.getLogger().info(ChatColor.RED + "------------------- Disabled " + this.getName());
        playerStatesSave.saveRecords();
    }




}
