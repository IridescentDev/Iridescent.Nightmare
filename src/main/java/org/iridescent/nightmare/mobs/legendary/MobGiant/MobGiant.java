package org.iridescent.nightmare.mobs.legendary.MobGiant;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.iridescent.nightmare.Until;
import org.iridescent.nightmare.mobs.AbstractMob;
import org.iridescent.nightmare.tables.SpecialMobType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MobGiant extends AbstractMob {
    public MobGiant(Location spawnLoc) {
        super(spawnLoc);

    }

    @Override
    protected @NotNull LivingEntity getEntity(@NotNull Location spawnLoc) {
        CraftWorld craftWorld = ((CraftWorld) spawnLoc.getWorld());
        entity = craftWorld.addEntity(new EntityGiant(spawnLoc), CreatureSpawnEvent.SpawnReason.NATURAL);
        entity.setAI(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1000);
        entity.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(40);
        entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(100);
        entity.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(10);
        entity.setHealth(1000);
        return entity;
    }

    @Override
    public List<Until.Pair<ItemStack, Float>> getLoots() {
        List<Until.Pair<ItemStack, Float>> lootList = new ArrayList<>();
        lootList.add(new Until.Pair<>(
                new Until.IItemStack(Material.ROTTEN_FLESH)
                        .setDisplayName(getType().getRarity().getColor() + "Giant's Rotten Flesh")
                        .setLore(new String[]{ChatColor.GRAY + "A huge piece of rotten flesh, emitting a rotten smell."})
                        .setPersistentData(LOOT_KEY, PersistentDataType.STRING, this.getType().getRegName())
                        .build(), 50.0f)
        );
        return lootList;
    }

    @Override
    public @NotNull SpecialMobType getType() {
        return SpecialMobType.GIANT;
    }

    @Override
    protected BukkitRunnable tick() {
        final int[] tickCount = {0};
        return new BukkitRunnable() {
            @Override
            public void run() {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 1));
                if (entity.getFireTicks() >= 0) entity.setFireTicks(0);
                if (entity.getFreezeTicks() >= 0) entity.setFreezeTicks(0);
            }
        };
    }
}
