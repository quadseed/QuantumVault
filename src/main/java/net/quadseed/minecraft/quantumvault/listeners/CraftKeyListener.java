package net.quadseed.minecraft.quantumvault.listeners;

import net.quadseed.minecraft.quantumvault.items.CraftKey;
import net.quadseed.minecraft.quantumvault.items.IntegratedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftKeyListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        boolean isCraftKey = item.equals(CraftKey.getItem());
        boolean isIntegratedKey = item.equals(IntegratedKey.getItem()) && !player.isSneaking();


        if (!isCraftKey && !isIntegratedKey) {
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