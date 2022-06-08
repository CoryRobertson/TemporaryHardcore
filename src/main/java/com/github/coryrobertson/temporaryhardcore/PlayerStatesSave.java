package com.github.coryrobertson.temporaryhardcore;

import com.github.coryrobertson.simplesaver.Serializer;
import org.bukkit.Bukkit;

import java.io.Serial;
import java.util.ArrayList;

public class PlayerStatesSave
{
    ArrayList<PlayerRecord> playerRecords;

    public static final String SAVE_NAME = "./playerRecords.sav";

    public PlayerStatesSave()
    {
        if (Serializer.saveExists(SAVE_NAME))
        {

        }
        else
        {
            playerRecords = new ArrayList<>();
        }
    }

    public void addPlayerDeath(PlayerRecord playerRecord)
    {
        for (int i = 0; i < playerRecords.size(); i++) {
            PlayerRecord player = playerRecords.get(i);
            if (player.name().equals(playerRecord.name()))
            {
                //if the player being added to the recordlist already has an entry
                playerRecords.set(i, playerRecord); // just update their entry, else add it
                Bukkit.getLogger().info("updates new player to playerrecords: " + playerRecord);
                return;
            }
        }
        Bukkit.getLogger().info("added new player to playerrecords: " + playerRecord);
        playerRecords.add(playerRecord);
    }

    public void saveRecords()
    {
        Serializer.save(playerRecords, SAVE_NAME);
    }

}
