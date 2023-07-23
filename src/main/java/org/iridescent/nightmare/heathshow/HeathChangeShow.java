package org.iridescent.nightmare.heathshow;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static org.iridescent.nightmare.IridescentNightmare.getThisPlugin;
import static org.iridescent.nightmare.IridescentNightmare.random;

public class HeathChangeShow {
    private final ArmorStand changeShow;
    public HeathChangeShow (double heathChange, @NotNull Location at) {
        heathChange = (double) Math.round(heathChange * 100) / 100;
        changeShow = (ArmorStand) at.getWorld().spawnEntity(new Location(at.getWorld(),
                at.getX() + random.nextDouble(-1, 1),
                at.getY() + random.nextDouble(-0.5, 1),
                at.getZ() + random.nextDouble(-1, 1)), EntityType.ARMOR_STAND);
        changeShow.setVelocity(at.toVector().subtract(changeShow.getLocation().toVector()).normalize());
        changeShow.setInvulnerable(true);
        changeShow.setMarker(true);
        changeShow.setGravity(false);
        changeShow.setVisible(false);
        changeShow.setSmall(true);
        changeShow.setCustomNameVisible(true);

        if (heathChange > 0)
            changeShow.setCustomName(ChatColor.DARK_GREEN + String.valueOf(heathChange));
        else changeShow.setCustomName(ChatColor.DARK_RED + String.valueOf(heathChange));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!changeShow.isDead())
                    changeShow.remove();
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                if (!changeShow.isDead()) changeShow.remove();
                super.cancel();
            }
        }.runTaskLater(getThisPlugin(), 20 * 1);
    }
}
