package com.github.coryrobertson.temporaryhardcore;

import org.bukkit.Bukkit;
import java.util.ArrayList;

public class PlayerStatesSave
{
    public ArrayList<PlayerRecord> playerRecords;

    public static final String SAVE_NAME = "./playerRecords.sav";

    public PlayerStatesSave()
    {

        playerRecords = new ArrayList<>();

    }

    public void addPlayerDeath(PlayerRecord playerRecord)
    {
        Bukkit.getLogger().info("All player deaths: =========================");

        for (var player: playerRecords)
        {
            Bukkit.getLogger().info(String.valueOf(player));
        }
        Bukkit.getLogger().info("=========================");

        for (int i = 0; i < playerRecords.size(); i++) {
            PlayerRecord player = playerRecords.get(i);
            if (player.name().equals(playerRecord.name()))
            {
                //if the player being added to the recordlist already has an entry
                playerRecords.set(i, playerRecord); // just update their entry, else add it
                Bukkit.getLogger().info("updated new player to playerrecords: " + playerRecord);
//                saveRecords();
                return;
            }
        }
        Bukkit.getLogger().info("added new player to playerrecords: " + playerRecord);
        playerRecords.add(playerRecord);
//        saveRecords();
    }

    /**
     * Currently unused
     */
    public void saveRecords()
    {
//        SerializableSave serializableSave = new SerializableSave<>(playerRecords);
//        Serializer.save(serializableSave, SAVE_NAME);
    }

}
