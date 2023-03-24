package net.toiletmc.signitem.commands;

import net.toiletmc.signitem.SignItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CommandSign implements CommandExecutor {
    private SignItem plugin;

    public CommandSign(SignItem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;

                ItemStack items = player.getInventory().getItemInMainHand();

                if (items.getType() == Material.AIR) {
                    player.sendMessage(ChatColor.RED + "请将需要签名的物品拿在手中！");
                    return true;
                }

                NamespacedKey key = new NamespacedKey(plugin, "signitem");

                ItemMeta itemMeta = items.getItemMeta();
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                if (dataContainer.has(key)) {
                    commandSender.sendMessage(ChatColor.RED + "签名失败，因为物品已被签名！");
                    return true;
                }
                dataContainer.set(key, PersistentDataType.BYTE, (byte) 1);
                items.setItemMeta(itemMeta);
                return true;

            } else {
                commandSender.sendMessage(ChatColor.RED + "别闹，签名指令只能被玩家执行。");
                return true;
            }
        } else {
            switch (strings[0]) {
                default:
                    return true;//TODO 其它命令待完成
            }
        }

    }
}
