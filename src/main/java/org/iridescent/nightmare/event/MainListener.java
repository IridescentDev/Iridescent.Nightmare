package org.iridescent.nightmare.event;

import net.minecraft.world.entity.monster.EntityGiantZombie;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGiant;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.iridescent.nightmare.heathshow.HeathBar;
import org.iridescent.nightmare.tables.SpecialMobType;

import static org.iridescent.nightmare.Until.rollChance;
import static org.iridescent.nightmare.heathshow.HeathBar.getEntityTag;

public class MainListener implements Listener {
    @EventHandler
    public void creatureSpawnListener (CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Monster)) return;
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND) ||
            event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER)) return;
        if (rollChance(SpecialMobType.ZOMBIE_SOLDIER.getChance())) return;

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getCustomName() == null) return;
        if (!event.getEntity().getCustomName().isBlank()) {
            event.getEntity().setCustomName(null);
            event.getEntity().setCustomNameVisible(false);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;
        if (getEntityTag(event.getEntity()) == null || getEntityTag(event.getEntity()) == "null")
            new HeathBar((LivingEntity) event.getEntity());
        if (!(event.getEntity() instanceof Giant)) return;
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            event.setCancelled(true);
    }

}
