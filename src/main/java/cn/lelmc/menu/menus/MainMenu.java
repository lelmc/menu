package cn.lelmc.menu.menus;

import cn.lelmc.menu.PixelmonItems;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.plugin.PluginContainer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class MainMenu extends ConfigurableMenu {

    @Inject
    public MainMenu(@ConfigDir(sharedRoot = false) Path configDir, PluginContainer plugin) throws IOException {
        super(configDir.resolve("main.conf"), plugin);
    }

    @Override
    protected List<ItemStack> items() {
        List<ItemStack> list = new ArrayList<>(6 * 9);
        for (int i = 0; i < 6 * 9; i++) {
            list.add(ItemStack.builder()
                    .itemType(ItemTypes.WHITE_STAINED_GLASS_PANE)
                    .add(Keys.CUSTOM_NAME, Component.text("乐联欢迎您"))
                    .build());
        }
        for (int i = 0; i < 6 * 9; i++) {
            if (i == 0 || i == 8 || i == 45 || i == 53) {
                list.set(i, ItemStack.builder()
                        .itemType(ItemTypes.ORANGE_STAINED_GLASS_PANE)
                        .add(Keys.CUSTOM_NAME, Component.text("乐联欢迎您"))
                        .build());
            }
            if (i == 10) {
                list.set(i, ItemStack.builder()
                        .itemType(ItemTypes.BEACON)
                        .add(Keys.CUSTOM_NAME, Component.text("主城")
                                .color(NamedTextColor.GREEN)
                                .decorate(TextDecoration.BOLD))
                        .build());
            } else if (i == 12) {
//                list.set(i, PixelmonItems.sprite(ItemStack.builder()
//                        .itemType(PixelmonItems.SPRITE)
//                        .add(Keys.CUSTOM_NAME, Component.text("精灵世界")
//                                .color(NamedTextColor.YELLOW)
//                                .decorate(TextDecoration.BOLD))
//                        .build(), 756));
            } else if (i == 14) {
                list.set(i, ItemStack.builder()
                        .itemType(ItemTypes.SOUL_SAND)
                        .add(Keys.CUSTOM_NAME, Component.text("地狱")
                                .color(NamedTextColor.DARK_RED)
                                .decorate(TextDecoration.BOLD))
                        .build());
            } else if (i == 16) {
                list.set(i, ItemStack.builder()
                        .itemType(ItemTypes.DRAGON_EGG)
                        .add(Keys.CUSTOM_NAME, Component.text("末地")
                                .color(NamedTextColor.LIGHT_PURPLE)
                                .decorate(TextDecoration.BOLD))
                        .build());
            } else if (i == 20) {
//                list.set(i, ItemStack.builder()
//                        .itemType(PixelmonItems.POKE_BALL)
//                        .add(Keys.CUSTOM_NAME, Component.text("精灵数据")
//                                .color(NamedTextColor.GREEN)
//                        ).build());
            } else if (i == 22) {
                list.set(i, ItemStack.builder()
                        .itemType(ItemTypes.ENDER_CHEST)
                        .add(Keys.CUSTOM_NAME, Component.text("末影箱")
                                .color(NamedTextColor.LIGHT_PURPLE)
                                .decorate(TextDecoration.BOLD))
                        .build());
//            } else if (i == 24) {
//                list.set(i, item("pixelmon:marsh_badge", "&e点卷商店"));
//            } else if (i == 27) {
//                list.set(i, item("pixelmon:cobble_badge", "&e我的领地"));
//            } else if (i == 29) {
//                list.set(i, item("pixelmon:ilex_shrine", "&e我的HOME"));
//            } else if (i == 31) {
//                list.set(i, item("minecraft:chest", "&e玩家商店"));
//            } else if (i == 33) {
//                list.set(i, item("pixelmon:gift_box", "&d礼包"));
//            } else if (i == 35) {
//                list.set(i, item("pixelmon:pixelmon_spawner", "&d刷率参考"));
//            } else if (i == 37) {
//                list.set(i, item("pixelmon:rare_candy", "&c可梦刷机"));
//            } else if (i == 39) {
//                list.set(i, item("pixelmon:lemonade", "&e可梦刷努力"));
//            } else if (i == 41) {
//                list.set(i, item("minecraft:grass", "&2地皮世界"));
//            } else if (i == 43) {
//                list.set(i, item("minecraft:pink_bed", "&2回地皮"));
            }
        }
        return list;
    }

    @Override
    protected Map<Integer, String> commands() {
        HashMap<Integer, String> map = Maps.newHashMap();
        map.put(10, "warp zc");
        map.put(12, "warp poke");
        map.put(14, "warp dy");
        return map;
    }
}
