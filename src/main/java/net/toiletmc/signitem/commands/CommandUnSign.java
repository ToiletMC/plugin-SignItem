package net.toiletmc.signitem.commands;

import net.toiletmc.signitem.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandUnSign implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {//TODO 取消签名还没做完，也不打算公布。
            if (!commandSender.hasPermission("signitem.command.unsign")) {
                commandSender.sendMessage(ChatColor.RED + "未知的命令");
            }

            Player player = (Player) commandSender;
            ItemStack items = player.getInventory().getItemInMainHand();

            if (items.getType() == Material.AIR) {
                player.sendMessage(ChatColor.RED + "请将需要取消签名的物品拿在手中！");
                return true;
            }

            if (Util.isSigned(items)) {
                commandSender.sendMessage(ChatColor.RED + "取消签名失败，因为物品没有任何签名！");
                return true;
            }

            Util.removeSign(items);
            commandSender.sendMessage("取消签名成功！");
            return true;

        } else {
            commandSender.sendMessage(ChatColor.RED + "别闹，取消签名指令只能被玩家执行。");
            return true;
        }
    }
}
