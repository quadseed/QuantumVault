package net.quadseed.minecraft.quantumvault.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class QuantumKey {

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "マルチファンクションキー");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "右クリックでクラフト可能");
        lore.add(ChatColor.LIGHT_PURPLE + "スニーク + 右クリックでマルチページ倉庫を展開");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("quantum_key"), getItem());
        recipe.shape(
                "EEE",
                "EIE",
                "EEE"
        );
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(IntegratedKey.getItem()));
        recipe.setIngredient('E', Material.ENDER_CHEST);

        return recipe;
    }
}
