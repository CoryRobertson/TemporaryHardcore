package com.github.coryrobertson.temporaryhardcore.listener;

import com.github.coryrobertson.temporaryhardcore.PlayerRecord;
import com.github.coryrobertson.temporaryhardcore.TemporaryHardcore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Date;

public class DieEventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {

        e.setDeathMessage(e.getEntity().getPlayerListName() + " has died lmao.");
//        e.setNewLevel(50);
        e.getEntity().chat("Arg, im an idiot");
//        e.getEntity().spawnParticle();
//        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
        //TODO: do something cool when someone dies
        TemporaryHardcore.playerStatesSave.addPlayerDeath(new PlayerRecord(e.getEntity().getPlayerListName(), new Date()));
        e.getEntity().getPlayer().kickPlayer(TemporaryHardcore.DEATH_MESSAGE);

    }
}
