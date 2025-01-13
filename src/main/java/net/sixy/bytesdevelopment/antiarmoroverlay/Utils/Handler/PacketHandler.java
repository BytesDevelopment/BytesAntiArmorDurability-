package net.sixy.bytesdevelopment.antiarmoroverlay.Utils.Handler;

import net.sixy.bytesdevelopment.antiarmoroverlay.Utils.Armor.ArmorUtils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketHandler {
    private final JavaPlugin plugin;
    private final ProtocolManager protocolManager;

    public PacketHandler(JavaPlugin plugin, ProtocolManager protocolManager) {
        this.plugin = plugin;
        this.protocolManager = protocolManager;
    }

    public void setupPacketListeners() {
        setupEquipmentListener();
        setupWindowItemsListener();
    }

    private void setupEquipmentListener() {
        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.ENTITY_EQUIPMENT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Server.ENTITY_EQUIPMENT) {
                    PacketContainer packet = event.getPacket();
                    ItemStack item = packet.getItemModifier().read(0);

                    if (item != null && ArmorUtils.isArmor(item)) {
                        PacketContainer clonedPacket = packet.deepClone();
                        ItemStack clonedItem = item.clone();

                        clonedItem.setDurability((short) 0);
                        clonedPacket.getItemModifier().write(0, clonedItem);

                        event.setPacket(clonedPacket);
                    }
                }
            }
        });
    }

    private void setupWindowItemsListener() {
        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.WINDOW_ITEMS) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Server.WINDOW_ITEMS) {
                    PacketContainer packet = event.getPacket();
                    ItemStack[] items = packet.getItemArrayModifier().read(0);

                    boolean modified = false;
                    ItemStack[] clonedItems = new ItemStack[items.length];

                    for (int i = 0; i < items.length; i++) {
                        if (items[i] != null && ArmorUtils.isArmor(items[i])) {
                            clonedItems[i] = items[i].clone();
                            clonedItems[i].setDurability((short) 0);
                            modified = true;
                        } else {
                            clonedItems[i] = items[i];
                        }
                    }

                    if (modified) {
                        PacketContainer clonedPacket = packet.deepClone();
                        clonedPacket.getItemArrayModifier().write(0, clonedItems);
                        event.setPacket(clonedPacket);
                    }
                }
            }
        });
    }
}
