package io.github.catchyaintit.game.map;

import io.github.catchyaintit.Flungeon;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.source.BiomeSource;
import xyz.nucleoid.plasmid.map.template.MapTemplate;
import xyz.nucleoid.plasmid.map.template.MapTemplateSerializer;
import xyz.nucleoid.plasmid.util.BlockBounds;

import java.io.IOException;

public class FlungeonMapGenerator {
    private FlungeonMapConfig config;
    public FlungeonMapGenerator(FlungeonMapConfig config) {
        this.config = config;
    }
    public FlungeonMap generate() {
        FlungeonMap map;
        MapTemplate template = null;

        try {
            template = MapTemplateSerializer.INSTANCE.loadFromResource(config.id);
            BlockBounds spawn = template.getMetadata().getFirstRegion("Spawn").getBounds();
            BlockBounds bounds = template.getMetadata().getFirstRegion("Floor1").getBounds();
            map = new FlungeonMap(config, template, spawn, bounds);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
