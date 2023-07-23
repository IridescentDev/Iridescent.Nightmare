package org.iridescent.nightmare.tables;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.iridescent.nightmare.Until.Rarity;
import org.iridescent.nightmare.mobs.common.MobSkeletonArcher;
import org.iridescent.nightmare.mobs.common.MobSpiderAssassin;
import org.iridescent.nightmare.mobs.common.MobZombieSoldier;
import org.iridescent.nightmare.mobs.legendary.MobGiant.MobGiant;
import org.iridescent.nightmare.mobs.uncommon.MobLightingCreeper;
import org.jetbrains.annotations.NotNull;

public enum SpecialMobType {
    //Common
    ZOMBIE_SOLDIER("zombie_soldier", "Zombie Soldier", Rarity.COMMON, 5) {
        @Override
        public LivingEntity spawnEntity(@NotNull Location spawnLoc) {
            return new MobZombieSoldier(spawnLoc).getMob();
        }
    },

    SKELETON_ARCHER("skeleton_archer", "Skeleton Archer", Rarity.COMMON, 5) {
        @Override
        public LivingEntity spawnEntity(@NotNull Location spawnLoc) {
            return new MobSkeletonArcher(spawnLoc).getMob();
        }
    },

    SPIDER_ASSASSIN("spider_assassin", "Spider Assassin", Rarity.COMMON, 5) {
        @Override
        public LivingEntity spawnEntity(@NotNull Location spawnLoc) {
            return new MobSpiderAssassin(spawnLoc).getMob();
        }
    },

    //Uncommon

    LIGHTNING_CREEPER("lightning_creeper", "Lightning Creeper", Rarity.UNCOMMON, 5) {
        @Override
        public LivingEntity spawnEntity(@NotNull Location spawnLoc) {
            return new MobLightingCreeper(spawnLoc).getMob();
        }
    },

    //Rare

    //Epic

    //Legendary
    GIANT("giant", "Giant", Rarity.LEGENDARY, 5) {
        @Override
        public LivingEntity spawnEntity(@NotNull Location spawnLoc) {
            return new MobGiant(spawnLoc).getMob();
        }
    };

    //Mythic

    //Ultimate

    private final String regName;
    private final String name;
    private final Rarity rarity;
    private final float chance;

    SpecialMobType(String regName, String name, Rarity rarity, float chance) {
        this.regName = regName;
        this.name = name;
        this.rarity = rarity;
        this.chance = chance;
    }

    public String getRegName() {
        return regName;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public float getChance() {
        return chance;
    }

    public abstract LivingEntity spawnEntity (@NotNull Location spawnLoc);

}

