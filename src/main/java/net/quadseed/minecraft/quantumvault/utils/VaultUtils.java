package net.quadseed.minecraft.quantumvault.utils;

import net.quadseed.minecraft.quantumvault.menugui.MenuUtility;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VaultUtils {

    private static final HashMap<Player, MenuUtility> menuUtilityMap = new HashMap<>();

    public static MenuUtility getMenuUtility(Player player) {
        MenuUtility utility;

        if (menuUtilityMap.containsKey(player)) {
            return menuUtilityMap.get(player);
        } else {
            utility = new MenuUtility(player);
            menuUtilityMap.put(player, utility);
            return utility;
        }
    }

}
