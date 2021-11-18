package net.quadseed.minecraft.quantumvault.listeners;

import net.quadseed.minecraft.quantumvault.QuantumVault;
import net.quadseed.minecraft.quantumvault.items.IntegratedKey;
import net.quadseed.minecraft.quantumvault.items.QuantumKey;
import net.quadseed.minecraft.quantumvault.items.VaultKey;
import net.quadseed.minecraft.quantumvault.menugui.BaseMenu;
import net.quadseed.minecraft.quantumvault.menugui.MultiPageVault;
import net.quadseed.minecraft.quantumvault.menugui.SinglePageVault;
import net.quadseed.minecraft.quantumvault.utils.VaultUtils;
import net.quadseed.minecraft.itemcontainerapi.ItemContainerManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


public class VaultKeyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), player);
        container.registerNameSpace("vault");
        for (int i = 1; i < 5; i++) {
            container.registerNameSpace(String.valueOf(i));
        }
        player.discoverRecipe(NamespacedKey.minecraft("vault_key"));
        player.discoverRecipe(NamespacedKey.minecraft("integrated_key"));
        player.discoverRecipe(NamespacedKey.minecraft("quantum_key"));
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getRecipe().getResult().equals(QuantumKey.getItem())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(QuantumVault.getPlugin(), () -> {
                event.getClickedInventory().setItem(5, IntegratedKey.getItem());
            });
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        boolean isVaultKey = item.isSimilar(VaultKey.getItem());
        boolean isIntegratedKey = item.isSimilar(IntegratedKey.getItem()) && player.isSneaking();
        boolean isQuantumKey = item.isSimilar(QuantumKey.getItem()) && player.isSneaking();

        if (!isVaultKey && !isIntegratedKey && !isQuantumKey) {
            return;
        }

        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            event.setCancelled(true);
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
            if (isQuantumKey) {
                MultiPageVault vault = new MultiPageVault(VaultUtils.getMenuUtility(player));
                vault.open();
            } else {
                SinglePageVault vault = new SinglePageVault(VaultUtils.getMenuUtility(player));
                vault.open();
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof BaseMenu) {
            BaseMenu menu = (BaseMenu) holder;
            menu.InventoryClickHandler(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof BaseMenu) {
            BaseMenu menu = (BaseMenu) holder;
            menu.InventoryCloseHandler(event);
        }
    }
}
