package cn.lelmc.menu.menus;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.leangen.geantyref.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.item.inventory.ContainerTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.menu.InventoryMenu;
import org.spongepowered.api.item.inventory.type.ViewableInventory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.plugin.PluginContainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurableMenu {
    private final Path configFile;
    private final ViewableInventory inventory;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode node;
    private InventoryMenu menu;
    private Map<Integer, String> commands;

    public ConfigurableMenu(@NotNull Path configPath, PluginContainer plugin)
            throws IOException {

        // create directory
        configFile = configPath;
        if (!Files.exists(configPath.getParent())) {
            Files.createDirectories(configPath.getParent());
        }
        if (!Files.exists(configPath)) {
            Files.createFile(configPath);
        }
        // try load config
        this.inventory =
                ViewableInventory.builder().type(ContainerTypes.GENERIC_9X6.get())
                        .completeStructure()
                        .plugin(plugin)
                        .build();
        this.load();
    }

    public void load() throws ConfigurateException {
        this.loader = HoconConfigurationLoader.builder()
                .defaultOptions(ConfigurationOptions.defaults().serializers(Sponge.configManager().serializers()))
                .path(this.configFile)
                .build();
        this.node = loader.load();
        List<ItemStack> items = this.node.node("Items").virtual() ? items() : this.node.node("Items").getList(ItemStack.class);
        this.commands = this.node.node("Commands").virtual() ? commands() : this.node.node("Commands").get(new TypeToken<Map<Integer, String>>() {
        });
        this.commands = this.commands == null ? commands() : this.commands;
        for (int i = 0; i < items.size(); i++) {
            this.inventory.set(i, items.get(i));
        }
        this.menu = this.inventory.asMenu();
        this.menu.registerSlotClick((cause, container, slot, slotIndex, clickType) -> {
            if (this.commands.containsKey(slotIndex)) {
                try {
                    Sponge.server().commandManager().process(cause.first(ServerPlayer.class).get(), this.commands.get(slotIndex));
                } catch (CommandException e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        });
        save();
    }

    protected Map<Integer, String> commands() {
        return Maps.newHashMap();
    }

    protected List<ItemStack> items() {
        return Lists.newArrayList();
    }

    public void open(ServerPlayer player) {
        this.menu.open(player);
    }

    public void save() throws ConfigurateException {
        List<ItemStack> items = this.inventory.slots().stream().map(Inventory::peek).collect(Collectors.toList());
        this.loader.createNode();
        this.node.node("Items").setList(ItemStack.class, items);
        this.node.node("Commands").set(commands);
        this.loader.save(this.node);
    }
}
