package net.toiletmc.signitem;

import net.toiletmc.signitem.commands.CommandSign;
import org.bukkit.plugin.java.JavaPlugin;

public final class SignItem extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("sign").setExecutor(new CommandSign(this));
    }

    @Override
    public void onDisable() {

    }
}
