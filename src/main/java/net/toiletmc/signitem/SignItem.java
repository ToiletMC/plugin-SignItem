package net.toiletmc.signitem;

import net.toiletmc.signitem.commands.CommandSign;
import org.bukkit.plugin.java.JavaPlugin;

public final class SignItem extends JavaPlugin {
    private static SignItem instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("sign").setExecutor(new CommandSign(this));
    }

    @Override
    public void onDisable() {

    }

    public static SignItem getInstance() {
        return instance;
    }
}
