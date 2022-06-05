package cn.lelmc.menu;

import cn.lelmc.menu.menus.MainMenu;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.event.lifecycle.RefreshGameEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

@Plugin("lel-menu")

public class LelMenu {
    public PluginContainer container;
    @Inject private Injector injector;

    @Inject
    LelMenu(final PluginContainer container) {
        this.container = container;
    }

    @Listener
    public void onRefreshGame(RefreshGameEvent event) throws ConfigurateException {
        injector.getInstance(MainMenu.class).load();
    }

    @Listener
    public void UseItem(InteractItemEvent.Secondary event,
                        @First(typeFilter = ServerPlayer.class) ServerPlayer player) {
        ItemType type = event.itemStack().type();
        if (type.equals(ItemTypes.CLOCK.get())) {
            injector.getInstance(MainMenu.class).open(player);
        }
    }
}