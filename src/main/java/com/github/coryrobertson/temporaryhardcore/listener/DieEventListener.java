package com.github.coryrobertson.temporaryhardcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DieEventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {

        e.setDeathMessage(e.getEntity().getPlayerListName() + " has died lmao.");
        e.setNewLevel(50);

    }
}
