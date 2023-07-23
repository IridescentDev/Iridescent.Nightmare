package org.iridescent.nightmare.mobs.common;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
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

public class MobSpiderAssassin extends AbstractMob {
    public MobSpiderAssassin(Location spawnLoc) {
        super(spawnLoc);
    }

    @Override
    protected @NotNull LivingEntity getEntity(@NotNull Location spawnLoc) {
        Spider spider = (Spider) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.SPIDER);
        spider.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(4);
        return spider;
    }

    @Override
    public List<Until.Pair<ItemStack, Float>> getLoots() {
        List<Until.Pair<ItemStack, Float>> lootList = new ArrayList<>();
        lootList.add(new Until.Pair<>(
                new Until.IItemStack(Material.STRING)
                        .setDisplayName(getType().getRarity().getColor() + "Spider Assassin's String")
                        .setLore(new String[]{ChatColor.GRAY + "A tough thread. Should be good for bows."})
                        .setPersistentData(LOOT_KEY, PersistentDataType.STRING, this.getType().getRegName())
                        .build(), 10.0f)

        );
        lootList.add(new Until.Pair<>(
                new Until.IItemStack(Material.SPIDER_EYE)
                        .setDisplayName(getType().getRarity().getColor() + "Spider Assassin's Eye")
                        .setLore(new String[]{ChatColor.GRAY + "The spider assassin's eyes, revealing a hint of sharp."})
                        .setPersistentData(LOOT_KEY, PersistentDataType.STRING, this.getType().getRegName())
                        .build(), 10.0f)

        );
        return lootList;
    }

    @Override
    public @NotNull SpecialMobType getType() {
        return SpecialMobType.SPIDER_ASSASSIN;
    }

    @Override
    protected BukkitRunnable tick() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1, 1));
            }
        };
    }
}
