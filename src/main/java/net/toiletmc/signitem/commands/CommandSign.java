package net.toiletmc.signitem.commands;

import net.toiletmc.signitem.SignItem;
import net.toiletmc.signitem.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
                if (!commandSender.hasPermission("signitem.command.sign")) {
                    commandSender.sendMessage(ChatColor.RED + "未知的命令");
                }

                Player player = (Player) commandSender;
                ItemStack items = player.getInventory().getItemInMainHand();

                if (items.getType() == Material.AIR) {
                    player.sendMessage(ChatColor.RED + "请将需要签名的物品拿在手中！");
                    return true;
                }

                if (Util.isSigned(items)) {
                    commandSender.sendMessage(ChatColor.RED + "签名失败，因为物品已被签名！");
                    return true;
                }

                Util.addSign(items, ((Player) commandSender).getUniqueId());
                commandSender.sendMessage("签名成功！");
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
