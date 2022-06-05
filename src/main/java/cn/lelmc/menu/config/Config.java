package cn.lelmc.menu.config;

import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public class Config {

    @Comment("主菜单配置文件")
    public settings settings;

    public Config(){
        settings = new settings();
    }

    @ConfigSerializable
    public static class settings {
        @Comment("插槽")
        public Map<String, ItemStack> slot = new HashMap<String, ItemStack>() {{
            put("Slot1", ItemStack.of(ItemTypes.CLOCK));
        }};
    }

}