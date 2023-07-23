package org.iridescent.nightmare.heathshow;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static org.iridescent.nightmare.IridescentNightmare.getThisPlugin;

public class HeathBar {
    private final LivingEntity entity;
    private final ArmorStand heathBar;
    private final BukkitRunnable runnable;

    public HeathBar (@NotNull LivingEntity entity){
        this.entity = entity;
        heathBar = (ArmorStand) entity.getWorld().spawnEntity(entity.getEyeLocation(), EntityType.ARMOR_STAND);
        heathBar.setInvulnerable(true);
        heathBar.setMarker(true);
        heathBar.setGravity(false);
        heathBar.setVisible(false);
        heathBar.setSmall(false);
        heathBar.setCustomNameVisible(true);
        setEntityTag(entity, "SHOWING");

        this.runnable = new BukkitRunnable() {
            long tickCount = 0;
            double lastHeath = 0;
            @Override
            public void run() {
                double thisHeath = entity.getHealth();
                showHeath(thisHeath);
                entity.addPassenger(heathBar);

                if (entity.isDead()) stopShow();
                if (thisHeath - lastHeath != 0 && lastHeath != 0)
                    new HeathChangeShow(thisHeath - lastHeath, entity.getEyeLocation());

                if (thisHeath == lastHeath)
                    tickCount ++;
                else tickCount = 0;
                if (tickCount >= 20 * 10)
                    stopShow();
                lastHeath = thisHeath;
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                heathBar.remove();
                setEntityTag(entity, "null");
                super.cancel();
            }
        };
        runnable.runTaskTimer(getThisPlugin(), 0, 1);
    }

    public void stopShow() {
        runnable.cancel();
    }

    private void showHeath(double entityHealth) {
        double maxHeath = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        String heathString = String.valueOf((double) Math.round(entityHealth * 100) / 100);
        if (entityHealth > (maxHeath / 2)) {
            heathString = ChatColor.DARK_GREEN + heathString;
        } else if (entityHealth > (maxHeath / 5)) {
            heathString = ChatColor.GOLD + heathString;
        } else {
            heathString = ChatColor.RED + heathString;
        }
        heathBar.setCustomName(heathString + "/" + ChatColor.DARK_GREEN + (int) maxHeath);
    }

    public static void setEntityTag(Entity entity, String tagName) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(getThisPlugin(), "TagKey");
        dataContainer.set(key, PersistentDataType.STRING, tagName);
    }

    public static String getEntityTag(Entity entity) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(getThisPlugin(), "TagKey");
        return dataContainer.get(key, PersistentDataType.STRING);
    }
}
