package net.sixy.bytesdevelopment.antiarmoroverlay;

import net.sixy.bytesdevelopment.antiarmoroverlay.Utils.Handler.PacketHandler;
import net.sixy.bytesdevelopment.antiarmoroverlay.Utils.StartupMessage;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiArmorOverlay extends JavaPlugin {
    private ProtocolManager protocolManager;
    private PacketHandler packetHandler;

    @Override
    public void onEnable() {
        if (!setupProtocolLib()) {
            getLogger().severe("ProtocolLib no encontrado! Desactivando plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.packetHandler = new PacketHandler(this, protocolManager);
        packetHandler.setupPacketListeners();
        StartupMessage.send(this);
    }

    private boolean setupProtocolLib() {
        try {
            this.protocolManager = ProtocolLibrary.getProtocolManager();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}