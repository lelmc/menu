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

public class util {
    //返回一个物品
    public static ItemStack item(String itemType, String disName){
        String replace = disName.replace("&", "§");
        ItemType value = ItemTypes.registry().value(ResourceKey.resolve(itemType));
        ItemStack itemStack = ItemStack.of(value);
        itemStack.offer(Keys.CUSTOM_NAME, Component.text(replace));
        List<Component> A = new ArrayList<>();
        A.add(Component.text("§d服务器菜单"));
        A.add(Component.text("§d22222222"));
        itemStack.offer(Keys.LORE, A);
        return itemStack;
    }

    //返回一个照片
    public static ItemStack sprite(String form, int ndex, String disName){
        String replace = disName.replace("&", "§");
        ItemType value = ItemTypes.registry().value(ResourceKey.resolve("pixelmon:pixelmon_sprite"));
        ItemStack of = ItemStack.of(value);
        //{form:"",gender:1b,ndex:731s,palette:"none"}
        DataContainer dataContainer = of.toContainer()
                .set(DataQuery.of("UnsafeData", "form"), form)
                .set(DataQuery.of("UnsafeData", "ndex"), (short)ndex)
                .set(DataQuery.of("UnsafeData", "palette"), "none");
        ItemStack build = ItemStack.builder().fromContainer(dataContainer).build();
        build.offer(Keys.CUSTOM_NAME, Component.text(replace));
        return build;
    }
}
