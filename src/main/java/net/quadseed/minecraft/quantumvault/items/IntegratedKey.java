package net.quadseed.minecraft.quantumvault.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class IntegratedKey {

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "賢者の鍵");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.MAGIC + "IntegratedKey");
        lore.add(ChatColor.LIGHT_PURPLE + "右クリックでクラフト可能");
        lore.add(ChatColor.LIGHT_PURPLE + "スニーク + 右クリックで倉庫を展開");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapelessRecipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("integrated_key"), getItem());

        recipe.addIngredient(new RecipeChoice.ExactChoice(CraftKey.getItem()));
        recipe.addIngredient(new RecipeChoice.ExactChoice(VaultKey.getItem()));

        return recipe;
    }
}
