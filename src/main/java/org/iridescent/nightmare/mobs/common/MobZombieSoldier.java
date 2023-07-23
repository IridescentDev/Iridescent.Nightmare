package org.iridescent.nightmare.mobs.common;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.iridescent.nightmare.Until;
import org.iridescent.nightmare.Until.IItemStack;
import org.iridescent.nightmare.mobs.AbstractMob;
import org.iridescent.nightmare.tables.SpecialMobType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MobZombieSoldier extends AbstractMob {
    public MobZombieSoldier(Location spawnLoc) {
        super(spawnLoc);
    }

    @Override
    @NotNull
    protected LivingEntity getEntity(Location spawnLoc) {
        Zombie entity = (Zombie) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.ZOMBIE);
        entity.setCanBreakDoors(true);
        entity.setAdult();
        entity.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        entity.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        entity.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        entity.getEquipment().setItemInMainHand(loots.get(0).first);
        return entity;
    }

    @Override
    public SpecialMobType getType() {
        return SpecialMobType.ZOMBIE_SOLDIER;
    }

    @Override
    protected BukkitRunnable tick() {
        return null;
    }

    @Override
    public List<Until.Pair<ItemStack, Float>> getLoots() {
        List<Until.Pair<ItemStack, Float>> lootList = new ArrayList<>();
        lootList.add(new Until.Pair<>(
                new IItemStack(Material.IRON_SWORD)
                        .setDisplayName(getType().getRarity().getColor() + "Zombie Soldier's Sword")
                        .setLore(new String[]{ChatColor.GRAY + "A tattered sword with bloodstains on it"})
                        .addEnchantment(Enchantment.DAMAGE_ALL, 2)
                        .setPersistentData(LOOT_KEY, PersistentDataType.STRING, this.getType().getRegName())
                        .build(), 5.0f)
        );
        return lootList;
    }
}
