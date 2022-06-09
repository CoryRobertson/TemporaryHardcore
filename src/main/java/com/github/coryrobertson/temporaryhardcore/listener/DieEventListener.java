package com.github.coryrobertson.temporaryhardcore.listener;

import com.github.coryrobertson.temporaryhardcore.PlayerRecord;
import com.github.coryrobertson.temporaryhardcore.TemporaryHardcore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DieEventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        Location location = e.getEntity().getLocation();
        String playerName = e.getEntity().getPlayer().getPlayerListName();

        // check for disable for ops config option
        boolean disableForOps = TemporaryHardcore.configuration.getBoolean("disable-for-ops");
        if(e.getEntity().getPlayer().isOp() && disableForOps)
        {
            return;
        }

        // check for change death message config option
        boolean changeDeathMessage = TemporaryHardcore.configuration.getBoolean("change-death-message");
        if(changeDeathMessage)
        {
            e.setDeathMessage(e.getEntity().getPlayerListName() + TemporaryHardcore.DEATH_MESSAGE);
        }

        // check for spawn enemy on death config option
        boolean spawnEnemyOnDeath = TemporaryHardcore.configuration.getBoolean("spawn-enemy-on-death");
        if(spawnEnemyOnDeath)
        {
            Entity entity = e.getEntity().getWorld().spawnEntity(location, EntityType.ZOMBIE, false);
            entity.setCustomName(playerName);
            entity.setCustomNameVisible(true);
            if (entity instanceof LivingEntity)
            {
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
                ((LivingEntity) entity).setHealth(40);
            }
        }

        // check for enable lightning config option
        boolean enableLightning = TemporaryHardcore.configuration.getBoolean("enable-lightning");
        if(enableLightning)
        {
            e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
        }


        // small chance to drop a non-pickup-able bread on death
        int rand = ThreadLocalRandom.current().nextInt(0,100 + 1);
        if(rand < TemporaryHardcore.FUNNY_CHANCE)
        {
            Item is = e.getEntity().getWorld().dropItemNaturally(location, new ItemStack(Material.BREAD));
            is.setPickupDelay(9999);
            is.setTicksLived(4800); // this should make the item die in one minute
        }

        // add new player death state to arraylist
        TemporaryHardcore.playerStatesSave.addPlayerDeath(new PlayerRecord(e.getEntity().getPlayerListName(), new Date()));

        // clear their inventory, for some reason this is needed to prevent the game from duplicating their items
        e.getEntity().getPlayer().getInventory().clear();

        // finally, kick the player from the game
        e.getEntity().getPlayer().kickPlayer(TemporaryHardcore.KICK_MESSAGE);

    }
}
