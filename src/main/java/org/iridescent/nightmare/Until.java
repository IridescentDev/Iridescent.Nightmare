package org.iridescent.nightmare;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Arrays;

import static org.iridescent.nightmare.IridescentNightmare.random;

public class Until {
    public enum Rarity {
        COMMON(ChatColor.WHITE),
        UNCOMMON(ChatColor.GREEN),
        RARE(ChatColor.BLUE),
        EPIC(ChatColor.DARK_PURPLE),
        LEGENDARY(ChatColor.GOLD),
        MYTHIC(ChatColor.LIGHT_PURPLE),
        ULTIMATE(ChatColor.AQUA);

        private final ChatColor color;
        Rarity(ChatColor color) {
            this.color = color;
        }

        public ChatColor getColor() {
            return color;
        }
    }

    public static class Pair <F, S> {
        public F first;
        public S second;
        public Pair (F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public static boolean rollChance(float percent) {
        return random.nextFloat(100) < percent;
    }

    public static class IItemStack {
        private ItemStack itemStack;
        public IItemStack (Material material) {
            this.itemStack = new ItemStack(material);
        }

        public IItemStack (Material material, int amount) {
            this.itemStack = new ItemStack(material, amount);
        }

        public IItemStack addEnchantment (Enchantment ench, int level) {
            itemStack.addEnchantment(ench, level);
            return this;
        }

        public IItemStack setDisplayName (@NotNull String name) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);
            return this;
        }

        public IItemStack setLore (@NotNull String[] lore) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(Arrays.stream(lore).toList());
            itemStack.setItemMeta(itemMeta);
            return this;
        }

        public IItemStack setPersistentData (@NotNull NamespacedKey key, PersistentDataType type, Object content) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            container.set(key, type, content);
            itemStack.setItemMeta(itemMeta);
            return this;
        }

        public ItemStack build() {
            return itemStack;
        }
    }

    //public static boolean chanceGen ()
}
