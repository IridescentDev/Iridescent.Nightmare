package org.iridescent.nightmare.mobs;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.iridescent.nightmare.Until;
import org.iridescent.nightmare.Until.Pair;
import org.iridescent.nightmare.tables.SpecialMobType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.iridescent.nightmare.IridescentNightmare.PLUGIN_NAME;
import static org.iridescent.nightmare.IridescentNightmare.getThisPlugin;

public abstract class AbstractMob {
    protected LivingEntity entity;
    protected SpecialMobType type;
    protected BukkitRunnable tick;
    protected BukkitRunnable livingTick;
    protected Location lastLoc;
    protected List<Pair<ItemStack, Float>> loots;

    public static NamespacedKey LOOT_KEY = new NamespacedKey(getThisPlugin(),"nightmare_loot");

    public AbstractMob (Location spawnLoc) {
        this.loots = getLoots();
        this.entity = getEntity(spawnLoc);
        this.initEntity();
        this.type = getType();
        this.tick = tick();
        this.livingTick = livingTick();
        this.lastLoc = entity.getLocation();
        this.entity.setCustomName(this.getType().getRarity().getColor() + getType().getName());
        this.entity.setCustomNameVisible(true);

        this.livingTick.runTaskTimer(getThisPlugin(), 20, 1);
        if (tick != null)
            this.tick.runTaskTimer(getThisPlugin(), 20, 1);
    }

    @NotNull
    protected abstract LivingEntity getEntity(@NotNull Location spawnLoc);
    public abstract List<Pair<ItemStack, Float>> getLoots();

    @NotNull
    public abstract SpecialMobType getType ();
    protected abstract BukkitRunnable tick ();

    public ItemStack[] getLootsArray() {
        ItemStack[] itemStacks = new ItemStack[loots.size() + 1];
        for (int i = 0; i < loots.size(); i++) {
            itemStacks[i] = loots.get(i).first;
        }
        return itemStacks;
    }

    public LivingEntity getMob() {
        return this.entity;
    }

    private void initEntity () {
        try { entity.getEquipment().setHelmetDropChance(0); } catch (NullPointerException ignored) {}
        try { entity.getEquipment().setChestplateDropChance(0); } catch (NullPointerException ignored) {}
        try { entity.getEquipment().setLeggingsDropChance(0); } catch (NullPointerException ignored) {}
        try { entity.getEquipment().setBootsDropChance(0); } catch (NullPointerException ignored) {}
        try { entity.getEquipment().setItemInMainHandDropChance(0); } catch (NullPointerException ignored) {}
        try { entity.getEquipment().setItemInOffHandDropChance(0); } catch (NullPointerException ignored) {}
    }
    @NotNull
    private BukkitRunnable livingTick() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    if(tick != null) tick.cancel();
                    if (loots != null) {
                        for (Pair<ItemStack, Float> loot : loots) {
                            if (Until.rollChance(loot.second)) {
                                //System.out.println("Dropped!!!");
                                Item item = (Item) lastLoc.getWorld().spawnEntity(lastLoc, EntityType.DROPPED_ITEM);
                                item.setItemStack(loot.first);
                            }
                        }
                    }
                    livingTick.cancel();
                } else if (entity == null) {
                    if (tick != null) tick.cancel();
                    livingTick.cancel();
                }
                lastLoc = entity.getLocation();
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                if (!entity.isDead()) entity.setHealth(0);
                super.cancel();
            }
        };
    }
}
