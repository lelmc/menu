package cn.lelmc.menu.config;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public class menusCounterSerializer implements TypeSerializer<menus> {
    @Override
    public menus deserialize(Type type, ConfigurationNode node) throws SerializationException {
        ItemStackSnapshot item = node.node("item").get(ItemStackSnapshot.class);
        return new menus(item);

    }

    @Override
    public void serialize(Type type, @Nullable menus obj, ConfigurationNode node) throws SerializationException {
        assert obj != null;
        node.node("item").set(ItemStackSnapshot.class, obj.getItem());
    }


}
