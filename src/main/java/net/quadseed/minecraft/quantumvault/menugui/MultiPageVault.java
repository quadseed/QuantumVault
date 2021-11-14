package net.quadseed.minecraft.quantumvault.menugui;

import net.quadseed.minecraft.itemcontainerapi.ItemContainerManager;
import net.quadseed.minecraft.quantumvault.QuantumVault;
import net.quadseed.minecraft.quantumvault.items.QuantumKey;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiPageVault extends BaseMenu {

    protected int page = 1;

    public MultiPageVault(MenuUtility menuUtility) {
        super(menuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.BLUE + "マルチページ倉庫";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void InventoryClickHandler(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        int index = event.getSlot();
        if (44 < index && index < 54) {
            event.setCancelled(true);
        }
        if (index == 48) {
            if (page == 1) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
                player.sendMessage( ChatColor.RED+ "最初のページです");
            } else {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                this.saveItems();
                page -= 1;
                this.setItems();
            }
        } else if (index == 50) {
            if (page == 5) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
                player.sendMessage( ChatColor.RED+ "最後のページです");
            } else {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                this.saveItems();
                page += 1;
                this.setItems();
            }
        }

        ItemStack item = event.getCurrentItem();

        if (item == null) {
            return;
        }

        if (item.equals(QuantumKey.getItem())) {
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
            player.sendMessage(ChatColor.RED + "マルチファンクションキーは収納できません");
        }
    }

    @Override
    public void InventoryCloseHandler(InventoryCloseEvent event) {
        this.saveItems();
    }

    @Override
    public void setItems() {
        setControlSection();
        Player player = menuUtility.getOwner();
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), player);
        ArrayList<ItemStack> vaultItems = container.getItems(String.valueOf(page));
        for (int i = 0; i < vaultItems.size(); i++) {
            inventory.setItem(i, vaultItems.get(i));
        }
    }

    @Override
    public void saveItems() {
        ItemContainerManager container = new ItemContainerManager(QuantumVault.getPlugin(), menuUtility.getOwner());

        List<ItemStack> content = new ArrayList<>(Arrays.asList(inventory.getStorageContents()));
        content.subList(45, 54).clear();
        container.storeItems(String.valueOf(page), content);
    }

    public void setControlSection() {
        ItemStack spacer = new ItemStack(Material.BARRIER);
        ItemMeta meta = spacer.getItemMeta();
        meta.setDisplayName(" ");
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spacer.setItemMeta(meta);
        for (int i = 45; i < 48; i++) {
            inventory.setItem(i, spacer);
        }
        for (int i = 51; i < 54; i++) {
            inventory.setItem(i, spacer);
        }

        ItemStack left = new ItemStack(Material.STONE_BUTTON);
        ItemMeta leftMeta = left.getItemMeta();
        leftMeta.setDisplayName(ChatColor.DARK_PURPLE + "前のページ");
        leftMeta.addEnchant(Enchantment.LUCK, 1, true);
        leftMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        left.setItemMeta(leftMeta);
        inventory.setItem(48, left);

        ItemStack pageIndex = new ItemStack(Material.MAP, page);
        ItemMeta meta2 = pageIndex.getItemMeta();
        meta2.setDisplayName(ChatColor.DARK_GREEN + "ページ" + page);
        meta2.addEnchant(Enchantment.LUCK, 1, true);
        meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pageIndex.setItemMeta(meta2);
        inventory.setItem(49, pageIndex);

        ItemStack right = new ItemStack(Material.STONE_BUTTON);
        ItemMeta rightMeta = right.getItemMeta();
        rightMeta.setDisplayName(ChatColor.DARK_PURPLE + "次のページ");
        rightMeta.addEnchant(Enchantment.LUCK, 1, true);
        rightMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        right.setItemMeta(rightMeta);
        inventory.setItem(50, right);

    }
}
