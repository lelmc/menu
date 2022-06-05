package cn.lelmc.menu.config;

import org.spongepowered.api.item.inventory.ItemStackSnapshot;

public class menus {
    private ItemStackSnapshot item;

    public menus(ItemStackSnapshot item) {
        this.item = item;
    }

    public ItemStackSnapshot getItem() {
        return item;
    }

    public void setItem(ItemStackSnapshot item) {
        this.item = item;
    }
}
