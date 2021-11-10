package net.quadseed.minecraft.quantumvault.utils;

import net.quadseed.minecraft.quantumvault.QuantumVault;
import net.quadseed.minecraft.itemcontainerapi.ItemContainerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class VaultUtils {

    public static void openVault(Player player) {
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), player);
        ArrayList<ItemStack> vaultItems = container.getItems("vault");

        Inventory vault = Bukkit.createInventory(player, 54, ChatColor.DARK_PURPLE + "プライベート倉庫");
        vaultItems.forEach(vault::addItem);

        player.openInventory(vault);
    }

}
