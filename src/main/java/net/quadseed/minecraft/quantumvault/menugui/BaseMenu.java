package net.quadseed.minecraft.quantumvault.menugui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class BaseMenu implements InventoryHolder {

    protected Inventory inventory;

    protected MenuUtility menuUtility;

    public BaseMenu(MenuUtility menuUtility) {
        this.menuUtility = menuUtility;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void InventoryClickHandler(InventoryClickEvent event);

    public abstract void InventoryCloseHandler(InventoryCloseEvent event);

    public abstract void setItems();

    public abstract void saveItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());
        this.setItems();

        menuUtility.getOwner().openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
