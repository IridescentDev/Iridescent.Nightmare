package org.iridescent.nightmare.mobs.uncommon;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.iridescent.nightmare.Until;
import org.iridescent.nightmare.mobs.AbstractMob;
import org.iridescent.nightmare.tables.SpecialMobType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MobLightingCreeper extends AbstractMob {
    public MobLightingCreeper(Location spawnLoc) {
        super(spawnLoc);
    }

    @Override
    protected @NotNull LivingEntity getEntity(@NotNull Location spawnLoc) {
        Creeper creeper = (Creeper) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.CREEPER);
        creeper.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(4);
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
        creeper.setPowered(true);
        creeper.setExplosionRadius(2);
        return creeper;
    }

    @Override
    public List<Until.Pair<ItemStack, Float>> getLoots() {
        return null;
    }

    @Override
    public @NotNull SpecialMobType getType() {
        return SpecialMobType.LIGHTNING_CREEPER;
    }

    @Override
    protected BukkitRunnable tick() {
        return null;
    }
}
