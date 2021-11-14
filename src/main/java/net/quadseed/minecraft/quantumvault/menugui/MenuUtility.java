package net.quadseed.minecraft.quantumvault.menugui;

import org.bukkit.entity.Player;

public class MenuUtility {

    private Player owner;

    public MenuUtility(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
