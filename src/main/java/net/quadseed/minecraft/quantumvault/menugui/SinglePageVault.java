package net.quadseed.minecraft.quantumvault.menugui;

import net.quadseed.minecraft.itemcontainerapi.ItemContainerManager;
import net.quadseed.minecraft.quantumvault.QuantumVault;
import net.quadseed.minecraft.quantumvault.items.IntegratedKey;
import net.quadseed.minecraft.quantumvault.items.VaultKey;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class SinglePageVault extends BaseMenu {

    public SinglePageVault(MenuUtility menuUtility) {
        super(menuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_PURPLE + "プライベート倉庫";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void InventoryClickHandler(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
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

    @Override
    public void InventoryCloseHandler(InventoryCloseEvent event) {
        this.saveItems();
    }

    @Override
    public void setItems() {
        Player player = menuUtility.getOwner();
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), player);
        ArrayList<ItemStack> vaultItems = container.getItems("vault");
        for (int i = 0; i < vaultItems.size(); i++) {
            inventory.setItem(i, vaultItems.get(i));
        }
    }

    @Override
    public void saveItems() {
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), menuUtility.getOwner());
        container.storeItems("vault", new ArrayList<>(Arrays.asList(inventory.getStorageContents())));
    }
}
