package cn.lelmc.menu;

import net.kyori.adventure.text.Component;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.persistence.DataContainer;
import org.spongepowered.api.data.persistence.DataQuery;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PixelmonItems {
    public static final ItemType SPRITE = ItemTypes.registry().value(ResourceKey.of("pixelmon","pixelmon_sprite"));
    public static final ItemType POKE_BALL = ItemTypes.registry().value(ResourceKey.of("pixelmon","poke_ball"));

    public static ItemStack sprite(ItemStack itemStack, int ndex) {
        DataContainer dataContainer = itemStack.toContainer()
                .set(DataQuery.of("UnsafeData", "form"), "")
                .set(DataQuery.of("UnsafeData", "ndex"), (short)ndex)
                .set(DataQuery.of("UnsafeData", "palette"), "none");
        return ItemStack.builder().fromContainer(dataContainer).build();
    }
}
