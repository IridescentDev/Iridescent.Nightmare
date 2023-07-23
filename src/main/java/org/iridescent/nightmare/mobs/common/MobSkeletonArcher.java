package org.iridescent.nightmare.mobs.common;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.iridescent.nightmare.Until;
import org.iridescent.nightmare.mobs.AbstractMob;
import org.iridescent.nightmare.tables.SpecialMobType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MobSkeletonArcher extends AbstractMob {
    public MobSkeletonArcher(Location spawnLoc) {
        super(spawnLoc);
    }

    @Override
    protected @NotNull LivingEntity getEntity(@NotNull Location spawnLoc) {
        Skeleton skeleton = (Skeleton) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.SKELETON);
        skeleton.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        skeleton.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        skeleton.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        skeleton.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        skeleton.getEquipment().setItemInMainHand(loots.get(0).first);
        return skeleton;
    }

    @Override
    public List<Until.Pair<ItemStack, Float>> getLoots() {
        List<Until.Pair<ItemStack, Float>> lootList = new ArrayList<>();
        lootList.add(new Until.Pair<>(
                new Until.IItemStack(Material.BOW)
                        .setDisplayName(getType().getRarity().getColor() + "Skeleton Archer's Bow")
                        .setLore(new String[]{ChatColor.GRAY + "A worn-out bow, but the bowstring remains remarkably resilient."})
                        .addEnchantment(Enchantment.ARROW_DAMAGE, 2)
                        .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        .setPersistentData(LOOT_KEY, PersistentDataType.STRING, this.getType().getRegName())
                        .build(), 10.0f)
        );
        return lootList;
    }

    @Override
    public @NotNull SpecialMobType getType() {
        return SpecialMobType.SKELETON_ARCHER;
    }

    @Override
    protected BukkitRunnable tick() {
        return null;
    }
}
