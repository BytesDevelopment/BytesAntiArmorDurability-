package net.sixy.bytesdevelopment.antiarmoroverlay.Utils;

import org.bukkit.plugin.java.JavaPlugin;

public class StartupMessage {
    public static void send(JavaPlugin plugin) {
        plugin.getServer().getConsoleSender().sendMessage("§a――――――――――――――――――――――――――――――――――――――");
        plugin.getServer().getConsoleSender().sendMessage("§a    [BytesDevelopment] - iSixy");
        plugin.getServer().getConsoleSender().sendMessage("§a    [ArmorDurabilityFix] Plugin iniciado correctamente");
        plugin.getServer().getConsoleSender().sendMessage("§a    - Author: iSixy");
        plugin.getServer().getConsoleSender().sendMessage("§a――――――――――――――――――――――――――――――――――――――");
    }
}

