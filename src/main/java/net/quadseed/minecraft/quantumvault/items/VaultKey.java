package net.quadseed.minecraft.quantumvault.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VaultKey {

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "ストレージキー");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "プライベート倉庫にアクセスするための鍵");
        lore.add(ChatColor.LIGHT_PURPLE + "右クリックでプライベート倉庫を展開");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("vault_key"), getItem());
        recipe.shape(
                "ODO",
                "DED",
                "ODO"
        );
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('E', Material.END_CRYSTAL);
        recipe.setIngredient('O', Material.OBSIDIAN);

        return recipe;
    }
}
