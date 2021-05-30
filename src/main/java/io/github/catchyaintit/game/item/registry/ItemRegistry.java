package io.github.catchyaintit.game.item.registry;

import io.github.catchyaintit.game.item.ServerItem;
import org.jetbrains.annotations.NotNull;
import xyz.nucleoid.leukocyte.util.TinyRegistry;

import java.util.Iterator;

public class ItemRegistry {
    static TinyRegistry<ServerItem> REGISTRY = TinyRegistry.newStable();

    public static void register (String id, ServerItem item) {
        REGISTRY.register(id, item);
    }
    public static @NotNull Iterator<ServerItem> getItems() {

        return REGISTRY.iterator();
    }
}
