package com.github.coryrobertson.temporaryhardcore.listener;

import com.github.coryrobertson.temporaryhardcore.PlayerRecord;
import com.github.coryrobertson.temporaryhardcore.TemporaryHardcore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Date;

public class PlayerJoinEventListener implements Listener
{

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e)
    {

        ArrayList<PlayerRecord> list = TemporaryHardcore.playerStatesSave.playerRecords;
        Date currentTime = new Date();

        for (int i = 0; i < list.size() ; i++)
        {

            PlayerRecord player = list.get(i);

            if(e.getPlayer().getPlayerListName().equals(player.name()))
            {
                long timeOfDeath = player.timeOfDeath().getTime();
                long currentTimeMilis = currentTime.getTime();
                if(Math.abs(currentTimeMilis - timeOfDeath) < TemporaryHardcore.BAN_LENGTH_MILIS)
                {
                    e.getPlayer().kickPlayer(TemporaryHardcore.DEATH_MESSAGE);
                    Bukkit.broadcastMessage(player.name() + " tried to connect, but was dead. " + player.timeOfDeath());
                }

                Bukkit.getLogger().info("==============player joined who was on the list!!!!");
                Bukkit.getLogger().info(String.valueOf(player));

            }
        }
    }
}
