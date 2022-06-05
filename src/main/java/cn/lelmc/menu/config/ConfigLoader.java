package cn.lelmc.menu.config;

import com.google.inject.Inject;
import io.leangen.geantyref.TypeToken;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.ConfigManager;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigLoader {

    public static ConfigLoader instance;
    private Config config;
    private final Path configFile;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode node;
    private final ConfigManager configManager;
    private final Path configDir;

    @Inject
    public ConfigLoader(@ConfigDir(sharedRoot = false) Path configDir, final ConfigManager configManager) throws IOException {
        this.configManager = configManager;
        this.configDir = configDir;

        // create dir directory
        configFile = configDir.resolve("setting.conf");
        if (!Files.exists(configDir)) {
            Files.createDirectory(configDir);
        }
        // create setting file
        if (!Files.exists(configFile)) {
            Files.createFile(configFile);
        }
        // try load config
        this.load();
    }

    public void load() throws ConfigurateException {
        //HoconConfigurationLoader.builder()

        //                .defaultOptions(ConfigurationOptions.defaults().serializers(configManager.serializers()))

        this.loader = HoconConfigurationLoader.builder()
                .defaultOptions(ConfigurationOptions.defaults().serializers(configManager.serializers()))
                .path(this.configFile)
                .build();
        this.node = loader.load();
        List<ItemStack> list = this.node.get(new TypeToken<List<ItemStack>>() {
        });
        this.node.set(list);
        this.config = this.node.get(TypeToken.get(Config.class), new Config());
        this.loader.save(node);
/*
        this.config = this.node.get(TypeToken.get(Config.class), new Config());
        this.save();
*
        /*this.loader = HoconConfigurationLoader.builder()
                .path(this.configFile)
                .build();
        this.rootNode = this.loader.load();
        this.config = this.rootNode.get(TypeToken.get(Config.class), new Config());
        this.save();*/
    }


    public void save() throws ConfigurateException {
        this.loader.save(this.node);
    }

    public Config getConfig() {
        return config;
    }

}