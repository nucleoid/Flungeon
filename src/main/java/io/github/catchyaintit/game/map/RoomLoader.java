package io.github.catchyaintit.game.map;

import io.github.catchyaintit.Flungeon;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.Plasmid;

import java.util.Collection;

public class RoomLoader {

    public static void load() {
        ResourceManagerHelper manager = ResourceManagerHelper.get(ResourceType.SERVER_DATA);

        manager.registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier(Plasmid.ID, Flungeon.ID);
            }

            @Override
            public void apply(ResourceManager manager) {
                Collection<Identifier> roomJson = manager.findResources("rooms", path -> path.endsWith(".json"));
                for (Identifier id : roomJson) {
                    System.out.println(id);
                }
            }
        });
    }
}
