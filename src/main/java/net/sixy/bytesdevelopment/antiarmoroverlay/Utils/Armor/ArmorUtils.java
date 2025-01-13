package net.sixy.bytesdevelopment.antiarmoroverlay.Utils.Armor;

import org.bukkit.inventory.ItemStack;

public class ArmorUtils {
    public static boolean isArmor(ItemStack item) {
        if (item == null) return false;
        String name = item.getType().name();
        return name.endsWith("_HELMET") ||
                name.endsWith("_CHESTPLATE") ||
                name.endsWith("_LEGGINGS") ||
                name.endsWith("_BOOTS");
    }
}
