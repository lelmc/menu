package cn.lelmc.menu;

import cn.lelmc.menu.config.ConfigLoader;
import cn.lelmc.menu.menus.lel;
import com.google.inject.Inject;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import java.util.Objects;

@Plugin("menu")

public class menu {

    public PluginContainer container;

    @Inject
    menu(final PluginContainer container, final ConfigLoader config) {
        this.container = container;
        Objects.requireNonNull(config);
    }

    @Listener
    public void UseItem(InteractItemEvent.Secondary event) {//right click
        ItemType type = event.itemStack().type();
        if (type.equals(ItemTypes.CLOCK.get())) {
            ServerPlayer player = (ServerPlayer) event.source();
            lel.doFancyStuff(this.container, player);
        }
    }
}