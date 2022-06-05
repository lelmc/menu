package cn.lelmc.menu.menus;

import net.kyori.adventure.text.Component;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Cause;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.ContainerTypes;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.menu.ClickType;
import org.spongepowered.api.item.inventory.menu.InventoryMenu;
import org.spongepowered.api.item.inventory.menu.handler.SlotClickHandler;
import org.spongepowered.api.item.inventory.type.ViewableInventory;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.plugin.PluginContainer;

import static cn.lelmc.menu.util.item;
import static cn.lelmc.menu.util.sprite;

public class lel {

    public static void doFancyStuff(PluginContainer plugin, Player player) {
        ViewableInventory inventory = ViewableInventory.builder().type(ContainerTypes.GENERIC_9X6.get())
                .completeStructure()
                .carrier(player)
                .plugin(plugin)
                .build();
        InventoryMenu menu = inventory.asMenu();
        menu.setReadOnly(true);
        menu.setTitle(Component.text("§d服务器菜单"));
        for (int i = 0; i < 54; i++) {
            inventory.set(i, item("white_stained_glass_pane", "乐联欢迎您"));
            if (i == 0 || i == 8 || i == 45 || i == 53) {
                inventory.set(i, item("orange_stained_glass_pane", "乐联欢迎您"));
            }
            if (i == 10){
                inventory.set(i, item("minecraft:beacon", "&4主城"));
            }
            if (i == 12){
                inventory.set(i, sprite("", 756, "&e精灵世界"));
            }
            if (i == 14){
                inventory.set(i, item("minecraft:diamond", "&5地狱"));
            }
            if (i == 16){
                inventory.set(i, item("dragon_egg", "&5末地"));
            }
            if (i == 20){
                inventory.set(i, item("pixelmon:poke_ball", "&a精灵数据"));
            }
            if (i == 22){
                inventory.set(i, item("minecraft:ender_chest", "&e末影箱"));
            }
            if (i == 24){
                inventory.set(i, item("pixelmon:marsh_badge", "&e点卷商店"));
            }
            if (i == 27){
                inventory.set(i, item("pixelmon:cobble_badge", "&e我的领地"));
            }
            if (i == 29){
                inventory.set(i, item("pixelmon:ilex_shrine", "&e我的HOME"));
            }
            if (i == 31){
                inventory.set(i, item("minecraft:chest", "&e玩家商店"));
            }
            if (i == 33){
                inventory.set(i, item("pixelmon:gift_box", "&d礼包"));
            }
            if (i == 35){
                inventory.set(i, item("pixelmon:pixelmon_spawner", "&d刷率参考"));
            }
            if (i == 37){
                inventory.set(i, item("pixelmon:rare_candy", "&c可梦刷机"));
            }
            if (i == 39){
                inventory.set(i, item("pixelmon:lemonade", "&e可梦刷努力"));
            }
            if (i == 41){
                inventory.set(i, item("minecraft:grass", "&2地皮世界"));
            }
            if (i == 43){
                inventory.set(i, item("minecraft:pink_bed", "&2回地皮"));
            }
        }

        menu.registerSlotClick(new MySlotClickHandler(plugin, menu, inventory, (ServerPlayer) player));
        Sponge.server().scheduler().submit(Task.builder().plugin(plugin).execute(() -> {
            menu.open((ServerPlayer) player);
        }).build());
    }

    //点击处理
    private static class MySlotClickHandler implements SlotClickHandler {
        private final InventoryMenu menu;
        private final PluginContainer plugin;
        private final ViewableInventory primary;
        private ViewableInventory last;
        private final ServerPlayer player;

        public MySlotClickHandler(PluginContainer plugin, final InventoryMenu menu, final ViewableInventory primary, ServerPlayer player) {
            this.plugin = plugin;
            this.primary = primary;
            this.menu = menu;
            this.player = player;
        }

        @Override
        public boolean handle(final Cause cause, final Container container, final Slot slot, final int slotIndex, final ClickType<?> clickType) {
            if (slot.viewedSlot().parent() == this.primary) {
                switch (slotIndex) {
                    case 10:
                        comm(player, "warp zc");
                        player.closeInventory();
                        break;
                    case 12:
                        comm(player, "warp poke");
                        player.closeInventory();
                        break;
                    case 14:
                        comm(player, "warp dy");
                        player.closeInventory();
                        break;
                    case 16:
                        comm(player, "warp md");
                        player.closeInventory();
                        break;
                    case 20:
                        comm(player, "help");
                        player.closeInventory();
                        break;
                    case 22:
                        comm(player, "enderchest");
                        break;
                    case 24:
                        comm(player, "help ");
                        player.closeInventory();
                        break;
                    case 27:
                        comm(player, "gd");
                        break;
                    case 29:
                        comm(player, "homes");
                        player.closeInventory();
                        break;
                    case 31:
                        comm(player, "gts");
                        player.closeInventory();
                        break;
                    case 33:
                        comm(player, "kit");
                        player.closeInventory();
                        break;
                    case 35:
                        comm(player, "checkspawns legendary");
                        player.closeInventory();
                        break;
                    case 39:
                        comm(player, "warp evs");
                        player.closeInventory();
                        break;
                    case 41:
                        comm(player, "warp dp");
                        player.closeInventory();
                        break;
                    case 43:
                        comm(player, "p h");
                        player.closeInventory();
                        break;
                }
                return false;
            } else if (slot.viewedSlot().parent() == this.last) {
                Sponge.server().scheduler().submit(Task.builder().execute(() -> this.menu.setCurrentInventory(this.primary)).plugin(this.plugin).build());
                return false;
            }
            return true;
        }
    }

    public static void comm(ServerPlayer player, String comm) {
        try {
            Sponge.server().commandManager().process(player, comm);
        } catch (CommandException ignored) {
        }
    }
}
