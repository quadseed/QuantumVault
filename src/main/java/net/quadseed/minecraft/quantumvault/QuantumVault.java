package net.quadseed.minecraft.quantumvault;

import net.quadseed.minecraft.quantumvault.items.CraftKey;
import net.quadseed.minecraft.quantumvault.items.IntegratedKey;
import net.quadseed.minecraft.quantumvault.items.QuantumKey;
import net.quadseed.minecraft.quantumvault.items.VaultKey;
import net.quadseed.minecraft.quantumvault.listeners.CraftKeyListener;
import net.quadseed.minecraft.quantumvault.listeners.VaultKeyListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class QuantumVault extends JavaPlugin {

    private static QuantumVault plugin;


    @Override
    public void onEnable() {

        plugin = this;

        getServer().getPluginManager().registerEvents(new VaultKeyListener(), this);
        getServer().getPluginManager().registerEvents(new CraftKeyListener(), this);

        Bukkit.getServer().addRecipe(CraftKey.getRecipe());
        Bukkit.getServer().addRecipe(VaultKey.getRecipe());
        Bukkit.getServer().addRecipe(IntegratedKey.getRecipe());
        Bukkit.getServer().addRecipe(QuantumKey.getRecipe());

    }

    public static QuantumVault getPlugin() {
        return plugin;
    }

}
