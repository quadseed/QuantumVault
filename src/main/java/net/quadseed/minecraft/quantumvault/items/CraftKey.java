package net.quadseed.minecraft.quantumvault.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftKey {

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "賢者の石");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "遠隔でクラフト可能");
        lore.add(ChatColor.LIGHT_PURPLE + "右クリックで展開");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

    public static ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("craft_key"), getItem());
        recipe.shape(
                " E ",
                " C ",
                " T "
        );
        recipe.setIngredient('C', Material.CRAFTING_TABLE);
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('T', Material.ENCHANTING_TABLE);

        return recipe;
    }

}
