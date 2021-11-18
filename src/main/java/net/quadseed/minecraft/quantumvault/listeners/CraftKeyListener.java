package net.quadseed.minecraft.quantumvault.listeners;

import net.quadseed.minecraft.quantumvault.items.CraftKey;
import net.quadseed.minecraft.quantumvault.items.IntegratedKey;
import net.quadseed.minecraft.quantumvault.items.QuantumKey;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftKeyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().discoverRecipe(NamespacedKey.minecraft("craft_key"));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        boolean isCraftKey = item.isSimilar(CraftKey.getItem());
        boolean isIntegratedKey = item.isSimilar(IntegratedKey.getItem()) && !player.isSneaking();
        boolean isQuantumKey = item.isSimilar(QuantumKey.getItem()) && !player.isSneaking();


        if (!isCraftKey && !isIntegratedKey && !isQuantumKey) {
            return;
        }

        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            event.setCancelled(true);
            return;
        }

        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (!Objects.requireNonNull(event.getClickedBlock()).getType().isInteractable()) {
                player.openWorkbench(null, true);
                event.setCancelled(true);
            }
            return;
        }

        if (action.equals(Action.RIGHT_CLICK_AIR)) {
            player.openWorkbench(null, true);
        }
    }

}
