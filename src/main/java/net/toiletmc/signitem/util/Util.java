package net.toiletmc.signitem.util;

import net.toiletmc.signitem.SignItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Util {
    public static boolean isSigned(ItemStack items) {
        ItemMeta itemMeta = items.getItemMeta();
        return itemMeta.getPersistentDataContainer().has(getSignerKey());
    }

    public static void addSign(ItemStack items, UUID signer) {
        ItemMeta itemMeta = items.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        dataContainer.set(getSignerKey(), PersistentDataType.STRING, signer.toString());
        items.setItemMeta(itemMeta);
    }

    public static void removeSign(ItemStack items) {
        ItemMeta itemMeta = items.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        dataContainer.remove(getSignerKey());
        items.setItemMeta(itemMeta);
    }

    public static NamespacedKey getSignerKey() {
        return new NamespacedKey(SignItem.getIntense(), "signer");
    }
}
