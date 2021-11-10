package net.quadseed.minecraft.quantumvault.listeners;

import net.quadseed.minecraft.quantumvault.QuantumVault;
import net.quadseed.minecraft.quantumvault.items.IntegratedKey;
import net.quadseed.minecraft.quantumvault.items.VaultKey;
import net.quadseed.minecraft.quantumvault.utils.VaultUtils;
import net.quadseed.minecraft.itemcontainerapi.ItemContainerManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class VaultKeyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), player);
        container.registerNameSpace("vault");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        boolean isVaultKey = item.equals(VaultKey.getItem());
        boolean isIntegratedKey = item.equals(IntegratedKey.getItem()) && player.isSneaking();

        if (!isVaultKey && !isIntegratedKey) {
            return;
        }

        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            event.setCancelled(true);
            return;
        }

        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (!Objects.requireNonNull(event.getClickedBlock()).getType().isInteractable()) {
                VaultUtils.openVault(player);
                event.setCancelled(true);
            }
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR) {
            VaultUtils.openVault(player);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_PURPLE + "プライベート倉庫")) {
            ItemStack item = event.getCurrentItem();

            if (item == null) {
                return;
            }

            boolean isVaultKey = item.equals(VaultKey.getItem());
            boolean isIntegratedKey = item.equals(IntegratedKey.getItem());

            if (isVaultKey || isIntegratedKey) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
                player.sendMessage(ChatColor.RED + "ストレージキーは収納できません");
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_PURPLE + "プライベート倉庫")) {

            ArrayList<ItemStack> prunedItems = new ArrayList<>();

            Arrays.stream(event.getInventory().getContents())
                    .filter(Objects::nonNull)
                    .forEach(prunedItems::add);

            ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), player);
            container.storeItems("vault", prunedItems);
        }
    }
}
