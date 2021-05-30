package io.github.catchyaintit.game.item.registry;

import io.github.catchyaintit.game.item.ServerItem;
import org.jetbrains.annotations.NotNull;
import xyz.nucleoid.leukocyte.util.TinyRegistry;

import java.util.Iterator;

public class ItemRegistry {
    public static TinyRegistry<ServerItem> REGISTRY = TinyRegistry.newStable();

    public static void register (String id, ServerItem item) {
        item.init(id);
        REGISTRY.register(id, item);
    }
    public static @NotNull Iterator<ServerItem> getItemsIterator() {
        return REGISTRY.iterator();
    }
}
