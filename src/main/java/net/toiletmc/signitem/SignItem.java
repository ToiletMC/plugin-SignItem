package net.toiletmc.signitem;

import net.toiletmc.signitem.commands.CommandSign;
import org.bukkit.plugin.java.JavaPlugin;

public final class SignItem extends JavaPlugin {
    private static SignItem intense;

    @Override
    public void onEnable() {
        intense = this;
        getCommand("sign").setExecutor(new CommandSign(this));
    }

    @Override
    public void onDisable() {

    }

    public static SignItem getIntense() {
        return intense;
    }
}
